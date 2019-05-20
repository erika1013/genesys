package com.guandera.core.client.view;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.guandera.core.client.service.CuentaCobroService;
import com.guandera.core.server.service.CuentaCobroServiceImpl;
import com.guandera.core.shared.model.CuentaCobro;
import com.guandera.core.shared.model.CuentaCobroEstado;
import com.guandera.core.shared.model.Moneda;
import com.guandera.core.shared.model.Proveedor;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@ManagedBean(name = "cuentaCobroMB")
@SessionScoped
public class CuentaCobroManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private CuentaCobroService cuentaCobroServicio;
	private CuentaCobro itemSeleccionado;
	private List<CuentaCobro> listaItems = new ArrayList<CuentaCobro>();
	private boolean listing = false;

	private List<SelectItem> ProveedorItems;
	private Long proveedorid;
	private boolean listingProveedorItem = false;

	private List<SelectItem> estadoItems;
	private Long estadoid;
	private boolean listingEstadoItem = false;
	
	private List<SelectItem> monedaItems;
	private Long monedaid;
	private boolean listingMonedaItem = false;
	
	

	public CuentaCobroManagedBean() {
		cuentaCobroServicio = new CuentaCobroServiceImpl();

		inicializar();
	}

	public String actualizar() {

		try {
			
			CuentaCobroEstado cuentaCobroEstado = new CuentaCobroEstado();
			cuentaCobroEstado.setCuentaCobroEstadoid(estadoid);
			
			Moneda moneda = new Moneda();
			moneda.setMonedaid(monedaid);

			
			
			itemSeleccionado.setCuentaCobroEstado(cuentaCobroEstado);
			itemSeleccionado.setMoneda(moneda);
			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			cuentaCobroServicio.actualizar(itemSeleccionado);
			mensajeInfo("CuentaCobroUpdated");
			inicializar();
			verificarLista();
			return prepararLista();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String crear() {

		try {

			Proveedor proveedor = new Proveedor();
			proveedor.setProveedorid(proveedorid);
			
			CuentaCobroEstado cuentaCobroEstado = new CuentaCobroEstado();
			cuentaCobroEstado.setCuentaCobroEstadoid(estadoid);
			
			Moneda moneda = new Moneda();
			moneda.setMonedaid(monedaid);
			
			
			
			itemSeleccionado.setCuentaCobroEstado(cuentaCobroEstado);
			itemSeleccionado.setProveedor(proveedor);
			itemSeleccionado.setMoneda(moneda);
			
			itemSeleccionado.setNumeroCuenta(cuentaCobroServicio.siguienteRegistro());
			itemSeleccionado.setUsuarioCreacion(usuario());
			itemSeleccionado.setFechaCreacion(new Date());
			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			cuentaCobroServicio.crear(itemSeleccionado);

			mensajeInfo("CuentaCobroCreated");
			inicializar();
			verificarLista();

			return prepararLista();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			System.out.println(e.getMessage());
			return null;
		}

	}

	public String eliminar() {

		cuentaCobroServicio.eliminar(itemSeleccionado);
		listing = false;
		return prepararLista();
	}

	public CuentaCobro getItemSeleccionado() {
		return itemSeleccionado;
	}

	public List<CuentaCobro> getListaItems() {
		if (!listing) {
			listing = true;

			listaItems = cuentaCobroServicio.consultarTodos();

		}
		return listaItems;
	}

	public CuentaCobroService getCuentaCobroServicio() {
		return cuentaCobroServicio;
	}

	private void inicializar() {

		itemSeleccionado = new CuentaCobro();

	}

	public boolean isListing() {
		return listing;
	}

	public void listarCuentaCobro() {
		setListing(true);
		listaItems = cuentaCobroServicio.consultarTodos();
	}

	public String prepararConsulta() {
		return "Detalle";
	}

	public String prepararCreacion() {
		itemSeleccionado = new CuentaCobro();
		setProveedorid((long) 0);
		setMonedaid((long) 0);
		setEstadoid((long) 0);
		return "Crear";

	}

	public String prepararEdicion() {
		setProveedorid(itemSeleccionado.getProveedor().getProveedorid());
		setMonedaid(itemSeleccionado.getMoneda().getMonedaid());
		setEstadoid(itemSeleccionado.getCuentaCobroEstado().getCuentaCobroEstadoid());
		
		
		return "Editar";
	}

	public String prepararLista() {
		return "Lista";
	}

	@PostConstruct
	public void prerender() {
		inicializar();

	}

	public void setItemSeleccionado(CuentaCobro itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public void setListaItems(List<CuentaCobro> listaItems) {
		this.listaItems = listaItems;
	}

	public void setListing(boolean listing) {
		this.listing = listing;
	}

	public void setCuentaCobroServicio(CuentaCobroService cuentaCobroServicio) {
		this.cuentaCobroServicio = cuentaCobroServicio;
	}

	private void verificarLista() {
		if (listing) {
			this.listaItems = cuentaCobroServicio.consultarTodos();
		}
	}

	public List<SelectItem> getProveedorItems() {

		if (!listingProveedorItem) {
			listingProveedorItem = true;
			ProveedorItems = new ArrayList<SelectItem>();
			List<Proveedor> proveedorList = cuentaCobroServicio.consultarProveedores();

			for (Proveedor proveedor : proveedorList) {
				ProveedorItems.add(new SelectItem(proveedor.getProveedorid(),
						proveedor.getProveedornombre() + " - " + proveedor.getProveedoridentificacion()));

			}
		}

		return ProveedorItems;
	}

	public void setProveedorItems(List<SelectItem> proveedorItems) {
		ProveedorItems = proveedorItems;
	}

	public Long getProveedorid() {
		return proveedorid;
	}

	public void setProveedorid(Long proveedorid) {
		this.proveedorid = proveedorid;
	}

	public List<SelectItem> getEstadoItems() {

		if (!listingEstadoItem) {
			listingEstadoItem = true;
			estadoItems = new ArrayList<SelectItem>();
			List<CuentaCobroEstado> estadoList = cuentaCobroServicio.consultarEstados();

			for (CuentaCobroEstado estado : estadoList) {
				estadoItems.add(new SelectItem(estado.getCuentaCobroEstadoid(),
						estado.getCuentaCobroEstadoid() + " - " + estado.getCuentaCobroEstadoNombre()));

			}
		}

		return estadoItems;
	}

	public void setEstadoItems(List<SelectItem> estadoItems) {
		this.estadoItems = estadoItems;
	}

	public Long getEstadoid() {
		return estadoid;
	}

	public void setEstadoid(Long estadoid) {
		this.estadoid = estadoid;
	}

	public boolean isListingEstadoItem() {
		return listingEstadoItem;
	}

	public void setListingEstadoItem(boolean listingEstadoItem) {
		this.listingEstadoItem = listingEstadoItem;
	}

	public void imprimirCuentaCobro() {
		Document document = new Document(PageSize.LETTER);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		try {

			PdfWriter.getInstance(document, baos);
			document.open();

			DecimalFormat formateador = new DecimalFormat("###,###.##");
			DecimalFormat formateador2 = new DecimalFormat("#.###");
			PdfPCell cellT;
			PdfPCell cellvacio = new PdfPCell(new Paragraph("\n" + "\n" + "\n"));

			cellvacio.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellvacio.setUseVariableBorders(true);
			cellvacio.setBorderColor(BaseColor.WHITE);

			Image img = Image.getInstance("images/LOGO.png");
			img.setAlignment(Image.MIDDLE);
			img.scalePercent(30f);
			
			document.add(img);
			document.add(new Paragraph("\n"));

			PdfPTable tablaInfoEmpresa = new PdfPTable(1);
			tablaInfoEmpresa.setTotalWidth(new float[] { 500 });
			tablaInfoEmpresa.setLockedWidth(true);
			//cellT = new PdfPCell(new Paragraph("" + itemSeleccionado.getProveedor().getCompania().getCompanianombre(),
			//		FontFactory.getFont("arial", 12, Font.BOLD, BaseColor.BLACK)));
			//cellT.setHorizontalAlignment(Element.ALIGN_CENTER);
			//cellT.setUseVariableBorders(true);
			//cellT.setBorderColor(BaseColor.WHITE);
			//tablaInfoEmpresa.addCell(cellT);
			//cellT.setColspan(1);
			cellT = new PdfPCell(new Paragraph("NIT  " + itemSeleccionado.getProveedor().getCompania().getCompanianit() + " - " + + itemSeleccionado.getProveedor().getCompania().getCompaniadv(),
					FontFactory.getFont("arial", 14, Font.BOLD, BaseColor.BLACK)));
			cellT.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);
			tablaInfoEmpresa.addCell(cellT);
			cellT.setColspan(1);
			tablaInfoEmpresa.addCell(cellvacio);
			cellT.setColspan(1);
			cellT = new PdfPCell(new Paragraph("DEBE A", FontFactory.getFont("arial", 14, Font.BOLD, BaseColor.BLACK)));
			cellT.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);
			tablaInfoEmpresa.addCell(cellT);
			cellT.setColspan(1);
			tablaInfoEmpresa.addCell(cellvacio);
			cellT.setColspan(1);
			tablaInfoEmpresa.addCell(cellvacio);
			cellT.setColspan(1);
			cellT = new PdfPCell(new Paragraph("" + itemSeleccionado.getProveedor().getProveedornombre(),
					FontFactory.getFont("arial", 12, Font.BOLD, BaseColor.BLACK)));
			cellT.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);
			tablaInfoEmpresa.addCell(cellT);
			cellT.setColspan(1);
			Long identificacion = itemSeleccionado.getProveedor().getProveedoridentificacion();
			String identificacionP = formateador.format(identificacion);
			cellT = new PdfPCell(
					new Paragraph("" + identificacionP, FontFactory.getFont("arial", 12, Font.BOLD, BaseColor.BLACK)));
			cellT.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);
			tablaInfoEmpresa.addCell(cellT);
			cellT.setColspan(1);
			tablaInfoEmpresa.addCell(cellvacio);

			document.add(tablaInfoEmpresa);
			document.add(cellvacio);

			PdfPTable valor = new PdfPTable(2);

			valor.setTotalWidth(new float[] { 180, 320 });
			valor.setLockedWidth(true);

			cellT = new PdfPCell(
					new Paragraph("LA SUMA DE: ", FontFactory.getFont("arial", 12, Font.BOLD, BaseColor.BLACK)));
			cellT.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);
			valor.addCell(cellT);

			Double valor1 = itemSeleccionado.getValor();
			Long valorv = valor1.longValue();
			String ValorPago = formateador.format(valorv);
			String valorPagoL = formateador2.format(valorv);

			cellT = new PdfPCell(
					new Paragraph("$ " + ValorPago, FontFactory.getFont("arial", 12, Font.BOLD, BaseColor.BLACK)));
			cellT.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);

			valor.addCell(cellT);

			document.add(valor);

			PdfPTable valorLetras = new PdfPTable(1);

			valorLetras.setTotalWidth(new float[] { 500 });
			valorLetras.setLockedWidth(true);
			Convertir convertir = new Convertir();

			String vLetras = convertir.Convertir(valorPagoL, itemSeleccionado.getMoneda().getNombre());
			
			//System.out.print("---" + vLetras);
			cellT = new PdfPCell(
					new Paragraph("" + vLetras, FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
			cellT.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);
			valorLetras.addCell(cellT);
			cellT.setColspan(1);

			valorLetras.addCell(cellvacio);

			document.add(valorLetras);

			PdfPTable concepto = new PdfPTable(1);

			concepto.setTotalWidth(new float[] { 500 });
			concepto.setLockedWidth(true);

			cellT = new PdfPCell(new Paragraph("POR CONCEPTO DE:  " + itemSeleccionado.getConcepto(),
					FontFactory.getFont("arial", 12, Font.BOLD, BaseColor.BLACK)));
			cellT.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);
			concepto.addCell(cellT);
			cellT.setColspan(1);
			concepto.addCell(cellvacio);
			cellT.setColspan(1);
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date currenDate = itemSeleccionado.getFecha();
			String date = formatter.format(currenDate);

			cellT = new PdfPCell(
					new Paragraph(itemSeleccionado.getCiudad()+", " + date, FontFactory.getFont("arial", 12, Font.BOLD, BaseColor.BLACK)));
			cellT.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);
			concepto.addCell(cellT);
			cellT.setColspan(1);
			concepto.addCell(cellvacio);
			cellT.setColspan(1);

			cellT = new PdfPCell(
					new Paragraph("Atentamente;", FontFactory.getFont("arial", 12, Font.BOLD, BaseColor.BLACK)));
			cellT.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);
			concepto.addCell(cellT);
			concepto.addCell(cellvacio);

			document.add(concepto);
			PdfPTable firma = new PdfPTable(1);

			firma.setTotalWidth(new float[] { 500 });
			firma.setLockedWidth(true);

			cellT = new PdfPCell(new Paragraph("" + itemSeleccionado.getProveedor().getProveedornombre(),
					FontFactory.getFont("arial", 12, Font.BOLD, BaseColor.BLACK)));
			cellT.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);
			cellT.setColspan(1);

			firma.addCell(cellT);

			cellT = new PdfPCell(
					new Paragraph("" + identificacionP, FontFactory.getFont("arial", 12, Font.BOLD, BaseColor.BLACK)));
			cellT.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellT.setVerticalAlignment(Element.ALIGN_BOTTOM);
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);
			firma.addCell(cellT);
			
			
			cellT = new PdfPCell(new Paragraph("TEL." + itemSeleccionado.getProveedor().getProveedortelefono(),
					FontFactory.getFont("arial", 12, Font.BOLD, BaseColor.BLACK)));
			cellT.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);
			cellT.setColspan(1);

			firma.addCell(cellT);
			

			document.add(firma);

		} catch (Exception ex) {
			System.out.println("Error " + ex.getMessage());
			System.out.print(ex.getLocalizedMessage());
		}

		document.close();

		FacesContext context = FacesContext.getCurrentInstance();
		Object response = context.getExternalContext().getResponse();

		if (response instanceof HttpServletResponse) {
			HttpServletResponse hsr = (HttpServletResponse) response;

			hsr.setContentType("application/pdf");
			// hsr.setHeader("Content-disposition", "attachment");
			hsr.setHeader("Content-disposition", "inline=filename=Calificacion.pdf");
			hsr.setContentLength(baos.size());

			try {
				ServletOutputStream out = hsr.getOutputStream();
				baos.writeTo(out);
				out.flush();
			} catch (IOException ex) {
				System.out.println("Error " + ex.getMessage());
				System.out.print(ex.getLocalizedMessage());
			}

			context.responseComplete();

		}

	}

	public List<SelectItem> getMonedaItems() {
		
		
		if (!listingMonedaItem) {
			listingMonedaItem = true;
			monedaItems = new ArrayList<SelectItem>();
			List<Moneda> monedaList = cuentaCobroServicio.consultarMonedas();

			for (Moneda moneda : monedaList) {
				monedaItems.add(new SelectItem(moneda.getMonedaid(),
						moneda.getNombre() + "(" + moneda.getCodigo()+")"));

			}
		}
		
		
		
		
		
		
		return monedaItems;
	}

	public void setMonedaItems(List<SelectItem> monedaItems) {
		this.monedaItems = monedaItems;
	}

	public Long getMonedaid() {
		return monedaid;
	}

	public void setMonedaid(Long monedaid) {
		this.monedaid = monedaid;
	}

}
