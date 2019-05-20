
package com.guandera.talento.aspirante.shared.model;

import java.io.Serializable;
import java.util.Date;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;
import com.guandera.core.shared.model.Auditoria;
import com.guandera.core.shared.model.Ciudad;
import com.guandera.talento.shared.model.EstadoCivil;

/**
 * 
 * @author FrediJavier
 */
@Entity
public class Aspirante extends Auditoria implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private Long aspiranteid;

	@Index
	private Integer codigo;

	private String nombres;

	private String apellidos;

	private Long identificacion;

	private Date fechaNacimiento;

	private Integer edad;

	private String sexo;

	private Long telefono;

	private Long celular;

	private String correo;

	private String direccion;

	private String perfil;

	@Index
	@Load
	Ref<Ciudad> lugarNacimiento;

	@Index
	@Load
	Ref<EstadoCivil> estadoCivil;

	@Index
	@Load
	Ref<AspiranteEstado> estado;

	@Index
	@Load
	Ref<Ciudad> ciudadResidencia;

	@Index
	@Load
	Ref<Ciudad> ciudadIdentificacion;

	public Aspirante() {
	}

	public Aspirante(Long aspiranteid) {
		this.aspiranteid = aspiranteid;
	}

	public Long getAspiranteid() {
		return aspiranteid;
	}

	public void setAspiranteid(Long aspiranteid) {
		this.aspiranteid = aspiranteid;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Long getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(Long identificacion) {
		this.identificacion = identificacion;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Long getTelefono() {
		return telefono;
	}

	public void setTelefono(Long telefono) {
		this.telefono = telefono;
	}

	public Long getCelular() {
		return celular;
	}

	public void setCelular(Long celular) {
		this.celular = celular;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public EstadoCivil getEstadoCivil() {
		return estadoCivil.get();
	}

	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = Ref.create(estadoCivil);
	}

	public AspiranteEstado getEstado() {
		return estado.get();
	}

	public void setEstado(AspiranteEstado estado) {
		this.estado = Ref.create(estado);
	}

	public Ciudad getLugarNacimiento() {
		return lugarNacimiento.get();
	}

	public void setLugarNacimiento(Ciudad lugarNacimiento) {
		this.lugarNacimiento = Ref.create(lugarNacimiento);
	}

	public Ciudad getCiudadResidencia() {
		return ciudadResidencia.get();
	}

	public void setCiudadResidencia(Ciudad ciudadResidencia) {
		this.ciudadResidencia = Ref.create(ciudadResidencia);
	}

	public Ciudad getCiudadIdentificacion() {
		return ciudadIdentificacion.get();
	}

	public void setCiudadIdentificacion(Ciudad ciudadIdentificacion) {
		this.ciudadIdentificacion = Ref.create(ciudadIdentificacion);
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (aspiranteid != null ? aspiranteid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Aspirante)) {
			return false;
		}
		Aspirante other = (Aspirante) object;
		if ((this.aspiranteid == null && other.aspiranteid != null)
				|| (this.aspiranteid != null && !this.aspiranteid.equals(other.aspiranteid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.talento.shared.model.Aspirante[ aspiranteid=" + aspiranteid + " ]";
	}

}
