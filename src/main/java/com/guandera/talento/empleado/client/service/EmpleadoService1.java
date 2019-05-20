package com.guandera.talento.empleado.client.service;

import java.util.List;

import com.guandera.core.shared.model.CompaniaCargo;
import com.guandera.core.shared.model.Sede;
import com.guandera.core.shared.model.TipoIdentificacion;
import com.guandera.talento.empleado.shared.model.Empleado;
import com.guandera.talento.empleado.shared.model.EmpleadoAcudiente;
import com.guandera.talento.empleado.shared.model.EmpleadoEstado;
import com.guandera.talento.empleado.shared.model.EmpleadoInformacionAcademica;
import com.guandera.talento.empleado.shared.model.EmpleadoInformacionLaboral;
import com.guandera.talento.empleado.shared.model.EmpleadoSeguridadSocial;
import com.guandera.talento.empleado.shared.model.EmpresaSeguridadSocial;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface EmpleadoService1 {

	public void actualizar(Empleado empleado);

	public Empleado consultarPorId(Long empleadoid);

	public List<Empleado> consultarTodos();

	public long contar();

	public void crear(Empleado empleado);

	public void eliminar(Empleado empleado);

	public Long siguienteRegistro();

	public List<CompaniaCargo> consultarCargos();

	public List<EmpresaSeguridadSocial> consultarEmpresaSeguridadSocial();

	public List<TipoIdentificacion> consultarTiposIdentificacion();

	public List<Sede> consultarSedes();



	public List<EmpleadoEstado> consultarEmpleadoEstados();

	// *********** Empleado Informacion Academica Crear ///

	public List<EmpleadoInformacionAcademica> consultarInformacionAcademicaEmpleado(Long empleadoid);

	public void crearEmpleadoInformacionAcademica(EmpleadoInformacionAcademica itemEmpleadoInformacionAcademica);

	public void eliminarEmpleadoInformacionAcademica(EmpleadoInformacionAcademica itemEmpleadoInformacionAcademica);

	public void actualizarEmpleadoInformacionAcademica(EmpleadoInformacionAcademica itemEmpleadoInformacionAcademica);

	// ****** Empleado Informacion Laboral
	public List<EmpleadoInformacionLaboral> consultarInformacionLaboralEmpleado(Long empleadoid);

	public void eliminarEmpleadoInformacionLaboral(EmpleadoInformacionLaboral itemEmpleadoInformacionLaboral);

	public void crearEmpleadoInformacionLaboral(EmpleadoInformacionLaboral itemInformacionLaboral);

	public void actualizarEmpleadoInformacionLaboral(EmpleadoInformacionLaboral itemInformacionLaboral);

	// ** Empleado Acudiente
	public List<EmpleadoAcudiente> consultarEmpleadoAcudiente(Long empleadoacudienteid);

	public void crearEmpleadoAcudiente(EmpleadoAcudiente itemEmpleadoAcudiente);

	public void actualizarEmpleadoAcudiente(EmpleadoAcudiente itemEmpleadoAcudiente);

	public void eliminarEmpleadoAcudiente(EmpleadoAcudiente itemEmpleadoAcudiente);

	// *********
	// Empleado Seguridad Social
	public List<EmpleadoSeguridadSocial> consultarEmpleadoSeguridadSocial(Long empleadoSeguridadSocialid);

	public void crearEmpleadoSeguridadSocial(EmpleadoSeguridadSocial itemEmpleadoSeguridadSocial);

	public void actualizarEmpleadoSeguridadSocial(EmpleadoSeguridadSocial itemEmpleadoSeguridadSocial);

	public void eliminarEmpleadoSeguridadSocial(EmpleadoSeguridadSocial itemEmpleadoSeguridadSocial);

}
