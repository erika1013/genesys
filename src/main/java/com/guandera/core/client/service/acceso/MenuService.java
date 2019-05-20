package com.guandera.core.client.service.acceso;

import java.util.List;

import com.guandera.core.shared.model.acceso.Menu;
import com.guandera.core.shared.model.acceso.Opcion;
import com.guandera.core.shared.model.acceso.Submenu;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */
public interface MenuService {

	public void actualizar(Menu menu);

	public void actualizarSubmenu(Submenu submenu);

	public Menu consultarPorId(Long menuid);

	public List<Submenu> consultarSubmenuPorMenu(Long menuid);

	public List<Menu> consultarTodos();

	public List<Opcion> consultarOpciones();

	public long contar();

	public void crear(Menu menu);

	public void crearSubmenu(Submenu submenu);

	public void eliminar(Menu menu);

	public void eliminarSubmenu(Submenu submenu);

	public Long siguienteRegistro();

	public Long siguienteSubmenu();

}
