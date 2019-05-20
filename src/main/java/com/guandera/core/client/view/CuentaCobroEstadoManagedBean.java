package com.guandera.core.client.view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.guandera.core.client.service.CuentaCobroEstadoService;
import com.guandera.core.server.service.CuentaCobroEstadoServiceImpl;
import com.guandera.core.shared.model.CuentaCobroEstado;

@ManagedBean(name = "cuentaCobroEstadoMB")
@SessionScoped
public class CuentaCobroEstadoManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private CuentaCobroEstadoService cuentaCobroEstadoServicio;
	private CuentaCobroEstado itemSeleccionado;
	private List<CuentaCobroEstado> listaItems;
	private boolean listing = false;

	public CuentaCobroEstadoManagedBean() {
		cuentaCobroEstadoServicio = new CuentaCobroEstadoServiceImpl();

		inicializar();
	}

	public String actualizar() {

		try {

			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			cuentaCobroEstadoServicio.actualizar(itemSeleccionado);
			mensajeInfo("CuentaCobroEstadoUpdated");
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

			cuentaCobroEstadoServicio.crear(itemSeleccionado);
			mensajeInfo("CuentaCobroEstadoCreated");
			inicializar();
			verificarLista();
			return prepararLista();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String eliminar() {

		cuentaCobroEstadoServicio.eliminar(itemSeleccionado);
		listing = false;
		return prepararLista();
	}

	public CuentaCobroEstado getItemSeleccionado() {
		return itemSeleccionado;
	}

	public List<CuentaCobroEstado> getListaItems() {
		if (!listing) {
			listing = true;
			listaItems = cuentaCobroEstadoServicio.consultarTodos();
		}
		return listaItems;
	}

	public CuentaCobroEstadoService getCuentaCobroEstadoServicio() {
		return cuentaCobroEstadoServicio;
	}

	private void inicializar() {

		itemSeleccionado = new CuentaCobroEstado();

	}

	public boolean isListing() {
		return listing;
	}

	public void listarCuentaCobroEstado() {
		setListing(true);
		listaItems = cuentaCobroEstadoServicio.consultarTodos();
	}

	public String prepararConsulta() {
		return "Detalle";
	}

	public String prepararCreacion() {
		itemSeleccionado = new CuentaCobroEstado();
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

	public void setItemSeleccionado(CuentaCobroEstado itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public void setListaItems(List<CuentaCobroEstado> listaItems) {
		this.listaItems = listaItems;
	}

	public void setListing(boolean listing) {
		this.listing = listing;
	}

	public void setCuentaCobroEstadoServicio(CuentaCobroEstadoService cuentaCobroEstadoServicio) {
		this.cuentaCobroEstadoServicio = cuentaCobroEstadoServicio;
	}

	private void verificarLista() {
		if (listing) {
			this.listaItems = cuentaCobroEstadoServicio.consultarTodos();
		}
	}
}
