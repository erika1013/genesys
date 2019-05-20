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
public class Factura extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long facturaid;

	@Index
	private String faturacodigo;

	private Date facturafecha;

	private Double subtotal;

	private Double valorIva;

	private Double valorTotal;

	private List<Key<FacturaDetalle>> facturaDetalleList;

	@Index
	@Load
	Ref<Cliente> cliente;

	@Index
	@Load
	Ref<ClienteContacto> contacto;

	@Index
	@Load
	Ref<Compania> compania;

	@Index
	@Load
	private Ref<Sede> sede;

	@Index
	@Load
	private Ref<CentroCostos> centroCostos;

	@Index
	@Load
	Ref<FacturaEstado> estado;
	

	public Factura() {
	}

	public Factura(Long facturaid) {
		this.facturaid = facturaid;
	}

	public Long getFacturaid() {
		return facturaid;
	}

	public void setFacturaid(Long facturaid) {
		this.facturaid = facturaid;
	}

	public String getFaturacodigo() {
		return faturacodigo;
	}

	public void setFaturacodigo(String faturacodigo) {
		this.faturacodigo = faturacodigo;
	}

	public Date getFacturafecha() {
		return facturafecha;
	}

	public void setFacturafecha(Date facturafecha) {
		this.facturafecha = facturafecha;
	}

	public List<Key<FacturaDetalle>> getFacturaDetalleList() {
		return facturaDetalleList;
	}

	public void setFacturaDetalleList(List<Key<FacturaDetalle>> facturaDetalleList) {
		this.facturaDetalleList = facturaDetalleList;
	}

	public Cliente getCliente() {
		return cliente.get();
	}

	public void setCliente(Cliente cliente) {
		this.cliente = Ref.create(cliente);
	}

	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	public Double getValorIva() {
		return valorIva;
	}

	public void setValorIva(Double valorIva) {
		this.valorIva = valorIva;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public ClienteContacto getContacto() {
		return contacto.get();
	}

	public void setContacto(ClienteContacto contacto) {
		this.contacto = Ref.create(contacto);
	}

	public CentroCostos getCentroCostos() {
		return centroCostos.get();
	}

	public void setCentroCostos(CentroCostos centroCostos) {
		this.centroCostos = Ref.create(centroCostos);
	}

	public FacturaEstado getEstado() {
		return estado.get();
	}

	public void setEstado(FacturaEstado estado) {
		this.estado = Ref.create(estado);
	}

	public Compania getCompania() {
		return compania.get();
	}

	public void setCompania(Compania compania) {
		this.compania = Ref.create(compania);
	}

	public Sede getSede() {
		return sede.get();
	}

	public void setSede(Sede sede) {
		this.sede = Ref.create(sede);
	}

}
