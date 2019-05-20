package com.guandera.talento.client.service;

import java.util.List;

import com.guandera.talento.shared.model.Idioma;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface IdiomaService {

	public void actualizar(Idioma idioma);

	public Idioma consultarPorId(Long idiomaid);

	public List<Idioma> consultarTodos();

	public long contar();

	public void crear(Idioma idioma);

	public void eliminar(Idioma idioma);

}
