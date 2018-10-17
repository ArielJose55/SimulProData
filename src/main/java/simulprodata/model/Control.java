/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simulprodata.model;

import java.util.List;
import java.util.Queue;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import org.jfree.chart.ChartPanel;
import simulprodata.model.logic.Persona;

/**
 *
 * @author Ariel Arnedo
 */
public class Control {
    
    private final java.util.List<Persona> personasProcesadas;
    private final java.util.Queue<Persona> colaDeCaja;
    private final java.util.Queue<Persona> colaDeAtencion;
    private javax.swing.JPanel panelWork;
    private Integer panelView;
    private final java.util.Map<Integer,javax.swing.JPanel> PANELS_VIEWS;

    public Control() {
        this.personasProcesadas = new java.util.ArrayList<>();
        this.colaDeAtencion = new java.util.LinkedList<>();
        this.colaDeCaja = new java.util.LinkedList<>();
        this.PANELS_VIEWS = new java.util.HashMap<>();
    }

    public List<Persona> getPersonasProcesadas() {
        return personasProcesadas;
    }
    
    public Queue<Persona> getColaDeCaja() {
        return colaDeCaja;
    }

    public Queue<Persona> getColaDeAtencion() {
        return colaDeAtencion;
    }
    
    public void addPanelView(Integer index,javax.swing.JPanel panelView){
        PANELS_VIEWS.put(index, panelView);
    }
    
    public void setPanelWork(javax.swing.JPanel panelWork){
        this.panelWork = panelWork;
    }
    
    public boolean incluirCategoria(List<List<String>> list){

        if(personasProcesadas.isEmpty())
            throw new RuntimeException("Aun No hay Registros de Personas");
        
        if(list.size() >= personasProcesadas.size()){
            for (int i = 0; i < personasProcesadas.size(); i++) {
                personasProcesadas.get(i).setCategorias(list.get(i));
            } 
        }else{
            for (int i = 0; i < list.size(); i++) {
                personasProcesadas.get(i).setCategorias(list.get(i));
            }
        }
        return list.size() == personasProcesadas.size();
    }
    
    public void changeViewPanel(Integer indexPanelView){
        if(panelView != null && panelView == indexPanelView){
            return;
        }
        panelView = indexPanelView;
        panelWork.removeAll();
        panelWork.add(PANELS_VIEWS.get(indexPanelView));
        panelWork.updateUI();
    }
    
    public void changeViewPanel(JScrollPane panel){
        panelView = -1;
        panelWork.removeAll();       
        panelWork.add(panel);
        panelWork.updateUI();
    }
    
    public void changeViewPanel(JPanel panel){
        panelView = -1;
        panelWork.removeAll();       
        panelWork.add(panel);
        panelWork.updateUI();
    }
}
