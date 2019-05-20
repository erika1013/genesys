package com.guandera.proyecto.client.view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.guandera.core.client.view.BaseManagedBean;
import com.guandera.proyecto.client.service.ProyectoEtapaService;
import com.guandera.proyecto.server.service.ProyectoEtapaServiceImpl;
import com.guandera.proyecto.shared.model.ProyectoEtapa;

@ManagedBean(name = "proyectoEtapaMB")
@SessionScoped
public class ProyectoEtapaManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private ProyectoEtapaService proyectoEtapaServicio;
	private ProyectoEtapa itemSeleccionado;
	private List<ProyectoEtapa> listaItems;
	private boolean listing = false;

	public ProyectoEtapaManagedBean() {
		proyectoEtapaServicio = new ProyectoEtapaServiceImpl();

		inicializar();
	}

	public String actualizar() {

		try {
			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			proyectoEtapaServicio.actualizar(itemSeleccionado);
			mensajeInfo("ProyectoEtapaUpdated");
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

			proyectoEtapaServicio.crear(itemSeleccionado);
			mensajeInfo("ProyectoEtapaCreated");
			inicializar();
			verificarLista();
			return prepararLista();

		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String eliminar() {

		proyectoEtapaServicio.eliminar(itemSeleccionado);
		listing = false;
		return prepararLista();
	}

	public ProyectoEtapa getItemSeleccionado() {
		return itemSeleccionado;
	}

	public List<ProyectoEtapa> getListaItems() {
		if (!listing) {
			listing = true;
			listaItems = proyectoEtapaServicio.consultarTodos();
		}
		return listaItems;
	}

	public ProyectoEtapaService getProyectoEtapaServicio() {
		return proyectoEtapaServicio;
	}

	private void inicializar() {

		itemSeleccionado = new ProyectoEtapa();

	}

	public boolean isListing() {
		return listing;
	}

	public void listarProyectoEtapa() {
		setListing(true);
		listaItems = proyectoEtapaServicio.consultarTodos();
	}

	public String prepararConsulta() {
		return "Detalle";
	}

	public String prepararCreacion() {
		itemSeleccionado = new ProyectoEtapa();
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

	public void setItemSeleccionado(ProyectoEtapa itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public void setListaItems(List<ProyectoEtapa> listaItems) {
		this.listaItems = listaItems;
	}

	public void setListing(boolean listing) {
		this.listing = listing;
	}

	public void setProyectoEtapaServicio(ProyectoEtapaService proyectoEtapaServicio) {
		this.proyectoEtapaServicio = proyectoEtapaServicio;
	}

	private void verificarLista() {
		if (listing) {
			this.listaItems = proyectoEtapaServicio.consultarTodos();
		}
	}
}
