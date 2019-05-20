package com.guandera.talento.nomina.client.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import com.guandera.core.client.view.BaseManagedBean;
import com.guandera.talento.nomina.client.service.NominaConceptoService;
import com.guandera.talento.nomina.server.service.NominaConceptoServiceImpl;
import com.guandera.talento.nomina.shared.model.NominaConcepto;
import com.guandera.talento.nomina.shared.model.NominaConceptoTipo;

;

@ManagedBean(name = "nominaConceptoMB")
@SessionScoped
public class NominaConceptoManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private NominaConceptoService nominaConceptoServicio;
	private NominaConcepto itemSeleccionado;
	private List<NominaConcepto> listaItems = new ArrayList<NominaConcepto>();
	private boolean listing = false;

	private Long conceptoTipoid;
	private List<SelectItem> conceptoTipoItems;
	private boolean listingConcepto = false;

	public NominaConceptoManagedBean() {
		nominaConceptoServicio = new NominaConceptoServiceImpl();

		inicializar();
	}

	public String actualizar() {

		try {
			
			NominaConceptoTipo conceptoTipo = new NominaConceptoTipo();
			conceptoTipo.setConceptotipoid(conceptoTipoid);
			itemSeleccionado.setConceptoTipo(conceptoTipo);
			
			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			nominaConceptoServicio.actualizar(itemSeleccionado);
			mensajeInfo("nominaConceptoUpdated");
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

			NominaConceptoTipo conceptoTipo = new NominaConceptoTipo();
			conceptoTipo.setConceptotipoid(conceptoTipoid);
			itemSeleccionado.setConceptoTipo(conceptoTipo);
			itemSeleccionado.setUsuarioCreacion(usuario());
			itemSeleccionado.setFechaCreacion(new Date());
			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());
			nominaConceptoServicio.crear(itemSeleccionado);
			mensajeInfo("NominaConceptoCreated");
			inicializar();
			verificarLista();
			return prepararLista();
		} catch (Exception e) {

			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String eliminar() {

		nominaConceptoServicio.eliminar(itemSeleccionado);
		listing = false;
		return prepararLista();
	}

	public NominaConcepto getItemSeleccionado() {
		return itemSeleccionado;
	}

	public List<NominaConcepto> getListaItems() {

		if (!listing) {
			listing = true;
			listaItems = nominaConceptoServicio.consultarTodos();
		}
		return listaItems;
	}

	public NominaConceptoService getNominaConceptoServicio() {
		return nominaConceptoServicio;
	}

	private void inicializar() {

		itemSeleccionado = new NominaConcepto();

	}

	public boolean isListing() {
		return listing;
	}

	public void listarNominaConcepto() {
		setListing(true);
		listaItems = nominaConceptoServicio.consultarTodos();
	}

	public String prepararConsulta() {
		return "Detalle";
	}

	public String prepararCreacion() {
		itemSeleccionado = new NominaConcepto();
		conceptoTipoid = (long) 0;

		return "Crear";
	}

	public String prepararEdicion() {
		
		
		conceptoTipoid = itemSeleccionado.getConceptoTipo().getConceptotipoid();
		return "Editar";
	}

	public String prepararLista() {
		return "Lista";
	}

	@PostConstruct
	public void prerender() {
		inicializar();

	}

	public void setItemSeleccionado(NominaConcepto itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public void setListaItems(List<NominaConcepto> listaItems) {
		this.listaItems = listaItems;
	}

	public void setListing(boolean listing) {
		this.listing = listing;
	}

	public void setNominaConceptoServicio(NominaConceptoService nominaConceptoServicio) {
		this.nominaConceptoServicio = nominaConceptoServicio;
	}

	private void verificarLista() {
		if (listing) {
			this.listaItems = nominaConceptoServicio.consultarTodos();
		}
	}

	public Long getConceptoTipoid() {
		return conceptoTipoid;
	}

	public void setConceptoTipoid(Long conceptoTipoid) {
		this.conceptoTipoid = conceptoTipoid;
	}

	public List<SelectItem> getConceptoTipoItems() {

		if (!listingConcepto) {
			listingConcepto = true;
			conceptoTipoItems = new ArrayList<SelectItem>();
			List<NominaConceptoTipo> conceptoTipoList = nominaConceptoServicio.consultarConceptoTipo();

			for (NominaConceptoTipo conceptoTipo : conceptoTipoList) {
				conceptoTipoItems
						.add(new SelectItem(conceptoTipo.getConceptotipoid(), conceptoTipo.getConceptotiponombre()));
			}
		}
		return conceptoTipoItems;
	}

	public void setConceptoTiponItems(List<SelectItem> conceptoTipoItems) {
		this.conceptoTipoItems = conceptoTipoItems;
	}

}
