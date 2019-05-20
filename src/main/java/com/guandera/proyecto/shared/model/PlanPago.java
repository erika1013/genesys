
package com.guandera.proyecto.shared.model;

import java.io.Serializable;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;
import com.guandera.core.shared.model.Auditoria;

@Entity
public class PlanPago extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long planpruebasid;
	private String planpruebasdescripcion;

	@Index
	@Load

	Ref<Requerimiento> requerimientoid;

	public PlanPago() {
	}

	public PlanPago(Long planpruebasid) {
		this.planpruebasid = planpruebasid;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (planpruebasid != null ? planpruebasid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof PlanPago)) {
			return false;
		}
		PlanPago other = (PlanPago) object;
		if ((this.planpruebasid == null && other.planpruebasid != null)
				|| (this.planpruebasid != null && !this.planpruebasid.equals(other.planpruebasid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.proyecto.shared.model.PlanPrueba[ planpruebasid=" + planpruebasid + " ]";
	}

	public Requerimiento getRequerimientoid() {
		return requerimientoid.get();
	}

	public void setRequerimientoid(Requerimiento requerimientoid) {
		this.requerimientoid = Ref.create(requerimientoid);
	}

	public String getPlanpruebasdescripcion() {
		return planpruebasdescripcion;
	}

	public void setPlanpruebasdescripcion(String planpruebasdescripcion) {
		this.planpruebasdescripcion = planpruebasdescripcion;
	}

	public Long getPlanpruebasid() {
		return planpruebasid;
	}

	public void setPlanpruebasid(Long planpruebasid) {
		this.planpruebasid = planpruebasid;
	}

}
