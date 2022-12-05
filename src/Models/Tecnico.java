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
public class Tecnico extends Persona implements Serializable{
    private int aniosExperiencia;
    private double salario;
    private Equipo miEquipo;

    public Tecnico(int aniosExperiencia, double salario, String cedula, String nombre, String apellido, int edad) {
        super(cedula, nombre, apellido, edad);
        this.aniosExperiencia = aniosExperiencia;
        this.salario = salario;
    }

    /**
     * @return the aniosExperiencia
     */
    public int getAniosExperiencia() {
        return aniosExperiencia;
    }

    /**
     * @param aniosExperiencia the aniosExperiencia to set
     */
    public void setAniosExperiencia(int aniosExperiencia) {
        this.aniosExperiencia = aniosExperiencia;
    }

    /**
     * @return the salario
     */
    public double getSalario() {
        return salario;
    }

    /**
     * @param salario the salario to set
     */
    public void setSalario(double salario) {
        this.salario = salario;
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
