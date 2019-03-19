package com.model;

import com.model.Cliente;
import com.model.Detallefactura;
import com.model.Vendedor;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2019-03-18T15:43:18")
@StaticMetamodel(Factura.class)
public class Factura_ { 

    public static volatile SingularAttribute<Factura, Date> fechaRegistro;
    public static volatile ListAttribute<Factura, Detallefactura> detallefacturaList;
    public static volatile SingularAttribute<Factura, BigDecimal> totalVenta;
    public static volatile SingularAttribute<Factura, Integer> numeroFactura;
    public static volatile SingularAttribute<Factura, Vendedor> codVendedor;
    public static volatile SingularAttribute<Factura, Integer> codFactura;
    public static volatile SingularAttribute<Factura, Cliente> codCliente;

}