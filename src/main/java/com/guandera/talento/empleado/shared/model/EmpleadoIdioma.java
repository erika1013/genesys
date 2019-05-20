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
import com.guandera.talento.shared.model.Idioma;

/**
 * 
 * @author FrediJavier
 */
@Entity
public class EmpleadoIdioma extends Auditoria implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private Long empleadoidiomaid;

	private Integer nivel;

	@Index
	@Load
	Ref<Empleado> empleado;

	@Index
	@Load
	Ref<Idioma> idioma;

	private String observacion;

	public EmpleadoIdioma() {
	}

	public EmpleadoIdioma(Long empleadoidiomaid) {
		this.empleadoidiomaid = empleadoidiomaid;
	}

	public Long getEmpleadoidiomaid() {
		return empleadoidiomaid;
	}

	public void setEmpleadoidiomaid(Long empleadoidiomaid) {
		this.empleadoidiomaid = empleadoidiomaid;
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

	public Idioma getIdioma() {
		return idioma.get();
	}

	public void setIdioma(Idioma idioma) {
		this.idioma = Ref.create(idioma);
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (empleadoidiomaid != null ? empleadoidiomaid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof EmpleadoIdioma)) {
			return false;
		}
		EmpleadoIdioma other = (EmpleadoIdioma) object;
		if ((this.empleadoidiomaid == null && other.empleadoidiomaid != null)
				|| (this.empleadoidiomaid != null && !this.empleadoidiomaid.equals(other.empleadoidiomaid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.talento.shared.model.EmpleadoIdioma[ empleadoidiomaid=" + empleadoidiomaid + " ]";
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

}
