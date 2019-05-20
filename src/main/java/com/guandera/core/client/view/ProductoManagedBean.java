package com.guandera.core.client.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import com.guandera.core.client.service.ProductoService;
import com.guandera.core.server.service.ProductoServiceImpl;
import com.guandera.core.shared.model.Producto;
import com.guandera.core.shared.model.Sede;

@ManagedBean(name = "productoMB")
@SessionScoped
public class ProductoManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private ProductoService productoProducto;
	private Producto itemSeleccionado;
	private List<Producto> listaItems;
	private boolean listing = false;

	private Long sedeid;
	private List<SelectItem> sedeItems;
	private boolean listingSede = false;

	public ProductoManagedBean() {
		productoProducto = new ProductoServiceImpl();

		inicializar();
	}

	public String actualizar() {

		try {

			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());
			productoProducto.actualizar(itemSeleccionado);
			mensajeInfo("ProductoUpdated");
			inicializar();
			verificarLista();
			return prepararLista();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String crear() {

		try {

			itemSeleccionado.setUsuarioCreacion(usuario());
			itemSeleccionado.setFechaCreacion(new Date());
			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			Sede sede = new Sede();

			sede.setSedeid(sedeid);

			itemSeleccionado.setSede(sede);

			productoProducto.crear(itemSeleccionado);
			mensajeInfo("ProductoCreated");
			inicializar();
			verificarLista();
			return prepararLista();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String eliminar() {

		productoProducto.eliminar(itemSeleccionado);
		listing = false;
		return prepararLista();
	}

	public Producto getItemSeleccionado() {
		return itemSeleccionado;
	}

	public List<Producto> getListaItems() {

		if (!listing) {
			listaItems = productoProducto.consultarTodos();
		}
		return listaItems;
	}

	public ProductoService getProductoProducto() {
		return productoProducto;
	}

	private void inicializar() {

		itemSeleccionado = new Producto();

	}

	public boolean isListing() {
		return listing;
	}

	public void listarProducto() {
		setListing(true);
		listaItems = productoProducto.consultarTodos();
	}

	public String prepararConsulta() {
		return "Detalle";
	}

	public String prepararCreacion() {
		itemSeleccionado = new Producto();
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

	public void setItemSeleccionado(Producto itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public void setListaItems(List<Producto> listaItems) {
		this.listaItems = listaItems;
	}

	public void setListing(boolean listing) {
		this.listing = listing;
	}

	public void setProductoProducto(ProductoService productoProducto) {
		this.productoProducto = productoProducto;
	}

	private void verificarLista() {
		if (listing) {
			this.listaItems = productoProducto.consultarTodos();
		}
	}

	public Long getSedeid() {
		return sedeid;
	}

	public void setSedeid(Long sedeid) {
		this.sedeid = sedeid;
	}

	public List<SelectItem> getSedeItems() {

		if (!listingSede) {
			listingSede = true;
			sedeItems = new ArrayList<SelectItem>();
			List<Sede> sedeList = productoProducto.consultarSedes();

			for (Sede sede : sedeList) {
				sedeItems.add(new SelectItem(sede.getSedeid(), sede.getSedenombre()));
			}
		}

		return sedeItems;
	}

	public void setSedeItems(List<SelectItem> sedeItems) {
		this.sedeItems = sedeItems;
	}

}
