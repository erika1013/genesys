package com.guandera.core.client.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import com.guandera.core.client.service.GastoService;
import com.guandera.core.server.service.GastoServiceImpl;
import com.guandera.core.shared.model.Gasto;
import com.guandera.core.shared.model.GastoConcepto;
import com.guandera.core.shared.model.GastoSubConcepto;
import com.guandera.core.shared.model.Proveedor;

@ManagedBean(name = "gastoMB")
@SessionScoped
public class GastoManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private GastoService gastoServicio;
	private Gasto itemSeleccionado;
	private List<Gasto> listaItems;
	private boolean listing = false;

	private Long proveedorid;
	private List<SelectItem> proveedorItems;
	private boolean listingProveedor = false;

	private Long gastoConceptoid;
	private List<SelectItem> gastoConceptoItems;
	private boolean listingConcepto = false;

	private Long subConceptoid;
	private List<SelectItem> subConceptoItems;

	public GastoManagedBean() {
		gastoServicio = new GastoServiceImpl();

		inicializar();
	}

	public String actualizar() {

		try {

			GastoConcepto gastoConcepto = new GastoConcepto();
			Proveedor proveedor = new Proveedor();
			GastoSubConcepto gastoSubConcepto = new GastoSubConcepto();

			gastoConcepto.setGastoConceptoid(gastoConceptoid);
			gastoSubConcepto.setGastoSubConceptoid(subConceptoid);
			proveedor.setProveedorid(proveedorid);

			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			gastoServicio.actualizar(itemSeleccionado);
			mensajeInfo("GastoUpdated");
			inicializar();
			verificarLista();
			return prepararLista();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String crear() {

		listing = false;

		try {
			itemSeleccionado.setUsuarioCreacion(usuario());
			itemSeleccionado.setFechaCreacion(new Date());
			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			GastoConcepto gastoConcepto = new GastoConcepto();
			Proveedor proveedor = new Proveedor();
			GastoSubConcepto gastoSubConcepto = new GastoSubConcepto();

			gastoConcepto.setGastoConceptoid(gastoConceptoid);
			gastoSubConcepto.setGastoSubConceptoid(subConceptoid);
			proveedor.setProveedorid(proveedorid);

			itemSeleccionado.setGastoConcepto(gastoConcepto);
			itemSeleccionado.setSubConcepto(gastoSubConcepto);
			itemSeleccionado.setProveedor(proveedor);

			gastoServicio.crear(itemSeleccionado);
			mensajeInfo("GastoCreated");

			itemSeleccionado = new Gasto();

			return null;

		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String eliminar() {

		gastoServicio.eliminar(itemSeleccionado);
		listing = false;
		return prepararLista();
	}

	public Gasto getItemSeleccionado() {
		return itemSeleccionado;
	}

	public List<Gasto> getListaItems() {

		if (!listing) {
			listing = true;
			listaItems = gastoServicio.consultarTodos();
		}
		return listaItems;
	}

	public GastoService getGastoGasto() {
		return gastoServicio;
	}

	private void inicializar() {

		itemSeleccionado = new Gasto();

	}

	public boolean isListing() {
		return listing;
	}

	public void listarGasto() {
		setListing(true);
		listaItems = gastoServicio.consultarTodos();
	}

	public String prepararConsulta() {
		return "Detalle";
	}

	public String prepararCreacion() {
		itemSeleccionado = new Gasto();
		return "Crear";
	}

	public String prepararEdicion() {

		gastoConceptoid = itemSeleccionado.getConcepto().getGastoConceptoid();
		subConceptoid = itemSeleccionado.getSubConcepto().getGastoSubConceptoid();
		proveedorid = itemSeleccionado.getProveedor().getProveedorid();

		return "Editar";
	}

	public String prepararLista() {

		return "Lista";
	}

	@PostConstruct
	public void prerender() {
		inicializar();

	}

	public void setItemSeleccionado(Gasto itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public void setListaItems(List<Gasto> listaItems) {
		this.listaItems = listaItems;
	}

	public void setListing(boolean listing) {
		this.listing = listing;
	}

	public void setGastoGasto(GastoService gastoServicio) {
		this.gastoServicio = gastoServicio;
	}

	private void verificarLista() {
		if (!listing) {
			this.listaItems = gastoServicio.consultarTodos();
		}
	}

	public Long getProveedorid() {
		return proveedorid;
	}

	public void setProveedorid(Long proveedorid) {
		this.proveedorid = proveedorid;
	}

	public List<SelectItem> getProveedorItems() {
		if (!listingProveedor) {
			listingProveedor = true;
			proveedorItems = new ArrayList<SelectItem>();
			List<Proveedor> proveedorList = gastoServicio.consultarProveedores();

			for (Proveedor proveedor : proveedorList) {
				proveedorItems.add(new SelectItem(proveedor.getProveedorid(), proveedor.getProveedornombre()));

			}
		}

		return proveedorItems;
	}

	public void setProveedorItems(List<SelectItem> proveedorItems) {
		this.proveedorItems = proveedorItems;
	}

	public Long getGastoConceptoid() {
		return gastoConceptoid;
	}

	public void setGastoConceptoid(Long gastoConceptoid) {
		this.gastoConceptoid = gastoConceptoid;
	}

	public List<SelectItem> getGastoConceptoItems() {
		if (!listingConcepto) {
			listingConcepto = true;
			gastoConceptoItems = new ArrayList<SelectItem>();
			List<GastoConcepto> gastoConceptoList = gastoServicio.consultarTiposGasto();

			for (GastoConcepto gastoConcepto : gastoConceptoList) {
				gastoConceptoItems.add(
						new SelectItem(gastoConcepto.getGastoConceptoid(), gastoConcepto.getGastoConceptonombre()));
			}
		}

		return gastoConceptoItems;
	}

	public void setGastoConceptoItems(List<SelectItem> gastoConceptoItems) {
		this.gastoConceptoItems = gastoConceptoItems;
	}

	public Long getSubConceptoid() {
		return subConceptoid;
	}

	public void setSubConceptoid(Long subConceptoid) {
		this.subConceptoid = subConceptoid;
	}

	public List<SelectItem> getSubConceptoItems() {

		return subConceptoItems;
	}

	public void setSubConceptoItems(List<SelectItem> subConceptoItems) {
		this.subConceptoItems = subConceptoItems;
	}

	public void cargarSubConceptos() {

		if (gastoConceptoid != 0) {

			subConceptoItems = new ArrayList<SelectItem>();
			List<GastoSubConcepto> subConceptoList = gastoServicio.consultarSubConceptos(gastoConceptoid);

			for (GastoSubConcepto subConcepto : subConceptoList) {
				subConceptoItems.add(new SelectItem(subConcepto.getGastoSubConceptoid(), subConcepto.getNombre()));
			}
		}

	}

}
