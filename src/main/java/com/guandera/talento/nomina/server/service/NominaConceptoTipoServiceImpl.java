package com.guandera.talento.nomina.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.talento.nomina.client.service.NominaConceptoTipoService;
import com.guandera.talento.nomina.shared.model.NominaConceptoTipo;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class NominaConceptoTipoServiceImpl implements NominaConceptoTipoService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(NominaConceptoTipo nominaConceptoTipo) {
		ofy().save().entity(nominaConceptoTipo).now();
	}

	@Override
	public NominaConceptoTipo consultarPorId(Long id) {
		Key<NominaConceptoTipo> k = Key.create(NominaConceptoTipo.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<NominaConceptoTipo> consultarTodos() {
		return ofy().load().type(NominaConceptoTipo.class).list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(NominaConceptoTipo.class).count();
		return cont;

	}

	@Override
	public void crear(NominaConceptoTipo nominaConceptoTipo) {
		ofy().save().entity(nominaConceptoTipo).now();
	}

	@Override
	public void eliminar(NominaConceptoTipo nominaConceptoTipo) {
		ofy().delete().entity(nominaConceptoTipo).now();

	}

	@Override
	public Long siguienteRegistro() {

		long siguiente = ofy().load().type(NominaConceptoTipo.class).count();
		return siguiente + 1;
	}
}