/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guandera.core.shared.model;

import java.io.Serializable;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;

/**
 * 
 * @author Fredi J. Velasco Villarreal - GUANDERA S.A.S.
 */
@Entity
public class TipoServicio extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long tiposervicioid;

	@Index
	private String tiposervicionombre;

	@Index
	@Load
	Ref<TipoCobro> tipoCobro;

	private Double extraordinaria; // Factor incremento
	private Double extemporanea; // Factor incremento

	private Double tasaMora; // Factor incremento

	private Integer ordenAplicacion;

	public TipoServicio() {
	}

	public TipoServicio(Long tiposervicioid) {
		this.tiposervicioid = tiposervicioid;
	}

	public Long getTiposervicioid() {
		return tiposervicioid;
	}

	public void setTiposervicioid(Long tiposervicioid) {
		this.tiposervicioid = tiposervicioid;
	}

	public String getTiposervicionombre() {
		return tiposervicionombre;
	}

	public void setTiposervicionombre(String tiposervicionombre) {
		this.tiposervicionombre = tiposervicionombre;
	}

	public TipoCobro getTipoCobro() {

		return tipoCobro.get();
	}

	public void setTipoCobro(TipoCobro tipoCobro) {
		this.tipoCobro = Ref.create(tipoCobro);
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (tiposervicioid != null ? tiposervicioid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof TipoServicio)) {
			return false;
		}
		TipoServicio other = (TipoServicio) object;
		if ((this.tiposervicioid == null && other.tiposervicioid != null)
				|| (this.tiposervicioid != null && !this.tiposervicioid.equals(other.tiposervicioid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.core.shared.model.TipoServicio[ tiposervicioid=" + tiposervicioid + " ]";
	}

	public Double getExtraordinaria() {
		return extraordinaria;
	}

	public void setExtraordinaria(Double extraordinaria) {
		this.extraordinaria = extraordinaria;
	}

	public Double getExtemporanea() {
		return extemporanea;
	}

	public void setExtemporanea(Double extemporanea) {
		this.extemporanea = extemporanea;
	}

	public Double getTasaMora() {
		return tasaMora;
	}

	public void setTasaMora(Double tasaMora) {
		this.tasaMora = tasaMora;
	}

	public Integer getOrdenAplicacion() {
		return ordenAplicacion;
	}

	public void setOrdenAplicacion(Integer ordenAplicacion) {
		this.ordenAplicacion = ordenAplicacion;
	}

}
