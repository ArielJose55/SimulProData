/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simulprodata.model.util.chart;


import java.awt.Dimension;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import org.jfree.chart.ChartPanel;
import simulprodata.model.logic.Persona;

/**
 *
 * @author Ariel Arnedo
 */
public class FactoryChartPanel {
    
	public static JPanel createCharPanel(List<Persona> list,Integer interval, Parameter parameter){
        ChartPanel chartPanel = new ChartPanel(FactoryChart.createChart(list ,interval, parameter));
        return chartPanel;
    }
    
    public static JPanel createCharPanel(List<Persona> list, Parameter parameter){
        ChartPanel chartPanel = new ChartPanel(FactoryChart.createChart(list , parameter));
        return chartPanel;
    }
}
