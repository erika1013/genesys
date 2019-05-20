package com.guandera.core.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.core.client.service.GastoSubConceptoService;
import com.guandera.core.shared.model.GastoConcepto;
import com.guandera.core.shared.model.GastoSubConcepto;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class GastoSubConceptoServiceImpl implements GastoSubConceptoService, Serializable {

	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(GastoSubConcepto subConcepto) {
		ofy().save().entity(subConcepto).now();
	}

	@Override
	public GastoSubConcepto consultarPorId(Long id) {
		Key<GastoSubConcepto> k = Key.create(GastoSubConcepto.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<GastoSubConcepto> consultarTodos() {
		return ofy().load().type(GastoSubConcepto.class).order("codigo").list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(GastoSubConcepto.class).count();
		return cont;

	}

	@Override
	public void crear(GastoSubConcepto subConcepto) {
		ofy().save().entity(subConcepto).now();
	}

	@Override
	public void eliminar(GastoSubConcepto subConcepto) {
		ofy().delete().entity(subConcepto).now();

	}

	@Override
	public Long siguienteRegistro() {

		long siguiente = ofy().load().type(GastoSubConcepto.class).count();
		return siguiente + 1;
	}

	@Override
	public List<GastoConcepto> consultarConceptos() {
		return ofy().load().type(GastoConcepto.class).list();
	}

	@Override
	public Integer siguienteSubConcepto() {

		Integer siguiente = 0;

		try {

			GastoSubConcepto subConcepto = new GastoSubConcepto();
			subConcepto = ofy().load().type(GastoSubConcepto.class).order("-codigo").first().now();
			siguiente = subConcepto.getCodigo() + 1;

		} catch (Exception e) {

			siguiente = 1;
		}

		return siguiente;

	}
}