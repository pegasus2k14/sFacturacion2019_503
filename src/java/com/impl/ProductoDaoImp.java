package com.impl;

import com.dao.ProductoDao;
import com.model.Producto;
import com.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;

public class ProductoDaoImp implements ProductoDao{

    @Override
    public List<Producto> listarProductos() {
         //Lista de Productos
        List<Producto> listProducto = null;
        //Variable de Tipo Session
        Session session = null;
        //abrimos una 'Session' de hibernate y obtenemos una referencia a esta
        //mediante los metodos de la Clase 'HibernateUtil.java' creada previamente
          session = HibernateUtil.getSessionFactory().openSession();
        //Cadena con consulta HQL
        String hql = "FROM Producto p ORDER BY p.codProducto DESC";
        try{
            //Ejecutamos la consulta
            listProducto = session.createQuery(hql).list();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(session != null){
                 session.close(); //cerramos la session
            }
           
        }
        //retornamos la lista de instancias de Productos
        return listProducto;
    }

    @Override
    public void insertProducto(Producto producto) {
         //Variable de tipo Session de Hibernate
        Session session = null;
        try{
            //Ontenemos una referencia a una Session de Hibernate
            session = HibernateUtil.getSessionFactory().openSession();
            //iniciamos una transaccion
            session.beginTransaction();
            //Ejecutamos la inserccion de la instancia de Cliente
            session.save(producto);
            //Confirmamos la transaccion
            session.getTransaction().commit();
            
        }catch(Exception e){
            //hacemos un Rolback
            session.getTransaction().rollback();
            e.printStackTrace();
        }finally{
            if(session != null){
                session.close(); //cerramos la session
            }
        }
    }

    @Override
    public void updateProducto(Producto producto) {
         //variable de tipo Session de Hibernate
        Session session = null;
        try {
            //Abrimos y obtenemos una referencia a una Session de Hibernate
            session = HibernateUtil.getSessionFactory().openSession();
            //Iniciamos una transaccion
            session.beginTransaction();
            //Ejecutamos la operacion de modificado
            session.update(producto);
            //Confirmamos la transaccion
            session.getTransaction().commit();
        } catch (Exception e) {
            //Hacemos Rollback
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            if (session != null) {
                //Cerramos la Session
                session.close();

            }
        }
    }

    @Override
    public void deleteProducto(Producto producto) {
         //Variable de tipo Session de Hibernate
        Session session = null;
        try{
            //Abrimos una Session de Hibernate y obtenemos una referencia a esta
            session = HibernateUtil.getSessionFactory().openSession();
            //Abrimos una transaccion
            session.beginTransaction();
            //Realizamos la operacion de eliminacion
            session.delete(producto);
            //Confirmamos la transaccion
            session.getTransaction().commit();
        }catch(Exception e){
            //Hacemos Rollback
            session.getTransaction().rollback();
            e.printStackTrace();
        }finally{
            if(session != null){
                session.close();
            }
        }
    }
    
}
