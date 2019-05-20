package com.guandera.core.client.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import com.guandera.core.client.service.ClienteContactoService;
import com.guandera.core.server.service.ClienteContactoServiceImpl;
import com.guandera.core.shared.model.Cliente;
import com.guandera.core.shared.model.ClienteCargo;
import com.guandera.core.shared.model.ClienteContacto;
import com.guandera.core.shared.model.TipoIdentificacion;

@ManagedBean(name = "clienteContactoMB")
@SessionScoped
public class ClienteContactoManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private ClienteContactoService clienteContactoServicio;
	private ClienteContacto itemSeleccionado;
	private List<ClienteContacto> listaItems = new ArrayList<ClienteContacto>();
	private boolean listing = false;

	private Long tipoidentificacionid;
	private List<SelectItem> tipoIdentificacionItems;
	private boolean listingTipoIdentificacion = false;

	private Long clienteid;
	private List<SelectItem> clienteItems;
	private boolean listingCliente = false;

	private Long clienteCargoid;
	private List<SelectItem> clienteCargoItems;
	private boolean listingClienteCargo = false;

	public ClienteContactoManagedBean() {
		clienteContactoServicio = new ClienteContactoServiceImpl();

		inicializar();
	}

	public String actualizar() {

		try {
			TipoIdentificacion tipoid = new TipoIdentificacion();

			tipoid.setTipoidentificacionid(tipoidentificacionid);
			Cliente cliente = new Cliente();

			cliente.setClienteid(clienteid);
			itemSeleccionado.setCliente(cliente);
			ClienteCargo clientecargo = new ClienteCargo();

			clientecargo.setCargoid(clienteCargoid);
			itemSeleccionado.setCargo(clientecargo);
			String nombreApellido = itemSeleccionado.getPrimernombre().trim() + ' '
					+ itemSeleccionado.getSegundonombre().trim() + ' ' + itemSeleccionado.getPrimerapellido().trim()
					+ ' ' + itemSeleccionado.getSegundoapellido().trim();

			String apellidoNombre = itemSeleccionado.getPrimerapellido().trim() + ' '
					+ itemSeleccionado.getSegundoapellido().trim() + ' ' + itemSeleccionado.getPrimernombre().trim()
					+ ' ' + itemSeleccionado.getSegundonombre().trim();

			itemSeleccionado.setApellidoNombre(apellidoNombre);
			itemSeleccionado.setNombreApellido(nombreApellido);

			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());
			itemSeleccionado.setTipoIdentificacion(tipoid);
			clienteContactoServicio.actualizar(itemSeleccionado);
			mensajeInfo("ClienteContactoUpdated");
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
			boolean existe = clienteContactoServicio.existeClienteContacto(itemSeleccionado.getNumeroidentificacion());
			System.out.println("existe ..." + existe);

			if (existe == false) {
				Cliente cliente = new Cliente();

				cliente.setClienteid(clienteid);
				itemSeleccionado.setCliente(cliente);
				ClienteCargo clientecargo = new ClienteCargo();

				clientecargo.setCargoid(clienteCargoid);
				itemSeleccionado.setCargo(clientecargo);
				TipoIdentificacion tipoid = new TipoIdentificacion();

				tipoid.setTipoidentificacionid(tipoidentificacionid);

				String nombreApellido = itemSeleccionado.getPrimernombre().trim() + ' '
						+ itemSeleccionado.getSegundonombre().trim() + ' ' + itemSeleccionado.getPrimerapellido().trim()
						+ ' ' + itemSeleccionado.getSegundoapellido().trim();

				String apellidoNombre = itemSeleccionado.getPrimerapellido().trim() + ' '
						+ itemSeleccionado.getSegundoapellido().trim() + ' ' + itemSeleccionado.getPrimernombre().trim()
						+ ' ' + itemSeleccionado.getSegundonombre().trim();

				itemSeleccionado.setApellidoNombre(apellidoNombre);
				itemSeleccionado.setNombreApellido(nombreApellido);

				itemSeleccionado.setTipoIdentificacion(tipoid);
				itemSeleccionado.setUsuarioCreacion(usuario());
				itemSeleccionado.setFechaCreacion(new Date());
				itemSeleccionado.setUsuarioModificacion(usuario());
				itemSeleccionado.setFechaModificacion(new Date());
				clienteContactoServicio.crear(itemSeleccionado);
				inicializar();
				verificarLista();
				mensajeInfo("ClienteContactoCreated");

			} else {
				error("N�mero de identificaci�n ya se encuentra registrado!");
			}

			return prepararLista();
		} catch (Exception e) {

			System.out.println("Error: " + e.getMessage());
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String eliminar() {

		clienteContactoServicio.eliminar(itemSeleccionado);
		listing = false;
		return prepararLista();
	}

	public ClienteContacto getItemSeleccionado() {
		return itemSeleccionado;
	}

	public List<ClienteContacto> getListaItems() {

		if (!listing) {
			listing = true;
			listaItems = clienteContactoServicio.consultarTodos();
		}
		return listaItems;
	}

	public ClienteContactoService getClienteContactoServicio() {
		return clienteContactoServicio;
	}

	private void inicializar() {

		itemSeleccionado = new ClienteContacto();

	}

	public boolean isListing() {
		return listing;
	}

	public void listarClienteContacto() {
		setListing(true);
		listaItems = clienteContactoServicio.consultarTodos();
	}

	public String prepararConsulta() {
		return "Detalle";
	}

	public String prepararCreacion() {
		itemSeleccionado = new ClienteContacto();
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

	public void setItemSeleccionado(ClienteContacto itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public void setListaItems(List<ClienteContacto> listaItems) {
		this.listaItems = listaItems;
	}

	public void setListing(boolean listing) {
		this.listing = listing;
	}

	public void setClienteContactoServicio(ClienteContactoService clienteContactoServicio) {
		this.clienteContactoServicio = clienteContactoServicio;
	}

	private void verificarLista() {
		if (listing) {
			this.listaItems = clienteContactoServicio.consultarTodos();
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
			List<TipoIdentificacion> tipoIdList = clienteContactoServicio.consultarTiposIdentificacion();

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

	public Long getClienteid() {
		return clienteid;
	}

	public void setClienteid(Long clienteid) {
		this.clienteid = clienteid;
	}

	public List<SelectItem> getClienteItems() {

		if (!listingCliente) {
			listingCliente = true;
			clienteItems = new ArrayList<SelectItem>();
			List<Cliente> clienteList = clienteContactoServicio.consultarClientes();

			for (Cliente cliente : clienteList) {
				clienteItems.add(new SelectItem(cliente.getClienteid(),
						cliente.getClienteidentificacion() + " / " + cliente.getClientenombre()));

			}
		}

		return clienteItems;
	}

	public void setClienteItems(List<SelectItem> clienteItems) {
		this.clienteItems = clienteItems;
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
			List<ClienteCargo> clienteCargoList = clienteContactoServicio.consultarCargos();
			System.out.print(clienteCargoList);
			for (ClienteCargo clienteCargo : clienteCargoList) {
				clienteCargoItems.add(new SelectItem(clienteCargo.getCargoid(), clienteCargo.getNombre()));

			}
		}

		return clienteCargoItems;
	}

	public void setClienteCargoItems(List<SelectItem> clienteCargoItems) {
		this.clienteCargoItems = clienteCargoItems;
	}

}
