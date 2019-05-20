package com.guandera.talento.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.core.shared.model.Departamento;
import com.guandera.talento.client.service.DepartamentoService;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class DepartamentoServiceImpl implements DepartamentoService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(Departamento departamento) {
		ofy().save().entity(departamento).now();
	}

	@Override
	public Departamento consultarPorId(Long id) {
		Key<Departamento> k = Key.create(Departamento.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<Departamento> consultarTodos() {
		return ofy().load().type(Departamento.class).order("codigo").list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(Departamento.class).count();
		return cont;

	}

	@Override
	public void crear(Departamento departamento) {
		ofy().save().entity(departamento).now();
	}

	@Override
	public void eliminar(Departamento departamento) {
		ofy().delete().entity(departamento).now();

	}

}