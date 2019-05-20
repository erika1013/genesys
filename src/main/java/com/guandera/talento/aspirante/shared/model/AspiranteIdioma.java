/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guandera.talento.aspirante.shared.model;

import java.io.Serializable;
import java.util.Date;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;
import com.guandera.core.shared.model.Auditoria;
import com.guandera.talento.shared.model.Idioma;

/**
 * 
 * @author FrediJavier
 */
@Entity
public class AspiranteIdioma extends Auditoria implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private Long aspiranteidiomaid;

	private Integer nivel;

	@Index
	@Load
	Ref<Aspirante> aspirante;

	@Index
	@Load
	Ref<Idioma> idioma;

	private String observacion;

	public AspiranteIdioma() {
	}

	public AspiranteIdioma(Long aspiranteidiomaid) {
		this.aspiranteidiomaid = aspiranteidiomaid;
	}

	public Long getAspiranteidiomaid() {
		return aspiranteidiomaid;
	}

	public void setAspiranteidiomaid(Long aspiranteidiomaid) {
		this.aspiranteidiomaid = aspiranteidiomaid;
	}

	public Integer getNivel() {
		return nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}

	public Aspirante getAspirante() {
		return aspirante.get();
	}

	public void setAspirante(Aspirante aspirante) {
		this.aspirante = Ref.create(aspirante);
	}

	public Idioma getIdioma() {
		return idioma.get();
	}

	public void setIdioma(Idioma idioma) {
		this.idioma = Ref.create(idioma);
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (aspiranteidiomaid != null ? aspiranteidiomaid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof AspiranteIdioma)) {
			return false;
		}
		AspiranteIdioma other = (AspiranteIdioma) object;
		if ((this.aspiranteidiomaid == null && other.aspiranteidiomaid != null)
				|| (this.aspiranteidiomaid != null && !this.aspiranteidiomaid.equals(other.aspiranteidiomaid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.talento.shared.model.AspiranteIdioma[ aspiranteidiomaid=" + aspiranteidiomaid + " ]";
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

}
