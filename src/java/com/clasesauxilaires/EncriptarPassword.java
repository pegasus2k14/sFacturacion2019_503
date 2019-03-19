package com.clasesauxilaires;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncriptarPassword {
    
    //Metodo para encriptar
    public static String encriptar(String password){
        //Objeto StringBuilder (Cadena mutable)
        StringBuilder sb = new StringBuilder();
        
        try{
           //Creamos instancia de MessageDigest indicando su algoritmo de 
           //encriptacion
            MessageDigest md = MessageDigest.getInstance("SHA-512");
           //Pasamos a el objeto MessageDigest la cadena que contiene el Password
           md.update(password.getBytes());
           //Encriptamos
           //se Completa el cálculo de hash realizando operaciones finales como el relleno y
           //el resultado se deposita en un arreglo de bytes
           byte[] mb=md.digest();
           
           //Recorremos el arreglo de byte y concatenamos su contenido como cadena a
           //la variable StringBuilder
           for(int i =0; i < mb.length;i++){
               sb.append(Integer.toString((mb[i] & 0xff)+ 0x100,16).substring(1));
           }
        }catch(NoSuchAlgorithmException ex){
            ex.printStackTrace();
        }
        //retornamos el StringBuilder como una cadena
        return sb.toString();
    }
}
