/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guandera.core.shared.model;

import java.io.Serializable;
import java.util.Date;
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
public class ProveedorPago extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long pagoproveedor;

	private String pagocodigo;

	private Long numerocuentacobro;
	private String concepto;
	private Double pagovalortotal;

	private Double pagovalorneto;

	private Date fecha;
	private String ciudad;

	@Index
	@Load
	Ref<ProveedorPagoEstado> estado;

	@Index
	@Load
	Ref<PagoTipo> pagoTipo;

	@Index
	@Load

	Ref<Proveedor> proveedor;

	private List<Key<ProveedorPagoRetencion>> pagoProveedorRetencionList;

	public ProveedorPago() {
	}

	public ProveedorPago(Long pagoproveedor) {
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

	public Double getPagovalortotal() {
		return pagovalortotal;
	}

	public void setPagovalortotal(Double pagovalortotal) {
		this.pagovalortotal = pagovalortotal;
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
		hash += (pagoproveedor != null ? pagoproveedor.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof ProveedorPago)) {
			return false;
		}
		ProveedorPago other = (ProveedorPago) object;
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

	public ProveedorPagoEstado getEstado() {
		return estado.get();
	}

	public void setEstado(ProveedorPagoEstado estado) {
		this.estado = Ref.create(estado);
	}

	public PagoTipo getPagoTipo() {
		return pagoTipo.get();
	}

	public void setPagoTipo(PagoTipo pagoTipo) {
		this.pagoTipo = Ref.create(pagoTipo);
	}

	public Double getPagovalorneto() {
		return pagovalorneto;
	}

	public void setPagovalorneto(Double pagovalorneto) {
		this.pagovalorneto = pagovalorneto;
	}

	public Long getNumerocuentacobro() {
		return numerocuentacobro;
	}

	public void setNumerocuentacobro(Long numerocuentacobro) {
		this.numerocuentacobro = numerocuentacobro;
	}

	public Proveedor getProveedor() {
		return proveedor.get();
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = Ref.create(proveedor);
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

}
