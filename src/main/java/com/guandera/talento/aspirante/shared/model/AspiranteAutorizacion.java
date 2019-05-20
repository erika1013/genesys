
package com.guandera.talento.aspirante.shared.model;

import java.io.Serializable;
import java.util.Date;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;
import com.guandera.core.shared.model.Auditoria;

/**
 * 
 * @author FrediJavier
 */
@Entity
public class AspiranteAutorizacion extends Auditoria implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private Long aspiranteAutorizacionId;

	@Index
	@Load
	Ref<AspiranteAcceso> aspiranteAcceso;
	
	@Index
	@Load
	Ref<Aspirante> aspirante;



	public AspiranteAutorizacion() {
	}

	public AspiranteAutorizacion(Long aspiranteAutorizacionId) {
		this.aspiranteAutorizacionId = aspiranteAutorizacionId;
	}

	
	public Aspirante getAspirante() {
		return aspirante.get();
	}

	public void setAspirante(Aspirante aspirante) {
		this.aspirante = Ref.create(aspirante);
	}

	public AspiranteAcceso getAspiranteAcceso() {
		return aspiranteAcceso.get();
	}

	public void setAspiranteAcceso(AspiranteAcceso aspiranteAcceso) {
		this.aspiranteAcceso = Ref.create(aspiranteAcceso);
	}
	

}
