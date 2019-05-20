package com.guandera.talento.client.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import com.guandera.core.client.view.BaseManagedBean;
import com.guandera.talento.client.service.CompetenciaService;
import com.guandera.talento.client.service.TalentoService;
import com.guandera.talento.server.service.CompetenciaServiceImpl;
import com.guandera.talento.server.service.TalentoServiceImpl;
import com.guandera.talento.shared.model.Competencia;
import com.guandera.talento.shared.model.CompetenciaTipo;

@ManagedBean(name = "CompetenciaMB")
@SessionScoped
public class CompetenciaManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private Competencia itemSeleccionado;
	private List<Competencia> listaItems;
	private boolean listingCompetencia = false;
	private CompetenciaService servicio;
	private TalentoService talentoServicio;

	private Long tipoId;
	private List<SelectItem> tipoItems;
	private boolean listingTipo = false;

	public CompetenciaManagedBean() {
		servicio = new CompetenciaServiceImpl();
		talentoServicio = new TalentoServiceImpl();

	}

	public String refrescarLista() {

		listingCompetencia = false;
		return "Lista";
	}

	public String prepararLista() {

		return "Lista";
	}

	public String prepararConsulta() {

		return "Detalle";
	}

	public String prepararCreacion() {
		itemSeleccionado = new Competencia();
		Integer siguienteCodigo = servicio.siguienteCodigoCompetencia();
		itemSeleccionado.setCodigo(siguienteCodigo);
		tipoId = 0l;
		return "Crear";
	}

	public String prepararEdicion() {
		tipoId = itemSeleccionado.getTipo().getTipoId();
		return "Editar";
	}

	public String crear() {

		try {

			CompetenciaTipo tipo = new CompetenciaTipo();
			tipo.setTipoId(tipoId);
			itemSeleccionado.setTipo(tipo);

			itemSeleccionado.setUsuarioCreacion(usuario());
			itemSeleccionado.setFechaCreacion(new Date());
			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			servicio.crear(itemSeleccionado);
			mensajeInfo("CompetenciaCreated");
			return prepararCreacion();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String actualizar() {
		try {

			CompetenciaTipo tipo = new CompetenciaTipo();
			tipo.setTipoId(tipoId);
			itemSeleccionado.setTipo(tipo);

			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			servicio.actualizar(itemSeleccionado);
			mensajeInfo("CompetenciaUpdated");
			return prepararConsulta();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String eliminar() {
		servicio.eliminar(itemSeleccionado);
		listingCompetencia = false;
		return prepararLista();
	}

	public Competencia getItemSeleccionado() {
		return itemSeleccionado;
	}

	public void setItemSeleccionado(Competencia itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public List<Competencia> getListaItems() {

		if (!listingCompetencia) {
			listingCompetencia = true;
			listaItems = servicio.consultarTodos();
		}
		return listaItems;
	}

	public void setListaItems(List<Competencia> listaItems) {
		this.listaItems = listaItems;
	}

	public Long getTipoId() {
		return tipoId;
	}

	public void setTipoId(Long tipoId) {
		this.tipoId = tipoId;
	}

	public List<SelectItem> getTipoItems() {

		if (!listingTipo) {
			listingTipo = true;
			tipoItems = new ArrayList<SelectItem>();
			List<CompetenciaTipo> tipoList = talentoServicio.listaCompetenciaTipo();

			for (CompetenciaTipo tipo : tipoList) {
				tipoItems.add(new SelectItem(tipo.getTipoId(), tipo.getNombre()));

			}
		}

		return tipoItems;
	}

	public void setTipoItems(List<SelectItem> tipoItems) {
		this.tipoItems = tipoItems;
	}

}
