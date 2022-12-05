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
public class Partido implements Serializable {
    private String id;
    private String fecha;
    private int golesLocal;
    private int golesVisitante;
    private Jornada miJornada;
    private Equipo miEquipoLocal;
    private Equipo miEquipoVisitante;
    private Estadio miEstadio;
    private Arbitro miArbitro;
    
    public Partido(String id, String fecha, int golesLocal, int golesVisitante) {
        this.id = id;
        this.fecha = fecha;
        this.golesLocal = golesLocal;
        this.golesVisitante = golesVisitante;
    }

    public boolean goleada(){
       int restaGoles= getGolesLocal()-getGolesVisitante();
       if(restaGoles<0){
           restaGoles = -1*(restaGoles);
       }
        if (restaGoles >4){
            return true;
        }
       return false;
    }
    
    public void asignarPuntos(int GolesVisitante, int GolesLocal){
        
        //Asignacion de goles a favor Local
        int totalGolesFavorLocal = this.getMiEquipoLocal().getGolesAFavor();
        totalGolesFavorLocal+=GolesLocal;
        this.getMiEquipoLocal().setGolesAFavor(totalGolesFavorLocal);
        //Asignacion goles en contra local
        int totalGolesContraLocal = this.getMiEquipoLocal().getGolesEnContra();
        totalGolesContraLocal+=GolesVisitante;
        this.getMiEquipoLocal().setGolesEnContra(totalGolesContraLocal);
        
        //Asignacion goles a favor visita
        int totalGolesFavorVisita = this.getMiEquipoVisitante().getGolesAFavor();
        totalGolesFavorVisita+=GolesVisitante;
        this.getMiEquipoVisitante().setGolesAFavor(totalGolesFavorVisita);
        
        //Asignacion goles en contra visita
        int totalGolesContraVisita = this.getMiEquipoVisitante().getGolesEnContra();
        totalGolesContraVisita+=GolesLocal;
        this.getMiEquipoVisitante().setGolesEnContra(totalGolesContraVisita);
        
        if(GolesLocal>GolesVisitante){
            int puntosLocal= this.getMiEquipoLocal().getPuntos();
            puntosLocal+=3;
            this.getMiEquipoLocal().setPuntos(puntosLocal);
        }else if (GolesLocal<GolesVisitante){
            int puntosVisitante = this.getMiEquipoVisitante().getPuntos();
            puntosVisitante+=3;
            this.getMiEquipoVisitante().setPuntos(puntosVisitante);
        }else if(GolesLocal == GolesVisitante){
            int puntosLocal = this.getMiEquipoLocal().getPuntos();
            int puntosVisitante = this.getMiEquipoVisitante().getPuntos();
            puntosLocal++;
            puntosVisitante++;
            this.getMiEquipoLocal().setPuntos(puntosLocal);
            this.getMiEquipoVisitante().setPuntos(puntosVisitante);
        }
    }
  
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the golesLocal
     */
    public int getGolesLocal() {
        return golesLocal;
    }

    /**
     * @param golesLocal the golesLocal to set
     */
    public void setGolesLocal(int golesLocal) {
        this.golesLocal = golesLocal;
    }

    /**
     * @return the golesVisitante
     */
    public int getGolesVisitante() {
        return golesVisitante;
    }

    /**
     * @param golesVisitante the golesVisitante to set
     */
    public void setGolesVisitante(int golesVisitante) {
        this.golesVisitante = golesVisitante;
    }

    /**
     * @return the miJornada
     */
    public Jornada getMiJornada() {
        return miJornada;
    }

    /**
     * @param miJornada the miJornada to set
     */
    public void setMiJornada(Jornada miJornada) {
        this.miJornada = miJornada;
    }


    /**
     * @return the miEstadio
     */
    public Estadio getMiEstadio() {
        return miEstadio;
    }

    /**
     * @param miEstadio the miEstadio to set
     */
    public void setMiEstadio(Estadio miEstadio) {
        this.miEstadio = miEstadio;
    }

    /**
     * @return the miArbitro
     */
    public Arbitro getMiArbitro() {
        return miArbitro;
    }

    /**
     * @param miArbitro the miArbitro to set
     */
    public void setMiArbitro(Arbitro miArbitro) {
        this.miArbitro = miArbitro;
    }

    /**
     * @return the miEquipoLocal
     */
    public Equipo getMiEquipoLocal() {
        return miEquipoLocal;
    }

    /**
     * @param miEquipoLocal the miEquipoLocal to set
     */
    public void setMiEquipoLocal(Equipo miEquipoLocal) {
        this.miEquipoLocal = miEquipoLocal;
    }

    /**
     * @return the miEquipoVisitante
     */
    public Equipo getMiEquipoVisitante() {
        return miEquipoVisitante;
    }

    /**
     * @param miEquipoVisitante the miEquipoVisitante to set
     */
    public void setMiEquipoVisitante(Equipo miEquipoVisitante) {
        this.miEquipoVisitante = miEquipoVisitante;
    }
}
