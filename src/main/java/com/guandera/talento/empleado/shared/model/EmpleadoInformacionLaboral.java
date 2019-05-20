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
public class EmpleadoInformacionLaboral extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long empleadoInformacionLaboralid;

	private String cargo;

	private String empresa;

	private Date fechaInicio;

	private Date fechaRetiro;

	private String jefe;

	@Index
	@Load
	Ref<Empleado> empleado;

	public EmpleadoInformacionLaboral() {
	}

	public EmpleadoInformacionLaboral(Long empleadoInformacionLaboralid) {
		this.empleadoInformacionLaboralid = empleadoInformacionLaboralid;
	}

	public Long getEmpleadoid() {
		return empleadoInformacionLaboralid;
	}

	public void setEmpleadoid(Long empleadoInformacionLaboralid) {
		this.empleadoInformacionLaboralid = empleadoInformacionLaboralid;
	}

	public Empleado getEmpleado() {
		return empleado.get();
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = Ref.create(empleado);
	}

	public String getCargo() {
		return cargo;
	}

	public void setcargo(String cargo) {
		this.cargo = cargo;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaRetiro() {
		return fechaRetiro;
	}

	public void setFechaRetiro(Date fechaRetiro) {
		this.fechaRetiro = fechaRetiro;
	}

	public String getJefe() {
		return jefe;
	}

	public void setJefe(String jefe) {
		this.jefe = jefe;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (empleadoInformacionLaboralid != null ? empleadoInformacionLaboralid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {

		if (!(object instanceof EmpleadoInformacionLaboral)) {
			return false;
		}
		EmpleadoInformacionLaboral other = (EmpleadoInformacionLaboral) object;
		if ((this.empleadoInformacionLaboralid == null && other.empleadoInformacionLaboralid != null)
				|| (this.empleadoInformacionLaboralid != null
						&& !this.empleadoInformacionLaboralid.equals(other.empleadoInformacionLaboralid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.core.shared.model.EmpleadoInformacionLaboral[ empleadoInformacionLaboralid="
				+ empleadoInformacionLaboralid + " ]";
	}

}
