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
public class Equipo implements Serializable{
    private String id;
    private String nombre;
    private int anioFundacion;
    private int numeroTitulosNacionales;
    private int numeroTitulosInternacionales;
    private int puntos;
    private int golesAFavor;
    private int golesEnContra;
    private int partidosJugados;
    private LinkedList<Aficionado> misAficionados;
    private LinkedList<Partido> misPartidos;
    private LinkedList<Jugador> misJugadores;
    private Manager miManager;
    private Tecnico miTecnico;

    public Equipo(String id, String nombre, int anioFundacion, int numeroTitulosNacionales, int numeroTitulosInternacionales, int puntos, int golesAFavor, int golesEnContra, int partidosJugados) {
        this.id = id;
        this.nombre = nombre;
        this.anioFundacion = anioFundacion;
        this.numeroTitulosNacionales = numeroTitulosNacionales;
        this.numeroTitulosInternacionales = numeroTitulosInternacionales;
        this.puntos = puntos;
        this.golesAFavor = golesAFavor;
        this.golesEnContra = golesEnContra;
        this.partidosJugados = partidosJugados;
        this.misAficionados = new LinkedList<>();
        this.misPartidos = new LinkedList<>();
        this.misJugadores = new LinkedList<>();

    }

    public void crearJugador(Jugador nuevoJugador){
        this.getMisJugadores().add(nuevoJugador);
    }
    
    public void crearTecnico(Tecnico nuevoTecnico){
        this.miTecnico = nuevoTecnico;
    }
    
    public Jugador jugadorMasJovenEquipo(){
        int menor = Integer.MAX_VALUE;
        Jugador jugadorMasJoven = null;
        for(Jugador jugadorActual : this.misJugadores){
            if(jugadorActual.getEdad()<menor){
                menor = jugadorActual.getEdad();
                jugadorMasJoven = jugadorActual;
            }
        }
        return jugadorMasJoven;
    }
    
    public double promedioEdad(){
        int suma= 0;
        double promedio = 0;
        for(Jugador jugadorActual: this.misJugadores){
            suma+=jugadorActual.getEdad();
        }
        if(this.getMisJugadores().size() == 0){
            return 0;
        }
        promedio= suma/this.getMisJugadores().size();
        return promedio;
    }
    
    public Jugador goleadorEquipo(){
        Jugador jugadorGoleador = null;
        int mayor = Integer.MIN_VALUE;
        for(Jugador jugadorActual : this.misJugadores){
            if(jugadorActual.getNumeroGolesMarcados()>mayor){
                mayor = jugadorActual.getNumeroGolesMarcados();
                jugadorGoleador = jugadorActual;
            }
        }
        return jugadorGoleador;
    }
    
    public int CantidadPartidosGoleadaPorEquipo(){
        int cantPartidos= 0;
        for(Partido partidoActual: this.misPartidos){
            boolean partidoGoleada = partidoActual.goleada();
            if(partidoGoleada){
                cantPartidos+=1;
            }
        }
        return cantPartidos;
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
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the anioFundacion
     */
    public int getAnioFundacion() {
        return anioFundacion;
    }

    /**
     * @param anioFundacion the anioFundacion to set
     */
    public void setAnioFundacion(int anioFundacion) {
        this.anioFundacion = anioFundacion;
    }

    /**
     * @return the numeroTitulosNacionales
     */
    public int getNumeroTitulosNacionales() {
        return numeroTitulosNacionales;
    }

    /**
     * @param numeroTitulosNacionales the numeroTitulosNacionales to set
     */
    public void setNumeroTitulosNacionales(int numeroTitulosNacionales) {
        this.numeroTitulosNacionales = numeroTitulosNacionales;
    }

    /**
     * @return the numeroTitulosInternacionales
     */
    public int getNumeroTitulosInternacionales() {
        return numeroTitulosInternacionales;
    }

    /**
     * @param numeroTitulosInternacionales the numeroTitulosInternacionales to set
     */
    public void setNumeroTitulosInternacionales(int numeroTitulosInternacionales) {
        this.numeroTitulosInternacionales = numeroTitulosInternacionales;
    }

    /**
     * @return the puntos
     */
    public int getPuntos() {
        return puntos;
    }

    /**
     * @param puntos the puntos to set
     */
    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    /**
     * @return the golesAFavor
     */
    public int getGolesAFavor() {
        return golesAFavor;
    }

    /**
     * @param golesAFavor the golesAFavor to set
     */
    public void setGolesAFavor(int golesAFavor) {
        this.golesAFavor = golesAFavor;
    }

    /**
     * @return the golesEnContra
     */
    public int getGolesEnContra() {
        return golesEnContra;
    }

    /**
     * @param golesEnContra the golesEnContra to set
     */
    public void setGolesEnContra(int golesEnContra) {
        this.golesEnContra = golesEnContra;
    }

    /**
     * @return the partidosJugados
     */
    public int getPartidosJugados() {
        return partidosJugados;
    }

    /**
     * @param partidosJugados the partidosJugados to set
     */
    public void setPartidosJugados(int partidosJugados) {
        this.partidosJugados = partidosJugados;
    }

    /**
     * @return the misAficionados
     */
    public LinkedList<Aficionado> getMisAficionados() {
        return misAficionados;
    }

    /**
     * @param misAficionados the misAficionados to set
     */
    public void setMisAficionados(LinkedList<Aficionado> misAficionados) {
        this.misAficionados = misAficionados;
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

    /**
     * @return the misJugadores
     */
    public LinkedList<Jugador> getMisJugadores() {
        return misJugadores;
    }

    /**
     * @param misJugadores the misJugadores to set
     */
    public void setMisJugadores(LinkedList<Jugador> misJugadores) {
        this.misJugadores = misJugadores;
    }

    /**
     * @return the miTecnico
     */
    public Tecnico getMiTecnico() {
        return miTecnico;
    }

    /**
     * @param miTecnico the miTecnico to set
     */
    public void setMiTecnico(Tecnico miTecnico) {
        this.miTecnico = miTecnico;
    }
}
