/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guandera.talento.nomina.shared.model;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;
import com.guandera.core.shared.model.Auditoria;
import com.guandera.talento.empleado.shared.model.EmpleadoContratoDetalle;

/**
 * 
 * @author Fredi J. Velasco Villarreal - GUANDERA S.A.S.
 */
@Entity
public class NominaConcepto extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long nominaconceptoid;

	@Index
	private Integer nominaconceptocodigo;

	private String nominaconceptonombre;

	private boolean nominaconceptofactor;

	private double nominaconceptoValor;


	@Index
	@Load
	Ref<NominaConceptoTipo> conceptoTipo;

	public NominaConcepto() {
	}

	public NominaConcepto(Long nominaconceptoid) {
		this.nominaconceptoid = nominaconceptoid;
	}

	public Integer getNominaconceptocodigo() {
		return nominaconceptocodigo;
	}

	public void setNominaconceptocodigo(Integer nominaconceptocodigo) {
		this.nominaconceptocodigo = nominaconceptocodigo;
	}

	public Long getNominaconceptoid() {
		return nominaconceptoid;
	}

	public void setNominaconceptoid(Long nominaconceptoid) {
		this.nominaconceptoid = nominaconceptoid;
	}

	public String getNominaconceptonombre() {
		return nominaconceptonombre;
	}

	public void setNominaconceptonombre(String nominaconceptonombre) {
		this.nominaconceptonombre = nominaconceptonombre;
	}

	public boolean getNominaconceptofactor() {
		return nominaconceptofactor;
	}

	public void setNominaconceptofactor(boolean nominaconceptofactor) {
		this.nominaconceptofactor = nominaconceptofactor;
	}



	public NominaConceptoTipo getConceptoTipo() {
		return conceptoTipo.get();
	}

	public void setConceptoTipo(NominaConceptoTipo conceptoTipo) {
		this.conceptoTipo = Ref.create(conceptoTipo);
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (nominaconceptoid != null ? nominaconceptoid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof NominaConcepto)) {
			return false;
		}
		NominaConcepto other = (NominaConcepto) object;
		if ((this.nominaconceptoid == null && other.nominaconceptoid != null)
				|| (this.nominaconceptoid != null && !this.nominaconceptoid.equals(other.nominaconceptoid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.core.shared.model.NominaConcepto[ nominaconcepto=" + nominaconceptoid + " ]";
	}

	public double getNominaconceptoValor() {
		return nominaconceptoValor;
	}

	public void setNominaconceptoValor(double nominaconceptoValor) {
		this.nominaconceptoValor = nominaconceptoValor;
	}

}
