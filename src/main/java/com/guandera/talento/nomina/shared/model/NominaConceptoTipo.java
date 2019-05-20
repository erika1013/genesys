/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guandera.talento.nomina.shared.model;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.guandera.core.shared.model.Auditoria;

/**
 * 
 * @author Fredi J. Velasco Villarreal - GUANDERA S.A.S.
 */
@Entity
public class NominaConceptoTipo extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;


	@Id
	private Long conceptotipoid;

	private String conceptotiponombre;

	@Index
	private boolean obligatorio;

	@Index
	private Long tipo;

	@Index
	private Long conceptopor;

	

	public NominaConceptoTipo() {
	}

	public NominaConceptoTipo(Long conceptotipoid) {
		this.conceptotipoid = conceptotipoid;
	}

	public Long getConceptotipoid() {
		return conceptotipoid;
	}

	public void setConceptotipoid(Long conceptotipoid) {
		this.conceptotipoid = conceptotipoid;
	}

	public String getConceptotiponombre() {
		return conceptotiponombre;
	}

	public void setConceptotiponombre(String conceptotiponombre) {
		this.conceptotiponombre = conceptotiponombre;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (conceptotipoid != null ? conceptotipoid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof NominaConceptoTipo)) {
			return false;
		}
		NominaConceptoTipo other = (NominaConceptoTipo) object;
		if ((this.conceptotipoid == null && other.conceptotipoid != null)
				|| (this.conceptotipoid != null && !this.conceptotipoid.equals(other.conceptotipoid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.core.shared.model.ConceptoTipo[ conceptotipoid=" + conceptotipoid + " ]";
	}

	public boolean isObligatorio() {
		return obligatorio;
	}

	public void setObligatorio(boolean obligatorio) {
		this.obligatorio = obligatorio;
	}

	public Long getTipo() {
		return tipo;
	}

	public void setTipo(Long tipo) {
		this.tipo = tipo;
	}

	public Long getConceptopor() {
		return conceptopor;
	}

	public void setConceptopor(Long conceptopor) {
		this.conceptopor = conceptopor;
	}

}
