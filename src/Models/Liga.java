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
public class Liga implements Serializable {
    private String id;
    private LinkedList<Estadio> misEstadios;
    private LinkedList<Persona> misPersonas;
    private LinkedList<Equipo> misEquipos;
    private LinkedList<Jornada> misJornadas;
    private LinkedList<Partido> misPartidos;
    public Liga(String id) {
        this.id = id;
        this.misEstadios = new LinkedList<>();
        this.misPersonas = new LinkedList<>();
        this.misEquipos = new LinkedList<>();
        this.misJornadas= new LinkedList<>();
        this.misPartidos = new LinkedList<>();
    }
    
    //Creacion y eliminacion para la composicion Liga - Equipo
    
    public void crearEquipo(String id, String nombre, int anioFundacion, int numeroTitulosNacionales, int numeroTitulosInternacionales, int puntos, int golesAFavor, int golesEnContra, int PartidosJugados){
        Equipo nuevoEquipo = new Equipo(id, nombre, anioFundacion, numeroTitulosNacionales, numeroTitulosInternacionales, puntos, golesAFavor, golesEnContra, PartidosJugados);
        this.getMisEquipos().add(nuevoEquipo);
    }
    
    public void eliminarEquipo(Equipo equipoEliminado){
        this.getMisEquipos().remove(equipoEliminado);
    }
    
    //Metodos pedidos
    
    public Jugador jugadorMasJovenLiga(){
        Jugador jugadorMasJoven = null;
        int menor = Integer.MAX_VALUE;
        for(Equipo equipoActual: this.getMisEquipos()){
            Jugador jugador = equipoActual.jugadorMasJovenEquipo();
            if(jugador.getEdad()<menor){
                menor = jugador.getEdad();
                jugadorMasJoven = jugador;
            }
        }
//        for(Persona personaActual : this.getMisPersonas()){
//            if(personaActual instanceof  Jugador){
//            if(personaActual.getEdad()<menor){
//                menor = personaActual.getEdad();
//                jugadorMasJoven = (Jugador)personaActual;
//                 }
//            }       
//        }
        return jugadorMasJoven;
    }
    
    
    public Jugador goleadorLiga(){
        Jugador jugadorGoleador= null;
        int mayor = Integer.MIN_VALUE;
        for(Equipo equipoActual: this.getMisEquipos()){
            Jugador jugadorMasGoleadorEquipo = equipoActual.goleadorEquipo();
            if(jugadorMasGoleadorEquipo.getNumeroGolesMarcados()>mayor){
                mayor = jugadorMasGoleadorEquipo.getNumeroGolesMarcados();
                jugadorGoleador = jugadorMasGoleadorEquipo;
            }
        }
        return jugadorGoleador;
    }
    
    //Organizar dependiendo de los puntos
    public LinkedList<Equipo> listaPosiciones(){
        Equipo aux = null;
        LinkedList <Equipo> posiciones = this.getMisEquipos();
        for(int j=0; j<posiciones.size(); j++){
             for(int i=0; i<posiciones.size(); i++){
            if(i<posiciones.size()-1){
                if(posiciones.get(i+1).getPuntos()>posiciones.get(i).getPuntos()){
                aux= posiciones.get(i);
                posiciones.set(i, this.getMisEquipos().get(i+1));
                posiciones.set(i+1, aux);
            }
           }
        }           
       }
        golesIguales(posiciones);
        return this.getMisEquipos();
    }
    
    
    public int cantidadPartidosGoleada(){
        int cantPartidos = 0;
        for(Equipo equipoActual : this.getMisEquipos()){
            cantPartidos+=equipoActual.CantidadPartidosGoleadaPorEquipo();
        }
        return cantPartidos;
    }
    
    //Organizar dependiendo de la diferencia de goles
    public LinkedList<Equipo> golesIguales(LinkedList<Equipo> listaEquipos){
      int tam = listaEquipos.size();
      Equipo aux = null;
      for(int i=0; i<tam; i++){
          for(int j=0; j<tam-1; j++){
              if(j<tam-1){
                  int diferenciaGolEquipo1 = listaEquipos.get(j).getGolesAFavor() - listaEquipos.get(j).getGolesEnContra();
              int diferenciaGolEquipo2 = listaEquipos.get(j+1).getGolesAFavor() - listaEquipos.get(j+1).getGolesEnContra();
              if(listaEquipos.get(j).getPuntos() == listaEquipos.get(j+1).getPuntos()){      
                  if( diferenciaGolEquipo2>diferenciaGolEquipo1){
                         aux=listaEquipos.get(j);
                         listaEquipos.set(j, listaEquipos.get(j+1));
                         listaEquipos.set(j+1, aux);
                  }                  
                }             
            }
          }    
        }   
       return listaEquipos;
      }
    
   
    
    //Buscar si existe un objeto
    
    public Estadio buscarEstadio(String id){
        Estadio estadioEncontrado = null;
        for(Estadio estadioActual: this.getMisEstadios()){
            if(estadioActual.getId().equals(id)){
                estadioEncontrado = estadioActual;
            }
        }
        return estadioEncontrado;
    }
    
    public Equipo buscarEquipo(String id){
        Equipo equipoEncontrado = null;
        for(Equipo equipoActual: this.getMisEquipos()){
            if(equipoActual.getId().equals(id)){
                equipoEncontrado = equipoActual;
            }
        }
        return equipoEncontrado;
    }
    
