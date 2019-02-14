package com.controller;

import com.dao.ClienteDao;
import com.impl.ClienteDaoImp;
import com.model.Cliente;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "clienteController")
@ViewScoped
public class ClienteController implements Serializable{
  //Variables
    List<Cliente> listCliente;
    Cliente cliente;
    
    //constructor vacio
    public ClienteController() {
        cliente = new Cliente();
    }
    
    //Metode que se ocupra de crear una nueva instancia de Cliente Vacia
    public void prepararNuevoCliente(){
        cliente  = new Cliente();
    }
    
    //Metodo para insertar un nuevo Cliente
    public void nuevoCliente(){
        ClienteDao clienteDao = new ClienteDaoImp();
        try{
            clienteDao.insertCliente(cliente);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    //Metodo para Modificar un cliente existente
    public void modificarCliente(){
        ClienteDao clienteDao = new ClienteDaoImp();
        try{
            clienteDao.updateCliente(cliente);
            //limpiamos la instancia de Cliente
            prepararNuevoCliente();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    //Metodo para eliminar una instancia de Cliente
    public void eliminarcliente(){
        ClienteDao clienteDao = new ClienteDaoImp();
        try{
            clienteDao.deleteCliente(cliente);
            prepararNuevoCliente();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    //GETTERS Y SETTERS

    public List<Cliente> getListCliente() {
        //creamos instancia de 'ClienteDao'
        ClienteDao clienteDao = new ClienteDaoImp();
        //obtenemos la lista de Clientes
        listCliente = clienteDao.listarClientes();
        //retornamos la Lista de Clientes
        return listCliente;
    }

    public void setListCliente(List<Cliente> listCliente) {
        this.listCliente = listCliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
}
