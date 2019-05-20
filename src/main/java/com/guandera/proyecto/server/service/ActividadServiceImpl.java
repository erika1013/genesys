package com.guandera.proyecto.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.core.shared.model.acceso.Usuario;
import com.guandera.proyecto.client.service.ActividadService;
import com.guandera.proyecto.shared.model.Actividad;
import com.guandera.proyecto.shared.model.ActividadTipo;
import com.guandera.proyecto.shared.model.Asignacion;
import com.guandera.proyecto.shared.model.AsignacionEstado;
import com.guandera.proyecto.shared.model.Tiempo;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class ActividadServiceImpl implements ActividadService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(Actividad itemActividad) {
		ofy().save().entity(itemActividad).now();
	}

	@Override
	public Actividad consultarPorId(Long id) {
		Key<Actividad> k = Key.create(Actividad.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<Actividad> consultarTodos(Integer i, Usuario usuario) {
		Long tipo = 1L;
		List<Actividad> listaActividades = new ArrayList<Actividad>();
		Key<AsignacionEstado> k = Key.create(AsignacionEstado.class, tipo);
		List<Asignacion> asignacionList = new ArrayList<Asignacion>();
		if (i == 1) {
			asignacionList = ofy().load().type(Asignacion.class).filter("estado", k).list();

		}

		else {
			asignacionList = ofy().load().type(Asignacion.class).filter("estado", k).filter("usuario", usuario).list();
		}

		for (Asignacion asignacion : asignacionList) {
			Actividad actividad = ofy().load().type(Actividad.class).filter("asignacion", asignacion).first().now();
			if (actividad != null) {
				listaActividades.add(actividad);
			}
		}
		return listaActividades;

	}

	@Override
	public List<Actividad> consultarActividadesFinalizadas(Integer i, Usuario usuario) {
		Long tipo = 2L;
		List<Actividad> listaActividades = new ArrayList<Actividad>();
		Key<AsignacionEstado> k = Key.create(AsignacionEstado.class, tipo);
		List<Asignacion> asignacionList = new ArrayList<Asignacion>();
		if (i == 1) {
			asignacionList = ofy().load().type(Asignacion.class).filter("estado", k).list();

		}

		else {
			asignacionList = ofy().load().type(Asignacion.class).filter("estado", k).filter("usuario", usuario).list();
		}
		for (Asignacion asignacion : asignacionList) {
			Actividad actividad = ofy().load().type(Actividad.class).filter("asignacion", asignacion).first().now();
			if (actividad != null) {
				listaActividades.add(actividad);
			}
		}
		return listaActividades;
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(Actividad.class).count();
		return cont;

	}

	@Override
	public void crear(Actividad itemActividad) {
		ofy().save().entity(itemActividad).now();
	}

	@Override
	public void eliminar(Actividad itemActividad) {
		ofy().delete().entity(itemActividad).now();

	}

	@Override
	public Long siguienteRegistro() {

		long siguiente = ofy().load().type(Actividad.class).count();
		return siguiente + 1;
	}

	@Override
	public List<ActividadTipo> consultarTipos() {
		return ofy().load().type(ActividadTipo.class).list();
	}

	@Override
	public List<Asignacion> consultarAsignacion(Integer i, Usuario usuario) {
		Long tipo = 1L;
		Key<AsignacionEstado> k = Key.create(AsignacionEstado.class, tipo);
		List<Asignacion> listaAsignacion = new ArrayList<Asignacion>();
		if (i == 1) {
			listaAsignacion = ofy().load().type(Asignacion.class).filter("estado", k).list();
		} else {
			listaAsignacion = ofy().load().type(Asignacion.class).filter("estado", k).filter("usuario", usuario).list();
		}
		return listaAsignacion;
	}

	@Override
	public List<Tiempo> consultarTiempos() {
		return ofy().load().type(Tiempo.class).list();
	}

	@Override
	public void finalizar(Actividad itemSeleccionado) {

		Long estadoid = 2L;
		AsignacionEstado estado = new AsignacionEstado();
		estado.setEstadoid(estadoid);
		Asignacion asignacion = itemSeleccionado.getAsignacion();
		asignacion.setEstado(estado);
		ofy().save().entity(asignacion).now();
		itemSeleccionado.setAsignacion(asignacion);
		itemSeleccionado.setActividadFechaModificacion(new Date());
		ofy().save().entity(itemSeleccionado).now();

	}

	@Override
	public void finalizarAsignacion(Asignacion asignacion) {

		Long estadoid = 2L;
		AsignacionEstado estado = new AsignacionEstado();
		estado.setEstadoid(estadoid);
		asignacion.setEstado(estado);

		ofy().save().entity(asignacion).now();
	}

	@Override
	public List<Actividad> consultarListaActividades() {
		return ofy().load().type(Actividad.class).list();
	}

	@Override
	public List<Actividad> consultarActividadesPorUsuario(Usuario usuario) {

		List<Asignacion> asignacionList = new ArrayList<Asignacion>();
		List<Actividad> listaActividades = new ArrayList<Actividad>();

		asignacionList = ofy().load().type(Asignacion.class).filter("usuario", usuario).list();

		for (Asignacion asignacion : asignacionList) {
			Actividad actividad = ofy().load().type(Actividad.class).filter("asignacion", asignacion).first().now();
			if (actividad != null) {
				listaActividades.add(actividad);
			}
		}
		return listaActividades;
	}

	@Override
	public List<Actividad> consultarActividadesUsuarioPeriodo(Usuario usuario, Integer periodo) {

		List<Actividad> listaActividades = new ArrayList<Actividad>();

		listaActividades = ofy().load().type(Actividad.class).filter("usuario", usuario).filter("periodo =", periodo).order("-actividadNumero")
				.list();

		return listaActividades;
	}

	@Override
	public List<Asignacion> consultarAsignacionPorUsuario(Usuario usuario) {
		
		Long tipo = 1L;
		Key<AsignacionEstado> k = Key.create(AsignacionEstado.class, tipo);
		List<Asignacion> listaAsignacion = new ArrayList<Asignacion>();

		listaAsignacion = ofy().load().type(Asignacion.class).filter("estado", k).filter("usuario", usuario).list();

		return listaAsignacion;
	}

	@Override
	public Integer siguienteActividad() {
		int siguiente = 0;

		Actividad actividad = new Actividad();

		try {
			actividad = ofy().load().type(Actividad.class).order("-actividadNumero").first().now();
			siguiente = actividad.getActividadNumero();
		} catch (Exception e) {

			siguiente = 0;
		}

		return siguiente + 1;
	}

	@Override
	public List<Usuario> consultarUsuarios() {
		return ofy().load().type(Usuario.class).list();
	}

	@Override
	public List<Actividad> consultarActividadesUsuarioPeriodo(Long usuarioid, Integer periodo) {
		List<Actividad> listaActividades = new ArrayList<Actividad>();
		
		Key<Usuario> kUsuario = Key.create(Usuario.class, usuarioid);
		Usuario usuario =  ofy().load().key(kUsuario).now();
		

		listaActividades = ofy().load().type(Actividad.class).filter("usuario", usuario).filter("periodo =", periodo).order("-actividadNumero")
				.list();

		return listaActividades;
	}

}