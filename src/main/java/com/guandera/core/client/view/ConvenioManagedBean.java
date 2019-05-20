package com.guandera.core.client.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import com.guandera.core.client.service.ConvenioService;
import com.guandera.core.server.service.ConvenioServiceImpl;
import com.guandera.core.shared.model.Convenio;
import com.guandera.core.shared.model.Sede;
import com.guandera.core.shared.model.TipoServicio;

@ManagedBean(name = "convenioMB")
@SessionScoped
public class ConvenioManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private ConvenioService convenioConvenio;
	private Convenio itemSeleccionado;
	private List<Convenio> listaItems;
	private boolean listing = false;

	private Long sedeid;
	private List<SelectItem> sedeItems;
	private boolean listingSede = false;

	private Long tipoServicioid;
	private List<SelectItem> tipoServicioItems;
	private boolean listingTipoServicio = false;

	public ConvenioManagedBean() {
		convenioConvenio = new ConvenioServiceImpl();

		inicializar();
	}

	public String actualizar() {

		try {

			TipoServicio tipoServicio = new TipoServicio();
			Sede sede = new Sede();

			tipoServicio.setTiposervicioid(tipoServicioid);
			sede.setSedeid(sedeid);

			itemSeleccionado.setTipoServicio(tipoServicio);
			itemSeleccionado.setSede(sede);

			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());
			convenioConvenio.actualizar(itemSeleccionado);
			mensajeInfo("ConvenioUpdated");
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

			TipoServicio tipoServicio = new TipoServicio();
			Sede sede = new Sede();

			tipoServicio.setTiposervicioid(tipoServicioid);
			sede.setSedeid(sedeid);

			itemSeleccionado.setTipoServicio(tipoServicio);
			itemSeleccionado.setSede(sede);

			convenioConvenio.crear(itemSeleccionado);
			mensajeInfo("ConvenioCreated");
			inicializar();
			verificarLista();

			listing = false;
			return prepararLista();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String eliminar() {

		convenioConvenio.eliminar(itemSeleccionado);
		listing = false;
		return prepararLista();
	}

	public Convenio getItemSeleccionado() {
		return itemSeleccionado;
	}

	public List<Convenio> getListaItems() {
		if (!listing) {
			listing = true;
			listaItems = convenioConvenio.consultarTodos();
		}
		return listaItems;
	}

	public ConvenioService getConvenioConvenio() {
		return convenioConvenio;
	}

	private void inicializar() {

		itemSeleccionado = new Convenio();

	}

	public boolean isListing() {
		return listing;
	}

	public void listarConvenio() {
		setListing(true);
		listaItems = convenioConvenio.consultarTodos();
	}

	public String prepararConsulta() {
		return "Detalle";
	}

	public String prepararCreacion() {
		itemSeleccionado = new Convenio();
		tipoServicioid = 0l;
		sedeid = 0L;
		return "Crear";
	}

	public String prepararEdicion() {

		tipoServicioid = itemSeleccionado.getTipoServicio().getTiposervicioid();
		sedeid = itemSeleccionado.getSede().getSedeid();

		return "Editar";
	}

	public String prepararLista() {
		return "Lista";
	}

	@PostConstruct
	public void prerender() {
		inicializar();

	}

	public void setItemSeleccionado(Convenio itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public void setListaItems(List<Convenio> listaItems) {
		this.listaItems = listaItems;
	}

	public void setListing(boolean listing) {
		this.listing = listing;
	}

	public void setConvenioConvenio(ConvenioService convenioConvenio) {
		this.convenioConvenio = convenioConvenio;
	}

	private void verificarLista() {
		if (listing) {
			this.listaItems = convenioConvenio.consultarTodos();
		}
	}

	public Long getSedeid() {
		return sedeid;
	}

	public void setSedeid(Long sedeid) {
		this.sedeid = sedeid;
	}

	public List<SelectItem> getSedeItems() {
		if (!listingSede) {
			listingSede = true;

			sedeItems = new ArrayList<SelectItem>();
			List<Sede> sedeList = convenioConvenio.consultarSedes();

			for (Sede sede : sedeList) {
				sedeItems.add(new SelectItem(sede.getSedeid(), sede.getSedenombre()));

			}
		}

		return sedeItems;
	}

	public void setSedeItems(List<SelectItem> sedeItems) {
		this.sedeItems = sedeItems;
	}

	public Long getTipoServicioid() {
		return tipoServicioid;
	}

	public void setTipoServicioid(Long tipoServicioid) {
		this.tipoServicioid = tipoServicioid;
	}

	public List<SelectItem> getTipoServicioItems() {
		if (!listingTipoServicio) {
			listingTipoServicio = true;

			tipoServicioItems = new ArrayList<SelectItem>();
			List<TipoServicio> tipoServicioList = convenioConvenio.consultarTiposConvenio();

			for (TipoServicio tipoServicio : tipoServicioList) {
				tipoServicioItems
						.add(new SelectItem(tipoServicio.getTiposervicioid(), tipoServicio.getTiposervicionombre()));

			}
		}

		return tipoServicioItems;
	}

	public void setTipoServicioItems(List<SelectItem> tipoServicioItems) {
		this.tipoServicioItems = tipoServicioItems;
	}

	public boolean isListingSede() {
		return listingSede;
	}

	public void setListingSede(boolean listingSede) {
		this.listingSede = listingSede;
	}

	public boolean isListingTipoServicio() {
		return listingTipoServicio;
	}

	public void setListingTipoServicio(boolean listingTipoServicio) {
		this.listingTipoServicio = listingTipoServicio;
	}

}
