/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guandera.core.shared.model;

import java.io.Serializable;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

/**
 * 
 * @author Fredi J. Velasco Villarreal - GUANDERA S.A.S.
 */
@Entity
public class ReciboPagoControl extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long reciboControlid;

	@Index
	private String periodo;

	public ReciboPagoControl() {
	}

	public ReciboPagoControl(Long reciboControlid) {
		this.reciboControlid = reciboControlid;
	}

	public Long getReciboControlid() {
		return reciboControlid;
	}

	public void setReciboControlid(Long reciboControlid) {
		this.reciboControlid = reciboControlid;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

}
