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
public class BancoConciliacion extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long conciliacionid;

	@Index
	@Load
	Ref<BancoPago> pago;

	@Index
	private Integer fechaPago;

	private Double valorTotal;
	private Double valorConciliado;
	private Double valorPendiente;

	private String nombreOrigen;

	@Index
	private Integer estudianteCodigo;

	private String observacion;

	public BancoConciliacion() {
	}

	public BancoConciliacion(Long conciliacionid) {
		this.conciliacionid = conciliacionid;
	}

	public Long getConciliacionid() {
		return conciliacionid;
	}

	public void setConciliacionid(Long conciliacionid) {
		this.conciliacionid = conciliacionid;
	}

	public BancoPago getPago() {
		return pago.get();
	}

	public void setPago(BancoPago pago) {
		this.pago = Ref.create(pago);
	}

	public String getNombreOrigen() {
		return nombreOrigen;
	}

	public void setNombreOrigen(String nombreOrigen) {
		this.nombreOrigen = nombreOrigen;
	}

	public Integer getEstudianteCodigo() {
		return estudianteCodigo;
	}

	public void setEstudianteCodigo(Integer estudianteCodigo) {
		this.estudianteCodigo = estudianteCodigo;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Integer getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Integer fechaPago) {
		this.fechaPago = fechaPago;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Double getValorConciliado() {
		return valorConciliado;
	}

	public void setValorConciliado(Double valorConciliado) {
		this.valorConciliado = valorConciliado;
	}

	public Double getValorPendiente() {
		return valorPendiente;
	}

	public void setValorPendiente(Double valorPendiente) {
		this.valorPendiente = valorPendiente;
	}

}
