package com.guandera.core.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.core.client.service.GastoConceptoService;
import com.guandera.core.shared.model.GastoConcepto;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class GastoConceptoServiceImpl implements GastoConceptoService, Serializable {

	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(GastoConcepto gastoConcepto) {
		ofy().save().entity(gastoConcepto).now();
	}

	@Override
	public GastoConcepto consultarPorId(Long id) {
		Key<GastoConcepto> k = Key.create(GastoConcepto.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<GastoConcepto> consultarTodos() {
		return ofy().load().type(GastoConcepto.class).order("gastoConceptocodigo").list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(GastoConcepto.class).count();
		return cont;

	}

	@Override
	public void crear(GastoConcepto gastoConcepto) {
		ofy().save().entity(gastoConcepto).now();
	}

	@Override
	public void eliminar(GastoConcepto gastoConcepto) {
		ofy().delete().entity(gastoConcepto).now();

	}

	@Override
	public Long siguienteRegistro() {

		long siguiente = ofy().load().type(GastoConcepto.class).count();
		return siguiente + 1;
	}

	@Override
	public boolean exiteCodigo(Integer conceptocodigo) {

		GastoConcepto concepto = ofy().load().type(GastoConcepto.class).filter("gastoConceptocodigo", conceptocodigo)
				.first().now();

		if (concepto != null) {
			return true;
		} else {
			return false;
		}
	}
}