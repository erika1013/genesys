package com.guandera.proyecto.client.service;

import java.util.List;

import com.guandera.proyecto.shared.model.ActividadTipo;

public interface ActividadTipoService {

	public void actualizar(ActividadTipo itemActividadTipo);

	public ActividadTipo consultarPorId(Long itemActividadTipo);

	public List<ActividadTipo> consultarTodos();

	public long contar();

	public void crear(ActividadTipo itemActividadTipo);

	public void eliminar(ActividadTipo itemActividadTipo);

	public Long siguienteRegistro();

}
