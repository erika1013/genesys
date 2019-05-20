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
public class CuentaCobro extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long cobroid;

	@Index
	private Long numeroCuenta;

	
	private String ciudad;
	
	@Index
	private Date fecha;

	private Double valor;

	private String concepto;

	@Index
	@Load
	Ref<Proveedor> proveedor;
	
	
	@Index
	@Load
	Ref<Moneda> moneda;
	
	
	
	private Date fechaAutorizacion;
	
	private Date fechaPago;
	
	
	
	

	@Index
	@Load
	private Ref<CuentaCobroEstado> cuentaCobroEstado;

	public CuentaCobro() {
	}

	public CuentaCobro(Long cobroid) {
		this.cobroid = cobroid;
	}

	public Long getCobroid() {
		return cobroid;
	}

	public void setCobroid(Long cobroid) {
		this.cobroid = cobroid;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (cobroid != null ? cobroid.hashCode() : 0);
		return hash;
	}

	public Long getNumero() {
		return getNumeroCuenta();
	}

	public void setNumero(Long numero) {
		this.setNumeroCuenta(numero);
	}

	@Override
	public boolean equals(Object object) {
		// not set
		if (!(object instanceof CuentaCobro)) {
			return false;
		}
		CuentaCobro other = (CuentaCobro) object;
		if ((this.cobroid == null && other.cobroid != null)
				|| (this.cobroid != null && !this.cobroid.equals(other.cobroid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.core.shared.model.CuentaCobro[ cobroid=" + cobroid + " ]";
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Long getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(Long numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public Proveedor getProveedor() {
		return proveedor.get();
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = Ref.create(proveedor);
	}

	public CuentaCobroEstado getCuentaCobroEstado() {
		return cuentaCobroEstado.get();
	}

	public void setCuentaCobroEstado(CuentaCobroEstado cuentaCobroEstado) {
		this.cuentaCobroEstado = Ref.create(cuentaCobroEstado);
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public Moneda getMoneda() {
		return moneda.get();
	}

	public void setMoneda(Moneda moneda) {
		this.moneda = Ref.create(moneda);
	}

	public Date getFechaAutorizacion() {
		return fechaAutorizacion;
	}

	public void setFechaAutorizacion(Date fechaAutorizacion) {
		this.fechaAutorizacion = fechaAutorizacion;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	
	
	
	
	

}
