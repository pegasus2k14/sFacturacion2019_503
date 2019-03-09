package com.dao;

import com.model.Factura;

public interface FacturaDao {
    //firmas de metodos
      //Metodo que obtiene el ultimo registro insertado en la Tabla Factura
      public Factura getMaxNumeroFactura();
      
      //Metodo que obtiene el numero de regstros existentes en la afctura
      public Long numeroRegistrosFactura();
      
      //Metodo para guardar el registro en la tabla Factura de la BD
      public boolean guardarFactura(Factura factura) throws Exception;
    
}
