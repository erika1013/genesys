package com.guandera.core.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.core.client.service.CentroCostosService;
import com.guandera.core.shared.model.CentroCostos;
import com.guandera.core.shared.model.Compania;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class CentroCostosServiceImpl implements CentroCostosService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(CentroCostos itemCentroCostos) {
		ofy().save().entity(itemCentroCostos).now();
	}

	@Override
	public CentroCostos consultarPorId(Long id) {
		Key<CentroCostos> k = Key.create(CentroCostos.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<CentroCostos> consultarTodos() {
		return ofy().load().type(CentroCostos.class).list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(CentroCostos.class).count();
		return cont;

	}

	@Override
	public void crear(CentroCostos itemCentroCostos) {

		ofy().save().entity(itemCentroCostos).now();
	}

	@Override
	public void eliminar(CentroCostos itemCentroCostos) {
		ofy().delete().entity(itemCentroCostos).now();

	}

	@Override
	public Long siguienteRegistro() {

		long siguiente = ofy().load().type(CentroCostos.class).count();
		return siguiente + 1;
	}

	@Override
	public List<Compania> consultarCompanias() {
		// TODO Auto-generated method stub
		return ofy().load().type(Compania.class).list();
	}

	@Override
	public boolean existeCentroCostos(String codigo) {
		CentroCostos centro = ofy().load().type(CentroCostos.class).filter("codigo", codigo).first().now();
		if (centro != null) {
			return true;
		} else {
			return false;
		}

	}

}