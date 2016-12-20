/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachada;

import dao.Cantantedao;
import dao.cantanteinterfaz;
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
    
}
