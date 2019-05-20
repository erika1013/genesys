/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guandera.proyecto.shared.model;

import java.io.Serializable;
import java.util.Date;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;
import com.guandera.core.shared.model.Auditoria;
import com.guandera.core.shared.model.acceso.Usuario;

@Entity
public class AsignacionCambioEstado extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long asignacioncambioid;

	private Integer asignacioncambioestadoantes;
	private Integer asignacioncambioestadodespues;
	private Date asignacioncambiofecha;

	@Index
	@Load

	Ref<Asignacion> asignacionid;

	@Index
	@Load

	Ref<Usuario> asignacioncambiousuario;

	public AsignacionCambioEstado() {
	}

	public AsignacionCambioEstado(Long asignacioncambioid) {
		this.asignacioncambioid = asignacioncambioid;
	}

	public Long getclientecontactoid() {
		return asignacioncambioid;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (asignacioncambioid != null ? asignacioncambioid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof AsignacionCambioEstado)) {
			return false;
		}
		AsignacionCambioEstado other = (AsignacionCambioEstado) object;
		if ((this.asignacioncambioid == null && other.asignacioncambioid != null)
				|| (this.asignacioncambioid != null && !this.asignacioncambioid.equals(other.asignacioncambioid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.proyecto.shared.model.AsignacionCambioEstado[ asignacioncambioid=" + asignacioncambioid
				+ " ]";
	}

	public Integer getAsignacioncambioestadoantes() {
		return asignacioncambioestadoantes;
	}

	public void setAsignacioncambioestadoantes(Integer asignacioncambioestadoantes) {
		this.asignacioncambioestadoantes = asignacioncambioestadoantes;
	}

	public Integer getAsignacioncambioestadodespues() {
		return asignacioncambioestadodespues;
	}

	public void setAsignacioncambioestadodespues(Integer asignacioncambioestadodespues) {
		this.asignacioncambioestadodespues = asignacioncambioestadodespues;
	}

	public Date getAsignacioncambiofecha() {
		return asignacioncambiofecha;
	}

	public void setAsignacioncambiofecha(Date asignacioncambiofecha) {
		this.asignacioncambiofecha = asignacioncambiofecha;
	}

	public Long getAsignacioncambioid() {
		return asignacioncambioid;
	}

	public void setAsignacioncambioid(Long asignacioncambioid) {
		this.asignacioncambioid = asignacioncambioid;
	}

	public Asignacion getAsignacionid() {
		return asignacionid.get();
	}

	public void setAsignacionid(Asignacion asignacionid) {
		this.asignacionid = Ref.create(asignacionid);
	}

	public Usuario getAsignacioncambiousuario() {
		return asignacioncambiousuario.get();
	}

	public void setAsignacioncambiousuario(Usuario asignacioncambiousuario) {
		this.asignacioncambiousuario = Ref.create(asignacioncambiousuario);
	}

}
