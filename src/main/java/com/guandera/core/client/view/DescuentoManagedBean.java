package com.guandera.core.client.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import com.guandera.core.client.service.DescuentoService;
import com.guandera.core.server.service.DescuentoServiceImpl;
import com.guandera.core.shared.model.Descuento;
import com.guandera.core.shared.model.Sede;
import com.guandera.core.shared.model.TipoServicio;

@ManagedBean(name = "descuentoMB")
@SessionScoped
public class DescuentoManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private DescuentoService descuentoDescuento;
	private Descuento itemSeleccionado;
	private List<Descuento> listaItems;
	private boolean listing = false;

	private Long sedeid;
	private List<SelectItem> sedeItems;
	private boolean listingsede = false;

	private Long tipoServicioid;
	private List<SelectItem> tipoServicioItems;

	private boolean listingtipoServicio = false;

	public DescuentoManagedBean() {
		descuentoDescuento = new DescuentoServiceImpl();

		inicializar();
	}

	public String actualizar() {

		try {

			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			descuentoDescuento.actualizar(itemSeleccionado);
			mensajeInfo("DescuentoUpdated");
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

			TipoServicio tipoServicio = new TipoServicio();
			Sede sede = new Sede();

			tipoServicio.setTiposervicioid(tipoServicioid);
			sede.setSedeid(sedeid);

			itemSeleccionado.setUsuarioCreacion(usuario());
			itemSeleccionado.setFechaCreacion(new Date());
			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			itemSeleccionado.setTipoServicio(tipoServicio);
			itemSeleccionado.setSede(sede);

			descuentoDescuento.crear(itemSeleccionado);
			mensajeInfo("DescuentoCreated");
			inicializar();
			verificarLista();
			return prepararLista();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String eliminar() {

		descuentoDescuento.eliminar(itemSeleccionado);
		listing = false;
		return prepararLista();
	}

	public Descuento getItemSeleccionado() {
		return itemSeleccionado;
	}

	public List<Descuento> getListaItems() {
		if (!listing) {
			listing = true;
			listaItems = descuentoDescuento.consultarTodos();
		}
		return listaItems;
	}

	public DescuentoService getDescuentoDescuento() {
		return descuentoDescuento;
	}

	private void inicializar() {

		itemSeleccionado = new Descuento();

	}

	public boolean isListing() {
		return listing;
	}

	public void listarDescuento() {
		setListing(true);
		listaItems = descuentoDescuento.consultarTodos();
	}

	public String prepararConsulta() {
		return "Detalle";
	}

	public String prepararCreacion() {
		itemSeleccionado = new Descuento();
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

	public void setItemSeleccionado(Descuento itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public void setListaItems(List<Descuento> listaItems) {
		this.listaItems = listaItems;
	}

	public void setListing(boolean listing) {
		this.listing = listing;
	}

	public void setDescuentoDescuento(DescuentoService descuentoDescuento) {
		this.descuentoDescuento = descuentoDescuento;
	}

	private void verificarLista() {
		if (listing) {
			this.listaItems = descuentoDescuento.consultarTodos();
		}
	}

	public Long getSedeid() {
		return sedeid;
	}

	public void setSedeid(Long sedeid) {
		this.sedeid = sedeid;
	}

	public List<SelectItem> getSedeItems() {
		if (!listingsede) {
			listingsede = true;

			sedeItems = new ArrayList<SelectItem>();
			List<Sede> sedeList = descuentoDescuento.consultarSedes();

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
		if (!listingtipoServicio) {
			listingtipoServicio = true;
			tipoServicioItems = new ArrayList<SelectItem>();
			List<TipoServicio> tipoServicioList = descuentoDescuento.consultarTiposDescuento();

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

}
