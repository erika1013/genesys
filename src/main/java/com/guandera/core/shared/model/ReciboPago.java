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
public class ReciboPago extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long reciboid;

	@Index
	private Integer numero;

	@Index
	private String periodo;

	@Index
	private Long year;

	@Index
	private Long mes;

	private Date fechaGeneracion;

	private Date fecha1;
	private Date fecha2;
	private Date fecha3;

	private Double valor1;
	private Double valor2;
	private Double valor3;

	private String referencia1;

	private String referencia2;

	private Date fechaPago;
	private Double valorAbonado;

	@Index
	@Load
	Ref<Sede> sede;

	@Index
	@Load
	Ref<Convenio> convenio;

	@Index
	@Load
	Ref<ReciboEstado> estado;

	@Index
	private String tipo; // tipo Generacion A=Automatico / M = Manual

	@Index
	private Long numeroLote;

	public ReciboPago() {
	}

	public ReciboPago(Long reciboid) {
		this.reciboid = reciboid;
	}

	public Long getReciboid() {
		return reciboid;
	}

	public void setReciboid(Long reciboid) {
		this.reciboid = reciboid;
	}

	public Convenio getConvenio() {
		return convenio.get();
	}

	public void setConvenio(Convenio convenio) {
		this.convenio = Ref.create(convenio);
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (reciboid != null ? reciboid.hashCode() : 0);
		return hash;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Long getYear() {
		return year;
	}

	public void setYear(Long year) {
		this.year = year;
	}

	public Long getMes() {
		return mes;
	}

	public void setMes(Long mes) {
		this.mes = mes;
	}

	public Date getFechaGeneracion() {
		return fechaGeneracion;
	}

	public void setFechaGeneracion(Date fechaGeneracion) {
		this.fechaGeneracion = fechaGeneracion;
	}

	public Date getFecha1() {
		return fecha1;
	}

	public void setFecha1(Date fecha1) {
		this.fecha1 = fecha1;
	}

	public Date getFecha2() {
		return fecha2;
	}

	public void setFecha2(Date fecha2) {
		this.fecha2 = fecha2;
	}

	public Date getFecha3() {
		return fecha3;
	}

	public void setFecha3(Date fecha3) {
		this.fecha3 = fecha3;
	}

	public Double getValor1() {
		return valor1;
	}

	public void setValor1(Double valor1) {
		this.valor1 = valor1;
	}

	public Double getValor2() {
		return valor2;
	}

	public void setValor2(Double valor2) {
		this.valor2 = valor2;
	}

	public Double getValor3() {
		return valor3;
	}

	public void setValor3(Double valor3) {
		this.valor3 = valor3;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public Double getValorAbonado() {
		return valorAbonado;
	}

	public void setValorAbonado(Double valorAbonado) {
		this.valorAbonado = valorAbonado;
	}

	public Sede getSede() {
		return sede.get();
	}

	public void setSede(Sede sede) {
		this.sede = Ref.create(sede);
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof ReciboPago)) {
			return false;
		}
		ReciboPago other = (ReciboPago) object;
		if ((this.reciboid == null && other.reciboid != null)
				|| (this.reciboid != null && !this.reciboid.equals(other.reciboid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.core.shared.model.EstudianteDetalle[ reciboid=" + reciboid + " ]";
	}

	public ReciboEstado getEstado() {
		return estado.get();
	}

	public void setEstado(ReciboEstado estado) {
		this.estado = Ref.create(estado);
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public String getReferencia1() {
		return referencia1;
	}

	public void setReferencia1(String referencia1) {
		this.referencia1 = referencia1;
	}

	public String getReferencia2() {
		return referencia2;
	}

	public void setReferencia2(String referencia2) {
		this.referencia2 = referencia2;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Long getNumeroLote() {
		return numeroLote;
	}

	public void setNumeroLote(Long numeroLote) {
		this.numeroLote = numeroLote;
	}

}
