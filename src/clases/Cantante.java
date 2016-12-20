/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;


/**
 *
 * @author diego
 */
public class Cantante  {
    
    String nombre;
    String estilomusical;
    
    
    public Cantante(String nombre, String estilomusical){
        this.nombre=nombre;
        this.estilomusical=estilomusical;
        
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstilomusical() {
        return estilomusical;
    }

    public void setEstilomusical(String estilomusical) {
        this.estilomusical = estilomusical;
    }
    
  
    
}
