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

/**
 * 
 * @author Fredi J. Velasco Villarreal - GUANDERA S.A.S.
 */
@Entity
public class NominaPagoNovedad extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long nominapagonovedadid;

	@Index
	@Load
	private Ref<NominaPago> nominaPago;

	@Index
	@Load
	private Ref<NominaNovedad> nominaNovedad;

	private double valorDevengado;
	private double valorDeduccion;

	private List<Key<NominaPagoDetalle>> detalleContratoList;

	public NominaPagoNovedad() {
	}

	public NominaPagoNovedad(Long nominapagonovedadid) {
		this.nominapagonovedadid = nominapagonovedadid;
	}

	public Long getNominanovedadid() {
		return nominapagonovedadid;
	}

	public void setNominanovedadid(Long nominapagonovedadid) {
		this.nominapagonovedadid = nominapagonovedadid;
	}

	public List<Key<NominaPagoDetalle>> getDetalleContratoList() {
		return detalleContratoList;
	}

	public void setDetalleContratoList(List<Key<NominaPagoDetalle>> detalleContratoList) {
		this.detalleContratoList = detalleContratoList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (nominapagonovedadid != null ? nominapagonovedadid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof NominaPagoNovedad)) {
			return false;
		}
		NominaPagoNovedad other = (NominaPagoNovedad) object;
		if ((this.nominapagonovedadid == null && other.nominapagonovedadid != null)
				|| (this.nominapagonovedadid != null && !this.nominapagonovedadid.equals(other.nominapagonovedadid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.core.shared.model.NominaPagoNovedad[ nominanovedad=" + nominapagonovedadid + " ]";
	}

	public NominaPago getNominaPago() {
		return nominaPago.get();
	}

	public void setNominaPago(NominaPago nominaPago) {
		this.nominaPago = Ref.create(nominaPago);
	}

	public NominaNovedad getNominaNovedad() {
		return nominaNovedad.get();
	}

	public void setNominaNovedad(NominaNovedad nominaNovedad) {
		this.nominaNovedad = Ref.create(nominaNovedad);
	}

	public double getValorDevengado() {
		return valorDevengado;
	}

	public void setValorDevengado(double valorDevengado) {
		this.valorDevengado = valorDevengado;
	}

	public double getValorDeduccion() {
		return valorDeduccion;
	}

	public void setValorDeduccion(double valorDeduccion) {
		this.valorDeduccion = valorDeduccion;
	}

}
