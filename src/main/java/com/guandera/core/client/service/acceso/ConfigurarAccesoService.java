package com.guandera.core.client.service.acceso;

import java.util.List;

import com.guandera.core.shared.model.acceso.Acceso;
import com.guandera.core.shared.model.acceso.Menu;
import com.guandera.core.shared.model.acceso.Opcion;
import com.guandera.core.shared.model.acceso.Rol;
import com.guandera.core.shared.model.acceso.Submenu;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface ConfigurarAccesoService {

	public void crearRol(Rol rol);

	public void crearVista(Opcion vista);

	public void crearMenu(Menu menu);

	public void crearSubmenu(Submenu submenu);

	public void crearAcceso(Acceso acceso);

	public List<Rol> consultarListaRoles();

	public List<Opcion> consultarListaOpciones();

	public List<Menu> consultarListaMenus();

	public List<Acceso> consultarListaAccesos();

	public List<Submenu> consultarListaSubmenus();

}
