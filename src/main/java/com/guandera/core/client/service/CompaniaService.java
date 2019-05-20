package com.guandera.core.client.service;

import java.util.List;

import com.guandera.core.shared.model.Compania;
import com.guandera.core.shared.model.CompaniaCuenta;
import com.guandera.core.shared.model.CompaniaImpuesto;
import com.guandera.core.shared.model.Sede;
import com.guandera.core.shared.model.acceso.UsuarioAdm;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface CompaniaService {

	public void actualizar(Compania compania);

	public Compania consultarPorId(Long companiaid);

	public List<Compania> consultarTodos();

	// public List<Compania> consultarCompaniaPorAncestor(Long usuarioAdmid);

	public long contar();

	public void crear(Compania compania);

	public void eliminar(Compania compania);

	public Long siguienteRegistro();

	public void crearSede(Sede itemSede);

	public void eliminarSede(Sede itemSede);

	public void actualizarSede(Sede itemSede);

	public List<Sede> consultarSedesPorCompania(Long companiaid);

	// public List<Sede> consultarSedesPorCompaniaAncestor(Long companiaid);

	Long siguienteRegistro1();

	public List<Compania> consultarPorUsuarioAdmCreador(UsuarioAdm usuarioSessionAdmId);

	public void eliminarImpuesto(CompaniaImpuesto itemImpuesto);

	public void actualizarImpuesto(CompaniaImpuesto itemImpuesto);

	public void crearImpuesto(CompaniaImpuesto itemImpuesto);

	public List<CompaniaImpuesto> consultarImpuestoPorCompania(Long companiaid);

	public List<CompaniaCuenta> consultarCuentaPorCompania(Long companiaid);

	public void crearCuenta(CompaniaCuenta itemCuenta);

	public void actualizarCuenta(CompaniaCuenta itemCuenta);

	public void eliminarCuenta(CompaniaCuenta itemCuenta);

}
