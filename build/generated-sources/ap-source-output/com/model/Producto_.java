package com.model;

import com.model.Detallefactura;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2019-03-18T15:43:18")
@StaticMetamodel(Producto.class)
public class Producto_ { 

    public static volatile SingularAttribute<Producto, Integer> stockMinimo;
    public static volatile SingularAttribute<Producto, Integer> codProducto;
    public static volatile SingularAttribute<Producto, String> codBarra;
    public static volatile ListAttribute<Producto, Detallefactura> detallefacturaList;
    public static volatile SingularAttribute<Producto, Integer> stockActual;
    public static volatile SingularAttribute<Producto, BigDecimal> precioVenta;
    public static volatile SingularAttribute<Producto, String> nombreProducto;

}