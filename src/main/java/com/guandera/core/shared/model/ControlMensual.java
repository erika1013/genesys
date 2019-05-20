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
public class ControlMensual extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long controlMensualid;

	@Index
	private Integer periodo;

	@Index
	@Load
	Ref<Sede> sede;

	@Index
	@Load
	Ref<CobroCalendario> calendario;

	private boolean finalMensual;
	private boolean cobroMensual;
	private boolean interesMensual;
	private boolean reciboMensual;
	private boolean impresionMensual;

	public ControlMensual() {
	}

	public ControlMensual(Long controlMensualid) {
		this.controlMensualid = controlMensualid;
	}

	public Long getContratoid() {
		return controlMensualid;
	}

	public void setContratoid(Long controlMensualid) {
		this.controlMensualid = controlMensualid;
	}

	public Sede getSede() {
		return sede.get();
	}

	public void setSede(Sede sede) {
		this.sede = Ref.create(sede);
	}

	public Integer getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Integer periodo) {
		this.periodo = periodo;
	}

	public CobroCalendario getCalendario() {
		return calendario.get();
	}

	public void setCalendario(CobroCalendario calendario) {
		this.calendario = Ref.create(calendario);
	}

	public boolean isCobroMensual() {
		return cobroMensual;
	}

	public void setCobroMensual(boolean cobroMensual) {
		this.cobroMensual = cobroMensual;
	}

	public boolean isInteresMensual() {
		return interesMensual;
	}

	public void setInteresMensual(boolean interesMensual) {
		this.interesMensual = interesMensual;
	}

	public boolean isReciboMensual() {
		return reciboMensual;
	}

	public void setReciboMensual(boolean reciboMensual) {
		this.reciboMensual = reciboMensual;
	}

	public boolean isImpresionMensual() {
		return impresionMensual;
	}

	public void setImpresionMensual(boolean impresionMensual) {
		this.impresionMensual = impresionMensual;
	}

	public boolean isFinalMensual() {
		return finalMensual;
	}

	public void setFinalMensual(boolean finalMensual) {
		this.finalMensual = finalMensual;
	}

}
