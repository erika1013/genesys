package com.guandera.core.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.core.client.service.CompaniaService;
import com.guandera.core.shared.model.Compania;
import com.guandera.core.shared.model.CompaniaCuenta;
import com.guandera.core.shared.model.CompaniaImpuesto;
import com.guandera.core.shared.model.Sede;
import com.guandera.core.shared.model.acceso.UsuarioAdm;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class CompaniaServiceImpl implements CompaniaService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(Compania compania) {
		ofy().save().entity(compania).now();
	}

	@Override
	public Compania consultarPorId(Long id) {
		Key<Compania> k = Key.create(Compania.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<Compania> consultarTodos() {
		return ofy().load().type(Compania.class).list();
	}

	@Override
	public List<Compania> consultarPorUsuarioAdmCreador(UsuarioAdm usuarioAdmCreacion) {
		return ofy().load().type(Compania.class).filter("usuarioAdmCreacion", usuarioAdmCreacion).list();
	}

	/*
	 * @Override public List<Compania> consultarCompaniaPorAncestor(Long
	 * usuarioAdmid) { Key<UsuarioAdm> parentKey= Key.create(UsuarioAdm.class,
	 * usuarioAdmid); System.out.println("key3...."+parentKey);// Creating the
	 * Ancestor key
	 * 
	 * 
	 * return ofy().load().type(Compania.class).ancestor(parentKey).list(); }
	 */

	@Override
	public long contar() {

		long cont = ofy().load().type(Compania.class).count();
		return cont;

	}

	@Override
	public void crear(Compania compania) {
		ofy().save().entity(compania).now();
	}

	@Override
	public void eliminar(Compania compania) {
		ofy().delete().entity(compania).now();

	}

	@Override
	public Long siguienteRegistro() {

		long siguiente = ofy().load().type(Compania.class).count();
		return siguiente + 1;
	}

	/*
	 * @Override public List<Sede> consultarSedesPorCompaniaAncestor(Long
	 * companiaid) {
	 * 
	 * Key<Compania> kCompania = Key.create(Compania.class, companiaid);
	 * 
	 * 
	 * List<Sede> listaSedes = new ArrayList<Sede>();
	 * 
	 * listaSedes = ofy().load().type(Sede.class).ancestor(kCompania)
	 * .order("sedecodigo").list();
	 * 
	 * return listaSedes;
	 * 
	 * }
	 */

	@Override
	public void crearSede(Sede itemSede) {
		ofy().save().entity(itemSede).now();

	}

	@Override
	public void eliminarSede(Sede sede) {
		ofy().delete().entity(sede).now();

	}

	@Override
	public void actualizarSede(Sede itemSede) {
		ofy().save().entity(itemSede).now();

	}

	@Override
	public List<Sede> consultarSedesPorCompania(Long companiaid) {
		Key<Compania> kCompania = Key.create(Compania.class, companiaid);

		List<Sede> listaSedes = new ArrayList<Sede>();

		listaSedes = ofy().load().type(Sede.class).filter("compania", kCompania).order("sedecodigo").list();

		return listaSedes;
	}

	@Override
	public Long siguienteRegistro1() {

		Compania compania = new Compania();
		long siguiente = 0;

		try {
			compania = ofy().load().type(Compania.class).order("-companiaNumero").first().now();
			// siguiente = compania.getCompaniaNumero();
		} catch (Exception e) {

			siguiente = 0;
		}

		return siguiente + 1;

	}

	@Override
	public void eliminarImpuesto(CompaniaImpuesto itemImpuesto) {
		ofy().delete().entity(itemImpuesto).now();

	}

	@Override
	public void actualizarImpuesto(CompaniaImpuesto itemImpuesto) {
		ofy().save().entity(itemImpuesto).now();

	}

	@Override
	public void crearImpuesto(CompaniaImpuesto itemImpuesto) {
		ofy().save().entity(itemImpuesto).now();

	}

	@Override
	public List<CompaniaImpuesto> consultarImpuestoPorCompania(Long companiaid) {
		Key<Compania> kCompania = Key.create(Compania.class, companiaid);

		List<CompaniaImpuesto> listaImpuesto = new ArrayList<CompaniaImpuesto>();

		listaImpuesto = ofy().load().type(CompaniaImpuesto.class).filter("compania", kCompania).list();

		return listaImpuesto;
	}

	@Override
	public List<CompaniaCuenta> consultarCuentaPorCompania(Long companiaid) {
		Key<Compania> kCompania = Key.create(Compania.class, companiaid);

		List<CompaniaCuenta> listaCuenta = new ArrayList<CompaniaCuenta>();

		listaCuenta = ofy().load().type(CompaniaCuenta.class).filter("compania", kCompania).list();

		return listaCuenta;
	}

	@Override
	public void crearCuenta(CompaniaCuenta itemCuenta) {
		ofy().save().entity(itemCuenta).now();

	}

	@Override
	public void actualizarCuenta(CompaniaCuenta itemCuenta) {
		ofy().save().entity(itemCuenta).now();

	}

	@Override
	public void eliminarCuenta(CompaniaCuenta itemCuenta) {
		ofy().delete().entity(itemCuenta).now();

	}

}