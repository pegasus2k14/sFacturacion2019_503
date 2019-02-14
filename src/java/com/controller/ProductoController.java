package com.controller;

import com.dao.ProductoDao;
import com.impl.ProductoDaoImp;
import com.model.Producto;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "productoController")
@ViewScoped
public class ProductoController implements Serializable{
//variables
    private List<Producto> listProducto;
    private Producto producto;
    
//Constructor vacio
    public ProductoController() {
        producto = new Producto();
    }
    
     //Metode que se ocupra de crear una nueva instancia de Producto Vacia
    public void prepararNuevoProducto(){
        producto  = new Producto();
    }
    
    //Metodo para insertar un nuevo Vendedor
    public void nuevoProducto(){
        ProductoDao productoDao = new ProductoDaoImp();
        try{
            productoDao.insertProducto(producto);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    //Metodo para Modificar un Producto existente
    public void modificarProducto(){
        ProductoDao productoDao = new ProductoDaoImp();
        try{
            productoDao.updateProducto(producto);
            //limpiamos la instancia de Producto
            prepararNuevoProducto();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    //Metodo para eliminar una instancia de Producto
    public void eliminarProducto(){
        ProductoDao productoDao = new ProductoDaoImp();
        try{
            productoDao.deleteProducto(producto);
            prepararNuevoProducto();
        }catch(Exception e){
            e.printStackTrace();
        }
    }    
    
    
    
    //Getters y Setters

    public List<Producto> getListProducto() {
        ProductoDao productoDao = new ProductoDaoImp();
        listProducto = productoDao.listarProductos();
        return listProducto;
    }

    public void setListProducto(List<Producto> listProducto) {
        this.listProducto = listProducto;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
    
}
