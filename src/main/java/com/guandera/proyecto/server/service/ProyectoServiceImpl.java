package com.guandera.proyecto.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.core.shared.model.CentroCostos;
import com.guandera.core.shared.model.Cliente;
import com.guandera.core.shared.model.ClienteContacto;
import com.guandera.core.shared.model.Compania;
import com.guandera.core.shared.model.Sede;
import com.guandera.core.shared.model.acceso.Usuario;
import com.guandera.proyecto.client.service.ProyectoService;
import com.guandera.proyecto.shared.model.Actividad;
import com.guandera.proyecto.shared.model.Asignacion;
import com.guandera.proyecto.shared.model.AsignacionEstado;
import com.guandera.proyecto.shared.model.EquipoCliente;
import com.guandera.proyecto.shared.model.EquipoCompania;
import com.guandera.proyecto.shared.model.ProyectoEtapa;
import com.guandera.proyecto.shared.model.Proyecto;
import com.guandera.proyecto.shared.model.ProyectoEtapaCambio;
import com.guandera.proyecto.shared.model.ProyectoTipo;
import com.guandera.proyecto.shared.model.Requerimiento;
import com.guandera.proyecto.shared.model.RequerimientoEstado;
import com.guandera.proyecto.shared.model.RequerimientoTipo;
import com.guandera.proyecto.shared.model.RolProyecto;
import com.guandera.proyecto.shared.model.Tarea;
import com.guandera.proyecto.shared.model.TareaEstado;
import com.guandera.proyecto.shared.model.Tiempo;
import com.guandera.talento.empleado.shared.model.Empleado;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class ProyectoServiceImpl implements ProyectoService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(Proyecto itemProyecto) {
		ofy().save().entity(itemProyecto).now();
	}

	@Override
	public Proyecto consultarPorId(Long id) {
		Key<Proyecto> k = Key.create(Proyecto.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<Proyecto> consultarTodos() {
		return ofy().load().type(Proyecto.class).order("-proyectoNumero").list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(Proyecto.class).count();
		return cont;

	}

	@Override
	public void crear(Proyecto itemProyecto) {
		ofy().save().entity(itemProyecto).now();
	}

	@Override
	public void eliminar(Proyecto itemProyecto) {

		ofy().delete().entity(itemProyecto).now();

	}

	@Override
	public Long siguienteRegistro() {

		long siguiente = ofy().load().type(Proyecto.class).count();
		return siguiente + 1;
	}

	@Override
	public List<ProyectoTipo> consultarTipos() {
		return ofy().load().type(ProyectoTipo.class).list();
	}

	@Override
	public List<Compania> consultarCompanias() {

		return ofy().load().type(Compania.class).list();

	}

	@Override
	public List<CentroCostos> consultarCentroscostos(Long companiaid) {

		Key<Compania> kcompania = Key.create(Compania.class, companiaid);

		Compania compania = ofy().load().key(kcompania).now();

		List<CentroCostos> centroCostosList = new ArrayList<CentroCostos>();

		centroCostosList = ofy().load().type(CentroCostos.class).filter("compania", compania).list();

		return centroCostosList;
	}

	@Override
	public List<Cliente> consultarClientes() {

		return ofy().load().type(Cliente.class).list();
	}

	@Override
	public List<ProyectoEtapa> consultarEtapas() {
		return ofy().load().type(ProyectoEtapa.class).list();
	}

	@Override
	public List<Requerimiento> consultarRequerimiento(Long proyectoid) {

		Key<Proyecto> kProyecto = Key.create(Proyecto.class, proyectoid);

		List<Requerimiento> requerimientoList = new ArrayList<Requerimiento>();

		requerimientoList = ofy().load().type(Requerimiento.class).filter("proyecto", kProyecto)
				.order("-requerimientoNumero").list();

		return requerimientoList;
	}

	@Override
	public List<RequerimientoTipo> consultarTiposRequerimiento() {
		return ofy().load().type(RequerimientoTipo.class).list();
	}

	@Override
	public List<RequerimientoEstado> consultarEstadosRequerimiento() {
		return ofy().load().type(RequerimientoEstado.class).list();

	}

	@Override
	public void eliminarRequerimiento(Requerimiento itemRequerimiento) {
		ofy().delete().entity(itemRequerimiento).now();

	}

	@Override
	public void crearRequerimiento(Requerimiento itemRequerimiento) {
		ofy().save().entity(itemRequerimiento).now();

	}

	@Override
	public void actualizarRequerimiento(Requerimiento itemRequerimiento) {
		ofy().save().entity(itemRequerimiento).now();

	}

	@Override
	public List<Tarea> consultarTarea(Long requerimientoid) { 

		Key<Requerimiento> kRequerimiento = Key.create(Requerimiento.class, requerimientoid);

		List<Tarea> tareaList = new ArrayList<Tarea>();

		tareaList = ofy().load().type(Tarea.class).filter("requerimiento", kRequerimiento).order("tareaNumero").list();
		return tareaList;
	}

	@Override
	public List<Tiempo> consultarTiempos() {
		return ofy().load().type(Tiempo.class).list();

	}

	@Override
	public List<TareaEstado> consultarEstadosTarea() {
		return ofy().load().type(TareaEstado.class).list();
	}

	@Override
	public void eliminarTarea(Tarea itemTarea) {
		ofy().delete().entity(itemTarea).now();

	}

	@Override
	public void crearTarea(Tarea itemTarea) {
		ofy().save().entity(itemTarea).now();

	}

	@Override
	public void actualizarTarea(Tarea itemTarea) {
		ofy().save().entity(itemTarea).now();

	}

	@Override
	public List<Usuario> consultarUsuarios() {
		return ofy().load().type(Usuario.class).list();

	}

	@Override
	public List<AsignacionEstado> consultarEstadosAsignacion() {
		return ofy().load().type(AsignacionEstado.class).list();
	}

	@Override
	public void eliminarAsignacion(Asignacion itemAsignacion) {
		ofy().delete().entity(itemAsignacion).now();
	}

	@Override
	public void crearAsignacion(Asignacion itemAsignacion) {
		ofy().save().entity(itemAsignacion).now();
	}

	@Override
	public void actualizarAsignacion(Asignacion itemAsignacion) {
		ofy().save().entity(itemAsignacion).now();

	}

	@Override
	public List<ProyectoEtapaCambio> consultarProyectoEtapaCambio(Long proyectoid) {
		Key<Proyecto> kProyecto = Key.create(Proyecto.class, proyectoid);
		return ofy().load().type(ProyectoEtapaCambio.class).filter("proyecto", kProyecto).list();
	}

	@Override
	public List<ProyectoEtapa> consultarProyectoEtapa() {
		return ofy().load().type(ProyectoEtapa.class).list();
	}

	@Override
	public void eliminarProyectoEtapaCambio(ProyectoEtapaCambio itemProyectoEtapaCambio) {
		ofy().delete().entity(itemProyectoEtapaCambio).now();
	}

	@Override
	public void crearProyectoEtapaCambio(ProyectoEtapaCambio itemProyectoEtapaCambio) {
		ofy().save().entity(itemProyectoEtapaCambio).now();
	}

	@Override
	public void actualizarProyectoEtapaCambio(ProyectoEtapaCambio itemProyectoEtapaCambio) {
		ofy().save().entity(itemProyectoEtapaCambio).now();
	}

	@Override
	public List<EquipoCliente> consultarEquipoCliente(Long proyectoid) {
		Key<Proyecto> kProyecto = Key.create(Proyecto.class, proyectoid);
		return ofy().load().type(EquipoCliente.class).filter("proyecto", kProyecto).list();
	}

	@Override
	public List<RolProyecto> consultarRolProyecto() {
		return ofy().load().type(RolProyecto.class).list();
	}

	@Override
	public List<ClienteContacto> consultarClienteContacto(Long clienteid) {
		Key<Cliente> kCliente = Key.create(Cliente.class, clienteid);
		return ofy().load().type(ClienteContacto.class).filter("cliente", kCliente).list();

	}

	@Override
	public void eliminarEquipoCliente(EquipoCliente itemEquipoCliente) {
		ofy().delete().entity(itemEquipoCliente).now();

	}

	@Override
	public void crearEquipoCliente(EquipoCliente itemEquipoCliente) {
		ofy().save().entity(itemEquipoCliente).now();

	}

	@Override
	public void actualizarEquipoCliente(EquipoCliente itemEquipoCliente) {
		ofy().save().entity(itemEquipoCliente).now();

	}

	@Override
	public List<EquipoCompania> consultarEquipoCompania(Long proyectoid) {
		Key<Proyecto> kProyecto = Key.create(Proyecto.class, proyectoid);
		return ofy().load().type(EquipoCompania.class).filter("proyecto", kProyecto).list();
	}

	@Override
	public List<Empleado> consultarEmpleados(Long companiaid) {
		Key<Compania> kCompania = Key.create(Compania.class, companiaid);
		Sede sede = ofy().load().type(Sede.class).filter("compania", kCompania).first().now();

		return ofy().load().type(Empleado.class).filter("sede", sede).list();
	}

	@Override
	public void eliminarEquipoCompania(EquipoCompania itemEquipoCompania) {
		ofy().delete().entity(itemEquipoCompania).now();

	}

	@Override
	public void crearEquipoCompania(EquipoCompania itemEquipoCompania) {
		ofy().save().entity(itemEquipoCompania).now();

	}

	@Override
	public void actualizarEquipoCompania(EquipoCompania itemEquipoCompania) {
		ofy().save().entity(itemEquipoCompania).now();
	}

	@Override
	public Integer siguienteProyecto() {

		int siguiente = 0;

		Proyecto proyecto = new Proyecto();

		try {
			proyecto = ofy().load().type(Proyecto.class).order("-proyectoNumero").first().now();
			siguiente = proyecto.getProyectoNumero();
		} catch (Exception e) {

			siguiente = 0;
		}

		return siguiente + 1;

	}

	@Override
	public Integer siguienteRequerimiento() {
		int siguiente = 0;

		Requerimiento requerimiento = new Requerimiento();

		try {
			requerimiento = ofy().load().type(Requerimiento.class).order("-requerimientoNumero").first().now();
			siguiente = requerimiento.getRequerimientoNumero();
		} catch (Exception e) {

			siguiente = 0;
		}

		return siguiente + 1;
	}

	@Override
	public Integer siguienteTarea() {
		int siguiente = 0;

		Tarea tarea = new Tarea();

		try {
			tarea = ofy().load().type(Tarea.class).order("-tareaNumero").first().now();
			siguiente = tarea.getTareaNumero();
		} catch (Exception e) {

			siguiente = 0;
		}

		return siguiente + 1;
	}

	@Override
	public Integer siguienteAsignacion() {
		int siguiente = 0;

		Asignacion asignacion = new Asignacion();

		try {
			asignacion = ofy().load().type(Asignacion.class).order("-asignacionNumero").first().now();
			siguiente = asignacion.getAsignacionNumero();
		} catch (Exception e) {

			siguiente = 0;
		}

		return siguiente + 1;
	}

	@Override
	public void inicializarNumero() {

		int proyectoNumero = 0;
		List<Proyecto> proyectoList = ofy().load().type(Proyecto.class).list();
		for (Proyecto proyecto : proyectoList) {
			proyectoNumero += 1;
			proyecto.setProyectoNumero(proyectoNumero);
			ofy().save().entity(proyecto).now();
		}

		int requerimientoNumero = 0;
		List<Requerimiento> requerimientoList = ofy().load().type(Requerimiento.class).list();
		for (Requerimiento requerimiento : requerimientoList) {
			requerimientoNumero += 1;
			requerimiento.setRequerimientoNumero(requerimientoNumero);
			ofy().save().entity(requerimiento).now();
		}

		int tareaNumero = 0;
		List<Tarea> tareaList = ofy().load().type(Tarea.class).list();
		for (Tarea tarea : tareaList) {
			tareaNumero += 1;
			tarea.setTareaNumero(tareaNumero);
			ofy().save().entity(tarea).now();
		}

		int asignacionNumero = 0;
		List<Asignacion> asignacionList = ofy().load().type(Asignacion.class).list();
		for (Asignacion asignacion : asignacionList) {
			asignacionNumero += 1;
			asignacion.setAsignacionNumero(asignacionNumero);
			ofy().save().entity(asignacion).now();
		}

		int actividadNumero = 0;
		List<Actividad> actividadList = ofy().load().type(Actividad.class).list();
		for (Actividad actividad : actividadList) {
			actividadNumero += 1;
			actividad.setActividadNumero(actividadNumero);
			ofy().save().entity(actividad).now();
		}

	}

	@Override
	public List<Proyecto> consultarProyectosPorClienteEtapa(Long clienteid, Long proyectoEtapaid) {

		Key<Cliente> kCliente = Key.create(Cliente.class, clienteid);
		Cliente cliente = ofy().load().key(kCliente).now();

		Key<ProyectoEtapa> kEtapa = Key.create(ProyectoEtapa.class, proyectoEtapaid);
		ProyectoEtapa etapa = ofy().load().key(kEtapa).now();

		return ofy().load().type(Proyecto.class).filter("cliente", cliente).filter("etapa", etapa).list();
	}

	@Override
	public List<Requerimiento> consultarRequerimientosPorProyectoTipoEstado(Long proyectoid, Long requerimientoTipoid,
			Long requerimientoEstadoid) {

		Key<Proyecto> kProyecto = Key.create(Proyecto.class, proyectoid);
		Proyecto proyecto = ofy().load().key(kProyecto).now();

		Key<RequerimientoTipo> kTipo = Key.create(RequerimientoTipo.class, requerimientoTipoid);
		RequerimientoTipo tipo = ofy().load().key(kTipo).now();

		Key<RequerimientoEstado> kEstado = Key.create(RequerimientoEstado.class, requerimientoEstadoid);
		RequerimientoEstado estado = ofy().load().key(kEstado).now();

		return ofy().load().type(Requerimiento.class).filter("proyecto", proyecto).filter("tipo", tipo)
				.filter("estado", estado).order("requerimientoNumero").list();
	}

	@Override
	public List<Requerimiento> consultarRequerimientosPorTipoEstado(Long requerimientoTipoid,
			Long requerimientoEstadoid) {
		Key<RequerimientoTipo> kTipo = Key.create(RequerimientoTipo.class, requerimientoTipoid);
		RequerimientoTipo tipo = ofy().load().key(kTipo).now();

		Key<RequerimientoEstado> kEstado = Key.create(RequerimientoEstado.class, requerimientoEstadoid);
		RequerimientoEstado estado = ofy().load().key(kEstado).now();

		return ofy().load().type(Requerimiento.class).filter("tipo", tipo).filter("estado", estado)
				.order("requerimientoNumero").list();
	}

	@Override
	public List<Tarea> consultarTareasPorEstado(Long tareaEstadoid) {

		Key<TareaEstado> kEstado = Key.create(TareaEstado.class, tareaEstadoid);
		TareaEstado estado = ofy().load().key(kEstado).now();

		return ofy().load().type(Tarea.class).filter("estado", estado).list();
	}

	@Override
	public List<Asignacion> consultarAsignacionPorTarea(Long tareaid) {
		Key<Tarea> kTarea = Key.create(Tarea.class, tareaid);

		List<Asignacion> asignacionList = new ArrayList<Asignacion>();

		asignacionList = ofy().load().type(Asignacion.class).filter("tarea", kTarea).list();
		return asignacionList;
	}

	@Override
	public List<Asignacion> consultarAsignacionPorUsuarioEstado(Long usuarioid, Long asignacionEstadoid) {

		Key<Usuario> kUsuario = Key.create(Usuario.class, usuarioid);
		Usuario usuario = ofy().load().key(kUsuario).now();

		Key<AsignacionEstado> kEstado = Key.create(AsignacionEstado.class, asignacionEstadoid);
		AsignacionEstado estado = ofy().load().key(kEstado).now();

		return ofy().load().type(Asignacion.class).filter("usuario", usuario).filter("estado", estado)
				.order("asignacionNumero").list();

	}

	@Override
	public List<Proyecto> consultarProyectosPorCliente(Long clienteid) {
		Key<Cliente> kCliente = Key.create(Cliente.class, clienteid);
		Cliente cliente = ofy().load().key(kCliente).now();

		return ofy().load().type(Proyecto.class).filter("cliente", cliente).list();
	}

}