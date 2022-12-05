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
public class Jugador extends Persona implements Serializable{
    private String nacionalidad;
    private String posicion;
    private int numeroGolesMarcados;
    private double salario;
    private Equipo miEquipo;
    private Manager miManager;

    public Jugador(String nacionalidad, String posicion, int numeroGolesMarcados, double salario, String cedula, String nombre, String apellido, int edad) {
        super(cedula, nombre, apellido, edad);
        this.nacionalidad = nacionalidad;
        this.posicion = posicion;
        this.numeroGolesMarcados = numeroGolesMarcados;
        this.salario = salario;
    }



    /**
     * @return the nacionalidad
     */
    public String getNacionalidad() {
        return nacionalidad;
    }

    /**
     * @param nacionalidad the nacionalidad to set
     */
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    /**
     * @return the posicion
     */
    public String getPosicion() {
        return posicion;
    }

    /**
     * @param posicion the posicion to set
     */
    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    /**
     * @return the numeroGolesMarcados
     */
    public int getNumeroGolesMarcados() {
        return numeroGolesMarcados;
    }

    /**
     * @param numeroGolesMarcados the numeroGolesMarcados to set
     */
    public void setNumeroGolesMarcados(int numeroGolesMarcados) {
        this.numeroGolesMarcados = numeroGolesMarcados;
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
     * @return the miManager
     */
    public Manager getMiManager() {
        return miManager;
    }

    /**
     * @param miManager the miManager to set
     */
    public void setMiManager(Manager miManager) {
        this.miManager = miManager;
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
