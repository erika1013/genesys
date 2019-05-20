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
public class Cobro extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long cobroid;

	@Index
	private Long numero;

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

	private Date fechaPago;
	private Double valorAbonado;

	@Index
	@Load
	Ref<Servicio> servicio;

	private Integer cantidad;

	private Double valorUnidad;

	@Index
	@Load
	Ref<CobroEstado> estado;

	@Index
	private String tipo; // tipo Generacion A=Automatico / M = Manual

	@Index
	private Long numeroLote;

	public Cobro() {
	}

	public Cobro(Long cobroid) {
		this.cobroid = cobroid;
	}

	public Long getCobroid() {
		return cobroid;
	}

	public void setCobroid(Long cobroid) {
		this.cobroid = cobroid;
	}

	public Servicio getServicio() {
		return servicio.get();
	}

	public void setServicio(Servicio servicio) {
		this.servicio = Ref.create(servicio);
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (cobroid != null ? cobroid.hashCode() : 0);
		return hash;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
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

	@Override
	public boolean equals(Object object) {

		if (!(object instanceof Cobro)) {
			return false;
		}
		Cobro other = (Cobro) object;
		if ((this.cobroid == null && other.cobroid != null)
				|| (this.cobroid != null && !this.cobroid.equals(other.cobroid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.core.shared.model.EstudianteDetalle[ cobroid=" + cobroid + " ]";
	}

	public CobroEstado getEstado() {
		return estado.get();
	}

	public void setEstado(CobroEstado estado) {
		this.estado = Ref.create(estado);
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
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

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Double getValorUnidad() {
		return valorUnidad;
	}

	public void setValorUnidad(Double valorUnidad) {
		this.valorUnidad = valorUnidad;
	}

}
