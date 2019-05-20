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

@Entity
public class ClienteCargo extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long cargoid;

	@Index
	@Load
	Ref<Cliente> cliente;

	private String nombre;
	private String descripcion;

	public ClienteCargo() {
	}

	public Long getCargoid() {
		return cargoid;
	}

	public void setCargoid(Long cargoid) {
		this.cargoid = cargoid;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (cargoid != null ? cargoid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof ClienteCargo)) {
			return false;
		}
		ClienteCargo other = (ClienteCargo) object;
		if ((this.cargoid == null && other.cargoid != null)
				|| (this.cargoid != null && !this.cargoid.equals(other.cargoid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.proyecto.shared.model.ClienteCargo[ cargoid=" + cargoid + " ]";
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Cliente getCliente() {
		return cliente.get();
	}

	public void setCliente(Cliente cliente) {
		this.cliente = Ref.create(cliente);
	}

}
