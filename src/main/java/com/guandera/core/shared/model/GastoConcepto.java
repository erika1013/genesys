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
public class GastoConcepto extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long gastoConceptoid;

	@Index
	private Integer gastoConceptocodigo;
	private String gastoConceptonombre;

	public GastoConcepto() {
	}

	public GastoConcepto(Long gastoConceptoid) {
		this.gastoConceptoid = gastoConceptoid;
	}

	public Long getGastoConceptoid() {
		return gastoConceptoid;
	}

	public void setGastoConceptoid(Long gastoConceptoid) {
		this.gastoConceptoid = gastoConceptoid;
	}

	public String getGastoConceptonombre() {
		return gastoConceptonombre;
	}

	public void setGastoConceptonombre(String gastoConceptonombre) {
		this.gastoConceptonombre = gastoConceptonombre;
	}

	public Integer getGastoConceptocodigo() {
		return gastoConceptocodigo;
	}

	public void setGastoConceptocodigo(Integer gastoConceptocodigo) {
		this.gastoConceptocodigo = gastoConceptocodigo;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (gastoConceptoid != null ? gastoConceptoid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof GastoConcepto)) {
			return false;
		}
		GastoConcepto other = (GastoConcepto) object;
		if ((this.gastoConceptoid == null && other.gastoConceptoid != null)
				|| (this.gastoConceptoid != null && !this.gastoConceptoid.equals(other.gastoConceptoid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.core.shared.model.GastoConcepto[ gastoConceptoid=" + gastoConceptoid + " ]";
	}

}
