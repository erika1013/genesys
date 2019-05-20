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
public class CobroEstado extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long cobroEstadoid;

	private String cobroEstadonombre;

	public CobroEstado() {
	}

	public CobroEstado(Long cobroEstadoid) {
		this.cobroEstadoid = cobroEstadoid;
	}

	public Long getCobroEstadoid() {
		return cobroEstadoid;
	}

	public void setCobroEstadoid(Long cobroEstadoid) {
		this.cobroEstadoid = cobroEstadoid;
	}

	public String getCobroEstadonombre() {
		return cobroEstadonombre;
	}

	public void setCobroEstadonombre(String cobroEstadonombre) {
		this.cobroEstadonombre = cobroEstadonombre;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (cobroEstadoid != null ? cobroEstadoid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof CobroEstado)) {
			return false;
		}
		CobroEstado other = (CobroEstado) object;
		if ((this.cobroEstadoid == null && other.cobroEstadoid != null)
				|| (this.cobroEstadoid != null && !this.cobroEstadoid.equals(other.cobroEstadoid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.core.shared.model.CobroEstado[ cobroEstadoid=" + cobroEstadoid + " ]";
	}

}
