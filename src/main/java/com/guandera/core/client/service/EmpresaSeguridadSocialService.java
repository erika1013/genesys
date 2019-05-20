package com.guandera.core.client.service;

import java.util.List;

import com.guandera.talento.empleado.shared.model.EmpresaSeguridadSocial;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface EmpresaSeguridadSocialService {

	public void actualizar(EmpresaSeguridadSocial empresaSeguridadSocial);

	public EmpresaSeguridadSocial consultarPorId(Long empresaSeguridadSocialid);

	public List<EmpresaSeguridadSocial> consultarTodos();

	public long contar();

	public void crear(EmpresaSeguridadSocial empresaSeguridadSocial);

	public void eliminar(EmpresaSeguridadSocial empresaSeguridadSocial);

	public Long siguienteRegistro();

}
