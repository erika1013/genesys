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
import com.guandera.talento.shared.model.Competencia;

/**
 * 
 * @author FrediJavier
 */
@Entity
public class EmpleadoCompetencia extends Auditoria implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private Long empleadocompetenciaid;

	private Integer nivel;

	@Index
	@Load
	Ref<Empleado> empleado;

	@Index
	@Load
	Ref<Competencia> competencia;

	public EmpleadoCompetencia() {
	}

	public EmpleadoCompetencia(Long empleadocompetenciaid) {
		this.empleadocompetenciaid = empleadocompetenciaid;
	}

	public Long getEmpleadocompetenciaid() {
		return empleadocompetenciaid;
	}

	public void setEmpleadocompetenciaid(Long empleadocompetenciaid) {
		this.empleadocompetenciaid = empleadocompetenciaid;
	}

	public Integer getNivel() {
		return nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}

	public Empleado getEmpleado() {
		return empleado.get();
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = Ref.create(empleado);
	}

	public Competencia getCompetencia() {
		return competencia.get();
	}

	public void setCompetencia(Competencia competencia) {
		this.competencia = Ref.create(competencia);
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (empleadocompetenciaid != null ? empleadocompetenciaid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof EmpleadoCompetencia)) {
			return false;
		}
		EmpleadoCompetencia other = (EmpleadoCompetencia) object;
		if ((this.empleadocompetenciaid == null && other.empleadocompetenciaid != null)
				|| (this.empleadocompetenciaid != null
						&& !this.empleadocompetenciaid.equals(other.empleadocompetenciaid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.talento.shared.model.EmpleadoCompetencia[ empleadocompetenciaid="
				+ empleadocompetenciaid + " ]";
	}

}
