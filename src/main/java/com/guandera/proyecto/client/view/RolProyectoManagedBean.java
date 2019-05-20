package com.guandera.proyecto.client.view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.guandera.core.client.view.BaseManagedBean;
import com.guandera.proyecto.client.service.RolProyectoService;
import com.guandera.proyecto.server.service.RolProyectoServiceImpl;
import com.guandera.proyecto.shared.model.RolProyecto;

@ManagedBean(name = "rolProyectoMB")
@SessionScoped
public class RolProyectoManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private RolProyectoService rolProyectoServicio;
	private RolProyecto itemSeleccionado;
	private List<RolProyecto> listaItems;
	private boolean listing = false;

	public RolProyectoManagedBean() {
		rolProyectoServicio = new RolProyectoServiceImpl();

		inicializar();
	}

	public String actualizar() {

		try {
			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			rolProyectoServicio.actualizar(itemSeleccionado);
			mensajeInfo("RolProyectoUpdated");
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

			rolProyectoServicio.crear(itemSeleccionado);
			mensajeInfo("RolProyectoCreated");
			inicializar();
			verificarLista();
			return prepararLista();

		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String eliminar() {

		rolProyectoServicio.eliminar(itemSeleccionado);
		listing = false;
		return prepararLista();
	}

	public RolProyecto getItemSeleccionado() {
		return itemSeleccionado;
	}

	public List<RolProyecto> getListaItems() {
		if (!listing) {
			listing = true;
			listaItems = rolProyectoServicio.consultarTodos();
		}
		return listaItems;
	}

	public RolProyectoService getRolProyectoServicio() {
		return rolProyectoServicio;
	}

	private void inicializar() {

		itemSeleccionado = new RolProyecto();

	}

	public boolean isListing() {
		return listing;
	}

	public void listarRolProyecto() {
		setListing(true);
		listaItems = rolProyectoServicio.consultarTodos();
	}

	public String prepararConsulta() {
		return "Detalle";
	}

	public String prepararCreacion() {
		itemSeleccionado = new RolProyecto();
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

	public void setItemSeleccionado(RolProyecto itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public void setListaItems(List<RolProyecto> listaItems) {
		this.listaItems = listaItems;
	}

	public void setListing(boolean listing) {
		this.listing = listing;
	}

	public void setRolProyectoServicio(RolProyectoService rolProyectoServicio) {
		this.rolProyectoServicio = rolProyectoServicio;
	}

	private void verificarLista() {
		if (listing) {
			this.listaItems = rolProyectoServicio.consultarTodos();
		}
	}
}
