package com.guandera.core.client.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import com.guandera.core.client.service.CentroCostosService;
import com.guandera.core.server.service.CentroCostosServiceImpl;
import com.guandera.core.shared.model.CentroCostos;
import com.guandera.core.shared.model.Compania;

@ManagedBean(name = "centroCostosMB")
@SessionScoped
public class CentroCostosManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private CentroCostosService centroCostosServicio;
	private CentroCostos itemSeleccionado;
	private List<CentroCostos> listaItems = new ArrayList<CentroCostos>();
	private boolean listing = false;

	private Long companiaid;
	private List<SelectItem> companiaItems;
	private boolean listingCompania = false;

	public CentroCostosManagedBean() {
		centroCostosServicio = new CentroCostosServiceImpl();

		inicializar();
	}

	public String actualizar() {

		try {
			Compania compania = new Compania();

			compania.setCompaniaid(companiaid);

			itemSeleccionado.setCompania(compania);

			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());
			centroCostosServicio.actualizar(itemSeleccionado);
			mensajeInfo("CentroCostosUpdated");
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
			boolean existeCentroCostos = centroCostosServicio.existeCentroCostos(itemSeleccionado.getCodigo());
			if (existeCentroCostos == false) {
				Compania compania = new Compania();

				compania.setCompaniaid(companiaid);

				itemSeleccionado.setCompania(compania);
				itemSeleccionado.setUsuarioCreacion(usuario());
				itemSeleccionado.setFechaCreacion(new Date());
				itemSeleccionado.setUsuarioModificacion(usuario());
				itemSeleccionado.setFechaModificacion(new Date());
				centroCostosServicio.crear(itemSeleccionado);
				inicializar();
				verificarLista();
				mensajeInfo("CentroCostosCreated");
				return prepararLista();
			} else {
				error("El c�digo ya se encuentra asignado");
				return null;
			}

		} catch (Exception e) {

			System.out.println("Error: " + e.getMessage());
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String eliminar() {

		centroCostosServicio.eliminar(itemSeleccionado);
		listing = false;
		return prepararLista();
	}

	public CentroCostos getItemSeleccionado() {
		return itemSeleccionado;
	}

	public List<CentroCostos> getListaItems() {

		if (!listing) {
			listing = true;
			listaItems = centroCostosServicio.consultarTodos();
		}
		return listaItems;
	}

	public CentroCostosService getCentroCostosServicio() {
		return centroCostosServicio;
	}

	private void inicializar() {

		itemSeleccionado = new CentroCostos();

	}

	public boolean isListing() {
		return listing;
	}

	public void listarCentroCostos() {
		setListing(true);
		listaItems = centroCostosServicio.consultarTodos();
	}

	public String prepararConsulta() {
		return "Detalle";
	}

	public String prepararCreacion() {
		itemSeleccionado = new CentroCostos();
		companiaid = (long) 0;

		return "Crear";
	}

	public String prepararEdicion() {
		companiaid = itemSeleccionado.getCompania().getCompaniaid();
		return "Editar";
	}

	public String prepararLista() {
		return "Lista";
	}

	@PostConstruct
	public void prerender() {
		inicializar();

	}

	public void setItemSeleccionado(CentroCostos itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public void setListaItems(List<CentroCostos> listaItems) {
		this.listaItems = listaItems;
	}

	public void setListing(boolean listing) {
		this.listing = listing;
	}

	public void setCentroCostosServicio(CentroCostosService centroCostosServicio) {
		this.centroCostosServicio = centroCostosServicio;
	}

	private void verificarLista() {
		if (listing) {
			this.listaItems = centroCostosServicio.consultarTodos();
		}
	}

	public Long getCompaniaid() {
		return companiaid;
	}

	public void setCompaniaid(Long companiaid) {
		this.companiaid = companiaid;
	}

	public List<SelectItem> getCompaniaItems() {

		if (!listingCompania) {
			listingCompania = true;
			companiaItems = new ArrayList<SelectItem>();
			List<Compania> companiaList = centroCostosServicio.consultarCompanias();

			for (Compania compania : companiaList) {
				companiaItems.add(new SelectItem(compania.getCompaniaid(),
						"Nit: " + compania.getCompanianit() + " / " + compania.getCompanianombre()));

			}
		}

		return companiaItems;
	}

	public void setCompaniaItems(List<SelectItem> companiaItems) {
		this.companiaItems = companiaItems;
	}

}
