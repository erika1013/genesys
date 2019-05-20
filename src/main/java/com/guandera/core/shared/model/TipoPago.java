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
public class TipoPago extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long tipoPagoid;

	private String tipoPagonombre;

	public TipoPago() {
	}

	public TipoPago(Long tipoPagoid) {
		this.tipoPagoid = tipoPagoid;
	}

	public Long getTipoPagoid() {
		return tipoPagoid;
	}

	public void setTipoPagoid(Long tipoPagoid) {
		this.tipoPagoid = tipoPagoid;
	}

	public String getTipoPagonombre() {
		return tipoPagonombre;
	}

	public void setTipoPagonombre(String tipoPagonombre) {
		this.tipoPagonombre = tipoPagonombre;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (tipoPagoid != null ? tipoPagoid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof TipoPago)) {
			return false;
		}
		TipoPago other = (TipoPago) object;
		if ((this.tipoPagoid == null && other.tipoPagoid != null)
				|| (this.tipoPagoid != null && !this.tipoPagoid.equals(other.tipoPagoid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.core.shared.model.TipoPago[ tipoPagoid=" + tipoPagoid + " ]";
	}

}
