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
public class NominaPagoDetalle extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long nominapagodetalleid;

	private double nominapagodetallevalorTotal;

	private double valordevengado;
	private double valordeduccion;
	private String empresa;

	@Index
	private Long conceptopor;
	private String empleado;

	@Index
	@Load
	Ref<NominaPago> pagoNomina;

	@Index
	@Load
	private Ref<NominaConcepto> nominaConcepto;

	public NominaPagoDetalle() {
	}

	public NominaPagoDetalle(Long nominapagodetalleid) {
		this.nominapagodetalleid = nominapagodetalleid;
	}

	public Long getNominapagodetalleid() {
		return nominapagodetalleid;
	}

	public void setNominapagodetalleid(Long nominapagodetalleid) {
		this.nominapagodetalleid = nominapagodetalleid;
	}

	public double getNominapagodetallevalorTotal() {
		return nominapagodetallevalorTotal;
	}

	public void setNominapagodetallevalorTotal(double nominapagodetallevalorTotal) {
		this.nominapagodetallevalorTotal = nominapagodetallevalorTotal;
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
		hash += (nominapagodetalleid != null ? nominapagodetalleid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof NominaPagoDetalle)) {
			return false;
		}
		NominaPagoDetalle other = (NominaPagoDetalle) object;
		if ((this.nominapagodetalleid == null && other.nominapagodetalleid != null)
				|| (this.nominapagodetalleid != null && !this.nominapagodetalleid.equals(other.nominapagodetalleid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.core.shared.model.NominaPagoDetalle[ nominapagodetalleid=" + nominapagodetalleid + " ]";
	}

	public double getValordevengado() {
		return valordevengado;
	}

	public void setValordevengado(double valordevengado) {
		this.valordevengado = valordevengado;
	}

	public double getValordeduccion() {
		return valordeduccion;
	}

	public void setValordeduccion(double valordeduccion) {
		this.valordeduccion = valordeduccion;
	}

	public NominaConcepto getNominaConcepto() {
		return nominaConcepto.get();
	}

	public void setNominaConcepto(NominaConcepto concepto) {
		this.nominaConcepto = Ref.create(concepto);
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getEmpleado() {
		return empleado;
	}

	public void setEmpleado(String empleado) {
		this.empleado = empleado;
	}

	public Long getConceptopor() {
		return conceptopor;
	}

	public void setConceptopor(Long conceptopor) {
		this.conceptopor = conceptopor;
	}

}
