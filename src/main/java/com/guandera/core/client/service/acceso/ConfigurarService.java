package com.guandera.core.client.service.acceso;

import com.guandera.core.shared.model.Cobro;
import com.guandera.core.shared.model.CobroCalendario;
import com.guandera.core.shared.model.Sede;
import com.guandera.core.shared.model.Servicio;
import com.guandera.core.shared.model.acceso.Acceso;
import com.guandera.core.shared.model.acceso.Menu;
import com.guandera.core.shared.model.acceso.Opcion;
import com.guandera.core.shared.model.acceso.Rol;
import com.guandera.core.shared.model.acceso.Submenu;
import com.guandera.core.shared.model.acceso.Usuario;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface ConfigurarService {

	public void actualizarTipoServicio();

	public void actualizarEmpleadoNombres();

	public void actualizarCodigoSede();

	public void actualizarServicio();

	public void crearRol(Rol rol);

	public void crearVista(Opcion vista);

	public void crearMenu(Menu menu);

	public void crearSubmenu(Submenu submenu);

	public void crearAcceso(Acceso acceso);

	public Sede cargarSede(String codigoSede);

	public void crearServicio(Servicio servicio);

	public Servicio cargarServicio(Long tipoServicioId, Integer tarifa);

	public void reiniciarCobros();

	public void actualizarCodigoServicio();

	public CobroCalendario cargarCalendarioPerido(String periodo);

	public Servicio cargarServicioPorCodigo(long codigoServicio);

	public Long siguienteNroCobro();

	public Integer siguienteNroRecibo();

	public void crearCobro(Cobro cobro);

	public void reiniciarBaseCobros();

	public void actualizarConciliacion();

	public void actualizarDetalleRecibo(Usuario usuario);

}
