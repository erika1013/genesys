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

/**
 * 
 * @author Fredi J. Velasco Villarreal - GUANDERA S.A.S.
 */
@Entity
public class SeguridadSocial extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long seguridadsocialid;

	@Index
	@Load
	Ref<EmpleadoContratoDetalle> detalleContrato;

	@Index
	@Load
	Ref<EmpresaSeguridadSocial> empresaSeguridadSocial;

	public SeguridadSocial() {
	}

	public SeguridadSocial(Long seguridadsocialid) {
		this.seguridadsocialid = seguridadsocialid;
	}

	public Long getSeguridadsocialid() {
		return seguridadsocialid;
	}

	public void setSeguridadsocialid(Long seguridadsocialid) {
		this.seguridadsocialid = seguridadsocialid;
	}

	public EmpleadoContratoDetalle getDetalleContrato() {
		return detalleContrato.get();
	}

	public void setDetalleContrato(EmpleadoContratoDetalle detalleContrato) {
		this.detalleContrato = Ref.create(detalleContrato);
	}

	public EmpresaSeguridadSocial getEmpresaSeguridadSocial() {
		return empresaSeguridadSocial.get();
	}

	public void setEmpresaSeguridadSocial(EmpresaSeguridadSocial empresaSeguridadSocial) {
		this.empresaSeguridadSocial = Ref.create(empresaSeguridadSocial);
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (seguridadsocialid != null ? seguridadsocialid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof SeguridadSocial)) {
			return false;
		}
		SeguridadSocial other = (SeguridadSocial) object;
		if ((this.seguridadsocialid == null && other.seguridadsocialid != null)
				|| (this.seguridadsocialid != null && !this.seguridadsocialid.equals(other.seguridadsocialid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.core.shared.model.SeguridadSocial[ seguridadsocialid=" + seguridadsocialid + " ]";
	}

}
