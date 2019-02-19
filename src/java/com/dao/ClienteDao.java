package com.dao;

import com.model.Cliente;
import java.util.List;

public interface ClienteDao {
    //Firmas de metodos
    //Metodo que lista todas las instancias de Clientes
    public List<Cliente> listarClientes();
    //Metodo que agrega una nueva instancia de Cliente a la BD
    public void insertCliente(Cliente cliente);
    //Metodo que modifica una instancia de Cliente
    public void updateCliente(Cliente cliente);
    //metodo que elimina una instancia de Cliente
    public void deleteCliente(Cliente cliente);
    
    
    //Metodo que sera usado por la Clase controladora 'facturaController.java'
    public Cliente obtenerClientePorCodigo(int codCliente);
    
}
