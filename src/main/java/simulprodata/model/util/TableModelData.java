/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simulprodata.model.util;

import java.util.List;
import java.util.Observable;
import simulprodata.model.logic.Persona;

/**
 *
 * @author Ariel Arnedo
 */
public class TableModelData extends javax.swing.table.AbstractTableModel implements java.util.Observer{

    private final List<Persona> personas;
    private final boolean withCategory;
    
    public TableModelData(List<Persona> personas) {
        this.personas = personas;
        this.withCategory = false;
        initNotifier();
    }

    public TableModelData(List<Persona> personas, boolean withCategory) {
        this.personas = personas;
        this.withCategory = withCategory;
        initNotifier();
    }
    
    @Override
    public int getRowCount() {
        return personas.isEmpty()? 0 : personas.size();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
    
    @Override
    public int getColumnCount() {
        return withCategory ? 4 : 8;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return getValue(personas.get(rowIndex), rowIndex, columnIndex);
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof String && String.valueOf(arg).compareTo("Update") == 0){
            fireTableDataChanged();
        }
    }
        
    private Object getValue(Persona persona,int row, int column){
        try{
            
            if(withCategory){
                switch(column){
                    case 0:{
                        return row + 1;
                    }
                    case 1:{
                        return persona.getSexo();
                    }
                    case 2:{
                        return persona.getTipo();
                    }
                    case 3:{
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < persona.getCategorias().size(); i++) {
                            sb.append(persona.getCategorias().get(i));
                            if(i != persona.getCategorias().size() - 1)
                                sb.append(", ");
                        }
//                        System.out.println(persona.getCategorias());
                        return sb.toString();
                    }
                    default:{
                        throw new RuntimeException("Columna No Implmentada");
                    }
                }
            }else{
                switch (column){
                    case 0:{
                        return row + 1;
                    }
                    case 1:{
                        return persona.getSexo();
                    }
                    case 2:{
                        return persona.getTipo();
                    }
                    case 3:{
                        return persona.getTiempos().getMomentoDeRecepcionEnCaja();
                    }
                    case 4:{
                        return persona.getTiempos().getMomentoDeRecepcionEnEspera();
                    }
                    case 5:{
                        return persona.getTiempos().getMomentoDeSalida();
                    }
                    case 6:{
                        return persona.getTiempos().getTiempoEnCaja();
                    }
                    case 7:{
                        return persona.getTiempos().getTiempoDeAtencion();
                    }
                    default:{
                        throw new RuntimeException("Columna No Implmentada");
                    }
                }
            }
       
        }catch(NullPointerException ex){
            return "";
        }catch(RuntimeException ex){
            return "";
        }

    }
    
    private void initNotifier(){
        Notifier.getNotificador().añadirObsevador(this);
    }
}
