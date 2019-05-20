/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guandera.core.shared.model;

import java.io.Serializable;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;

/**
 * 
 * @author Fredi J. Velasco Villarreal - GUANDERA S.A.S.
 */
@Entity
public class ReciboReImpresion implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long reImpresionid;

	@Index
	@Load
	Ref<ReciboPago> reciboPago;

	public ReciboReImpresion() {
	}

	public ReciboReImpresion(Long reImpresionid) {
		this.reImpresionid = reImpresionid;
	}

	public Long getReImpresionid() {
		return reImpresionid;
	}

	public void setReImpresionid(Long reImpresionid) {
		this.reImpresionid = reImpresionid;
	}

	public ReciboPago getReciboPago() {
		return reciboPago.get();
	}

	public void setReciboPago(ReciboPago reciboPago) {
		this.reciboPago = Ref.create(reciboPago);
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (reImpresionid != null ? reImpresionid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof ReciboReImpresion)) {
			return false;
		}
		ReciboReImpresion other = (ReciboReImpresion) object;
		if ((this.reImpresionid == null && other.reImpresionid != null)
				|| (this.reImpresionid != null && !this.reImpresionid.equals(other.reImpresionid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.core.shared.model.ReciboPagoDetalle[ reImpresionid=" + reImpresionid + " ]";
	}

}
