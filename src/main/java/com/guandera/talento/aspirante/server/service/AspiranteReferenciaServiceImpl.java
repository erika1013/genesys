package com.guandera.talento.aspirante.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.talento.aspirante.client.service.AspiranteReferenciaService;
import com.guandera.talento.aspirante.shared.model.Aspirante;
import com.guandera.talento.aspirante.shared.model.AspiranteReferencia;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class AspiranteReferenciaServiceImpl implements AspiranteReferenciaService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(AspiranteReferencia aspiranteReferencia) {
		ofy().save().entity(aspiranteReferencia).now();
	}

	@Override
	public AspiranteReferencia consultarPorId(Long id) {
		Key<AspiranteReferencia> k = Key.create(AspiranteReferencia.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<AspiranteReferencia> consultarTodos() {
		return ofy().load().type(AspiranteReferencia.class).list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(AspiranteReferencia.class).count();
		return cont;

	}

	@Override
	public void crear(AspiranteReferencia aspiranteReferencia) {
		ofy().save().entity(aspiranteReferencia).now();
	}

	@Override
	public void eliminar(AspiranteReferencia aspiranteReferencia) {
		ofy().delete().entity(aspiranteReferencia).now();

	}

	@Override
	public List<AspiranteReferencia> consultarReferenciasAspirante(Aspirante aspirante) {
		return ofy().load().type(AspiranteReferencia.class).filter("aspirante", aspirante).list();
	}

}