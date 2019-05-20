package com.guandera.core.client.view.acceso;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import com.guandera.core.client.service.acceso.LoginService;
import com.guandera.core.client.service.acceso.UsuarioService;
import com.guandera.core.client.view.BaseManagedBean;
import com.guandera.core.server.service.acceso.LoginServiceImpl;
import com.guandera.core.server.service.acceso.UsuarioServiceImpl;
import com.guandera.core.shared.model.acceso.Rol;
import com.guandera.core.shared.model.acceso.Usuario;

@ManagedBean(name = "usuarioMB")
@SessionScoped
public class UsuarioManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private UsuarioService usuarioServicio;

	private LoginService loginService;
	private Usuario itemSeleccionado;
	private boolean listing = false;
	private List<Usuario> listaUsuario;
	private List<SelectItem> rolItems;
	private Long rolid;
	private String usuarioclaveOriginal;

	private String usuarioclaveNueva;

	private boolean autorizar = false;

	public UsuarioManagedBean() {
		usuarioServicio = new UsuarioServiceImpl();
		loginService = new LoginServiceImpl();
		inicializar();
	}

	public static String md5Encode(String texto) {

		String output = "";
		try {
			byte[] defaultBytes = texto.getBytes();
			MessageDigest algorithm = MessageDigest.getInstance("MD5");
			algorithm.reset();
			algorithm.update(defaultBytes);
			byte messageDigest[] = algorithm.digest();

			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < messageDigest.length; i++) {
				hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
			}

			output = hexString + "";

		} catch (NoSuchAlgorithmException noSuchAlgorithmException) {

		}
		return output;
	}

	public String actualizar() {

		try {
			Rol rolu = new Rol();
			rolu.setRolid(rolid);
			itemSeleccionado.setRol(rolu);

			itemSeleccionado.setModificacionusuario(usuarioSessionId());
			itemSeleccionado.setModificacionfecha(new Date());
			usuarioServicio.actualizar(itemSeleccionado);
			mensajeInfo("UsuarioUpdated");
			inicializar();
			verificarLista();
			return prepararLista();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String actualizarClave() {

		try {

			usuarioclaveNueva = "Z8A." + usuarioclaveNueva;

			String md5Nuevo = md5Encode(usuarioclaveNueva);

			itemSeleccionado.setUsuarioclave(md5Nuevo);

			itemSeleccionado.setModificacionusuario(usuarioSessionId());
			itemSeleccionado.setModificacionfecha(new Date());
			usuarioServicio.actualizar(itemSeleccionado);
			mensajeInfo("UsuarioUpdated");
			inicializar();
			verificarLista();
			return prepararLista();

		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String actualizarClaveUsuario() {

		try {

			Rol rolu = new Rol();
			rolu.setRolid(rolid);
			itemSeleccionado.setRol(rolu);

			usuarioclaveOriginal = "Z8A." + usuarioclaveOriginal;

			itemSeleccionado = loginService.CargarUsuario(usuarioSessionUsuario());

			String md5 = md5Encode(usuarioclaveOriginal);

			if (md5.equals(itemSeleccionado.getUsuarioclave()) == true) {

				usuarioclaveNueva = "Z8A." + usuarioclaveNueva;

				String md5Nuevo = md5Encode(usuarioclaveNueva);

				itemSeleccionado.setUsuarioclave(md5Nuevo);

				itemSeleccionado.setModificacionusuario(usuarioSessionId());
				itemSeleccionado.setModificacionfecha(new Date());
				usuarioServicio.actualizar(itemSeleccionado);
				mensajeInfo("UsuarioUpdated");

				return "/index";

			} else {

				mensajeError("Usuario_ClaveIncorrecta");
				return null;
			}

		} catch (Exception e) {

			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public void autorizarSubproyecto() {

		if (autorizar == false) {
			autorizar = true;
		} else {
			autorizar = false;
		}
	}

	public String crear() {

		try {
			Rol rolu = new Rol();
			rolu.setRolid(rolid);

			itemSeleccionado.setRol(rolu);
			String md5 = md5Encode("Z8A." + itemSeleccionado.getUsuarioclave());
			itemSeleccionado.setUsuarioclave(md5);

			itemSeleccionado.setCreacionusuario(usuarioSessionId());
			itemSeleccionado.setCreacionfecha(new Date());
			itemSeleccionado.setModificacionusuario(usuarioSessionId());
			itemSeleccionado.setModificacionfecha(new Date());

			usuarioServicio.crear(itemSeleccionado);
			mensajeInfo("UsuarioCreated");
			inicializar();
			verificarLista();
			return prepararLista();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String eliminar() {

		try {
			usuarioServicio.eliminar(itemSeleccionado);
			mensajeInfo("UsuarioDeleted");
			return prepararLista();

		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}
	}

	public Usuario getItemSeleccionado() {
		return itemSeleccionado;
	}

	public List<Usuario> getListaUsuario() {

		listaUsuario = usuarioServicio.consultarTodos();
		return listaUsuario;
	}

	public Long getRolid() {
		return rolid;
	}

	public List<SelectItem> getRolItems() {

		rolItems = new ArrayList<SelectItem>();
		List<Rol> rolList = usuarioServicio.consultarRoles();

		for (Rol rol1 : rolList) {
			rolItems.add(new SelectItem(rol1.getRolid(), rol1.getRolnombre()));

		}

		return rolItems;
	}

	public String getUsuarioclaveNueva() {
		return usuarioclaveNueva;
	}

	public String getUsuarioclaveOriginal() {
		return usuarioclaveOriginal;
	}

	public UsuarioService getUsuarioServicio() {
		return usuarioServicio;
	}

	private void inicializar() {
		setItemSeleccionado(new Usuario());
	}

	public boolean isAutorizar() {
		return autorizar;
	}

	public boolean isListing() {
		return listing;
	}

	public void listarUsuario() {
		setListing(true);
		listaUsuario = usuarioServicio.consultarTodos();
	}

	public String prepararConsulta() {
		return "Detalle";
	}

	public String prepararCreacion() {
		itemSeleccionado = new Usuario();
		rolid = (long) 0;

		return "Crear";
	}

	public String prepararEdicion() {
		rolid = itemSeleccionado.getRol().getRolid();
		return "Editar";
	}

	public String prepararCambioClave() {

		return "EditarClave";
	}

	public String prepararLista() {
		return "Lista";
	}

	@PostConstruct
	public void prerender() {
		inicializar();

	}

	public void setAutorizar(boolean autorizar) {
		this.autorizar = autorizar;
	}

	public void setItemSeleccionado(Usuario itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	public void setListing(boolean listing) {
		this.listing = listing;
	}

	public void setRolid(Long rolid) {
		this.rolid = rolid;
	}

	public void setRolItems(List<SelectItem> rolItems) {
		this.rolItems = rolItems;
	}

	public void setUsuarioclaveNueva(String usuarioclaveNueva) {
		this.usuarioclaveNueva = usuarioclaveNueva;
	}

	public void setUsuarioclaveOriginal(String usuarioclaveOriginal) {
		this.usuarioclaveOriginal = usuarioclaveOriginal;
	}

	public void setUsuarioServicio(UsuarioService usuarioServicio) {
		this.usuarioServicio = usuarioServicio;
	}

	private void verificarLista() {
		if (listing) {
			this.listaUsuario = usuarioServicio.consultarTodos();
		}
	}

}
