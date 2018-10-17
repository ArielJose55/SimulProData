/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simulprodata.controller;

import java.awt.event.ItemEvent;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JRadioButton;
import simulprodata.model.Control;
import simulprodata.model.util.Notifier;
import simulprodata.view.panels.ConstantPanelView;

/**
 *
 * @author Ariel Arnedo
 */
public class ViewRadioButtonListener implements java.awt.event.ItemListener,ConstantPanelView{
    
    private final Control control;

    public ViewRadioButtonListener(Control control) {
        this.control = control;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        
        if(e.getStateChange() == ItemEvent.SELECTED){
            JCheckBoxMenuItem item = (JCheckBoxMenuItem) e.getSource();
            switch(item.getActionCommand()){
                case "Personas con sus Categorias":{
                    control.changeViewPanel(PANEL_VIEW_PERSON_WITH_CATEGORY);
                    Notifier.getNotificador().notificar("UPDATE_VIEW_QUEUE");
                    break;
                }
                case "Personas con sus Tiempos":{
                    control.changeViewPanel(PANEL_VIEW_PERSON_WITH_TIME);
                    break;
                }
                case "Graficas de Variables Aleatorias":{
                    control.changeViewPanel(PANEL_VIEW_CAHRT);
                    break;
                }
            }
        } 
    }
}
