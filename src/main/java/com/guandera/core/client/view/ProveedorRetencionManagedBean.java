package com.guandera.core.client.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import com.guandera.core.client.service.ProveedorRetencionService;
import com.guandera.core.server.service.ProveedorRetencionServiceImpl;
import com.guandera.core.shared.model.ImpuestoConcepto;
import com.guandera.core.shared.model.PagoTipo;
import com.guandera.core.shared.model.Proveedor;
import com.guandera.core.shared.model.ProveedorRetencion;

@ManagedBean(name = "proveedorRetencionMB")
@SessionScoped
public class ProveedorRetencionManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private ProveedorRetencionService proveedorRetencionServicio;
	private ProveedorRetencion itemSeleccionado;
	private List<ProveedorRetencion> listaItems = new ArrayList<ProveedorRetencion>();
	private boolean listing = false;

	private List<Proveedor> listaProveedor = new ArrayList<Proveedor>();
	private List<SelectItem> proveedorItems;
	private Long proveedorid;
	private Proveedor proveedorSeleccionado;

	private boolean listingProveedor = false;

	// private List<PagoTipo> listaPagoTipo;
	private List<SelectItem> tipoPagoItems;
	// private PagoTipo pagoTipo;
	private Long pagoTipoid;
	private boolean listingPagoTipo = false;

	// private List<ImpuestoConcepto> listaImpuestoConcepto;
	private List<SelectItem> impuestoItems;
	// private ImpuestoConcepto impuestoConcepto;
	private Long impuestoConceptoid;
	private boolean listingImpuestoConcepto = false;

	public ProveedorRetencionManagedBean() {
		proveedorRetencionServicio = new ProveedorRetencionServiceImpl();

		inicializar();
	}

	public String actualizar() {

		try {

			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			proveedorRetencionServicio.actualizar(itemSeleccionado);
			mensajeInfo("ProveedorRetencionUpdated");
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
			PagoTipo pagoTipo = new PagoTipo();
			pagoTipo.setPagoTipoid(pagoTipoid);
			itemSeleccionado.setPagoTipo(pagoTipo);

			ImpuestoConcepto impuesto = new ImpuestoConcepto();
			impuesto.setImpuestoconceptoid(impuestoConceptoid);
			itemSeleccionado.setImpuestoConcepto(impuesto);

			itemSeleccionado.setCodigoRetencion(proveedorRetencionServicio.siguienteRegistro());
			itemSeleccionado.setUsuarioCreacion(usuario());
			itemSeleccionado.setFechaCreacion(new Date());
			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			itemSeleccionado.setProveedor(proveedorSeleccionado);

			proveedorRetencionServicio.crear(itemSeleccionado);

			mensajeInfo("ProveedorRetencionCreated");
			inicializar();
			verificarLista();

			return prepararLista();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			System.out.println(e.getMessage());
			return null;
		}

	}

	public String eliminar() {

		proveedorRetencionServicio.eliminar(itemSeleccionado);
		listing = false;
		return prepararLista();
	}

	public ProveedorRetencion getItemSeleccionado() {
		return itemSeleccionado;
	}

	public List<ProveedorRetencion> getListaItems() {
		if (!listing) {
			listing = true;

			listaItems = proveedorRetencionServicio.consultarTodos();

		}
		return listaItems;
	}

	public ProveedorRetencionService getProveedorRetencionServicio() {
		return proveedorRetencionServicio;
	}

	private void inicializar() {

		itemSeleccionado = new ProveedorRetencion();

	}

	public boolean isListing() {
		return listing;
	}

	public void listarProveedorRetencion() {
		setListing(true);
		listaItems = proveedorRetencionServicio.consultarTodos();
	}

	public String prepararConsulta() {

		return "Detalle";
	}

	public String prepararCreacionContrato() {
		proveedorSeleccionado = new Proveedor();

		return "CrearBuscar";

	}

	public String prepararCreacion() {
		itemSeleccionado = new ProveedorRetencion();
		pagoTipoid = (long) 0;
		impuestoConceptoid = (long) 0;
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

	public void setItemSeleccionado(ProveedorRetencion itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public void setListaItems(List<ProveedorRetencion> listaItems) {
		this.listaItems = listaItems;
	}

	public void setListing(boolean listing) {
		this.listing = listing;
	}

	public void setProveedorRetencionServicio(ProveedorRetencionService proveedorRetencionServicio) {
		this.proveedorRetencionServicio = proveedorRetencionServicio;
	}

	private void verificarLista() {
		if (listing) {
			this.listaItems = proveedorRetencionServicio.consultarTodos();
		}
	}

	public List<SelectItem> getProveedorItems() {
		if (!listingProveedor) {
			listingProveedor = true;

			proveedorItems = new ArrayList<SelectItem>();
			List<Proveedor> proveedorList = proveedorRetencionServicio.consultarProveedores();

			for (Proveedor proveedor : proveedorList) {
				proveedorItems.add(new SelectItem(proveedor.getProveedorid(),
						proveedor.getProveedoridentificacion() + "- " + proveedor.getProveedornombre()));

			}
		}

		return proveedorItems;
	}

	public void setProveedorItems(List<SelectItem> proveedorItems) {
		this.proveedorItems = proveedorItems;
	}

	public List<SelectItem> getTipoPagoItems() {
		if (!listingPagoTipo) {
			listingPagoTipo = true;

			tipoPagoItems = new ArrayList<SelectItem>();
			List<PagoTipo> tipoList = proveedorRetencionServicio.consultarTiposPago();

			for (PagoTipo pagoTipo : tipoList) {
				tipoPagoItems.add(new SelectItem(pagoTipo.getPagoTipoid(),
						pagoTipo.getPagoTipoNumero() + " - " + pagoTipo.getPagoTiponombre()));

			}
		}

		return tipoPagoItems;
	}

	public void setTipoPagoItems(List<SelectItem> tipoPagoItems) {
		this.tipoPagoItems = tipoPagoItems;
	}

	public Long getPagoTipoid() {
		return pagoTipoid;
	}

	public void setPagoTipoid(Long pagoTipoid) {
		this.pagoTipoid = pagoTipoid;
	}

	public List<SelectItem> getImpuestoItems() {
		if (!listingImpuestoConcepto) {
			listingImpuestoConcepto = true;

			impuestoItems = new ArrayList<SelectItem>();
			List<ImpuestoConcepto> impuestoList = proveedorRetencionServicio.consultarTiposConceptos();

			for (ImpuestoConcepto impuesto : impuestoList) {
				impuestoItems.add(new SelectItem(impuesto.getImpuestoconceptoid(),
						impuesto.getImpuestoCodigo() + " - " + impuesto.getImpuestoconceptonombre()));
			}
		}
		return impuestoItems;
	}

	public void setImpuestoItems(List<SelectItem> impuestoItems) {
		this.impuestoItems = impuestoItems;
	}

	public Long getImpuestoConceptoid() {
		return impuestoConceptoid;
	}

	public void setImpuestoConceptoid(Long impuestoConceptoid) {
		this.impuestoConceptoid = impuestoConceptoid;
	}

	public Long getProveedorid() {
		return proveedorid;
	}

	public void setProveedorid(Long proveedorid) {
		this.proveedorid = proveedorid;
	}

	public List<Proveedor> getListaProveedor() {
		if (!listingProveedor) {

			listingProveedor = true;
			listaProveedor = proveedorRetencionServicio.consultarProveedores();
		}
		return listaProveedor;
	}

	public void setListaProveedor(List<Proveedor> listaProveedor) {
		this.listaProveedor = listaProveedor;
	}

	public Proveedor getProveedorSeleccionado() {
		return proveedorSeleccionado;
	}

	public void setProveedorSeleccionado(Proveedor proveedorSeleccionado) {
		this.proveedorSeleccionado = proveedorSeleccionado;
	}

}