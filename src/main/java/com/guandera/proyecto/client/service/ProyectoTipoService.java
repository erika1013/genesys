package com.guandera.proyecto.client.service;

import java.util.List;

import com.guandera.proyecto.shared.model.ProyectoTipo;

public interface ProyectoTipoService {

	public void actualizar(ProyectoTipo itemProyectoTipo);

	public ProyectoTipo consultarPorId(Long itemProyectoTipo);

	public List<ProyectoTipo> consultarTodos();

	public long contar();

	public void crear(ProyectoTipo itemProyectoTipo);

	public void eliminar(ProyectoTipo itemProyectoTipo);

	public Long siguienteRegistro();

}
