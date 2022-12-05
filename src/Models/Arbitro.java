/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.io.Serializable;

/**
 *
 * @author andre
 */
public class Arbitro extends Persona implements Serializable{
    private boolean esFiFA;

    public Arbitro(boolean esFiFA, String cedula, String nombre, String apellido, int edad) {
        super(cedula, nombre, apellido, edad);
        this.esFiFA = esFiFA;
    }



    /**
     * @return the esFiFA
     */
    public boolean isEsFiFA() {
        return esFiFA;
    }

    /**
     * @param esFiFA the esFiFA to set
     */
    public void setEsFiFA(boolean esFiFA) {
        this.esFiFA = esFiFA;
    }
}
