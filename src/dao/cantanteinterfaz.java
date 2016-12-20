/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author diego
 */
public interface cantanteinterfaz {
    
    public void añadirCantante(String nombre, String estilomusical);
    public void eliminarCantante(String nombre);
    public void modificarCantante(String nombre, String nombre2, String estilomusical);
    public DefaultTableModel listarCantantes();
    
    
}
