package com.guandera.talento.client.view;

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

import com.guandera.core.client.view.BaseManagedBean;
import com.guandera.core.shared.model.Ciudad;
import com.guandera.core.shared.model.Departamento;
import com.guandera.talento.client.service.ConfigurarTalentoService;
import com.guandera.talento.server.service.ConfigurarTalentoServiceImpl;
import com.guandera.talento.shared.model.*;

@ManagedBean(name = "configurarTalentoMB")
@SessionScoped
public class ConfigurarTalentoManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private ConfigurarTalentoService configurarTalentoServicio;

	private String textoCSV;

	public String getTextoCSV() {
		return textoCSV;
	}

	public void setTextoCSV(String textoCSV) {
		this.textoCSV = textoCSV;
	}

	public ConfigurarTalentoManagedBean() {
		configurarTalentoServicio = new ConfigurarTalentoServiceImpl();

	}

	public String configurar() {

		textoCSV = "";
		return "Configurar";
	}

	public String cargarDepartamentos() {

		try {

			StringTokenizer tokens = new StringTokenizer(textoCSV, "\n");
			while (tokens.hasMoreTokens()) {

				String linea = tokens.nextToken();
				String[] campos = linea.split(";");

				Departamento departamento = new Departamento();

				departamento.setCodigo(Integer.valueOf(campos[0]));
				departamento.setNombre(campos[1]);

				departamento.setUsuarioCreacion(usuario());
				departamento.setFechaCreacion(new Date());
				departamento.setUsuarioModificacion(usuario());
				departamento.setFechaModificacion(new Date());

				configurarTalentoServicio.crearDepartamento(departamento);

			}

			textoCSV = " ";

			return configurar();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String cargarCiudades() {

		try {

			StringTokenizer tokens = new StringTokenizer(textoCSV, "\n");
			while (tokens.hasMoreTokens()) {

				String linea = tokens.nextToken();
				String[] campos = linea.split(";");

				Ciudad ciudad = new Ciudad();

				Integer DepartamentoCodigo = 0;

				ciudad.setCodigo(Integer.valueOf(campos[0].trim()));
				ciudad.setNombre(campos[1]);

				DepartamentoCodigo = Integer.valueOf(campos[2].trim());

				ciudad.setUsuarioCreacion(usuario());
				ciudad.setFechaCreacion(new Date());
				ciudad.setUsuarioModificacion(usuario());
				ciudad.setFechaModificacion(new Date());

				configurarTalentoServicio.crearCiudad(ciudad, DepartamentoCodigo);

			}

			textoCSV = " ";

			return configurar();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public void exportDepartamentos() {

		DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
		simbolo.setDecimalSeparator(',');
		simbolo.setGroupingSeparator('.');

		DecimalFormat nf = new DecimalFormat("#,##0.00", simbolo);

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		List<Departamento> listaItems = configurarTalentoServicio.consultarListaDepartamentos();

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {

			String coma = ";";
			String salto = "\n";

			for (Departamento departamento : listaItems) {

				String texto;

				texto = departamento.getDepartamentoId() + coma + departamento.getNombre().trim() + salto;

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

			hsr.setHeader("Content-Disposition", "attachment;filename=01Departamentos.csv");

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

	public void exportCiudades() {

		DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
		simbolo.setDecimalSeparator(',');
		simbolo.setGroupingSeparator('.');

		DecimalFormat nf = new DecimalFormat("#,##0.00", simbolo);

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		List<Ciudad> listaItems = configurarTalentoServicio.consultarListaCiudades();

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {

			String coma = ";";
			String salto = "\n";

			for (Ciudad ciudad : listaItems) {

				String texto;

				texto = ciudad.getDepartamento().getCodigo() + coma + ciudad.getDepartamento().getNombre().trim() + coma
						+ ciudad.getCodigo() + coma + ciudad.getNombre().trim() + salto;

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

			hsr.setHeader("Content-Disposition", "attachment;filename=03Ciudades.csv");

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