    public Aficionado buscarAficionado(String cedula){
        Aficionado aficionado = null;
        for(Persona aficionadoActual: this.getMisPersonas()){
            if(aficionadoActual instanceof Aficionado && aficionadoActual.getCedula().equals(cedula)){
                aficionado = ((Aficionado)aficionadoActual);
            }
        }
        return aficionado;
    }
    
    public Jugador buscarJugador(String cedula){
        Jugador jugador = null;
        for(Persona jugadorActual : this.getMisPersonas()){
            if(jugadorActual instanceof Jugador && jugadorActual.getCedula().equals(cedula)){
                jugador = ((Jugador)jugadorActual);
            }
        }
        return jugador;
    }
    
    public Manager buscarManager(String cedula){
        Manager manager = null; 
        for(Persona managerActual: this.getMisPersonas()){
            if(managerActual instanceof Manager && managerActual.getCedula().equals(cedula)){
                manager = ((Manager)managerActual);
            }
        }
        return manager;
    }
    
    public Arbitro buscarArbitro(String cedula){
        Arbitro arbitro = null;
        for(Persona arbitroActual: this.getMisPersonas()){
            if(arbitroActual instanceof Arbitro && arbitroActual.getCedula().equals(cedula)){
                arbitro = ((Arbitro)arbitroActual);
            }
        }
        return arbitro;
    }
    
    public Tecnico buscarTecnico(String cedula){
        Tecnico tecnico = null;
        for(Persona tecnicoActual : this.getMisPersonas()){
            if(tecnicoActual instanceof Tecnico && tecnicoActual.getCedula().equals(cedula)){
                tecnico= ((Tecnico)tecnicoActual);
            }
        }
        return tecnico;
    }
    
    public Jornada buscarJornada(String numero){
        Jornada jornada = null;
        for(Jornada jornadaActual: this.getMisJornadas()){
            if(jornadaActual.getNumero().equals(numero)){
                jornada = jornadaActual;
            }
        }
        return jornada;
    }
    
    public Partido buscarPartido(String id){
        Partido partido = null;
        for(Partido partidoActual: this.getMisPartidos()){
            if(partidoActual.getId().equals(id)){
                partido=partidoActual;
            }
        }
        return partido;
    }
    
    
    
    public boolean verificarPersonExiste(String cedula){
        boolean personaExistente = false;
        for(Persona personaActual : this.getMisPersonas()){
            if(personaActual.getCedula().equals(cedula)){
                personaExistente = true;
            }
        }
        return personaExistente;
    }
    
    
    //listar para saber que tipo de personas se esta buscando
    
    public LinkedList<Manager> listaManager(){
        LinkedList<Manager> listaManager = new LinkedList<>();
        for(Persona personaActual: this.getMisPersonas()){
            if(personaActual instanceof Manager){
                listaManager.add((Manager)personaActual);
            }
        }
        return listaManager;
    }
    
    public LinkedList<Jugador> listaJugador(){
        LinkedList<Jugador> lista = new LinkedList<>();
        for(Persona personaActual: this.getMisPersonas()){
            if(personaActual instanceof Jugador ){
                    lista.add((Jugador)personaActual);
            }
        }
        return lista;
    }
    
    public LinkedList<Tecnico> listaTecnico(){
        LinkedList<Tecnico> listaTecnicos = new LinkedList<>();
        for(Persona personaActual: this.getMisPersonas()){
            if(personaActual instanceof Tecnico){
                listaTecnicos.add((Tecnico) personaActual);
            }
        }
        return listaTecnicos;
    }
    
    public LinkedList<Aficionado> listaAficionado(){
        LinkedList<Aficionado> listaAficionados = new LinkedList<>();
        for(Persona personaActual : this.getMisPersonas()){
            if(personaActual instanceof Aficionado){
                listaAficionados.add((Aficionado) personaActual);
            }
        }
        return listaAficionados;
    }
    public LinkedList<Arbitro> listaArbitros(){
        LinkedList<Arbitro> listaArbitros = new LinkedList<>();
        for(Persona personaActual: this.getMisPersonas()){
            if(personaActual instanceof Arbitro){
                listaArbitros.add((Arbitro) personaActual);
            }
        }
        return listaArbitros;
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
     * @return the misEstadios
     */
    public LinkedList<Estadio> getMisEstadios() {
        return misEstadios;
    }

    /**
     * @param misEstadios the misEstadios to set
     */
    public void setMisEstadios(LinkedList<Estadio> misEstadios) {
        this.misEstadios = misEstadios;
    }

    /**
     * @return the misPersonas
     */
    public LinkedList<Persona> getMisPersonas() {
        return misPersonas;
    }

    /**
     * @param misPersonas the misPersonas to set
     */
    public void setMisPersonas(LinkedList<Persona> misPersonas) {
        this.misPersonas = misPersonas;
    }

    /**
     * @return the misEquipos
     */
    public LinkedList<Equipo> getMisEquipos() {
        return misEquipos;
    }

    /**
     * @param misEquipos the misEquipos to set
     */
    public void setMisEquipos(LinkedList<Equipo> misEquipos) {
        this.misEquipos = misEquipos;
    }

    /**
     * @return the misJornadas
     */
    public LinkedList<Jornada> getMisJornadas() {
        return misJornadas;
    }

    /**
     * @param misJornadas the misJornadas to set
     */
    public void setMisJornadas(LinkedList<Jornada> misJornadas) {
        this.misJornadas = misJornadas;
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
