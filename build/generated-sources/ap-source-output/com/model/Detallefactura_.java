package com.model;

import com.model.Factura;
import com.model.Producto;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2019-02-27T15:52:24")
@StaticMetamodel(Detallefactura.class)
public class Detallefactura_ { 

    public static volatile SingularAttribute<Detallefactura, BigDecimal> total;
    public static volatile SingularAttribute<Detallefactura, Producto> codProducto;
    public static volatile SingularAttribute<Detallefactura, Integer> codDetalle;
    public static volatile SingularAttribute<Detallefactura, String> codBarra;
    public static volatile SingularAttribute<Detallefactura, Integer> cantidad;
    public static volatile SingularAttribute<Detallefactura, BigDecimal> precioVenta;
    public static volatile SingularAttribute<Detallefactura, Factura> codFactura;
    public static volatile SingularAttribute<Detallefactura, String> nombreProducto;

}