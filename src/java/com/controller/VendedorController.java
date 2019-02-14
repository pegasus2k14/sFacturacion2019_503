package com.controller;

import com.dao.VendedorDao;
import com.impl.VendedorDaoImp;
import com.model.Vendedor;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "vendedorController")
@ViewScoped
public class VendedorController implements Serializable {
    //variables
    private List<Vendedor> listVendedor;
    private Vendedor vendedor;

    public VendedorController() {
    }
    
     //Metode que se ocupra de crear una nueva instancia de Vendedor Vacia
    public void prepararNuevoVendedor(){
        vendedor  = new Vendedor();
    }
    
    //Metodo para insertar un nuevo Vendedor
    public void nuevoVendedor(){
        VendedorDao vendedorDao = new VendedorDaoImp();
        try{
            vendedorDao.insertVendedor(vendedor);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    //Metodo para Modificar un Vendedor existente
    public void modificarVendedor(){
        VendedorDao vendedorDao = new VendedorDaoImp();
        try{
            vendedorDao.updateVendedor(vendedor);
            //limpiamos la instancia de Vendedor
            prepararNuevoVendedor();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    //Metodo para eliminar una instancia de Cliente
    public void eliminarVendedor(){
        VendedorDao vendedorDao = new VendedorDaoImp();
        try{
            vendedorDao.deleteVendedor(vendedor);
            prepararNuevoVendedor();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    

//Getters y Setters

    public List<Vendedor> getListVendedor() {
        VendedorDao vendedorDao = new VendedorDaoImp();
        listVendedor = vendedorDao.listarVendedores();
        return listVendedor;
    }

    public void setListVendedor(List<Vendedor> listVendedor) {
        this.listVendedor = listVendedor;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }
    
    
}
