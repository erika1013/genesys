/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guandera.talento.nomina.shared.model;

import java.io.Serializable;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;
import com.guandera.core.shared.model.Auditoria;

/**
 * 
 * @author Fredi J. Velasco Villarreal - GUANDERA S.A.S.
 */
@Entity
public class NominaProvision extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long nominaprovisionid;

	@Index
	@Load
	Ref<NominaPago> pagoNomina;

	public NominaProvision() {
	}

	public NominaProvision(Long nominaprovisionid) {
		this.nominaprovisionid = nominaprovisionid;
	}

	public Long getNominaprovisionid() {
		return nominaprovisionid;
	}

	public void setNominaprovisionid(Long nominaprovisionid) {
		this.nominaprovisionid = nominaprovisionid;
	}

	public NominaPago getPagoNomina() {
		return pagoNomina.get();
	}

	public void setPagoNomina(NominaPago pagoNomina) {
		this.pagoNomina = Ref.create(pagoNomina);
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (nominaprovisionid != null ? nominaprovisionid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof NominaProvision)) {
			return false;
		}
		NominaProvision other = (NominaProvision) object;
		if ((this.nominaprovisionid == null && other.nominaprovisionid != null)
				|| (this.nominaprovisionid != null && !this.nominaprovisionid.equals(other.nominaprovisionid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.core.shared.model.NominaProvision[ nominaprovisionid=" + nominaprovisionid + " ]";
	}

}
