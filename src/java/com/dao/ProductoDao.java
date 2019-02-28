package com.dao;

import com.model.Producto;
import java.util.List;

public interface ProductoDao {
     //Firmas de metodos
    //Metodo que lista todas las instancias de Producto
    public List<Producto> listarProductos();
    //Metodo que agrega una nueva instancia de Producto a la BD
    public void insertProducto(Producto producto);
    //Metodo que modifica una instancia de Producto
    public void updateProducto(Producto producto);
    //metodo que elimina una instancia de Producto
    public void deleteProducto(Producto producto);
    
    //Metodo utilizado en 'FacturaController.java'
    public Producto obtenerProductoPorCodigoBarra(String codBarra);
}
