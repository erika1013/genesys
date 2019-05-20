package com.guandera.talento.client.view;

import com.guandera.talento.client.service.*;
import com.guandera.talento.server.service.*;
import com.guandera.talento.shared.model.NivelEducativo;
import com.guandera.talento.shared.model.ProgramaAcademico;
import com.guandera.talento.shared.model.ProgramaAcademico;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import com.guandera.core.client.view.BaseManagedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.guandera.talento.client.service.*;
import com.guandera.talento.server.service.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@ManagedBean(name = "ProgramaAcademicoMB")
@SessionScoped
public class ProgramaAcademicoManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private ProgramaAcademico itemSeleccionado;
	private List<ProgramaAcademico> listaItems;
	private boolean listingProgramaAcademico = false;
	private ProgramaAcademicoService servicio;
	private TalentoService talentoServicio;

	private Long nivelEducativoId;
	private List<SelectItem> nivelEducativoItems;
	private boolean listingNivelEducativo = false;

	public ProgramaAcademicoManagedBean() {
		servicio = new ProgramaAcademicoServiceImpl();
		talentoServicio = new TalentoServiceImpl();

	}

	public String prepararLista() {

		return "Lista";
	}

	public String prepararConsulta() {

		return "Detalle";
	}

	public String prepararCreacion() {
		itemSeleccionado = new ProgramaAcademico();
		nivelEducativoId = 0l;
		return "Crear";
	}

	public String prepararEdicion() {
		nivelEducativoId = itemSeleccionado.getNivelEducativo().getNivelEducativoId();

		return "Editar";
	}

	public String crear() {

		try {

			NivelEducativo nivelEducativo = new NivelEducativo();
			nivelEducativo.setNivelEducativoId(nivelEducativoId);
			itemSeleccionado.setNivelEducativo(nivelEducativo);

			itemSeleccionado.setUsuarioCreacion(usuario());
			itemSeleccionado.setFechaCreacion(new Date());
			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			servicio.crear(itemSeleccionado);
			mensajeInfo("ProgramaAcademicoCreated");
			return prepararCreacion();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String actualizar() {
		try {

			NivelEducativo nivelEducativo = new NivelEducativo();
			nivelEducativo.setNivelEducativoId(nivelEducativoId);
			itemSeleccionado.setNivelEducativo(nivelEducativo);

			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			servicio.actualizar(itemSeleccionado);
			mensajeInfo("ProgramaAcademicoUpdated");
			return prepararConsulta();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String eliminar() {
		servicio.eliminar(itemSeleccionado);
		listingProgramaAcademico = false;
		return prepararLista();
	}

	public ProgramaAcademico getItemSeleccionado() {
		return itemSeleccionado;
	}

	public void setItemSeleccionado(ProgramaAcademico itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public List<ProgramaAcademico> getListaItems() {

		if (!listingProgramaAcademico) {
			listingProgramaAcademico = true;
			listaItems = servicio.consultarTodos();
		}
		return listaItems;
	}

	public void setListaItems(List<ProgramaAcademico> listaItems) {
		this.listaItems = listaItems;
	}

	public Long getNivelEducativoId() {
		return nivelEducativoId;
	}

	public void setNivelEducativoId(Long nivelEducativoId) {
		this.nivelEducativoId = nivelEducativoId;
	}

	public List<SelectItem> getNivelEducativoItems() {

		if (!listingNivelEducativo) {
			listingNivelEducativo = true;
			nivelEducativoItems = new ArrayList<SelectItem>();
			List<NivelEducativo> nivelEducativoList = talentoServicio.listaNivelEducativo();

			for (NivelEducativo nivelEducativo : nivelEducativoList) {
				nivelEducativoItems
						.add(new SelectItem(nivelEducativo.getNivelEducativoId(), nivelEducativo.getNombre()));

			}
		}

		return nivelEducativoItems;
	}

	public void setNivelEducativoItems(List<SelectItem> nivelEducativoItems) {
		this.nivelEducativoItems = nivelEducativoItems;
	}

}
