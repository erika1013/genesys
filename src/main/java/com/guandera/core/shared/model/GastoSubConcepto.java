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
public class GastoSubConcepto extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long subConceptoid;

	@Index
	private Integer codigo;
	private String nombre;

	@Index
	@Load
	Ref<GastoConcepto> concepto;

	public GastoSubConcepto() {
	}

	public GastoSubConcepto(Long subConceptoid) {
		this.subConceptoid = subConceptoid;
	}

	public Long getGastoSubConceptoid() {
		return subConceptoid;
	}

	public void setGastoSubConceptoid(Long subConceptoid) {
		this.subConceptoid = subConceptoid;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (subConceptoid != null ? subConceptoid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof GastoSubConcepto)) {
			return false;
		}
		GastoSubConcepto other = (GastoSubConcepto) object;
		if ((this.subConceptoid == null && other.subConceptoid != null)
				|| (this.subConceptoid != null && !this.subConceptoid.equals(other.subConceptoid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.core.shared.model.GastoSubConcepto[ subConceptoid=" + subConceptoid + " ]";
	}

	public GastoConcepto getConcepto() {
		return concepto.get();
	}

	public void setConcepto(GastoConcepto concepto) {
		this.concepto = Ref.create(concepto);
	}
}
