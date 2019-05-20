package com.guandera.core.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.core.client.service.ConvenioService;
import com.guandera.core.shared.model.Convenio;
import com.guandera.core.shared.model.Sede;
import com.guandera.core.shared.model.TipoServicio;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class ConvenioServiceImpl implements ConvenioService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(Convenio convenio) {
		ofy().save().entity(convenio).now();
	}

	@Override
	public Convenio consultarPorId(Long id) {
		Key<Convenio> k = Key.create(Convenio.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<Convenio> consultarTodos() {
		return ofy().load().type(Convenio.class).list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(Convenio.class).count();
		return cont;

	}

	@Override
	public void crear(Convenio convenio) {
		ofy().save().entity(convenio).now();
	}

	@Override
	public void eliminar(Convenio convenio) {
		ofy().delete().entity(convenio).now();

	}

	@Override
	public Long siguienteRegistro() {

		long siguiente = ofy().load().type(Convenio.class).count();
		return siguiente + 1;
	}

	@Override
	public List<Sede> consultarSedes() {
		return ofy().load().type(Sede.class).list();
	}

	@Override
	public List<TipoServicio> consultarTiposConvenio() {
		return ofy().load().type(TipoServicio.class).list();
	}
}