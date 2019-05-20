/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guandera.core.shared.model;

import java.io.Serializable;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * 
 * @author Fredi J. Velasco Villarreal - GUANDERA S.A.S.
 */
@Entity
public class CuentaCobroEstado extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long cuentaCobroEstadoid;

	private String cuentaCobroEstadoNombre;

	public CuentaCobroEstado() {
	}

	public CuentaCobroEstado(Long cuentaCobroEstadoid) {
		this.cuentaCobroEstadoid = cuentaCobroEstadoid;
	}

	public Long getCuentaCobroEstadoid() {
		return cuentaCobroEstadoid;
	}

	public void setCuentaCobroEstadoid(Long cuentaCobroEstadoid) {
		this.cuentaCobroEstadoid = cuentaCobroEstadoid;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (cuentaCobroEstadoid != null ? cuentaCobroEstadoid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof CuentaCobroEstado)) {
			return false;
		}
		CuentaCobroEstado other = (CuentaCobroEstado) object;
		if ((this.cuentaCobroEstadoid == null && other.cuentaCobroEstadoid != null)
				|| (this.cuentaCobroEstadoid != null && !this.cuentaCobroEstadoid.equals(other.cuentaCobroEstadoid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.core.shared.model.CuentaCobroEstado[ cuentaCobroEstadoid=" + cuentaCobroEstadoid + " ]";
	}

	public String getCuentaCobroEstadoNombre() {
		return cuentaCobroEstadoNombre;
	}

	public void setCuentaCobroEstadoNombre(String cuentaCobroEstadoNombre) {
		this.cuentaCobroEstadoNombre = cuentaCobroEstadoNombre;
	}

}
