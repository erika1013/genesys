/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.guandera.core.client.service.acceso;

import java.util.List;

import com.guandera.core.shared.model.acceso.Menu;
import com.guandera.core.shared.model.acceso.Opcion;
import com.guandera.core.shared.model.acceso.Usuario;

public interface LoginService {

	public Usuario CargarUsuario(String nombreusuario);

	public List<Menu> consultarMenuPorRol(Long rolid);

	public List<Opcion> consultarOpcionesPorMenu(Long menuid);

	boolean existeUsuario(String usuariousuario);

	boolean verificarUsuario(Usuario usuario);

	public void actualizarIngreso(Usuario usuario);
}
