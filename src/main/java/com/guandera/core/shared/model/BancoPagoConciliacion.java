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
public class BancoPagoConciliacion extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long conciliacionid;

	@Index
	@Load
	Ref<BancoPago> pago;

	@Index
	@Load
	Ref<ReciboPago> recibo;

	public BancoPagoConciliacion() {
	}

	public BancoPagoConciliacion(Long conciliacionid) {
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

	public ReciboPago getRecibo() {
		return recibo.get();
	}

	public void setRecibo(ReciboPago recibo) {
		this.recibo = Ref.create(recibo);
	}

}
