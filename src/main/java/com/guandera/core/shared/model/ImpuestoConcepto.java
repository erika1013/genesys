/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guandera.core.shared.model;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

/**
 * 
 * @author Fredi J. Velasco Villarreal - GUANDERA S.A.S.
 */
@Entity
public class ImpuestoConcepto extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long impuestoconceptoid;

	@Index
	private Long impuestoCodigo;

	private String impuestoconceptonombre;

	private Double impuestoconceptofactor;

	private Long obligacionde;
	private String obligacion;

	private boolean obligatorio;

	private List<Key<Compra>> compraList;

	private List<Key<Factura>> facturaList;

	private List<Key<ProveedorPagoRetencion>> pagoProveedorRetencionList;

	public ImpuestoConcepto() {
	}

	public ImpuestoConcepto(Long impuestoconceptoid) {
		this.impuestoconceptoid = impuestoconceptoid;
	}

	public Long getImpuestoconceptoid() {
		return impuestoconceptoid;
	}

	public void setImpuestoconceptoid(Long impuestoconceptoid) {
		this.impuestoconceptoid = impuestoconceptoid;
	}

	public String getImpuestoconceptonombre() {
		return impuestoconceptonombre;
	}

	public void setImpuestoconceptonombre(String impuestoconceptonombre) {
		this.impuestoconceptonombre = impuestoconceptonombre;
	}

	public Double getImpuestoconceptofactor() {
		return impuestoconceptofactor;
	}

	public void setImpuestoconceptofactor(Double impuestoconceptofactor) {
		this.impuestoconceptofactor = impuestoconceptofactor;
	}

	public List<Key<Compra>> getCompraList() {
		return compraList;
	}

	public void setCompraList(List<Key<Compra>> compraList) {
		this.compraList = compraList;
	}

	public List<Key<Factura>> getFacturaList() {
		return facturaList;
	}

	public void setFacturaList(List<Key<Factura>> facturaList) {
		this.facturaList = facturaList;
	}

	public List<Key<ProveedorPagoRetencion>> getPagoProveedorRetencionList() {
		return pagoProveedorRetencionList;
	}

	public void setPagoProveedorRetencionList(List<Key<ProveedorPagoRetencion>> pagoProveedorRetencionList) {
		this.pagoProveedorRetencionList = pagoProveedorRetencionList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (impuestoconceptoid != null ? impuestoconceptoid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof ImpuestoConcepto)) {
			return false;
		}
		ImpuestoConcepto other = (ImpuestoConcepto) object;
		if ((this.impuestoconceptoid == null && other.impuestoconceptoid != null)
				|| (this.impuestoconceptoid != null && !this.impuestoconceptoid.equals(other.impuestoconceptoid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.core.shared.model.ImpuestoConcepto[ impuestoconceptoid=" + impuestoconceptoid + " ]";
	}

	public Long getImpuestoCodigo() {
		return impuestoCodigo;
	}

	public void setImpuestoCodigo(Long impuestoCodigo) {
		this.impuestoCodigo = impuestoCodigo;
	}

	public Long getObligacionde() {
		return obligacionde;
	}

	public void setObligacionde(Long obligacionde) {
		this.obligacionde = obligacionde;
	}

	public boolean isObligatorio() {
		return obligatorio;
	}

	public void setObligatorio(boolean obligatorio) {
		this.obligatorio = obligatorio;
	}

	public String getObligacion() {
		return obligacion;
	}

	public void setObligacion(String obligacion) {
		this.obligacion = obligacion;
	}

}
