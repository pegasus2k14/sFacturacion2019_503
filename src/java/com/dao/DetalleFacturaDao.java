package com.dao;

import com.model.Detallefactura;

public interface DetalleFacturaDao {
    //Metodo para guardar el detalle de la factura
    public boolean guardarDetalleFactura(Detallefactura detalleFactura);
    
}
