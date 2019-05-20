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
import com.guandera.talento.empleado.shared.model.Empleado;

@Entity
public class EquipoCompania extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long equipocompaniaid;

	@Index
	@Load
	Ref<Proyecto> proyecto;

	@Index
	@Load
	Ref<RolProyecto> rol;

	@Index
	@Load
	Ref<Empleado> empleado;

	public EquipoCompania() {
	}

	public EquipoCompania(Long equipocompaniaid) {
		this.equipocompaniaid = equipocompaniaid;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (equipocompaniaid != null ? equipocompaniaid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof EquipoCompania)) {
			return false;
		}
		EquipoCompania other = (EquipoCompania) object;
		if ((this.equipocompaniaid == null && other.equipocompaniaid != null)
				|| (this.equipocompaniaid != null && !this.equipocompaniaid.equals(other.equipocompaniaid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.proyecto.shared.model.EquipoCompania[ equipocompaniaid=" + equipocompaniaid + " ]";
	}

	public RolProyecto getRol() {
		return rol.get();
	}

	public void setRol(RolProyecto rol) {
		this.rol = Ref.create(rol);
	}

	public Empleado getEmpleado() {
		return empleado.get();
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = Ref.create(empleado);
	}

	public Proyecto getProyecto() {
		return proyecto.get();
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = Ref.create(proyecto);
	}

}
