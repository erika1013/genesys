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
import com.guandera.talento.shared.model.Competencia;

/**
 * 
 * @author FrediJavier
 */
@Entity
public class AspiranteCompetencia extends Auditoria implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private Long aspirantecompetenciaid;

	private Integer nivel;

	@Index
	@Load
	Ref<Aspirante> aspirante;

	@Index
	@Load
	Ref<Competencia> competencia;

	public AspiranteCompetencia() {
	}

	public AspiranteCompetencia(Long aspirantecompetenciaid) {
		this.aspirantecompetenciaid = aspirantecompetenciaid;
	}

	public Long getAspirantecompetenciaid() {
		return aspirantecompetenciaid;
	}

	public void setAspirantecompetenciaid(Long aspirantecompetenciaid) {
		this.aspirantecompetenciaid = aspirantecompetenciaid;
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

	public Competencia getCompetencia() {
		return competencia.get();
	}

	public void setCompetencia(Competencia competencia) {
		this.competencia = Ref.create(competencia);
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (aspirantecompetenciaid != null ? aspirantecompetenciaid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof AspiranteCompetencia)) {
			return false;
		}
		AspiranteCompetencia other = (AspiranteCompetencia) object;
		if ((this.aspirantecompetenciaid == null && other.aspirantecompetenciaid != null)
				|| (this.aspirantecompetenciaid != null
						&& !this.aspirantecompetenciaid.equals(other.aspirantecompetenciaid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.talento.shared.model.AspiranteCompetencia[ aspirantecompetenciaid="
				+ aspirantecompetenciaid + " ]";
	}

}
