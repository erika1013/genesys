package com.guandera.talento.empleado.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.core.client.service.TipoContratoService;
import com.guandera.talento.empleado.shared.model.TipoContrato;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class TipoContratoServiceImpl implements TipoContratoService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(TipoContrato tipoContrato) {
		ofy().save().entity(tipoContrato).now();
	}

	@Override
	public TipoContrato consultarPorId(Long id) {
		Key<TipoContrato> k = Key.create(TipoContrato.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<TipoContrato> consultarTodos() {
		return ofy().load().type(TipoContrato.class).list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(TipoContrato.class).count();
		return cont;

	}

	@Override
	public void crear(TipoContrato tipoContrato) {
		ofy().save().entity(tipoContrato).now();
	}

	@Override
	public void eliminar(TipoContrato tipoContrato) {
		ofy().delete().entity(tipoContrato).now();

	}

	@Override
	public Long siguienteRegistro() {

		long siguiente = ofy().load().type(TipoContrato.class).count();
		return siguiente + 1;
	}
}