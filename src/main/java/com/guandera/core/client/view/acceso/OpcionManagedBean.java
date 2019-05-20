package com.guandera.core.client.view.acceso;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.guandera.core.client.service.acceso.OpcionService;
import com.guandera.core.client.view.BaseManagedBean;
import com.guandera.core.server.service.acceso.OpcionServiceImpl;
import com.guandera.core.shared.model.acceso.Opcion;

@ManagedBean(name = "opcionMB")
@SessionScoped
public class OpcionManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private OpcionService opcionServicio;
	private Opcion itemSeleccionado;
	private boolean listing = false;
	private List<Opcion> listaOpcion;

	public OpcionManagedBean() {
		opcionServicio = new OpcionServiceImpl();

		inicializar();
	}

	public String actualizar() {

		try {

			itemSeleccionado.setModificacionusuario(usuarioSessionId());
			itemSeleccionado.setModificacionfecha(new Date());
			opcionServicio.actualizar(itemSeleccionado);
			mensajeInfo("OpcionUpdated");
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

			itemSeleccionado.setCreacionusuario(usuarioSessionId());
			itemSeleccionado.setCreacionfecha(new Date());
			itemSeleccionado.setModificacionusuario(usuarioSessionId());
			itemSeleccionado.setModificacionfecha(new Date());
			opcionServicio.crear(itemSeleccionado);
			mensajeInfo("OpcionCreated");
			inicializar();
			verificarLista();
			return prepararLista();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String eliminar() {

		opcionServicio.eliminar(itemSeleccionado);
		return prepararLista();
	}

	public Opcion getItemSeleccionado() {
		return itemSeleccionado;
	}

	public List<Opcion> getListaOpcion() {

		listaOpcion = opcionServicio.consultarTodos();
		return listaOpcion;
	}

	public OpcionService getOpcionServicio() {
		return opcionServicio;
	}

	private void inicializar() {
		setItemSeleccionado(new Opcion());

	}

	public boolean isListing() {
		return listing;
	}

	public void listarOpcion() {
		setListing(true);
		listaOpcion = opcionServicio.consultarTodos();
	}

	public String prepararConsulta() {
		return "Detalle";
	}

	public String prepararCreacion() {
		itemSeleccionado = new Opcion();
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

	public void setItemSeleccionado(Opcion itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public void setListaOpcion(List<Opcion> listaOpcion) {
		this.listaOpcion = listaOpcion;
	}

	public void setListing(boolean listing) {
		this.listing = listing;
	}

	public void setOpcionServicio(OpcionService opcionServicio) {
		this.opcionServicio = opcionServicio;
	}

	private void verificarLista() {
		if (listing) {
			this.listaOpcion = opcionServicio.consultarTodos();
		}
	}
}
