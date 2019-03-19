package com.impl;

import com.clasesauxilaires.EncriptarPassword;
import com.dao.UsuarioDao;
import com.model.Usuario;
import com.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

public class UsuarioDaoImp implements UsuarioDao{

    @Override
    public Usuario obtenerDatosPorUsuario(Usuario usuario) {
        //Variable de tipo Session
        Session session = null;
        //Cadena con la consulta HQL
        String hql = "from Usuario us where us.nombreUsuario =:nombreUsuario";
        try{
            //Abrimos una Session a partir de la Clase HibernateUtil y pasamos
            //la referencia a la variable de tipo Session
            session = HibernateUtil.getSessionFactory().openSession();
            //Creamos la consulta
            Query q = session.createQuery(hql);
            //Seteamos parametros
            q.setParameter("nombreUsuario", usuario.getNombreUsuario());
            
            //retornamos el resultado
            return (Usuario) q.uniqueResult();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Usuario loginUsuario(Usuario usuario) {
        //invocamos al metodo que devuelve una instancia de usuario en base  a su
        //username
        Usuario user = obtenerDatosPorUsuario(usuario);
        //Si user es distinto de nulo, quiere decir que el usuario existe
        if(user != null){
            //comprobamos si el password del usuario obtenido en la instruccion
            //anterior NO es igual al password del usuario recibido como parametro
            if(!user.getPassword().equals(EncriptarPassword.encriptar(usuario.getPassword()))){
                user = null;
                        
            }
        }
        return user;
    }
    
}
