package com.guandera.talento.empleado.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.core.client.service.EmpresaSeguridadSocialService;
import com.guandera.talento.empleado.shared.model.EmpresaSeguridadSocial;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class EmpresaSeguridadSocialServiceImpl implements EmpresaSeguridadSocialService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(EmpresaSeguridadSocial empresaSeguridadSocial) {
		ofy().save().entity(empresaSeguridadSocial).now();
	}

	@Override
	public EmpresaSeguridadSocial consultarPorId(Long id) {
		Key<EmpresaSeguridadSocial> k = Key.create(EmpresaSeguridadSocial.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<EmpresaSeguridadSocial> consultarTodos() {
		return ofy().load().type(EmpresaSeguridadSocial.class).list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(EmpresaSeguridadSocial.class).count();
		return cont;

	}

	@Override
	public void crear(EmpresaSeguridadSocial empresaSeguridadSocial) {
		ofy().save().entity(empresaSeguridadSocial).now();
	}

	@Override
	public void eliminar(EmpresaSeguridadSocial empresaSeguridadSocial) {
		ofy().delete().entity(empresaSeguridadSocial).now();

	}

	@Override
	public Long siguienteRegistro() {

		long siguiente = ofy().load().type(EmpresaSeguridadSocial.class).count();
		return siguiente + 1;
	}
}