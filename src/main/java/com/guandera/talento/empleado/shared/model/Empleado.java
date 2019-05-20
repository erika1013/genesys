/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guandera.talento.empleado.shared.model;

import java.io.Serializable;
import java.util.Date;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;
import com.guandera.core.shared.model.Auditoria;
import com.guandera.core.shared.model.Ciudad;
import com.guandera.core.shared.model.CompaniaCargo;
import com.guandera.core.shared.model.Sede;
import com.guandera.core.shared.model.TipoIdentificacion;
import com.guandera.talento.shared.model.EstadoCivil;

/**
 * 
 * @author Fredi J. Velasco Villarreal - GUANDERA S.A.S.
 */
@Entity
public class Empleado extends Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long empleadoid;
	
	
	@Index
	private Integer codigo;
	

	private String primerNombre;

	private String segundoNombre;

	private String primerApellido;

	private String segundoApellido;

	@Index
	private String apellidoNombre;

	private String nombreApellido;

	@Index
	private Long numeroIdentificacion;

	private Date fechaNacimiento;

	private Date fechaIngreso;

	private Date fechaRetiro;

	private String correo;

	private String celular;
	private String telefono;
	
	private Integer edad;

	private String sexo;
	
	private String direccion;
	
	
	@Index
	@Load
	Ref<Ciudad> lugarNacimiento;

	@Index
	@Load
	Ref<EstadoCivil> estadoCivil;
	
	
	@Index
	@Load
	Ref<Ciudad> ciudadResidencia;

	@Index
	@Load
	Ref<Ciudad> ciudadIdentificacion;
	
	
	

	@Index
	@Load
	Ref<Sede> sede;

	@Index
	@Load
	Ref<CompaniaCargo> cargo;

	@Index
	@Load
	Ref<TipoIdentificacion> tipoIdentificacion;

	@Index
	@Load
	Ref<EmpleadoEstado> estado;

	@Index
	private boolean contratoEmpleado; // para verificar si ese empleado ya tiene
										// contrato.

	public Empleado() {
	}

	public Empleado(Long empleadoid) {
		this.empleadoid = empleadoid;
	}

	public Long getEmpleadoid() {
		return empleadoid;
	}

	public void setEmpleadoid(Long empleadoid) {
		this.empleadoid = empleadoid;
	}

	


	public Sede getSede() {
		return sede.get();
	}

	public void setSede(Sede sede) {
		this.sede = Ref.create(sede);
	}

	

	public String getApellidoNombre() {
		return apellidoNombre;
	}

	public void setApellidoNombre(String apellidoNombre) {
		this.apellidoNombre = apellidoNombre;
	}

	public String getNombreApellido() {
		return nombreApellido;
	}

	public void setNombreApellido(String nombreApellido) {
		this.nombreApellido = nombreApellido;
	}

	


	public TipoIdentificacion getTipoIdentificacion() {
		return tipoIdentificacion.get();
	}

	public void setTipoIdentificacion(TipoIdentificacion tipoIdentificacion) {
		this.tipoIdentificacion = Ref.create(tipoIdentificacion);
	}



	

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (empleadoid != null ? empleadoid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {

		if (!(object instanceof Empleado)) {
			return false;
		}
		Empleado other = (Empleado) object;
		if ((this.empleadoid == null && other.empleadoid != null)
				|| (this.empleadoid != null && !this.empleadoid.equals(other.empleadoid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.guandera.core.shared.model.Empleado[ empleadoid=" + empleadoid + " ]";
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getPrimerNombre() {
		return primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public Long getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	public void setNumeroIdentificacion(Long numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Date getFechaRetiro() {
		return fechaRetiro;
	}

	public void setFechaRetiro(Date fechaRetiro) {
		this.fechaRetiro = fechaRetiro;
	}

	

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public CompaniaCargo getCargo() {
		return cargo.get();
	}

	public void setCargo(CompaniaCargo cargo) {
		this.cargo = Ref.create(cargo);
	}

	public EmpleadoEstado getEstado() {
		return estado.get();
	}

	public void setEstado(EmpleadoEstado estado) {
		this.estado = Ref.create(estado);
	}

	public boolean isContratoEmpleado() {
		return contratoEmpleado;
	}

	public void setContratoEmpleado(boolean contratoEmpleado) {
		this.contratoEmpleado = contratoEmpleado;
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
	
	public EstadoCivil getEstadoCivil() {
		return estadoCivil.get();
	}

	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = Ref.create(estadoCivil);
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

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	


}
