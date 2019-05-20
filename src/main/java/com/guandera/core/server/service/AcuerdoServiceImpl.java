package com.guandera.core.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.core.client.service.AcuerdoService;
import com.guandera.core.shared.model.Acuerdo;
import com.guandera.core.shared.model.Compania;
import com.guandera.core.shared.model.Servicio;
import com.guandera.core.shared.model.Cliente;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class AcuerdoServiceImpl implements AcuerdoService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(Acuerdo acuerdo) {

		ofy().save().entity(acuerdo).now();
	}

	@Override
	public Acuerdo consultarPorId(Long id) {
		Key<Acuerdo> k = Key.create(Acuerdo.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<Acuerdo> consultarTodos() {
		return ofy().load().type(Acuerdo.class).list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(Acuerdo.class).count();
		return cont;

	}

	@Override
	public void crear(Acuerdo acuerdo) {

		ofy().save().entity(acuerdo).now();
	}

	@Override
	public void eliminar(Acuerdo acuerdo) {
		ofy().delete().entity(acuerdo).now();

	}

	@Override
	public Long siguienteRegistro() {

		long siguiente = ofy().load().type(Acuerdo.class).count();
		return siguiente + 1;
	}

	@Override
	public List<Cliente> consultarClientes() {
		return ofy().load().type(Cliente.class).list();
	}

	@Override
	public List<Compania> consultarCompanias() {
		return ofy().load().type(Compania.class).list();
	}

	@Override
	public List<Servicio> consultarServicios(Long companiaid) {

		Key<Compania> kCompania = Key.create(Compania.class, companiaid);

		List<Servicio> listaServicios = ofy().load().type(Servicio.class).filter("compania", kCompania).list();

		return listaServicios;
	}
}