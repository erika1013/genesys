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
public class ProveedorRetencion extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long proveedorretencionid;

	@Index
	private Long codigoRetencion;

	private String nombreRetencion;

	@Index
	@Load
	Ref<ImpuestoConcepto> impuestoConcepto;

	@Index
	@Load
	Ref<Proveedor> proveedor;

	@Index
	@Load
	private Ref<PagoTipo> pagoTipo;

	public ProveedorRetencion() {
	}

	public ProveedorRetencion(Long proveedorretencionid) {
		this.proveedorretencionid = proveedorretencionid;
	}

	public Long getProveedorretencionid() {
		return proveedorretencionid;
	}

	public void setProveedorretencionid(Long proveedorretencionid) {
		this.proveedorretencionid = proveedorretencionid;
	}

	public ImpuestoConcepto getImpuestoConcepto() {
		return impuestoConcepto.get();
	}

	public void setImpuestoConcepto(ImpuestoConcepto impuestoConcepto) {
		this.impuestoConcepto = Ref.create(impuestoConcepto);
	}

	public Proveedor getProveedor() {
		return proveedor.get();
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = Ref.create(proveedor);
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (proveedorretencionid != null ? proveedorretencionid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof ProveedorRetencion)) {
			return false;
		}
		ProveedorRetencion other = (ProveedorRetencion) object;
		if ((this.proveedorretencionid == null && other.proveedorretencionid != null)
				|| (this.proveedorretencionid != null
						&& !this.proveedorretencionid.equals(other.proveedorretencionid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.core.shared.model.ProveedorRetencion[ proveedorretencionid=" + proveedorretencionid + " ]";
	}

	public PagoTipo getPagoTipo() {
		return pagoTipo.get();
	}

	public void setPagoTipo(PagoTipo pagoTipo) {
		this.pagoTipo = Ref.create(pagoTipo);
	}

	public String getNombreRetencion() {
		return nombreRetencion;
	}

	public void setNombreRetencion(String nombreRetencion) {
		this.nombreRetencion = nombreRetencion;
	}

	public Long getCodigoRetencion() {
		return codigoRetencion;
	}

	public void setCodigoRetencion(Long codigoRetencion) {
		this.codigoRetencion = codigoRetencion;
	}

}
