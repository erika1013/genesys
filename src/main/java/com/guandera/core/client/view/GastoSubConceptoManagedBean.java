package com.guandera.core.client.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import com.guandera.core.client.service.GastoSubConceptoService;
import com.guandera.core.server.service.GastoSubConceptoServiceImpl;
import com.guandera.core.shared.model.GastoConcepto;
import com.guandera.core.shared.model.GastoSubConcepto;

@ManagedBean(name = "gastoSubConceptoMB")
@SessionScoped
public class GastoSubConceptoManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private GastoSubConceptoService gastoSubConceptoServicio;
	private GastoSubConcepto itemSeleccionado;
	private List<GastoSubConcepto> listaItems;
	private boolean listing = false;

	private Long conceptoid;
	private List<SelectItem> conceptoItems;
	private boolean listingConcepto = false;

	public GastoSubConceptoManagedBean() {
		gastoSubConceptoServicio = new GastoSubConceptoServiceImpl();

		inicializar();
	}

	public String actualizar() {

		try {

			GastoConcepto concepto = new GastoConcepto();
			concepto.setGastoConceptoid(conceptoid);
			itemSeleccionado.setConcepto(concepto);

			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			gastoSubConceptoServicio.actualizar(itemSeleccionado);
			mensajeInfo("GastoSubConceptoUpdated");
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

			GastoConcepto concepto = new GastoConcepto();
			concepto.setGastoConceptoid(conceptoid);
			itemSeleccionado.setConcepto(concepto);

			itemSeleccionado.setUsuarioCreacion(usuario());
			itemSeleccionado.setFechaCreacion(new Date());
			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());
			gastoSubConceptoServicio.crear(itemSeleccionado);
			mensajeInfo("GastoSubConceptoCreated");
			inicializar();
			verificarLista();
			return prepararLista();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String eliminar() {

		gastoSubConceptoServicio.eliminar(itemSeleccionado);
		listing = false;
		return prepararLista();
	}

	public GastoSubConcepto getItemSeleccionado() {
		return itemSeleccionado;
	}

	public List<GastoSubConcepto> getListaItems() {

		if (!listing) {
			listing = true;
			listaItems = gastoSubConceptoServicio.consultarTodos();
		}
		return listaItems;
	}

	public GastoSubConceptoService getGastoSubConceptoServicio() {
		return gastoSubConceptoServicio;
	}

	private void inicializar() {

		itemSeleccionado = new GastoSubConcepto();

	}

	public boolean isListing() {
		return listing;
	}

	public void listarGastoSubConcepto() {
		setListing(true);
		listaItems = gastoSubConceptoServicio.consultarTodos();
	}

	public String prepararConsulta() {
		return "Detalle";
	}

	public String prepararCreacion() {
		itemSeleccionado = new GastoSubConcepto();
		itemSeleccionado.setCodigo(gastoSubConceptoServicio.siguienteSubConcepto());
		conceptoid = (long) 0;
		return "Crear";
	}

	public String prepararEdicion() {
		conceptoid = itemSeleccionado.getConcepto().getGastoConceptoid();
		return "Editar";
	}

	public String prepararLista() {
		return "Lista";
	}

	@PostConstruct
	public void prerender() {
		inicializar();

	}

	public void setItemSeleccionado(GastoSubConcepto itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public void setListaItems(List<GastoSubConcepto> listaItems) {
		this.listaItems = listaItems;
	}

	public void setListing(boolean listing) {
		this.listing = listing;
	}

	public void setGastoSubConceptoServicio(GastoSubConceptoService gastoSubConceptoServicio) {
		this.gastoSubConceptoServicio = gastoSubConceptoServicio;
	}

	private void verificarLista() {
		if (listing) {
			this.listaItems = gastoSubConceptoServicio.consultarTodos();
		}
	}

	public List<SelectItem> getConceptoItems() {
		if (!listingConcepto) {
			listingConcepto = true;

			conceptoItems = new ArrayList<SelectItem>();
			List<GastoConcepto> conceptoList = gastoSubConceptoServicio.consultarConceptos();

			for (GastoConcepto concepto : conceptoList) {
				conceptoItems.add(new SelectItem(concepto.getGastoConceptoid(), concepto.getGastoConceptonombre()));

			}
		}
		return conceptoItems;
	}

	public void setConceptoItems(List<SelectItem> conceptoItems) {
		this.conceptoItems = conceptoItems;
	}

	public Long getConceptoid() {
		return conceptoid;
	}

	public void setConceptoid(Long conceptoid) {
		this.conceptoid = conceptoid;
	}

}
