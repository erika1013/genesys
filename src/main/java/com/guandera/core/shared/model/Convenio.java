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
public class Convenio extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long convenioid;

	@Index
	@Load
	Ref<Sede> sede;

	@Index
	@Load
	Ref<TipoServicio> tipoServicio;

	@Index
	private Integer convenioNumero;
	private String nombreBanco;
	private String tipoCuenta; // AHORROS-CORRIENTE
	private String numeroCuenta;
	private String nombreTitular;
	private String nitTitular;

	private Double extraordinaria; // Porcentaje incremento
	private Double extemporanea; // Porcentaje incremento

	public Convenio() {
	}

	public Convenio(Long convenioid) {
		this.convenioid = convenioid;
	}

	public Long getConvenioid() {
		return convenioid;
	}

	public void setConvenioid(Long convenioid) {
		this.convenioid = convenioid;
	}

	public TipoServicio getTipoServicio() {
		return tipoServicio.get();
	}

	public void setTipoServicio(TipoServicio tipoServicio) {
		this.tipoServicio = Ref.create(tipoServicio);
	}

	public Sede getSede() {
		return sede.get();
	}

	public void setSede(Sede sede) {
		this.sede = Ref.create(sede);
	}

	public Integer getConvenioNumero() {
		return convenioNumero;
	}

	public void setConvenioNumero(Integer convenioNumero) {
		this.convenioNumero = convenioNumero;
	}

	public String getNombreBanco() {
		return nombreBanco;
	}

	public void setNombreBanco(String nombreBanco) {
		this.nombreBanco = nombreBanco;
	}

	public String getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public String getNombreTitular() {
		return nombreTitular;
	}

	public void setNombreTitular(String nombreTitular) {
		this.nombreTitular = nombreTitular;
	}

	public String getNitTitular() {
		return nitTitular;
	}

	public void setNitTitular(String nitTitular) {
		this.nitTitular = nitTitular;
	}

	public Double getExtraordinaria() {
		return extraordinaria;
	}

	public void setExtraordinaria(Double extraordinaria) {
		this.extraordinaria = extraordinaria;
	}

	public Double getExtemporanea() {
		return extemporanea;
	}

	public void setExtemporanea(Double extemporanea) {
		this.extemporanea = extemporanea;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (convenioid != null ? convenioid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Convenio)) {
			return false;
		}
		Convenio other = (Convenio) object;
		if ((this.convenioid == null && other.convenioid != null)
				|| (this.convenioid != null && !this.convenioid.equals(other.convenioid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.core.shared.model.Convenio[ convenioid=" + convenioid + " ]";
	}

}
