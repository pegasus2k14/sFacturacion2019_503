package com.impl;

import com.dao.DetalleFacturaDao;
import com.model.Detallefactura;
import com.util.HibernateUtil;
import org.hibernate.Session;

public class DetalleFacturaDaoImp implements DetalleFacturaDao{
    //Metodo que guarda el detalle de factura
    @Override
    public boolean guardarDetalleFactura(Detallefactura detalleFactura) {
        //Variable de Tipo Session
        Session session = null;
        boolean resultado = false;
        try{
            //abrimos una 'Session' de hibernate y obtenemos una referencia a esta
            //mediante los metodos de la Clase 'HibernateUtil.java' creada previamente
            session = HibernateUtil.getSessionFactory().openSession();
            //iniciamos una transaccion
            session.beginTransaction();
            //guardamos el Detalle de Factura
            session.save(detalleFactura);
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
