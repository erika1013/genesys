package com.guandera.core.client.view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.guandera.core.client.service.CompaniaService;
import com.guandera.core.server.service.CompaniaServiceImpl;
import com.guandera.core.shared.model.Compania;
import com.guandera.core.shared.model.CompaniaCuenta;
import com.guandera.core.shared.model.CompaniaImpuesto;
import com.guandera.core.shared.model.Gasto;
import com.guandera.core.shared.model.Sede;

@ManagedBean(name = "companiaMB")
@SessionScoped
public class CompaniaManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private CompaniaService companiaServicio;

	private Compania itemSeleccionado;
	private List<Compania> listaItems;
	private boolean listing = false;

	private List<CompaniaImpuesto> listaImpuesto;
	private CompaniaImpuesto itemImpuesto;
	private boolean listingImpuesto = false;

	private List<Sede> listaSede;
	private Sede itemSede;
	private boolean listingSede = false;

	private List<CompaniaCuenta> listaCuenta;
	private CompaniaCuenta itemCuenta;
	private boolean listingCuenta = false;

	public CompaniaManagedBean() {
		companiaServicio = new CompaniaServiceImpl();

		inicializar();
	}

	public String actualizar() {

		try {

			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			companiaServicio.actualizar(itemSeleccionado);
			mensajeInfo("CompaniaUpdated");
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

			// itemSeleccionado.setCompaniaNumero(companiaServicio.siguienteRegistro1());
			itemSeleccionado.setUsuarioCreacion(usuario());
			itemSeleccionado.setFechaCreacion(new Date());
			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());
			companiaServicio.crear(itemSeleccionado);

			mensajeInfo("CompaniaCreated");
			inicializar();
			verificarLista();
			return prepararLista();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String eliminar() {

		companiaServicio.eliminar(itemSeleccionado);
		listing = false;
		return prepararLista();
	}

	public Compania getItemSeleccionado() {
		return itemSeleccionado;
	}

	public List<Compania> getListaItems() {
		if (!listing) {
			listing = true;
			// listaItems =
			// companiaServicio.consultarPorUsuarioAdmCreador(usuario());
			listaItems = companiaServicio.consultarTodos();
			System.out.println(listaItems);
		}
		return listaItems;
	}

	public CompaniaService getCompaniaServicio() {
		return companiaServicio;
	}

	private void inicializar() {

		itemSeleccionado = new Compania();

	}

	public boolean isListing() {
		return listing;
	}

	public void listarCompania() {
		setListing(true);
		// listaItems =
		// companiaServicio.consultarPorUsuarioAdmCreador(usuario());
		listaItems = companiaServicio.consultarTodos();

		System.out.println(listaItems);
	}

	public String prepararConsulta() {
		listingSede = false;
		return "Detalle";
	}

	public String prepararCreacion() {
		itemSeleccionado = new Compania();
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

	public void setItemSeleccionado(Compania itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public void setListaItems(List<Compania> listaItems) {
		this.listaItems = listaItems;
	}

	public void setListing(boolean listing) {
		this.listing = listing;
	}

	public void setCompaniaServicio(CompaniaService companiaServicio) {
		this.companiaServicio = companiaServicio;
	}

	private void verificarLista() {
		if (listing) {
			// this.listaItems =
			// companiaServicio.consultarPorUsuarioAdmCreador(usuario());

			this.listaItems = companiaServicio.consultarTodos();

		}
	}

	// informacion de Sede

	public List<Sede> getListaSede() {

		if (!listingSede) {
			listingSede = true;
			listaSede = companiaServicio.consultarSedesPorCompania(itemSeleccionado.getCompaniaid());

		}
		return listaSede;
	}

	public void setListaSede(List<Sede> listaSede) {
		this.listaSede = listaSede;
	}

	public Sede getItemSede() {
		return itemSede;
	}

	public void setItemSede(Sede itemSede) {
		this.itemSede = itemSede;
	}

	public String prepararConsultaSede() {
		return "SedeDetalle";
	}

	public String prepararCreacionSede() {
		setItemSede(new Sede());
		return "SedeCrear";
	}

	public String prepararEdicionSede() {
		return "SedeEditar";
	}

	public String crearSede() {

		try {

			getItemSede().setCompania(itemSeleccionado);

			getItemSede().setUsuarioCreacion(usuario());
			getItemSede().setFechaCreacion(new Date());
			getItemSede().setUsuarioModificacion(usuario());
			getItemSede().setFechaModificacion(new Date());

			companiaServicio.crearSede(getItemSede());

			mensajeInfo("SedeCreated");
			listingSede = false;
			return prepararConsulta();

		} catch (Exception e) {

			mensajeError("PersistenceErrorOccured");
			return null;

		}

	}

	public String actualizarSede() {

		try {
			getItemSede().setUsuarioModificacion(usuario());
			getItemSede().setFechaModificacion(new Date());

			companiaServicio.actualizarSede(getItemSede());

			mensajeInfo("SedeUpdated");
			listingSede = false;
			return prepararConsulta();

		} catch (Exception e) {
			System.out.print("Donde" + e.getLocalizedMessage());
			System.out.print("Que0" + e.getMessage());
			mensajeError("PersistenceErrorOccured");
			return null;

		}

	}

	public String eliminarSede() {

		try {

			companiaServicio.eliminarSede(getItemSede());

			mensajeInfo("SedeDeleted");
			return prepararConsulta();

		} catch (Exception e) {

			mensajeError("PersistenceErrorOccured");
			return null;

		}

	}

	public List<CompaniaImpuesto> getListaImpuesto() {

		if (!listingImpuesto) {
			listingImpuesto = true;
			listaImpuesto = companiaServicio.consultarImpuestoPorCompania(itemSeleccionado.getCompaniaid());

		}

		return listaImpuesto;
	}

	public void setListaImpuesto(List<CompaniaImpuesto> listaImpuesto) {
		this.listaImpuesto = listaImpuesto;
	}

	public CompaniaImpuesto getItemImpuesto() {
		return itemImpuesto;
	}

	public void setItemImpuesto(CompaniaImpuesto itemImpuesto) {
		this.itemImpuesto = itemImpuesto;
	}

	public String prepararConsultaImpuesto() {
		return "ImpuestoDetalle";
	}

	public String prepararCreacionImpuesto() {

		itemImpuesto = new CompaniaImpuesto();
		return "ImpuestoCrear";
	}

	public String prepararEdicionImpuesto() {
		return "ImpuestoEditar";
	}

	public String crearImpuesto() {

		try {

			itemImpuesto.setCompania(itemSeleccionado);

			itemImpuesto.setUsuarioCreacion(usuario());
			itemImpuesto.setFechaCreacion(new Date());
			itemImpuesto.setUsuarioModificacion(usuario());
			itemImpuesto.setFechaModificacion(new Date());

			companiaServicio.crearImpuesto(itemImpuesto);

			mensajeInfo("ImpuestoCreated");
			listingImpuesto = false;
			return prepararConsulta();

		} catch (Exception e) {

			mensajeError("PersistenceErrorOccured");
			return null;

		}

	}

	public String actualizarImpuesto() {

		try {
			itemImpuesto.setUsuarioModificacion(usuario());
			itemImpuesto.setFechaModificacion(new Date());

			companiaServicio.actualizarImpuesto(itemImpuesto);

			mensajeInfo("ImpuestoUpdated");
			listingImpuesto = false;
			return prepararConsulta();

		} catch (Exception e) {

			mensajeError("PersistenceErrorOccured");
			return null;

		}

	}

	public String eliminarImpuesto() {

		try {

			companiaServicio.eliminarImpuesto(itemImpuesto);

			mensajeInfo("ImpuestoDeleted");
			return prepararConsulta();

		} catch (Exception e) {

			mensajeError("PersistenceErrorOccured");
			return null;

		}

	}

	public List<CompaniaCuenta> getListaCuenta() {

		if (!listingCuenta) {
			listingCuenta = true;
			listaCuenta = companiaServicio.consultarCuentaPorCompania(itemSeleccionado.getCompaniaid());

		}

		return listaCuenta;
	}

	public void setListaCuenta(List<CompaniaCuenta> listaCuenta) {
		this.listaCuenta = listaCuenta;
	}

	public CompaniaCuenta getItemCuenta() {
		return itemCuenta;
	}

	public void setItemCuenta(CompaniaCuenta itemCuenta) {
		this.itemCuenta = itemCuenta;
	}

	public String prepararConsultaCuenta() {
		return "CuentaDetalle";
	}

	public String prepararCreacionCuenta() {

		itemCuenta = new CompaniaCuenta();
		return "CuentaCrear";
	}

	public String prepararEdicionCuenta() {
		return "CuentaEditar";
	}

	public String crearCuenta() {

		try {

			itemCuenta.setCompania(itemSeleccionado);

			itemCuenta.setUsuarioCreacion(usuario());
			itemCuenta.setFechaCreacion(new Date());
			itemCuenta.setUsuarioModificacion(usuario());
			itemCuenta.setFechaModificacion(new Date());

			companiaServicio.crearCuenta(itemCuenta);

			mensajeInfo("CuentaCreated");
			listingCuenta = false;
			return prepararConsulta();

		} catch (Exception e) {

			mensajeError("PersistenceErrorOccured");
			return null;

		}

	}

	public String actualizarCuenta() {

		try {
			itemCuenta.setUsuarioModificacion(usuario());
			itemCuenta.setFechaModificacion(new Date());

			companiaServicio.actualizarCuenta(itemCuenta);

			mensajeInfo("CuentaUpdated");
			listingCuenta = false;
			return prepararConsulta();

		} catch (Exception e) {

			mensajeError("PersistenceErrorOccured");
			return null;

		}

	}

	public String eliminarCuenta() {

		try {

			companiaServicio.eliminarCuenta(itemCuenta);

			mensajeInfo("CuentaDeleted");
			return prepararConsulta();

		} catch (Exception e) {

			mensajeError("PersistenceErrorOccured");
			return null;

		}

	}

}
