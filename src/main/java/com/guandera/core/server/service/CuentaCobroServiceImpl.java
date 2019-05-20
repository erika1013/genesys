package com.guandera.core.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.core.client.service.CuentaCobroService;
import com.guandera.core.shared.model.CuentaCobro;
import com.guandera.core.shared.model.CuentaCobroEstado;
import com.guandera.core.shared.model.Moneda;
import com.guandera.core.shared.model.Proveedor;

public class CuentaCobroServiceImpl implements CuentaCobroService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(CuentaCobro cuentaCobro) {
		ofy().save().entity(cuentaCobro).now();
	}

	@Override
	public CuentaCobro consultarPorId(Long id) {
		Key<CuentaCobro> k = Key.create(CuentaCobro.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<CuentaCobro> consultarTodos() {
		return ofy().load().type(CuentaCobro.class).order("-numeroCuenta").list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(CuentaCobro.class).count();
		return cont;

	}

	@Override
	public void crear(CuentaCobro cuentaCobro) {

		ofy().save().entity(cuentaCobro).now();

	}

	@Override
	public void eliminar(CuentaCobro cuentaCobro) {
		ofy().delete().entity(cuentaCobro).now();

	}

	@Override
	public Long siguienteRegistro() {

		CuentaCobro cuentaCobro = new CuentaCobro();
		long siguiente = 0;

		try {
			cuentaCobro = ofy().load().type(CuentaCobro.class).order("-numeroCuenta").first().now();
			siguiente = cuentaCobro.getNumeroCuenta();
		} catch (Exception e) {

			siguiente = 0;
		}

		return siguiente + 1;

	}

	@Override
	public List<Proveedor> consultarProveedores() {
		return ofy().load().type(Proveedor.class).list();
	}

	@Override
	public List<CuentaCobroEstado> consultarEstados() {
		return ofy().load().type(CuentaCobroEstado.class).list();

	}

	@Override
	public List<Moneda> consultarMonedas() {
		return ofy().load().type(Moneda.class).list();
	}

}