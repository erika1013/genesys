package com.guandera.core.client.view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.guandera.core.client.service.ImpuestoConceptoService;
import com.guandera.core.server.service.ImpuestoConceptoServiceImpl;
import com.guandera.core.shared.model.ImpuestoConcepto;

@ManagedBean(name = "impuestoConceptoMB")
@SessionScoped
public class ImpuestoConceptoManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private ImpuestoConceptoService impuestoConceptoServicio;
	private ImpuestoConcepto itemSeleccionado;
	private List<ImpuestoConcepto> listaItems;
	private boolean listing = false;

	public ImpuestoConceptoManagedBean() {
		impuestoConceptoServicio = new ImpuestoConceptoServiceImpl();

		inicializar();
	}

	public String actualizar() {

		try {
			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			impuestoConceptoServicio.actualizar(itemSeleccionado);
			mensajeInfo("ImpuestoConceptoUpdated");
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
			itemSeleccionado.setImpuestoCodigo(impuestoConceptoServicio.siguienteRegistro());
			itemSeleccionado.setUsuarioCreacion(usuario());
			itemSeleccionado.setFechaCreacion(new Date());
			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			impuestoConceptoServicio.crear(itemSeleccionado);
			mensajeInfo("ImpuestoConceptoCreated");
			inicializar();
			verificarLista();
			return prepararLista();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String eliminar() {

		impuestoConceptoServicio.eliminar(itemSeleccionado);
		listing = false;
		return prepararLista();
	}

	public ImpuestoConcepto getItemSeleccionado() {
		return itemSeleccionado;
	}

	public List<ImpuestoConcepto> getListaItems() {
		if (!listing) {
			listing = true;
			listaItems = impuestoConceptoServicio.consultarTodos();
		}
		return listaItems;
	}

	public ImpuestoConceptoService getImpuestoConceptoServicio() {
		return impuestoConceptoServicio;
	}

	private void inicializar() {

		itemSeleccionado = new ImpuestoConcepto();

	}

	public boolean isListing() {
		return listing;
	}

	public void listarImpuestoConcepto() {
		setListing(true);
		listaItems = impuestoConceptoServicio.consultarTodos();
	}

	public String prepararConsulta() {
		return "Detalle";
	}

	public String prepararCreacion() {
		itemSeleccionado = new ImpuestoConcepto();
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

	public void setItemSeleccionado(ImpuestoConcepto itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public void setListaItems(List<ImpuestoConcepto> listaItems) {
		this.listaItems = listaItems;
	}

	public void setListing(boolean listing) {
		this.listing = listing;
	}

	public void setImpuestoConceptoServicio(ImpuestoConceptoService impuestoConceptoServicio) {
		this.impuestoConceptoServicio = impuestoConceptoServicio;
	}

	private void verificarLista() {
		if (listing) {
			this.listaItems = impuestoConceptoServicio.consultarTodos();
		}
	}
}
