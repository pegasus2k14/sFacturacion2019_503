package com.impl;

import com.dao.FacturaDao;
import com.model.Factura;
import com.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

public class FacturaDaoImp  implements FacturaDao{

    @Override
    public Factura getMaxNumeroFactura() {
      //Creamos cadena con la consulta
      String hql ="from Factura f order by f.codFactura DESC";
      //Variable de Tipo Session
      Session session = null;
      
      //Variable de tipo Factura
      Factura factura = null;
      
      try{
          //abrimos una 'Session' de hibernate y obtenemos una referencia a esta
          //mediante los metodos de la Clase 'HibernateUtil.java' creada previamente
          session = HibernateUtil.getSessionFactory().openSession();
          //Creamos la consulta
          Query q = session.createQuery(hql).setMaxResults(1);
          //Ejecutamos la consulta
          factura =(Factura) q.uniqueResult();
      }catch(Exception e){
          e.printStackTrace();
      }finally{
          if(session != null){
              session.close();
          }
      }
      return factura;
    }

    @Override
    public long numeroRegistrosFactura() {
        //Creamos la cadena con la consulta
        String hql = "select count(f) from Factura f";
        Session session = null;
        long cantidad =0;
        try{
           //abrimos una 'Session' de hibernate y obtenemos una referencia a esta
          //mediante los metodos de la Clase 'HibernateUtil.java' creada previamente
          session = HibernateUtil.getSessionFactory().openSession();
          //creamos la consulta
          Query q = session.createQuery(hql);
          //Ejecutamos la consulta
          cantidad = (long) q.uniqueResult();
          return cantidad;        
        }catch(Exception e){
            e.printStackTrace();;
        }finally{
            if(session != null){
              session.close();
            } 
        }
        return cantidad;
                
    }
    
}
