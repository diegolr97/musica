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
import com.db4o.query.Constraint;
import com.db4o.query.Query;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public class Canciondao implements cancioninterfaz {

    @Override
    public void añadirCancion(String titulo, String nombre, String duraccion) {
        
      ObjectContainer bd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),"musica.db4o");

      try{
          ObjectSet res = bd.queryByExample(new Cantante(nombre, null));
            Cantante cantante = (Cantante) res.next();
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
    public void modificarCancion(String titulo, String titulo2, String nombre, String duraccion) {
          ObjectContainer bd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),"musica.db4o");
        try{
            ObjectSet res=bd.queryByExample(new Cancion(titulo, null, null));
            Cancion cancion = (Cancion)res.next();
            ObjectSet res2 = bd.queryByExample(new Cantante(nombre, null));
            Cantante cantante = (Cantante) res2.next();
            cancion.setTitulo(titulo2);
            cancion.setCantante(cantante);
            cancion.setDuracion(duraccion);
            bd.store(cancion);
            
            JOptionPane.showMessageDialog(null, "Cantante modificado");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al modificar cantante");
        }finally{
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
        Object data[][] = new String[res.size()][3];
        while (res.hasNext()) {//recorres el iterador
            Cancion c = (Cancion) res.next();
            data[i][0] = c.getTitulo();
            data[i][1] = c.getCantante().getNombre();
            data[i][2] = c.getDuracion();

            i++;
        }
        dtm.setDataVector(data, columns);
        bd.close();
        return dtm;
    }
    @Override
     public DefaultComboBoxModel comboCantantes() {
        ObjectContainer bd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),"musica.db4o");
        DefaultComboBoxModel cbm = new DefaultComboBoxModel();
        Query q = bd.query();
        q.constrain(Cantante.class);
        ObjectSet res = q.execute();

        while (res.hasNext()) {
            Cantante cantante = (Cantante) res.next();
            cbm.addElement(cantante.getNombre());
        }
        bd.close();
        return cbm;
    }

   @Override
    public DefaultTableModel listarCancionesporTitulo(String nombre) {
        ObjectContainer bd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),"musica.db4o");
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int colum) {
                return false;
            }
        };
        String[] columns = {"Título", "Cantante", "Duracción"};
        dtm.setColumnIdentifiers(columns);
        Query q  = bd.query();
        q.constrain(Cantante.class);
        q.descend("nombre").constrain(nombre);
        ObjectSet res = q.execute();
        Cantante can =(Cantante)res.next();
        q  = bd.query();
        q.constrain(Cancion.class);
        q.descend("cantante").constrain(can);
        ObjectSet resul = q.execute();
        while(resul.hasNext()) {
        Object[]data  = new Object[3];
        Cancion c =(Cancion)resul.next();
        data[0] = c.getTitulo();
        data[1] = c.getCantante().getNombre();
        data[2] = c.getDuracion();
        
        dtm.addRow(data);
         
        }
        bd.close();
        return dtm;
      
  }
    @Override
    public DefaultTableModel listarCancionesporDuraccion(String duracion, String duracion2) {
    ObjectContainer bd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "musica.db4o");
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int colum) {
                return false;
            }
        };
        String[] columns = {"Título", "Cantante", "Duracción"};
        dtm.setColumnIdentifiers(columns);
        Query q  = bd.query();
        q.constrain(Cancion.class);
        Constraint constr = q.descend("duracion").constrain(duracion).smaller();
        q.descend("duracion").constrain(duracion2).greater().and(constr);
        ObjectSet res = q.execute();
        Object data[][] = new String[res.size()][3];
        int i = 0;
        while(res.hasNext()) {
        Cancion c =(Cancion)res.next();
        data[i][0] = c.getTitulo();
        data[i][1] = c.getCantante().getNombre();
        data[i][2] = c.getDuracion();
        i++;
         
        }
        dtm.setDataVector(data, columns);
        bd.close();
        return dtm;
    }
}

