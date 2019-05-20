package com.guandera.core.client.service;

import java.util.List;

import com.guandera.core.shared.model.ImpuestoConcepto;
import com.guandera.core.shared.model.PagoTipo;
import com.guandera.core.shared.model.Proveedor;
import com.guandera.core.shared.model.ProveedorRetencion;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface ProveedorRetencionService {

	public void actualizar(ProveedorRetencion proveedorRetencion);

	public ProveedorRetencion consultarPorId(Long proveedorRetencionid);

	public List<ProveedorRetencion> consultarTodos();

	public long contar();

	public void crear(ProveedorRetencion proveedorRetencion);

	public void eliminar(ProveedorRetencion proveedorRetencion);

	public Long siguienteRegistro();

	public List<Proveedor> consultarProveedores();

	public List<PagoTipo> consultarTiposPago();

	public List<ImpuestoConcepto> consultarTiposConceptos();

}
