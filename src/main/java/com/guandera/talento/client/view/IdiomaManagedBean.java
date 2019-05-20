package com.guandera.talento.client.view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.guandera.core.client.view.BaseManagedBean;
import com.guandera.talento.client.service.IdiomaService;
import com.guandera.talento.server.service.IdiomaServiceImpl;
import com.guandera.talento.shared.model.Idioma;

@ManagedBean(name = "IdiomaMB")
@SessionScoped
public class IdiomaManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private Idioma itemSeleccionado;
	private List<Idioma> listaItems;
	private boolean listingIdioma = false;
	private IdiomaService servicio;

	public IdiomaManagedBean() {
		servicio = new IdiomaServiceImpl();
		inicializar();
	}

	public String prepararLista() {

		return "Lista";
	}

	public String prepararConsulta() {

		return "Detalle";
	}

	public String prepararCreacion() {
		itemSeleccionado = new Idioma();
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
			mensajeInfo("IdiomaCreated");
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
			mensajeInfo("IdiomaUpdated");
			return prepararConsulta();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String eliminar() {
		servicio.eliminar(itemSeleccionado);
		listingIdioma = false;
		return prepararLista();
	}

	public Idioma getItemSeleccionado() {
		return itemSeleccionado;
	}

	public void setItemSeleccionado(Idioma itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public List<Idioma> getListaItems() {

		if (!listingIdioma) {
			listingIdioma = true;
			listaItems = servicio.consultarTodos();
		}
		return listaItems;
	}

	public void setListaItems(List<Idioma> listaItems) {
		this.listaItems = listaItems;
	}

	private void inicializar() {

		itemSeleccionado = new Idioma();

	}

	@PostConstruct
	public void prerender() {
		inicializar();

	}

}
