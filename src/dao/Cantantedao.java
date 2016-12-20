/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import clases.Cantante;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author diego
 */
public class Cantantedao implements cantanteinterfaz{
    
     

@Override
    public void añadirCantante(String nombre, String estilomusical) {
        
        ObjectContainer bd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),"musica.db4o");
        
        try{
            
            Cantante cantante = new Cantante(nombre, estilomusical);
            bd.store(cantante);
            
            JOptionPane.showMessageDialog(null,"Cantante añadido");
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "error al añadir cliente");
            
        }
        finally{
            bd.close();
        }
    }
    

@Override
    public DefaultTableModel listarCantantes() {
       ObjectContainer bd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),"musica.db4o");

        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int colum) {
                return false;
            }
        };

        String[] columns = {"Nombre", "Estilo"};
        Query q  = bd.query();
        q.constrain(Cantante.class);
        ObjectSet res = q.execute();
        int i = 0;
        Object data[][] = new String[res.size()][2];
        while (res.hasNext()) {//recorres el iterador
            Cantante c = (Cantante) res.next();
            data[i][0] = c.getNombre();
            data[i][1] = c.getEstilomusical();

            i++;
        }
        dtm.setDataVector(data, columns);
        bd.close();
        return dtm;
    }
    
    @Override
     public void eliminarCantante(String nombre) {
        
        ObjectContainer bd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),"musica.db4o");
        
        
        try{
            ObjectSet res = bd.queryByExample(new Cantante(nombre, null));
            Cantante cantante = (Cantante) res.next();
            bd.delete(cantante);
            
            JOptionPane.showMessageDialog(null,"Cantante eliminado");
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "error al eliminar Cantante");
            
        }
        finally{
            bd.close();
        }
    }

    @Override
    public void modificarCantante(String nombre, String estilomusical){
        ObjectContainer bd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),"musica.db4o");
        try{
            ObjectSet res=bd.queryByExample(new Cantante(nombre, null));
            Cantante cantante = (Cantante)res.next();
            cantante.setEstilomusical(estilomusical);
            bd.store(cantante);
            
            JOptionPane.showMessageDialog(null, "Cantante modificado");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al modificar cantante");
        }finally{
            bd.close();
        }
    }
     
     

    
    
    
    
}
