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
public class TipoPersona extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long tipopersonaid;

	private String tipopersonanombre;

	public TipoPersona() {
	}

	public TipoPersona(Long tipopersonaid) {
		this.tipopersonaid = tipopersonaid;
	}

	public Long getTipopersonaid() {
		return tipopersonaid;
	}

	public void setTipopersonaid(Long tipopersonaid) {
		this.tipopersonaid = tipopersonaid;
	}

	public String getTipopersonanombre() {
		return tipopersonanombre;
	}

	public void setTipopersonanombre(String tipopersonanombre) {
		this.tipopersonanombre = tipopersonanombre;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (tipopersonaid != null ? tipopersonaid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof TipoPersona)) {
			return false;
		}
		TipoPersona other = (TipoPersona) object;
		if ((this.tipopersonaid == null && other.tipopersonaid != null)
				|| (this.tipopersonaid != null && !this.tipopersonaid.equals(other.tipopersonaid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.core.shared.model.TipoPersona[ tipopersonaid=" + tipopersonaid + " ]";
	}

}
