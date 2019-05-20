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

/**
 * 
 * @author FrediJavier
 */
@Entity
public class EmpleadoObservacion extends Auditoria implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private Long observacionid;

	private Date fecha;

	private String observacion;

	@Index
	@Load
	Ref<Empleado> empleado;

	public EmpleadoObservacion() {
	}

	public EmpleadoObservacion(Long observacionid) {
		this.observacionid = observacionid;
	}

	public Long getObservacionid() {
		return observacionid;
	}

	public void setObservacionid(Long observacionid) {
		this.observacionid = observacionid;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Empleado getEmpleado() {
		return empleado.get();
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = Ref.create(empleado);
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (observacionid != null ? observacionid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof EmpleadoObservacion)) {
			return false;
		}
		EmpleadoObservacion other = (EmpleadoObservacion) object;
		if ((this.observacionid == null && other.observacionid != null)
				|| (this.observacionid != null && !this.observacionid.equals(other.observacionid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.talento.shared.model.EmpleadoObservacion[ observacionid=" + observacionid + " ]";
	}

}
