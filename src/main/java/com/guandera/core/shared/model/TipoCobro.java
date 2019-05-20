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
public class TipoCobro extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long tipoCobroid;

	private String tipoCobronombre;

	public TipoCobro() {
	}

	public TipoCobro(Long tipoCobroid) {
		this.tipoCobroid = tipoCobroid;
	}

	public Long getTipoCobroid() {
		return tipoCobroid;
	}

	public void setTipoCobroid(Long tipoCobroid) {
		this.tipoCobroid = tipoCobroid;
	}

	public String getTipoCobronombre() {
		return tipoCobronombre;
	}

	public void setTipoCobronombre(String tipoCobronombre) {
		this.tipoCobronombre = tipoCobronombre;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (tipoCobroid != null ? tipoCobroid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof TipoCobro)) {
			return false;
		}
		TipoCobro other = (TipoCobro) object;
		if ((this.tipoCobroid == null && other.tipoCobroid != null)
				|| (this.tipoCobroid != null && !this.tipoCobroid.equals(other.tipoCobroid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.core.shared.model.TipoCobro[ tipoCobroid=" + tipoCobroid + " ]";
	}

}
