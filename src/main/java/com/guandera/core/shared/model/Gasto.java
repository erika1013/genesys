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
public class Gasto extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long gastoid;

	private String soporte;
	private String soporteNumero;

	@Index
	private Date gastofecha;

	private String descripcion;

	private Double valorUnitario;

	private Integer cantidad;

	private Double valorTotal;

	@Index
	@Load
	Ref<Proveedor> proveedor;

	@Index
	private Integer conceptoCodigo;

	@Index
	private Integer subConceptoCodigo;

	@Index
	@Load
	Ref<GastoConcepto> concepto;

	@Index
	@Load
	Ref<GastoSubConcepto> subConcepto;

	public Gasto() {
	}

	public Gasto(Long gastoid) {
		this.gastoid = gastoid;
	}

	public Long getGastoid() {
		return gastoid;
	}

	public void setGastoid(Long gastoid) {
		this.gastoid = gastoid;
	}

	public GastoConcepto getConcepto() {
		return concepto.get();
	}

	public void setGastoConcepto(GastoConcepto concepto) {
		this.concepto = Ref.create(concepto);
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
		hash += (gastoid != null ? gastoid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Gasto)) {
			return false;
		}
		Gasto other = (Gasto) object;
		if ((this.gastoid == null && other.gastoid != null)
				|| (this.gastoid != null && !this.gastoid.equals(other.gastoid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.core.shared.model.Gasto[ gastoid=" + gastoid + " ]";
	}

	public String getSoporte() {
		return soporte;
	}

	public void setSoporte(String soporte) {
		this.soporte = soporte;
	}

	public String getSoporteNumero() {
		return soporteNumero;
	}

	public void setSoporteNumero(String soporteNumero) {
		this.soporteNumero = soporteNumero;
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

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public GastoSubConcepto getSubConcepto() {
		return subConcepto.get();
	}

	public void setSubConcepto(GastoSubConcepto subConcepto) {
		this.subConcepto = Ref.create(subConcepto);
	}

	public Date getGastofecha() {
		return gastofecha;
	}

	public void setGastofecha(Date gastofecha) {
		this.gastofecha = gastofecha;
	}

	public Integer getConceptoCodigo() {
		return conceptoCodigo;
	}

	public void setConceptoCodigo(Integer conceptoCodigo) {
		this.conceptoCodigo = conceptoCodigo;
	}

	public Integer getSubConceptoCodigo() {
		return subConceptoCodigo;
	}

	public void setSubConceptoCodigo(Integer subConceptoCodigo) {
		this.subConceptoCodigo = subConceptoCodigo;
	}

}
