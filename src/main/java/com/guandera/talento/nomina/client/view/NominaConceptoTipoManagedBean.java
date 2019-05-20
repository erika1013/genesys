package com.guandera.talento.nomina.client.view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.guandera.core.client.view.BaseManagedBean;
import com.guandera.talento.nomina.client.service.NominaConceptoTipoService;
import com.guandera.talento.nomina.server.service.NominaConceptoTipoServiceImpl;
import com.guandera.talento.nomina.shared.model.NominaConceptoTipo;

@ManagedBean(name = "nominaConceptoTipoMB")
@SessionScoped
public class NominaConceptoTipoManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private NominaConceptoTipoService nominaNominaConceptoTipoServicio;
	private NominaConceptoTipo itemSeleccionado;
	private List<NominaConceptoTipo> listaItems;

	private boolean listing = false;

	public NominaConceptoTipoManagedBean() {
		nominaNominaConceptoTipoServicio = new NominaConceptoTipoServiceImpl();

		inicializar();
	}

	public String actualizar() {

		try {

			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			nominaNominaConceptoTipoServicio.actualizar(itemSeleccionado);
			mensajeInfo("NominaConceptoTipoUpdated");
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

			nominaNominaConceptoTipoServicio.crear(itemSeleccionado);
			mensajeInfo("NominaConceptoTipoCreated");
			inicializar();
			verificarLista();
			return prepararLista();
		} catch (Exception e) {
			System.out.print(e.getMessage());
			System.out.println(e.getLocalizedMessage());
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String eliminar() {

		nominaNominaConceptoTipoServicio.eliminar(itemSeleccionado);
		listing = false;
		return prepararLista();
	}

	public NominaConceptoTipo getItemSeleccionado() {
		return itemSeleccionado;
	}

	public List<NominaConceptoTipo> getListaItems() {
		if (!listing) {
			listing = true;
			listaItems = nominaNominaConceptoTipoServicio.consultarTodos();
		}
		return listaItems;
	}

	public NominaConceptoTipoService getNominaConceptoTipoServicio() {
		return nominaNominaConceptoTipoServicio;
	}

	private void inicializar() {

		itemSeleccionado = new NominaConceptoTipo();

	}

	public boolean isListing() {
		return listing;
	}

	public boolean tipo() {

		return true;

	}

	public void listarNominaConceptoTipo() {
		setListing(true);
		listaItems = nominaNominaConceptoTipoServicio.consultarTodos();
	}

	public String prepararConsulta() {
		return "Detalle";
	}

	public String prepararCreacion() {
		itemSeleccionado = new NominaConceptoTipo();
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

	public void setItemSeleccionado(NominaConceptoTipo itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public void setListaItems(List<NominaConceptoTipo> listaItems) {
		this.listaItems = listaItems;
	}

	public void setListing(boolean listing) {
		this.listing = listing;
	}

	public void setNominaConceptoTipoServicio(NominaConceptoTipoService nominaNominaConceptoTipoServicio) {
		this.nominaNominaConceptoTipoServicio = nominaNominaConceptoTipoServicio;
	}

	private void verificarLista() {
		if (listing) {
			this.listaItems = nominaNominaConceptoTipoServicio.consultarTodos();
		}
	}

}
