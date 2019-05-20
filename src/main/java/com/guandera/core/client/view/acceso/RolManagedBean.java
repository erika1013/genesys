package com.guandera.core.client.view.acceso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import com.guandera.core.client.service.acceso.RolService;
import com.guandera.core.client.view.BaseManagedBean;
import com.guandera.core.server.service.acceso.RolServiceImpl;
import com.guandera.core.shared.model.acceso.Acceso;
import com.guandera.core.shared.model.acceso.Menu;
import com.guandera.core.shared.model.acceso.Rol;

@ManagedBean(name = "rolMB")
@SessionScoped
public class RolManagedBean extends BaseManagedBean implements Serializable {
	private static final long serialVersionUID = 10L;
	private RolService rolServicio;
	private Rol itemSeleccionado;
	private boolean listing = false;
	private List<Rol> listaRol;
	private List<Menu> listaMenu;
	private List<SelectItem> menuItems;
	private Long menuid;

	private List<Acceso> listaAcceso;
	private Acceso accesoSeleccionado;

	private boolean autorizar = false;

	public RolManagedBean() {

		rolServicio = new RolServiceImpl();
		inicializar();
	}

	public String actualizar() {

		try {

			itemSeleccionado.setModificacionusuario(usuarioSessionId());
			itemSeleccionado.setModificacionfecha(new Date());
			rolServicio.actualizar(itemSeleccionado);
			mensajeInfo("RolUpdated");
			inicializar();
			verificarLista();
			return prepararLista();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public void autorizarMenu() {

		if (autorizar == false) {

			autorizar = true;
		} else {
			autorizar = false;
		}
	}

	public String crear() {

		try {

			// itemSeleccionado.setCreacionusuario(usuarioSessionId());

			itemSeleccionado.setCreacionfecha(new Date());

			// itemSeleccionado.setModificacionusuario(usuarioSessionId());

			itemSeleccionado.setModificacionfecha(new Date());

			rolServicio.crear(itemSeleccionado);

			mensajeInfo("RolCreated");

			inicializar();

			verificarLista();

			return prepararLista();

		} catch (Exception e) {

			e.printStackTrace();

			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public void crearAcceso() {

		try {
			Acceso acceso = new Acceso();
			Menu menuu = new Menu();
			menuu.setMenuid(menuid);

			acceso.setAccesoid(rolServicio.siguienteAcceso());
			acceso.setRol(itemSeleccionado);
			acceso.setMenu(menuu);

			acceso.setCreacionusuario(usuarioSessionId());
			acceso.setCreacionfecha(new Date());
			acceso.setModificacionusuario(usuarioSessionId());
			acceso.setModificacionfecha(new Date());

			rolServicio.crearAcceso(acceso);
			info("Acceso Addicionado");
			autorizar = false;

		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");

		}

	}

	public String eliminar() {

		rolServicio.eliminar(itemSeleccionado);
		return prepararLista();
	}

	public String eliminarAcceso() {

		try {
			rolServicio.eliminarAcceso(accesoSeleccionado);
			info("Acceso Eliminado: DEBE Ingresar Nuevamente para Ver reflejados los cambios");

		} catch (Exception e) {

			e.printStackTrace();
			mensajeError("PersistenceErrorOccured");

		}

		return prepararConsulta();
	}

	public Acceso getAccesoSeleccionado() {
		return accesoSeleccionado;
	}

	public Rol getItemSeleccionado() {
		return itemSeleccionado;
	}

	public List<Acceso> getListaAcceso() {

		listaAcceso = rolServicio.consultarAccesosPorRol(itemSeleccionado.getRolid());
		return listaAcceso;
	}

	public List<Menu> getListaMenu() {
		listaMenu = rolServicio.consultarMenuPorRol(itemSeleccionado.getRolid());
		return listaMenu;
	}

	public List<Rol> getListaRol() {

		listaRol = rolServicio.consultarTodos();
		return listaRol;
	}

	public Long getMenuid() {
		return menuid;
	}

	public List<SelectItem> getMenuItems() {

		if (menuItems == null) {
			menuItems = new ArrayList<SelectItem>();
			List<Menu> menuList = rolServicio.consultarMenus();

			for (Menu menu1 : menuList) {
				menuItems.add(new SelectItem(menu1.getMenuid(), menu1.getMenunombre()));

			}
		}

		return menuItems;
	}

	public RolService getRolServicio() {
		return rolServicio;
	}

	private void inicializar() {
		setItemSeleccionado(new Rol());

	}

	public boolean isAutorizar() {
		return autorizar;
	}

	public boolean isListing() {
		return listing;
	}

	public void listarRol() {
		setListing(true);
		listaRol = rolServicio.consultarTodos();
	}

	public String prepararConsulta() {
		autorizar = false;
		return "Detalle";
	}

	public String prepararCreacion() {
		itemSeleccionado = new Rol();
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

	public void setAccesoSeleccionado(Acceso accesoSeleccionado) {
		this.accesoSeleccionado = accesoSeleccionado;
	}

	public void setItemSeleccionado(Rol itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public void setListaAcceso(List<Acceso> listaAcceso) {
		this.listaAcceso = listaAcceso;
	}

	public void setListaMenu(List<Menu> listaMenu) {
		this.listaMenu = listaMenu;
	}

	public void setListaRol(List<Rol> listaRol) {
		this.listaRol = listaRol;
	}

	public void setListing(boolean listing) {
		this.listing = listing;
	}

	public void setMenuid(Long menuid) {
		this.menuid = menuid;
	}

	public void setMenuItems(List<SelectItem> menuItems) {
		this.menuItems = menuItems;
	}

	public void setRolServicio(RolService rolServicio) {
		this.rolServicio = rolServicio;
	}

	private void verificarLista() {
		if (listing) {
			this.listaRol = rolServicio.consultarTodos();
		}
	}
}
