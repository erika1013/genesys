package com.guandera.core.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.core.shared.model.PagoTipo;
import com.guandera.talento.nomina.client.service.PagoTipoService;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class PagoTipoServiceImpl implements PagoTipoService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(PagoTipo pagoTipo) {
		ofy().save().entity(pagoTipo).now();
	}

	@Override
	public PagoTipo consultarPorId(Long id) {
		Key<PagoTipo> k = Key.create(PagoTipo.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<PagoTipo> consultarTodos() {
		return ofy().load().type(PagoTipo.class).list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(PagoTipo.class).count();
		return cont;

	}

	@Override
	public void crear(PagoTipo pagoTipo) {
		ofy().save().entity(pagoTipo).now();
	}

	@Override
	public void eliminar(PagoTipo pagoTipo) {
		ofy().delete().entity(pagoTipo).now();

	}

	@Override
	public Long siguienteRegistro() {

		PagoTipo pagoTipo = new PagoTipo();
		long siguiente = 0;

		try {
			pagoTipo = ofy().load().type(PagoTipo.class).order("-pagoTipoNumero").first().now();
			siguiente = pagoTipo.getPagoTipoNumero();
		} catch (Exception e) {

			siguiente = 0;
		}

		return siguiente + 1;
	}
}