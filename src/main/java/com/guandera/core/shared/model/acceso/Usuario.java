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
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long usuarioid;

	@Index
	private String usuariousuario;

	@Index
	private String usuarioclave;

	private String usuarionombre;

	private String usuarioapellido;

	private String usuarioemail;

	private Date usuarioultimoingreso;

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

	public Usuario() {
	}

	public Usuario(Long usuarioid) {
		this.usuarioid = usuarioid;
	}

	public Usuario(Long usuarioid, String usuariousuario) {
		this.usuarioid = usuarioid;
		this.usuariousuario = usuariousuario;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Usuario)) {
			return false;
		}
		Usuario other = (Usuario) object;
		if ((this.usuarioid == null && other.usuarioid != null)
				|| (this.usuarioid != null && !this.usuarioid.equals(other.usuarioid))) {
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

	public String getUsuarioapellido() {
		return usuarioapellido;
	}

	public String getUsuarioclave() {
		return usuarioclave;
	}

	public Long getUsuarioid() {
		return usuarioid;
	}

	public String getUsuarionombre() {
		return usuarionombre;
	}

	public String getUsuariousuario() {
		return usuariousuario;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (usuarioid != null ? usuarioid.hashCode() : 0);
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

	public void setUsuarioapellido(String usuarioapellido) {
		this.usuarioapellido = usuarioapellido;
	}

	public void setUsuarioclave(String usuarioclave) {
		this.usuarioclave = usuarioclave;
	}

	public void setUsuarioid(Long usuarioid) {
		this.usuarioid = usuarioid;
	}

	public void setUsuarionombre(String usuarionombre) {
		this.usuarionombre = usuarionombre;
	}

	public void setUsuariousuario(String usuariousuario) {
		this.usuariousuario = usuariousuario;
	}

	public String getUsuarioemail() {
		return usuarioemail;
	}

	public void setUsuarioemail(String usuarioemail) {
		this.usuarioemail = usuarioemail;
	}

	public Date getUsuarioultimoingreso() {
		return usuarioultimoingreso;
	}

	public void setUsuarioultimoingreso(Date usuarioultimoingreso) {
		this.usuarioultimoingreso = usuarioultimoingreso;
	}

	@Override
	public String toString() {
		return "com.davivienda.sigpre.modelo.entity.Usuario[ usuarioid=" + usuarioid + " ]";
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
