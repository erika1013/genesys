
package com.guandera.core.shared.model.acceso;

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
public class UsuarioAdm implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long usuarioAdmid;

	@Index
	private String usuarioAdmclave;

	private String usuarioAdmnombre;

	private String usuarioAdmapellido;

	@Index
	private String usuarioAdmemail;

	private Date usuarioAdmultimoingreso;

	@Index
	@Load
	Ref<Rol> rol;

	private Long creacionusuario;

	private Date creacionfecha;

	private Long modificacionusuario;

	private Date modificacionfecha;

	private boolean crear;
	private boolean modificar;
	private boolean eliminar;
	private boolean consultar;
	private boolean imprimir;
	private boolean exportar;
	private boolean importar;

	public UsuarioAdm() {
	}

	public UsuarioAdm(Long usuarioAdmid) {
		this.usuarioAdmid = usuarioAdmid;
	}

	public UsuarioAdm(Long usuarioAdmid, String usuarioAdmemail) {
		this.usuarioAdmid = usuarioAdmid;
		this.usuarioAdmemail = usuarioAdmemail;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof UsuarioAdm)) {
			return false;
		}
		UsuarioAdm other = (UsuarioAdm) object;
		if ((this.usuarioAdmid == null && other.usuarioAdmid != null)
				|| (this.usuarioAdmid != null && !this.usuarioAdmid.equals(other.usuarioAdmid))) {
			return false;
		}
		return true;
	}

	public Date getCreacionfecha() {
		return creacionfecha;
	}

	public Long getCreacionusuario() {
		return creacionusuario;
	}

	public Date getModificacionfecha() {
		return modificacionfecha;
	}

	public Long getModificacionusuario() {
		return modificacionusuario;
	}

	public Rol getRol() {
		return rol.get();
	}

	public String getUsuarioAdmapellido() {
		return usuarioAdmapellido;
	}

	public String getUsuarioAdmclave() {
		return usuarioAdmclave;
	}

	public Long getUsuarioAdmid() {
		return usuarioAdmid;
	}

	public String getUsuarioAdmnombre() {
		return usuarioAdmnombre;
	}

	public String getUsuarioAdmemail() {
		return usuarioAdmemail;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (usuarioAdmid != null ? usuarioAdmid.hashCode() : 0);
		return hash;
	}

	public void setCreacionfecha(Date creacionfecha) {
		this.creacionfecha = creacionfecha;
	}

	public void setCreacionusuario(Long creacionusuario) {
		this.creacionusuario = creacionusuario;
	}

	public void setModificacionfecha(Date modificacionfecha) {
		this.modificacionfecha = modificacionfecha;
	}

	public void setModificacionusuario(Long modificacionusuario) {
		this.modificacionusuario = modificacionusuario;
	}

	public void setRol(Rol rol) {
		this.rol = Ref.create(rol);
	}

	public void setUsuarioAdmapellido(String usuarioAdmapellido) {
		this.usuarioAdmapellido = usuarioAdmapellido;
	}

	public void setUsuarioAdmclave(String usuarioAdmclave) {
		this.usuarioAdmclave = usuarioAdmclave;
	}

	public void setUsuarioAdmid(Long usuarioAdmid) {
		this.usuarioAdmid = usuarioAdmid;
	}

	public void setUsuarioAdmnombre(String usuarioAdmnombre) {
		this.usuarioAdmnombre = usuarioAdmnombre;
	}

	public void setUsuarioAdmemail(String usuarioAdmemail) {
		this.usuarioAdmemail = usuarioAdmemail;
	}

	public Date getUsuarioAdmultimoingreso() {
		return usuarioAdmultimoingreso;
	}

	public void setUsuarioAdmultimoingreso(Date usuarioAdmultimoingreso) {
		this.usuarioAdmultimoingreso = usuarioAdmultimoingreso;
	}

	@Override
	public String toString() {
		return "com.davivienda.sigpre.modelo.entity.UsuarioAdm[ usuarioAdmid=" + usuarioAdmid + " ]";
	}

	public boolean isCrear() {
		return crear;
	}

	public void setCrear(boolean crear) {
		this.crear = crear;
	}

	public boolean isModificar() {
		return modificar;
	}

	public void setModificar(boolean modificar) {
		this.modificar = modificar;
	}

	public boolean isEliminar() {
		return eliminar;
	}

	public void setEliminar(boolean eliminar) {
		this.eliminar = eliminar;
	}

	public boolean isConsultar() {
		return consultar;
	}

	public void setConsultar(boolean consultar) {
		this.consultar = consultar;
	}

	public boolean isImprimir() {
		return imprimir;
	}

	public void setImprimir(boolean imprimir) {
		this.imprimir = imprimir;
	}

	public boolean isExportar() {
		return exportar;
	}

	public void setExportar(boolean exportar) {
		this.exportar = exportar;
	}

	public boolean isImportar() {
		return importar;
	}

	public void setImportar(boolean importar) {
		this.importar = importar;
	}

}
