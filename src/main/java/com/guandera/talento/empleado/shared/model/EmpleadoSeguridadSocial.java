package com.guandera.talento.empleado.shared.model;

import java.io.Serializable;

import com.googlecode.objectify.Ref;

import java.io.Serializable;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;
import com.guandera.core.shared.model.Auditoria;

@Entity
public class EmpleadoSeguridadSocial extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long empleadoSeguridadSocialid;

	@Index
	@Load
	private Ref<Empleado> empleado;

	private String concepto;

	@Index
	@Load
	Ref<EmpresaSeguridadSocial> empresaseguridadSocial;

	public EmpleadoSeguridadSocial() {
	}

	public EmpleadoSeguridadSocial(Long empleadoSeguridadSocialid) {
		this.empleadoSeguridadSocialid = empleadoSeguridadSocialid;
	}

	public Long getEmpleadoSeguridadSocialid() {
		return empleadoSeguridadSocialid;
	}

	public void setEmpleadoSeguridadSocialid(Long empleadoSeguridadSocialid) {
		this.empleadoSeguridadSocialid = empleadoSeguridadSocialid;
	}

	public EmpresaSeguridadSocial getEmpresaseguridadSocial() {
		return empresaseguridadSocial.get();
	}

	public void setEmpresaseguridadSocial(EmpresaSeguridadSocial empresaseguridadSocial) {
		this.empresaseguridadSocial = Ref.create(empresaseguridadSocial);
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
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
		hash += (empleadoSeguridadSocialid != null ? empleadoSeguridadSocialid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof EmpleadoSeguridadSocial)) {
			return false;
		}
		EmpleadoSeguridadSocial other = (EmpleadoSeguridadSocial) object;
		if ((this.empleadoSeguridadSocialid == null && other.empleadoSeguridadSocialid != null)
				|| (this.empleadoSeguridadSocialid != null
						&& !this.empleadoSeguridadSocialid.equals(other.empleadoSeguridadSocialid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.core.shared.model.EmpleadoSeguridadSocial[ empleadoSeguridadSocialid="
				+ empleadoSeguridadSocialid + " ]";
	}
}
