/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simulprodata.view.panels;

import simulprodata.model.Control;
import simulprodata.model.util.FactoryColumnModel;
import simulprodata.model.util.TableModelData;

/**
 *
 * @author Ariel Arnedo
 */
public class PanelViewWithCategoria extends javax.swing.JPanel {

    private final Control CONTROL;
    private final TableModelData TABLEMODEL;
    
    public PanelViewWithCategoria(Control control) {
        this.CONTROL = control;
        TABLEMODEL = new TableModelData(CONTROL.getPersonasProcesadas(),true);
        initComponents();
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tableWithCategory = new javax.swing.JTable();

        tableWithCategory.setModel(TABLEMODEL);
        tableWithCategory.setColumnModel(FactoryColumnModel.createModelColumnTableMain(0));
        jScrollPane1.setViewportView(tableWithCategory);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 740, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableWithCategory;
    // End of variables declaration//GEN-END:variables
}
