
package com.guandera.talento.aspirante.shared.model;

import java.io.Serializable;
import java.util.Date;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.guandera.core.shared.model.Auditoria;

/**
 * 
 * @author FrediJavier
 */
@Entity
public class AspiranteAcceso extends Auditoria implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private Long aspiranteAccesoId;

	@Index
	private String correo;

	@Index
	private String clave;
	
	
	private Date ultimoAcceso;
	
	

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public AspiranteAcceso() {
	}

	public AspiranteAcceso(Long aspiranteAccesoId) {
		this.aspiranteAccesoId = aspiranteAccesoId;
	}

	public Date getUltimoAcceso() {
		return ultimoAcceso;
	}

	public void setUltimoAcceso(Date ultimoAcceso) {
		this.ultimoAcceso = ultimoAcceso;
	}

}
