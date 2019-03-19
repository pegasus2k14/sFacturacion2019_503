package com.clasesauxilaires;

import com.util.HibernateUtil;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import org.hibernate.engine.spi.SessionImplementor;

public class ReporteFactura {
    //Variables
    private static JasperReport report;   //Variable de tipo reporte
    private static JasperPrint reportFilled; //almacenara el reporte ya lleno
    private static Connection conexion = null;    //Variable de tipo conexion java.sql
     
    
    //Metodo para crear el reporte
     //Recibe como parametros la ruta tonde esta ubicado el archivo del reporte,
     //y los parametros que se pasaran al reporte
    public static void createReport(String ruta, Integer codCliente,Integer codVendedor,Integer codFactura){
       try{ 
          //Obtenemos una SessionImplementor
          SessionImplementor sessionImpl =(SessionImplementor) HibernateUtil.getSessionFactory().openSession();
          //a partir del SessionImplementor obtenemos una conexion
          conexion = sessionImpl.connection();
          
          //Definimos una coleccion Map  con los parametros que el reporte necesita
          //Los nombres de las Claves deben corresponder con los nombres de parametros
          //que se definieron en el diseño del reporte
          Map mapParameters = new HashMap();
          //Agregamos los parametros a la coleccion
          mapParameters.put("codCliente", codCliente);
          mapParameters.put("codVendedor", codVendedor);
          mapParameters.put("codFactura", codFactura);
          
          
          //obtenemos el archivo del reporte desde el sistema de archivos
          report = (JasperReport) JRLoader.loadObjectFromFile(ruta);
          
          //llenamos el reporte, pasamos como parametros
          //El 1-archivo del reporte, 2-La coleccion Map con los parametros
          //3-la conexion 
          reportFilled = JasperFillManager.fillReport(report, mapParameters, conexion);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
           //Cerramos la conexion
           if(conexion != null){
               try {
                   conexion.close();
               } catch (SQLException ex) {
                   Logger.getLogger(ReporteFactura.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
       }
    } 
    
    //Metodo para esporter el reporte a PDF y visualizarlo en el navegador
    public static void exportReportToPdfWeb(){
        try{
            //obtenemos un objeto Response
            HttpServletResponse httpServletResponse = 
                   (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            
            //indicamos el tipo de contenido del response
            httpServletResponse.setContentType("application/pdf");
            
            //exportamos el reporte a formato PDF dentro de un flujo de salida
            JasperExportManager.exportReportToPdfStream(reportFilled, httpServletResponse.getOutputStream());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
