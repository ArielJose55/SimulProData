/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simulprodata.model.logic;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author Ariel Arnedo
 */
public class Persona {
    
    private String sexo;
    private String tipo;
    private Tiempo tiempos;
    private java.util.List<String> categorias;
    public Persona() {
        tiempos = new Tiempo(new TimeSimul(new GregorianCalendar()));
    }

    public Persona(String sexo) {
        this();
        this.sexo = sexo;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Tiempo getTiempos() {
        return tiempos;
    }

    public void setTiempos(Tiempo tiempos) {
        this.tiempos = tiempos;
    }

    public List<String> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<String> categorias) {
        List<String> list = new ArrayList<>();
        for (String string : categorias) {
            if(string.compareTo("caf�") == 0)
                list.add("Café");
            else if(string.compareTo("T�") == 0)
                list.add("Té");
            else if(string.compareTo("chocolaT�") == 0)
                list.add("Chocolaté");
            else
                list.add(string);
        }
        this.categorias = list;
    }
    
    
    
}
