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

import com.guandera.core.client.service.FacturaService;
import com.guandera.core.server.service.FacturaServiceImpl;
import com.guandera.core.shared.model.CentroCostos;
import com.guandera.core.shared.model.Cliente;
import com.guandera.core.shared.model.ClienteContacto;
import com.guandera.core.shared.model.Compania;
import com.guandera.core.shared.model.Factura;
import com.guandera.core.shared.model.FacturaDetalle;
import com.guandera.core.shared.model.FacturaEstado;
import com.guandera.core.shared.model.Sede;
import com.guandera.talento.nomina.shared.model.NominaPagoDetalle;
import com.guandera.talento.nomina.shared.model.NominaPagoNovedad;
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

@ManagedBean(name = "facturaMB")
@SessionScoped
public class FacturaManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private FacturaService facturaServicio;
	private Factura itemSeleccionado;
	private List<Factura> listaItems = new ArrayList<Factura>();
	private boolean listing = false;

	private List<Cliente> listaClientes = new ArrayList<Cliente>();
	private Cliente clienteSeleccionado;
	private boolean listingCliente = false;

	private FacturaDetalle itemFacturaDetalle;
	private List<FacturaDetalle> listaFacturaDetalleItems = new ArrayList<FacturaDetalle>();
	private boolean listingFacturaDetalle = false;

	private Long companiaid;
	private List<SelectItem> companiaItems;
	private boolean listingCompania;

	private Long sedeid;
	private List<SelectItem> sedeItems;

	private Long clienteid;
	private List<SelectItem> clienteItems;
	private boolean listingClienteItem = false;

	private Long contactoid;
	private List<SelectItem> contactoItems;

	private Long centroCostosid;
	private List<SelectItem> centroCostosItems;

	private Long facturaEstadoid;
	private List<SelectItem> facturaEstadoItems;
	private boolean listingEstado = false;

	public FacturaManagedBean() {
		facturaServicio = new FacturaServiceImpl();

		inicializar();
	}

	public String actualizar() {

		try {

			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			facturaServicio.actualizar(itemSeleccionado);
			mensajeInfo("FacturaUpdated");
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

			facturaEstadoid = (long) 1; // Factura Emitida

			Sede sede = new Sede();
			sede.setSedeid(sedeid);

			Cliente cliente = new Cliente();
			cliente.setClienteid(clienteid);

			ClienteContacto contacto = new ClienteContacto();
			contacto.setContactoid(contactoid);

			CentroCostos centro = new CentroCostos();
			centro.setCentroCostosid(centroCostosid);

			Compania compania = new Compania();
			compania.setCompaniaid(companiaid);

			FacturaEstado facturaEstado = new FacturaEstado();
			facturaEstado.setFacturaEstadoid(facturaEstadoid);

			itemSeleccionado.setUsuarioCreacion(usuario());
			itemSeleccionado.setFechaCreacion(new Date());
			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());
			itemSeleccionado.setEstado(facturaEstado);

			itemSeleccionado.setCompania(compania);
			itemSeleccionado.setSede(sede);
			itemSeleccionado.setCentroCostos(centro);

			itemSeleccionado.setCliente(cliente);
			itemSeleccionado.setContacto(contacto);

			itemSeleccionado.setSubtotal(0D);
			itemSeleccionado.setValorTotal(0D);
			itemSeleccionado.setValorIva(0D);

			facturaServicio.crear(itemSeleccionado);

			mensajeInfo("FacturaCreated");
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

		facturaServicio.eliminar(itemSeleccionado);
		listing = false;
		return prepararLista();
	}

	public Factura getItemSeleccionado() {
		return itemSeleccionado;
	}

	public List<Factura> getListaItems() {
		if (!listing) {
			listing = true;

			listaItems = facturaServicio.consultarTodos();

		}
		return listaItems;
	}

	public FacturaService getFacturaServicio() {
		return facturaServicio;
	}

	private void inicializar() {

		itemSeleccionado = new Factura();

	}

	public boolean isListing() {
		return listing;
	}

	public void listarFactura() {
		setListing(true);
		listaItems = facturaServicio.consultarTodos();
	}

	public String prepararConsulta() {
		listing = false;
		listingFacturaDetalle = false;
		return "Detalle";
	}

	public String prepararCreacionFactura() {
		clienteSeleccionado = new Cliente();

		return "BuscarCliente";

	}

	public String prepararCreacion() {

		itemSeleccionado = new Factura();

		facturaEstadoid = (long) 0;
		clienteid = (long) 0;

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

	public void setItemSeleccionado(Factura itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public void setListaItems(List<Factura> listaItems) {
		this.listaItems = listaItems;
	}

	public void setListing(boolean listing) {
		this.listing = listing;
	}

	public void setFacturaServicio(FacturaService facturaServicio) {
		this.facturaServicio = facturaServicio;
	}

	private void verificarLista() {
		if (listing) {
			this.listaItems = facturaServicio.consultarTodos();
		}
	}

	public Long getClienteid() {
		return clienteid;
	}

	public void setClienteid(Long clienteid) {
		this.clienteid = clienteid;
	}

	public List<SelectItem> getClienteItems() {
		if (!listingClienteItem) {
			listingClienteItem = true;
			clienteItems = new ArrayList<SelectItem>();
			List<Cliente> clienteList = facturaServicio.consultarCliente();

			for (Cliente cliente : clienteList) {
				clienteItems.add(new SelectItem(cliente.getClienteid(), cliente.getClientenombre()));

			}
		}

		return clienteItems;
	}

	public void setClienteItems(List<SelectItem> clienteItems) {
		this.clienteItems = clienteItems;
	}

	public Long getFacturaEstadoid() {
		return facturaEstadoid;
	}

	public void setFacturaEstadoid(Long facturaEstadoid) {
		this.facturaEstadoid = facturaEstadoid;
	}

	public List<SelectItem> getFacturaEstadoItems() {
		if (!listingEstado) {
			listingEstado = true;
			facturaEstadoItems = new ArrayList<SelectItem>();
			List<FacturaEstado> facturaEstadoList = facturaServicio.consultarEstados();

			for (FacturaEstado facturaEstado : facturaEstadoList) {
				facturaEstadoItems.add(new SelectItem(facturaEstado.getFacturaEstadoid(),
						facturaEstado.getFacturaEstadoid() + " - " + facturaEstado.getFacturaEstadoNombre()));

			}
		}

		return facturaEstadoItems;
	}

	public void setFacturaEstadoItems(List<SelectItem> facturaEstadoItems) {
		this.facturaEstadoItems = facturaEstadoItems;
	}

	public FacturaDetalle getItemFacturaDetalle() {
		return itemFacturaDetalle;
	}

	public void setItemFacturaDetalle(FacturaDetalle itemFacturaDetalle) {
		this.itemFacturaDetalle = itemFacturaDetalle;
	}

	public List<FacturaDetalle> getListaFacturaDetalleItems() {
		if (!listingFacturaDetalle) {
			listingFacturaDetalle = true;
			listaFacturaDetalleItems = facturaServicio.consultarFacturaDetalle(itemSeleccionado.getFacturaid());

		}
		return listaFacturaDetalleItems;
	}

	public void setListaFacturaDetalleItems(List<FacturaDetalle> listaFacturaDetalleItems) {
		this.listaFacturaDetalleItems = listaFacturaDetalleItems;
	}

	public String prepararConsultaFacturaDetalle() {
		return "FacturaDetalleDetalle";
	}

	public String prepararCreacionFacturaDetalle() {
		itemFacturaDetalle = new FacturaDetalle();
		return "FacturaDetalleCrear";
	}

	public String prepararEdicionFacturaDetalle() {
		return "FacturaDetalleEditar";
	}

	public String prepararListaFacturaDetalle() {
		return "FacturaDetalleLista";
	}

	public String crearFacturaDetalle() {

		try {

			itemFacturaDetalle.setFactura(itemSeleccionado);

			itemFacturaDetalle.setUsuarioCreacion(usuario());
			itemFacturaDetalle.setFechaCreacion(new Date());
			itemFacturaDetalle.setUsuarioModificacion(usuario());
			itemFacturaDetalle.setFechaModificacion(new Date());

			facturaServicio.crearFacturaDetalle(itemFacturaDetalle);

			mensajeInfo("FacturaDetalleCreated");
			return prepararConsulta();

		} catch (Exception e) {

			mensajeError("PersistenceErrorOccured");
			return null;

		}

	}

	public String actualizarFacturaDetalle() {

		try {

			itemFacturaDetalle.setUsuarioModificacion(usuario());
			itemFacturaDetalle.setFechaModificacion(new Date());
			facturaServicio.actualizarFacturaDetalle(itemFacturaDetalle);

			mensajeInfo("FacturaDetalleUpdated");
			return prepararConsulta();

		} catch (Exception e) {

			mensajeError("PersistenceErrorOccured");
			return null;

		}

	}

	public String eliminarFacturaDetalle() {

		try {

			facturaServicio.eliminarFacturaDetalle(itemFacturaDetalle);

			mensajeInfo("FacturaDetalleDeleted");
			return prepararConsulta();

		} catch (Exception e) {

			mensajeError("PersistenceErrorOccured");
			return null;

		}

	}

	public List<Cliente> getListaClientes() {
		if (!listingCliente) {

			listingCliente = true;
			listaClientes = facturaServicio.consultarCliente();
		}
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public Cliente getClienteSeleccionado() {
		return clienteSeleccionado;
	}

	public void setClienteSeleccionado(Cliente clienteSeleccionado) {
		this.clienteSeleccionado = clienteSeleccionado;
	}

	public List<SelectItem> getCompaniaItems() {

		if (!listingCompania) {
			listingCompania = true;
			companiaItems = new ArrayList<SelectItem>();
			List<Compania> companiaList = facturaServicio.consultarCompanias();

			for (Compania compania : companiaList) {
				companiaItems.add(new SelectItem(compania.getCompaniaid(), compania.getCompanianombre()));

			}
		}

		return companiaItems;
	}

	public void setCompaniaItems(List<SelectItem> companiaItems) {
		this.companiaItems = companiaItems;
	}

	public Long getCompaniaid() {
		return companiaid;
	}

	public void setCompaniaid(Long companiaid) {
		this.companiaid = companiaid;
	}

	public void imprimirFactura() {
		Document document = new Document(PageSize.LETTER);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		try {

			PdfWriter.getInstance(document, baos);
			document.open();

			Image img = Image.getInstance("images/logo.png");
			img.setAlignment(Image.MIDDLE);
			DecimalFormat formateador = new DecimalFormat("###,###.##");
			DecimalFormat formateador2 = new DecimalFormat("#.###");

			// Formateando la fecha:

			DateFormat formatter = new SimpleDateFormat("yyyy");
			DateFormat formatter2 = new SimpleDateFormat("MM");
			DateFormat formatter3 = new SimpleDateFormat("dd");

			PdfPCell cellfin = new PdfPCell(
					new Paragraph(" ", FontFactory.getFont("arial", 6, Font.NORMAL, BaseColor.BLACK)));
			cellfin.setBorderColor(BaseColor.WHITE);
			cellfin.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellfin.setUseVariableBorders(true);
			cellfin.setBorderColorRight(BaseColor.BLACK);

			PdfPCell cellInicio = new PdfPCell(
					new Paragraph(" ", FontFactory.getFont("arial", 6, Font.NORMAL, BaseColor.BLACK)));
			cellInicio.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellInicio.setBorderColor(BaseColor.WHITE);
			cellInicio.setUseVariableBorders(true);
			cellInicio.setBorderColorLeft(BaseColor.BLACK);

			Date currenDate = itemSeleccionado.getFacturafecha();
			String anno = formatter.format(currenDate);
			String mes = formatter2.format(currenDate);
			String dia = formatter3.format(currenDate);

			PdfPCell cellT;
			PdfPTable tablaparteFIN = new PdfPTable(2);
			tablaparteFIN.setTotalWidth(new float[] { 250, 250 });
			tablaparteFIN.setLockedWidth(true);

			cellT = new PdfPCell(new Paragraph(""));
			cellT.setBorderColor(BaseColor.WHITE);
			cellT.setUseVariableBorders(true);
			cellT.setBorderColorLeft(BaseColor.BLACK);
			cellT.setBorderColorBottom(BaseColor.BLACK);
			tablaparteFIN.addCell(cellT);
			cellT = new PdfPCell(new Paragraph(" "));
			cellT.setBorderColor(BaseColor.WHITE);
			cellT.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellT.setUseVariableBorders(true);
			cellT.setBorderColorRight(BaseColor.BLACK);
			cellT.setBorderColorBottom(BaseColor.BLACK);
			tablaparteFIN.addCell(cellT);
			PdfPTable tablainicio = new PdfPTable(2);

			tablainicio.setTotalWidth(new float[] { 300, 200 });
			tablainicio.setLockedWidth(true);

			cellT = new PdfPCell(new Paragraph(""));
			cellT.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);
			tablainicio.addCell(cellT);

			cellT = new PdfPCell(new Paragraph("FACTURA DE VENTA" + "\n",
					FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLUE)));
			cellT.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.DARK_GRAY);
			tablainicio.addCell(cellT);
			document.add(tablainicio);

			PdfPTable tablafactura = new PdfPTable(2);

			tablafactura.setTotalWidth(new float[] { 300, 200 });
			tablafactura.setLockedWidth(true);

			cellT = new PdfPCell(img);
			cellT.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);
			tablafactura.addCell(cellT);

			cellT = new PdfPCell(new Paragraph("" + itemSeleccionado.getFaturacodigo(),
					FontFactory.getFont("arial", 13, Font.BOLD, BaseColor.BLACK)));
			cellT.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.DARK_GRAY);
			tablafactura.addCell(cellT);
			document.add(tablafactura);

			PdfPTable tablainfoempresa = new PdfPTable(2);

			tablainfoempresa.setTotalWidth(new float[] { 300, 200 });
			tablainfoempresa.setLockedWidth(true);

			cellT = new PdfPCell(new Paragraph("NIT: " + itemSeleccionado.getCompania().getCompanianit(),
					FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);
			tablainfoempresa.addCell(cellT);

			cellT = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
			cellT.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);

			cellT.setColspan(2);
			tablainfoempresa.addCell(cellT);
			cellT = new PdfPCell(new Paragraph("" + itemSeleccionado.getSede().getSededireccion(),
					FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);
			tablainfoempresa.addCell(cellT);

			cellT = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
			cellT.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);

			cellT.setColspan(2);
			tablainfoempresa.addCell(cellT);
			cellT = new PdfPCell(new Paragraph("" + itemSeleccionado.getSede().getSedetelefono1(),
					FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);
			tablainfoempresa.addCell(cellT);

			cellT = new PdfPCell(new Paragraph(" ", FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
			cellT.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);
			cellT.setColspan(2);
			tablainfoempresa.addCell(cellT);
			cellT = new PdfPCell(new Paragraph("Web" + itemSeleccionado.getCompania().getCompaniawebsite(),
					FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);
			tablainfoempresa.addCell(cellT);

			cellT = new PdfPCell(
					new Paragraph("FECHA FACTURA ", FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLUE)));
			cellT.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.DARK_GRAY);
			cellT.setColspan(2);
			tablainfoempresa.addCell(cellT);
			cellT = new PdfPCell(new Paragraph(
					"" + itemSeleccionado.getCompania().getCompaniaciudad() + "  "
							+ itemSeleccionado.getCompania().getCompaniapais(),
					FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);
			tablainfoempresa.addCell(cellT);

			cellT = new PdfPCell(new Paragraph("" + "A�O" + "      " + "MES" + "      " + "D�A",
					FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.WHITE)));
			cellT.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellT.setBackgroundColor(BaseColor.BLUE);
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.DARK_GRAY);
			cellT.setColspan(2);
			tablainfoempresa.addCell(cellT);
			cellT = new PdfPCell(new Paragraph("", FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
			cellT.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);
			tablainfoempresa.addCell(cellT);

			cellT = new PdfPCell(new Paragraph("" + anno + "      " + mes + "       " + dia,
					FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
			cellT.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.DARK_GRAY);
			cellT.setColspan(2);
			tablainfoempresa.addCell(cellT);
			cellT = new PdfPCell(
					new Paragraph("\n" + "\n", FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);
			cellT.setBorderColorBottom(BaseColor.BLUE);
			tablainfoempresa.addCell(cellT);
			tablainfoempresa.addCell(cellT);
			document.add(tablainfoempresa);
			document.add(new Paragraph("\n"));
			// Informacion Cliente

			PdfPCell cellvacio = new PdfPCell(new Paragraph(""));
			cellvacio.setUseVariableBorders(true);
			cellvacio.setBorderColor(BaseColor.WHITE);
			PdfPCell cellvacio2 = new PdfPCell(new Paragraph(""));
			cellvacio2.setUseVariableBorders(true);
			cellvacio2.setBorderColor(BaseColor.WHITE);
			cellvacio2.setBorderColorLeft(BaseColor.BLACK);

			PdfPTable tablacliente = new PdfPTable(5);

			tablacliente.setTotalWidth(new float[] { 3, 97, 297, 6, 97 });
			tablacliente.setLockedWidth(true);

			tablacliente.addCell(cellvacio);
			cellT = new PdfPCell(new Paragraph("CLIENTE", FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLUE)));
			cellT.setVerticalAlignment(Element.ALIGN_TOP);
			cellT.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);
			tablacliente.addCell(cellT);
			cellT = new PdfPCell(new Paragraph(""));
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);
			cellT.setBorderColorBottom(BaseColor.BLACK);
			tablacliente.addCell(cellT);
			tablacliente.addCell(cellvacio);
			cellT = new PdfPCell(new Paragraph(""));
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);

			cellT.setColspan(5);
			tablacliente.addCell(cellT);

			tablacliente.addCell(cellInicio);
			cellT = new PdfPCell(
					new Paragraph("\n" + "Nombre:", FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);
			tablacliente.addCell(cellT);

			cellT = new PdfPCell(new Paragraph("\n" + itemSeleccionado.getCliente().getClientenombre(),
					FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);
			cellT.setBorderColorBottom(BaseColor.BLACK);
			cellT.setBorderColorTop(BaseColor.BLACK);
			cellfin.setBorderColorRight(BaseColor.BLACK);
			tablacliente.addCell(cellT);
			PdfPCell cellvacio3 = new PdfPCell(new Paragraph(" "));
			cellvacio3.setUseVariableBorders(true);
			cellvacio3.setBorderColor(BaseColor.WHITE);
			cellvacio3.setBorderColorRight(BaseColor.BLACK);
			cellT = new PdfPCell(new Paragraph("\n"));
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);
			cellT.setBorderColorTop(BaseColor.BLACK);
			cellT.setBorderColorRight(BaseColor.BLACK);
			tablacliente.addCell(cellT);

			cellT.setColspan(5);
			tablacliente.addCell(cellvacio2);
			tablacliente.addCell(cellInicio);
			cellT = new PdfPCell(new Paragraph(
					"" + itemSeleccionado.getCliente().getTipoIdentificacion().getTipoidentificacionnombre() + ":",
					FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);
			tablacliente.addCell(cellT);

			cellT = new PdfPCell(new Paragraph("" + itemSeleccionado.getCliente().getClienteidentificacion(),
					FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);
			cellT.setBorderColorBottom(BaseColor.BLACK);

			tablacliente.addCell(cellT);
			tablacliente.addCell(cellvacio3);

			cellT.setColspan(5);
			tablacliente.addCell(cellvacio2);

			tablacliente.addCell(cellInicio);
			cellT = new PdfPCell(
					new Paragraph("Direcci�n: ", FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);
			tablacliente.addCell(cellT);

			cellT = new PdfPCell(new Paragraph("" + itemSeleccionado.getCliente().getClientedireccion(),
					FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);
			cellT.setBorderColorBottom(BaseColor.BLACK);
			cellfin.setBorderColorRight(BaseColor.BLACK);

			tablacliente.addCell(cellT);
			tablacliente.addCell(cellvacio3);

			cellT.setColspan(5);
			tablacliente.addCell(cellvacio2);
			tablacliente.addCell(cellInicio);
			cellT = new PdfPCell(new Paragraph("Pa�s: ", FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);
			tablacliente.addCell(cellT);

			cellT = new PdfPCell(new Paragraph("" + itemSeleccionado.getCliente().getClientepais(),
					FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);
			cellT.setBorderColorBottom(BaseColor.BLACK);
			cellfin.setBorderColorRight(BaseColor.BLACK);

			tablacliente.addCell(cellT);
			tablacliente.addCell(cellvacio3);

			cellT.setColspan(5);
			tablacliente.addCell(cellvacio2);
			tablacliente.addCell(cellInicio);
			cellT = new PdfPCell(
					new Paragraph("Ciudad: ", FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);
			tablacliente.addCell(cellT);

			cellT = new PdfPCell(new Paragraph("" + itemSeleccionado.getCliente().getClienteciudad(),
					FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);
			cellT.setBorderColorBottom(BaseColor.BLACK);
			cellfin.setBorderColorRight(BaseColor.BLACK);

			tablacliente.addCell(cellT);
			tablacliente.addCell(cellvacio3);

			cellT.setColspan(5);
			tablacliente.addCell(cellvacio2);
			tablacliente.addCell(cellInicio);
			cellT = new PdfPCell(
					new Paragraph("Tel�fono: ", FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);
			tablacliente.addCell(cellT);

			cellT = new PdfPCell(new Paragraph("" + itemSeleccionado.getCliente().getClientetelefono(),
					FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);
			cellT.setBorderColorBottom(BaseColor.BLACK);
			cellfin.setBorderColorRight(BaseColor.BLACK);

			tablacliente.addCell(cellT);
			tablacliente.addCell(cellvacio3);

			cellT.setColspan(5);
			tablacliente.addCell(cellvacio2);
			tablacliente.addCell(cellInicio);
			cellT = new PdfPCell(
					new Paragraph("Director: " + "\n", FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);
			cellT.setBorderColorBottom(BaseColor.BLACK);

			tablacliente.addCell(cellT);

			cellT = new PdfPCell(new Paragraph("" + itemSeleccionado.getCliente().getDirector() + "\n",
					FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);
			cellT.setBorderColorBottom(BaseColor.BLACK);

			tablacliente.addCell(cellT);
			cellT = new PdfPCell(new Paragraph("\n", FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);
			cellT.setBorderColorBottom(BaseColor.BLACK);
			cellfin.setBorderColorRight(BaseColor.BLACK);
			tablacliente.addCell(cellvacio3);

			tablacliente.addCell(cellvacio2);

			document.add(tablacliente);
			document.add(new Paragraph("\n"));

			// tabla conceptos

			Double valor = itemSeleccionado.getSubtotal();
			Double valoriva = itemSeleccionado.getValorIva();
			Double valorTotal = itemSeleccionado.getValorTotal();
			Long valor1 = valor.longValue();
			Long iva = valoriva.longValue();
			Long valorTotal1 = valorTotal.longValue();
			String valor2 = formateador.format(valor1);
			String valoriva1 = formateador.format(iva);
			String valorTotalfinal = formateador.format(valorTotal1);
			String valorTotalL = formateador2.format(valorTotal1);
			Convertir convertir = new Convertir();
			String valorTotalLetras = convertir.Convertir(valorTotalL, "PESOS");

			PdfPTable tablaconcepto = new PdfPTable(4);

			tablaconcepto.setTotalWidth(new float[] { 70, 230, 100, 100 });
			tablaconcepto.setLockedWidth(true);

			cellT = new PdfPCell(
					new Paragraph("CANTIDAD ", FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.WHITE)));
			cellT.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellT.setBackgroundColor(BaseColor.DARK_GRAY);
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.DARK_GRAY);
			tablaconcepto.addCell(cellT);// 2

			cellT = new PdfPCell(
					new Paragraph("DESCRIPCI�N ", FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.WHITE)));
			cellT.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellT.setBackgroundColor(BaseColor.DARK_GRAY);
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.DARK_GRAY);
			tablaconcepto.addCell(cellT);// 3
			cellT = new PdfPCell(
					new Paragraph("VALOR UNITARIO ", FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.WHITE)));
			cellT.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellT.setBackgroundColor(BaseColor.DARK_GRAY);
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.DARK_GRAY);
			tablaconcepto.addCell(cellT);// 4

			cellT = new PdfPCell(
					new Paragraph("VALOR TOTAL ", FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.WHITE)));
			cellT.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellT.setBackgroundColor(BaseColor.DARK_GRAY);
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.DARK_GRAY);
			tablaconcepto.addCell(cellT);// 5
			document.add(tablaconcepto);

			PdfPTable tablaconcepto2 = new PdfPTable(4);

			tablaconcepto2.setTotalWidth(new float[] { 70, 230, 100, 100 });
			tablaconcepto2.setLockedWidth(true);
			for (FacturaDetalle detalle : listaFacturaDetalleItems) {

				cellT = new PdfPCell(new Paragraph("" + detalle.getCantidad(),
						FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
				tablaconcepto2.addCell(cellT);// 2
				cellT = new PdfPCell(new Paragraph("" + detalle.getDescripcion(),
						FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
				tablaconcepto2.addCell(cellT);// 3
				Double valorunitario = detalle.getValorUnitario();
				Double valorTotalconcepto = detalle.getValorTotal();
				Long valorU = valorunitario.longValue();
				Long valorTC = valorTotalconcepto.longValue();
				String valorUni = formateador.format(valorU);
				String valorTotalC = formateador.format(valorTC);
				cellT = new PdfPCell(
						new Paragraph("$ " + valorUni, FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
				tablaconcepto2.addCell(cellT);// 4

				cellT = new PdfPCell(new Paragraph("$ " + valorTotalC,
						FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
				tablaconcepto2.addCell(cellT);// 5

			}

			document.add(tablaconcepto2);
			document.add(new Paragraph("\n"));

			PdfPTable tablafinal = new PdfPTable(3);

			tablafinal.setTotalWidth(new float[] { 300, 80, 120 });
			tablafinal.setLockedWidth(true);
			tablafinal.addCell(cellvacio);
			cellT = new PdfPCell(
					new Paragraph("SubTotal ", FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
			cellT.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellT.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.DARK_GRAY);
			tablafinal.addCell(cellT);// 1
			cellT = new PdfPCell(
					new Paragraph("$   " + valor2, FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
			cellT.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellT.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.DARK_GRAY);
			cellT.setColspan(3);
			tablafinal.addCell(cellT);// 2

			tablafinal.addCell(cellvacio);// 1
			cellT = new PdfPCell(new Paragraph("IVA ", FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
			cellT.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellT.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.DARK_GRAY);
			tablafinal.addCell(cellT);// 2
			cellT = new PdfPCell(
					new Paragraph("$   " + valoriva1, FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
			cellT.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellT.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.DARK_GRAY);
			cellT.setColspan(3);
			tablafinal.addCell(cellT);// 3

			tablafinal.addCell(cellvacio);// 1
			cellT = new PdfPCell(new Paragraph("TOTAL ", FontFactory.getFont("arial", 12, Font.BOLD, BaseColor.BLACK)));
			cellT.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellT.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.DARK_GRAY);
			tablafinal.addCell(cellT);// 2
			cellT = new PdfPCell(new Paragraph("$   " + valorTotalfinal,
					FontFactory.getFont("arial", 12, Font.BOLD, BaseColor.BLACK)));
			cellT.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellT.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cellT.setUseVariableBorders(true);

			cellT.setBorderColor(BaseColor.DARK_GRAY);
			tablafinal.addCell(cellT);// 3
			document.add(tablafinal);

			document.add(new Paragraph("\n"));

			PdfPTable tablavalorLetras = new PdfPTable(2);

			tablavalorLetras.setTotalWidth(new float[] { 300, 200 });
			tablavalorLetras.setLockedWidth(true);

			tablavalorLetras.addCell(cellvacio);

			cellT = new PdfPCell(
					new Paragraph("" + valorTotalLetras, FontFactory.getFont("arial", 8, Font.BOLD, BaseColor.BLACK)));
			cellT.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellT.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.DARK_GRAY);
			tablavalorLetras.addCell(cellT);
			document.add(tablavalorLetras);

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

	public Long getSedeid() {
		return sedeid;
	}

	public void setSedeid(Long sedeid) {
		this.sedeid = sedeid;
	}

	public List<SelectItem> getSedeItems() {
		return sedeItems;
	}

	public void setSedeItems(List<SelectItem> sedeItems) {
		this.sedeItems = sedeItems;
	}

	public void cargarSede() {

		if (companiaid != 0) {

			sedeItems = new ArrayList<SelectItem>();
			sedeItems.clear();

			List<Sede> sedeList = facturaServicio.consultarSedes(companiaid);

			for (Sede sede : sedeList) {
				sedeItems.add(new SelectItem(sede.getSedeid(), sede.getSedenombre()));

			}

		} else {
			sedeItems.clear();
		}
	}

	public Long getContactoid() {
		return contactoid;
	}

	public void setContactoid(Long contactoid) {
		this.contactoid = contactoid;
	}

	public List<SelectItem> getContactoItems() {
		return contactoItems;
	}

	public void setContactoItems(List<SelectItem> contactoItems) {
		this.contactoItems = contactoItems;
	}

	public void cargarContactos() {

		if (clienteid != 0) {

			contactoItems = new ArrayList<SelectItem>();
			contactoItems.clear();

			List<ClienteContacto> contactoList = facturaServicio.consultarContactosPorCliente(clienteid);

			for (ClienteContacto contacto : contactoList) {
				contactoItems.add(new SelectItem(contacto.getContactoid(), contacto.getNombreApellido()));

			}

		} else {
			contactoItems.clear();
		}
	}

	public Long getCentroCostosid() {
		return centroCostosid;
	}

	public void setCentroCostosid(Long centroCostosid) {
		this.centroCostosid = centroCostosid;
	}

	public List<SelectItem> getCentroCostosItems() {
		return centroCostosItems;
	}

	public void setCentroCostosItems(List<SelectItem> centroCostosItems) {
		this.centroCostosItems = centroCostosItems;
	}

	public void cargarCentroCostos() {

		if (companiaid != 0) {

			centroCostosItems = new ArrayList<SelectItem>();
			centroCostosItems.clear();

			List<CentroCostos> centroCostosList = facturaServicio.consultarCentrosCostosPorCompania(companiaid);

			for (CentroCostos centrocostos : centroCostosList) {
				centroCostosItems.add(new SelectItem(centrocostos.getCentroCostosid(), centrocostos.getNombre()));

			}

		} else {
			centroCostosItems.clear();
		}
	}

}