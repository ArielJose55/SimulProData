/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simulprodata.model.logic;

/**
 *
 * @author Ariel Arnedo
 */
public class Tiempo {
    
    private TimeSimul momentoDeRecepcionEnCola;
    private TimeSimul momentoDeRecepcionEnEspera;
    private TimeSimul momentoDeSalida;
    

    public Tiempo() {
    }

    public Tiempo(TimeSimul momentoDeRecepcionEnCola) {
        this.momentoDeRecepcionEnCola = momentoDeRecepcionEnCola;
    }

    public Tiempo(TimeSimul momentoDeRecepcionEnCola, TimeSimul momentoDeRecepcionEnEspera) {
        this.momentoDeRecepcionEnCola = momentoDeRecepcionEnCola;
        this.momentoDeRecepcionEnEspera = momentoDeRecepcionEnEspera;
    }

    public TimeSimul getMomentoDeRecepcionEnCaja() {
        return momentoDeRecepcionEnCola;
    }

    public void setMomentoDeRecepcionEnCaja(TimeSimul momentoDeRecepcionEnCola) {
        this.momentoDeRecepcionEnCola = momentoDeRecepcionEnCola;
    }

    public TimeSimul getMomentoDeRecepcionEnEspera() {
        return momentoDeRecepcionEnEspera;
    }

    public void setMomentoDeRecepcionEnEspera(TimeSimul momentoDeRecepcionEnEspera) {
        this.momentoDeRecepcionEnEspera = momentoDeRecepcionEnEspera;
    }

    public TimeSimul getMomentoDeSalida() {
        return momentoDeSalida;
    }

    public void setMomentoDeSalida(TimeSimul momentoDeSalida) {
        this.momentoDeSalida = momentoDeSalida;
    }
    
    public int getTiempoEnCaja(){
        if(momentoDeRecepcionEnCola == null)
            throw new RuntimeException("Momento de recepcion en cola no ha sido estimado");
        if(momentoDeRecepcionEnEspera == null)
            throw new RuntimeException("Momento de recepcion en ser atendio el cliente no ha sido estimado");
        
        return momentoDeRecepcionEnEspera.getDiferenciaEnMinutos(momentoDeRecepcionEnCola);
    }
    
    public int getTiempoDeAtencion(){
        if(momentoDeRecepcionEnCola == null)
            throw new RuntimeException("Momento de recepcion en cola no ha sido estimado");
        if(momentoDeRecepcionEnEspera == null)
            throw new RuntimeException("Momento de recepcion en ser atendio el cliente no ha sido estimado");
        
        return momentoDeSalida.getDiferenciaEnMinutos(momentoDeRecepcionEnEspera);
    }
}
