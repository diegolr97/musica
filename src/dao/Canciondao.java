/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import clases.Cancion;
import clases.Cantante;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public class Canciondao implements cancioninterfaz {

    @Override
    public void añadirCancion(String titulo, Cantante cantante, String duraccion) {
        
      ObjectContainer bd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),"musica.db4o");

        try{
            Cancion cancion = new Cancion(titulo, cantante, duraccion);
            bd.store(cancion);
            JOptionPane.showMessageDialog(null, "Cancion añadida");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "erro al añadir cancion");
            
        }
        finally{
            bd.close();
        
    }
        
        
        
        
        
    }

    @Override
    public void eliminarCancion(String titulo) {
       ObjectContainer bd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),"musica.db4o");
       
       try{
           ObjectSet res = bd.queryByExample(new Cancion(titulo, null, null));
           Cancion cancion = (Cancion) res.next();
           bd.delete(cancion);
           JOptionPane.showMessageDialog(null, "Cancion eliminada");
           
       }catch(Exception e){
           JOptionPane.showMessageDialog(null, "error al eliminar cancion");
           
       }
       finally{
           bd.close();
           
       }

        
        
    }

    @Override
    public void modificarCancion(String titulo, Cantante cantante, String duraccion) {
     ObjectContainer bd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),"musica.db4o");
     try{
         ObjectSet res = bd.queryByExample(new Cancion(titulo, null, null));
         Cancion cancion = (Cancion) res.next();
         cancion.setCantante(cantante);
         cancion.setDuracion(duraccion);
         bd.store(cancion);
         JOptionPane.showMessageDialog(null, "Cancion modificada");
         
     }catch(Exception e){
         JOptionPane.showMessageDialog(null, "Error al modfiicar cancion");
         
     }
     finally{
         bd.close();
         
     }

    }

    @Override
    public DefaultTableModel listarCanciones() {
        ObjectContainer bd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),"musica.db4o");

        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int colum) {
                return false;
            }
        };

        String[] columns = {"Título", "Cantante", "Duracción"};
        Query q  = bd.query();
        q.constrain(Cancion.class);
        ObjectSet res = q.execute();
        int i = 0;
        Object data[][] = new String[res.size()][2];
        while (res.hasNext()) {//recorres el iterador
            Cancion c = (Cancion) res.next();
//            data[i][0] = c();
//            data[i][1] = c.getEstilomusical();

            i++;
        }
        dtm.setDataVector(data, columns);
        bd.close();
        return dtm;
    }
    
}
