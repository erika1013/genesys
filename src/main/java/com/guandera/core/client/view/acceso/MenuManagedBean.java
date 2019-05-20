package com.guandera.core.client.view.acceso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import com.guandera.core.client.service.acceso.MenuService;
import com.guandera.core.client.view.BaseManagedBean;
import com.guandera.core.server.service.acceso.MenuServiceImpl;
import com.guandera.core.shared.model.acceso.Menu;
import com.guandera.core.shared.model.acceso.Opcion;
import com.guandera.core.shared.model.acceso.Submenu;

@ManagedBean(name = "menuMB")
@SessionScoped
public class MenuManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private MenuService menuServicio;
	private Menu itemSeleccionado;
	private Submenu submenuSeleccionado;
	private Submenu submenu;
	private boolean listing = false;
	private List<Menu> listaMenu;
	private List<Submenu> listaSubmenu;
	private Long opcionid;
	private short submenuorden;
	private boolean agregar = false;
	private boolean actualizaropcion = false;
	private List<SelectItem> opcionItems;

	public MenuManagedBean() {

		menuServicio = new MenuServiceImpl();
		inicializar();
	}

	public String actualizar() {

		try {

			itemSeleccionado.setModificacionusuario(usuarioSessionId());
			itemSeleccionado.setModificacionfecha(new Date());

			menuServicio.actualizar(itemSeleccionado);
			mensajeInfo("MenuUpdated");
			inicializar();
			verificarLista();
			return prepararLista();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public void actualizarOpcion() {

		if (actualizaropcion == false) {
			actualizaropcion = true;
		} else {
			actualizaropcion = false;
		}
	}

	public void actualizarSubmenu() {

		try {

			submenu.setModificacionusuario(usuarioSessionId());
			submenu.setModificacionfecha(new Date());
			menuServicio.actualizarSubmenu(submenu);

			info("Opcion Actualizada.");
			actualizaropcion = false;

		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");

		}

	}

	public void agregarOpcion() {

		submenu = new Submenu();
		opcionid = 0L;

		if (agregar == false) {
			agregar = true;
		} else {
			agregar = false;
		}

	}

	public String crear() {

		try {

			itemSeleccionado.setCreacionusuario(usuarioSessionId());
			itemSeleccionado.setCreacionfecha(new Date());
			itemSeleccionado.setModificacionusuario(usuarioSessionId());
			itemSeleccionado.setModificacionfecha(new Date());

			menuServicio.crear(itemSeleccionado);
			mensajeInfo("MenuCreated");
			inicializar();
			verificarLista();
			return prepararLista();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public void crearSubmenu() {

		try {

			submenu = new Submenu();
			Opcion opcionu = new Opcion();
			opcionu.setOpcionid(opcionid);

			// submenu.setSubmenuid(menuServicio.siguienteSubmenu());

			submenu.setMenu(itemSeleccionado);

			submenu.setOpcion(opcionu);

			submenu.setSubmenuorden(submenuorden);

			submenu.setCreacionusuario(usuarioSessionId());
			submenu.setCreacionfecha(new Date());
			submenu.setModificacionusuario(usuarioSessionId());
			submenu.setModificacionfecha(new Date());

			menuServicio.crearSubmenu(submenu);

			info("Opci�n Adicionada: DEBE Ingresar Nuevamente para Ver reflejados los cambios");
			agregar = false;

		} catch (Exception e) {

			mensajeError("PersistenceErrorOccured");

		}

	}

	public String eliminar() {
		try {
			menuServicio.eliminar(itemSeleccionado);
			mensajeInfo("MenuDeleted");
			return prepararLista();
		} catch (Exception e) {

			e.printStackTrace();
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public void eliminarSubmenu() {

		try {
			menuServicio.eliminarSubmenu(submenuSeleccionado);
			info("Opcion Retirada del Menu: DEBE Ingresar Nuevamente para Ver reflejados los cambios");

		} catch (Exception e) {

			e.printStackTrace();
			mensajeError("PersistenceErrorOccured");

		}

	}

	public Menu getItemSeleccionado() {
		return itemSeleccionado;
	}

	public List<Menu> getListaMenu() {

		listaMenu = menuServicio.consultarTodos();
		return listaMenu;
	}

	public List<Submenu> getListaSubmenu() {
		listaSubmenu = menuServicio.consultarSubmenuPorMenu(itemSeleccionado.getMenuid());

		return listaSubmenu;
	}

	public MenuService getMenuServicio() {
		return menuServicio;
	}

	public Submenu getSubmenu() {
		return submenu;
	}

	public short getSubmenuorden() {
		return submenuorden;
	}

	public Submenu getSubmenuSeleccionado() {
		return submenuSeleccionado;
	}

	public Long getOpcionid() {
		return opcionid;
	}

	public List<SelectItem> getOpcionItems() {
		if (opcionItems == null) {
			opcionItems = new ArrayList<SelectItem>();
			List<Opcion> opcionList = menuServicio.consultarOpciones();

			for (Opcion opcion1 : opcionList) {
				opcionItems.add(new SelectItem(opcion1.getOpcionid(), opcion1.getOpcionnombre()));

			}
		}

		return opcionItems;
	}

	private void inicializar() {
		setItemSeleccionado(new Menu());

	}

	public boolean isActualizaropcion() {
		return actualizaropcion;
	}

	public boolean isAgregar() {
		return agregar;
	}

	public boolean isListing() {
		return listing;
	}

	public void listarMenu() {
		setListing(true);
		listaMenu = menuServicio.consultarTodos();
	}

	public String prepararConsulta() {
		agregar = false;
		return "Detalle";
	}

	public String prepararCreacion() {
		itemSeleccionado = new Menu();
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

	public void setActualizaropcion(boolean actualizaropcion) {
		this.actualizaropcion = actualizaropcion;
	}

	public void setAgregar(boolean agregar) {
		this.agregar = agregar;
	}

	public void setItemSeleccionado(Menu itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public void setListaMenu(List<Menu> listaMenu) {
		this.listaMenu = listaMenu;
	}

	public void setListaSubmenu(List<Submenu> listaSubmenu) {
		this.listaSubmenu = listaSubmenu;
	}

	public void setListing(boolean listing) {
		this.listing = listing;
	}

	public void setMenuServicio(MenuService menuServicio) {
		this.menuServicio = menuServicio;
	}

	public void setSubmenu(Submenu submenu) {
		this.submenu = submenu;

	}

	public void setSubmenuorden(short submenuorden) {
		this.submenuorden = submenuorden;
	}

	public void setSubmenuSeleccionado(Submenu submenuSeleccionado) {
		this.submenuSeleccionado = submenuSeleccionado;
	}

	public void setOpcionid(Long opcionid) {
		this.opcionid = opcionid;
	}

	public void setOpcionItems(List<SelectItem> opcionItems) {
		this.opcionItems = opcionItems;
	}

	private void verificarLista() {
		if (listing) {
			this.listaMenu = menuServicio.consultarTodos();
		}
	}
}
