package com.model;

import com.model.Factura;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2019-02-13T16:33:35")
@StaticMetamodel(Cliente.class)
public class Cliente_ { 

    public static volatile SingularAttribute<Cliente, String> apellidos;
    public static volatile SingularAttribute<Cliente, String> direccion;
    public static volatile ListAttribute<Cliente, Factura> facturaList;
    public static volatile SingularAttribute<Cliente, Integer> codCliente;
    public static volatile SingularAttribute<Cliente, String> nombres;

}