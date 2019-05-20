package com.guandera.proyecto.client.service;

import java.util.List;

import com.guandera.core.shared.model.acceso.Usuario;
import com.guandera.proyecto.shared.model.ActividadTipo;
import com.guandera.proyecto.shared.model.Asignacion;
import com.guandera.proyecto.shared.model.Actividad;
import com.guandera.proyecto.shared.model.Tiempo;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface ActividadService {

	public void actualizar(Actividad itemActividad);

	public Actividad consultarPorId(Long itemActividad);

	public List<Actividad> consultarTodos(Integer i, Usuario usuario);

	public long contar();

	public void crear(Actividad itemActividad);

	public void eliminar(Actividad itemActividad);

	public Long siguienteRegistro();

	public List<ActividadTipo> consultarTipos();

	public List<Tiempo> consultarTiempos();

	public void finalizar(Actividad itemSeleccionado);

	public List<Actividad> consultarActividadesFinalizadas(Integer i, Usuario usuario);

	public List<Asignacion> consultarAsignacion(Integer i, Usuario usuario);

	public void finalizarAsignacion(Asignacion asignacionSeleccionada);

	public List<Actividad> consultarListaActividades();

	public List<Actividad> consultarActividadesPorUsuario(Usuario usuario);

	public List<Actividad> consultarActividadesUsuarioPeriodo(Usuario usuario, Integer periodo);

	public List<Asignacion> consultarAsignacionPorUsuario(Usuario usuario);

	public Integer siguienteActividad();

	public List<Usuario> consultarUsuarios();

	public List<Actividad> consultarActividadesUsuarioPeriodo(Long usuarioid, Integer periodo);

}
