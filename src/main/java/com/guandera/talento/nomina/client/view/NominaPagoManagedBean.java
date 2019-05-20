package com.guandera.talento.nomina.client.view;

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

import com.guandera.core.client.view.BaseManagedBean;
import com.guandera.core.client.view.Convertir;
import com.guandera.talento.empleado.shared.model.EmpleadoContrato;
import com.guandera.talento.nomina.client.service.NominaPagoService;
import com.guandera.talento.nomina.server.service.NominaPagoServiceImpl;
import com.guandera.talento.nomina.shared.model.NominaConcepto;
import com.guandera.talento.nomina.shared.model.NominaPago;
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

@ManagedBean(name = "nominaPagoMB")
@SessionScoped
public class NominaPagoManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private NominaPagoService nominaPagoServicio;
	private NominaPago itemSeleccionado;
	private List<NominaPago> listaItems = new ArrayList<NominaPago>();
	private boolean listing = false;

	private List<EmpleadoContrato> listaEmpleadoContrato = new ArrayList<EmpleadoContrato>();
	private EmpleadoContrato empleadoSeleccionado;
	private boolean listingEmpleado = false;
	private boolean listingNominaNovedad = false;

	private NominaPagoDetalle itemnominaPagoDetalle;
	private List<NominaPagoDetalle> listanominaPagoDetalleItems = new ArrayList<NominaPagoDetalle>();
	private List<NominaPagoNovedad> listaNominaPagoNovedadItems = new ArrayList<NominaPagoNovedad>();
	private boolean listingNominaPago = false;

	private List<NominaPagoDetalle> listaNominaDetalle;
	private List<NominaPagoNovedad> listaNominaNovedad;
	private NominaPagoDetalle nominaPagoDetalle;

	private Integer numeroIdentificacion;
	private String periodo;

	private NominaPagoNovedad itemNominaPagoNovedad;
	private Long empleadoid;
	private List<SelectItem> empleadoContratoItems;
	private boolean listingEmpleadoItem = false;

	private Long nominaConcepto;

	private List<SelectItem> nominaConceptoItems;
	private boolean listingNomina = false;

	public NominaPagoManagedBean() {
		nominaPagoServicio = new NominaPagoServiceImpl();
		inicializar();
	}

	public String actualizar() {

		try {

			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			nominaPagoServicio.actualizar(itemSeleccionado);
			mensajeInfo("NominaPagoUpdated");
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

			itemSeleccionado.setUsuarioCreacion(usuario());
			itemSeleccionado.setFechaCreacion(new Date());
			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			String periodo = itemSeleccionado.getAno() + itemSeleccionado.getMes();
			itemSeleccionado.setPeriodo(periodo);

			nominaPagoServicio.crearLiquidacion(itemSeleccionado);

			mensajeInfo("NominaPagoCreated");
			inicializar();
			verificarLista();
			return prepararLista();

		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String eliminar() {

		nominaPagoServicio.eliminar(itemSeleccionado);
		listing = false;
		return prepararLista();
	}

	public NominaPago getItemSeleccionado() {
		return itemSeleccionado;
	}

	public List<NominaPago> getListaItems() {
		if (!listing) {
			listing = true;

			listaItems = nominaPagoServicio.consultarTodos();

		}
		return listaItems;
	}

	public NominaPagoService getNominaPagoServicio() {
		return nominaPagoServicio;
	}

	private void inicializar() {

		itemSeleccionado = new NominaPago();

	}

	public boolean isListing() {
		return listing;
	}

	public void listarNominaPago() {
		setListing(true);
		listaItems = nominaPagoServicio.consultarTodos();
	}

	public String prepararConsulta() {
		listingNominaPago = false;
		listingNominaNovedad = false;
		return "Detalle";
	}

	public String prepararCreacionContrato() {
		empleadoSeleccionado = new EmpleadoContrato();

		return "CrearBuscar";

	}

	public String prepararCreacion() {
		itemSeleccionado = new NominaPago();
		empleadoid = (long) 0;

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

	public void setItemSeleccionado(NominaPago itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public void setListaItems(List<NominaPago> listaItems) {
		this.listaItems = listaItems;
	}

	public void setListing(boolean listing) {
		this.listing = listing;
	}

	public void setNominaPagoServicio(NominaPagoService nominaPagoServicio) {
		this.nominaPagoServicio = nominaPagoServicio;
	}

	private void verificarLista() {
		if (listing) {
			this.listaItems = nominaPagoServicio.consultarTodos();
		}
	}

	public Long getEmpleadoid() {
		return empleadoid;
	}

	public void setEmpleadoid(Long empleadoid) {
		this.empleadoid = empleadoid;
	}

	public List<SelectItem> getEmpleadoContratoItems() {
		if (!listingEmpleadoItem) {
			listingEmpleadoItem = true;
			empleadoContratoItems = new ArrayList<SelectItem>();
			List<EmpleadoContrato> empleadoList = nominaPagoServicio.consultarEmpleadoContrato();

			for (EmpleadoContrato empleado : empleadoList) {
				empleadoContratoItems.add(new SelectItem(empleado.getEmpleado().getEmpleadoid(),
						empleado.getEmpleado().getPrimerNombre()));

			}
		}

		return empleadoContratoItems;
	}

	public void setEmpleadoContratoItems(List<SelectItem> empleadoContratoItems) {
		this.empleadoContratoItems = empleadoContratoItems;
	}

	// NOmina

	public Long getNominaConcepto() {
		return nominaConcepto;
	}

	public void setNominaConcepto(Long nominaConcepto) {
		this.nominaConcepto = nominaConcepto;
	}

	public List<SelectItem> getNominaConceptoItems() {
		if (!listingNomina) {
			listingNomina = true;
			nominaConceptoItems = new ArrayList<SelectItem>();
			List<NominaConcepto> nominaConceptoList = nominaPagoServicio.consultarNominaConcepto();

			for (NominaConcepto nominaConcepto : nominaConceptoList) {
				nominaConceptoItems.add(
						new SelectItem(nominaConcepto.getNominaconceptoid(), nominaConcepto.getNominaconceptonombre()));

			}
		}

		return nominaConceptoItems;
	}

	public void setNominaConceptoItems(List<SelectItem> nominaConceptoItems) {
		this.nominaConceptoItems = nominaConceptoItems;
	}

	// detalle contrato

	public NominaPagoDetalle getItemnominaPagoDetalle() {
		return itemnominaPagoDetalle;
	}

	public void setItemnominaPagoDetalle(NominaPagoDetalle itemnominaPagoDetalle) {
		this.itemnominaPagoDetalle = itemnominaPagoDetalle;
	}

	public List<NominaPagoDetalle> getListanominaPagoDetalleItems() {
		if (!listingNominaPago) {
			listingNominaPago = true;
			listanominaPagoDetalleItems = nominaPagoServicio
					.consultarNominaPagoDetalle(itemSeleccionado.getPagonominaid());

		}
		return listanominaPagoDetalleItems;
	}

	public void setListanominaPagoDetalleItems(List<NominaPagoDetalle> listanominaPagoDetalleItems) {
		this.listanominaPagoDetalleItems = listanominaPagoDetalleItems;
	}

	public String prepararConsultanominaPagoDetalle() {
		return "NominaPagoDetalleDetalle";
	}

	public String prepararCreacionnominaPagoDetalle() {
		itemnominaPagoDetalle = new NominaPagoDetalle();
		nominaConcepto = (long) 0;
		return "NominaPagoDetalleCrear";
	}

	public String prepararEdicionnominaPagoDetalle() {
		return "NominaPagoDetalleEditar";
	}

	public String prepararListanominaPagoDetalle() {
		return "NominaPagoDetalleLista";
	}

	public String prepararConsultaNominaPagoNovedad() {
		return "NominaPagoNovedadDetalle";
	}

	public String eliminarnominaPagoDetalle() {

		try {

			nominaPagoServicio.eliminarNominaPagoDetalle(itemnominaPagoDetalle);

			mensajeInfo("nominaPagoDetalleDeleted");
			return prepararConsulta();

		} catch (Exception e) {

			mensajeError("PersistenceErrorOccured");
			return null;

		}

	}

	public List<EmpleadoContrato> getListaEmpleadoContrato() {
		if (!listingEmpleado) {

			listingEmpleado = true;
			listaEmpleadoContrato = nominaPagoServicio.consultarEmpleadoContrato();
		}
		return listaEmpleadoContrato;
	}

	public void setListaEmpleadoContrato(List<EmpleadoContrato> listaEmpleadoContrato) {
		this.listaEmpleadoContrato = listaEmpleadoContrato;
	}

	public EmpleadoContrato getEmpleadoSeleccionado() {
		return empleadoSeleccionado;
	}

	public void setEmpleadoSeleccionado(EmpleadoContrato empleadoSeleccionado) {
		this.empleadoSeleccionado = empleadoSeleccionado;
	}

	public List<NominaPagoNovedad> getListaNominaPagoNovedadItems() {
		if (!listingNominaNovedad) {
			listingNominaNovedad = true;
			listaNominaPagoNovedadItems = nominaPagoServicio
					.consultarNominaPagoNovedad(itemSeleccionado.getPagonominaid());
			System.out.println("Lista nomina novedad********" + listaNominaPagoNovedadItems);
		}
		return listaNominaPagoNovedadItems;
	}

	public void setListaNominaPagoNovedadItems(List<NominaPagoNovedad> listaNominaPagoNovedadItems) {
		this.listaNominaPagoNovedadItems = listaNominaPagoNovedadItems;
	}

	public NominaPagoNovedad getItemNominaPagoNovedad() {
		return itemNominaPagoNovedad;
	}

	public void setItemNominaPagoNovedad(NominaPagoNovedad itemNominaPagoNovedad) {
		this.itemNominaPagoNovedad = itemNominaPagoNovedad;
	}

	public String prepararBusqueda() {
		return "BusquedaDesprendible";

	}

	public String prepararDespendible() {
		return "DesprendibleNomina";
	}

	public List<NominaPagoDetalle> getListaNominaDetalle() {
		return listaNominaDetalle;
	}

	public void setListaNominaDetalle(List<NominaPagoDetalle> listaNominaDetalle) {
		this.listaNominaDetalle = listaNominaDetalle;
	}

	public String buscarNomina() {
		setListaNominaDetalle(new ArrayList<NominaPagoDetalle>());
		setListaNominaNovedad(new ArrayList<NominaPagoNovedad>());
		// FJVV NominaPago nominaPago1 =
		// nominaPagoServicio.consultarNominaPago(numeroIdentificacion,
		// periodo);
		try {
			if (numeroIdentificacion != null && periodo != null) {
				// if (nominaPago1 != null) {
				if (1 == 1) {

					setListaNominaDetalle(nominaPagoServicio.consultarNomina(numeroIdentificacion, periodo));
					setListaNominaNovedad(nominaPagoServicio.consultarNominaNovedad(numeroIdentificacion, periodo));
					// FJVV
					// setNominaPago(nominaPagoServicio.consultarNominaPago(numeroIdentificacion,
					// periodo));

					return "DesprendibleNomina";
				} else {
					mensaje("No hay nomina para el empleado");
				}
			} else {

				return "BusquedaDesprendible";
			}
		} catch (Exception e) {

			mensajeError("Error");
			return null;
		}
		return null;

	}

	public Integer getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	public void setNumeroIdentificacion(Integer numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public NominaPagoDetalle getNominaPagoDetalle() {
		return nominaPagoDetalle;
	}

	public void setNominaPagoDetalle(NominaPagoDetalle nominaPagoDetalle) {
		this.nominaPagoDetalle = nominaPagoDetalle;
	}

	public List<NominaPagoNovedad> getListaNominaNovedad() {
		return listaNominaNovedad;
	}

	public void setListaNominaNovedad(List<NominaPagoNovedad> listaNominaNovedad) {
		this.listaNominaNovedad = listaNominaNovedad;
	}

	public void imprimirDesprendible() {
		imprimirNomina(itemSeleccionado);
	}

	public void imprimirNomina(NominaPago nominaPago) {
		Document document = new Document(PageSize.LETTER);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		try {

			PdfWriter.getInstance(document, baos);
			document.open();

			Image img = Image.getInstance("images/LOGO.png");
			img.setAlignment(Image.LEFT);
			img.scalePercent(30f);

			document.add(img);
			document.add(new Paragraph("\n"));

			DecimalFormat formateador = new DecimalFormat("###,###.##");
			DecimalFormat formateador2 = new DecimalFormat("#.###");

			// Formateando la fecha:

			DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd ");
			Date currenDate = new Date();
			String date = formatter.format(currenDate);

			PdfPCell cellT;

			PdfPCell cellvacio = new PdfPCell(
					new Paragraph("", FontFactory.getFont("arial", 10, Font.NORMAL, BaseColor.BLACK)));

			cellvacio.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellvacio.setUseVariableBorders(true);
			cellvacio.setBorderColor(BaseColor.WHITE);

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
			PdfPTable tablaInicial = new PdfPTable(2);
			// / parte final
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
			tablaInicial.setTotalWidth(new float[] { 250, 250 });
			tablaInicial.setLockedWidth(true);
			cellT = new PdfPCell(new Paragraph(" "

					, FontFactory.getFont("arial", 6, Font.BOLD, BaseColor.DARK_GRAY)));
			cellT.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellT.setBorderColor(BaseColor.WHITE);
			cellT.setUseVariableBorders(true);
			cellT.setBorderColorTop(BaseColor.BLACK);
			cellT.setBorderColorLeft(BaseColor.BLACK);
			tablaInicial.addCell(cellT);
			cellT = new PdfPCell(new Paragraph("", FontFactory.getFont("arial", 6, Font.BOLD, BaseColor.DARK_GRAY)));
			cellT.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellT.setBorderColor(BaseColor.WHITE);
			cellT.setUseVariableBorders(true);
			cellT.setBorderColorTop(BaseColor.BLACK);
			cellT.setBorderColorRight(BaseColor.BLACK);
			tablaInicial.addCell(cellT);
			PdfPTable tablaInfoEmpresa = new PdfPTable(3);

			tablaInfoEmpresa.setTotalWidth(new float[] { 3, 494, 3 });
			tablaInfoEmpresa.setLockedWidth(true);
			tablaInfoEmpresa.addCell(cellvacio);
			cellT = new PdfPCell(new Paragraph(
					"" + nominaPago.getEmpleadoContrato().getEmpleado().getSede().getCompania().getCompanianombre(),
					FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
			cellT.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);
			tablaInfoEmpresa.addCell(cellT);
			cellT.setColspan(3);
			tablaInfoEmpresa.addCell(cellvacio);

			tablaInfoEmpresa.addCell(cellvacio);

			cellT = new PdfPCell(new Paragraph(
					"Nit  " + nominaPago.getEmpleadoContrato().getEmpleado().getSede().getCompania().getCompanianit(),
					FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
			cellT.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);
			tablaInfoEmpresa.addCell(cellT);
			cellT.setColspan(3);
			tablaInfoEmpresa.addCell(cellvacio);
			tablaInfoEmpresa.addCell(cellvacio);
			cellT = new PdfPCell(
					new Paragraph("" + nominaPago.getEmpleadoContrato().getEmpleado().getSede().getSededireccion(),
							FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
			cellT.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);
			tablaInfoEmpresa.addCell(cellT);
			cellT.setColspan(3);
			tablaInfoEmpresa.addCell(cellvacio);
			tablaInfoEmpresa.addCell(cellvacio);
			cellT = new PdfPCell(new Paragraph(
					"Tel   " + nominaPago.getEmpleadoContrato().getEmpleado().getSede().getSedetelefono1(),
					FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
			cellT.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);
			tablaInfoEmpresa.addCell(cellT);
			cellT.setColspan(3);
			tablaInfoEmpresa.addCell(cellvacio);
			tablaInfoEmpresa.addCell(cellvacio);
			cellT = new PdfPCell(new Paragraph(
					"" + nominaPago.getEmpleadoContrato().getEmpleado().getSede().getCompania().getCompaniawebsite(),
					FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
			cellT.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);
			tablaInfoEmpresa.addCell(cellT);

			cellT.setColspan(3);
			tablaInfoEmpresa.addCell(cellvacio);
			tablaInfoEmpresa.addCell(cellvacio);
			cellT = new PdfPCell(new Paragraph(
					"" + nominaPago.getEmpleadoContrato().getEmpleado().getSede().getCompania().getCompaniapais()
							+ " - "
							+ nominaPago.getEmpleadoContrato().getEmpleado().getSede().getCompania()
									.getCompaniaciudad(),
					FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
			cellT.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);
			tablaInfoEmpresa.addCell(cellT);
			tablaInfoEmpresa.addCell(cellvacio);

			document.add(tablaInfoEmpresa);
			document.add(new Paragraph("\n"));

			document.add(tablaInicial);

			PdfPTable tablaInfoEmpleado = new PdfPTable(4);

			tablaInfoEmpleado.setTotalWidth(new float[] { 3, 150, 344, 3 });
			tablaInfoEmpleado.setLockedWidth(true);
			tablaInfoEmpleado.addCell(cellInicio);
			cellT = new PdfPCell(
					new Paragraph("Empleado:", FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
			cellT.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellT.setUseVariableBorders(true);

			cellT.setBorderColor(BaseColor.WHITE);

			tablaInfoEmpleado.addCell(cellT);
			cellT = new PdfPCell(new Paragraph(
					" " + nominaPago.getEmpleadoContrato().getEmpleado().getApellidoNombre().toUpperCase(),
					FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
			cellT.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellT.setUseVariableBorders(true);

			cellT.setBorderColor(BaseColor.WHITE);
			tablaInfoEmpleado.addCell(cellT);
			cellT.setColspan(4);
			tablaInfoEmpleado.addCell(cellfin);
			tablaInfoEmpleado.addCell(cellInicio);
			cellT = new PdfPCell(new Paragraph("Nï¿½mero Identificaciï¿½n:",
					FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));

			cellT.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellT.setUseVariableBorders(true);

			cellT.setBorderColor(BaseColor.WHITE);
			tablaInfoEmpleado.addCell(cellT);

			cellT = new PdfPCell(
					new Paragraph(" " + nominaPago.getEmpleadoContrato().getEmpleado().getNumeroIdentificacion(),
							FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
			cellT.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellT.setUseVariableBorders(true);

			cellT.setBorderColor(BaseColor.WHITE);
			tablaInfoEmpleado.addCell(cellT);
			cellT.setColspan(4);
			tablaInfoEmpleado.addCell(cellfin);
			tablaInfoEmpleado.addCell(cellInicio);
			cellT = new PdfPCell(
					new Paragraph("Periodo Pago:", FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));

			cellT.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellT.setUseVariableBorders(true);

			cellT.setBorderColor(BaseColor.WHITE);
			tablaInfoEmpleado.addCell(cellT);

			cellT = new PdfPCell(new Paragraph(" " + nominaPago.getPeriodo(),
					FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
			cellT.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellT.setUseVariableBorders(true);

			cellT.setBorderColor(BaseColor.WHITE);
			tablaInfoEmpleado.addCell(cellT);
			tablaInfoEmpleado.addCell(cellfin);
			document.add(tablaInfoEmpleado);

			document.add(tablaparteFIN);

			document.add(new Paragraph("\n"));

			PdfPTable tablaResumen = new PdfPTable(4);

			tablaResumen.setTotalWidth(new float[] { 80, 200, 110, 110 });
			tablaResumen.setLockedWidth(true);
			cellT = new PdfPCell(new Paragraph("CÓDIGO", FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.WHITE)));
			cellT.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellT.setBackgroundColor(BaseColor.GRAY);
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.BLACK);
			tablaResumen.addCell(cellT);

			cellT = new PdfPCell(
					new Paragraph("DESCRIPCIÓN", FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.WHITE)));
			cellT.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellT.setBackgroundColor(BaseColor.GRAY);
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.BLACK);

			tablaResumen.addCell(cellT);

			cellT = new PdfPCell(
					new Paragraph("DEVENGADO", FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.WHITE)));
			cellT.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellT.setBackgroundColor(BaseColor.GRAY);
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.BLACK);
			tablaResumen.addCell(cellT);

			cellT = new PdfPCell(
					new Paragraph("DEDUCCIï¿½N", FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.WHITE)));
			cellT.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellT.setBackgroundColor(BaseColor.GRAY);
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.BLACK);

			cellT.setColspan(4);
			tablaResumen.addCell(cellT);

			cellT = new PdfPCell(new Paragraph("" + 01, FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
			tablaResumen.addCell(cellT);
			cellT = new PdfPCell(
					new Paragraph("Salario Bï¿½sico", FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
			tablaResumen.addCell(cellT);
			cellT = new PdfPCell(new Paragraph("$  " + nominaPago.getEmpleadoContrato().getContratosalario(),
					FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));

			tablaResumen.addCell(cellT);

			cellT = new PdfPCell(
					new Paragraph("$  " + 0.0, FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));

			cellT.setColspan(4);
			tablaResumen.addCell(cellT);

			for (NominaPagoDetalle nominaPagoDetalle : listanominaPagoDetalleItems) {

				 // Solo se imprimen los conceptos a cargo del Empleado
				if (nominaPagoDetalle.getConceptopor() == 2L) {
					cellT = new PdfPCell(
							new Paragraph("" + nominaPagoDetalle.getNominaConcepto().getNominaconceptocodigo(),
									FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
					tablaResumen.addCell(cellT);

					cellT = new PdfPCell(
							new Paragraph("" + nominaPagoDetalle.getNominaConcepto().getNominaconceptonombre(),
									FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
					tablaResumen.addCell(cellT);

					cellT = new PdfPCell(new Paragraph("$  " + nominaPagoDetalle.getValordevengado(),
							FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));

					tablaResumen.addCell(cellT);
					cellT = new PdfPCell(new Paragraph("$  " + nominaPagoDetalle.getValordeduccion(),
							FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));

					tablaResumen.addCell(cellT);
				}

			}

			document.add(tablaResumen);

			Double deduccion = nominaPago.getTotalDeducciones();
			Double Neto = nominaPago.getValorNetoPago();
			Double devengado = nominaPago.getTotalDevengado();
			Long deduccion1 = deduccion.longValue();
			Long Neto1 = Neto.longValue();
			Long devengado1 = devengado.longValue();

			String dedeccionTotal = formateador.format(deduccion1);
			String devengadoTotal = formateador.format(devengado1);
			String netoTotalNumero = formateador.format(Neto1);
			String netoTotalL = formateador2.format(Neto1);
			Convertir convertir = new Convertir();
			String netoTotalLetras = convertir.Convertir(netoTotalL, "PESOS");
			PdfPTable tablaTotales = new PdfPTable(3);

			tablaTotales.setTotalWidth(new float[] { 280, 110, 110 });
			tablaTotales.setLockedWidth(true);
			cellT = new PdfPCell(new Paragraph("TOTAL", FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
			cellT.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.BLACK);
			tablaTotales.addCell(cellT);

			cellT = new PdfPCell(new Paragraph("$   " + devengadoTotal,
					FontFactory.getFont("arial", 8, Font.BOLD, BaseColor.BLACK)));
			cellT.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.BLACK);
			tablaTotales.addCell(cellT);
			cellT = new PdfPCell(new Paragraph("$   " + dedeccionTotal,
					FontFactory.getFont("arial", 8, Font.BOLD, BaseColor.BLACK)));
			cellT.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.BLACK);
			tablaTotales.addCell(cellT);

			document.add(tablaTotales);

			document.add(new Paragraph("\n"));

			PdfPTable tablaSaldoTotal = new PdfPTable(3);

			tablaSaldoTotal.setTotalWidth(new float[] { 80, 200, 220 });
			tablaSaldoTotal.setLockedWidth(true);

			cellT = new PdfPCell(new Paragraph(" "));
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);
			tablaSaldoTotal.addCell(cellT);

			cellT = new PdfPCell(
					new Paragraph("NETO PAGO", FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
			cellT.setHorizontalAlignment(Element.ALIGN_CENTER);

			cellT.setBorderColor(BaseColor.BLACK);
			tablaSaldoTotal.addCell(cellT);

			cellT = new PdfPCell(new Paragraph("$   " + netoTotalNumero,
					FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
			cellT.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellT.setBackgroundColor(BaseColor.GRAY);

			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.BLACK);
			tablaSaldoTotal.addCell(cellT);

			document.add(tablaSaldoTotal);

			PdfPTable tablaSaldoTotalLetras = new PdfPTable(2);

			tablaSaldoTotalLetras.setTotalWidth(new float[] { 80, 420 });
			tablaSaldoTotalLetras.setLockedWidth(true);

			cellT = new PdfPCell(new Paragraph(" "));
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);
			tablaSaldoTotalLetras.addCell(cellT);

			cellT = new PdfPCell(
					new Paragraph("" + netoTotalLetras, FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
			cellT.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);
			tablaSaldoTotalLetras.addCell(cellT);
			document.add(tablaSaldoTotalLetras);

			document.add(new Paragraph("\n"));
			PdfPTable firma = new PdfPTable(2);

			firma.setTotalWidth(new float[] { 180, 320 });
			firma.setLockedWidth(true);

			cellT = new PdfPCell(new Paragraph("\n" + "\n"));
			cellT.setUseVariableBorders(true);
			cellT.setBorderColor(BaseColor.WHITE);
			firma.addCell(cellT);

			cellT = new PdfPCell(
					new Paragraph("C.C." + nominaPago.getEmpleadoContrato().getEmpleado().getNumeroIdentificacion(),
							FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
			cellT.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellT.setVerticalAlignment(Element.ALIGN_BOTTOM);
			cellT.setBorderColor(BaseColor.BLACK);
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
			hsr.setHeader("Content-disposition", "inline=filename=Desprendible.pdf");
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