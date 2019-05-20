package com.guandera.core.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.core.client.service.ProveedorRetencionService;
import com.guandera.core.shared.model.ImpuestoConcepto;
import com.guandera.core.shared.model.PagoTipo;
import com.guandera.core.shared.model.Proveedor;
import com.guandera.core.shared.model.ProveedorRetencion;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class ProveedorRetencionServiceImpl implements ProveedorRetencionService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(ProveedorRetencion proveedorRetencion) {
		ofy().save().entity(proveedorRetencion).now();
	}

	@Override
	public ProveedorRetencion consultarPorId(Long id) {
		Key<ProveedorRetencion> k = Key.create(ProveedorRetencion.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<ProveedorRetencion> consultarTodos() {
		return ofy().load().type(ProveedorRetencion.class).list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(ProveedorRetencion.class).count();
		return cont;

	}

	@Override
	public void crear(ProveedorRetencion proveedorRetencion) {
		ofy().save().entity(proveedorRetencion).now();
	}

	@Override
	public void eliminar(ProveedorRetencion proveedorRetencion) {
		ofy().delete().entity(proveedorRetencion).now();

	}

	@Override
	public Long siguienteRegistro() {

		ProveedorRetencion proveedorRetencion = new ProveedorRetencion();
		long siguiente = 0;

		try {
			proveedorRetencion = ofy().load().type(ProveedorRetencion.class).order("-codigoRetencion").first().now();
			siguiente = proveedorRetencion.getCodigoRetencion();
		} catch (Exception e) {

			siguiente = 0;
		}

		return siguiente + 1;
	}

	@Override
	public List<Proveedor> consultarProveedores() {

		return ofy().load().type(Proveedor.class).list();
	}

	@Override
	public List<PagoTipo> consultarTiposPago() {
		// TODO Auto-generated method stub
		return ofy().load().type(PagoTipo.class).list();
	}

	@Override
	public List<ImpuestoConcepto> consultarTiposConceptos() {
		// TODO Auto-generated method stub
		return ofy().load().type(ImpuestoConcepto.class).list();
	}
}