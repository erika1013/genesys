package com.guandera.core.client.service;

import java.util.List;

import com.guandera.core.shared.model.Compania;
import com.guandera.core.shared.model.Proveedor;
import com.guandera.core.shared.model.ProveedorContacto;
import com.guandera.core.shared.model.TipoIdentificacion;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface ProveedorService {

	public void actualizar(Proveedor proveedor);

	public Proveedor consultarPorId(Long proveedorid);

	public List<Proveedor> consultarTodos();

	public long contar();

	public void crear(Proveedor proveedor);

	public void eliminar(Proveedor proveedor);

	public Long siguienteRegistro();

	public List<Compania> consultarCompanias();

	public List<TipoIdentificacion> consultarTiposIdentificacion();

	public List<ProveedorContacto> consultarProveedorContactos(Long proveedorid);

	public void crearProveedorContacto(ProveedorContacto itemProveedorContacto);

	public void actualizarProveedorContacto(ProveedorContacto itemProveedorContacto);

	public void eliminarProveedorContacto(ProveedorContacto itemProveedorContacto);

}
