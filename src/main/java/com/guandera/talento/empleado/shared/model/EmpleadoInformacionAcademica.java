/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guandera.talento.empleado.shared.model;

import java.io.Serializable;
import java.util.Date;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;
import com.guandera.core.shared.model.Auditoria;

/**
 * 
 * @author Fredi J. Velasco Villarreal - GUANDERA S.A.S.
 */
@Entity
public class EmpleadoInformacionAcademica extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long empleadoInformacionAcademicaid;

	private String nivelEstudio;

	private String institucion;

	private String tituloObtenido;

	private Date fechaInicio;

	private Date fechaTerminacion;

	private String estado;

	@Index
	@Load
	Ref<Empleado> empleado;

	public EmpleadoInformacionAcademica() {
	}

	public EmpleadoInformacionAcademica(Long empleadoInformacionAcademicaid) {
		this.empleadoInformacionAcademicaid = empleadoInformacionAcademicaid;
	}

	public Long getEmpleadoid() {
		return empleadoInformacionAcademicaid;
	}

	public void setEmpleadoid(Long empleadoInformacionAcademicaid) {
		this.empleadoInformacionAcademicaid = empleadoInformacionAcademicaid;
	}

	public Empleado getEmpleado() {
		return empleado.get();
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = Ref.create(empleado);
	}

	public String getNivelEstudio() {
		return nivelEstudio;
	}

	public void setnivelEstudio(String nivelEstudio) {
		this.nivelEstudio = nivelEstudio;
	}

	public String getInstitucion() {
		return institucion;
	}

	public void setInstitucion(String institucion) {
		this.institucion = institucion;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaTerminacion() {
		return fechaTerminacion;
	}

	public void setFechaTerminacion(Date fechaTerminacion) {
		this.fechaTerminacion = fechaTerminacion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getTituloObtenido() {
		return tituloObtenido;
	}

	public void setTituloObtenido(String tituloObtenido) {
		this.tituloObtenido = tituloObtenido;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (empleadoInformacionAcademicaid != null ? empleadoInformacionAcademicaid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {

		if (!(object instanceof EmpleadoInformacionAcademica)) {
			return false;
		}
		EmpleadoInformacionAcademica other = (EmpleadoInformacionAcademica) object;
		if ((this.empleadoInformacionAcademicaid == null && other.empleadoInformacionAcademicaid != null)
				|| (this.empleadoInformacionAcademicaid != null
						&& !this.empleadoInformacionAcademicaid.equals(other.empleadoInformacionAcademicaid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.core.shared.model.EmpleadoInformacionAcademica[ empleadoInformacionAcademicaid="
				+ empleadoInformacionAcademicaid + " ]";
	}

}
