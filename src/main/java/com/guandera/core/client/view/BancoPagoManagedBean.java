package com.guandera.core.client.view;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.guandera.core.client.service.BancoPagoService;
import com.guandera.core.server.service.BancoPagoServiceImpl;
import com.guandera.core.shared.model.BancoConciliacion;
import com.guandera.core.shared.model.BancoPago;
import com.guandera.core.shared.model.BancoPagoEstado;

@ManagedBean(name = "bancoPagoMB")
@SessionScoped
public class BancoPagoManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private BancoPagoService bancoPagoServicio;
	private BancoPago itemSeleccionado;

	private BancoConciliacion itemConciliacion;

	private List<BancoPago> listaItems;
	private boolean listing = false;

	private Integer nroRecibo;

	private Integer tipoMovimiento;

	private boolean listarMovimientos = false;

	private Long estadoid;
	private List<SelectItem> estadoItems;
	private boolean listingEstado = false;

	public BancoPagoManagedBean() {
		bancoPagoServicio = new BancoPagoServiceImpl();

		inicializar();
	}

	public String actualizar() {

		try {
			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			bancoPagoServicio.actualizar(itemSeleccionado);
			mensajeInfo("BancoPagoUpdated");
			inicializar();
			verificarLista();
			return prepararLista();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String conciliarPago() {

		try {

			// if (bancoPagoServicio.existeRecibo(nroRecibo)) {

			itemConciliacion.setUsuarioCreacion(usuario());
			itemConciliacion.setFechaCreacion(new Date());
			itemConciliacion.setUsuarioModificacion(usuario());
			itemConciliacion.setFechaModificacion(new Date());

			itemConciliacion.setPago(itemSeleccionado);
			itemConciliacion.setValorTotal(itemSeleccionado.getValorTotal());
			itemConciliacion.setFechaPago(itemSeleccionado.getFechaProceso());

			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			bancoPagoServicio.conciliarPago(itemConciliacion, itemSeleccionado, usuario());

			mensaje("Pago Conciliado");

			return "ConciliarOK";

			// } else {

			// error("Error: N�mero de Recibo no exite!!");
			// return null;
			// }

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

			bancoPagoServicio.crear(itemSeleccionado);
			mensajeInfo("BancoPagoCreated");
			inicializar();
			verificarLista();
			return prepararLista();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String eliminar() {

		bancoPagoServicio.eliminar(itemSeleccionado);
		listing = false;
		return prepararLista();
	}

	public BancoPago getItemSeleccionado() {
		return itemSeleccionado;
	}

	public List<BancoPago> getListaItems() {

		// if (!listing) {
		// listing = true;
		// listaItems = bancoPagoServicio.consultarTodos();
		// }
		return listaItems;
	}

	public BancoPagoService getBancoPagoServicio() {
		return bancoPagoServicio;
	}

	private void inicializar() {

		itemSeleccionado = new BancoPago();

	}

	public boolean isListing() {
		return listing;
	}

	public void listarBancoPago() {
		setListing(true);
		listaItems = bancoPagoServicio.consultarTodos();
	}

	public String prepararConsulta() {
		return "Detalle";
	}

	public String prepararCreacion() {
		itemSeleccionado = new BancoPago();
		return "Crear";
	}

	public String prepararEdicion() {
		return "Editar";
	}

	public String prepararConciliacion() {

		itemConciliacion = new BancoConciliacion();
		return "Conciliar";
	}

	public String prepararLista() {
		return "Lista";
	}

	@PostConstruct
	public void prerender() {
		inicializar();

	}

	public String buscarTipoMovimiento() {

		listaItems = new ArrayList<BancoPago>();

		setListarMovimientos(false);

		try {
			if (tipoMovimiento != 0) {

				this.listaItems = bancoPagoServicio.consultarMovimientoEstado(tipoMovimiento, estadoid);
				setListarMovimientos(true);
				return "Lista";

			} else {
				setListarMovimientos(false);
				return "Lista";
			}
		} catch (Exception e) {
			mensaje("No Existen movimientos");
			return null;
		}

	}

	public String exportarMovimiento() {

		listaItems = new ArrayList<BancoPago>();

		setListarMovimientos(false);

		try {
			if (tipoMovimiento != 0) {

				this.listaItems = bancoPagoServicio.consultarMovimientoEstado(tipoMovimiento, estadoid);

				exportMovimiento();

				listaItems = new ArrayList<BancoPago>();
				return null;

			} else {
				setListarMovimientos(false);
				return "Lista";
			}
		} catch (Exception e) {
			mensaje("No Existen movimientos");
			return null;
		}

	}

	public void exportMovimiento() {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {

			String encabezado = "Fecha Sistema;Hora del Sistema;Valor Transacci�n;  Referencia1; Referencia 2; Estado\n";
			String coma = ";";
			String salto = "\n";

			byte[] exportContent = encabezado.getBytes();

			baos.write(exportContent);

			for (BancoPago pago : listaItems) {

				String texto;

				texto = pago.getFechaSistema() + coma + pago.getHoraSistema() + coma + pago.getValorTotal() + coma
						+ pago.getReferencia1() + coma + pago.getReferencia2() + coma
						+ pago.getEstado().getBancoPagoEstadonombre() + salto;

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
			// hsr.setHeader("Content-disposition", "attachment");
			// hsr.setHeader("Content-disposition",
			// "inline=filename=resumen.txt");
			hsr.setHeader("Content-Disposition", "attachment;filename=InformeRecaudo.csv");

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

	public void setItemSeleccionado(BancoPago itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public void setListaItems(List<BancoPago> listaItems) {
		this.listaItems = listaItems;
	}

	public void setListing(boolean listing) {
		this.listing = listing;
	}

	public void setBancoPagoServicio(BancoPagoService bancoPagoServicio) {
		this.bancoPagoServicio = bancoPagoServicio;
	}

	private void verificarLista() {
		if (listing) {
			this.listaItems = bancoPagoServicio.consultarTodos();
		}
	}

	public Integer getNroRecibo() {
		return nroRecibo;
	}

	public void setNroRecibo(Integer nroRecibo) {
		this.nroRecibo = nroRecibo;
	}

	public Integer getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(Integer tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	public boolean isListarMovimientos() {
		return listarMovimientos;
	}

	public void setListarMovimientos(boolean listarMovimientos) {
		this.listarMovimientos = listarMovimientos;
	}

	public Long getEstadoid() {
		return estadoid;
	}

	public void setEstadoid(Long estadoid) {
		this.estadoid = estadoid;
	}

	public List<SelectItem> getEstadoItems() {

		if (!listingEstado) {
			listingEstado = true;
			estadoItems = new ArrayList<SelectItem>();

			List<BancoPagoEstado> estadoList = bancoPagoServicio.consultarBancoEstados();

			for (BancoPagoEstado estado : estadoList) {
				estadoItems.add(new SelectItem(estado.getBancoPagoEstadoid(), estado.getBancoPagoEstadonombre()));

			}
		}

		return estadoItems;
	}

	public void setEstadoItems(List<SelectItem> estadoItems) {
		this.estadoItems = estadoItems;
	}

	public BancoConciliacion getItemConciliacion() {
		return itemConciliacion;
	}

	public void setItemConciliacion(BancoConciliacion itemConciliacion) {
		this.itemConciliacion = itemConciliacion;
	}

}
