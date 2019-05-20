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
public class PagoTipo extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long pagoTipoid;

	@Index
	private Long pagoTipoNumero;

	private String pagoTiponombre;

	public PagoTipo() {
	}

	public PagoTipo(Long pagoTipoid) {
		this.pagoTipoid = pagoTipoid;
	}

	public Long getPagoTipoid() {
		return pagoTipoid;
	}

	public void setPagoTipoid(Long pagoTipoid) {
		this.pagoTipoid = pagoTipoid;
	}

	public String getPagoTiponombre() {
		return pagoTiponombre;
	}

	public void setPagoTiponombre(String pagoTiponombre) {
		this.pagoTiponombre = pagoTiponombre;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (pagoTipoid != null ? pagoTipoid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof PagoTipo)) {
			return false;
		}
		PagoTipo other = (PagoTipo) object;
		if ((this.pagoTipoid == null && other.pagoTipoid != null)
				|| (this.pagoTipoid != null && !this.pagoTipoid.equals(other.pagoTipoid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.core.shared.model.PagoTipo[ pagoTipoid=" + pagoTipoid + " ]";
	}

	public Long getPagoTipoNumero() {
		return pagoTipoNumero;
	}

	public void setPagoTipoNumero(Long pagoTipoNumero) {
		this.pagoTipoNumero = pagoTipoNumero;
	}

}
