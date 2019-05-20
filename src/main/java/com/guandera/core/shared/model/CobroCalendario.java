/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guandera.core.shared.model;

import java.io.Serializable;
import java.util.Date;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

/**
 * 
 * @author Fredi J. Velasco Villarreal - GUANDERA S.A.S.
 */
@Entity
public class CobroCalendario extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long cobroCalendarioid;

	@Index
	private String periodo;

	private Date fecha1;
	private Date fecha2;
	private Date fecha3;

	public CobroCalendario() {
	}

	public CobroCalendario(Long cobroCalendarioid) {
		this.cobroCalendarioid = cobroCalendarioid;
	}

	public Long getCobroCalendarioid() {
		return cobroCalendarioid;
	}

	public void setCobroCalendarioid(Long cobroCalendarioid) {
		this.cobroCalendarioid = cobroCalendarioid;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public Date getFecha1() {
		return fecha1;
	}

	public void setFecha1(Date fecha1) {
		this.fecha1 = fecha1;
	}

	public Date getFecha2() {
		return fecha2;
	}

	public void setFecha2(Date fecha2) {
		this.fecha2 = fecha2;
	}

	public Date getFecha3() {
		return fecha3;
	}

	public void setFecha3(Date fecha3) {
		this.fecha3 = fecha3;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (cobroCalendarioid != null ? cobroCalendarioid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof CobroCalendario)) {
			return false;
		}
		CobroCalendario other = (CobroCalendario) object;
		if ((this.cobroCalendarioid == null && other.cobroCalendarioid != null)
				|| (this.cobroCalendarioid != null && !this.cobroCalendarioid.equals(other.cobroCalendarioid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.core.shared.model.CobroCalendario[ cobroCalendarioid=" + cobroCalendarioid + " ]";
	}

}
