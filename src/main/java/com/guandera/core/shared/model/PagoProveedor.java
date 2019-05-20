/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guandera.core.shared.model;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
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
public class PagoProveedor extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long pagoproveedor;

	private String pagocodigo;

	private Long pagovalor;

	private String pagoestado;

	@Index
	@Load
	Ref<Proveedor> proveedor;

	private List<Key<PagoProveedorRetencion>> pagoProveedorRetencionList;

	public PagoProveedor() {
	}

	public PagoProveedor(Long pagoproveedor) {
		this.pagoproveedor = pagoproveedor;
	}

	public Long getPagoproveedor() {
		return pagoproveedor;
	}

	public void setPagoproveedor(Long pagoproveedor) {
		this.pagoproveedor = pagoproveedor;
	}

	public String getPagocodigo() {
		return pagocodigo;
	}

	public void setPagocodigo(String pagocodigo) {
		this.pagocodigo = pagocodigo;
	}

	public Long getPagovalor() {
		return pagovalor;
	}

	public void setPagovalor(Long pagovalor) {
		this.pagovalor = pagovalor;
	}

	public String getPagoestado() {
		return pagoestado;
	}

	public void setPagoestado(String pagoestado) {
		this.pagoestado = pagoestado;
	}

	public Proveedor getProveedor() {
		return proveedor.get();
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = Ref.create(proveedor);
	}

	public List<Key<PagoProveedorRetencion>> getPagoProveedorRetencionList() {
		return pagoProveedorRetencionList;
	}

	public void setPagoProveedorRetencionList(List<Key<PagoProveedorRetencion>> pagoProveedorRetencionList) {
		this.pagoProveedorRetencionList = pagoProveedorRetencionList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (pagoproveedor != null ? pagoproveedor.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof PagoProveedor)) {
			return false;
		}
		PagoProveedor other = (PagoProveedor) object;
		if ((this.pagoproveedor == null && other.pagoproveedor != null)
				|| (this.pagoproveedor != null && !this.pagoproveedor.equals(other.pagoproveedor))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.core.shared.model.PagoProveedor[ pagoproveedor=" + pagoproveedor + " ]";
	}

}
