/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simulprodata.model.util.chart;

import java.util.List;
import javax.swing.ImageIcon;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import simulprodata.model.logic.Persona;

/**
 *
 * @author Ariel Arnedo
 */
public class FactoryChart {
    
    public static JFreeChart createChart(List<Persona> list, Parameter parameter){
       switch (parameter){
           case TIMES_BETWEEN_ARRIVALS:{
               JFreeChart graficoBarras = ChartFactory.createBarChart3D(
                 "Frecuencia de Tiempos entre LLegadas",        //Título de la gráfica 
                 "Tiempos entre LLegadas",           //leyenda Eje horizontal 
                 "Frecuencia",      //leyenda Eje vertical 
                 FactoryData.createDataset(list, parameter),                   //datos 
                 PlotOrientation.VERTICAL,  //orientación 
                 false,                      //incluir leyendas 
                 true,                      //mostrar tooltips 
                 true);
                CategoryPlot plot = (CategoryPlot) graficoBarras.getPlot();
                CategoryAxis xAxis =  plot.getDomainAxis();
                xAxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_90); // Inclinamos 45 grados las etiquetas del eje X
                plot.setBackgroundAlpha(0.9f);

                return graficoBarras;
           }
           default:{
               throw new RuntimeException("Parameter Invalid");
           }
       }
    }
    
    public static JFreeChart createChart(List<Persona> list,int intervals, Parameter parameter){
       switch (parameter){

           case TIMES_AVERAGE_BETWEEN_ARRIVALS:{
               JFreeChart graficoBarras = ChartFactory.createBarChart3D(
                 "Promedio de tiempos entre llegadas por intervalos",        //Título de la gráfica 
                 "Intervalos",           //leyenda Eje horizontal 
                 "Promedio entre tiempos de llegadas (Seg)",      //leyenda Eje vertical 
                 FactoryData.createDataset(list,intervals, parameter),                   //datos 
                 PlotOrientation.VERTICAL,  //orientación 
                 false,                      //incluir leyendas 
                 true,                      //mostrar tooltips 
                 true);
                CategoryPlot plot = (CategoryPlot) graficoBarras.getPlot();
                CategoryAxis xAxis =  plot.getDomainAxis();
                xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45); // Inclinamos 45 grados las etiquetas del eje X
                plot.setBackgroundAlpha(0.9f);

                return graficoBarras;
           }
           default:{
               throw new RuntimeException("Parameter Invalid");
           }
       }
    }
}
