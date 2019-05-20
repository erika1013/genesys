package com.guandera.core.client.view;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import com.guandera.core.shared.model.Departamento;
import com.guandera.talento.client.service.*;
import com.guandera.talento.server.service.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@ManagedBean(name = "DepartamentoMB")
@SessionScoped
public class DepartamentoManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private Departamento itemSeleccionado;
	private List<Departamento> listaItems;
	private boolean listingDepartamento = false;
	private DepartamentoService servicio;

	public DepartamentoManagedBean() {
		servicio = new DepartamentoServiceImpl();

	}

	public String prepararLista() {

		return "Lista";
	}

	public String prepararConsulta() {

		return "Detalle";
	}

	public String prepararCreacion() {
		itemSeleccionado = new Departamento();
		return "Crear";
	}

	public String prepararEdicion() {

		return "Editar";
	}

	public String crear() {

		try {
			itemSeleccionado.setUsuarioCreacion(usuario());
			itemSeleccionado.setFechaCreacion(new Date());
			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			servicio.crear(itemSeleccionado);
			mensajeInfo("DepartamentoCreated");
			return prepararCreacion();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String actualizar() {
		try {

			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			servicio.actualizar(itemSeleccionado);
			mensajeInfo("DepartamentoUpdated");
			return prepararConsulta();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String eliminar() {
		servicio.eliminar(itemSeleccionado);
		listingDepartamento = false;
		return prepararLista();
	}

	public Departamento getItemSeleccionado() {
		return itemSeleccionado;
	}

	public void setItemSeleccionado(Departamento itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public List<Departamento> getListaItems() {

		if (!listingDepartamento) {
			listingDepartamento = true;
			listaItems = servicio.consultarTodos();
		}
		return listaItems;
	}

	public void setListaItems(List<Departamento> listaItems) {
		this.listaItems = listaItems;
	}

}
