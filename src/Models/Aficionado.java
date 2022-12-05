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
public class Aficionado extends Persona implements Serializable{
    private int aniosFidelidad;
    private boolean abonado;
    private Equipo miEquipo;

    public Aficionado(int aniosFidelidad, boolean abonado, String cedula, String nombre, String apellido, int edad) {
        super(cedula, nombre, apellido, edad);
        this.aniosFidelidad = aniosFidelidad;
        this.abonado = abonado;
    }
    
    /**
     * @return the aniosFidelidad
     */
    public int getAniosFidelidad() {
        return aniosFidelidad;
    }

    /**
     * @param aniosFidelidad the aniosFidelidad to set
     */
    public void setAniosFidelidad(int aniosFidelidad) {
        this.aniosFidelidad = aniosFidelidad;
    }

    /**
     * @return the abonado
     */
    public boolean isAbonado() {
        return abonado;
    }

    /**
     * @param abonado the abonado to set
     */
    public void setAbonado(boolean abonado) {
        this.abonado = abonado;
    }

    /**
     * @return the miEquipo
     */
    public Equipo getMiEquipo() {
        return miEquipo;
    }

    /**
     * @param miEquipo the miEquipo to set
     */
    public void setMiEquipo(Equipo miEquipo) {
        this.miEquipo = miEquipo;
    }
}
