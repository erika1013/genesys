package com.guandera.core.client.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import com.guandera.core.client.service.ProveedorService;
import com.guandera.core.server.service.ProveedorServiceImpl;
import com.guandera.core.shared.model.Compania;
import com.guandera.core.shared.model.Proveedor;
import com.guandera.core.shared.model.ProveedorContacto;
import com.guandera.core.shared.model.TipoIdentificacion;

@ManagedBean(name = "proveedorMB")
@SessionScoped
public class ProveedorManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private ProveedorService proveedorServicio;
	private Proveedor itemSeleccionado;
	private List<Proveedor> listaItems = new ArrayList<Proveedor>();
	private boolean listing = false;

	private ProveedorContacto itemProveedorContacto;
	private List<ProveedorContacto> listaProveedorContactoItems = new ArrayList<ProveedorContacto>();
	private boolean listingProveedorContacto = false;

	private Long companiaid;
	private List<SelectItem> companiaItems;
	private boolean listingCompania = false;

	private Long tipoidentificacionid;
	private List<SelectItem> tipoIdentificacionItems;
	private boolean listingTipoIdentificacion = false;

	public ProveedorManagedBean() {
		proveedorServicio = new ProveedorServiceImpl();

		inicializar();
	}

	public String actualizar() {

		try {

			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			proveedorServicio.actualizar(itemSeleccionado);
			mensajeInfo("ProveedorUpdated");
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

			TipoIdentificacion tipoid = new TipoIdentificacion();
			Compania compania = new Compania();

			tipoid.setTipoidentificacionid(tipoidentificacionid);
			compania.setCompaniaid(companiaid);
			itemSeleccionado.setTipoIdentificacion(tipoid);
			itemSeleccionado.setCompania(compania);
			itemSeleccionado.setUsuarioCreacion(usuario());
			itemSeleccionado.setFechaCreacion(new Date());
			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());
			proveedorServicio.crear(itemSeleccionado);
			inicializar();
			verificarLista();
			mensajeInfo("ProveedorCreated");

			return prepararLista();
		} catch (Exception e) {

			System.out.println("Error: " + e.getMessage());
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String eliminar() {

		proveedorServicio.eliminar(itemSeleccionado);
		listing = false;
		return prepararLista();
	}

	public Proveedor getItemSeleccionado() {
		return itemSeleccionado;
	}

	public List<Proveedor> getListaItems() {

		if (!listing) {
			listing = true;
			listaItems = proveedorServicio.consultarTodos();
		}
		return listaItems;
	}

	public ProveedorService getProveedorServicio() {
		return proveedorServicio;
	}

	private void inicializar() {

		itemSeleccionado = new Proveedor();

	}

	public boolean isListing() {
		return listing;
	}

	public void listarProveedor() {
		setListing(true);
		listaItems = proveedorServicio.consultarTodos();
	}

	public String prepararConsulta() {
		listingProveedorContacto = false;
		return "Detalle";
	}

	public String prepararCreacion() {
		itemSeleccionado = new Proveedor();
		companiaid = (long) 0;
		tipoidentificacionid = (long) 0;

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

	public void setItemSeleccionado(Proveedor itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public void setListaItems(List<Proveedor> listaItems) {
		this.listaItems = listaItems;
	}

	public void setListing(boolean listing) {
		this.listing = listing;
	}

	public void setProveedorServicio(ProveedorService proveedorServicio) {
		this.proveedorServicio = proveedorServicio;
	}

	private void verificarLista() {
		if (listing) {
			this.listaItems = proveedorServicio.consultarTodos();
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
			List<Compania> companiaList = proveedorServicio.consultarCompanias();

			for (Compania compania : companiaList) {
				companiaItems.add(new SelectItem(compania.getCompaniaid(), compania.getCompanianombre()));
			}
		}

		return companiaItems;
	}

	public void setCompaniaItems(List<SelectItem> companiaItems) {
		this.companiaItems = companiaItems;
	}

	public Long getTipoidentificacionid() {
		return tipoidentificacionid;
	}

	public void setTipoidentificacionid(Long tipoidentificacionid) {
		this.tipoidentificacionid = tipoidentificacionid;
	}

	public List<SelectItem> getTipoIdentificacionItems() {

		if (!listingTipoIdentificacion) {
			listingTipoIdentificacion = true;
			tipoIdentificacionItems = new ArrayList<SelectItem>();
			List<TipoIdentificacion> tipoIdList = proveedorServicio.consultarTiposIdentificacion();

			for (TipoIdentificacion tipoid : tipoIdList) {
				tipoIdentificacionItems
						.add(new SelectItem(tipoid.getTipoidentificacionid(), tipoid.getTipoidentificacionnombre()));

			}
		}

		return tipoIdentificacionItems;
	}

	public void setTipoIdentificacionItems(List<SelectItem> tipoIdentificacionItems) {
		this.tipoIdentificacionItems = tipoIdentificacionItems;
	}

	public ProveedorContacto getItemProveedorContacto() {
		return itemProveedorContacto;
	}

	public void setItemProveedorContacto(ProveedorContacto itemProveedorContacto) {
		this.itemProveedorContacto = itemProveedorContacto;
	}

	public List<ProveedorContacto> getListaProveedorContactoItems() {

		if (!listingProveedorContacto) {
			listingProveedorContacto = true;
			listaProveedorContactoItems = proveedorServicio
					.consultarProveedorContactos(itemSeleccionado.getProveedorid());
		}
		return listaProveedorContactoItems;
	}

	public void setListaProveedorContactoItems(List<ProveedorContacto> listaProveedorContactoItems) {
		this.listaProveedorContactoItems = listaProveedorContactoItems;
	}

	public String prepararConsultaProveedorContacto() {
		return "ProveedorContactoDetalle";
	}

	public String prepararCreacionProveedorContacto() {
		itemProveedorContacto = new ProveedorContacto();
		return "ProveedorContactoCrear";
	}

	public String prepararEdicionProveedorContacto() {
		return "ProveedorContactoEditar";
	}

	public String prepararListaProveedorContacto() {
		return "ProveedorContactoLista";
	}

	public String crearProveedorContacto() {

		try {

			itemProveedorContacto.setUsuarioCreacion(usuario());
			itemProveedorContacto.setFechaCreacion(new Date());
			itemProveedorContacto.setUsuarioModificacion(usuario());
			itemProveedorContacto.setFechaModificacion(new Date());

			itemProveedorContacto.setProveedor(itemSeleccionado);

			proveedorServicio.crearProveedorContacto(itemProveedorContacto);

			mensajeInfo("ProveedorContactoCreated");
			return prepararConsultaProveedorContacto();

		} catch (Exception e) {

			mensajeError("PersistenceErrorOccured");
			return null;

		}

	}

	public String actualizarProveedorContacto() {

		try {
			itemProveedorContacto.setUsuarioModificacion(usuario());
			itemProveedorContacto.setFechaModificacion(new Date());

			proveedorServicio.actualizarProveedorContacto(itemProveedorContacto);

			mensajeInfo("ProveedorContactoUpdated");
			return prepararListaProveedorContacto();

		} catch (Exception e) {

			mensajeError("PersistenceErrorOccured");
			return null;

		}

	}

	public String eliminarProveedorContacto() {

		try {

			proveedorServicio.eliminarProveedorContacto(itemProveedorContacto);

			mensajeInfo("ProveedorContactoDeleted");
			return prepararListaProveedorContacto();

		} catch (Exception e) {

			mensajeError("PersistenceErrorOccured");
			return null;

		}

	}

}
