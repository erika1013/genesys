/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guandera.core.shared.model;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * 
 * @author Fredi J. Velasco Villarreal - GUANDERA S.A.S.
 */
@Entity
public class TipoAcudiente extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long tipoacudienteid;

	private String tipoacudientenombre;

	public TipoAcudiente() {
	}

	public TipoAcudiente(Long tipoacudienteid) {
		this.tipoacudienteid = tipoacudienteid;
	}

	public Long getTipoacudienteid() {
		return tipoacudienteid;
	}

	public void setTipoacudienteid(Long tipoacudienteid) {
		this.tipoacudienteid = tipoacudienteid;
	}

	public String getTipoacudientenombre() {
		return tipoacudientenombre;
	}

	public void setTipoacudientenombre(String tipoacudientenombre) {
		this.tipoacudientenombre = tipoacudientenombre;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (tipoacudienteid != null ? tipoacudienteid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof TipoAcudiente)) {
			return false;
		}
		TipoAcudiente other = (TipoAcudiente) object;
		if ((this.tipoacudienteid == null && other.tipoacudienteid != null)
				|| (this.tipoacudienteid != null && !this.tipoacudienteid.equals(other.tipoacudienteid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.core.shared.model.TipoAcudiente[ tipoacudienteid=" + tipoacudienteid + " ]";
	}

}
