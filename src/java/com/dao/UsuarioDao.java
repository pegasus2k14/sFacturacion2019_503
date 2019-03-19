package com.dao;

import com.model.Usuario;

public interface UsuarioDao {
    //Firmas de metodos
    //Metodo que devuelve una instancia de Usuario en base a su nombre de usuario
    public Usuario obtenerDatosPorUsuario(Usuario usuario);
    
    //metodo de logueo, devuelve una instancia de usuario si
    //su nombre de usuario y contraseña son correctos 
    public Usuario loginUsuario(Usuario usuario);
    
    
}
