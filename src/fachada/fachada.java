/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachada;

import clases.Cantante;
import dao.Canciondao;
import dao.Cantantedao;
import dao.cancioninterfaz;
import dao.cantanteinterfaz;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author diego
 */
public class fachada{
    
    
    //-----------------Catante------------------//
    
    private cantanteinterfaz cantante = new Cantantedao();

    
   public void añadirCantante(String nombre, String estilomusical){
       cantante.añadirCantante(nombre, estilomusical);
       
   }
   
   public DefaultTableModel listarCantantes(){
       return cantante.listarCantantes();
       
   }
   public void eliminarCantante(String nombre){
       cantante.eliminarCantante(nombre);
   }
   public void modificarCantante(String nombre, String estilomusical){
       cantante.modificarCantante(nombre, estilomusical);
   }
   
   //-----------Canciones--------------------//
   
   private cancioninterfaz cancion = new Canciondao();
   
   public void añadirCancion(String titulo, String nombre, String duraccion){
       cancion.añadirCancion(titulo, nombre, duraccion);
       
   }
    public void eliminarCancion(String titulo){
        cancion.eliminarCancion(titulo);
        
    }
    public void modificarCancion(String titulo, Cantante cantante, String duraccion){
        cancion.modificarCancion(titulo, cantante, duraccion);
        
    }
    public DefaultTableModel listarCanciones(){
        return cancion.listarCanciones();
        
    }
    public DefaultComboBoxModel comboCantantes(){
        return cancion.comboCantantes();
    }
    
}
