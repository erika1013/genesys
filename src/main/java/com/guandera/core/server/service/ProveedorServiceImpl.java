package com.guandera.core.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.core.client.service.ProveedorService;
import com.guandera.core.shared.model.Compania;
import com.guandera.core.shared.model.Proveedor;
import com.guandera.core.shared.model.ProveedorContacto;
import com.guandera.core.shared.model.TipoIdentificacion;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class ProveedorServiceImpl implements ProveedorService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(Proveedor proveedor) {
		ofy().save().entity(proveedor).now();
	}

	@Override
	public Proveedor consultarPorId(Long id) {
		Key<Proveedor> k = Key.create(Proveedor.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<Proveedor> consultarTodos() {
		return ofy().load().type(Proveedor.class).list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(Proveedor.class).count();
		return cont;

	}

	@Override
	public void crear(Proveedor proveedor) {

		ofy().save().entity(proveedor).now();
	}

	@Override
	public void eliminar(Proveedor proveedor) {
		ofy().delete().entity(proveedor).now();

	}

	@Override
	public Long siguienteRegistro() {

		long siguiente = ofy().load().type(Proveedor.class).count();
		return siguiente + 1;
	}

	@Override
	public List<Compania> consultarCompanias() {
		return ofy().load().type(Compania.class).list();
	}

	@Override
	public List<TipoIdentificacion> consultarTiposIdentificacion() {
		return ofy().load().type(TipoIdentificacion.class).list();
	}

	@Override
	public List<ProveedorContacto> consultarProveedorContactos(Long proveedorid) {
		Key<Proveedor> kProveedor = Key.create(Proveedor.class, proveedorid);

		List<ProveedorContacto> listaProveedorContacto = new ArrayList<ProveedorContacto>();

		listaProveedorContacto = ofy().load().type(ProveedorContacto.class).filter("proveedor", kProveedor).list();

		return listaProveedorContacto;
	}

	@Override
	public void crearProveedorContacto(ProveedorContacto proveedorContacto) {
		ofy().save().entity(proveedorContacto).now();

	}

	@Override
	public void actualizarProveedorContacto(ProveedorContacto proveedorContacto) {
		ofy().save().entity(proveedorContacto).now();

	}

	@Override
	public void eliminarProveedorContacto(ProveedorContacto proveedorContacto) {
		ofy().delete().entity(proveedorContacto).now();

	}

}