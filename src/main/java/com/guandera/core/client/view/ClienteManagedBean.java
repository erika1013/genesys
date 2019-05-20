package com.guandera.core.client.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import com.guandera.core.client.service.ClienteService;
import com.guandera.core.server.service.ClienteServiceImpl;
import com.guandera.core.shared.model.Cliente;
import com.guandera.core.shared.model.ClienteCargo;
import com.guandera.core.shared.model.ClienteContacto;
import com.guandera.core.shared.model.TipoIdentificacion;

@ManagedBean(name = "clienteMB")
@SessionScoped
public class ClienteManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private ClienteService clienteServicio;
	private Cliente itemSeleccionado;
	private List<Cliente> listaItems = new ArrayList<Cliente>();
	private boolean listing = false;

	private Long tipoidentificacionid;
	private List<SelectItem> tipoIdentificacionItems;
	private boolean listingTipoIdentificacion = false;

	private ClienteContacto itemClienteContacto;
	private List<ClienteContacto> listaClienteContactoItems = new ArrayList<ClienteContacto>();

	private Long clienteCargoid;
	private List<SelectItem> clienteCargoItems;
	private boolean listingClienteCargo = false;

	private ClienteCargo itemCargo;
	private List<ClienteCargo> listaClienteCargoItems = new ArrayList<ClienteCargo>();

	public ClienteManagedBean() {
		clienteServicio = new ClienteServiceImpl();

		inicializar();
	}

	public String actualizar() {

		try {
			TipoIdentificacion tipoid = new TipoIdentificacion();

			tipoid.setTipoidentificacionid(tipoidentificacionid);
			itemSeleccionado.setTipoIdentificacion(tipoid);
			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			clienteServicio.actualizar(itemSeleccionado);
			mensajeInfo("ClienteUpdated");
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

			tipoid.setTipoidentificacionid(tipoidentificacionid);
			itemSeleccionado.setTipoIdentificacion(tipoid);
			itemSeleccionado.setUsuarioCreacion(usuario());
			itemSeleccionado.setFechaCreacion(new Date());
			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());
			clienteServicio.crear(itemSeleccionado);
			inicializar();
			verificarLista();
			mensajeInfo("ClienteCreated");

			return prepararLista();
		} catch (Exception e) {

			System.out.println("Error: " + e.getMessage());
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String eliminar() {

		clienteServicio.eliminar(itemSeleccionado);
		listing = false;
		return prepararLista();
	}

	public Cliente getItemSeleccionado() {
		return itemSeleccionado;
	}

	public List<Cliente> getListaItems() {

		if (!listing) {
			listing = true;
			listaItems = clienteServicio.consultarTodos();
		}
		return listaItems;
	}

	public ClienteService getClienteServicio() {
		return clienteServicio;
	}

	private void inicializar() {

		itemSeleccionado = new Cliente();

	}

	public boolean isListing() {
		return listing;
	}

	public void listarCliente() {
		setListing(true);
		listaItems = clienteServicio.consultarTodos();
	}

	public String prepararConsulta() {
		return "Detalle";
	}

	public String prepararCreacion() {
		itemSeleccionado = new Cliente();
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

	public void setItemSeleccionado(Cliente itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public void setListaItems(List<Cliente> listaItems) {
		this.listaItems = listaItems;
	}

	public void setListing(boolean listing) {
		this.listing = listing;
	}

	public void setClienteServicio(ClienteService clienteServicio) {
		this.clienteServicio = clienteServicio;
	}

	private void verificarLista() {
		if (listing) {
			this.listaItems = clienteServicio.consultarTodos();
		}
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
			List<TipoIdentificacion> tipoIdList = clienteServicio.consultarTiposIdentificacion();

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

	// Cliente Contacto

	public String actualizarClienteContacto() {

		try {

			TipoIdentificacion tipoid = new TipoIdentificacion();

			tipoid.setTipoidentificacionid(tipoidentificacionid);

			ClienteCargo clientecargo = new ClienteCargo();

			clientecargo.setCargoid(clienteCargoid);
			itemClienteContacto.setCargo(clientecargo);
			String nombreApellido = itemClienteContacto.getPrimernombre().trim() + ' '
					+ itemClienteContacto.getSegundonombre().trim() + ' '
					+ itemClienteContacto.getPrimerapellido().trim() + ' '
					+ itemClienteContacto.getSegundoapellido().trim();

			String apellidoNombre = itemClienteContacto.getPrimerapellido().trim() + ' '
					+ itemClienteContacto.getSegundoapellido().trim() + ' '
					+ itemClienteContacto.getPrimernombre().trim() + ' '
					+ itemClienteContacto.getSegundonombre().trim();

			itemClienteContacto.setApellidoNombre(apellidoNombre);
			itemClienteContacto.setNombreApellido(nombreApellido);
			itemClienteContacto.setCliente(itemSeleccionado);
			itemClienteContacto.setUsuarioModificacion(usuario());
			itemClienteContacto.setFechaModificacion(new Date());
			itemClienteContacto.setTipoIdentificacion(tipoid);
			clienteServicio.actualizarClienteContacto(itemClienteContacto);
			mensajeInfo("ClienteContactoUpdated");
			return prepararConsulta();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String crearClienteContacto() {

		try {

			itemClienteContacto.setCliente(itemSeleccionado);

			ClienteCargo clientecargo = new ClienteCargo();
			clientecargo.setCargoid(clienteCargoid);
			itemClienteContacto.setCargo(clientecargo);

			String nombreApellido = itemClienteContacto.getPrimernombre().trim() + ' '
					+ itemClienteContacto.getSegundonombre().trim() + ' '
					+ itemClienteContacto.getPrimerapellido().trim() + ' '
					+ itemClienteContacto.getSegundoapellido().trim();

			String apellidoNombre = itemClienteContacto.getPrimerapellido().trim() + ' '
					+ itemClienteContacto.getSegundoapellido().trim() + ' '
					+ itemClienteContacto.getPrimernombre().trim() + ' '
					+ itemClienteContacto.getSegundonombre().trim();

			itemClienteContacto.setApellidoNombre(apellidoNombre);
			itemClienteContacto.setNombreApellido(nombreApellido);

			itemClienteContacto.setUsuarioCreacion(usuario());
			itemClienteContacto.setFechaCreacion(new Date());
			itemClienteContacto.setUsuarioModificacion(usuario());
			itemClienteContacto.setFechaModificacion(new Date());
			clienteServicio.crearClienteContacto(itemClienteContacto);
			mensajeInfo("ClienteContactoCreated");
			return prepararConsulta();

		} catch (Exception e) {

			System.out.println("Error: " + e.getMessage());
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String eliminarClienteContacto() {

		clienteServicio.eliminarClienteContacto(itemClienteContacto);
		return prepararConsulta();
	}

	public ClienteContacto getItemClienteContacto() {
		return itemClienteContacto;
	}

	public List<ClienteContacto> getListaClienteContactoItems() {

		listaClienteContactoItems = clienteServicio.consultarClienteContactos(itemSeleccionado.getClienteid());

		return listaClienteContactoItems;
	}

	public ClienteCargo getItemCargo() {
		return itemCargo;
	}

	public void setItemCargo(ClienteCargo itemCargo) {
		this.itemCargo = itemCargo;
	}

	public List<ClienteCargo> getListaClienteCargoItems() {
		return listaClienteCargoItems = clienteServicio.consultarClienteCargos(itemSeleccionado.getClienteid());
	}

	public void setListaClienteCargoItems(List<ClienteCargo> listaClienteCargoItems) {
		this.listaClienteCargoItems = listaClienteCargoItems;
	}

	public String prepararConsultaClienteContacto() {
		return "ClienteContactoDetalle";
	}

	public String prepararCreacionClienteContacto() {
		itemClienteContacto = new ClienteContacto();
		return "ClienteContactoCrear";
	}

	public String prepararEdicionClienteContacto() {

		tipoidentificacionid = itemClienteContacto.getTipoIdentificacion().getTipoidentificacionid();
		clienteCargoid = itemClienteContacto.getCargo().getCargoid();
		return "ClienteContactoEditar";
	}

	public String prepararListaClienteContacto() {
		return "ClienteContactoLista";
	}

	public void setItemClienteContacto(ClienteContacto itemClienteContacto) {
		this.itemClienteContacto = itemClienteContacto;
	}

	public void setListaClienteContactoItems(List<ClienteContacto> listaClienteContactoItems) {
		this.listaClienteContactoItems = listaClienteContactoItems;
	}

	public Long getClienteCargoid() {
		return clienteCargoid;
	}

	public void setClienteCargoid(Long clienteCargoid) {
		this.clienteCargoid = clienteCargoid;
	}

	public List<SelectItem> getClienteCargoItems() {

		if (!listingClienteCargo) {
			listingClienteCargo = true;
			clienteCargoItems = new ArrayList<SelectItem>();
			List<ClienteCargo> clienteCargoList = clienteServicio
					.consultarClienteCargos(itemSeleccionado.getClienteid());
			for (ClienteCargo clienteCargo : clienteCargoList) {
				clienteCargoItems.add(new SelectItem(clienteCargo.getCargoid(), clienteCargo.getNombre()));

			}
		}

		return clienteCargoItems;
	}

	public void setClienteCargoItems(List<SelectItem> clienteCargoItems) {
		this.clienteCargoItems = clienteCargoItems;
	}

	// -----

	public String crearCargo() {

		try {

			itemCargo.setUsuarioCreacion(usuario());
			itemCargo.setFechaCreacion(new Date());
			itemCargo.setUsuarioModificacion(usuario());
			itemCargo.setFechaModificacion(new Date());
			itemCargo.setCliente(itemSeleccionado);

			clienteServicio.crearCargo(itemCargo);
			mensajeInfo("ClienteCargoCreated");
			inicializar();
			verificarLista();
			return prepararLista();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String actualizarCargo() {

		try {
			itemCargo.setUsuarioModificacion(usuario());
			itemCargo.setFechaModificacion(new Date());

			clienteServicio.actualizarCargo(itemCargo);
			mensajeInfo("ClienteCargoUpdated");
			inicializar();
			verificarLista();
			return prepararLista();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String eliminarCargo() {

		clienteServicio.eliminarCargo(itemCargo);
		// listingCargo = false;
		return prepararLista();
	}

	public String prepararConsultaCargo() {
		return "CargoDetalle";
	}

	public String prepararCreacionCargo() {
		itemCargo = new ClienteCargo();
		return "CargoCrear";
	}

	public String prepararEdicionCargo() {

		tipoidentificacionid = itemClienteContacto.getTipoIdentificacion().getTipoidentificacionid();
		clienteCargoid = itemClienteContacto.getCargo().getCargoid();
		return "CargoEditar";
	}

	public String prepararListaCargo() {
		return "CargoLista";
	}

}
