package com.guandera.core.client.view.acceso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.guandera.core.client.service.acceso.ConfigurarAccesoService;
import com.guandera.core.client.view.BaseManagedBean;
import com.guandera.core.server.service.acceso.ConfigurarAccesoServiceImpl;
import com.guandera.core.shared.model.acceso.Acceso;
import com.guandera.core.shared.model.acceso.Menu;
import com.guandera.core.shared.model.acceso.Opcion;
import com.guandera.core.shared.model.acceso.Rol;
import com.guandera.core.shared.model.acceso.Submenu;

@ManagedBean(name = "configurarAccesoMB")
@SessionScoped
public class ConfigurarAccesoManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private ConfigurarAccesoService configurarAccesoServicio;

	private String textoCSV;

	public String getTextoCSV() {
		return textoCSV;
	}

	public void setTextoCSV(String textoCSV) {
		this.textoCSV = textoCSV;
	}

	public ConfigurarAccesoManagedBean() {
		configurarAccesoServicio = new ConfigurarAccesoServiceImpl();

	}

	public String configurar() {

		textoCSV = "";
		return "Configurar";
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

				configurarAccesoServicio.crearRol(rol);

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

				configurarAccesoServicio.crearVista(opcion);

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

				configurarAccesoServicio.crearMenu(menu);

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

				configurarAccesoServicio.crearSubmenu(submenu);

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

				configurarAccesoServicio.crearAcceso(acceso);

			}

			textoCSV = " ";

			return configurar();
		} catch (Exception e) {

			System.out.println(e.toString());
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public void exportRoles() {

		DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
		simbolo.setDecimalSeparator(',');
		simbolo.setGroupingSeparator('.');

		DecimalFormat nf = new DecimalFormat("#,##0.00", simbolo);

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		List<Rol> listaItems = configurarAccesoServicio.consultarListaRoles();

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {

			String coma = ";";
			String salto = "\n";

			for (Rol rol : listaItems) {

				String texto;

				texto = rol.getRolid() + coma + rol.getRolnombre().trim() + salto;

				byte[] exportTexto = texto.getBytes();

				baos.write(exportTexto);

			}

		} catch (IOException e) {

			e.printStackTrace();
		}

		FacesContext context = FacesContext.getCurrentInstance();
		Object response = context.getExternalContext().getResponse();

		if (response instanceof HttpServletResponse) {
			HttpServletResponse hsr = (HttpServletResponse) response;

			hsr.setContentType("text/plain;charset=UTF-8");
			hsr.setContentLength(baos.size());

			hsr.setHeader("Content-Disposition", "attachment;filename=01Roles.csv");

			try {
				ServletOutputStream out = hsr.getOutputStream();
				baos.writeTo(out);
				out.flush();
			} catch (IOException ex) {
				System.out.println("Error " + ex.getMessage());
			}

			context.responseComplete();

		}

	}

	public void exportOpciones() {

		DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
		simbolo.setDecimalSeparator(',');
		simbolo.setGroupingSeparator('.');

		DecimalFormat nf = new DecimalFormat("#,##0.00", simbolo);

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		List<Opcion> listaItems = configurarAccesoServicio.consultarListaOpciones();

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {

			String coma = ";";
			String salto = "\n";

			for (Opcion opcion : listaItems) {

				String texto;

				texto = opcion.getOpcionid() + coma + opcion.getOpcionnombre().trim() + coma
						+ opcion.getOpcionvalor().trim() + salto;

				byte[] exportTexto = texto.getBytes();

				baos.write(exportTexto);

			}

		} catch (IOException e) {

			e.printStackTrace();
		}

		FacesContext context = FacesContext.getCurrentInstance();
		Object response = context.getExternalContext().getResponse();

		if (response instanceof HttpServletResponse) {
			HttpServletResponse hsr = (HttpServletResponse) response;

			hsr.setContentType("text/plain;charset=UTF-8");
			hsr.setContentLength(baos.size());

			hsr.setHeader("Content-Disposition", "attachment;filename=02Opciones.csv");

			try {
				ServletOutputStream out = hsr.getOutputStream();
				baos.writeTo(out);
				out.flush();
			} catch (IOException ex) {
				System.out.println("Error " + ex.getMessage());
			}

			context.responseComplete();

		}

	}

	public void exportMenus() {

		DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
		simbolo.setDecimalSeparator(',');
		simbolo.setGroupingSeparator('.');

		DecimalFormat nf = new DecimalFormat("#,##0.00", simbolo);

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		List<Menu> listaItems = configurarAccesoServicio.consultarListaMenus();

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {

			String coma = ";";
			String salto = "\n";

			for (Menu menu : listaItems) {

				String texto;

				texto = menu.getMenuid() + coma + menu.getMenunombre().trim() + salto;

				byte[] exportTexto = texto.getBytes();

				baos.write(exportTexto);

			}

		} catch (IOException e) {

			e.printStackTrace();
		}

		FacesContext context = FacesContext.getCurrentInstance();
		Object response = context.getExternalContext().getResponse();

		if (response instanceof HttpServletResponse) {
			HttpServletResponse hsr = (HttpServletResponse) response;

			hsr.setContentType("text/plain;charset=UTF-8");
			hsr.setContentLength(baos.size());

			hsr.setHeader("Content-Disposition", "attachment;filename=03Menus.csv");

			try {
				ServletOutputStream out = hsr.getOutputStream();
				baos.writeTo(out);
				out.flush();
			} catch (IOException ex) {
				System.out.println("Error " + ex.getMessage());
			}

			context.responseComplete();

		}

	}

	public void exportRolMenu() {

		DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
		simbolo.setDecimalSeparator(',');
		simbolo.setGroupingSeparator('.');

		DecimalFormat nf = new DecimalFormat("#,##0.00", simbolo);

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		List<Acceso> listaItems = configurarAccesoServicio.consultarListaAccesos();

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {

			String coma = ";";
			String salto = "\n";

			for (Acceso acceso : listaItems) {

				String texto;

				texto = acceso.getAccesoid() + coma + acceso.getRol().getRolid() + coma + acceso.getMenu().getMenuid()
						+ salto;

				byte[] exportTexto = texto.getBytes();

				baos.write(exportTexto);

			}

		} catch (IOException e) {

			e.printStackTrace();
		}

		FacesContext context = FacesContext.getCurrentInstance();
		Object response = context.getExternalContext().getResponse();

		if (response instanceof HttpServletResponse) {
			HttpServletResponse hsr = (HttpServletResponse) response;

			hsr.setContentType("text/plain;charset=UTF-8");
			hsr.setContentLength(baos.size());

			hsr.setHeader("Content-Disposition", "attachment;filename=04RolMenu.csv");

			try {
				ServletOutputStream out = hsr.getOutputStream();
				baos.writeTo(out);
				out.flush();
			} catch (IOException ex) {
				System.out.println("Error " + ex.getMessage());
			}

			context.responseComplete();

		}

	}

	public void exportMenuOpcion() {

		DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
		simbolo.setDecimalSeparator(',');
		simbolo.setGroupingSeparator('.');

		DecimalFormat nf = new DecimalFormat("#,##0.00", simbolo);

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		List<Submenu> listaItems = configurarAccesoServicio.consultarListaSubmenus();

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {

			String coma = ";";
			String salto = "\n";

			for (Submenu submenu : listaItems) {

				String texto;

				try {

					texto = submenu.getSubmenuid() + coma + submenu.getSubmenuorden() + coma
							+ submenu.getMenu().getMenuid() + coma + submenu.getOpcion().getOpcionid() + salto;
				} catch (Exception e) {
					texto = submenu.getSubmenuid() + " error";
				}

				byte[] exportTexto = texto.getBytes();

				baos.write(exportTexto);

			}

		} catch (IOException e) {

			e.printStackTrace();
		}

		FacesContext context = FacesContext.getCurrentInstance();
		Object response = context.getExternalContext().getResponse();

		if (response instanceof HttpServletResponse) {
			HttpServletResponse hsr = (HttpServletResponse) response;

			hsr.setContentType("text/plain;charset=UTF-8");
			hsr.setContentLength(baos.size());

			hsr.setHeader("Content-Disposition", "attachment;filename=05MenuOpcion.csv");

			try {
				ServletOutputStream out = hsr.getOutputStream();
				baos.writeTo(out);
				out.flush();
			} catch (IOException ex) {
				System.out.println("Error " + ex.getMessage());
			}

			context.responseComplete();

		}

	}

}
