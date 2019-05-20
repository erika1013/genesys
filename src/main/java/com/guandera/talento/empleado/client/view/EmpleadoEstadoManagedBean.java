package com.guandera.talento.empleado.client.view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.guandera.core.client.view.BaseManagedBean;
import com.guandera.talento.empleado.client.service.EmpleadoEstadoService;
import com.guandera.talento.empleado.server.service.EmpleadoEstadoServiceImpl;
import com.guandera.talento.empleado.shared.model.EmpleadoEstado;

@ManagedBean(name = "empleadoEstadoMB")
@SessionScoped
public class EmpleadoEstadoManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private EmpleadoEstadoService empleadoEstadoServicio;
	private EmpleadoEstado itemSeleccionado;
	private List<EmpleadoEstado> listaItems;

	private boolean listing = false;

	public EmpleadoEstadoManagedBean() {
		empleadoEstadoServicio = new EmpleadoEstadoServiceImpl();

		inicializar();
	}

	public String actualizar() {

		try {

			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			empleadoEstadoServicio.actualizar(itemSeleccionado);
			mensajeInfo("EmpleadoEstadoUpdated");
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

			empleadoEstadoServicio.crear(itemSeleccionado);
			mensajeInfo("EmpleadoEstadoCreated");
			inicializar();
			verificarLista();
			return prepararLista();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String eliminar() {

		empleadoEstadoServicio.eliminar(itemSeleccionado);
		listing = false;
		return prepararLista();
	}

	public EmpleadoEstado getItemSeleccionado() {
		return itemSeleccionado;
	}

	public List<EmpleadoEstado> getListaItems() {
		if (!listing) {
			listing = true;

			listaItems = empleadoEstadoServicio.consultarTodos();
		}
		return listaItems;
	}

	public EmpleadoEstadoService getEmpleadoEstadoServicio() {
		return empleadoEstadoServicio;
	}

	private void inicializar() {

		itemSeleccionado = new EmpleadoEstado();

	}

	public boolean isListing() {
		return listing;
	}

	public void listarEmpleadoEstado() {
		setListing(true);
		listaItems = empleadoEstadoServicio.consultarTodos();
	}

	public String prepararConsulta() {
		return "Detalle";
	}

	public String prepararCreacion() {
		itemSeleccionado = new EmpleadoEstado();
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

	public void setItemSeleccionado(EmpleadoEstado itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public void setListaItems(List<EmpleadoEstado> listaItems) {
		this.listaItems = listaItems;
	}

	public void setListing(boolean listing) {
		this.listing = listing;
	}

	public void setEmpleadoEstadoServicio(EmpleadoEstadoService empleadoEstadoServicio) {
		this.empleadoEstadoServicio = empleadoEstadoServicio;
	}

	private void verificarLista() {
		if (listing) {
			this.listaItems = empleadoEstadoServicio.consultarTodos();
		}
	}
}
