package com.guandera.talento.client.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import com.guandera.core.client.view.BaseManagedBean;
import com.guandera.core.shared.model.Ciudad;
import com.guandera.core.shared.model.Departamento;
import com.guandera.talento.client.service.InstitucionService;
import com.guandera.talento.client.service.TalentoService;
import com.guandera.talento.server.service.InstitucionServiceImpl;
import com.guandera.talento.server.service.TalentoServiceImpl;
import com.guandera.talento.shared.model.Institucion;

@ManagedBean(name = "InstitucionMB")
@SessionScoped
public class InstitucionManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private Institucion itemSeleccionado;
	private List<Institucion> listaItems;
	private boolean listingInstitucion = false;
	private InstitucionService servicio;
	private TalentoService talentoServicio;

	private Long ciudadId;
	private List<SelectItem> ciudadItems;
	private boolean listingCiudad = false;

	private Long departamentoId;
	private List<SelectItem> departamentoItems;
	private boolean listingDepartamento = false;

	public InstitucionManagedBean() {
		servicio = new InstitucionServiceImpl();
		talentoServicio = new TalentoServiceImpl();

	}

	public String prepararLista() {

		return "Lista";
	}

	public String prepararConsulta() {

		return "Detalle";
	}

	public String prepararCreacion() {
		itemSeleccionado = new Institucion();
		Integer siguienteCodigo = servicio.siguienteCodigoInstitucion();
		itemSeleccionado.setCodigo(siguienteCodigo);

		ciudadId = 0l;
		return "Crear";
	}

	public String prepararEdicion() {

		ciudadId = itemSeleccionado.getCiudad().getCiudadId();
		return "Editar";
	}

	public String crear() {

		try {

			Ciudad ciudad = new Ciudad();
			ciudad.setCiudadId(ciudadId);
			itemSeleccionado.setCiudad(ciudad);

			itemSeleccionado.setUsuarioCreacion(usuario());
			itemSeleccionado.setFechaCreacion(new Date());
			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			servicio.crear(itemSeleccionado);
			mensajeInfo("InstitucionCreated");
			return prepararCreacion();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String actualizar() {
		try {

			Ciudad ciudad = new Ciudad();
			ciudad.setCiudadId(ciudadId);
			itemSeleccionado.setCiudad(ciudad);

			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			servicio.actualizar(itemSeleccionado);
			mensajeInfo("InstitucionUpdated");
			return prepararConsulta();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String eliminar() {
		servicio.eliminar(itemSeleccionado);
		listingInstitucion = false;
		return prepararLista();
	}

	public Institucion getItemSeleccionado() {
		return itemSeleccionado;
	}

	public void setItemSeleccionado(Institucion itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public List<Institucion> getListaItems() {

		if (!listingInstitucion) {
			listingInstitucion = true;
			listaItems = servicio.consultarTodos();
		}
		return listaItems;
	}

	public void setListaItems(List<Institucion> listaItems) {
		this.listaItems = listaItems;
	}

	public Long getCiudadId() {
		return ciudadId;
	}

	public void setCiudadId(Long ciudadId) {
		this.ciudadId = ciudadId;
	}

	public List<SelectItem> getCiudadItems() {

		return ciudadItems;
	}

	public void setCiudadItems(List<SelectItem> ciudadItems) {
		this.ciudadItems = ciudadItems;
	}

	public Long getDepartamentoId() {
		return departamentoId;
	}

	public void setDepartamentoId(Long departamentoId) {
		this.departamentoId = departamentoId;
	}

	public List<SelectItem> getDepartamentoItems() {

		if (!listingDepartamento) {
			listingDepartamento = true;
			departamentoItems = new ArrayList<SelectItem>();
			List<Departamento> departamentoList = talentoServicio.listaDepartamento();

			for (Departamento departamento : departamentoList) {
				departamentoItems.add(new SelectItem(departamento.getDepartamentoId(), departamento.getNombre()));

			}
		}

		return departamentoItems;
	}

	public void setDepartamentoItems(List<SelectItem> departamentoItems) {
		this.departamentoItems = departamentoItems;
	}

	public void cargarCiudadesDepartamento() {

		if (departamentoId != null) {
			// listingCiudad = true;
			ciudadItems = new ArrayList<SelectItem>();
			List<Ciudad> ciudadList = talentoServicio.listaCiudadPorDepartamentoId(departamentoId);

			for (Ciudad ciudad : ciudadList) {
				ciudadItems.add(new SelectItem(ciudad.getCiudadId(), ciudad.getNombre()));

			}
		}

	}

}
