package com.clasesauxilaires;

import com.model.Usuario;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

public class FiltroURL implements PhaseListener{
    
    //Metodos para restringir el acceso a URLs si el usuario NO esta lgueado
    
    //Metodo que se ejecuta luego que finaliza el procesamiento de una fase en
    //particular del ciclo de vida de una Peticion de JSF.
    @Override
    public void afterPhase(PhaseEvent event){
        //Obtenermos una instancia de FacesContext a partir del Evento,
        //para poder acceder al contexto de la aplicacion
        FacesContext facesContext = event.getFacesContext();
        
        //Capturamos el nombre de la vista Actual  que se este navegando
        String currentPage = facesContext.getViewRoot().getViewId();
        
        System.out.println("La pagina actual es: "+ currentPage);
        
        //Determinamos si la vista actual es la vista de Login
        boolean isPageLogin = currentPage.lastIndexOf("login.xhtml") > -1?true:false;
        
        //Obtenemos una instancia de la Session actual
        HttpSession session = (HttpSession)facesContext.getExternalContext().getSession(true);
        
        //a partir de la session actual obtenemos el objeto Usuario que se definio en LoginController.java
        //y que se encuentra en Session
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        
        //Si NO nos encontramos en la pagina de Login y ademas la instancia
        //de usuario que esta en Session es Nula
        if(!isPageLogin && usuario == null){
            try {
                //Direccionamos a la pagina de Login
                FacesContext.getCurrentInstance().getExternalContext().redirect("./../../login.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(FiltroURL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    //Metodo que se ejecuta Antes de que inicie el procesamiento de una fase en
    //particular del ciclo de vida de una Peticion de JSF.
    @Override
    public void beforePhase(PhaseEvent event) {
        
    }

    //Metodo que retorna el Id de la Fase actual
    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
    
}
