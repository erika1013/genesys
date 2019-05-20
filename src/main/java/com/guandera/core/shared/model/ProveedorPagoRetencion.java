/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guandera.core.shared.model;

import java.io.Serializable;
import java.util.Date;

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
public class ProveedorPagoRetencion extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long pagoproveedorretencionid;

	private Double factor;
	private Double valor;

	@Index
	@Load
	Ref<ImpuestoConcepto> impuestoConcepto;

	@Index
	@Load
	Ref<ProveedorPago> pagoproveedor;

	public ProveedorPagoRetencion() {
	}

	public ProveedorPagoRetencion(Long pagoproveedorretencionid) {
		this.pagoproveedorretencionid = pagoproveedorretencionid;
	}

	public Long getPagoproveedorretencionid() {
		return pagoproveedorretencionid;
	}

	public void setPagoproveedorretencionid(Long pagoproveedorretencionid) {
		this.pagoproveedorretencionid = pagoproveedorretencionid;
	}

	public ImpuestoConcepto getImpuestoConcepto() {
		return impuestoConcepto.get();
	}

	public void setImpuestoConcepto(ImpuestoConcepto impuestoConcepto) {
		this.impuestoConcepto = Ref.create(impuestoConcepto);
	}

	public ProveedorPago getPagoproveedor() {
		return pagoproveedor.get();
	}

	public void setPagoproveedor(ProveedorPago pagoproveedor) {
		this.pagoproveedor = Ref.create(pagoproveedor);
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (pagoproveedorretencionid != null ? pagoproveedorretencionid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof ProveedorPagoRetencion)) {
			return false;
		}
		ProveedorPagoRetencion other = (ProveedorPagoRetencion) object;
		if ((this.pagoproveedorretencionid == null && other.pagoproveedorretencionid != null)
				|| (this.pagoproveedorretencionid != null
						&& !this.pagoproveedorretencionid.equals(other.pagoproveedorretencionid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.core.shared.model.ProveedorPagoRetencion[ pagoproveedorretencionid="
				+ pagoproveedorretencionid + " ]";
	}

	public Double getFactor() {
		return factor;
	}

	public void setFactor(Double factor) {
		this.factor = factor;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

}
