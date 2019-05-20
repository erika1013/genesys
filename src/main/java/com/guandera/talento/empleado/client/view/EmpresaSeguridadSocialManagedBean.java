package com.guandera.talento.empleado.client.view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.guandera.core.client.service.EmpresaSeguridadSocialService;
import com.guandera.core.client.view.BaseManagedBean;
import com.guandera.talento.empleado.server.service.EmpresaSeguridadSocialServiceImpl;
import com.guandera.talento.empleado.shared.model.EmpresaSeguridadSocial;

@ManagedBean(name = "empresaSeguridadSocialMB")
@SessionScoped
public class EmpresaSeguridadSocialManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private EmpresaSeguridadSocialService empresaSeguridadSocialServicio;
	private EmpresaSeguridadSocial itemSeleccionado;
	private List<EmpresaSeguridadSocial> listaItems;
	private boolean listing = false;

	public EmpresaSeguridadSocialManagedBean() {
		empresaSeguridadSocialServicio = new EmpresaSeguridadSocialServiceImpl();

		inicializar();
	}

	public String actualizar() {

		try {

			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			empresaSeguridadSocialServicio.actualizar(itemSeleccionado);
			mensajeInfo("EmpresaSeguridadSocialUpdated");
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

			empresaSeguridadSocialServicio.crear(itemSeleccionado);
			mensajeInfo("EmpresaSeguridadSocialCreated");
			inicializar();
			verificarLista();
			return prepararLista();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String eliminar() {

		empresaSeguridadSocialServicio.eliminar(itemSeleccionado);
		listing = false;
		return prepararLista();
	}

	public EmpresaSeguridadSocial getItemSeleccionado() {
		return itemSeleccionado;
	}

	public List<EmpresaSeguridadSocial> getListaItems() {
		if (!listing) {
			listing = true;

			listaItems = empresaSeguridadSocialServicio.consultarTodos();
		}
		return listaItems;
	}

	public EmpresaSeguridadSocialService getEmpresaSeguridadSocialServicio() {
		return empresaSeguridadSocialServicio;
	}

	private void inicializar() {

		itemSeleccionado = new EmpresaSeguridadSocial();

	}

	public boolean isListing() {
		return listing;
	}

	public void listarEmpresaSeguridadSocial() {
		setListing(true);
		listaItems = empresaSeguridadSocialServicio.consultarTodos();
	}

	public String prepararConsulta() {
		return "Detalle";
	}

	public String prepararCreacion() {
		itemSeleccionado = new EmpresaSeguridadSocial();
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

	public void setItemSeleccionado(EmpresaSeguridadSocial itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public void setListaItems(List<EmpresaSeguridadSocial> listaItems) {
		this.listaItems = listaItems;
	}

	public void setListing(boolean listing) {
		this.listing = listing;
	}

	public void setEmpresaSeguridadSocialServicio(EmpresaSeguridadSocialService empresaSeguridadSocialServicio) {
		this.empresaSeguridadSocialServicio = empresaSeguridadSocialServicio;
	}

	private void verificarLista() {
		if (listing) {
			this.listaItems = empresaSeguridadSocialServicio.consultarTodos();
		}
	}
}
