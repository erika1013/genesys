package com.guandera.talento.empleado.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.core.shared.model.CompaniaCargo;
import com.guandera.core.shared.model.Sede;
import com.guandera.core.shared.model.TipoIdentificacion;
import com.guandera.talento.empleado.client.service.EmpleadoService1;
import com.guandera.talento.empleado.shared.model.Empleado;
import com.guandera.talento.empleado.shared.model.EmpleadoAcudiente;
import com.guandera.talento.empleado.shared.model.EmpleadoEstado;
import com.guandera.talento.empleado.shared.model.EmpleadoInformacionAcademica;
import com.guandera.talento.empleado.shared.model.EmpleadoInformacionLaboral;
import com.guandera.talento.empleado.shared.model.EmpleadoSeguridadSocial;
import com.guandera.talento.empleado.shared.model.EmpresaSeguridadSocial;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class EmpleadoServiceImpl1 implements EmpleadoService1, Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(Empleado empleado) {
		ofy().save().entity(empleado).now();
	}

	@Override
	public Empleado consultarPorId(Long id) {
		Key<Empleado> k = Key.create(Empleado.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<Empleado> consultarTodos() {
		return ofy().load().type(Empleado.class).list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(Empleado.class).count();
		return cont;

	}

	@Override
	public void crear(Empleado empleado) {
		ofy().save().entity(empleado).now();
	}

	@Override
	public void eliminar(Empleado empleado) {
		ofy().delete().entity(empleado).now();

	}

	@Override
	public Long siguienteRegistro() {

		long siguiente = ofy().load().type(Empleado.class).count();
		return siguiente + 1;
	}

	@Override
	public List<CompaniaCargo> consultarCargos() {

		return ofy().load().type(CompaniaCargo.class).list();
	}

	@Override
	public List<TipoIdentificacion> consultarTiposIdentificacion() {

		return ofy().load().type(TipoIdentificacion.class).list();
	}

	@Override
	public List<Sede> consultarSedes() {

		return ofy().load().type(Sede.class).list();
	}


	@Override
	public List<EmpleadoEstado> consultarEmpleadoEstados() {
		return ofy().load().type(EmpleadoEstado.class).list();
	}

	// Empleado informacion academica

	@Override
	public List<EmpleadoInformacionAcademica> consultarInformacionAcademicaEmpleado(Long empleadoid) {

		Key<Empleado> Kempleado = Key.create(Empleado.class, empleadoid);

		List<EmpleadoInformacionAcademica> listaInformacionAcademica = new ArrayList<EmpleadoInformacionAcademica>();

		listaInformacionAcademica = ofy().load().type(EmpleadoInformacionAcademica.class).filter("empleado", Kempleado)
				.list();

		return listaInformacionAcademica;

	}

	@Override
	public void crearEmpleadoInformacionAcademica(EmpleadoInformacionAcademica itemEmpleadoInformacionAcademica) {
		ofy().save().entity(itemEmpleadoInformacionAcademica).now();
	}

	@Override
	public void eliminarEmpleadoInformacionAcademica(EmpleadoInformacionAcademica itemEmpleadoInformacionAcademica) {
		// TODO Auto-generated method stub
		ofy().delete().entity(itemEmpleadoInformacionAcademica).now();

	}

	@Override
	public void actualizarEmpleadoInformacionAcademica(EmpleadoInformacionAcademica itemEmpleadoInformacionAcademica) {
		ofy().save().entity(itemEmpleadoInformacionAcademica).now();

	}

	// Empleado Informacion laboral

	@Override
	public List<EmpleadoInformacionLaboral> consultarInformacionLaboralEmpleado(Long empleadoid) {
		Key<Empleado> Kempleado = Key.create(Empleado.class, empleadoid);

		List<EmpleadoInformacionLaboral> listaInformacionLaboral = new ArrayList<EmpleadoInformacionLaboral>();

		listaInformacionLaboral = ofy().load().type(EmpleadoInformacionLaboral.class).filter("empleado", Kempleado)
				.list();

		return listaInformacionLaboral;
	}

	@Override
	public void crearEmpleadoInformacionLaboral(EmpleadoInformacionLaboral itemEmpleadoInformacionLaboral) {
		// TODO Auto-generated method stub

		ofy().save().entity(itemEmpleadoInformacionLaboral).now();

	}

	@Override
	public void eliminarEmpleadoInformacionLaboral(EmpleadoInformacionLaboral itemEmpleadoInformacionLaboral) {
		// TODO Auto-generated method stub
		ofy().delete().entity(itemEmpleadoInformacionLaboral).now();

	}

	@Override
	public void actualizarEmpleadoInformacionLaboral(EmpleadoInformacionLaboral itemEmpleadoInformacionLaboral) {
		// TODO Auto-generated method stub
		ofy().save().entity(itemEmpleadoInformacionLaboral).now();

	}

	// Empleado Acudiente

	@Override
	public List<EmpleadoAcudiente> consultarEmpleadoAcudiente(Long empleadoacudienteid) {
		Key<Empleado> Kempleado = Key.create(Empleado.class, empleadoacudienteid);

		List<EmpleadoAcudiente> listaEmpleadoAcudiente = new ArrayList<EmpleadoAcudiente>();

		listaEmpleadoAcudiente = ofy().load().type(EmpleadoAcudiente.class).filter("empleado", Kempleado).list();

		return listaEmpleadoAcudiente;
	}

	@Override
	public void crearEmpleadoAcudiente(EmpleadoAcudiente itemEmpleadoAcudiente) {
		// TODO Auto-generated method stub
		ofy().save().entity(itemEmpleadoAcudiente).now();
	}

	@Override
	public void actualizarEmpleadoAcudiente(EmpleadoAcudiente itemEmpleadoAcudiente) {
		// TODO Auto-generated method stub
		ofy().save().entity(itemEmpleadoAcudiente).now();

	}

	@Override
	public void eliminarEmpleadoAcudiente(EmpleadoAcudiente itemEmpleadoAcudiente) {
		// TODO Auto-generated method stub
		ofy().delete().entity(itemEmpleadoAcudiente).now();

	}

	// Seguridad Social

	@Override
	public List<EmpleadoSeguridadSocial> consultarEmpleadoSeguridadSocial(Long empleadoid) {
		Key<Empleado> Kempleado = Key.create(Empleado.class, empleadoid);

		List<EmpleadoSeguridadSocial> listaEmpleadoSeguridadSocial = new ArrayList<EmpleadoSeguridadSocial>();

		listaEmpleadoSeguridadSocial = ofy().load().type(EmpleadoSeguridadSocial.class).filter("empleado", Kempleado)
				.list();

		return listaEmpleadoSeguridadSocial;
	}

	@Override
	public void crearEmpleadoSeguridadSocial(EmpleadoSeguridadSocial itemEmpleadoSeguridadSocial) {
		ofy().save().entity(itemEmpleadoSeguridadSocial).now();

	}

	@Override
	public void actualizarEmpleadoSeguridadSocial(EmpleadoSeguridadSocial itemEmpleadoSeguridadSocial) {
		ofy().save().entity(itemEmpleadoSeguridadSocial).now();

	}

	@Override
	public void eliminarEmpleadoSeguridadSocial(EmpleadoSeguridadSocial itemEmpleadoSeguridadSocial) {
		ofy().delete().entity(itemEmpleadoSeguridadSocial).now();

	}

	@Override
	public List<EmpresaSeguridadSocial> consultarEmpresaSeguridadSocial() {
		return ofy().load().type(EmpresaSeguridadSocial.class).list();
	}

}