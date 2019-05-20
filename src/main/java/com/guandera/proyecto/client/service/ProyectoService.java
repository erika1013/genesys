package com.guandera.proyecto.client.service;

import java.util.List;

import com.guandera.core.shared.model.CentroCostos;
import com.guandera.core.shared.model.Cliente;
import com.guandera.core.shared.model.ClienteContacto;
import com.guandera.core.shared.model.Compania;
import com.guandera.core.shared.model.acceso.Usuario;
import com.guandera.proyecto.shared.model.Asignacion;
import com.guandera.proyecto.shared.model.AsignacionEstado;
import com.guandera.proyecto.shared.model.EquipoCliente;
import com.guandera.proyecto.shared.model.EquipoCompania;
import com.guandera.proyecto.shared.model.ProyectoEtapaCambio;
import com.guandera.proyecto.shared.model.Requerimiento;
import com.guandera.proyecto.shared.model.Proyecto;
import com.guandera.proyecto.shared.model.ProyectoEtapa;
import com.guandera.proyecto.shared.model.ProyectoTipo;
import com.guandera.proyecto.shared.model.RequerimientoEstado;
import com.guandera.proyecto.shared.model.RequerimientoTipo;
import com.guandera.proyecto.shared.model.RolProyecto;
import com.guandera.proyecto.shared.model.Tarea;
import com.guandera.proyecto.shared.model.TareaEstado;
import com.guandera.proyecto.shared.model.Tiempo;
import com.guandera.talento.empleado.shared.model.Empleado;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface ProyectoService {

	public void actualizar(Proyecto itemProyecto);

	public Proyecto consultarPorId(Long itemProyecto);

	public List<Proyecto> consultarTodos();

	public long contar();

	public void crear(Proyecto itemProyecto);

	public void eliminar(Proyecto itemProyecto);

	public Long siguienteRegistro();

	public List<ProyectoTipo> consultarTipos();

	public List<Compania> consultarCompanias();

	public List<CentroCostos> consultarCentroscostos(Long companiaid);

	public List<Cliente> consultarClientes();

	public List<ProyectoEtapa> consultarEtapas();

	// Requerimiento

	public List<Requerimiento> consultarRequerimiento(Long proyectoid);

	public List<RequerimientoTipo> consultarTiposRequerimiento();

	public List<RequerimientoEstado> consultarEstadosRequerimiento();

	public void eliminarRequerimiento(Requerimiento itemRequerimiento);

	public void crearRequerimiento(Requerimiento itemRequerimiento);

	public void actualizarRequerimiento(Requerimiento itemRequerimiento);

	// Tarea
	public List<Tarea> consultarTarea(Long requerimientoid);

	public List<Tiempo> consultarTiempos();

	public List<TareaEstado> consultarEstadosTarea();

	public void eliminarTarea(Tarea itemTarea);

	public void crearTarea(Tarea itemTarea);

	public void actualizarTarea(Tarea itemTarea);

	// Asignaciones

	public List<Asignacion> consultarAsignacionPorTarea(Long tareaid);

	public List<Usuario> consultarUsuarios();

	public List<AsignacionEstado> consultarEstadosAsignacion();

	public void eliminarAsignacion(Asignacion itemAsignacion);

	public void crearAsignacion(Asignacion itemAsignacion);

	public void actualizarAsignacion(Asignacion itemAsignacion);

	// ProyectoEtapaCambio

	public List<ProyectoEtapaCambio> consultarProyectoEtapaCambio(Long proyectoid);

	public List<ProyectoEtapa> consultarProyectoEtapa();

	public void eliminarProyectoEtapaCambio(ProyectoEtapaCambio itemProyectoEtapaCambio);

	public void crearProyectoEtapaCambio(ProyectoEtapaCambio itemProyectoEtapaCambio);

	public void actualizarProyectoEtapaCambio(ProyectoEtapaCambio itemProyectoEtapaCambio);

	// EquipoCliente

	public List<EquipoCliente> consultarEquipoCliente(Long proyectoid);

	public List<RolProyecto> consultarRolProyecto();

	public List<ClienteContacto> consultarClienteContacto(Long clienteid);

	public void eliminarEquipoCliente(EquipoCliente itemEquipoCliente);

	public void crearEquipoCliente(EquipoCliente itemEquipoCliente);

	public void actualizarEquipoCliente(EquipoCliente itemEquipoCliente);

	// Equipo Compania

	public List<EquipoCompania> consultarEquipoCompania(Long proyectoid);

	public List<Empleado> consultarEmpleados(Long companiaid);

	public void eliminarEquipoCompania(EquipoCompania itemEquipoCompania);

	public void crearEquipoCompania(EquipoCompania itemEquipoCompania);

	public void actualizarEquipoCompania(EquipoCompania itemEquipoCompania);

	public Integer siguienteProyecto();

	public Integer siguienteRequerimiento();

	public Integer siguienteTarea();

	public Integer siguienteAsignacion();

	public void inicializarNumero();

	public List<Proyecto> consultarProyectosPorClienteEtapa(Long clienteid, Long proyectoEtapaid);

	public List<Requerimiento> consultarRequerimientosPorProyectoTipoEstado(Long proyectoid, Long requerimientoTipoid,
			Long requerimientoEstadoid);

	public List<Requerimiento> consultarRequerimientosPorTipoEstado(Long requerimientoTipoid,
			Long requerimientoEstadoid);

	public List<Tarea> consultarTareasPorEstado(Long tareaEstadoid);

	public List<Asignacion> consultarAsignacionPorUsuarioEstado(Long usuarioid, Long asignacionEstadoid);

	public List<Proyecto> consultarProyectosPorCliente(Long clienteid);


	

}
