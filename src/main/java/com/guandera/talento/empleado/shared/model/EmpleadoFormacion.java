/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
import com.guandera.talento.shared.model.FormacionEstado;
import com.guandera.talento.shared.model.Institucion;
import com.guandera.talento.shared.model.ProgramaAcademico;

/**
 * 
 * @author FrediJavier
 */
@Entity
public class EmpleadoFormacion extends Auditoria implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private Long formacionid;

	private Date fechaInicio;

	private Date fechaFinal;

	private Integer semestre;

	@Index
	@Load
	Ref<Empleado> empleado;

	@Index
	@Load
	Ref<FormacionEstado> estado;

	@Index
	@Load
	Ref<Institucion> institucion;

	@Index
	@Load
	Ref<ProgramaAcademico> programaAcademico;

	public EmpleadoFormacion() {
	}

	public EmpleadoFormacion(Long formacionid) {
		this.formacionid = formacionid;
	}

	public Long getFormacionid() {
		return formacionid;
	}

	public void setFormacionid(Long formacionid) {
		this.formacionid = formacionid;
	}

	public Integer getSemestre() {
		return semestre;
	}

	public void setSemestre(Integer semestre) {
		this.semestre = semestre;
	}

	public Empleado getEmpleado() {
		return empleado.get();
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = Ref.create(empleado);
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public FormacionEstado getEstado() {
		return estado.get();
	}

	public void setEstado(FormacionEstado estado) {
		this.estado = Ref.create(estado);
	}

	public Institucion getInstitucion() {
		return institucion.get();
	}

	public void setInstitucion(Institucion institucion) {
		this.institucion = Ref.create(institucion);
	}

	public ProgramaAcademico getProgramaAcademico() {
		return programaAcademico.get();
	}

	public void setProgramaAcademico(ProgramaAcademico programaAcademico) {
		this.programaAcademico = Ref.create(programaAcademico);
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (formacionid != null ? formacionid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof EmpleadoFormacion)) {
			return false;
		}
		EmpleadoFormacion other = (EmpleadoFormacion) object;
		if ((this.formacionid == null && other.formacionid != null)
				|| (this.formacionid != null && !this.formacionid.equals(other.formacionid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.talento.shared.model.EmpleadoFormacion[ formacionid=" + formacionid + " ]";
	}

}
