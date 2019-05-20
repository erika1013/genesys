package com.guandera.core.client.service.acceso;

import java.util.List;

import com.guandera.core.shared.model.acceso.Acceso;
import com.guandera.core.shared.model.acceso.Menu;
import com.guandera.core.shared.model.acceso.Rol;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */
public interface RolService {

	public void actualizar(Rol rol);

	public List<Acceso> consultarAccesosPorRol(Long rolid);

	public List<Menu> consultarMenuPorRol(Long rolid);

	public List<Menu> consultarMenus();

	public Rol consultarPorId(Long rolid);

	public List<Rol> consultarTodos();

	public long contar();

	public void crear(Rol rol);

	public void crearAcceso(Acceso acceso);

	public void eliminar(Rol rol);

	public void eliminarAcceso(Acceso acceso);

	public Long siguienteAcceso();

	public Long siguienteRegistro();

}
