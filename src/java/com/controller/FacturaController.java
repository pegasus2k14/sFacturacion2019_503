package com.controller;

import com.dao.ClienteDao;
import com.impl.ClienteDaoImp;
import com.model.Cliente;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;


@Named(value = "facturaController")
@ViewScoped
public class FacturaController implements Serializable{
    //Variables
    private Cliente cliente;
    private Integer codigoCliente;

    //Constructor
    public FacturaController() {
        
    }
    
    
    //metodo para agregar los datos de los clientes por medio del
    // dialogClientes
    public void agregarDatosCliente(Integer codCliente){
        try{
          ClienteDao clienteDao = new ClienteDaoImp();
          //obtenemos la instancia de Cliente en Base a su codigo
          this.cliente = clienteDao.obtenerClientePorCodigo(codCliente);
          //Mensaje de confirmacion de exito de la operacion
            FacesMessage message = new  FacesMessage(FacesMessage.SEVERITY_INFO,"Correcto", "Cliente agregado correctamente!");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    //metodo para agregar los datos de los clientes por medio del
    // InputTexr
    public void agregarDatosClienteText(){
        try{
            
          if(codigoCliente== null){
              return;  //para que nos mantenga siempre en el imput
          }  
          
          ClienteDao clienteDao = new ClienteDaoImp();
          //obtenemos la instancia de Cliente en Base a su codigo
          this.cliente = clienteDao.obtenerClientePorCodigo(codigoCliente);
          
          if(cliente != null){
            //Mensaje de confirmacion de exito de la operacion  
            FacesMessage message = new  FacesMessage(FacesMessage.SEVERITY_INFO,"Correcto", "Cliente agregado correctamente!");
            FacesContext.getCurrentInstance().addMessage(null, message);  
          }else{
            FacesMessage message = new  FacesMessage(FacesMessage.SEVERITY_ERROR,"Error", "Codigo de Cliente NO valido!");
            FacesContext.getCurrentInstance().addMessage(null, message);  
          }
          
          codigoCliente = null;  
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    
    //Getters y Setters

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Integer getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(Integer codigoCliente) {
        this.codigoCliente = codigoCliente;
    }
    
}
