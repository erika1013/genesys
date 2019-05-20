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
public class ReciboPagoAbono extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long pagoid;

	@Index
	@Load
	Ref<ReciboPago> reciboPago;

	@Index
	private Date fechaPago;

	private Double valorPago;

	@Index
	@Load
	Ref<TipoPago> tipoPago;

	private String observacion;

	public ReciboPagoAbono() {
	}

	public ReciboPagoAbono(Long pagoreciboabonoid) {
		pagoid = pagoreciboabonoid;
	}

	public ReciboPago getReciboPago() {
		return reciboPago.get();
	}

	public void setReciboPago(ReciboPago reciboPago) {
		this.reciboPago = Ref.create(reciboPago);
	}

	public TipoPago getTipoPago() {
		return tipoPago.get();
	}

	public void setTipoPago(TipoPago tipoPago) {
		this.tipoPago = Ref.create(tipoPago);
	}

	public Long getPagoid() {
		return pagoid;
	}

	public void setPagoid(Long pagoid) {
		this.pagoid = pagoid;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public Double getValorPago() {
		return valorPago;
	}

	public void setValorPago(Double valorPago) {
		this.valorPago = valorPago;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

}
