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
public class Compra extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long comparaid;

	private String comprafactura;

	private Double compravalor;

	private Double compravaloriva;

	private Double compravalortotal;

	@Index
	@Load
	Ref<Proveedor> proveedor;

	@Index
	@Load
	Ref<ImpuestoConcepto> impuestoConcepto;

	public Compra() {
	}

	public Compra(Long comparaid) {
		this.comparaid = comparaid;
	}

	public Long getComparaid() {
		return comparaid;
	}

	public void setComparaid(Long comparaid) {
		this.comparaid = comparaid;
	}

	public String getComprafactura() {
		return comprafactura;
	}

	public void setComprafactura(String comprafactura) {
		this.comprafactura = comprafactura;
	}

	public Double getCompravalor() {
		return compravalor;
	}

	public void setCompravalor(Double compravalor) {
		this.compravalor = compravalor;
	}

	public Double getCompravaloriva() {
		return compravaloriva;
	}

	public void setCompravaloriva(Double compravaloriva) {
		this.compravaloriva = compravaloriva;
	}

	public Double getCompravalortotal() {
		return compravalortotal;
	}

	public void setCompravalortotal(Double compravalortotal) {
		this.compravalortotal = compravalortotal;
	}

	public Proveedor getProveedor() {
		return proveedor.get();
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = Ref.create(proveedor);
	}

	public ImpuestoConcepto getImpuestoConcepto() {
		return impuestoConcepto.get();
	}

	public void setImpuestoConcepto(ImpuestoConcepto impuestoConcepto) {
		this.impuestoConcepto = Ref.create(impuestoConcepto);
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (comparaid != null ? comparaid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Compra)) {
			return false;
		}
		Compra other = (Compra) object;
		if ((this.comparaid == null && other.comparaid != null)
				|| (this.comparaid != null && !this.comparaid.equals(other.comparaid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.core.shared.model.Compra[ comparaid=" + comparaid + " ]";
	}

}
