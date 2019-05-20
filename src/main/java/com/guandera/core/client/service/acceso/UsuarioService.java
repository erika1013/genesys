package com.guandera.core.client.service.acceso;

import java.util.List;

import com.guandera.core.shared.model.acceso.Rol;
import com.guandera.core.shared.model.acceso.Usuario;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface UsuarioService {

	public void actualizar(Usuario usuario);

	public Usuario consultarPorId(Long usuarioid);

	public List<Rol> consultarRoles();

	public List<Usuario> consultarTodos();

	public long contar();

	public void crear(Usuario usuario);

	public void eliminar(Usuario usuario);

	public Long siguienteRegistro();

}
