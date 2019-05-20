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
public class BancoPagoEstado extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long bancoPagoEstadoid;

	private String bancoPagoEstadonombre;

	public BancoPagoEstado() {
	}

	public BancoPagoEstado(Long bancoPagoEstadoid) {
		this.bancoPagoEstadoid = bancoPagoEstadoid;
	}

	public Long getBancoPagoEstadoid() {
		return bancoPagoEstadoid;
	}

	public void setBancoPagoEstadoid(Long bancoPagoEstadoid) {
		this.bancoPagoEstadoid = bancoPagoEstadoid;
	}

	public String getBancoPagoEstadonombre() {
		return bancoPagoEstadonombre;
	}

	public void setBancoPagoEstadonombre(String bancoPagoEstadonombre) {
		this.bancoPagoEstadonombre = bancoPagoEstadonombre;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (bancoPagoEstadoid != null ? bancoPagoEstadoid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof BancoPagoEstado)) {
			return false;
		}
		BancoPagoEstado other = (BancoPagoEstado) object;
		if ((this.bancoPagoEstadoid == null && other.bancoPagoEstadoid != null)
				|| (this.bancoPagoEstadoid != null && !this.bancoPagoEstadoid.equals(other.bancoPagoEstadoid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.core.shared.model.BancoPagoEstado[ bancoPagoEstadoid=" + bancoPagoEstadoid + " ]";
	}

}
