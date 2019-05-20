/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guandera.proyecto.shared.model;

import java.io.Serializable;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;
import com.guandera.core.shared.model.Auditoria;
import com.guandera.core.shared.model.ClienteContacto;

@Entity
public class EquipoCliente extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long equipoclienteid;

	@Index
	@Load
	Ref<ClienteContacto> contacto;

	@Index
	@Load
	Ref<Proyecto> proyecto;

	@Index
	@Load
	Ref<RolProyecto> rol;

	public EquipoCliente() {
	}

	public EquipoCliente(Long equipoclienteid) {
		this.equipoclienteid = equipoclienteid;
	}

	public ClienteContacto getContacto() {
		return contacto.get();
	}

	public void setContacto(ClienteContacto contacto) {
		this.contacto = Ref.create(contacto);
	}

	public Proyecto getProyecto() {
		return proyecto.get();
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = Ref.create(proyecto);
	}

	public Long getEquipoclienteid() {
		return equipoclienteid;
	}

	public void setEquipoclienteid(Long equipoclienteid) {
		this.equipoclienteid = equipoclienteid;
	}

	public RolProyecto getRol() {
		return rol.get();
	}

	public void setRol(RolProyecto rol) {
		this.rol = Ref.create(rol);
	}

}
