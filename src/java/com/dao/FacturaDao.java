package com.dao;

import com.model.Factura;

public interface FacturaDao {
    //firmas de metodos
      //Metodo que obtiene el ultimo registro insertado en la Tabla Factura
      public Factura getMaxNumeroFactura();
      
      //Metodo que obtiene el numero de regstros existentes en la afctura
      public long numeroRegistrosFactura();
    
    
}
