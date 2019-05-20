package com.guandera.core.server.service.acceso;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.core.client.service.acceso.UsuarioService;
import com.guandera.core.shared.model.acceso.Rol;
import com.guandera.core.shared.model.acceso.Usuario;

/**
 * 
 * @author FrediJavier
 */
public class UsuarioServiceImpl implements UsuarioService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3819355947773691945L;

	@Override
	public void actualizar(Usuario usuario) {
		ofy().save().entity(usuario).now();
	}

	@Override
	public Usuario consultarPorId(Long usuarioid) {
		Key<Usuario> k = Key.create(Usuario.class, usuarioid);
		return ofy().load().key(k).now();
	}

	@Override
	public List<Rol> consultarRoles() {

		return ofy().load().type(Rol.class).list();
	}

	@Override
	public List<Usuario> consultarTodos() {
		return ofy().load().type(Usuario.class).list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(Usuario.class).count();
		return cont;
	}

	@Override
	public void crear(Usuario usuario) {
		ofy().save().entity(usuario).now();
	}

	@Override
	public void eliminar(Usuario usuario) {
		ofy().delete().entity(usuario).now();
	}

	@Override
	public Long siguienteRegistro() {

		long siguiente = ofy().load().type(Usuario.class).count();
		return siguiente + 1;

	}

}