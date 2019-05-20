package com.guandera.talento.aspirante.server.service;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.Serializable;

import com.guandera.talento.aspirante.client.service.LoginAspiranteService;
import com.guandera.talento.aspirante.shared.model.Aspirante;
import com.guandera.talento.aspirante.shared.model.AspiranteAcceso;
import com.guandera.talento.aspirante.shared.model.AspiranteAutorizacion;

/**
 * 
 * @author Fredi Javier Velasco Villarreal-GUANDERA S.A.S.
 */

public class LoginAspiranteServiceImpl implements LoginAspiranteService, Serializable {

	private static final long serialVersionUID = -5158903422244484923L;

	@Override
	public boolean existeUsuario(String usuario) {

		AspiranteAcceso acceso = ofy().load().type(AspiranteAcceso.class).filter("correo", usuario).first().now();

		if (acceso != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean verificarAspiranteAcceso(AspiranteAcceso acceso) {

		
		System.out.println("FJVV 021 clave: "+acceso.getClave());
		
		AspiranteAcceso acceso1 = ofy().load().type(AspiranteAcceso.class).filter("correo", acceso.getCorreo().trim())
				.filter("clave", acceso.getClave()).first().now();

		
		if (acceso1 != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public AspiranteAcceso CargarAspiranteAcceso(AspiranteAcceso acceso) {
		return ofy().load().type(AspiranteAcceso.class).filter("correo", acceso.getCorreo()).first().now();
	}

	@Override
	public void actualizarUtimoAcceso(AspiranteAcceso aspiranteAcceso) {
		ofy().save().entity(aspiranteAcceso).now();

	}

	@Override
	public Aspirante CargarAspirante(AspiranteAcceso aspiranteAcceso) {

		AspiranteAutorizacion aspiranteAutorizacion = ofy().load().type(AspiranteAutorizacion.class)
				.filter("aspiranteAcceso", aspiranteAcceso).first().now();

		return aspiranteAutorizacion.getAspirante();
	}

}
