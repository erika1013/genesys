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
public class FacturaDetalle extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long facturadetalleid;

	@Index
	@Load
	Ref<Factura> factura;

	private Integer cantidad;

	private String descripcion;

	private Double valorUnitario;

	private Double valorTotal;

	public FacturaDetalle() {
	}

	public FacturaDetalle(Long facturadetalleid) {
		this.facturadetalleid = facturadetalleid;
	}

	public Long getFacturadetalleid() {
		return facturadetalleid;
	}

	public void setFacturadetalleid(Long facturadetalleid) {
		this.facturadetalleid = facturadetalleid;
	}

	public Factura getFactura() {
		return factura.get();
	}

	public void setFactura(Factura factura) {
		this.factura = Ref.create(factura);
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (facturadetalleid != null ? facturadetalleid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof FacturaDetalle)) {
			return false;
		}
		FacturaDetalle other = (FacturaDetalle) object;
		if ((this.facturadetalleid == null && other.facturadetalleid != null)
				|| (this.facturadetalleid != null && !this.facturadetalleid.equals(other.facturadetalleid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.core.shared.model.FacturaDetalle[ facturadetalleid=" + facturadetalleid + " ]";
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

}
