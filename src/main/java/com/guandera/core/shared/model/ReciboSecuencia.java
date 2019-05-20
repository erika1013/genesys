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
public class ReciboSecuencia extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long reciboSecuenciaid;

	private Integer reciboSecuencia;

	public ReciboSecuencia() {
	}

	public ReciboSecuencia(Long reciboSecuenciaid) {
		this.reciboSecuenciaid = reciboSecuenciaid;
	}

	public Long getReciboSecuenciaid() {
		return reciboSecuenciaid;
	}

	public void setReciboSecuenciaid(Long reciboSecuenciaid) {
		this.reciboSecuenciaid = reciboSecuenciaid;
	}

	public Integer getReciboSecuencia() {
		return reciboSecuencia;
	}

	public void setReciboSecuencia(Integer reciboSecuencia) {
		this.reciboSecuencia = reciboSecuencia;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (reciboSecuenciaid != null ? reciboSecuenciaid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof ReciboSecuencia)) {
			return false;
		}
		ReciboSecuencia other = (ReciboSecuencia) object;
		if ((this.reciboSecuenciaid == null && other.reciboSecuenciaid != null)
				|| (this.reciboSecuenciaid != null && !this.reciboSecuenciaid.equals(other.reciboSecuenciaid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.core.shared.model.ReciboSecuencia[ reciboSecuenciaid=" + reciboSecuenciaid + " ]";
	}

}
