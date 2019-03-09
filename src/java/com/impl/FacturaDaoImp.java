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
    public Long numeroRegistrosFactura() {
        //Creamos la cadena con la consulta
        String hql = "select count(f) from Factura f";
        Session session = null;
        Long cantidad =null;
        try{
           //abrimos una 'Session' de hibernate y obtenemos una referencia a esta
          //mediante los metodos de la Clase 'HibernateUtil.java' creada previamente
          session = HibernateUtil.getSessionFactory().openSession();
          //creamos la consulta
          Query q = session.createQuery(hql);
          //Ejecutamos la consulta
          cantidad = (Long) q.uniqueResult();
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

    //Metodo para guardar la factura
    @Override
    public boolean guardarFactura(Factura factura) {
          //Variable de tipo Session
          Session session = null;
          boolean resultado = false;
        try{
             //abrimos una 'Session' de hibernate y obtenemos una referencia a esta
             //mediante los metodos de la Clase 'HibernateUtil.java' creada previamente
             session = HibernateUtil.getSessionFactory().openSession();
             //iniciamos una transaccion
             session.beginTransaction();
             //Persistimos la instancia de factura
             session.save(factura);
             //confirmamos la transaccion
             session.getTransaction().commit();
             resultado = true;
        }catch(Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }finally{
            if(session != null){
                session.close();
            }
        }
        
       return resultado;
        
    }
    
}
