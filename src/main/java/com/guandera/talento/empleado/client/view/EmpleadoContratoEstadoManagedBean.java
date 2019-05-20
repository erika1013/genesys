package com.guandera.talento.empleado.client.view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.guandera.core.client.view.BaseManagedBean;
import com.guandera.talento.empleado.client.service.EmpleadoContratoEstadoService;
import com.guandera.talento.empleado.server.service.EmpleadoContratoEstadoServiceImpl;
import com.guandera.talento.empleado.shared.model.EmpleadoContratoEstado;

@ManagedBean(name = "empleadoContratoEstadoMB")
@SessionScoped
public class EmpleadoContratoEstadoManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private EmpleadoContratoEstadoService empleadoContratoEstadoServicio;
	private EmpleadoContratoEstado itemSeleccionado;
	private List<EmpleadoContratoEstado> listaItems;

	private boolean listing = false;

	public EmpleadoContratoEstadoManagedBean() {
		empleadoContratoEstadoServicio = new EmpleadoContratoEstadoServiceImpl();

		inicializar();
	}

	public String actualizar() {

		try {

			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			empleadoContratoEstadoServicio.actualizar(itemSeleccionado);
			mensajeInfo("EmpleadoContratoEstadoUpdated");
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

			empleadoContratoEstadoServicio.crear(itemSeleccionado);
			mensajeInfo("EmpleadoContratoEstadoCreated");
			inicializar();
			verificarLista();
			return prepararLista();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String eliminar() {

		empleadoContratoEstadoServicio.eliminar(itemSeleccionado);
		listing = false;
		return prepararLista();
	}

	public EmpleadoContratoEstado getItemSeleccionado() {
		return itemSeleccionado;
	}

	public List<EmpleadoContratoEstado> getListaItems() {
		if (!listing) {
			listing = true;
			listaItems = empleadoContratoEstadoServicio.consultarTodos();
		}

		return listaItems;
	}

	public EmpleadoContratoEstadoService getEmpleadoContratoEstadoServicio() {
		return empleadoContratoEstadoServicio;
	}

	private void inicializar() {

		itemSeleccionado = new EmpleadoContratoEstado();

	}

	public boolean isListing() {
		return listing;
	}

	public void listarEmpleadoContratoEstado() {
		setListing(true);
		listaItems = empleadoContratoEstadoServicio.consultarTodos();
	}

	public String prepararConsulta() {
		return "Detalle";
	}

	public String prepararCreacion() {
		itemSeleccionado = new EmpleadoContratoEstado();
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

	public void setItemSeleccionado(EmpleadoContratoEstado itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public void setListaItems(List<EmpleadoContratoEstado> listaItems) {
		this.listaItems = listaItems;
	}

	public void setListing(boolean listing) {
		this.listing = listing;
	}

	public void setEmpleadoContratoEstadoServicio(EmpleadoContratoEstadoService empleadoContratoEstadoServicio) {
		this.empleadoContratoEstadoServicio = empleadoContratoEstadoServicio;
	}

	private void verificarLista() {
		if (listing) {
			this.listaItems = empleadoContratoEstadoServicio.consultarTodos();
		}
	}
}
