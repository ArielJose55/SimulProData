package simulprodata.model.util;

import org.omg.CORBA.INTERNAL;

/**
 *
 * @author Klac
 */
public class FactoryColumnModel {
    
    public static javax.swing.table.TableColumnModel createModelColumnTableMain(){
        
        javax.swing.table.DefaultTableColumnModel tablaColumn = new  javax.swing.table.DefaultTableColumnModel();
        
        tablaColumn.setColumnMargin(0);
        
        int ancho[] ={20,60,60,140,120,120,120,120,120};
        
        String cabeceras[] = {"N°","Sexo","Tipo","M en Caja","M en Espera","M de Salida","T en Caja","T de Atencion"}; 
        
        for(int i = 0; i < cabeceras.length ; i++){
            
                javax.swing.table.TableColumn columna = new javax.swing.table.TableColumn(i,ancho[i]);
                
                columna.setHeaderValue(cabeceras[i]);
                
                javax.swing.table.DefaultTableCellRenderer rowRenderer = FactoryCellTableRenderer.createTableCellRenderer(-1);
                
                columna.setHeaderRenderer(rowRenderer);
                
                rowRenderer = FactoryCellTableRenderer.createTableCellRenderer(i);
                
                columna.setCellRenderer(rowRenderer);
                
                tablaColumn.addColumn(columna);
        }
        
        return tablaColumn;
    }
    

    public static javax.swing.table.TableColumnModel createModelColumnTableMain(int type){
        
        if(type == 0){
            int ancho[] = {20,60,60,300};
            String cabeceras[] = {"N°","Sexo","Tipo","Categorias"};
            return createTableColumn(ancho, cabeceras);
        }else{
            int ancho[] = {20,60,60,140,120,120,120,120,120};
            String cabeceras[] = {"N°","Sexo","Tipo","M en Caja","M en Espera","M de Salida","T en Caja","T de Atencion"};
            return createTableColumn(ancho, cabeceras);
        }     
    }
    
    private static javax.swing.table.TableColumnModel createTableColumn(int ancho[], String cabeceras[]){
        javax.swing.table.DefaultTableColumnModel tablaColumn = new  javax.swing.table.DefaultTableColumnModel();
        
        tablaColumn.setColumnMargin(0);
        for(int i = 0; i < cabeceras.length ; i++){
            
                javax.swing.table.TableColumn columna = new javax.swing.table.TableColumn(i,ancho[i]);
                
                columna.setHeaderValue(cabeceras[i]);
                
                javax.swing.table.DefaultTableCellRenderer rowRenderer = FactoryCellTableRenderer.createTableCellRenderer(-1);
                
                columna.setHeaderRenderer(rowRenderer);
                
                rowRenderer = FactoryCellTableRenderer.createTableCellRenderer(i);
                
                columna.setCellRenderer(rowRenderer);
                
                tablaColumn.addColumn(columna);
        }
        return tablaColumn;
    }
}