package com.guandera.talento.aspirante.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.talento.aspirante.client.service.AspiranteService;
import com.guandera.talento.aspirante.shared.model.Aspirante;
import com.guandera.talento.aspirante.shared.model.AspiranteAcceso;
import com.guandera.talento.aspirante.shared.model.AspiranteAutorizacion;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class AspiranteServiceImpl implements AspiranteService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(Aspirante aspirante) {
		ofy().save().entity(aspirante).now();
	}

	@Override
	public Aspirante consultarPorId(Long id) {
		Key<Aspirante> k = Key.create(Aspirante.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<Aspirante> consultarTodos() {
		return ofy().load().type(Aspirante.class).order("-codigo").list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(Aspirante.class).count();
		return cont;

	}

	@Override
	public void crear(Aspirante aspirante) {
		ofy().save().entity(aspirante).now();
	}

	@Override
	public void eliminar(Aspirante aspirante) {
		ofy().delete().entity(aspirante).now();

	}

	@Override
	public Integer siguienteCodigoAspirante() {

		Integer siguiente = 0;

		Aspirante aspirante = new Aspirante();

		try {
			aspirante = ofy().load().type(Aspirante.class).order("-codigo").first().now();
			siguiente = aspirante.getCodigo();
		} catch (Exception e) {

			siguiente = 0;
		}

		return siguiente + 1;
	}

	@Override
	public void crearCuentaAspirante(Aspirante aspirante, AspiranteAcceso aspiranteAcceso) {
		
		aspirante.setCodigo(siguienteCodigoAspirante());
		ofy().save().entity(aspirante).now();
		ofy().save().entity(aspiranteAcceso).now();
		
		AspiranteAutorizacion aspiranteAutorizacion = new AspiranteAutorizacion();
		
		aspiranteAutorizacion.setAspirante(aspirante);
		aspiranteAutorizacion.setAspiranteAcceso(aspiranteAcceso);
		
		ofy().save().entity(aspiranteAutorizacion).now();
		
		
		
	}

	@Override
	public boolean existeUsuario(String usuario) {
		AspiranteAcceso acceso = ofy().load().type(AspiranteAcceso.class).filter("correo", usuario).first().now();

		if (acceso != null) {
			return true;
		} else {
			return false;
		}
	}

}