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
public class CompaniaImpuesto extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long companiaImpuestoId;

	@Index
	private String codigo;

	@Index
	@Load
	Ref<Compania> compania;

	private String autorizacion;

	private String letra;

	private String fechaAutorizacion;

	private Integer numeroInicial;

	private Integer numeroFinal;

	private Integer numeroActual;

	private String actividadPrincipal;

	private String porcentajeIca;

	public CompaniaImpuesto() {
	}

	public Compania getCompania() {
		return compania.get();
	}

	public void setCompania(Compania compania) {
		this.compania = Ref.create(compania);
	}

	public Long getCompaniaImpuestoId() {
		return companiaImpuestoId;
	}

	public void setCompaniaImpuestoId(Long companiaImpuestoId) {
		this.companiaImpuestoId = companiaImpuestoId;
	}

	public String getAutorizacion() {
		return autorizacion;
	}

	public void setAutorizacion(String autorizacion) {
		this.autorizacion = autorizacion;
	}

	public String getLetra() {
		return letra;
	}

	public void setLetra(String letra) {
		this.letra = letra;
	}

	public String getFechaAutorizacion() {
		return fechaAutorizacion;
	}

	public void setFechaAutorizacion(String fechaAutorizacion) {
		this.fechaAutorizacion = fechaAutorizacion;
	}

	public Integer getNumeroInicial() {
		return numeroInicial;
	}

	public void setNumeroInicial(Integer numeroInicial) {
		this.numeroInicial = numeroInicial;
	}

	public Integer getNumeroFinal() {
		return numeroFinal;
	}

	public void setNumeroFinal(Integer numeroFinal) {
		this.numeroFinal = numeroFinal;
	}

	public Integer getNumeroActual() {
		return numeroActual;
	}

	public void setNumeroActual(Integer numeroActual) {
		this.numeroActual = numeroActual;
	}

	public String getActividadPrincipal() {
		return actividadPrincipal;
	}

	public void setActividadPrincipal(String actividadPrincipal) {
		this.actividadPrincipal = actividadPrincipal;
	}

	public String getPorcentajeIca() {
		return porcentajeIca;
	}

	public void setPorcentajeIca(String porcentajeIca) {
		this.porcentajeIca = porcentajeIca;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

}
