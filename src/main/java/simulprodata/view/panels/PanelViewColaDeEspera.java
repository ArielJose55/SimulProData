/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simulprodata.view.panels;

import java.awt.Color;
import java.util.Observable;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import simulprodata.model.Control;
import simulprodata.model.util.Notifier;

/**
 *
 * @author Ariel Arnedo
 */
public class PanelViewColaDeEspera extends javax.swing.JPanel implements java.util.Observer{
    
    private final Control control;
    

    public PanelViewColaDeEspera(Control control) {
        this.control = control;
        initComponents();
    }
    
    private void initComponents(){
        Notifier.getNotificador().añadirObsevador(this);
        this.setBackground(Color.WHITE);
        this.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(200, 200, 255)));
        this.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT,0,0)); 
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof String && String.valueOf(arg).compareTo("UPDATE_VIEW_QUEUE") == 0){
            if(this.getComponents().length != 0)
                this.removeAll();
            control.getColaDeAtencion().stream().forEach((persona) -> {
                JLabel icon;
                if(persona.getSexo().compareTo("Hombre") == 0){
                    icon = new JLabel(new ImageIcon(getClass().getResource("/simulprodata/view/resources/man40x40.png")));
                }else{
                    icon = new JLabel(new ImageIcon(getClass().getResource("/simulprodata/view/resources/woman40x40.png")));
                }
                this.add(icon);
            });
            this.updateUI();
        }
    }
}
