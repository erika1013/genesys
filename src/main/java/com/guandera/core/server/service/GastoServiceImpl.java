package com.guandera.core.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.core.client.service.GastoService;
import com.guandera.core.shared.model.Gasto;
import com.guandera.core.shared.model.GastoConcepto;
import com.guandera.core.shared.model.GastoSubConcepto;
import com.guandera.core.shared.model.Proveedor;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class GastoServiceImpl implements GastoService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(Gasto gasto) {

		gasto.setConceptoCodigo(gasto.getConcepto().getGastoConceptocodigo());
		gasto.setSubConceptoCodigo(gasto.getSubConcepto().getCodigo());
		ofy().save().entity(gasto).now();
	}

	@Override
	public Gasto consultarPorId(Long id) {
		Key<Gasto> k = Key.create(Gasto.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<Gasto> consultarTodos() {
		return ofy().load().type(Gasto.class).list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(Gasto.class).count();
		return cont;

	}

	@Override
	public void crear(Gasto gasto) {

		gasto.setConceptoCodigo(gasto.getConcepto().getGastoConceptocodigo());
		gasto.setSubConceptoCodigo(gasto.getSubConcepto().getCodigo());

		ofy().save().entity(gasto).now();
	}

	@Override
	public void eliminar(Gasto gasto) {
		ofy().delete().entity(gasto).now();

	}

	@Override
	public Long siguienteRegistro() {

		long siguiente = ofy().load().type(Gasto.class).count();
		return siguiente + 1;
	}

	@Override
	public List<Proveedor> consultarProveedores() {
		return ofy().load().type(Proveedor.class).list();
	}

	@Override
	public List<GastoConcepto> consultarTiposGasto() {
		return ofy().load().type(GastoConcepto.class).list();
	}

	@Override
	public List<GastoSubConcepto> consultarSubConceptos(Long conceptoid) {

		Key<GastoConcepto> kConcepto = Key.create(GastoConcepto.class, conceptoid);

		List<GastoSubConcepto> listaSubConceptos = ofy().load().type(GastoSubConcepto.class)
				.filter("concepto", kConcepto).list();

		return listaSubConceptos;
	}
}