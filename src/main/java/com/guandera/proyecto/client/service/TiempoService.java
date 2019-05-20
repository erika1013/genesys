package com.guandera.proyecto.client.service;

import java.util.List;

import com.guandera.proyecto.shared.model.Tiempo;

public interface TiempoService {

	public void actualizar(Tiempo itemTiempo);

	public Tiempo consultarPorId(Long itemTiempo);

	public List<Tiempo> consultarTodos();

	public long contar();

	public void crear(Tiempo itemTiempo);

	public void eliminar(Tiempo itemTiempo);

	public Long siguienteRegistro();

}
