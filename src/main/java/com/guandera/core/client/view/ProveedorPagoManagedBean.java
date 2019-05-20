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
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.guandera.core.client.service.ProveedorPagoService;
import com.guandera.core.client.view.Convertir;
import com.guandera.core.server.service.ProveedorPagoServiceImpl;
import com.guandera.core.shared.model.CuentaCobro;
import com.guandera.core.shared.model.PagoTipo;
import com.guandera.core.shared.model.Proveedor;
import com.guandera.core.shared.model.ProveedorPago;
import com.guandera.core.shared.model.ProveedorPagoEstado;
import com.guandera.core.shared.model.ProveedorPagoRetencion;
import com.guandera.core.shared.model.ProveedorRetencion;
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

@ManagedBean(name = "proveedorPagoMB")
@SessionScoped
public class ProveedorPagoManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private ProveedorPagoService proveedorPagoServicio;
	private ProveedorPago itemSeleccionado;
	private List<ProveedorPago> listaItems = new ArrayList<ProveedorPago>();
	private boolean listing = false;

	private List<Proveedor> listaProveedor = new ArrayList<Proveedor>();
	private List<SelectItem> proveedorItems;
	private boolean listingProveedorItem = false;
	private Proveedor proveedorSeleccionado;
	private Long proveedorid;

	public List<ProveedorRetencion> listaProveedorRetencion;
	private boolean listingProveedor = false;

	private ProveedorPagoEstado itemProveedorPagoEstado;
	private Long proveedorestadoid;
	private List<SelectItem> proveedorEstadoItems;
	private boolean listingProveedorEstado = false;

	private PagoTipo itemPagoTipo;
	private Long pagoTipoid;
	private List<SelectItem> pagoTipoItems;
	private boolean listingPpagoTipo = false;
	private List<ProveedorPagoRetencion> listaProveedorPagoRetencionItems = new ArrayList<ProveedorPagoRetencion>();
	private boolean listingProveedorPagoRetencion = false;
	private ProveedorPagoRetencion itemProveedorPagoRetencion;

	private Long numeroCuenta;
	private CuentaCobro cuentaCobro;
	private Long numeroIdentificacion;

	public ProveedorPagoManagedBean() {
		proveedorPagoServicio = new ProveedorPagoServiceImpl();

		inicializar();
	}

	public String actualizar() {

		try {

			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			proveedorPagoServicio.actualizar(itemSeleccionado);
			mensajeInfo("ProveedorPagoUpdated");
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
			ProveedorPagoEstado estado = new ProveedorPagoEstado();
			estado.setEstadoid(proveedorestadoid);
			itemSeleccionado.setEstado(estado);

			PagoTipo pago = new PagoTipo();
			pago.setPagoTipoid(pagoTipoid);
			itemSeleccionado.setPagoTipo(pago);
			itemSeleccionado.setUsuarioCreacion(usuario());
			itemSeleccionado.setFechaCreacion(new Date());
			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());
			itemSeleccionado.setNumerocuentacobro(cuentaCobro.getNumeroCuenta());
			itemSeleccionado.setPagovalortotal(cuentaCobro.getValor());
			itemSeleccionado.setConcepto(cuentaCobro.getConcepto());
			itemSeleccionado.setProveedor(proveedorSeleccionado);

			proveedorPagoServicio.crear(itemSeleccionado, proveedorSeleccionado);

			mensajeInfo("ProveedorPagoCreated");
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

		proveedorPagoServicio.eliminar(itemSeleccionado);
		listing = false;
		return prepararLista();
	}

	public ProveedorPago getItemSeleccionado() {
		return itemSeleccionado;
	}

	public List<ProveedorPago> getListaItems() {
		if (!listing) {
			listing = true;

			listaItems = proveedorPagoServicio.consultarTodos();

		}
		return listaItems;
	}

	public ProveedorPagoService getProveedorPagoServicio() {
		return proveedorPagoServicio;
	}

	private void inicializar() {

		itemSeleccionado = new ProveedorPago();

	}

	public boolean isListing() {
		return listing;
	}

	public void listarProveedorPago() {
		setListing(true);
		listaItems = proveedorPagoServicio.consultarTodos();
	}

	public String prepararConsulta() {
		listingProveedorPagoRetencion = false;
		return "Detalle";
	}

	public String prepararPagoProveedor() {
		proveedorSeleccionado = new Proveedor();

		return "CrearBuscar";

	}

	public String prepararCreacion() {
		itemSeleccionado = new ProveedorPago();
		proveedorid = (long) 0;
		proveedorestadoid = (long) 0;
		pagoTipoid = (long) 0;

		return "Crear";
	}

	public String prepararEdicion() {
		return "Editar";
	}

	public String prepararLista() {
		return "Lista";
	}

	@PostConstruct
	public void prerender() {
		inicializar();

	}

	public void setItemSeleccionado(ProveedorPago itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public void setListaItems(List<ProveedorPago> listaItems) {
		this.listaItems = listaItems;
	}

	public void setListing(boolean listing) {
		this.listing = listing;
	}

	public void setProveedorPagoServicio(ProveedorPagoService proveedorPagoServicio) {
		this.proveedorPagoServicio = proveedorPagoServicio;
	}

	private void verificarLista() {
		if (listing) {
			this.listaItems = proveedorPagoServicio.consultarTodos();
		}
	}

	public Long getProveedorid() {
		return proveedorid;
	}

	public void setProveedorid(Long proveedorid) {
		this.proveedorid = proveedorid;
	}

	public List<SelectItem> getProveedorItems() {
		if (!listingProveedorItem) {
			listingProveedorItem = true;
			proveedorItems = new ArrayList<SelectItem>();
			List<Proveedor> proveedorList = proveedorPagoServicio.consultarProveedores();

			for (Proveedor proveedor : proveedorList) {
				proveedorItems.add(new SelectItem(proveedor.getProveedorid(), proveedor.getProveedornombre()));

			}
		}

		return proveedorItems;
	}

	public void setProveedorItems(List<SelectItem> proveedorItems) {
		this.proveedorItems = proveedorItems;
	}

	public List<SelectItem> getproveedorEstadoItems() {
		if (!listingProveedorEstado) {
			listingProveedorEstado = true;
			proveedorEstadoItems = new ArrayList<SelectItem>();
			List<ProveedorPagoEstado> proveedorEstadoList = proveedorPagoServicio.consultarEstado();

			for (ProveedorPagoEstado proveedorEstadoPago : proveedorEstadoList) {
				proveedorEstadoItems.add(new SelectItem(proveedorEstadoPago.getEstadoid(),
						proveedorEstadoPago.getEstadoid() + " - " + proveedorEstadoPago.getNombre()));

			}
		}

		return proveedorEstadoItems;
	}

	public void setProveedorEstadoItems(List<SelectItem> proveedorEstadoItems) {
		this.proveedorEstadoItems = proveedorEstadoItems;
	}

	public List<Proveedor> getListaProveedor() {
		if (!listingProveedor) {

			listingProveedor = true;
			listaProveedor = proveedorPagoServicio.consultarProveedores();
		}
		return listaProveedor;
	}

	public void setListaProveedor(List<Proveedor> listaProveedor) {
		this.listaProveedor = listaProveedor;
	}

	public Proveedor getProveedorSeleccionado() {
		return proveedorSeleccionado;
	}

	public void setProveedorSeleccionado(Proveedor proveedorSeleccionado) {
		this.proveedorSeleccionado = proveedorSeleccionado;
	}

	public ProveedorPagoEstado getItemProveedorPagoEstado() {
		return itemProveedorPagoEstado;
	}

	public void setItemProveedorPagoEstado(ProveedorPagoEstado itemProveedorPagoEstado) {
		this.itemProveedorPagoEstado = itemProveedorPagoEstado;
	}

	public Long getProveedorestadoid() {
		return proveedorestadoid;
	}

	public void setProveedorestadoid(Long proveedorestadoid) {
		this.proveedorestadoid = proveedorestadoid;
	}

	public String buscarProveedor() {
		proveedorSeleccionado = new Proveedor();

		return "Busqueda";
	}

	public String BuscarCuentaCobro() {
		setProveedorSeleccionado(new Proveedor());
		CuentaCobro cuentaCobro1 = proveedorPagoServicio.consultarCuentaCobroProveedor(numeroIdentificacion,
				numeroCuenta);
		ProveedorRetencion proveedorretencion1 = proveedorPagoServicio.consultarRetencion(numeroIdentificacion);
		try {
			if (numeroIdentificacion != null && numeroCuenta != null) {
				if (cuentaCobro1 != null) {
					if (proveedorretencion1 != null) {
						setProveedorSeleccionado(proveedorPagoServicio.consultarProveedor(numeroIdentificacion));
						setCuentaCobro(proveedorPagoServicio.consultarCuentaCobroProveedor(numeroIdentificacion,
								numeroCuenta));

						itemSeleccionado = new ProveedorPago();
						proveedorid = (long) 0;
						proveedorestadoid = (long) 0;
						pagoTipoid = (long) 0;
						return "Crear";
					} else {
						mensaje("No existen retenciones para el proveedor ");
						return "Busqueda";
					}
				} else {
					mensaje("No hay cuenta de cobro para el proveedor");
					return "Busqueda";
				}

			} else {
				mensaje("Cuenta de cobro o numero de Identificacion no validos ");
				return "Busqueda";
			}
		} catch (Exception e) {
			mensaje("Verifique n�mero de cuenta y/o n�mero identificacion");
			return "Busqueda";
		}

	}

	public Long getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(Long numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public Long getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	public void setNumeroIdentificacion(Long numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}

	public CuentaCobro getCuentaCobro() {
		return cuentaCobro;
	}

	public void setCuentaCobro(CuentaCobro cuentaCobro) {
		this.cuentaCobro = cuentaCobro;
	}

	public PagoTipo getItemPagoTipo() {
		return itemPagoTipo;
	}

	public void setItemPagoTipo(PagoTipo itemPagoTipo) {
		this.itemPagoTipo = itemPagoTipo;
	}

	public Long getPagoTipoid() {
		return pagoTipoid;
	}

	public void setPagoTipoid(Long pagoTipoid) {
		this.pagoTipoid = pagoTipoid;
	}

	public List<SelectItem> getPagoTipoItems() {

		if (!listingPpagoTipo) {
			listingPpagoTipo = true;
			pagoTipoItems = new ArrayList<SelectItem>();
			List<PagoTipo> pagotipoList = proveedorPagoServicio.consultarTipoPago();

			for (PagoTipo pagoTipo : pagotipoList) {
				pagoTipoItems.add(new SelectItem(pagoTipo.getPagoTipoid(),
						pagoTipo.getPagoTipoNumero() + " - " + pagoTipo.getPagoTiponombre()));

			}
		}
		return pagoTipoItems;
	}

	public void setPagoTipoItems(List<SelectItem> pagoTipoItems) {
		this.pagoTipoItems = pagoTipoItems;
	}

	public boolean isListingPpagoTipo() {
		return listingPpagoTipo;
	}

	public void setListingPpagoTipo(boolean listingPpagoTipo) {
		this.listingPpagoTipo = listingPpagoTipo;
	}

	public List<ProveedorPagoRetencion> getListaProveedorPagoRetencionItems() {

		if (!listingProveedorPagoRetencion) {
			listingProveedorPagoRetencion = true;
			listaProveedorPagoRetencionItems = proveedorPagoServicio
					.consultarProveedorPagoRetencion(itemSeleccionado.getPagoproveedor());

		}
		return listaProveedorPagoRetencionItems;
	}

	public void setListaProveedorPagoRetencionItems(List<ProveedorPagoRetencion> listaProveedorPagoRetencionItems) {
		this.listaProveedorPagoRetencionItems = listaProveedorPagoRetencionItems;
	}

	public ProveedorPagoRetencion getItemProveedorPagoRetencion() {
		return itemProveedorPagoRetencion;
	}

	public void setItemProveedorPagoRetencion(ProveedorPagoRetencion itemProveedorPagoRetencion) {
		this.itemProveedorPagoRetencion = itemProveedorPagoRetencion;
	}

	public String prepararConsultaProveedorPagoRetencion() {
		return "DetalleProveedorPagoRetencion";
	}

	public void imprimirPagoProveedor() {
		Document document = new Document(PageSize.LETTER);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		try {

			PdfWriter.getInstance(document, baos);
			document.open();

			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date currenDate = itemSeleccionado.getFecha();
			String fecha = formatter.format(currenDate);

			DecimalFormat formateador = new DecimalFormat("###,###.##");
			DecimalFormat formateador2 = new DecimalFormat("#.###");
			
			Image img = Image.getInstance("images/LOGO.png");
			img.setAlignment(Image.MIDDLE);
			img.scalePercent(30f);
			
			document.add(img);
			

			// Celda Normal
			PdfPCell cellT;

			PdfPTable tablaInfoEmpresa = new PdfPTable(1);

			tablaInfoEmpresa.setTotalWidth(new float[] { 500 });
			tablaInfoEmpresa.setLockedWidth(true);

			//cellT = new PdfPCell(new Paragraph("" + itemSeleccionado.getProveedor().getCompania().getCompanianombre(),
			//		FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
			//cellT.setHorizontalAlignment(Element.ALIGN_CENTER);
			//cellT.setUseVariableBorders(true);
			//cellT.setBorderColor(BaseColor.WHITE);
			//tablaInfoEmpresa.addCell(cellT);
			//cellT.setColspan(1);
			cellT = new PdfPCell(new Paragraph("NIT  " + itemSeleccionado.getProveedor().getCompania().getCompanianit()+" - " + + itemSeleccionado.getProveedor().getCompania().getCompaniadv(),
					FontFactory.getFont("arial", 12, Font.BOLD, BaseColor.BLACK)));
			cellT.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);
			tablaInfoEmpresa.addCell(cellT);

			document.add(tablaInfoEmpresa);
			document.add(new Paragraph("\n" + "\n" + "\n"));

			PdfPTable tablaFechaCiudad = new PdfPTable(2);

			tablaFechaCiudad.setTotalWidth(new float[] { 200, 300 });
			tablaFechaCiudad.setLockedWidth(true);

			cellT = new PdfPCell(
					new Paragraph("CIUDAD Y FECHA", FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
			cellT.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);
			tablaFechaCiudad.addCell(cellT);

			cellT = new PdfPCell(new Paragraph("" + itemSeleccionado.getCiudad() + ", " + fecha,
					FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
			cellT.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);
			tablaFechaCiudad.addCell(cellT);
			document.add(tablaFechaCiudad);

			document.add(new Paragraph("\n" + "\n"));

			PdfPTable adquiereServicios = new PdfPTable(2);

			adquiereServicios.setTotalWidth(new float[] { 200, 300 });
			adquiereServicios.setLockedWidth(true);
			cellT = new PdfPCell(new Paragraph("ADQUIRIENTE DE LOS SERVICIOS",
					FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
			cellT.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);
			adquiereServicios.addCell(cellT);

			cellT = new PdfPCell(new Paragraph("" + itemSeleccionado.getProveedor().getCompania().getCompanianombre(),
					FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
			cellT.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);
			cellT.setColspan(2);

			adquiereServicios.addCell(cellT);

			cellT = new PdfPCell(new Paragraph("NIT:", FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
			cellT.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);
			adquiereServicios.addCell(cellT);

			cellT = new PdfPCell(new Paragraph("" + itemSeleccionado.getProveedor().getCompania().getCompanianit(),
					FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
			cellT.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);
			adquiereServicios.addCell(cellT);

			document.add(adquiereServicios);
			document.add(new Paragraph("\n" + "\n"));
			// Beneficiario
			PdfPTable beneficiario = new PdfPTable(2);

			beneficiario.setTotalWidth(new float[] { 200, 300 });
			beneficiario.setLockedWidth(true);

			cellT = new PdfPCell(new Paragraph("BENEFICIARIO DEL PAGO:",
					FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
			cellT.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);
			beneficiario.addCell(cellT);

			cellT = new PdfPCell(new Paragraph("" + itemSeleccionado.getProveedor().getProveedornombre(),
					FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
			cellT.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);
			cellT.setColspan(2);

			beneficiario.addCell(cellT);

			cellT = new PdfPCell(new Paragraph(
					"" + itemSeleccionado.getProveedor().getTipoIdentificacion().getTipoidentificacionnombre(),
					FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
			cellT.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);
			beneficiario.addCell(cellT);

			cellT = new PdfPCell(new Paragraph("" + itemSeleccionado.getProveedor().getProveedoridentificacion(),
					FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
			cellT.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);
			beneficiario.addCell(cellT);
			document.add(beneficiario);
			document.add(new Paragraph("\n" + "\n"));
			// concepto
			PdfPTable concepto = new PdfPTable(2);

			concepto.setTotalWidth(new float[] { 200, 300 });
			concepto.setLockedWidth(true);

			cellT = new PdfPCell(
					new Paragraph("CONCEPTO:", FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
			cellT.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);
			concepto.addCell(cellT);

			cellT = new PdfPCell(new Paragraph("" + itemSeleccionado.getConcepto(),
					FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
			cellT.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);
			concepto.addCell(cellT);
			document.add(concepto);
			document.add(new Paragraph("\n" + "\n"));

			Double valor1 = itemSeleccionado.getPagovalortotal();
			Double valor2 = itemSeleccionado.getPagovalorneto();
			Long valorNeto = valor2.longValue();
			Long valorv = valor1.longValue();
			String ValorNeto = formateador.format(valorNeto);
			String ValorPago = formateador.format(valorv);
			String valorPagoL = formateador2.format(valorv);

			PdfPTable valorServicios = new PdfPTable(2);

			valorServicios.setTotalWidth(new float[] { 200, 300 });
			valorServicios.setLockedWidth(true);

			cellT = new PdfPCell(new Paragraph("VALOR DE LOS SERVICIOS:",
					FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
			cellT.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);
			Convertir convertir = new Convertir();
			valorServicios.addCell(cellT);
			String vLetras = convertir.Convertir(valorPagoL, "PESOS");
			cellT = new PdfPCell(new Paragraph(vLetras, FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
			cellT.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);

			cellT.setColspan(2);

			valorServicios.addCell(cellT);
			cellT = new PdfPCell(new Paragraph(""));
			cellT.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);
			valorServicios.addCell(cellT);

			cellT = new PdfPCell(
					new Paragraph("$ " + ValorPago, FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
			cellT.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);
			valorServicios.addCell(cellT);
			document.add(valorServicios);
			document.add(new Paragraph("\n" + "\n"));

			PdfPTable impuestosEmpresa = new PdfPTable(4);

			impuestosEmpresa.setTotalWidth(new float[] { 50, 150, 50, 250 });
			impuestosEmpresa.setLockedWidth(true);

			for (ProveedorPagoRetencion proveedorPagoRtencion : listaProveedorPagoRetencionItems) {
				if (proveedorPagoRtencion.getImpuestoConcepto().getObligacionde() == 1) {
					Double valor3 = proveedorPagoRtencion.getValor();
					Long valor = valor3.longValue();
					String Valorimpuesto = formateador.format(valor);
					cellT = new PdfPCell(
							new Paragraph("", FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
					cellT.setUseVariableBorders(true);
					cellT.setBorderColor(BaseColor.WHITE);
					impuestosEmpresa.addCell(cellT);

					cellT = new PdfPCell(
							new Paragraph("" + proveedorPagoRtencion.getImpuestoConcepto().getImpuestoconceptonombre(),
									FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
					cellT.setUseVariableBorders(true);
					cellT.setBorderColor(BaseColor.WHITE);
					impuestosEmpresa.addCell(cellT);
					cellT = new PdfPCell(new Paragraph("" + proveedorPagoRtencion.getFactor() + "%",
							FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.RED)));
					cellT.setUseVariableBorders(true);
					cellT.setBorderColor(BaseColor.WHITE);
					impuestosEmpresa.addCell(cellT);

					cellT = new PdfPCell(new Paragraph("$ " + Valorimpuesto,
							FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
					cellT.setUseVariableBorders(true);
					cellT.setBorderColor(BaseColor.WHITE);
					impuestosEmpresa.addCell(cellT);
				}
			}
			document.add(impuestosEmpresa);
			// ImpuestoProveedor

			PdfPTable impuestosProveedor = new PdfPTable(5);

			impuestosProveedor.setTotalWidth(new float[] { 50, 70, 80, 50, 250 });
			impuestosProveedor.setLockedWidth(true);

			for (ProveedorPagoRetencion proveedorPagoRtencion : listaProveedorPagoRetencionItems) {
				if (proveedorPagoRtencion.getImpuestoConcepto().getObligacionde() == 2) {
					Double valor3 = proveedorPagoRtencion.getValor();
					Long valor = valor3.longValue();
					String Valorimpuesto = formateador.format(valor);
					cellT = new PdfPCell(
							new Paragraph("", FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
					cellT.setUseVariableBorders(true);
					cellT.setBorderColor(BaseColor.WHITE);
					impuestosProveedor.addCell(cellT);

					cellT = new PdfPCell(
							new Paragraph("RETENCION", FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
					cellT.setUseVariableBorders(true);
					cellT.setBorderColor(BaseColor.WHITE);
					impuestosProveedor.addCell(cellT);

					cellT = new PdfPCell(
							new Paragraph("" + proveedorPagoRtencion.getImpuestoConcepto().getImpuestoconceptonombre(),
									FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
					cellT.setUseVariableBorders(true);
					cellT.setBorderColor(BaseColor.WHITE);
					impuestosProveedor.addCell(cellT);
					cellT = new PdfPCell(new Paragraph("" + proveedorPagoRtencion.getFactor() + "%",
							FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.RED)));
					cellT.setUseVariableBorders(true);
					cellT.setBorderColor(BaseColor.WHITE);
					impuestosProveedor.addCell(cellT);

					cellT = new PdfPCell(new Paragraph("$ " + Valorimpuesto,
							FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
					cellT.setUseVariableBorders(true);
					cellT.setBorderColor(BaseColor.WHITE);
					impuestosProveedor.addCell(cellT);
				}
			}
			document.add(impuestosProveedor);

			PdfPTable neto = new PdfPTable(4);

			neto.setTotalWidth(new float[] { 50, 150, 50, 250 });
			neto.setLockedWidth(true);
			cellT = new PdfPCell(
					new Paragraph("" + "\n", FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
			cellT.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);
			neto.addCell(cellT);

			cellT = new PdfPCell(
					new Paragraph("NETO A PAGAR" + "\n", FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
			cellT.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);
			cellT.setBorderColorBottom(BaseColor.BLUE);
			neto.addCell(cellT);

			cellT = new PdfPCell(new Paragraph(""));
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);
			neto.addCell(cellT);

			cellT = new PdfPCell(new Paragraph("$ " + ValorNeto + "\n",
					FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
			cellT.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);
			neto.addCell(cellT);
			document.add(neto);
			document.add(new Paragraph("\n" + "\n"));

			PdfPTable firma = new PdfPTable(2);

			firma.setTotalWidth(new float[] { 200, 300 });
			firma.setLockedWidth(true);

			cellT = new PdfPCell(new Paragraph("FIRMA " + "\n" + "\n",
					FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);
			firma.addCell(cellT);

			cellT = new PdfPCell(new Paragraph(
					"" + itemSeleccionado.getProveedor().getProveedornombre() + "\n"
							+ itemSeleccionado.getProveedor().getProveedoridentificacion(),
					FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
			cellT.setHorizontalAlignment(Element.ALIGN_CENTER);

			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);
			cellT.setBorderColorTop(BaseColor.BLACK);
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

}