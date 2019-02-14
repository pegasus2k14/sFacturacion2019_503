package com.dao;

import com.model.Vendedor;
import java.util.List;

public interface VendedorDao {
      //Firmas de metodos
    //Metodo que lista todas las instancias de Vendedor
    public List<Vendedor> listarVendedores();
    //Metodo que agrega una nueva instancia de Vendedor a la BD
    public void insertVendedor(Vendedor vendedor);
    //Metodo que modifica una instancia de Vendedor
    public void updateVendedor(Vendedor vendedor);
    //metodo que elimina una instancia de Vendedor
    public void deleteVendedor(Vendedor vendedor);
}

