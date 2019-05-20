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
public class CompaniaCuenta extends Auditoria implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private Long cuentaid;

	@Index
	@Load
	Ref<Compania> compania;

	@Index
	private Integer codigo;

	private String nombreBanco;
	private String tipoCuenta; // AHORROS-CORRIENTE
	private String numeroCuenta;
	private String nombreTitular;

	public Compania getCompania() {
		return compania.get();
	}

	public void setCompania(Compania compania) {
		this.compania = Ref.create(compania);
	}

	public CompaniaCuenta() {
	}

	public CompaniaCuenta(Long cuentaid) {
		this.cuentaid = cuentaid;
	}

	public Long getCuentaid() {
		return cuentaid;
	}

	public void setCuentaid(Long cuentaid) {
		this.cuentaid = cuentaid;
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

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

}
