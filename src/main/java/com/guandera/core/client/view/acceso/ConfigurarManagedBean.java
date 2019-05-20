package com.guandera.core.client.view.acceso;

import java.io.Serializable;

import java.util.Date;
import java.util.StringTokenizer;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.guandera.core.client.service.acceso.ConfigurarService;
import com.guandera.core.client.view.BaseManagedBean;
import com.guandera.core.server.service.acceso.ConfigurarServiceImpl;

import com.guandera.core.shared.model.Convenio;

import com.guandera.core.shared.model.Sede;
import com.guandera.core.shared.model.Servicio;

import com.guandera.core.shared.model.TipoServicio;
import com.guandera.core.shared.model.acceso.Acceso;
import com.guandera.core.shared.model.acceso.Menu;
import com.guandera.core.shared.model.acceso.Opcion;
import com.guandera.core.shared.model.acceso.Rol;
import com.guandera.core.shared.model.acceso.Submenu;

@ManagedBean(name = "configurarMB")
@SessionScoped
public class ConfigurarManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private ConfigurarService configurarServicio;

	private String textoCSV;

	public String getTextoCSV() {
		return textoCSV;
	}

	public void setTextoCSV(String textoCSV) {
		this.textoCSV = textoCSV;
	}

	public ConfigurarManagedBean() {
		configurarServicio = new ConfigurarServiceImpl();

	}

	public String actualizarTipoServicio() {

		try {

			configurarServicio.actualizarTipoServicio();

			return configurar();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String configurar() {

		textoCSV = "";
		return "Configurar";
	}

	public String actualizarNombreEmpleado() {

		try {

			configurarServicio.actualizarEmpleadoNombres();

			return configurar();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String actualizarCodigoSede() {

		try {

			configurarServicio.actualizarCodigoSede();

			return configurar();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String actualizarServicio() {

		try {

			configurarServicio.actualizarServicio();

			return configurar();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String cargarRoles() {

		try {

			StringTokenizer tokens = new StringTokenizer(textoCSV, "\n");
			while (tokens.hasMoreTokens()) {

				String linea = tokens.nextToken();
				String[] campos = linea.split(";");

				Rol rol = new Rol();

				rol.setRolid(Long.parseLong(campos[0]));
				rol.setRolnombre(campos[1]);

				rol.setCreacionusuario(usuarioSessionId());
				rol.setCreacionfecha(new Date());
				rol.setModificacionusuario(usuarioSessionId());
				rol.setModificacionfecha(new Date());

				configurarServicio.crearRol(rol);

			}

			textoCSV = " ";

			return configurar();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String cargarOpciones() {

		try {

			StringTokenizer tokens = new StringTokenizer(textoCSV, "\n");
			while (tokens.hasMoreTokens()) {

				String linea = tokens.nextToken();
				String[] campos = linea.split(";");

				Opcion opcion = new Opcion();

				opcion.setOpcionid(Long.parseLong(campos[0]));
				opcion.setOpcionnombre(campos[1].trim());
				opcion.setOpcionvalor(campos[2].trim());

				opcion.setCreacionusuario(usuarioSessionId());
				opcion.setCreacionfecha(new Date());
				opcion.setModificacionusuario(usuarioSessionId());
				opcion.setModificacionfecha(new Date());

				configurarServicio.crearVista(opcion);

			}

			textoCSV = " ";

			return configurar();
		} catch (Exception e) {

			System.out.println(e.toString());
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String cargarMenus() {

		try {

			StringTokenizer tokens = new StringTokenizer(textoCSV, "\n");
			while (tokens.hasMoreTokens()) {

				String linea = tokens.nextToken();
				String[] campos = linea.split(";");

				Menu menu = new Menu();

				menu.setMenuid(Long.parseLong(campos[0]));
				menu.setMenunombre(campos[1]);

				menu.setCreacionusuario(usuarioSessionId());
				menu.setCreacionfecha(new Date());
				menu.setModificacionusuario(usuarioSessionId());
				menu.setModificacionfecha(new Date());

				configurarServicio.crearMenu(menu);

			}

			textoCSV = " ";

			return configurar();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String cargarMenuOpcion() {

		try {

			StringTokenizer tokens = new StringTokenizer(textoCSV, "\n");
			while (tokens.hasMoreTokens()) {

				String linea = tokens.nextToken();
				String[] campos = linea.split(";");

				Submenu submenu = new Submenu();

				submenu.setSubmenuid(Long.parseLong(campos[0].trim()));
				submenu.setSubmenuorden(Short.parseShort(campos[1].trim()));

				Menu menu = new Menu();
				menu.setMenuid(Long.parseLong(campos[2].trim()));
				submenu.setMenu(menu);

				Opcion opcion = new Opcion();
				opcion.setOpcionid(Long.parseLong(campos[3].trim()));

				submenu.setOpcion(opcion);

				submenu.setCreacionusuario(usuarioSessionId());
				submenu.setCreacionfecha(new Date());
				submenu.setModificacionusuario(usuarioSessionId());
				submenu.setModificacionfecha(new Date());

				configurarServicio.crearSubmenu(submenu);

			}

			textoCSV = " ";

			return configurar();
		} catch (Exception e) {

			System.out.println(e.toString());
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String cargarRolMenu() {

		try {

			StringTokenizer tokens = new StringTokenizer(textoCSV, "\n");
			while (tokens.hasMoreTokens()) {

				String linea = tokens.nextToken();
				String[] campos = linea.split(";");

				Acceso acceso = new Acceso();

				acceso.setAccesoid(Long.parseLong(campos[0].trim()));

				Rol rol = new Rol();
				rol.setRolid(Long.parseLong(campos[1].trim()));

				acceso.setRol(rol);

				Menu menu = new Menu();
				menu.setMenuid(Long.parseLong(campos[2].trim()));
				acceso.setMenu(menu);

				acceso.setCreacionusuario(usuarioSessionId());
				acceso.setCreacionfecha(new Date());
				acceso.setModificacionusuario(usuarioSessionId());
				acceso.setModificacionfecha(new Date());

				configurarServicio.crearAcceso(acceso);

			}

			textoCSV = " ";

			return configurar();
		} catch (Exception e) {

			System.out.println(e.toString());
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String actualizarCodigoServicio() {

		configurarServicio.actualizarCodigoServicio(); // se desahabilita para
		// intacion en Produccion

		return configurar();

	}

	public String actualizarDetalleRecibo() {

		configurarServicio.actualizarDetalleRecibo(usuario()); // se
																// desahabilita
																// para

		return configurar();
	}

}
