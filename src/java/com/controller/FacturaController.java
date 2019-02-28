package com.controller;

import com.dao.ClienteDao;
import com.dao.ProductoDao;
import com.impl.ClienteDaoImp;
import com.impl.ProductoDaoImp;
import com.model.Cliente;
import com.model.Detallefactura;
import com.model.Factura;
import com.model.Producto;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;


@Named(value = "facturaController")
@ViewScoped
public class FacturaController implements Serializable{
    //Variables
    private Cliente cliente;
    private Integer codigoCliente;
    private Producto producto;
    private String codigoBarra;
    private List<Detallefactura> listDetalle;
    
    
    private Integer cantidadProducto;
     private Integer cantidadProductoDlg;
    private String productoSeleccionado;
    private Factura  factura;

    //Constructor
    public FacturaController() {
        this.factura = new Factura();
        listDetalle = new ArrayList<>(); //inicializamos la lista
        codigoBarra="";
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
            codigoCliente = null;
            RequestContext.getCurrentInstance().update("frmFactura:inputCodCliente");
            //Mensaje de confirmacion de exito de la operacion  
            FacesMessage message = new  FacesMessage(FacesMessage.SEVERITY_INFO,"Correcto", "Cliente agregado correctamente!");
            FacesContext.getCurrentInstance().addMessage(null, message);  
          }else{
            codigoCliente = null;
            RequestContext.getCurrentInstance().update("frmFactura:inputCodCliente");
            FacesMessage message = new  FacesMessage(FacesMessage.SEVERITY_ERROR,"Error", "Cliente No encontrado con ese codigo");
            FacesContext.getCurrentInstance().addMessage(null, message);  
          }
          
          //codigoCliente = null;  
        }catch(Exception e){
            e.printStackTrace();
        } 
    }
    
    //Metodo para solicitar la cantidad de Producto a Vender
    public void pedirCantidadProducto(String codBarra){
        this.productoSeleccionado = codBarra;
    }
    
    
   //Metodo para agregar el Detalle del producto pro medio del DialogProducto
    //Los detalles de producto son agregados a la lista de Detalles
    public void agregarDetalleProducto() {
        //Creamos instancia de ProductoDao
        ProductoDao productoDao = new ProductoDaoImp();
        try {
            //obtenemos la instancia del producto
            producto = productoDao.obtenerProductoPorCodigoBarra(productoSeleccionado);
            if (cantidadProductoDlg != null && cantidadProductoDlg > 0) {
                //agregando detalle a la Lista de Detalle
                listDetalle.add(new Detallefactura(null, this.producto.getCodBarra(),
                        this.producto.getNombreProducto(), cantidadProductoDlg, this.producto.getPrecioVenta(),
                        (this.producto.getPrecioVenta().multiply(new BigDecimal(cantidadProductoDlg)))));

                //Limpiamos la variable 'cantidadProducto'
                cantidadProductoDlg = null;
                //Invocamos al metodo que calcula el Total de la Venta para la factura
                totalFacturaVenta();

                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Producto agregado al detalle!");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    

    //Metodo que obtiene una instancia de producto en base al codigo de barras
    //recibido desde el imputText y despliega el Dialog de la Cantidad de Producto
    public void obtenerCantidadProducto() {
        
        if (codigoBarra.equals("")) {
            return;  //para que nos mantenga siempre en el imput
        }

        ProductoDao productoDao = new ProductoDaoImp();
        try {
            //obtenemos la instancia de Producto en Base a su codigo de Barra
            producto = productoDao.obtenerProductoPorCodigoBarra(codigoBarra);

            if (producto != null) {
                //Levantamos dialog
                RequestContext.getCurrentInstance().execute("PF('dialogCantidadProducto2').show();");
            } else {
                codigoBarra = "";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Producto No encontrado con ese Codigo de Barra");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    
    //metodo para agregar los datos de los Productos por medio del
    // InputText
    public void agregarDatosProductoText() {
        if (cantidadProducto != null && cantidadProducto > 0) {
            //agregando detalle a la Lista de Detalle
            listDetalle.add(new Detallefactura(null, this.producto.getCodBarra(),
                    this.producto.getNombreProducto(), cantidadProducto, this.producto.getPrecioVenta(),
                    this.producto.getPrecioVenta().multiply(new BigDecimal(cantidadProducto))));

            cantidadProducto = null;
            //limpiamos la variable
            codigoBarra = "";
            //Invocamos al metodo que calcula el Total de la Venta para la factura
            totalFacturaVenta();

            //Actualizamos el componente ImputText
            RequestContext.getCurrentInstance().update("frmFactura:inputCodProducto");
            //Mensaje de confirmacion de exito de la operacion  
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Producto agregado correctamente!");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            cantidadProducto = null;
            //limpiamos la variable
            codigoBarra = "";
            //Actualizamos el componente ImputText
            RequestContext.getCurrentInstance().update("frmFactura:inputCodProducto");
        }

    }
   
    
    //Metodo para calcular el total vendido de la factura
    public void totalFacturaVenta(){
        BigDecimal totalVentaFactura = new BigDecimal(0);
        
        try{
          //Recorremos la lista de detalle y calculamos la Venta total de la factura
            for(Detallefactura det: listDetalle){
                //Sumamos a la variable 'totalVentaFactura'
               totalVentaFactura = totalVentaFactura.add(det.getTotal());
            }    
        }catch(Exception e){
            e.printStackTrace();
        }
        
        //Setemos al objeto Factura el valor de la variable 'totalFacturaVenta'
        factura.setTotalVenta(totalVentaFactura);
        
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

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public String getCodigoBarra() {
        return codigoBarra;
    }

    public void setCodigoBarra(String codigoBarra) {
        this.codigoBarra = codigoBarra;
    }

    public List<Detallefactura> getListDetalle() {
        return listDetalle;
    }

    public void setListDetalle(List<Detallefactura> listDetalle) {
        this.listDetalle = listDetalle;
    }

    public Integer getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(Integer cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public String getProductoSeleccionado() {
        return productoSeleccionado;
    }

    public void setProductoSeleccionado(String productoSeleccionado) {
        this.productoSeleccionado = productoSeleccionado;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Integer getCantidadProductoDlg() {
        return cantidadProductoDlg;
    }

    public void setCantidadProductoDlg(Integer cantidadProductoDlg) {
        this.cantidadProductoDlg = cantidadProductoDlg;
    }
         
}
