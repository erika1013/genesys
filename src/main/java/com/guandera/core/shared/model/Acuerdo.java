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
public class Acuerdo extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long acuerdoid;

	@Index
	private Integer acuerdoYear;

	@Index
	@Load
	Ref<Compania> compania;

	@Index
	@Load
	Ref<Servicio> servicio;

	@Index
	@Load
	Ref<Cliente> cliente;

	private Double valor;

	private String descripcion;

	public Acuerdo() {
	}

	public Acuerdo(Long acuerdoid) {
		this.acuerdoid = acuerdoid;
	}

	public Long getAcuerdoid() {
		return acuerdoid;
	}

	public void setAcuerdoid(Long acuerdoid) {
		this.acuerdoid = acuerdoid;
	}

	public Compania getCompania() {
		return compania.get();
	}

	public void setCompania(Compania compania) {
		this.compania = Ref.create(compania);
	}

	public Cliente getCliente() {
		return cliente.get();
	}

	public void setCliente(Cliente cliente) {
		this.cliente = Ref.create(cliente);
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (acuerdoid != null ? acuerdoid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Acuerdo)) {
			return false;
		}
		Acuerdo other = (Acuerdo) object;
		if ((this.acuerdoid == null && other.acuerdoid != null)
				|| (this.acuerdoid != null && !this.acuerdoid.equals(other.acuerdoid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.core.shared.model.Acuerdo[ acuerdoid=" + acuerdoid + " ]";
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Servicio getServicio() {
		return servicio.get();
	}

	public void setServicio(Servicio servicio) {
		this.servicio = Ref.create(servicio);
	}

	public Integer getAcuerdoYear() {
		return acuerdoYear;
	}

	public void setAcuerdoYear(Integer acuerdoYear) {
		this.acuerdoYear = acuerdoYear;
	}

}
