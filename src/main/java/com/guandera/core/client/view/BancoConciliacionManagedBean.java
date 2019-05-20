package com.guandera.core.client.view;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.guandera.core.client.service.BancoConciliacionService;
import com.guandera.core.server.service.BancoConciliacionServiceImpl;
import com.guandera.core.shared.model.BancoConciliacion;
import com.guandera.core.shared.model.BancoConciliacionDetalle;
import com.guandera.core.shared.model.BancoPagoConciliacion;

@ManagedBean(name = "bancoConciliacionMB")
@SessionScoped
public class BancoConciliacionManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private BancoConciliacionService bancoConciliacionServicio;
	private BancoConciliacion itemSeleccionado;
	private List<BancoConciliacion> listaItems;

	private List<BancoPagoConciliacion> listaItems1;
	private boolean listing = false;

	private List<BancoConciliacionDetalle> listaBancoConciliacionDetalle;
	private BancoConciliacionDetalle itemBancoConciliacionDetalle;
	private boolean listingBancoConciliacionDetalle = false;

	public BancoConciliacionManagedBean() {
		bancoConciliacionServicio = new BancoConciliacionServiceImpl();

		inicializar();
	}

	public String actualizar() {

		try {

			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			bancoConciliacionServicio.actualizar(itemSeleccionado);
			mensajeInfo("BancoConciliacionUpdated");
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

			// itemSeleccionado.setBancoConciliacionNumero(bancoConciliacionServicio.siguienteRegistro1());
			itemSeleccionado.setUsuarioCreacion(usuario());
			itemSeleccionado.setFechaCreacion(new Date());
			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());
			bancoConciliacionServicio.crear(itemSeleccionado);

			mensajeInfo("BancoConciliacionCreated");
			inicializar();
			verificarLista();
			return prepararLista();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String eliminar() {

		bancoConciliacionServicio.eliminar(itemSeleccionado);
		listing = false;
		return prepararLista();
	}

	public BancoConciliacion getItemSeleccionado() {
		return itemSeleccionado;
	}

	public List<BancoConciliacion> getListaItems() {
		if (!listing) {
			listing = true;
			// listaItems =
			// bancoConciliacionServicio.consultarPorUsuarioAdmCreador(usuario());
			listaItems = bancoConciliacionServicio.consultarTodos();
			System.out.println(listaItems);
		}
		return listaItems;
	}

	public BancoConciliacionService getBancoConciliacionServicio() {
		return bancoConciliacionServicio;
	}

	private void inicializar() {

		itemSeleccionado = new BancoConciliacion();

	}

	public boolean isListing() {
		return listing;
	}

	public void listarBancoConciliacion() {
		setListing(true);
		// listaItems =
		// bancoConciliacionServicio.consultarPorUsuarioAdmCreador(usuario());
		listaItems = bancoConciliacionServicio.consultarTodos();

		System.out.println(listaItems);
	}

	public String prepararConsulta() {
		listingBancoConciliacionDetalle = false;
		return "Detalle";
	}

	public String prepararCreacion() {
		itemSeleccionado = new BancoConciliacion();
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

	public void setItemSeleccionado(BancoConciliacion itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public void setListaItems(List<BancoConciliacion> listaItems) {
		this.listaItems = listaItems;
	}

	public void setListing(boolean listing) {
		this.listing = listing;
	}

	public void setBancoConciliacionServicio(BancoConciliacionService bancoConciliacionServicio) {
		this.bancoConciliacionServicio = bancoConciliacionServicio;
	}

	private void verificarLista() {
		if (listing) {
			// this.listaItems =
			// bancoConciliacionServicio.consultarPorUsuarioAdmCreador(usuario());

			this.listaItems = bancoConciliacionServicio.consultarTodos();

		}
	}

	// informacion de BancoConciliacionDetalle

	public List<BancoConciliacionDetalle> getListaBancoConciliacionDetalle() {

		if (!listingBancoConciliacionDetalle) {
			listingBancoConciliacionDetalle = true;
			listaBancoConciliacionDetalle = bancoConciliacionServicio
					.consultarBancoConciliacionDetallesPorBancoConciliacion(itemSeleccionado.getConciliacionid());

		}
		return listaBancoConciliacionDetalle;
	}

	public void setListaBancoConciliacionDetalle(List<BancoConciliacionDetalle> listaBancoConciliacionDetalle) {
		this.listaBancoConciliacionDetalle = listaBancoConciliacionDetalle;
	}

	public List<BancoPagoConciliacion> getListaItems1() {

		this.listaItems1 = bancoConciliacionServicio.consultarConciliacionV1();

		return listaItems1;
	}

	public void setListaItems1(List<BancoPagoConciliacion> listaItems1) {
		this.listaItems1 = listaItems1;
	}

	public BancoConciliacionDetalle getItemBancoConciliacionDetalle() {
		return itemBancoConciliacionDetalle;
	}

	public void setItemBancoConciliacionDetalle(BancoConciliacionDetalle itemBancoConciliacionDetalle) {
		this.itemBancoConciliacionDetalle = itemBancoConciliacionDetalle;
	}

	public String prepararConsultaBancoConciliacionDetalle() {
		return "DetalleDetalle";
	}

	public String prepararCreacionBancoConciliacionDetalle() {
		setItemBancoConciliacionDetalle(new BancoConciliacionDetalle());
		return "DetalleCrear";
	}

	public String prepararEdicionBancoConciliacionDetalle() {
		return "DetalleEditar";
	}

	public String crearBancoConciliacionDetalle() {

		try {

			getItemBancoConciliacionDetalle().setBancoConciliacion(itemSeleccionado);

			getItemBancoConciliacionDetalle().setUsuarioCreacion(usuario());
			getItemBancoConciliacionDetalle().setFechaCreacion(new Date());
			getItemBancoConciliacionDetalle().setUsuarioModificacion(usuario());
			getItemBancoConciliacionDetalle().setFechaModificacion(new Date());

			bancoConciliacionServicio.crearBancoConciliacionDetalle(getItemBancoConciliacionDetalle());

			mensajeInfo("BancoConciliacionDetalleCreated");
			listingBancoConciliacionDetalle = false;
			return prepararConsulta();

		} catch (Exception e) {

			mensajeError("PersistenceErrorOccured");
			return null;

		}

	}

	public String actualizarBancoConciliacionDetalle() {

		try {
			getItemBancoConciliacionDetalle().setUsuarioModificacion(usuario());
			getItemBancoConciliacionDetalle().setFechaModificacion(new Date());

			bancoConciliacionServicio.actualizarBancoConciliacionDetalle(getItemBancoConciliacionDetalle());

			mensajeInfo("BancoConciliacionDetalleUpdated");
			listingBancoConciliacionDetalle = false;
			return prepararConsulta();

		} catch (Exception e) {
			System.out.print("Donde" + e.getLocalizedMessage());
			System.out.print("Que0" + e.getMessage());
			mensajeError("PersistenceErrorOccured");
			return null;

		}

	}

	public String eliminarBancoConciliacionDetalle() {

		try {

			bancoConciliacionServicio.eliminarBancoConciliacionDetalle(getItemBancoConciliacionDetalle());

			mensajeInfo("BancoConciliacionDetalleDeleted");
			return prepararConsulta();

		} catch (Exception e) {

			mensajeError("PersistenceErrorOccured");
			return null;

		}

	}

	public String refrescarLista() {

		listing = false;
		return "Lista";
	}

	public void exportConciliacion() {

		listaBancoConciliacionDetalle = bancoConciliacionServicio.consultarDetalleConciliacion();

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {

			// String encabezado = "Periodo;CodigoEstudiante;Numero Recibo
			// ;Nombre;Valor Cobro; Valor Pago; Fecha Pago; Tipo Pago\n";
			String encabezado = "Origen;Fecha Pago ;Estudiante;  Valor Pago; Observacion 1; Nro recibo; Fecha Aplicacion; Valor Apliacion;Observacion;Usuario Creaci�n; Usuario Modificaci�n\n";
			String coma = ";";
			String salto = "\n";

			byte[] exportContent = encabezado.getBytes();

			baos.write(exportContent);

			for (BancoConciliacionDetalle detalle : listaBancoConciliacionDetalle) {

				String texto;

				texto = detalle.getBancoConciliacion().getNombreOrigen().trim() + coma
						+ detalle.getBancoConciliacion().getFechaPago() + coma
						+ detalle.getBancoConciliacion().getEstudianteCodigo() + coma
						+ detalle.getBancoConciliacion().getValorTotal() + coma
						+ detalle.getBancoConciliacion().getObservacion().trim() + coma

						+ detalle.getNumeroRecibo() + coma + detalle.getFechaAplicacion() + coma
						+ detalle.getValorAplicado() + coma + detalle.getObservacion().trim() + coma
						+ detalle.getUsuarioCreacion().getUsuariousuario().trim() + coma
						+ detalle.getUsuarioModificacion().getUsuariousuario().trim() + salto;

				byte[] exportTexto = texto.getBytes();

				baos.write(exportTexto);

			}

		} catch (IOException e) {

			e.printStackTrace();
		}

		FacesContext context = FacesContext.getCurrentInstance();
		Object response = context.getExternalContext().getResponse();

		if (response instanceof HttpServletResponse) {
			HttpServletResponse hsr = (HttpServletResponse) response;

			hsr.setContentType("text/plain;charset=UTF-8");
			hsr.setContentLength(baos.size());
			// hsr.setHeader("Content-disposition", "attachment");
			// hsr.setHeader("Content-disposition",
			// "inline=filename=resumen.txt");
			hsr.setHeader("Content-Disposition", "attachment;filename=Conciliacion.csv");

			try {
				ServletOutputStream out = hsr.getOutputStream();
				baos.writeTo(out);
				out.flush();
			} catch (IOException ex) {
				System.out.println("Error " + ex.getMessage());
			}

			context.responseComplete();

		}

	}

}
