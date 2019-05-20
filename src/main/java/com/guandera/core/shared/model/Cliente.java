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
public class Cliente extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long clienteid;

	private String clientenombre;

	@Index
	private Long clienteidentificacion;

	private Integer digitoVerificacion;
	private String clientepais;

	private String clienteciudad;

	private String clientedireccion;

	private String clientetelefono;

	private String director;

	@Index
	@Load
	Ref<TipoIdentificacion> tipoIdentificacion;

	public Cliente() {
	}

	public Cliente(Long clienteid) {
		this.clienteid = clienteid;
	}

	public Long getClienteid() {
		return clienteid;
	}

	public void setClienteid(Long clienteid) {
		this.clienteid = clienteid;
	}

	public String getClientenombre() {
		return clientenombre;
	}

	public void setClientenombre(String clientenombre) {
		this.clientenombre = clientenombre;
	}

	public Long getClienteidentificacion() {
		return clienteidentificacion;
	}

	public void setClienteidentificacion(Long clienteidentificacion) {
		this.clienteidentificacion = clienteidentificacion;
	}

	public String getClientepais() {
		return clientepais;
	}

	public void setClientepais(String clientepais) {
		this.clientepais = clientepais;
	}

	public String getClienteciudad() {
		return clienteciudad;
	}

	public void setClienteciudad(String clienteciudad) {
		this.clienteciudad = clienteciudad;
	}

	public String getClientedireccion() {
		return clientedireccion;
	}

	public void setClientedireccion(String clientedireccion) {
		this.clientedireccion = clientedireccion;
	}

	public String getClientetelefono() {
		return clientetelefono;
	}

	public void setClientetelefono(String clientetelefono) {
		this.clientetelefono = clientetelefono;
	}

	public TipoIdentificacion getTipoIdentificacion() {
		return tipoIdentificacion.get();
	}

	public void setTipoIdentificacion(TipoIdentificacion tipoIdentificacion) {
		this.tipoIdentificacion = Ref.create(tipoIdentificacion);
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (clienteid != null ? clienteid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Cliente)) {
			return false;
		}
		Cliente other = (Cliente) object;
		if ((this.clienteid == null && other.clienteid != null)
				|| (this.clienteid != null && !this.clienteid.equals(other.clienteid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.core.shared.model.Cliente[ clienteid=" + clienteid + " ]";
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public Integer getDigitoVerificacion() {
		return digitoVerificacion;
	}

	public void setDigitoVerificacion(Integer digitoVerificacion) {
		this.digitoVerificacion = digitoVerificacion;
	}

}
