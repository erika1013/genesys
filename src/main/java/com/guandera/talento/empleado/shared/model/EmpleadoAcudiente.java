/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guandera.talento.empleado.shared.model;

import java.io.Serializable;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;
import com.guandera.core.shared.model.Auditoria;
import com.guandera.core.shared.model.TipoIdentificacion;

/**
 * 
 * @author Fredi J. Velasco Villarreal - GUANDERA S.A.S.
 */
@Entity
public class EmpleadoAcudiente extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long empleadoAcudienteid;

	private String primernombre;

	private String segundonombre;

	private String primerapellido;

	private String segundoapellido;

	private String nombreApellido;
	private String apellidoNombre;

	@Index
	@Load
	Ref<TipoIdentificacion> tipoIdentificacion;

	@Index
	@Load
	Ref<Empleado> empleado;

	@Index
	@Load
	private Long numeroidentificacion;

	private Long telefono1;
	private Long extension1;
	private Long extension11;

	private Long telefono2;
	private Long extension2;
	private Long extension22;

	private Long telefono3;
	private Long extension3;
	private Long extension33;

	private String direccion;

	private String email;

	public EmpleadoAcudiente() {
	}

	public EmpleadoAcudiente(Long empleadoAcudienteid) {
		this.empleadoAcudienteid = empleadoAcudienteid;
	}

	public Long getEmpleadoAcudienteid() {
		return empleadoAcudienteid;
	}

	public void setEmpleadoAcudienteid(Long empleadoAcudienteid) {
		this.empleadoAcudienteid = empleadoAcudienteid;
	}

	public Long getTelefono1() {
		return telefono1;
	}

	public void setTelefono1(Long telefono1) {
		this.telefono1 = telefono1;
	}

	public Long getTelefono2() {
		return telefono2;
	}

	public void setTelefono2(Long telefono2) {
		this.telefono2 = telefono2;
	}

	public Long getTelefono3() {
		return telefono3;
	}

	public void setTelefono3(Long telefono3) {
		this.telefono3 = telefono3;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Empleado getEmpleado() {
		return empleado.get();
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = Ref.create(empleado);
	}

	public String getNombreApellido() {
		return nombreApellido;
	}

	public void setNombreApellido(String nombreApellido) {
		this.nombreApellido = nombreApellido;
	}

	public String getApellidoNombre() {
		return apellidoNombre;
	}

	public void setApellidoNombre(String apellidoNombre) {
		this.apellidoNombre = apellidoNombre;
	}

	public Long getNumeroidentificacion() {
		return numeroidentificacion;
	}

	public void setNumeroidentificacion(Long numeroidentificacion) {
		this.numeroidentificacion = numeroidentificacion;
	}

	public Long getExtension1() {
		return extension1;
	}

	public void setExtension1(Long extension1) {
		this.extension1 = extension1;
	}

	public Long getExtension11() {
		return extension11;
	}

	public void setExtension11(Long extension11) {
		this.extension11 = extension11;
	}

	public Long getExtension2() {
		return extension2;
	}

	public void setExtension2(Long extension2) {
		this.extension2 = extension2;
	}

	public Long getExtension22() {
		return extension22;
	}

	public void setExtension22(Long extension22) {
		this.extension22 = extension22;
	}

	public Long getExtension3() {
		return extension3;
	}

	public void setExtension3(Long extension3) {
		this.extension3 = extension3;
	}

	public Long getExtension33() {
		return extension33;
	}

	public void setExtension33(Long extension33) {
		this.extension33 = extension33;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (empleadoAcudienteid != null ? empleadoAcudienteid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof EmpleadoAcudiente)) {
			return false;
		}
		EmpleadoAcudiente other = (EmpleadoAcudiente) object;
		if ((this.empleadoAcudienteid == null && other.empleadoAcudienteid != null)
				|| (this.empleadoAcudienteid != null && !this.empleadoAcudienteid.equals(other.empleadoAcudienteid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.core.shared.model.EmpleadoAcudiente[ empleadoAcudienteid=" + empleadoAcudienteid + " ]";
	}

}
