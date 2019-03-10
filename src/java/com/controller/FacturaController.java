package com.controller;

import com.dao.ClienteDao;
import com.dao.DetalleFacturaDao;
import com.dao.FacturaDao;
import com.dao.ProductoDao;
import com.impl.ClienteDaoImp;
import com.impl.DetalleFacturaDaoImp;
import com.impl.FacturaDaoImp;
import com.impl.ProductoDaoImp;
import com.model.Cliente;
import com.model.Detallefactura;
import com.model.Factura;
import com.model.Producto;
import com.model.Vendedor;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;


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
    
    private Long numeroFactura;
    
    private Vendedor vendedor;
    
    private boolean enabled;
    
    private String fechaSistema;

    //Constructor
    public FacturaController() {
        this.factura = new Factura();
        listDetalle = new ArrayList<>(); //inicializamos la lista
        codigoBarra="";
        this.vendedor = new Vendedor();
        this.cliente = new Cliente();
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
                
                //Seteamos el producto al item del detalle
                  //Accedemos al ultimo Item agregado a la lista
                  listDetalle.get(listDetalle.size()-1).setCodProducto(producto);
                  
                //Seteamos la factura al item del detalle
                  //Accedemos al ultimo Item agregado a la lista  
                 // listDetalle.get(listDetalle.size()-1).setCodFactura(factura);
                  
                //Limpiamos la variable 'cantidadProducto'
                cantidadProductoDlg = null;
                //Invocamos al metodo que calcula el Total de la Venta para la factura
                totalFacturaVenta();

                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Producto agregado al detalle!");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }else{
                //Limpiamos la variable 'cantidadProducto'
                cantidadProductoDlg = null;
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR , "Incorrecto", "La cantidad es incorrecta!");
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
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", 
                        "Producto No encontrado con ese Codigo de Barra");
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
            
            //Seteamos el producto al item del detalle
                  //Accedemos al ultimo Item agregado a la lista
                  listDetalle.get(listDetalle.size()-1).setCodProducto(producto);
                  
                //Seteamos la factura al item del detalle
                  //Accedemos al ultimo Item agregado a la lista  
                 // listDetalle.get(listDetalle.size()-1).setCodFactura(factura);
            
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
    
    
    //Metodo para retirar un producto del detalle de la factura
    public void retirarProductoDetalleFactura(String codBarra,Integer rowIndex){
        //Recorremos la Lista de detalle
        int i =0;
        for(Detallefactura det : listDetalle){
            if(det.getCodBarra().equals(codBarra) && rowIndex.equals(i)){
               listDetalle.remove(i);
               break; //para que se salga inmediatamente del bucle
            }
            i++;
        }
        //Invocamos al metodo que calcula el Total de la Venta para la factura
        totalFacturaVenta();
        
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", 
                                                 "Producto retirado del Detalle de la factura");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    //Metodo que responde al evento de modificacion de una fila en el <p:dataTable/>
    public void rowEdit(RowEditEvent event){
        //obtenemos la instancia de la Fila modificada 
         Detallefactura detalle = (Detallefactura)event.getObject();
        
        //obtenemos el indice de la fila modificada
        DataTable table = (DataTable) event.getSource();
        Integer indice = table.getRowIndex();
        
        //Calculamos el nuevo total del producto
        BigDecimal nuevoTotal = detalle.getPrecioVenta().multiply(new BigDecimal(detalle.getCantidad()));
        //seteamos al detalle el nuevo total
        //detalle.setTotal(nuevoTotal);
        
        //Recorremos la lista de Detalle y actualizamos el item correspondiente
        int i =0;
        for(Detallefactura det : listDetalle){
            if(det.getCodBarra().equals(detalle.getCodBarra()) && indice.equals(i)){
                //listDetalle.set(i, detalle);
                listDetalle.get(i).setTotal(nuevoTotal);
                break; //para que se salfa del for de manera inmediata
            }
            i++;
        }
        
        totalFacturaVenta();
        System.out.println("TOTAL: "+ factura.getTotalVenta());
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto"
                                                  , "Producto: Cantidad de producto Modificado");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void cancelRowEdit(RowEditEvent event){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "No Modificado"
                                                  , "Producto: Cantidad de producto No Modificado");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    
    //Metodo que permite generar el numero de Factura
    public void generarNumeroFacura(){
        try{
            FacturaDao facturaDao = new FacturaDaoImp();
            //Comprobamos si hay registros en la tabla Factura de la BD
            this.numeroFactura = facturaDao.numeroRegistrosFactura();
            
            //Si no hay registros hacemos el numero de factura igual a 1
            if(numeroFactura <= 0 || numeroFactura == null){
                numeroFactura = Long.valueOf("1");
                this.factura.setNumeroFactura(this.numeroFactura.intValue());
                this.factura.setTotalVenta(new BigDecimal(0));
            }else{
                //Recuperamos el ultimo registro que existe en la tabla Factura
                Factura factura = facturaDao.getMaxNumeroFactura();
                //Le pasamos a la variable local el numero de factura incrementado en 1
                this.numeroFactura = Long.valueOf(factura.getNumeroFactura() + 1);
                this.factura.setNumeroFactura(this.numeroFactura.intValue());
                this.factura.setTotalVenta(new BigDecimal(0));
            }
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    //Metodo para cancelar (limpiar factura) una venta
    public void cancelarFactura(){
        this.cliente = new Cliente();
        this.factura = new Factura();
        this.listDetalle = new ArrayList<>();
        this.numeroFactura = null;
    }
    
    //Metodo para guardar la Venta
    public void guardarVenta(){
        //objeto vendedor estatico (manual)
        this.vendedor.setCodVendedor(4);
        try{
            FacturaDao facturaDao = new FacturaDaoImp();
            DetalleFacturaDao detfacturaDao = new DetalleFacturaDaoImp();
            //Seteando datos a la factura
            factura.setCodVendedor(vendedor);
            factura.setCodCliente(cliente);
            factura.setFechaRegistro(new Date());
            
            //Seteamos a cada Item del Detalle la factura
            for(int i=0;i<listDetalle.size();i++){
                listDetalle.get(i).setCodFactura(factura);
            }
            
            //Seteamos a la Factura la Lista de Detalel
            factura.setDetallefacturaList(listDetalle);
            
            //Guardamos la factura y su detalle
            facturaDao.guardarFactura(factura);
            cancelarFactura(); //para limpiar las variables
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto: ", "Factura Registrada");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }catch(Exception e){
            e.printStackTrace();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: ", "Factura no pudo ser Registrada");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    //Metodos para Activar/Desactivar controles en la factura
    
     public boolean isEnabled() {
        return enabled;
    }
     
    public void enableButton(){
        enabled = true;
    } 
    
    public void disableButton(){
        enabled = false;
    }
    
    //Metodo Get para la variable fechaSistema

    public String getFechaSistema() {
        Date date = new Date();
        DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        //Aplicamos el formato al objeto Date y el resultado se
        //lo pasamos a la variable String
        fechaSistema = formato.format(date);
        
        return fechaSistema;
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

    public Long getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(Long numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }
         
}
