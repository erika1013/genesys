/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guandera.core.shared.model;

import java.io.Serializable;
import java.util.Date;

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
public class ProveedorContacto extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long proveedorcontactoid;

	private String primernombre;

	private String segundonombre;

	private String primerapellido;

	private String segundoapellido;

	@Index
	@Load
	Ref<TipoIdentificacion> tipoIdentificacion;

	private Integer numeroidentificacion;

	private Date fechanacimiento;

	private Long proveedorcontactocelular;

	private Long proveedorcontactotelefono;

	private Integer proveedorcontactoeextension;

	private String proveedorcontactoemail;

	@Index
	@Load
	Ref<Proveedor> proveedor;

	public ProveedorContacto() {
	}

	public ProveedorContacto(Long proveedorcontactoid) {
		this.proveedorcontactoid = proveedorcontactoid;
	}

	public Long getProveedorcontactoid() {
		return proveedorcontactoid;
	}

	public void setProveedorcontactoid(Long proveedorcontactoid) {
		this.proveedorcontactoid = proveedorcontactoid;
	}

	public Long getProveedorcontactocelular() {
		return proveedorcontactocelular;
	}

	public void setProveedorcontactocelular(Long proveedorcontactocelular) {
		this.proveedorcontactocelular = proveedorcontactocelular;
	}

	public Long getProveedorcontactotelefono() {
		return proveedorcontactotelefono;
	}

	public void setProveedorcontactotelefono(Long proveedorcontactotelefono) {
		this.proveedorcontactotelefono = proveedorcontactotelefono;
	}

	public Integer getProveedorcontactoeextension() {
		return proveedorcontactoeextension;
	}

	public void setProveedorcontactoeextension(Integer proveedorcontactoeextension) {
		this.proveedorcontactoeextension = proveedorcontactoeextension;
	}

	public String getProveedorcontactoemail() {
		return proveedorcontactoemail;
	}

	public void setProveedorcontactoemail(String proveedorcontactoemail) {
		this.proveedorcontactoemail = proveedorcontactoemail;
	}

	public Proveedor getProveedor() {
		return proveedor.get();
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = Ref.create(proveedor);
	}

	public String getPrimernombre() {
		return primernombre;
	}

	public void setPrimernombre(String primernombre) {
		this.primernombre = primernombre;
	}

	public String getSegundonombre() {
		return segundonombre;
	}

	public void setSegundonombre(String segundonombre) {
		this.segundonombre = segundonombre;
	}

	public String getPrimerapellido() {
		return primerapellido;
	}

	public void setPrimerapellido(String primerapellido) {
		this.primerapellido = primerapellido;
	}

	public String getSegundoapellido() {
		return segundoapellido;
	}

	public void setSegundoapellido(String segundoapellido) {
		this.segundoapellido = segundoapellido;
	}

	public TipoIdentificacion getTipoIdentificacion() {
		return tipoIdentificacion.get();
	}

	public void setTipoIdentificacion(TipoIdentificacion tipoIdentificacion) {
		this.tipoIdentificacion = Ref.create(tipoIdentificacion);
	}

	public Integer getNumeroidentificacion() {
		return numeroidentificacion;
	}

	public void setNumeroidentificacion(Integer numeroidentificacion) {
		this.numeroidentificacion = numeroidentificacion;
	}

	public Date getFechanacimiento() {
		return fechanacimiento;
	}

	public void setFechanacimiento(Date fechanacimiento) {
		this.fechanacimiento = fechanacimiento;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (proveedorcontactoid != null ? proveedorcontactoid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof ProveedorContacto)) {
			return false;
		}
		ProveedorContacto other = (ProveedorContacto) object;
		if ((this.proveedorcontactoid == null && other.proveedorcontactoid != null)
				|| (this.proveedorcontactoid != null && !this.proveedorcontactoid.equals(other.proveedorcontactoid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.core.shared.model.ProveedorContacto[ proveedorcontactoid=" + proveedorcontactoid + " ]";
	}

}
