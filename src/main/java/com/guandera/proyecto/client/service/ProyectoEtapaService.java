package com.guandera.proyecto.client.service;

import java.util.List;

import com.guandera.proyecto.shared.model.ProyectoEtapa;

public interface ProyectoEtapaService {

	public void actualizar(ProyectoEtapa itemProyectoEtapa);

	public ProyectoEtapa consultarPorId(Long itemProyectoEtapa);

	public List<ProyectoEtapa> consultarTodos();

	public long contar();

	public void crear(ProyectoEtapa itemProyectoEtapa);

	public void eliminar(ProyectoEtapa itemProyectoEtapa);

	public Long siguienteRegistro();

}
