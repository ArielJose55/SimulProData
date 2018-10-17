/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simulprodata.model.util.chart;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.jfree.data.category.DefaultCategoryDataset;
import simulprodata.model.logic.Persona;

/**
 *
 * @author Ariel Arnedo
 */
public class FactoryData {
    
    
    
    public static DefaultCategoryDataset createDataset(List<Persona> list,int intervals, Parameter parameter){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset(); 
        switch(parameter){
            
            case TIMES_AVERAGE_BETWEEN_ARRIVALS:{
                int i = 1;
                java.util.List<Interval> map = FactoryData.getIntervals(list, intervals);
                for (Interval interval : map) {
                    Number value =  interval.getPromedioDeLlegadas();
                    dataset.setValue(value, (i++), interval.toString());
                }
                return dataset;
            }
            default:
                throw new RuntimeException("Parametro No valido");
        }
    }
    
     public static DefaultCategoryDataset createDataset(List<Persona> list, Parameter parameter){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset(); 
        switch(parameter){
            case TIMES_BETWEEN_ARRIVALS:{
                int i = 1;
                java.util.Map<Integer,Integer> map = FactoryData.getListTheTimeData(list, parameter);
                for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                    Number value =  entry.getValue();
                    dataset.setValue(value, (i++), entry.getKey());
                }
                return dataset;
            }
            default:
                throw new RuntimeException("Parametro No valido");
        }
    }
    
    private static Map<Integer, Integer> getListTheTimeData(List<Persona> listPerson, Parameter parameter){
        Map<Integer, Integer> list = new LinkedHashMap<>();
        List<Integer> listArrivals = getListTheTime(listPerson, parameter);
        
        for (int i = 0 ; i < listArrivals.size() ; i++) {
            int acum = 0;
            for (int j = 1 ; j < listPerson.size() ; j++) {
                if(listArrivals.get(i) == listPerson.get(j).getTiempos().getMomentoDeRecepcionEnCaja().getDiferenciaEnMinutos(
                                            listPerson.get(j-1).getTiempos().getMomentoDeRecepcionEnCaja())){
                    acum++;
                }
            }
            list.put(listArrivals.get(i), acum);
        }
        return list;
    }
    
    public static List<Integer> getListTheTime(List<Persona> listPerson, Parameter parameter){
        List<Integer> list = new ArrayList<>();
        
        switch(parameter){
            case TIMES_BETWEEN_ARRIVALS:{
                
                for (int i = 0; i < listPerson.size() ; i++) {
                    if(i != 0){
                        int time = listPerson.get(i).getTiempos().getMomentoDeRecepcionEnCaja().getDiferenciaEnMinutos(
                                            listPerson.get(i-1).getTiempos().getMomentoDeRecepcionEnCaja()
                                    );
                        
                        boolean noAparece = true;
                        
                        for (int j = 0 ; j < list.size() ; j++) {
                            if(list.get(j) == time)
                                noAparece = false;
                        }
                        if(noAparece)
                            list.add(time);
                    }
                }
                list.sort(null);
                return list;
            }
            default:{
                throw new RuntimeException("Opcion no valida para los tiempo");
            }
        }
    }
    
    public static List<Interval> getIntervals(List<Persona> listPerson,int intervalos){
        

        Integer timeObservation = 4 * 3600;
        
        List<Interval> listIntervals = new ArrayList<>();
        
        int inferior,superior;
        inferior = 0;
        
        for(int i = 0 ; i < intervalos ; i++){
            Interval interval = new Interval();
            interval.setInferior(inferior);
            inferior = superior = (timeObservation/intervalos + inferior);
            interval.setSuperior(superior);
            listIntervals.add(interval);
        }
        Persona primera = listPerson.get(0);
        
        for (int i = 1; i < listPerson.size(); i++) { 
            for (Interval interval : listIntervals) {
                if(interval.isContentInRange(listPerson.get(i).getTiempos()
                        .getMomentoDeRecepcionEnCaja()
                            .getDiferenciaEnMinutos(primera.getTiempos()
                                .getMomentoDeRecepcionEnCaja()))){
                    int time = listPerson.get(i).getTiempos().getMomentoDeRecepcionEnCaja().getDiferenciaEnMinutos(
                                            listPerson.get(i-1).getTiempos().getMomentoDeRecepcionEnCaja());
                    interval.setLlegadas(time);
                    interval.setNumeroDeLlegadas(1);
                }
            }
        }
//        for (Interval interval : listIntervals) {
//            System.out.println(interval+"\t"+interval.getLlegadas()+"\t"+interval.getNumeroDeLlegadas()+"\t"+interval.getPromedioDeLlegadas());
//        }
        return listIntervals;
    }
}
