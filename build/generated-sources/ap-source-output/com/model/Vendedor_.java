package com.model;

import com.model.Factura;
import com.model.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2019-02-27T15:52:24")
@StaticMetamodel(Vendedor.class)
public class Vendedor_ { 

    public static volatile SingularAttribute<Vendedor, String> apellidos;
    public static volatile ListAttribute<Vendedor, Usuario> usuarioList;
    public static volatile SingularAttribute<Vendedor, String> direccion;
    public static volatile ListAttribute<Vendedor, Factura> facturaList;
    public static volatile SingularAttribute<Vendedor, String> dui;
    public static volatile SingularAttribute<Vendedor, String> celular;
    public static volatile SingularAttribute<Vendedor, Integer> codVendedor;
    public static volatile SingularAttribute<Vendedor, String> nombres;

}