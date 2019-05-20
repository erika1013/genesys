/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guandera.core.shared.model;

import java.io.Serializable;
import java.util.Date;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;
import com.guandera.core.shared.model.acceso.Usuario;

/**
 * 
 * @author Fredi J. Velasco Villarreal - GUANDERA S.A.S.
 */

public class Auditoria implements Serializable {

	private static final long serialVersionUID = -5913295866754246495L;

	@Index
	@Load
	Ref<Usuario> usuarioCreacion;
	private Date fechaCreacion;

	@Index
	@Load
	Ref<Usuario> usuarioModificacion;
	private Date fechaModificacion;

	public Usuario getUsuarioCreacion() {
		return usuarioCreacion.get();
	}

	public void setUsuarioCreacion(Usuario usuarioCreacion) {
		this.usuarioCreacion = Ref.create(usuarioCreacion);
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Usuario getUsuarioModificacion() {
		return usuarioModificacion.get();
	}

	public void setUsuarioModificacion(Usuario usuarioModificacion) {
		this.usuarioModificacion = Ref.create(usuarioModificacion);
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		if (fechaModificacion == null) {

			this.fechaModificacion = new Date(0);

		} else {
			this.fechaModificacion = fechaModificacion;
		}

	}

}
