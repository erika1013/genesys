package com.guandera.core.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.core.client.service.FacturaService;
import com.guandera.core.shared.model.CentroCostos;
import com.guandera.core.shared.model.Cliente;
import com.guandera.core.shared.model.ClienteContacto;
import com.guandera.core.shared.model.Compania;
import com.guandera.core.shared.model.CompaniaImpuesto;
import com.guandera.core.shared.model.Factura;
import com.guandera.core.shared.model.FacturaDetalle;
import com.guandera.core.shared.model.FacturaEstado;
import com.guandera.core.shared.model.Sede;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class FacturaServiceImpl implements FacturaService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(Factura factura) {
		ofy().save().entity(factura).now();
	}

	@Override
	public Factura consultarPorId(Long id) {
		Key<Factura> k = Key.create(Factura.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<Factura> consultarTodos() {
		return ofy().load().type(Factura.class).list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(Factura.class).count();
		return cont;

	}

	@Override
	public void crear(Factura factura) {

		String codigo = siguienteFactura(factura.getCompania());

		factura.setFaturacodigo(codigo);

		ofy().save().entity(factura).now();

	}

	private String siguienteFactura(Compania compania) {

		CompaniaImpuesto facturacion = ofy().load().type(CompaniaImpuesto.class).filter("compania", compania).first()
				.now();
		int numero = facturacion.getNumeroActual() + 1;
		DecimalFormat formato = new DecimalFormat("0000");

		String codigo = facturacion.getLetra().trim() + "-" + formato.format(numero);

		facturacion.setNumeroActual(numero);

		ofy().save().entity(facturacion).now();
		return codigo;
	}

	@Override
	public void eliminar(Factura factura) {
		ofy().delete().entity(factura).now();

	}

	@Override
	public Long siguienteRegistro() {
		return null;

	}

	@Override
	public List<Cliente> consultarCliente() {

		return ofy().load().type(Cliente.class).list();

	}

	// contrato Detalle.

	@Override
	public List<FacturaDetalle> consultarFacturaDetalle(Long facturaid) {
		Key<Factura> kFactura = Key.create(Factura.class, facturaid);

		List<FacturaDetalle> listaFacturaDetalle = new ArrayList<FacturaDetalle>();

		listaFacturaDetalle = ofy().load().type(FacturaDetalle.class).filter("factura", kFactura).list();

		return listaFacturaDetalle;
	}

	@Override
	public void crearFacturaDetalle(FacturaDetalle facturaDetalle) {
		ofy().save().entity(facturaDetalle).now();
		facturaDetalle.setValorTotal(facturaDetalle.getValorUnitario() * facturaDetalle.getCantidad());
		ofy().save().entity(facturaDetalle).now();

		Key<Factura> kFactura = Key.create(Factura.class, facturaDetalle.getFactura().getFacturaid());

		Factura factura = ofy().load().key(kFactura).now();

		System.out.print("factura  " + factura);

		// factura.setFacturavalor(factura.getFacturavalor()
		// + facturaDetalle.getValorTotal());

		ofy().save().entities(factura).now();

		// factura.setIvavalor((factura.getFacturavalor() *
		// factura.getIvafactor()) / 100);
		// ofy().save().entities(factura).now();
		// factura.setFacturavalortotal(factura.getFacturavalor()
		// + factura.getIvavalor());
		// ofy().save().entities(factura).now();

	}

	@Override
	public void actualizarFacturaDetalle(FacturaDetalle facturaDetalle) {
		ofy().save().entity(facturaDetalle).now();

	}

	@Override
	public void eliminarFacturaDetalle(FacturaDetalle facturaDetalle) {
		ofy().delete().entity(facturaDetalle).now();

	}

	@Override
	public List<FacturaEstado> consultarEstados() {

		return ofy().load().type(FacturaEstado.class).list();
	}

	@Override
	public List<Compania> consultarCompanias() {
		return ofy().load().type(Compania.class).list();
	}

	@Override
	public List<Sede> consultarSedes(Long companiaid) {
		Key<Compania> kCompania = Key.create(Compania.class, companiaid);

		List<Sede> listaSede = new ArrayList<Sede>();

		listaSede = ofy().load().type(Sede.class).filter("compania", kCompania).list();

		return listaSede;

	}

	@Override
	public List<ClienteContacto> consultarContactosPorCliente(Long clienteid) {
		Key<Cliente> kCliente = Key.create(Cliente.class, clienteid);

		List<ClienteContacto> listaContactos = new ArrayList<ClienteContacto>();

		listaContactos = ofy().load().type(ClienteContacto.class).filter("cliente", kCliente).list();

		return listaContactos;
	}

	@Override
	public List<CentroCostos> consultarCentrosCostosPorCompania(Long companiaid) {
		Key<Compania> kCompania = Key.create(Compania.class, companiaid);

		List<CentroCostos> listaCentroCostos = new ArrayList<CentroCostos>();

		listaCentroCostos = ofy().load().type(CentroCostos.class).filter("compania", kCompania).list();

		return listaCentroCostos;
	}

}