/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guandera.core.shared.model;

import java.io.Serializable;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

/**
 * 
 * @author Fredi J. Velasco Villarreal - GUANDERA S.A.S.
 */
@Entity
public class CobroControl extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long cobroControlid;

	@Index
	private String periodo;

	public CobroControl() {
	}

	public CobroControl(Long cobroControlid) {
		this.cobroControlid = cobroControlid;
	}

	public Long getCobroControlid() {
		return cobroControlid;
	}

	public void setCobroControlid(Long cobroControlid) {
		this.cobroControlid = cobroControlid;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (cobroControlid != null ? cobroControlid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof CobroControl)) {
			return false;
		}
		CobroControl other = (CobroControl) object;
		if ((this.cobroControlid == null && other.cobroControlid != null)
				|| (this.cobroControlid != null && !this.cobroControlid.equals(other.cobroControlid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.core.shared.model.CobroControl[ cobroControlid=" + cobroControlid + " ]";
	}

}
