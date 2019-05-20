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
public class ReciboEstado extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long reciboEstadoid;

	private String reciboEstadonombre;

	public ReciboEstado() {
	}

	public ReciboEstado(Long reciboEstadoid) {
		this.reciboEstadoid = reciboEstadoid;
	}

	public Long getReciboEstadoid() {
		return reciboEstadoid;
	}

	public void setReciboEstadoid(Long reciboEstadoid) {
		this.reciboEstadoid = reciboEstadoid;
	}

	public String getReciboEstadonombre() {
		return reciboEstadonombre;
	}

	public void setReciboEstadonombre(String reciboEstadonombre) {
		this.reciboEstadonombre = reciboEstadonombre;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (reciboEstadoid != null ? reciboEstadoid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof ReciboEstado)) {
			return false;
		}
		ReciboEstado other = (ReciboEstado) object;
		if ((this.reciboEstadoid == null && other.reciboEstadoid != null)
				|| (this.reciboEstadoid != null && !this.reciboEstadoid.equals(other.reciboEstadoid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.core.shared.model.ReciboEstado[ reciboEstadoid=" + reciboEstadoid + " ]";
	}

}
