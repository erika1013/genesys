package com.guandera.core.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.core.client.service.ImpuestoConceptoService;
import com.guandera.core.shared.model.CuentaCobro;
import com.guandera.core.shared.model.ImpuestoConcepto;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class ImpuestoConceptoServiceImpl implements ImpuestoConceptoService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(ImpuestoConcepto impuestoConcepto) {
		ofy().save().entity(impuestoConcepto).now();
	}

	@Override
	public ImpuestoConcepto consultarPorId(Long id) {
		Key<ImpuestoConcepto> k = Key.create(ImpuestoConcepto.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<ImpuestoConcepto> consultarTodos() {
		return ofy().load().type(ImpuestoConcepto.class).list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(ImpuestoConcepto.class).count();
		return cont;

	}

	@Override
	public void crear(ImpuestoConcepto impuestoConcepto) {
		ofy().save().entity(impuestoConcepto).now();

		if (impuestoConcepto.getObligacionde() == 1) {
			impuestoConcepto.setObligacion("Empresa");
		} else if (impuestoConcepto.getObligacionde() == 2) {
			impuestoConcepto.setObligacion("Proveedor");
		}

		ofy().save().entity(impuestoConcepto).now();

	}

	@Override
	public void eliminar(ImpuestoConcepto impuestoConcepto) {
		ofy().delete().entity(impuestoConcepto).now();

	}

	@Override
	public Long siguienteRegistro() {
		ImpuestoConcepto impuestoConcepto = new ImpuestoConcepto();

		long siguiente = 0;

		try {
			impuestoConcepto = ofy().load().type(ImpuestoConcepto.class).order("-impuestoCodigo").first().now();
			siguiente = impuestoConcepto.getImpuestoCodigo();
		} catch (Exception e) {
			siguiente = 0;
		}

		return siguiente + 1;

	}
}