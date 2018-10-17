/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simulprodata.model.logic;

import java.util.GregorianCalendar;

/**
 *
 * @author Ariel Arnedo
 */
public class TimeSimul {
    
    private final int hora, min, seg;
    private final String zonaHoraria;
    private final GregorianCalendar calendar;
    
    public TimeSimul(GregorianCalendar calendar) {
        this.calendar = calendar;
        hora = calendar.get(GregorianCalendar.HOUR);
        min = calendar.get(GregorianCalendar.MINUTE);
        seg = calendar.get(GregorianCalendar.SECOND);
        zonaHoraria = (calendar.get(GregorianCalendar.AM_PM) == GregorianCalendar.AM? "AM":"PM");
    }

    public int getHora() {
        return hora;
    }

    public int getMin() {
        return min;
    }

    public int getSeg() {
        return seg;
    }
    
    public String getZonaHoraria() {
        return zonaHoraria;
    }

    public GregorianCalendar getCalendar() {
        return calendar;
    }
    
    
    public int getDiferenciaEnMinutos(TimeSimul timeSimul){
        if(zonaHoraria.compareTo(timeSimul.getZonaHoraria()) != 0)
            throw new RuntimeException("Para hallar las diferencias de tiempo las zonas horarias deben ser las mismas");
        int hour = hora - timeSimul.getHora();
        int minu = min - timeSimul.getMin();
        int segu = seg - timeSimul.getSeg();
        int total = (hour*3600) + (minu*60) + segu;
        return (total < 0) ? (total+3600) : total;
    }
    
    @Override
    public String toString() {
        return hora+":"+min+":"+seg+" "+zonaHoraria;
    }
    
//    public static void main(String ar[]){
//        System.out.println(obtenerTiempo("1:40:3 PM").toString());
//    }
    
    public static TimeSimul obtenerTiempo(String tiempo){
        
        String values[] = tiempo.split(" ")[0].split(":");
                
        GregorianCalendar calendar = new GregorianCalendar(new GregorianCalendar().get(GregorianCalendar.YEAR),
                new GregorianCalendar().get(GregorianCalendar.MONTH), 
                new GregorianCalendar().get(GregorianCalendar.DAY_OF_YEAR),
                Integer.parseInt(values[0])+12,
                Integer.parseInt(values[1]),
                Integer.parseInt(values[2]));

        return new TimeSimul(calendar);
    }
}
