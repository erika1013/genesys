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
public class ClienteContacto extends Persona implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long contactoid;

	@Index
	@Load
	Ref<Cliente> cliente;

	@Index
	@Load
	Ref<ClienteCargo> cargo;

	private String celular;
	private String telefono;
	private String extension;
	private String email;

	private String area;

	public ClienteContacto() {
	}

	public ClienteContacto(Long contactoid) {
		this.contactoid = contactoid;
	}

	public Long getContactoid() {
		return contactoid;
	}

	public void setContactoid(Long contactoid) {
		this.contactoid = contactoid;
	}

	public Cliente getCliente() {
		return cliente.get();
	}

	public void setCliente(Cliente cliente) {
		this.cliente = Ref.create(cliente);
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ClienteCargo getCargo() {
		return cargo.get();
	}

	public void setCargo(ClienteCargo cargo) {
		this.cargo = Ref.create(cargo);
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (contactoid != null ? contactoid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof ClienteContacto)) {
			return false;
		}
		ClienteContacto other = (ClienteContacto) object;
		if ((this.contactoid == null && other.contactoid != null)
				|| (this.contactoid != null && !this.contactoid.equals(other.contactoid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.proyecto.shared.model.ClienteContacto[ contactoid=" + contactoid + " ]";
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

}
