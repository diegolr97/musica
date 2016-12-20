/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import clases.Cantante;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public interface cancioninterfaz {
    public void añadirCancion(String titulo, String nombre, String duraccion);
    public void eliminarCancion(String titulo);
    public void modificarCancion(String titulo, Cantante cantante, String duraccion);
    public DefaultTableModel listarCanciones();
    public DefaultComboBoxModel comboCantantes();
    
}
