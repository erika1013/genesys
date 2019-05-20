package com.guandera.talento.nomina.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.talento.nomina.client.service.NominaConceptoService;
import com.guandera.talento.nomina.shared.model.NominaConcepto;
import com.guandera.talento.nomina.shared.model.NominaConceptoTipo;

/**
 * 
 * @author FrediJavier
 */
public class NominaConceptoServiceImpl implements NominaConceptoService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3819355947773691945L;

	@Override
	public void actualizar(NominaConcepto nominaConcepto) {
		ofy().save().entity(nominaConcepto).now();
	}

	@Override
	public NominaConcepto consultarPorId(Long id) {
		Key<NominaConcepto> k = Key.create(NominaConcepto.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<NominaConceptoTipo> consultarConceptoTipo() {

		return ofy().load().type(NominaConceptoTipo.class).list();
	}

	@Override
	public List<NominaConcepto> consultarTodos() {

		return ofy().load().type(NominaConcepto.class).order("nominaconceptocodigo").list();

	}

	@Override
	public long contar() {

		long cont = ofy().load().type(NominaConcepto.class).count();
		return cont;
	}

	@Override
	public void crear(NominaConcepto nominaConcepto) {
		ofy().save().entity(nominaConcepto).now();
	}

	@Override
	public void eliminar(NominaConcepto nominaConcepto) {
		ofy().delete().entity(nominaConcepto).now();
	}

	@Override
	public Long siguienteRegistro() {

		long siguiente = ofy().load().type(NominaConcepto.class).count();
		return siguiente + 1;

	}
}