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
public class FacturaEstado extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long facturaEstadoid;

	private String facturaEstadoNombre;

	public FacturaEstado() {
	}

	public FacturaEstado(Long facturaEstadoid) {
		this.facturaEstadoid = facturaEstadoid;
	}

	public Long getFacturaEstadoid() {
		return facturaEstadoid;
	}

	public void setFacturaEstadoid(Long facturaEstadoid) {
		this.facturaEstadoid = facturaEstadoid;
	}

	public String getFacturaEstadoNombre() {
		return facturaEstadoNombre;
	}

	public void setFacturaEstadoNombre(String facturaEstadoNombre) {
		this.facturaEstadoNombre = facturaEstadoNombre;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (facturaEstadoid != null ? facturaEstadoid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof FacturaEstado)) {
			return false;
		}
		FacturaEstado other = (FacturaEstado) object;
		if ((this.facturaEstadoid == null && other.facturaEstadoid != null)
				|| (this.facturaEstadoid != null && !this.facturaEstadoid.equals(other.facturaEstadoid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.core.shared.model.FacturaEstado[ facturaEstadoid=" + facturaEstadoid + " ]";
	}

}
