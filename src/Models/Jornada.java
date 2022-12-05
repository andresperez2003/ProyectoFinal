/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.io.Serializable;
import java.util.LinkedList;

/**
 *
 * @author andre
 */
public class Jornada implements Serializable{
    private String numero;
    private String slogaFIFA;
    private LinkedList<Partido> misPartidos;
    public Jornada(String numero, String slogaFIFA) {
        this.numero = numero;
        this.slogaFIFA = slogaFIFA;
        this.misPartidos = new LinkedList<>();
    }

    
    /**
     * @return the numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * @return the slogaFIFA
     */
    public String getSlogaFIFA() {
        return slogaFIFA;
    }

    /**
     * @param slogaFIFA the slogaFIFA to set
     */
    public void setSlogaFIFA(String slogaFIFA) {
        this.slogaFIFA = slogaFIFA;
    }

    /**
     * @return the misPartidos
     */
    public LinkedList<Partido> getMisPartidos() {
        return misPartidos;
    }

    /**
     * @param misPartidos the misPartidos to set
     */
    public void setMisPartidos(LinkedList<Partido> misPartidos) {
        this.misPartidos = misPartidos;
    }
}
