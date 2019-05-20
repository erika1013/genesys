package com.guandera.core.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.core.client.service.ReciboSecuenciaService;
import com.guandera.core.shared.model.ReciboSecuencia;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class ReciboSecuenciaServiceImpl implements ReciboSecuenciaService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(ReciboSecuencia reciboSecuencia) {
		ofy().save().entity(reciboSecuencia).now();
	}

	@Override
	public ReciboSecuencia consultarPorId(Long id) {
		Key<ReciboSecuencia> k = Key.create(ReciboSecuencia.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<ReciboSecuencia> consultarTodos() {
		return ofy().load().type(ReciboSecuencia.class).list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(ReciboSecuencia.class).count();
		return cont;

	}

	@Override
	public void crear(ReciboSecuencia reciboSecuencia) {
		ofy().save().entity(reciboSecuencia).now();
	}

	@Override
	public void eliminar(ReciboSecuencia reciboSecuencia) {
		ofy().delete().entity(reciboSecuencia).now();

	}

	@Override
	public Long siguienteRegistro() {

		long siguiente = ofy().load().type(ReciboSecuencia.class).count();
		return siguiente + 1;
	}
}