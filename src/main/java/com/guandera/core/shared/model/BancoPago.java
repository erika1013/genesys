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
public class BancoPago extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long pagoid;

	@Index
	private Integer tipo;

	@Index
	private Integer fechaProceso;

	private Long numeroCuenta;

	@Index
	private Integer fechaSistema;

	@Index
	private Integer horaSistema;

	@Index
	private Integer terminal;

	@Index
	private Integer talon;

	private String valorChequeSigno;
	private Double valorCheque;

	private String valorTotalSigno;
	private Double valorTotal;

	private String valorBlancoSigno;
	private Double valorBlanco;

	private Integer oficinaRecaudo;

	private Integer motivo;

	@Index
	private Long referencia1;

	@Index
	private Long referencia2;

	private String saldoSigno;
	private Double saldo;

	private Integer indicadorExento;

	private Double valorExento;

	private Integer canal;

	private Integer causalDevolucion;

	private Integer jornada;

	private Date fechaCarge;

	@Index
	@Load
	Ref<BancoPagoEstado> estado;

	public BancoPago() {
	}

	public BancoPago(Long pagoid) {
		this.pagoid = pagoid;
	}

	public Long getReciboid() {
		return pagoid;
	}

	public void setReciboid(Long pagoid) {
		this.pagoid = pagoid;
	}

	public BancoPagoEstado getEstado() {
		return estado.get();
	}

	public void setEstado(BancoPagoEstado estado) {
		this.estado = Ref.create(estado);
	}

	public Long getPagoid() {
		return pagoid;
	}

	public void setPagoid(Long pagoid) {
		this.pagoid = pagoid;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public Integer getFechaProceso() {
		return fechaProceso;
	}

	public void setFechaProceso(Integer fechaProceso) {
		this.fechaProceso = fechaProceso;
	}

	public Long getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(Long numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public Integer getFechaSistema() {
		return fechaSistema;
	}

	public void setFechaSistema(Integer fechaSistema) {
		this.fechaSistema = fechaSistema;
	}

	public Integer getHoraSistema() {
		return horaSistema;
	}

	public void setHoraSistema(Integer horaSistema) {
		this.horaSistema = horaSistema;
	}

	public Integer getTerminal() {
		return terminal;
	}

	public void setTerminal(Integer terminal) {
		this.terminal = terminal;
	}

	public Integer getTalon() {
		return talon;
	}

	public void setTalon(Integer talon) {
		this.talon = talon;
	}

	public Double getValorCheque() {
		return valorCheque;
	}

	public void setValorCheque(Double valorCheque) {
		this.valorCheque = valorCheque;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Double getValorBlanco() {
		return valorBlanco;
	}

	public void setValorBlanco(Double valorBlanco) {
		this.valorBlanco = valorBlanco;
	}

	public Integer getOficinaRecaudo() {
		return oficinaRecaudo;
	}

	public void setOficinaRecaudo(Integer oficinaRecaudo) {
		this.oficinaRecaudo = oficinaRecaudo;
	}

	public Integer getMotivo() {
		return motivo;
	}

	public void setMotivo(Integer motivo) {
		this.motivo = motivo;
	}

	public Long getReferencia1() {
		return referencia1;
	}

	public void setReferencia1(Long referencia1) {
		this.referencia1 = referencia1;
	}

	public Long getReferencia2() {
		return referencia2;
	}

	public void setReferencia2(Long referencia2) {
		this.referencia2 = referencia2;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public Integer getIndicadorExento() {
		return indicadorExento;
	}

	public void setIndicadorExento(Integer indicadorExento) {
		this.indicadorExento = indicadorExento;
	}

	public Double getValorExento() {
		return valorExento;
	}

	public void setValorExento(Double valorExento) {
		this.valorExento = valorExento;
	}

	public Integer getCanal() {
		return canal;
	}

	public void setCanal(Integer canal) {
		this.canal = canal;
	}

	public Integer getCausalDevolucion() {
		return causalDevolucion;
	}

	public void setCausalDevolucion(Integer causalDevolucion) {
		this.causalDevolucion = causalDevolucion;
	}

	public Integer getJornada() {
		return jornada;
	}

	public void setJornada(Integer jornada) {
		this.jornada = jornada;
	}

	public Date getFechaCarge() {
		return fechaCarge;
	}

	public void setFechaCarge(Date fechaCarge) {
		this.fechaCarge = fechaCarge;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getValorChequeSigno() {
		return valorChequeSigno;
	}

	public void setValorChequeSigno(String valorChequeSigno) {
		this.valorChequeSigno = valorChequeSigno;
	}

	public String getValorTotalSigno() {
		return valorTotalSigno;
	}

	public void setValorTotalSigno(String valorTotalSigno) {
		this.valorTotalSigno = valorTotalSigno;
	}

	public String getValorBlancoSigno() {
		return valorBlancoSigno;
	}

	public void setValorBlancoSigno(String valorBlancoSigno) {
		this.valorBlancoSigno = valorBlancoSigno;
	}

	public String getSaldoSigno() {
		return saldoSigno;
	}

	public void setSaldoSigno(String saldoSigno) {
		this.saldoSigno = saldoSigno;
	}

}
