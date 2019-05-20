/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guandera.talento.empleado.shared.model;

import java.io.Serializable;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;
import com.guandera.core.shared.model.Auditoria;
import com.guandera.talento.nomina.shared.model.NominaConcepto;

/**
 * 
 * @author Fredi J. Velasco Villarreal - GUANDERA S.A.S.
 */
@Entity
public class EmpleadoContratoDetalle extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long detallecontratoid;

	@Index
	@Load
	Ref<EmpleadoContrato> empleadoContrato;
	@Index
	@Load
	private Ref<NominaConcepto> nominaConcepto;

	private boolean factor;

	private double valor;

	public EmpleadoContratoDetalle() {
	}

	public EmpleadoContratoDetalle(Long detallecontratoid) {
		this.detallecontratoid = detallecontratoid;
	}

	public Long getDetallecontratoid() {
		return detallecontratoid;
	}

	public void setDetallecontratoid(Long detallecontratoid) {
		this.detallecontratoid = detallecontratoid;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public EmpleadoContrato getEmpleadoContrato() {
		return empleadoContrato.get();
	}

	public void setEmpleadoContrato(EmpleadoContrato empleadoContrato) {
		this.empleadoContrato = Ref.create(empleadoContrato);
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (detallecontratoid != null ? detallecontratoid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof EmpleadoContratoDetalle)) {
			return false;
		}
		EmpleadoContratoDetalle other = (EmpleadoContratoDetalle) object;
		if ((this.detallecontratoid == null && other.detallecontratoid != null)
				|| (this.detallecontratoid != null && !this.detallecontratoid.equals(other.detallecontratoid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.core.shared.model.DetalleContrato[ detallecontratoid=" + detallecontratoid + " ]";
	}

	public NominaConcepto getNominaConcepto() {
		return nominaConcepto.get();
	}

	public void setNominaConcepto(NominaConcepto nominaConcepto) {
		this.nominaConcepto = Ref.create(nominaConcepto);
	}

	public boolean isFactor() {
		return factor;
	}

	public void setFactor(boolean factor) {
		this.factor = factor;
	}

}
