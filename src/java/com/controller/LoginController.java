package com.controller;

import com.dao.UsuarioDao;
import com.impl.UsuarioDaoImp;
import com.model.Usuario;
import java.io.IOException;
import javax.inject.Named;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

@Named(value = "loginController")
@SessionScoped
public class LoginController  implements Serializable{
    //Variables
    
    private Usuario usuario;
    
    //Constructor
    public LoginController() {
        usuario = new Usuario();
    }
    
    
    //Metodo que comprueba la validez de las credenciales de usuario
    public void login() throws IOException {
        FacesMessage message = null;
        boolean loggedIn = false;
        UsuarioDao usuarioDao = new UsuarioDaoImp();
                
        //Recuperamos una instancia de Usuario en base a su password
        this.usuario = usuarioDao.loginUsuario(this.usuario);
        
        //Si el usuario es NO nulo esto indica que tanto el 'nombreUsuario' como el 'password'
        //son correctos
        if(this.usuario != null) {
            loggedIn = true;
            //Agregamos la instancia de usuario como atributo a la Session
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", this.usuario);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", usuario.getNombreUsuario());
            
        } else {
            loggedIn = false;
            this.usuario = new Usuario();
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Credenciales invalidas!!");
        }
        
       // FacesContext.getCurrentInstance().getExternalContext().redirect("/views/bienvenido.xhtml");
        FacesContext.getCurrentInstance().addMessage(null, message);
        RequestContext.getCurrentInstance().addCallbackParam("loggedIn", loggedIn);
        RequestContext.getCurrentInstance().addCallbackParam("ruta","/sysFacturacion/faces/views/bienvenido.xhtml");
    }   
    
    
    //Metodo para cerrar la Session
    public String closeSession(){
        //Recuperamos la Session y la invalidamos
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.invalidate(); //anula la session
        this.usuario = new Usuario();
        //Retornamos una cadena que indica la pagina a la que queremos direccionar
        return "/login";
    }
    
    //Getters y Setters

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
