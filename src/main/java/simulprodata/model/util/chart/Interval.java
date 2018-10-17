/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simulprodata.model.util.chart;

/**
 *
 * @author Ariel Arnedo
 */
public class Interval {
    
    private int inferior, superior;
    private int llegadas;
    private int numeroDeLlegadas;

    public Interval() {
        llegadas = 0;
    }

    public Interval(int inferior, int superior) {
        this();
        this.inferior = inferior;
        this.superior = superior;
    }

    public int getInferior() {
        return inferior;
    }

    public void setInferior(int inferior) {
        this.inferior = inferior;
    }

    public int getSuperior() {
        return superior;
    }

    public void setSuperior(int superior) {
        this.superior = superior;
    }

    public int getLlegadas() {
        return llegadas;
    }

    public void setLlegadas(int llegadas) {
        this.llegadas = llegadas + this.llegadas;
    }

    public int getNumeroDeLlegadas() {
        return numeroDeLlegadas;
    }

    public void setNumeroDeLlegadas(int numeroDeLlegadas) {
        this.numeroDeLlegadas = numeroDeLlegadas + this.numeroDeLlegadas;
    }
    
    public double getPromedioDeLlegadas(){
        return (double)llegadas/(double)numeroDeLlegadas;
    }
    
    public boolean isContentInRange(int value){
        return (value >= inferior && value <= superior);
    }

    @Override
    public String toString() {
        return  "("+inferior/60 + ":" + superior/60 + ")";
    }
    
    
}
