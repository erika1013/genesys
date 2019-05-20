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
public class Proveedor extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long proveedorid;

	private String proveedornombre;

	@Index
	private Long proveedoridentificacion;

	private Short proveedordv;

	private String proveedorpais;

	private String proveedorciudad;

	private String proveedordireccion;

	private String proveedortelefono;
	
	private String email;

	private String proveedorweb;

	private List<Key<Compra>> compraList;

	@Index
	@Load
	Ref<TipoIdentificacion> tipoIdentificacion;

	@Index
	@Load
	Ref<Compania> compania;

	private List<Key<ProveedorContacto>> proveedorContactoList;

	private List<Key<ProveedorPago>> pagoProveedorList;

	public Proveedor() {
	}

	public Proveedor(Long proveedorid) {
		this.proveedorid = proveedorid;
	}

	public Long getProveedorid() {
		return proveedorid;
	}

	public void setProveedorid(Long proveedorid) {
		this.proveedorid = proveedorid;
	}

	public String getProveedornombre() {
		return proveedornombre;
	}

	public void setProveedornombre(String proveedornombre) {
		this.proveedornombre = proveedornombre;
	}

	public Long getProveedoridentificacion() {
		return proveedoridentificacion;
	}

	public void setProveedoridentificacion(Long proveedoridentificacion) {
		this.proveedoridentificacion = proveedoridentificacion;
	}

	public Short getProveedordv() {
		return proveedordv;
	}

	public void setProveedordv(Short proveedordv) {
		this.proveedordv = proveedordv;
	}

	public String getProveedorpais() {
		return proveedorpais;
	}

	public void setProveedorpais(String proveedorpais) {
		this.proveedorpais = proveedorpais;
	}

	public String getProveedorciudad() {
		return proveedorciudad;
	}

	public void setProveedorciudad(String proveedorciudad) {
		this.proveedorciudad = proveedorciudad;
	}

	public String getProveedordireccion() {
		return proveedordireccion;
	}

	public void setProveedordireccion(String proveedordireccion) {
		this.proveedordireccion = proveedordireccion;
	}

	public String getProveedortelefono() {
		return proveedortelefono;
	}

	public void setProveedortelefono(String proveedortelefono) {
		this.proveedortelefono = proveedortelefono;
	}

	public String getProveedorweb() {
		return proveedorweb;
	}

	public void setProveedorweb(String proveedorweb) {
		this.proveedorweb = proveedorweb;
	}

	public List<Key<Compra>> getCompraList() {
		return compraList;
	}

	public void setCompraList(List<Key<Compra>> compraList) {
		this.compraList = compraList;
	}

	public TipoIdentificacion getTipoIdentificacion() {
		return tipoIdentificacion.get();
	}

	public void setTipoIdentificacion(TipoIdentificacion tipoIdentificacion) {
		this.tipoIdentificacion = Ref.create(tipoIdentificacion);
	}

	public Compania getCompania() {
		return compania.get();
	}

	public void setCompania(Compania compania) {
		this.compania = Ref.create(compania);
	}

	public List<Key<ProveedorContacto>> getProveedorContactoList() {
		return proveedorContactoList;
	}

	public void setProveedorContactoList(List<Key<ProveedorContacto>> proveedorContactoList) {
		this.proveedorContactoList = proveedorContactoList;
	}

	public List<Key<ProveedorPago>> getProveedorPagoList() {
		return pagoProveedorList;
	}

	public void setProveedorPagoList(List<Key<ProveedorPago>> pagoProveedorList) {
		this.pagoProveedorList = pagoProveedorList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (proveedorid != null ? proveedorid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Proveedor)) {
			return false;
		}
		Proveedor other = (Proveedor) object;
		if ((this.proveedorid == null && other.proveedorid != null)
				|| (this.proveedorid != null && !this.proveedorid.equals(other.proveedorid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.core.shared.model.Proveedor[ proveedorid=" + proveedorid + " ]";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}



}
