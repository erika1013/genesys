package com.guandera.core.client.service;

import java.util.List;

import com.guandera.core.shared.model.CuentaCobro;
import com.guandera.core.shared.model.PagoTipo;
import com.guandera.core.shared.model.Proveedor;
import com.guandera.core.shared.model.ProveedorPago;
import com.guandera.core.shared.model.ProveedorPagoEstado;
import com.guandera.core.shared.model.ProveedorPagoRetencion;
import com.guandera.core.shared.model.ProveedorRetencion;

public interface ProveedorPagoService {

	public void actualizar(ProveedorPago proveedorPago);

	public ProveedorPago consultarPorId(Long proveedorPagoid);

	public List<ProveedorPago> consultarTodos();

	public long contar();

	public void crear(ProveedorPago proveedorPago);

	public void eliminar(ProveedorPago proveedorPago);

	public Long siguienteRegistro();

	public List<ProveedorPagoEstado> consultarEstado();

	public List<PagoTipo> consultarTipoPago();

	public List<Proveedor> consultarProveedores();

	public List<ProveedorRetencion> consultarProveedoresconRetencion();

	public void crear(ProveedorPago proveedorPago, Proveedor proveedor);

	public Proveedor consultarProveedorvalido(Long proveedoridentificacion);

	public CuentaCobro consultarCuentaCobroProveedor(Long numeroIdentificacion, Long numeroCuenta);

	public ProveedorRetencion consultarRetencion(Long numeroIdentificacion);

	public Proveedor consultarProveedor(Long numeroIdentificacion);

	public List<ProveedorPagoRetencion> consultarProveedorPagoRetencion(Long pagoproveedor);

}
