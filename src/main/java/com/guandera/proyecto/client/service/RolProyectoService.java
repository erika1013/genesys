package com.guandera.proyecto.client.service;

import java.util.List;

import com.guandera.proyecto.shared.model.RolProyecto;

public interface RolProyectoService {

	public void actualizar(RolProyecto itemRolProyecto);

	public RolProyecto consultarPorId(Long itemRolProyecto);

	public List<RolProyecto> consultarTodos();

	public long contar();

	public void crear(RolProyecto itemRolProyecto);

	public void eliminar(RolProyecto itemRolProyecto);

	public Long siguienteRegistro();

}
