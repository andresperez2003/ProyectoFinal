/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinal;

import Models.Aficionado;
import Models.Arbitro;
import Models.Equipo;
import Models.Estadio;
import Models.Jornada;
import Models.Jugador;
import Models.Liga;
import Models.Manager;
import Models.Partido;
import Models.Persona;
import Models.Tecnico;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author andre
 */
public class jfProyectoFinal extends javax.swing.JFrame {

    Liga miLiga;
    
    /**
     * Creates new form jfProyectoFinal
     */
    public jfProyectoFinal() {
        initComponents();
        //this.miLiga = new Liga("01");
        cargarSesion();
        actualizarTablaEquipo();
        actualizarTablaEstadio();
        actualizarTablaAficionado();
        actualizarTablaJugadores();
        actualizarTablaManagers();
        actualizarTablaArbitro();
        actualizatTablaTecnico();
        actualizarTablaJornada();
        actualizarTablaPartidos();
        actualizarTablaJornadPartidos();
        actualizarTablaEstadioPartido();
        acualizarTablaEquipoPartidos();
        jugadorJovenLiga();
        goleadorLiga();
        
        
        actualizarComboJugadoresManager();
         addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                eventoCierre();
            }
        });
        
    }

    public void eventoCierre() {
        System.out.println("Guardando");
        try {
            ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream("liga.obj"));
            salida.writeObject(this.miLiga);
            salida.close();
        } catch (Exception ex) {
            System.out.println("" + ex);
        }
        System.exit(0);
    }
    
        public void cargarSesion() {
        try {
            ObjectInputStream entrada = new ObjectInputStream(new FileInputStream("liga.obj"));
            this.miLiga = (Liga) entrada.readObject();
        } catch (Exception ex) {
            System.out.println("" + ex);
        }
    }
    
    
    public void limpiarCajas(){
        
        //Equipo
        this.txtNombreEquipo.setText("");
        this.txtIdEquipo.setText("");
        this.txtAñoFundacion.setText("");
        this.txtGolesFavor.setText("");
        this.txtGolesContra.setText("");
        this.txtPartidosJugados.setText("");
        this.txtTitulosInternacionales.setText("");
        this.txtTitulosNacionales.setText("");
        this.txtPuntos.setText("");
        
        //Estadio
        this.txtIdEstadio.setText("");
        this.txtNombreEstadio.setText("");
        this.txtCiudad.setText("");
        this.txtCapacidad.setText("");
        
        //Aficionado
        this.txtCedulaAficionado.setText("");
        this.txtNombreAficionado.setText("");
        this.txtApellidoAficionado.setText("");
        this.txtEdadAficionado.setText("");
        this.txtFidelidadAficionado.setText("");
        this.checkAbonado.setSelected(false);
        
        //Jugador
        this.txtCedulaJugador.setText("");
        this.txtNombreJugador.setText("");
        this.txtApellidoJugador.setText("");
        this.txtEdadJugador.setText("");
        this.txtNacionalidad.setText("");
        this.txtGolesMarcados.setText("");
        this.txtPosicionJugador.setText("");
        this.txtSalarioJugador.setText("");
        
        //Manager
        this.txtCedulaManager.setText("");
        this.txtNombreManager.setText("");
        this.txtApellidoManager.setText("");
        this.txtEdadManager.setText("");
        this.txtAñosAfiliacion.setText("");
        this.cbManagerEquipo.removeAllItems();
        
        //Arbitro
        this.txtCedulaArbitro.setText("");
        this.txtNombreArbitro.setText("");
        this.txtApellidoArbitro.setText("");
        this.txtEdadArbitro.setText("");
        this.checkFIFA.setSelected(false);
        
        //Tecnico
        this.txtCedulaTecnico.setText("");
        this.txtNombreTecnico.setText("");
        this.txtApellidoTecnico.setText("");
        this.txtEdadTecnico.setText("");
        this.txtAñosExperiencia.setText("");
        this.txtSalario.setText("");
        
        //Jornada
        this.txtNumeroJornada.setText("");
        this.txtSloganJornada.setText("");
        
        //Partido
        this.txtIdPartido.setText("");
        this.txtFechaPartido.setText("");
        this.txtGolesLocal.setText("");
        this.txtGolesVisita.setText("");
    }
    
    
    
    //Actualizacion de ComboBox
    
    public void actualizarComboJugadoresManager(){
        try {
            this.cbJugadoresManager.removeAllItems();
        for(Persona personaActual: this.miLiga.listaJugador()){
            if(personaActual instanceof Jugador){
                this.cbJugadoresManager.addItem(personaActual.getCedula() + " - "+ personaActual.getNombre()+ " - "+ ((Jugador) personaActual).getPosicion());
            }
        }
        } catch (Exception e) {
            System.out.println(""+e);
        }
    }
    
    public void actualizarComboEquipoManager(){
        try {
            this.cbEquipoManager.removeAllItems();
            for(Persona personaActual: this.miLiga.listaManager()){
                this.cbEquipoManager.addItem(personaActual.getNombre() + " "+ personaActual.getApellido());
            }
        } catch (Exception e) {
            System.out.println(""+e);
        }
    }
    
    public void actualizarComboManagerEquipo(){
        try {
            this.cbManagerEquipo.removeAllItems();
            String cedula = this.txtCedulaManager.getText();
            Manager manager = this.miLiga.buscarManager(cedula);
            if(manager != null){
                this.cbManagerEquipo.addItem(manager.getMiEquipo().getNombre());
            }else{
                JOptionPane.showMessageDialog(this, "No se encontro el manager");
            }
        } catch (Exception e) {
        }
    }
    
    public void actualizarComboEquipoTecnico(){
        this.cbEquipoTecnico.removeAllItems();
        for(Tecnico tecnicoActual: this.miLiga.listaTecnico()){
            this.cbEquipoTecnico.addItem(tecnicoActual.getNombre()+" "+ tecnicoActual.getApellido());
        }
    }
    
    public void actualizarComboEquipoJugador(){
        this.cbEquipoJugador.removeAllItems();
        for(Jugador jugadorActual: this.miLiga.listaJugador()){
            this.cbEquipoJugador.addItem(jugadorActual.getNombre()+" "+ jugadorActual.getApellido());
        }
    }
    
    public void actualizarComboEquipoAficionado(){
        this.cbEquipoAficionado.removeAllItems();
        for(Aficionado aficionadoActual: this.miLiga.listaAficionado()){
            this.cbEquipoAficionado.addItem(aficionadoActual.getNombre()+ " "+ aficionadoActual.getApellido());
        }
    }
    
    public void actualizarComboPartidoEquipoLocal(){
        this.cbEquipoLocal.removeAllItems();
        for(Equipo equipoActual: this.miLiga.getMisEquipos()){
            this.cbEquipoLocal.addItem(equipoActual.getNombre());
        }
    }
    public void actualizaComboPartidoEquipoVisitante(){
        this.cbEquipoVisita.removeAllItems();
        for(Equipo equipoActual: this.miLiga.getMisEquipos()){
            this.cbEquipoVisita.addItem(equipoActual.getNombre());
        }
    }
    
    public void actualizarComboJornadaPartido(){
        this.cbJornadaEquipo.removeAllItems();
        for(Partido partidoActual: this.miLiga.getMisPartidos()){
            this.cbJornadaEquipo.addItem(partidoActual.getId()+ " - "+ partidoActual.getFecha());
        }
    }
    
    public void actualizarComboPartidoArbitro(){
        this.cbPartidoArbitro.removeAllItems();
        for(Arbitro arbitroActual: this.miLiga.listaArbitros()){
            this.cbPartidoArbitro.addItem(arbitroActual.getNombre()+" "+ arbitroActual.getApellido());
        }
    }
    
    public void actualizarComboEstadioPartido(){
        this.cbEstadioPartido.removeAllItems();
        for(Partido partidoActual: this.miLiga.getMisPartidos()){
            this.cbEstadioPartido.addItem(partidoActual.getId()+" - "+partidoActual.getFecha());
        }
    }
    
    public void actualizarComboGolesJugadores(){
        String id = this.txtIdPartido.getText();
        Partido partido = this.miLiga.buscarPartido(id);
        if(partido != null){
         if(partido.getMiEquipoLocal() !=null && partido.getMiEquipoVisitante() !=null){
         for(Jugador jugadorLocal: partido.getMiEquipoLocal().getMisJugadores()){
             this.cbGolesJugadorLocal.addItem(jugadorLocal.getNombre()+" "+jugadorLocal.getApellido());
         }
         for(Jugador jugadorVisita: partido.getMiEquipoVisitante().getMisJugadores()){
             this.cbGolesJugadorVisita.addItem(jugadorVisita.getNombre()+" " + jugadorVisita.getApellido());
         }      
        }else
        {
        } 
     }
    }
    
    //Metodos 
    public void jugadorJovenLiga(){
        this.txtJugadorJovenLiga.setEditable(false);
        Jugador joven = this.miLiga.jugadorMasJovenLiga();
        if(joven != null){
            this.txtJugadorJovenLiga.setText( joven.getNombre()+ " "+ joven.getApellido());
        }else{
            
        }        
    }
    public void goleadorLiga(){
        this.txtGoleadorLiga.setEditable(false);
        Jugador goleador = this.miLiga.goleadorLiga();
        if(goleador!= null){
            this.txtGoleadorLiga.setText(goleador.getNombre()+" "+ goleador.getApellido());
        }else{
        }
    }
    
    
    //Actualizacion de tablas
    
    public void actualizarTablaEquipo(){
        this.txtPuntos.setEditable(false);
        this.txtGolesContra.setEditable(false);
        this.txtGolesFavor.setEditable(false);
        this.txtPromedioEdad.setEditable(false);
        this.txtPartidosJugados.setEditable(false);
            String nombreColumnas[]={"Id", "Nombre", "Puntos", "Partidos", "Fundacion", "Goles en contra", "Goles a favor", "Diferencia de gol", "T. Nacionales", "T. internacionales"};
            DefaultTableModel miModelo = new DefaultTableModel(null, nombreColumnas);
            this.tblEquipos.setModel(miModelo);
            for(Equipo equipoActual: this.miLiga.listaPosiciones()){
                int diferenciaGol = equipoActual.getGolesAFavor() - equipoActual.getGolesEnContra();
                String fila[] = new String[nombreColumnas.length];
                fila[0]=equipoActual.getId();
                fila[1] = equipoActual.getNombre();
                fila[2] = ""+equipoActual.getPuntos();
                fila[3]=""+equipoActual.getPartidosJugados();
                fila[4]= ""+equipoActual.getAnioFundacion();
                fila[5]= ""+equipoActual.getGolesEnContra();
                fila[6]= ""+ equipoActual.getGolesAFavor();
                fila[7]= Integer.toString(diferenciaGol);
                fila[8]=""+equipoActual.getNumeroTitulosNacionales();
                fila[9]=""+equipoActual.getNumeroTitulosInternacionales();
                miModelo.addRow(fila);
            }
            actualizarComboEquipoManager();
            actualizarComboEquipoTecnico();
            actualizarComboEquipoJugador();
            actualizarTablaEquipoJugador();
            actualizarComboEquipoAficionado();
            actualizarTablaEquipoAficionado();
            
 
    }

    public void actualizarTablaEstadio(){
        try {
             String nombreColumna[]={"Id","Nombre", "Ciudad", "Capacidad"};
             DefaultTableModel miModelo = new DefaultTableModel(null, nombreColumna);
             this.tblTablaEstadios.setModel(miModelo);
             for(Estadio estadioActual: this.miLiga.getMisEstadios()){
                 String fila[]= new String[nombreColumna.length];
                 fila[0]= estadioActual.getId();
                 fila[1]= estadioActual.getNombre();
                 fila[2]= estadioActual.getCiudad();
                 fila[3] = ""+estadioActual.getCapacidad();
                 miModelo.addRow(fila);
             }
        } catch (Exception e) {
            System.out.println(""+e);
        }
    }
    
    public void actualizarTablaAficionado(){
        try {
            this.cbAficionadoEquipo.removeAllItems();
            this.cbAficionadoEquipo.setEnabled(false);
            String nombreColumna[]={"Cedula", "Nombre", "Apellido", "Edad", "Años Fidelidad", "Abonado"};
            DefaultTableModel miModelo = new DefaultTableModel(null, nombreColumna);
            this.tblAficionados.setModel(miModelo);
            for(Persona aficionadoActual : this.miLiga.getMisPersonas()){
                if(aficionadoActual instanceof Aficionado){
                    String abonado = "";
                    if(((Aficionado) aficionadoActual).isAbonado()){
                        abonado = "Si";
                    }else{
                        abonado = "No";
                    }
                    String fila[] = new String[nombreColumna.length];
                    fila[0] = aficionadoActual.getCedula();
                    fila[1] = aficionadoActual.getNombre();
                    fila[2] = aficionadoActual.getApellido();
                    fila[3] = ""+aficionadoActual.getEdad();
                    fila[4] =  ""+((Aficionado) aficionadoActual).getAniosFidelidad();
                    fila[5] = abonado;
                    miModelo.addRow(fila);
                }
            }
        } catch (Exception e) {
            System.out.println(""+e);
        }
    }
    
    public void actualizarTablaJugadores(){
        try {
            this.cbJugadorEquipo.removeAllItems();
            this.cbJugadorEquipo.setEnabled(false);
            this.cbJugadorManager.removeAllItems();
            this.cbJugadorManager.setEnabled(false);
            String nombreColumna[]={"Cedula","Nombre","Apellido","Edad", "Posicion", "Nacionalidad","Goles Marcados", "Salario"};
            DefaultTableModel miMidelo = new DefaultTableModel(null, nombreColumna);
            this.tblJugadores.setModel(miMidelo);
            for(Persona jugadorActual: this.miLiga.getMisPersonas()){
                if(jugadorActual instanceof  Jugador ){
                    String fila[] = new String [nombreColumna.length];
                    fila[0]= jugadorActual.getCedula();
                    fila[1]= jugadorActual.getNombre();
                    fila[2]= jugadorActual.getApellido();
                    fila[3]= ""+jugadorActual.getEdad();
                    fila[4]= ""+((Jugador) jugadorActual).getPosicion();
                    fila[5] = ((Jugador) jugadorActual).getNacionalidad();
                    fila[6] = ""+ ((Jugador) jugadorActual).getNumeroGolesMarcados();
                    fila[7]= ""+ ((Jugador) jugadorActual).getSalario();
                    miMidelo.addRow(fila);
                }
            }
        } catch (Exception e) {
            System.out.println(""+e);
        }
    }
    
    public void actualizarTablaManagers(){
        try {
            this.cbManagerEquipo.removeAllItems();
            String nombreColumnas[]={"Cedula", "Nombre", "Apellido", "Edad", "Años Afiliacion"};
            DefaultTableModel miModelo = new DefaultTableModel(null, nombreColumnas);
            this.tblManagers.setModel(miModelo);
            for(Persona managerActual : this.miLiga.getMisPersonas()){
                if(managerActual instanceof  Manager){
                    String fila[]= new String [nombreColumnas.length];
                    fila[0]= managerActual.getCedula();
                    fila[1] = managerActual.getNombre();
                    fila[2]= managerActual.getApellido();
                    fila[3] = ""+ managerActual.getEdad();
                    fila[4] = ""+ ((Manager) managerActual).getAniosAfiliacion();
                    miModelo.addRow(fila);
                }
            }
            actualizarTablaRelacionJugadoresManager();
        } catch (Exception e) {
            System.out.println(""+e);
        }
    }
    
        
    public void actualizarTablaRelacionJugadoresManager(){
        try {
            String nombreColumna[]={"Cedula","Nombre","Apellido","Edad", "Posicion", "Nacionalidad","Goles Marcados", "Salario"};
            String cedula = this.txtCedulaManager.getText();
            Manager manager = this.miLiga.buscarManager(cedula);
            DefaultTableModel miMidelo = new DefaultTableModel(null, nombreColumna);
            this.tblRelacionJugadoresManager.setModel(miMidelo);
            if(manager != null){
                for(Jugador jugadorActual: manager.getMisJugadores() ){
                if(jugadorActual instanceof  Jugador ){
                    String fila[] = new String [nombreColumna.length];
                    fila[0]= jugadorActual.getCedula();
                    fila[1]= jugadorActual.getNombre();
                    fila[2]= jugadorActual.getApellido();
                    fila[3]= ""+jugadorActual.getEdad();
                    fila[4]= ""+((Jugador) jugadorActual).getPosicion();
                    fila[5] = ((Jugador) jugadorActual).getNacionalidad();
                    fila[6] = ""+ ((Jugador) jugadorActual).getNumeroGolesMarcados();
                    fila[7]= ""+ ((Jugador) jugadorActual).getSalario();
                    miMidelo.addRow(fila);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(""+e);
        }
    }
    
    
    public void actualizarTablaArbitro(){
        String nombreColumnas[] = {"Cedula", "Nombre", "Apellido", "Edad", "Arbitro Fifa"};
        DefaultTableModel miModelo = new DefaultTableModel(null, nombreColumnas);
        this.tblArbitros.setModel(miModelo);
        for(Persona arbitroActual : this.miLiga.getMisPersonas()){
            if(arbitroActual instanceof Arbitro){
                String fifa = "No";
                if(((Arbitro) arbitroActual).isEsFiFA()){
                    fifa = "Si";
                }
                String fila[]= new String [nombreColumnas.length];
                fila[0]= arbitroActual.getCedula();
                fila[1] = arbitroActual.getNombre();
                fila[2]= arbitroActual.getApellido();
                fila[3] = "" + arbitroActual.getEdad();
                fila[4] = fifa;
                miModelo.addRow(fila);
            }
        }
    }
    
    
    //Tecnicos
    
    
    public void actualizatTablaTecnico(){
        this.cbTecnicoEquipo.removeAllItems();
        String nombreColumna[]= {"Cedula","Nombre", "Apellido", "Edad", "Años experiencia", "Salario"};
        DefaultTableModel miModelo = new DefaultTableModel(null, nombreColumna);
        this.tblTecnicos.setModel(miModelo);
        for(Persona tecnicoActual : this.miLiga.getMisPersonas()){
            if(tecnicoActual instanceof Tecnico){
                String fila[] = new String[nombreColumna.length];
                fila[0] = tecnicoActual.getCedula();
                fila[1] = tecnicoActual.getNombre();
                fila[2] = tecnicoActual.getApellido();
                fila[3] = ""+tecnicoActual.getEdad();
                fila[4] = ""+ ((Tecnico) tecnicoActual).getAniosExperiencia();
                fila[5] = ""+ ((Tecnico) tecnicoActual).getSalario();
                miModelo.addRow(fila);                
            }
        }
        this.cbTecnicoEquipo.setEnabled(false);
    }


    
    public void actualizarTablaJornada(){
        String nombreColumna[] = {"Numero", "Slogan"};
        DefaultTableModel miModelo = new DefaultTableModel(null, nombreColumna);
        this.tblJornada.setModel(miModelo);
        for(Jornada jornadaActual : this.miLiga.getMisJornadas()){
            String fila[] = new String[nombreColumna.length];
            fila[0]= jornadaActual.getNumero();
            fila[1] = jornadaActual.getSlogaFIFA();
            miModelo.addRow(fila);
        }
        actualizarComboJornadaPartido();
    }
    
    public void actualizarTablaEquipoJugador(){
        String nombreColumna[]={"Cedula","Nombre","Apellido","Edad", "Posicion", "Nacionalidad","Goles Marcados", "Salario"};
        DefaultTableModel miModelo = new DefaultTableModel(null, nombreColumna);
        this.tblEquipoJugador.setModel(miModelo);
        String id = this.txtIdEquipo.getText();
        Equipo equipo = this.miLiga.buscarEquipo(id);
        if(equipo !=null){
            for(Jugador jugadorActual: equipo.getMisJugadores()){
                String fila[] = new String[nombreColumna.length];
                fila[0]= jugadorActual.getCedula();
                    fila[1]= jugadorActual.getNombre();
                    fila[2]= jugadorActual.getApellido();
                    fila[3]= ""+jugadorActual.getEdad();
                    fila[4]= ""+((Jugador) jugadorActual).getPosicion();
                    fila[5] = ((Jugador) jugadorActual).getNacionalidad();
                    fila[6] = ""+ ((Jugador) jugadorActual).getNumeroGolesMarcados();
                    fila[7]= ""+ ((Jugador) jugadorActual).getSalario();
                    miModelo.addRow(fila);
            }     
        }
    }
    public void actualizarTablaEquipoAficionado(){
         this.cbGolesJugadorLocal.removeAllItems();
        this.cbGolesJugadorVisita.removeAllItems();
        String nombreColumna[]={"Cedula", "Nombre", "Apellido", "Edad", "Años Fidelidad", "Abonado"};
            DefaultTableModel miModelo = new DefaultTableModel(null, nombreColumna);
            this.tblEquipoAficionado.setModel(miModelo);
            String id = this.txtIdEquipo.getText();
            Equipo equipo = this.miLiga.buscarEquipo(id);
        if(equipo !=null){
            for(Aficionado aficionadoActual: equipo.getMisAficionados()){
                String fila[] = new String[nombreColumna.length];
                    String abonado = "";
                    if(((Aficionado) aficionadoActual).isAbonado()){
                        abonado = "Si";
                    }else{
                        abonado = "No";
                    }
                fila[0]=aficionadoActual.getCedula();
                fila[1]=aficionadoActual.getNombre();
                fila[2]=aficionadoActual.getApellido();
                fila[3]=""+aficionadoActual.getEdad();
                fila[4]=""+aficionadoActual.getAniosFidelidad();
                fila[5]= abonado;
                    miModelo.addRow(fila);
            }     
        }
    }
    

    
    public void actualizarTablaPartidos(){
        this.txtPartidoJornada.setEditable(false);
        this.txtPartidoEstadio.setEditable(false);
        String nombreColumna[] ={"id","Fecha","Goles Local", "Goles Visita"};
        DefaultTableModel miModelo = new DefaultTableModel(null, nombreColumna);
        this.tblPartido.setModel(miModelo);
        for(Partido partidoActual: this.miLiga.getMisPartidos()){
            String fila[] = new String[nombreColumna.length];
            fila[0]= partidoActual.getId();
            fila[1] = partidoActual.getFecha();
            fila[2] = ""+partidoActual.getGolesLocal();
            fila[3]= ""+partidoActual.getGolesVisitante();
            miModelo.addRow(fila);
        }
        actualizarComboPartidoEquipoLocal();
        actualizaComboPartidoEquipoVisitante();
        actualizarComboPartidoArbitro();
        actualizarComboEstadioPartido();
    }
    
    public void actualizarTablaJornadPartidos(){
        String nombreColumna[] ={"id","Fecha","Goles Local", "Goles Visita"};
        DefaultTableModel miModelo = new DefaultTableModel(null, nombreColumna);
        this.tblJornadaPartido.setModel(miModelo);
        String numero = this.txtNumeroJornada.getText();
        Jornada jornada = this.miLiga.buscarJornada(numero);
        if(jornada!=null){
            for(Partido partidoActual: jornada.getMisPartidos()){
                String fila[] = new String[nombreColumna.length];
                fila[0]= partidoActual.getId();
                fila[1]= partidoActual.getFecha();
                fila[2]= ""+partidoActual.getGolesLocal();
                fila[3]=""+partidoActual.getGolesVisitante();
                miModelo.addRow(fila);
            }
        }
    }
    
    public void actualizarTablaEstadioPartido(){
         String nombreColumna[]={"Id","Fecha", "Goles Local", "Goles Visita","Local", "Visita"};
         DefaultTableModel miModelo = new DefaultTableModel(null, nombreColumna);
         this.tblEstadioPartido.setModel(miModelo);
         String numero = this.txtIdEstadio.getText();
         Estadio estadio = this.miLiga.buscarEstadio(numero);
         if(estadio != null){
           for(Partido partidoActual: estadio.getMisPartidos()){
              String fila[]= new String[nombreColumna.length];
               fila[0]= partidoActual.getId();
              fila[1]= partidoActual.getFecha();
              fila[2]= ""+partidoActual.getGolesLocal();
              fila[3]= ""+partidoActual.getGolesVisitante();
              if(partidoActual.getMiEquipoLocal() != null && partidoActual.getMiEquipoVisitante() !=null){
                  fila[4]=partidoActual.getMiEquipoLocal().getNombre();
                  fila[5]= partidoActual.getMiEquipoVisitante().getNombre();
              }else{
                  fila[4]="";
                  fila[5]="";
              }
              miModelo.addRow(fila);
           }                                          
         }
    }
    
    public void acualizarTablaEquipoPartidos(){
        String nombreColumna[]={"G. Local", "Goles Visitante","Local", "Visita"};
        DefaultTableModel miModelo = new DefaultTableModel(null, nombreColumna);
        this.tblEquipoPartidos.setModel(miModelo);
        String id= this.txtIdEquipo.getText();
        Equipo equipo = this.miLiga.buscarEquipo(id);
        if(equipo != null){
            for(Partido partidoActual: equipo.getMisPartidos()){
                String fila[]= new String[nombreColumna.length];
                fila[0]= ""+partidoActual.getGolesLocal();
                fila[1]= ""+ partidoActual.getGolesVisitante();
                fila[2]= partidoActual.getMiEquipoLocal().getNombre();
                fila[3]= partidoActual.getMiEquipoVisitante().getNombre();
                miModelo.addRow(fila);
            }
        }
    }
    
    
    //Metodo para guardar los certificados
    
    public void guardarArchivo(String contenido,String extension) {
    	try {
        	JFileChooser file = new JFileChooser();
        	file.showSaveDialog(this);
        	//String ruta = "" + file.getCurrentDirectory();
        	try (BufferedWriter save = new BufferedWriter(new FileWriter(file.getSelectedFile().toString()+ "."+extension))) {
            	save.write(contenido);
        	}
        	JOptionPane.showMessageDialog(null, "El archivo se a guardado Exitosamente",
                                                "Información",
                                                JOptionPane.INFORMATION_MESSAGE);
    	} catch (IOException ex) {
        	JOptionPane.showMessageDialog(null,"Su archivo no se ha guardado",
                                               "Advertencia",
                                           	JOptionPane.WARNING_MESSAGE);
    	}
	}

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        checkFIFA = new javax.swing.JCheckBox();
        txtCedulaArbitro = new javax.swing.JTextField();
        txtApellidoArbitro = new javax.swing.JTextField();
        txtNombreArbitro = new javax.swing.JTextField();
        txtEdadArbitro = new javax.swing.JTextField();
        btnCrearArbitro = new javax.swing.JButton();
        btnEditarArbitro = new javax.swing.JButton();
        btnBuscarArbitro = new javax.swing.JButton();
        btnEliminarArbitro = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblArbitros = new javax.swing.JTable();
        btnCertificado = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel56 = new javax.swing.JLabel();
        txtNumeroJornada = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        txtSloganJornada = new javax.swing.JTextField();
        btnCrearJornada = new javax.swing.JButton();
        btnEditarJornada = new javax.swing.JButton();
        btnBuscarJornada = new javax.swing.JButton();
        btnEliminarJornada = new javax.swing.JButton();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblJornada = new javax.swing.JTable();
        jLabel78 = new javax.swing.JLabel();
        cbJornadaEquipo = new javax.swing.JComboBox<>();
        jScrollPane14 = new javax.swing.JScrollPane();
        tblJornadaPartido = new javax.swing.JTable();
        jLabel79 = new javax.swing.JLabel();
        btnAgregarJornadaPartido = new javax.swing.JButton();
        btnEliminarJornadaPartido = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        txtNombreEstadio = new javax.swing.JTextField();
        txtCiudad = new javax.swing.JTextField();
        txtCapacidad = new javax.swing.JTextField();
        btnCrearEstadio = new javax.swing.JButton();
        btnEditarEstadio = new javax.swing.JButton();
        btnBuscarEstadio = new javax.swing.JButton();
        btnEliminarEstadio = new javax.swing.JButton();
        jLabel33 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblTablaEstadios = new javax.swing.JTable();
        jLabel62 = new javax.swing.JLabel();
        txtIdEstadio = new javax.swing.JTextField();
        jScrollPane13 = new javax.swing.JScrollPane();
        tblEstadioPartido = new javax.swing.JTable();
        cbEstadioPartido = new javax.swing.JComboBox<>();
        jLabel73 = new javax.swing.JLabel();
        btnEstadioPartido = new javax.swing.JButton();
        btnEliminarEstadioPartido = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        txtCedulaJugador = new javax.swing.JTextField();
        txtApellidoJugador = new javax.swing.JTextField();
        txtNacionalidad = new javax.swing.JTextField();
        txtGolesMarcados = new javax.swing.JTextField();
        txtNombreJugador = new javax.swing.JTextField();
        txtPosicionJugador = new javax.swing.JTextField();
        txtEdadJugador = new javax.swing.JTextField();
        txtSalarioJugador = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        btnCrearJugador = new javax.swing.JButton();
        btnEditarJugador = new javax.swing.JButton();
        btnBuscarJugador = new javax.swing.JButton();
        btnEliminarJugador = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblJugadores = new javax.swing.JTable();
        jLabel75 = new javax.swing.JLabel();
        cbJugadorEquipo = new javax.swing.JComboBox<>();
        jLabel76 = new javax.swing.JLabel();
        cbJugadorManager = new javax.swing.JComboBox<>();
        btnCertificadoJugador = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNombreAficionado = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtEdadAficionado = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtCedulaAficionado = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtFidelidadAficionado = new javax.swing.JTextField();
        checkAbonado = new javax.swing.JCheckBox();
        btnCrearAficionado = new javax.swing.JButton();
        btnEditarAficionado = new javax.swing.JButton();
        btnBuscarAficionado = new javax.swing.JButton();
        btnEliminarAficionado = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        Jlabel = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblAficionados = new javax.swing.JTable();
        txtApellidoAficionado = new javax.swing.JTextField();
        jLabel80 = new javax.swing.JLabel();
        cbAficionadoEquipo = new javax.swing.JComboBox<>();
        btnCertificadoAficionado = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        txtCedulaManager = new javax.swing.JTextField();
        txtApellidoManager = new javax.swing.JTextField();
        txtAñosAfiliacion = new javax.swing.JTextField();
        txtNombreManager = new javax.swing.JTextField();
        txtEdadManager = new javax.swing.JTextField();
        btnCrearManager = new javax.swing.JButton();
        btnEditarManager = new javax.swing.JButton();
        btnBuscarManager = new javax.swing.JButton();
        btnEliminarManager = new javax.swing.JButton();
        jLabel55 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblManagers = new javax.swing.JTable();
        jLabel66 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblRelacionJugadoresManager = new javax.swing.JTable();
        cbJugadoresManager = new javax.swing.JComboBox<>();
        btnAñadirRelacionJugadorManager = new javax.swing.JButton();
        btnDesligarManagerJugador = new javax.swing.JButton();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        cbManagerEquipo = new javax.swing.JComboBox<>();
        jButton15 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        txtCedulaTecnico = new javax.swing.JTextField();
        txtApellidoTecnico = new javax.swing.JTextField();
        txtAñosExperiencia = new javax.swing.JTextField();
        txtNombreTecnico = new javax.swing.JTextField();
        txtEdadTecnico = new javax.swing.JTextField();
        txtSalario = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        btnCrearTecnico = new javax.swing.JButton();
        btnEditarTecnico = new javax.swing.JButton();
        btnBuscarTecnico = new javax.swing.JButton();
        btnEliminarTecnico = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblTecnicos = new javax.swing.JTable();
        jLabel77 = new javax.swing.JLabel();
        cbTecnicoEquipo = new javax.swing.JComboBox<>();
        btnCertificadoTecnico = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel70 = new javax.swing.JLabel();
        txtJugadorJovenLiga = new javax.swing.JTextField();
        jLabel87 = new javax.swing.JLabel();
        txtGoleadorLiga = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        btnCrearPartido = new javax.swing.JButton();
        btnEditarPartido = new javax.swing.JButton();
        btnBuscarPartido = new javax.swing.JButton();
        btnEliminarPartido = new javax.swing.JButton();
        txtFechaPartido = new javax.swing.JFormattedTextField();
        jLabel38 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        tblPartido = new javax.swing.JTable();
        cbEquipoLocal = new javax.swing.JComboBox<>();
        cbEquipoVisita = new javax.swing.JComboBox<>();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        txtGolesLocal = new javax.swing.JTextField();
        txtGolesVisita = new javax.swing.JTextField();
        cbGolesJugadorLocal = new javax.swing.JComboBox<>();
        jLabel74 = new javax.swing.JLabel();
        txtGolesJugadorLocal = new javax.swing.JTextField();
        jLabel71 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        cbGolesJugadorVisita = new javax.swing.JComboBox<>();
        jLabel85 = new javax.swing.JLabel();
        txtGolesJugadorVisita = new javax.swing.JTextField();
        btnAñadirGolLocal = new javax.swing.JButton();
        jLabel86 = new javax.swing.JLabel();
        txtIdPartido = new javax.swing.JTextField();
        btnAñadirGolVisita = new javax.swing.JButton();
        btnAsignarEquiposPartido = new javax.swing.JButton();
        jLabel89 = new javax.swing.JLabel();
        cbPartidoArbitro = new javax.swing.JComboBox<>();
        jLabel90 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        txtPartidoJornada = new javax.swing.JTextField();
        txtPartidoEstadio = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtNombreEquipo = new javax.swing.JTextField();
        txtTitulosInternacionales = new javax.swing.JTextField();
        txtTitulosNacionales = new javax.swing.JTextField();
        txtGolesFavor = new javax.swing.JTextField();
        txtGolesContra = new javax.swing.JTextField();
        txtAñoFundacion = new javax.swing.JTextField();
        txtPartidosJugados = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        btnCrearEquipo = new javax.swing.JButton();
        btnEditarEquipo = new javax.swing.JButton();
        btnBuscarEquipo = new javax.swing.JButton();
        btnEliminarEquipo = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        txtIdEquipo = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblEquipos = new javax.swing.JTable();
        jLabel63 = new javax.swing.JLabel();
        txtPuntos = new javax.swing.JTextField();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblEquipoJugador = new javax.swing.JTable();
        cbEquipoJugador = new javax.swing.JComboBox<>();
        jLabel69 = new javax.swing.JLabel();
        btnAgregarEquipoJugador = new javax.swing.JButton();
        btnEliminarEquipoJugador = new javax.swing.JButton();
        cbEquipoAficionado = new javax.swing.JComboBox<>();
        jLabel81 = new javax.swing.JLabel();
        btnEquipoAficionado = new javax.swing.JButton();
        btnEliminarEquipoAficionado = new javax.swing.JButton();
        jScrollPane12 = new javax.swing.JScrollPane();
        tblEquipoAficionado = new javax.swing.JTable();
        cbEquipoTecnico = new javax.swing.JComboBox<>();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        cbEquipoManager = new javax.swing.JComboBox<>();
        btnEquipoTecnico = new javax.swing.JButton();
        btnEquipoManager = new javax.swing.JButton();
        jLabel88 = new javax.swing.JLabel();
        txtPromedioEdad = new javax.swing.JTextField();
        jScrollPane16 = new javax.swing.JScrollPane();
        tblEquipoPartidos = new javax.swing.JTable();
        jLabel92 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jTabbedPane1.setBackground(new java.awt.Color(0, 255, 255));

        jPanel2.setBackground(new java.awt.Color(0, 255, 255));

        jLabel5.setText("Cedula");

        jLabel6.setText("Nombre");

        jLabel7.setText("Apellido");

        jLabel8.setText("Edad");

        checkFIFA.setBackground(new java.awt.Color(0, 255, 255));
        checkFIFA.setText("FIFA");

        btnCrearArbitro.setText("Crear");
        btnCrearArbitro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearArbitroActionPerformed(evt);
            }
        });

        btnEditarArbitro.setText("Editar");
        btnEditarArbitro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarArbitroActionPerformed(evt);
            }
        });

        btnBuscarArbitro.setText("Buscar");
        btnBuscarArbitro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarArbitroActionPerformed(evt);
            }
        });

        btnEliminarArbitro.setText("Eliminar");
        btnEliminarArbitro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarArbitroActionPerformed(evt);
            }
        });

        jLabel9.setText("Lista Arbitros");

        jLabel60.setText("Arbitros");

        tblArbitros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane8.setViewportView(tblArbitros);

        btnCertificado.setText("Certificado");
        btnCertificado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCertificadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(108, 108, 108)
                .addComponent(btnCrearArbitro)
                .addGap(18, 18, 18)
                .addComponent(btnEditarArbitro)
                .addGap(31, 31, 31)
                .addComponent(btnBuscarArbitro)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(1044, 1337, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(542, 542, 542))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel60)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(checkFIFA)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtEdadArbitro, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(txtCedulaArbitro, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(54, 54, 54)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNombreArbitro, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnEliminarArbitro)
                            .addComponent(txtApellidoArbitro, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCertificado, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 1016, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel60)
                .addGap(3, 3, 3)
                .addComponent(jLabel9)
                .addGap(36, 36, 36)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCedulaArbitro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(txtNombreArbitro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEdadArbitro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(txtApellidoArbitro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addComponent(checkFIFA)
                        .addGap(64, 64, 64)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCrearArbitro)
                            .addComponent(btnEditarArbitro)
                            .addComponent(btnBuscarArbitro)
                            .addComponent(btnEliminarArbitro)))
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCertificado)
                .addContainerGap(567, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Arbitro", jPanel2);

        jPanel8.setBackground(new java.awt.Color(0, 255, 255));

        jLabel56.setText("Numero");

        jLabel57.setText("Slogan");

        btnCrearJornada.setText("Crear");
        btnCrearJornada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearJornadaActionPerformed(evt);
            }
        });

        btnEditarJornada.setText("Editar");
        btnEditarJornada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarJornadaActionPerformed(evt);
            }
        });

        btnBuscarJornada.setText("Buscar");
        btnBuscarJornada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarJornadaActionPerformed(evt);
            }
        });

        btnEliminarJornada.setText("Eliminar");
        btnEliminarJornada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarJornadaActionPerformed(evt);
            }
        });

        jLabel58.setText("Jornada");

        jLabel59.setText("Lista Jornada");

        tblJornada.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblJornada);

        jLabel78.setText("Partido");

        cbJornadaEquipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        tblJornadaPartido.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane14.setViewportView(tblJornadaPartido);

        jLabel79.setText("Lista Partidos");

        btnAgregarJornadaPartido.setText("[+] Agregar");
        btnAgregarJornadaPartido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarJornadaPartidoActionPerformed(evt);
            }
        });

        btnEliminarJornadaPartido.setText("[-] Eliminar");
        btnEliminarJornadaPartido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarJornadaPartidoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(236, 236, 236)
                .addComponent(jLabel58)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel59)
                .addGap(610, 610, 610))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel56)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNumeroJornada, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(jLabel57)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSloganJornada, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(btnCrearJornada)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnEditarJornada)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnBuscarJornada)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnEliminarJornada))
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(jLabel78)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cbJornadaEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(10, 10, 10)))
                        .addGap(22, 22, 22))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(147, 147, 147)
                        .addComponent(btnAgregarJornadaPartido)
                        .addGap(39, 39, 39)
                        .addComponent(btnEliminarJornadaPartido)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1171, Short.MAX_VALUE)
                    .addComponent(jScrollPane14))
                .addGap(177, 177, 177))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel79)
                .addGap(600, 600, 600))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jLabel58))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel59)))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSloganJornada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel57)
                            .addComponent(txtNumeroJornada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel56))
                        .addGap(117, 117, 117)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEliminarJornada)
                            .addComponent(btnBuscarJornada)
                            .addComponent(btnEditarJornada)
                            .addComponent(btnCrearJornada)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(jLabel79)
                        .addGap(36, 36, 36)
                        .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(170, 170, 170)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbJornadaEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel78)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(221, 221, 221)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAgregarJornadaPartido)
                            .addComponent(btnEliminarJornadaPartido))))
                .addContainerGap(296, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Jornada", jPanel8);

        jPanel4.setBackground(new java.awt.Color(0, 255, 255));

        jLabel29.setText("Estadio");

        jLabel30.setText("Nombre");

        jLabel31.setText("Capacidad");

        jLabel32.setText("Ciudad");

        btnCrearEstadio.setText("Crear");
        btnCrearEstadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearEstadioActionPerformed(evt);
            }
        });

        btnEditarEstadio.setText("Editar");
        btnEditarEstadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarEstadioActionPerformed(evt);
            }
        });

        btnBuscarEstadio.setText("Buscar");
        btnBuscarEstadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarEstadioActionPerformed(evt);
            }
        });

        btnEliminarEstadio.setText("Eliminar");
        btnEliminarEstadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarEstadioActionPerformed(evt);
            }
        });

        jLabel33.setText("Lista Estadios");

        tblTablaEstadios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(tblTablaEstadios);

        jLabel62.setText("Id");

        tblEstadioPartido.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane13.setViewportView(tblEstadioPartido);

        cbEstadioPartido.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel73.setText("Partido");

        btnEstadioPartido.setText("[+] Agregar");
        btnEstadioPartido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEstadioPartidoActionPerformed(evt);
            }
        });

        btnEliminarEstadioPartido.setText("[-] Eliminar");
        btnEliminarEstadioPartido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarEstadioPartidoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(243, 243, 243)
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel33)
                .addGap(539, 539, 539))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap(336, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel30)
                                    .addComponent(jLabel62)
                                    .addComponent(jLabel32))
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCrearEstadio)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnEditarEstadio)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnBuscarEstadio)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnEliminarEstadio)
                                        .addGap(124, 124, 124))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(25, 25, 25)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(jPanel4Layout.createSequentialGroup()
                                                    .addComponent(txtIdEstadio, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jLabel31)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(txtCapacidad, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(txtNombreEstadio, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(cbEstadioPartido, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(38, 38, 38)))
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1008, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jLabel73))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(192, 192, 192)
                        .addComponent(btnEstadioPartido)
                        .addGap(37, 37, 37)
                        .addComponent(btnEliminarEstadioPartido, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 1008, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(84, 84, 84))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(jLabel33))
                .addGap(47, 47, 47)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIdEstadio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel62)
                            .addComponent(txtCapacidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel31))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNombreEstadio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel32)
                                    .addComponent(txtCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnEliminarEstadio)
                                    .addComponent(btnBuscarEstadio)
                                    .addComponent(btnEditarEstadio)
                                    .addComponent(btnCrearEstadio))))))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbEstadioPartido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel73))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEstadioPartido)
                            .addComponent(btnEliminarEstadioPartido))))
                .addContainerGap(320, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Estadio", jPanel4);

        jPanel6.setBackground(new java.awt.Color(0, 255, 255));

        jLabel39.setText("Jugador");

        jLabel40.setText("Cedula");

        jLabel41.setText("Apellido");

        jLabel42.setText("Nacionalidad");

        jLabel43.setText("Edad");

        jLabel44.setText("Nombre");

        jLabel45.setText("Posicion");

        jLabel46.setText("Goles Marcados");

        jLabel47.setText("Salario");

        txtGolesMarcados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGolesMarcadosActionPerformed(evt);
            }
        });

        jLabel48.setText("Lista Jugadores");

        btnCrearJugador.setText("Crear");
        btnCrearJugador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearJugadorActionPerformed(evt);
            }
        });

        btnEditarJugador.setText("Editar");
        btnEditarJugador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarJugadorActionPerformed(evt);
            }
        });

        btnBuscarJugador.setText("Buscar");
        btnBuscarJugador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarJugadorActionPerformed(evt);
            }
        });

        btnEliminarJugador.setText("Eliminar");
        btnEliminarJugador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarJugadorActionPerformed(evt);
            }
        });

        tblJugadores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tblJugadores);

        jLabel75.setText("Equipo");

        cbJugadorEquipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel76.setText("Manager");

        cbJugadorManager.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnCertificadoJugador.setText("Certificado");
        btnCertificadoJugador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCertificadoJugadorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(292, 292, 292)
                        .addComponent(jLabel39)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addContainerGap(323, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addComponent(btnCrearJugador)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnEditarJugador)
                                .addGap(18, 18, 18)
                                .addComponent(btnBuscarJugador)
                                .addGap(18, 18, 18)
                                .addComponent(btnEliminarJugador)
                                .addGap(286, 286, 286))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel40, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel41, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel42, javax.swing.GroupLayout.Alignment.TRAILING))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtCedulaJugador, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(txtGolesMarcados, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(txtNacionalidad, javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtApellidoJugador, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE))))
                                        .addGap(73, 73, 73)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel44)
                                            .addComponent(jLabel43)
                                            .addComponent(jLabel47)
                                            .addComponent(jLabel45))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtPosicionJugador, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtNombreJugador, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtEdadJugador, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtSalarioJugador, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jLabel46))
                                .addGap(167, 167, 167)))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 951, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCertificadoJugador, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel48)
                .addGap(351, 351, 351))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(185, 185, 185)
                .addComponent(jLabel75)
                .addGap(31, 31, 31)
                .addComponent(cbJugadorEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(187, 187, 187)
                .addComponent(jLabel76)
                .addGap(37, 37, 37)
                .addComponent(cbJugadorManager, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(jLabel39)
                .addGap(24, 24, 24)
                .addComponent(jLabel48)
                .addGap(30, 30, 30)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNombreJugador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCedulaJugador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel40)
                            .addComponent(jLabel44))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtApellidoJugador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPosicionJugador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel41)
                            .addComponent(jLabel45))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNacionalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEdadJugador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel43)
                            .addComponent(jLabel42))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSalarioJugador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel47)
                            .addComponent(txtGolesMarcados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel46))
                        .addGap(64, 64, 64)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEliminarJugador)
                            .addComponent(btnBuscarJugador)
                            .addComponent(btnEditarJugador)
                            .addComponent(btnCrearJugador))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCertificadoJugador)
                .addGap(127, 127, 127)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbJugadorEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel76)
                    .addComponent(cbJugadorManager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel75))
                .addContainerGap(358, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Jugador", jPanel6);

        jPanel1.setBackground(new java.awt.Color(0, 255, 255));

        jLabel1.setText("Nombre");

        jLabel2.setText("Edad");

        jLabel3.setText("Cedula");

        jLabel4.setText("Años Fidelidad");

        checkAbonado.setBackground(new java.awt.Color(0, 255, 255));
        checkAbonado.setText("Abonado");

        btnCrearAficionado.setText("Crear");
        btnCrearAficionado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearAficionadoActionPerformed(evt);
            }
        });

        btnEditarAficionado.setText("Editar");
        btnEditarAficionado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarAficionadoActionPerformed(evt);
            }
        });

        btnBuscarAficionado.setText("Buscar");
        btnBuscarAficionado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarAficionadoActionPerformed(evt);
            }
        });

        btnEliminarAficionado.setText("Eliminar");
        btnEliminarAficionado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarAficionadoActionPerformed(evt);
            }
        });

        jLabel10.setText("Lista de aficionados");

        jLabel21.setText("Aficionado");

        Jlabel.setText("Apellido");

        tblAficionados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(tblAficionados);

        jLabel80.setText("Equipo");

        cbAficionadoEquipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnCertificadoAficionado.setText("Certificado");
        btnCertificadoAficionado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCertificadoAficionadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(340, 340, 340)
                        .addComponent(jLabel21))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel80)
                                .addGap(18, 18, 18)
                                .addComponent(cbAficionadoEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(Jlabel)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtApellidoAficionado, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(30, 30, 30)
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtFidelidadAficionado))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtEdadAficionado, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(checkAbonado))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtCedulaAficionado, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtNombreAficionado, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(233, 233, 233)
                        .addComponent(btnCrearAficionado)
                        .addGap(18, 18, 18)
                        .addComponent(btnEditarAficionado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBuscarAficionado)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminarAficionado)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 310, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCertificadoAficionado, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel10)
                            .addGap(553, 553, 553))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 939, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(77, 77, 77)))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel21))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCedulaAficionado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1)
                            .addComponent(txtNombreAficionado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Jlabel)
                            .addComponent(txtFidelidadAficionado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(txtApellidoAficionado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEdadAficionado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(checkAbonado))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel80)
                            .addComponent(cbAficionadoEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(75, 75, 75)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCrearAficionado)
                            .addComponent(btnEditarAficionado)
                            .addComponent(btnBuscarAficionado)
                            .addComponent(btnEliminarAficionado)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCertificadoAficionado)))
                .addContainerGap(568, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Aficionado", jPanel1);

        jPanel7.setBackground(new java.awt.Color(0, 255, 255));

        jLabel49.setText("Manager");

        jLabel50.setText("Cedula");

        jLabel51.setText("Apellido");

        jLabel52.setText("Años afiliacion");

        jLabel53.setText("Nombre");

        jLabel54.setText("Edad");

        btnCrearManager.setText("Crear");
        btnCrearManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearManagerActionPerformed(evt);
            }
        });

        btnEditarManager.setText("Editar");
        btnEditarManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarManagerActionPerformed(evt);
            }
        });

        btnBuscarManager.setText("Buscar");
        btnBuscarManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarManagerActionPerformed(evt);
            }
        });

        btnEliminarManager.setText("Eliminar");
        btnEliminarManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarManagerActionPerformed(evt);
            }
        });

        jLabel55.setText("Lista Managers");

        tblManagers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane7.setViewportView(tblManagers);

        jLabel66.setText("Lista Jugadores");

        tblRelacionJugadoresManager.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane9.setViewportView(tblRelacionJugadoresManager);

        cbJugadoresManager.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnAñadirRelacionJugadorManager.setText("[+] Añadir");
        btnAñadirRelacionJugadorManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAñadirRelacionJugadorManagerActionPerformed(evt);
            }
        });

        btnDesligarManagerJugador.setText("[-] Desligar");
        btnDesligarManagerJugador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDesligarManagerJugadorActionPerformed(evt);
            }
        });

        jLabel67.setText("Jugador");

        jLabel68.setText("Equipo");

        cbManagerEquipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton15.setText("Certificado");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(231, 231, 231)
                .addComponent(jLabel49)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel55)
                .addGap(551, 551, 551))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel66)
                .addGap(576, 576, 576))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel67))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel51)
                            .addComponent(jLabel50)
                            .addComponent(jLabel52)
                            .addComponent(jLabel68))))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(btnAñadirRelacionJugadorManager, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(btnDesligarManagerJugador, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(118, 118, 118))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cbJugadoresManager, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addComponent(btnCrearManager)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnEditarManager)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnBuscarManager)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnEliminarManager))
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtCedulaManager, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtApellidoManager, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtAñosAfiliacion, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cbManagerEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(27, 27, 27)
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel54)
                                        .addComponent(jLabel53))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtEdadManager, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtNombreManager, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(54, 54, 54)))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 1079, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 1079, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(317, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel49)
                        .addGap(71, 71, 71)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel50)
                            .addComponent(txtCedulaManager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombreManager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel53))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEdadManager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel54)
                            .addComponent(txtApellidoManager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel51))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtAñosAfiliacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel52))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbManagerEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel68))
                        .addGap(47, 47, 47))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel55)
                        .addGap(37, 37, 37)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 129, Short.MAX_VALUE)
                        .addComponent(jLabel66)
                        .addGap(30, 30, 30)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(288, 288, 288))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton15)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnCrearManager)
                                .addComponent(btnEditarManager)
                                .addComponent(btnBuscarManager)
                                .addComponent(btnEliminarManager)))
                        .addGap(165, 165, 165)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel67)
                            .addComponent(cbJugadoresManager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnDesligarManagerJugador)
                            .addComponent(btnAñadirRelacionJugadorManager))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jTabbedPane1.addTab("Manager", jPanel7);

        jPanel9.setBackground(new java.awt.Color(0, 255, 255));

        jLabel14.setText("Tecnico");

        jLabel22.setText("Cedula");

        jLabel23.setText("Apellido");

        jLabel24.setText("Nombre");

        jLabel25.setText("Edad");

        jLabel26.setText("Años experiencia");

        jLabel27.setText("Salario");

        jLabel28.setText("Lista Tecnicos");

        btnCrearTecnico.setText("Crear");
        btnCrearTecnico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearTecnicoActionPerformed(evt);
            }
        });

        btnEditarTecnico.setText("Editar");
        btnEditarTecnico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarTecnicoActionPerformed(evt);
            }
        });

        btnBuscarTecnico.setText("Buscar");
        btnBuscarTecnico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarTecnicoActionPerformed(evt);
            }
        });

        btnEliminarTecnico.setText("Eliminar");
        btnEliminarTecnico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarTecnicoActionPerformed(evt);
            }
        });

        tblTecnicos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane6.setViewportView(tblTecnicos);

        jLabel77.setText("Equipo");

        cbTecnicoEquipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbTecnicoEquipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTecnicoEquipoActionPerformed(evt);
            }
        });

        btnCertificadoTecnico.setText("Cetificado");
        btnCertificadoTecnico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCertificadoTecnicoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jLabel77)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(204, 204, 204)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel28)
                        .addGap(629, 629, 629))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(204, 204, 204)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addComponent(jLabel24)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtNombreTecnico, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addComponent(jLabel27)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtSalario, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addComponent(jLabel25)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtEdadTecnico, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnEditarTecnico)
                                    .addComponent(cbTecnicoEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(btnBuscarTecnico)
                                .addGap(18, 18, 18)
                                .addComponent(btnEliminarTecnico)))
                        .addGap(58, 58, 58)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 1251, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCertificadoTecnico, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel23)
                                    .addComponent(jLabel22))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtApellidoTecnico, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                                    .addComponent(txtCedulaTecnico)))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addGap(18, 18, 18)
                                .addComponent(txtAñosExperiencia, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addComponent(btnCrearTecnico)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel14)
                .addGap(44, 44, 44)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jLabel24)
                    .addComponent(txtCedulaTecnico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombreTecnico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(jLabel23)
                    .addComponent(txtApellidoTecnico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEdadTecnico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(jLabel26)
                    .addComponent(txtAñosExperiencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSalario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel77)
                    .addComponent(cbTecnicoEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(67, 67, 67)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCrearTecnico)
                    .addComponent(btnEditarTecnico)
                    .addComponent(btnBuscarTecnico)
                    .addComponent(btnEliminarTecnico))
                .addGap(18, 18, Short.MAX_VALUE))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(58, Short.MAX_VALUE)
                .addComponent(jLabel28)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCertificadoTecnico)
                .addGap(561, 561, 561))
        );

        jTabbedPane1.addTab("Tecnico", jPanel9);

        jPanel10.setBackground(new java.awt.Color(0, 255, 255));

        jLabel70.setText("Jugador Mas Joven de la Liga");

        jLabel87.setText("Jugador que ha marcardo mas goles");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(0, 705, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtGoleadorLiga, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtJugadorJovenLiga, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(711, 711, 711))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(757, 757, 757)
                        .addComponent(jLabel70))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(742, 742, 742)
                        .addComponent(jLabel87)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(232, 232, 232)
                .addComponent(jLabel87)
                .addGap(54, 54, 54)
                .addComponent(txtGoleadorLiga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(jLabel70)
                .addGap(34, 34, 34)
                .addComponent(txtJugadorJovenLiga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(494, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("metodos", jPanel10);

        jPanel5.setBackground(new java.awt.Color(0, 255, 255));

        jLabel34.setText("Resultado Partido");

        jLabel35.setText("Fecha");

        jLabel36.setText("Equipo Local");

        jLabel37.setText("Equipo Visitante");

        btnCrearPartido.setText("Crear");
        btnCrearPartido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearPartidoActionPerformed(evt);
            }
        });

        btnEditarPartido.setText("Editar");
        btnEditarPartido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarPartidoActionPerformed(evt);
            }
        });

        btnBuscarPartido.setText("Buscar");
        btnBuscarPartido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarPartidoActionPerformed(evt);
            }
        });

        btnEliminarPartido.setText("Eliminar");
        btnEliminarPartido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarPartidoActionPerformed(evt);
            }
        });

        txtFechaPartido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaPartidoActionPerformed(evt);
            }
        });

        jLabel38.setText("Lista Partidos");

        tblPartido.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane11.setViewportView(tblPartido);

        cbEquipoLocal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbEquipoVisita.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel64.setText("Goles Local");

        jLabel65.setText("Goles Visita");

        cbGolesJugadorLocal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel74.setText("Goles Local");

        jLabel71.setText("Goles");

        jLabel84.setText("Goles Visita");

        cbGolesJugadorVisita.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel85.setText("Goles");

        btnAñadirGolLocal.setText("Añadir");
        btnAñadirGolLocal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAñadirGolLocalActionPerformed(evt);
            }
        });

        jLabel86.setText("Id");

        btnAñadirGolVisita.setText("Añadir");
        btnAñadirGolVisita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAñadirGolVisitaActionPerformed(evt);
            }
        });

        btnAsignarEquiposPartido.setText("Asignar");
        btnAsignarEquiposPartido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsignarEquiposPartidoActionPerformed(evt);
            }
        });

        jLabel89.setText("Arbitro");

        cbPartidoArbitro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel90.setText("Estadio");

        jLabel91.setText("Jornada");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel36)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(cbEquipoVisita, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel37)
                                .addGap(18, 18, 18)
                                .addComponent(cbEquipoLocal, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(178, 178, 178))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(129, 129, 129)
                                .addComponent(jLabel89)
                                .addGap(18, 18, 18)
                                .addComponent(cbPartidoArbitro, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel74)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jLabel71)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtGolesJugadorLocal, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbGolesJugadorLocal, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel84)
                                    .addComponent(jLabel85, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(btnAñadirGolLocal)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbGolesJugadorVisita, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGolesJugadorVisita, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAñadirGolVisita))
                        .addGap(202, 202, 202))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addContainerGap(137, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                                .addComponent(jLabel86)
                                                .addGap(34, 34, 34)
                                                .addComponent(txtIdPartido, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                                .addComponent(jLabel64)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtGolesLocal, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                                .addComponent(jLabel91)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtPartidoJornada, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(14, 14, 14)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addGap(25, 25, 25)
                                                .addComponent(jLabel35)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtFechaPartido, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel90)
                                                    .addComponent(jLabel65))
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtGolesVisita, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtPartidoEstadio)))))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel34)
                                        .addGap(150, 150, 150)))
                                .addGap(63, 63, 63))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                .addGap(59, 59, 59)
                                .addComponent(btnCrearPartido)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEditarPartido)
                                .addGap(14, 14, 14)
                                .addComponent(btnBuscarPartido)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEliminarPartido)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 1086, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel38)
                                .addGap(507, 507, 507)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(355, 355, 355)
                .addComponent(btnAsignarEquiposPartido)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(jLabel34)
                        .addGap(51, 51, 51)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel35)
                            .addComponent(txtFechaPartido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel86)
                            .addComponent(txtIdPartido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel64)
                            .addComponent(txtGolesLocal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel65)
                            .addComponent(txtGolesVisita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel90)
                            .addComponent(jLabel91)
                            .addComponent(txtPartidoJornada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPartidoEstadio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(72, 72, 72)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCrearPartido)
                            .addComponent(btnEditarPartido)
                            .addComponent(btnBuscarPartido)
                            .addComponent(btnEliminarPartido)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(jLabel38)
                        .addGap(48, 48, 48)
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbGolesJugadorLocal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel74))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtGolesJugadorLocal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(jLabel71)))
                        .addGap(18, 18, 18)
                        .addComponent(btnAñadirGolLocal)
                        .addGap(20, 20, 20)
                        .addComponent(btnAsignarEquiposPartido))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbEquipoVisita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel36)
                            .addComponent(cbEquipoLocal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel37)
                            .addComponent(jLabel84)
                            .addComponent(cbGolesJugadorVisita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel85)
                                    .addComponent(txtGolesJugadorVisita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnAñadirGolVisita))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbPartidoArbitro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel89))))))
                .addContainerGap(343, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Partido", jPanel5);

        jPanel3.setBackground(new java.awt.Color(0, 255, 255));

        jLabel11.setText("Nombre");

        jLabel12.setText("Titulos internacionales");

        jLabel13.setText("Titulos nacionales");

        jLabel15.setText("Año fundacion");

        jLabel16.setText("Goles a favor");

        jLabel17.setText("Goles en contra");

        jLabel18.setText("Partidos jugados");

        jLabel19.setText("Lista Equipos");

        btnCrearEquipo.setText("Crear");
        btnCrearEquipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearEquipoActionPerformed(evt);
            }
        });

        btnEditarEquipo.setText("Editar");
        btnEditarEquipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarEquipoActionPerformed(evt);
            }
        });

        btnBuscarEquipo.setText("Buscar");
        btnBuscarEquipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarEquipoActionPerformed(evt);
            }
        });

        btnEliminarEquipo.setText("Eliminar");
        btnEliminarEquipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarEquipoActionPerformed(evt);
            }
        });

        jLabel20.setText("Equipo");

        jLabel61.setText("Id");

        txtIdEquipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdEquipoActionPerformed(evt);
            }
        });

        tblEquipos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(tblEquipos);

        jLabel63.setText("Puntos");

        tblEquipoJugador.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane10.setViewportView(tblEquipoJugador);

        cbEquipoJugador.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel69.setText("Jugadores");

        btnAgregarEquipoJugador.setText("[+] Agregar");
        btnAgregarEquipoJugador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarEquipoJugadorActionPerformed(evt);
            }
        });

        btnEliminarEquipoJugador.setText("[-] Eliminar");
        btnEliminarEquipoJugador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarEquipoJugadorActionPerformed(evt);
            }
        });

        cbEquipoAficionado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel81.setText("Aficionados");

        btnEquipoAficionado.setText("[+] Agregar");
        btnEquipoAficionado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEquipoAficionadoActionPerformed(evt);
            }
        });

        btnEliminarEquipoAficionado.setText("[-] Eliminar");
        btnEliminarEquipoAficionado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarEquipoAficionadoActionPerformed(evt);
            }
        });

        tblEquipoAficionado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane12.setViewportView(tblEquipoAficionado);

        cbEquipoTecnico.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel82.setText("Tecnico");

        jLabel83.setText("Manager");

        cbEquipoManager.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnEquipoTecnico.setText("Selecionar");
        btnEquipoTecnico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEquipoTecnicoActionPerformed(evt);
            }
        });

        btnEquipoManager.setText("Seleccionar");
        btnEquipoManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEquipoManagerActionPerformed(evt);
            }
        });

        jLabel88.setText("Promedio");

        tblEquipoPartidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane16.setViewportView(tblEquipoPartidos);

        jLabel92.setText("Partidos");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(288, 288, 288)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel19)
                .addGap(618, 618, 618))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(286, 286, 286)
                .addComponent(jLabel82)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbEquipoTecnico, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(157, 157, 157)
                .addComponent(jLabel83)
                .addGap(18, 18, 18)
                .addComponent(cbEquipoManager, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(511, 511, 511)
                .addComponent(btnEquipoTecnico)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnEquipoManager)
                .addGap(401, 401, 401))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                                        .addGap(14, 14, 14)
                                                        .addComponent(jLabel61))
                                                    .addComponent(jLabel11))
                                                .addGap(24, 24, 24)
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(txtIdEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtNombreEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                                        .addComponent(jLabel12)
                                                        .addGap(0, 18, Short.MAX_VALUE))
                                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jLabel63)
                                                            .addComponent(jLabel16))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtGolesFavor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(txtPuntos, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(txtTitulosInternacionales, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(26, 26, 26)))
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                    .addGap(42, 42, 42)
                                                    .addComponent(jLabel15))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                                    .addGap(36, 36, 36)
                                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jLabel18)
                                                            .addComponent(jLabel17))
                                                        .addComponent(jLabel88, javax.swing.GroupLayout.Alignment.TRAILING))))
                                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING))
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtPartidosJugados, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtGolesContra, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtAñoFundacion, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtPromedioEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                                .addGap(10, 10, 10)
                                                .addComponent(txtTitulosNacionales, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(157, 157, 157)
                                        .addComponent(btnCrearEquipo)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnBuscarEquipo)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnEditarEquipo)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnEliminarEquipo)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(53, 53, 53)))
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(223, 223, 223)
                                .addComponent(jLabel92)))
                        .addGap(51, 51, 51)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 996, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 59, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel69)
                                        .addGap(35, 35, 35)
                                        .addComponent(cbEquipoJugador, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel81)
                                        .addGap(7, 7, 7))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(btnAgregarEquipoJugador, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnEliminarEquipoJugador, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(259, 259, 259)))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(69, 69, 69)
                                        .addComponent(btnEquipoAficionado)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnEliminarEquipoAficionado))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addComponent(cbEquipoAficionado, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(59, 59, 59))))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jLabel19))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTitulosNacionales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtAñoFundacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtGolesContra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtIdEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel61))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(txtNombreEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12)
                                    .addComponent(txtTitulosInternacionales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel18)
                                    .addComponent(txtPartidosJugados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPuntos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel63))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtGolesFavor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel88)
                                    .addComponent(txtPromedioEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnBuscarEquipo)
                                    .addComponent(btnCrearEquipo))
                                .addGap(0, 2, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnEliminarEquipo, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnEditarEquipo, javax.swing.GroupLayout.Alignment.TRAILING))))))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(jLabel92)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane16, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbEquipoJugador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel69)
                            .addComponent(cbEquipoAficionado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel81))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAgregarEquipoJugador)
                            .addComponent(btnEliminarEquipoJugador)
                            .addComponent(btnEquipoAficionado)
                            .addComponent(btnEliminarEquipoAficionado))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                            .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addGap(52, 52, 52)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbEquipoTecnico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbEquipoManager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel83)
                    .addComponent(jLabel82))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEquipoTecnico)
                    .addComponent(btnEquipoManager))
                .addGap(66, 66, 66))
        );

        jTabbedPane1.addTab("Equipo", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1680, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 954, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtFechaPartidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaPartidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaPartidoActionPerformed

    private void btnEliminarManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarManagerActionPerformed
        String cedula = this.txtCedulaManager.getText();
        Manager buscarManager = this.miLiga.buscarManager(cedula);
        if(buscarManager == null){
            JOptionPane.showMessageDialog(this, "Jugador no encontrado");
        }else{
            this.miLiga.getMisPersonas().remove(buscarManager);
            limpiarCajas();
            actualizarTablaManagers();
            JOptionPane.showMessageDialog(this, "Manager eliminado");
        }
    }//GEN-LAST:event_btnEliminarManagerActionPerformed

    private void btnBuscarManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarManagerActionPerformed
        String cedula = this.txtCedulaManager.getText();
        Manager buscarManager = this.miLiga.buscarManager(cedula);
        if(buscarManager == null){
            JOptionPane.showMessageDialog(this, "Manager no existe");
        }else{
            this.txtNombreManager.setText(buscarManager.getNombre());
            this.txtApellidoManager.setText(buscarManager.getApellido());
            this.txtEdadManager.setText(Integer.toString(buscarManager.getEdad()));
            this.txtAñosAfiliacion.setText(Integer.toString(buscarManager.getAniosAfiliacion()));
            this.cbManagerEquipo.setEnabled(false);
            actualizarTablaRelacionJugadoresManager();
            actualizarComboManagerEquipo();
            JOptionPane.showMessageDialog(this, "Manager encontrado");
        }
    }//GEN-LAST:event_btnBuscarManagerActionPerformed

    private void btnEditarManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarManagerActionPerformed
        String cedula = this.txtCedulaManager.getText();
        Manager buscarManager = this.miLiga.buscarManager(cedula);
        if(buscarManager == null){
            JOptionPane.showMessageDialog(this, "Jugador no encontrado");
        }else{
            buscarManager.setNombre(this.txtNombreManager.getText());
            buscarManager.setApellido(this.txtApellidoManager.getText());
            buscarManager.setEdad(Integer.parseInt(this.txtEdadManager.getText()));
            buscarManager.setAniosAfiliacion(Integer.parseInt(this.txtAñosAfiliacion.getText()));
            limpiarCajas();
            actualizarTablaManagers();
            JOptionPane.showMessageDialog(this, "Manager Editado");
            actualizarComboEquipoManager();
        }
    }//GEN-LAST:event_btnEditarManagerActionPerformed

    private void btnCrearManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearManagerActionPerformed
        String cedula = this.txtCedulaManager.getText();
        String nombre = this.txtNombreManager.getText();
        String apellido = this.txtApellidoManager.getText();
        int edad = Integer.parseInt(this.txtEdadManager.getText());
        int aniosAfiliacion = Integer.parseInt(this.txtAñosAfiliacion.getText());
        boolean personaExistente = this.miLiga.verificarPersonExiste(cedula);
        if(!personaExistente){
            Persona nuevoManager = new Manager(aniosAfiliacion, cedula, nombre, apellido, edad);
            this.miLiga.getMisPersonas().add(nuevoManager);
            limpiarCajas();
            actualizarTablaManagers();
            JOptionPane.showMessageDialog(this, "Manager creado");
            actualizarComboEquipoManager();
        }else{
            JOptionPane.showMessageDialog(this, "La cedula ingresada ya existe");
        }

    }//GEN-LAST:event_btnCrearManagerActionPerformed

    private void txtIdEquipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdEquipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdEquipoActionPerformed

    private void btnEliminarEquipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarEquipoActionPerformed
        String id = this.txtIdEquipo.getText();
        Equipo equipoEncontrado = this.miLiga.buscarEquipo(id);

        if(equipoEncontrado == null){
            JOptionPane.showMessageDialog(this, "No existe");
        }else{
            this.miLiga.eliminarEquipo(equipoEncontrado);
            limpiarCajas();
            actualizarTablaEquipo();
            actualizaComboPartidoEquipoVisitante();
            actualizarComboPartidoEquipoLocal();
            JOptionPane.showMessageDialog(this, "Equipo Eliminado");
        }
    }//GEN-LAST:event_btnEliminarEquipoActionPerformed

    private void btnBuscarEquipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarEquipoActionPerformed
         try {
            String id = this.txtIdEquipo.getText();
            Equipo equipoBuscado = this.miLiga.buscarEquipo(id);
            if(equipoBuscado == null){
                JOptionPane.showConfirmDialog(this, "No se encontro al equipo");
            }else{
                this.txtNombreEquipo.setText(equipoBuscado.getNombre());
                this.txtAñoFundacion.setText(Integer.toString(equipoBuscado.getAnioFundacion()));
                this.txtGolesFavor.setText(Integer.toString(equipoBuscado.getGolesAFavor()));
                this.txtGolesContra.setText(Integer.toString(equipoBuscado.getGolesEnContra()));
                this.txtTitulosInternacionales.setText(Integer.toString(equipoBuscado.getNumeroTitulosInternacionales()));
                this.txtTitulosNacionales.setText(Integer.toString(equipoBuscado.getNumeroTitulosNacionales()));
                this.txtPartidosJugados.setText(Integer.toString(equipoBuscado.getPartidosJugados()));
                this.txtPuntos.setText(Integer.toString(equipoBuscado.getPuntos()));
                double promedio = equipoBuscado.promedioEdad();
                this.txtPromedioEdad.setText(""+ promedio );
                actualizarTablaEquipoJugador();
                actualizarTablaEquipoAficionado();
                acualizarTablaEquipoPartidos();
                JOptionPane.showMessageDialog(this, "Equipo Encontrado");
            }
        } catch (Exception e) {
            System.out.println(""+e);
        }
    }//GEN-LAST:event_btnBuscarEquipoActionPerformed

    private void btnEditarEquipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarEquipoActionPerformed
        String id = this.txtIdEquipo.getText();
        Equipo equipoEncontrado = this.miLiga.buscarEquipo(id);

        if(equipoEncontrado == null){
            JOptionPane.showMessageDialog(this, "No se puede editar");
        }else{
            String nombre = this.txtNombreEquipo.getText();
            int anioFundacion = Integer.parseInt(this.txtAñoFundacion.getText());
            int golesAFavor= Integer.parseInt(this.txtGolesFavor.getText());
            int golesEnContra = Integer.parseInt(this.txtGolesContra.getText());
            int titulosInternacionales = Integer.parseInt(this.txtTitulosInternacionales.getText());
            int titulosNacionales = Integer.parseInt(this.txtTitulosNacionales.getText());
            int partidosJugados = Integer.parseInt(this.txtPartidosJugados.getText());
            int puntos = Integer.parseInt(txtPuntos.getText());
            equipoEncontrado.setNombre(nombre);
            equipoEncontrado.setAnioFundacion(anioFundacion);
            equipoEncontrado.setGolesAFavor(golesAFavor);
            equipoEncontrado.setGolesAFavor(golesAFavor);
            equipoEncontrado.setNumeroTitulosInternacionales(titulosInternacionales);
            equipoEncontrado.setNumeroTitulosNacionales(titulosNacionales);
            equipoEncontrado.setPartidosJugados(partidosJugados);
            equipoEncontrado.setPuntos(puntos);
            actualizarTablaEquipo();
            limpiarCajas();
            actualizaComboPartidoEquipoVisitante();
            actualizarComboPartidoEquipoLocal();
            JOptionPane.showMessageDialog(this, "Equipo editado");
        }
    }//GEN-LAST:event_btnEditarEquipoActionPerformed

    private void btnCrearEquipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearEquipoActionPerformed
        String id = this.txtIdEquipo.getText();
        String nombre = this.txtNombreEquipo.getText();
        int anioFundacion = Integer.parseInt(this.txtAñoFundacion.getText());
        int titulosInternacionales = Integer.parseInt(this.txtTitulosInternacionales.getText());
        int titulosNacionales = Integer.parseInt(this.txtTitulosNacionales.getText());
        this.miLiga.crearEquipo(id, nombre, anioFundacion, titulosNacionales, titulosInternacionales,0, 0, 0, 0);
        actualizarTablaEquipo();
        actualizaComboPartidoEquipoVisitante();
        actualizarComboPartidoEquipoLocal();
        limpiarCajas();
        JOptionPane.showMessageDialog(this, "Equipo Creado");
    }//GEN-LAST:event_btnCrearEquipoActionPerformed

    private void btnEliminarEstadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarEstadioActionPerformed
        String id = this.txtIdEstadio.getText();
        Estadio estadioEncontrado = this.miLiga.buscarEstadio(id);
        if(estadioEncontrado == null){
            JOptionPane.showMessageDialog(this, "Estadio no existente");
        }else{
            this.miLiga.getMisEstadios().remove(estadioEncontrado);
            actualizarTablaEstadio();
            
            JOptionPane.showMessageDialog(this, "Estadio eliminado");
        }
    }//GEN-LAST:event_btnEliminarEstadioActionPerformed

    private void btnBuscarEstadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarEstadioActionPerformed
        String id = this.txtIdEstadio.getText();
        Estadio estadioBuscado = this.miLiga.buscarEstadio(id);
        if(estadioBuscado == null){
            JOptionPane.showMessageDialog(this, "Estadio no encontrado");
        }else{
            this.txtNombreEstadio.setText(estadioBuscado.getNombre());
            this.txtCapacidad.setText(Double.toString(estadioBuscado.getCapacidad()));
            this.txtCiudad.setText(estadioBuscado.getCiudad());
            actualizarTablaEstadioPartido();
            JOptionPane.showMessageDialog(this, "Estadio encontrado");
        }
    }//GEN-LAST:event_btnBuscarEstadioActionPerformed

    private void btnEditarEstadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarEstadioActionPerformed
        String id = this.txtIdEstadio.getText();
        Estadio estadioEncontrado = this.miLiga.buscarEstadio(id);
        if(estadioEncontrado == null){
            JOptionPane.showMessageDialog(this, "Estadio no existente");
        }else{
            estadioEncontrado.setCapacidad(Double.parseDouble(this.txtCapacidad.getText()));
            estadioEncontrado.setCiudad(this.txtCiudad.getText());
            estadioEncontrado.setNombre(this.txtNombreEstadio.getText());
            limpiarCajas();
            actualizarTablaEstadio();
            JOptionPane.showMessageDialog(this, "Estadio editado");
        }
    }//GEN-LAST:event_btnEditarEstadioActionPerformed

    private void btnCrearEstadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearEstadioActionPerformed
        String id = this.txtIdEstadio.getText();
        String nombre = this.txtNombreEstadio.getText();
        int capacidad = Integer.parseInt(this.txtCapacidad.getText());
        String ciudad = this.txtCiudad.getText();
        Estadio nuevoEstadio = new Estadio(id, nombre, ciudad, capacidad);
        this.miLiga.getMisEstadios().add(nuevoEstadio);
        JOptionPane.showMessageDialog(this, "Estadio creado");
        actualizarTablaEstadio();
        limpiarCajas();
    }//GEN-LAST:event_btnCrearEstadioActionPerformed

    private void btnEliminarAficionadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarAficionadoActionPerformed
        String cedula = this.txtCedulaAficionado.getText();
        Aficionado aficionadoBuscar = this.miLiga.buscarAficionado(cedula);
        if(aficionadoBuscar == null){
            JOptionPane.showMessageDialog(this, "Aficionado no encontrado");
        }else{
            this.miLiga.getMisPersonas().remove(aficionadoBuscar);
            actualizarTablaAficionado();
            limpiarCajas();
            JOptionPane.showMessageDialog(this, "Aficionado eliminado");
        }
    }//GEN-LAST:event_btnEliminarAficionadoActionPerformed

    private void btnBuscarAficionadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarAficionadoActionPerformed
        String cedula = this.txtCedulaAficionado.getText();
        Aficionado aficionadoBuscar = this.miLiga.buscarAficionado(cedula);
        if(aficionadoBuscar == null){
            JOptionPane.showMessageDialog(this, "Aficionado no encontrado");
        }else{
            this.txtNombreAficionado.setText(aficionadoBuscar.getNombre());
            this.txtApellidoAficionado.setText(aficionadoBuscar.getApellido());
            this.txtEdadAficionado.setText(Integer.toString(aficionadoBuscar.getEdad()));
            this.txtFidelidadAficionado.setText(Integer.toString(aficionadoBuscar.getAniosFidelidad()));
            this.checkAbonado.setSelected(aficionadoBuscar.isAbonado());
            this.cbAficionadoEquipo.addItem(aficionadoBuscar.getMiEquipo().getNombre());
            JOptionPane.showMessageDialog(this, "Aficionado encontrado");
        }
    }//GEN-LAST:event_btnBuscarAficionadoActionPerformed

    private void btnEditarAficionadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarAficionadoActionPerformed
        String cedula = this.txtCedulaAficionado.getText();
        Aficionado aficionadoBuscar = this.miLiga.buscarAficionado(cedula);
        if(aficionadoBuscar == null){
            JOptionPane.showMessageDialog(this, "Aficionado no encontrado");
        }else{
            aficionadoBuscar.setNombre(this.txtNombreAficionado.getText());
            aficionadoBuscar.setApellido(this.txtApellidoAficionado.getText());
            aficionadoBuscar.setEdad(Integer.parseInt(this.txtEdadAficionado.getText()));
            aficionadoBuscar.setAniosFidelidad(Integer.parseInt(this.txtFidelidadAficionado.getText()));
            aficionadoBuscar.setAbonado(this.checkAbonado.isSelected());
            actualizarTablaAficionado();
            limpiarCajas();
            actualizarComboEquipoAficionado();
            actualizarTablaEquipoAficionado();
            JOptionPane.showMessageDialog(this, "Aficionado Editado");
        }
    }//GEN-LAST:event_btnEditarAficionadoActionPerformed

    private void btnCrearAficionadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearAficionadoActionPerformed
        try {
            String cedula = this.txtCedulaAficionado.getText();
            String nombre = this.txtNombreAficionado.getText();
            String apellido = this.txtApellidoAficionado.getText();
            int edad = Integer.parseInt(this.txtEdadAficionado.getText());
            int aniosFidelidad = Integer.parseInt(this.txtFidelidadAficionado.getText());
            boolean abonado = false;
            if(this.checkAbonado.isSelected()){
                abonado=true;
            }
            boolean personaExistente = this.miLiga.verificarPersonExiste(cedula);
            if(!personaExistente){
                Persona aficionadoNuevo = new Aficionado(aniosFidelidad, abonado, cedula, nombre, apellido, edad);
                this.miLiga.getMisPersonas().add(aficionadoNuevo);
                actualizarTablaAficionado();
                limpiarCajas();
                actualizarComboEquipoAficionado();
                actualizarTablaEquipoAficionado();
                JOptionPane.showMessageDialog(this, "Aficionado creado");
            }else{
                JOptionPane.showMessageDialog(this, "La cedula ingresada ya existe");
            }
            

        } catch (Exception e) {
            System.out.println(""+e);
        }
    }//GEN-LAST:event_btnCrearAficionadoActionPerformed

    private void btnEliminarJugadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarJugadorActionPerformed
        String cedula = this.txtCedulaJugador.getText();
        Jugador jugadorBuscar = this.miLiga.buscarJugador(cedula);
        if(jugadorBuscar == null){
            JOptionPane.showMessageDialog(this, "Jugador no encontrado");
        }else{
            this.miLiga.getMisPersonas().remove(jugadorBuscar);
            actualizarTablaJugadores();
            limpiarCajas();
            actualizarTablaEquipoAficionado();
            JOptionPane.showMessageDialog(this, "Jugador eliminado");
        }
    }//GEN-LAST:event_btnEliminarJugadorActionPerformed

    private void btnBuscarJugadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarJugadorActionPerformed
        String cedula = this.txtCedulaJugador.getText();
        Jugador jugadorBuscar = this.miLiga.buscarJugador(cedula);
        if(jugadorBuscar == null){
            JOptionPane.showMessageDialog(this, "Jugador no encontrado");
        }else{
            this.txtNombreJugador.setText(jugadorBuscar.getNombre());
            this.txtApellidoJugador.setText(jugadorBuscar.getApellido());
            this.txtEdadJugador.setText(Integer.toString(jugadorBuscar.getEdad()));
            this.txtNacionalidad.setText(jugadorBuscar.getNacionalidad());
            this.txtGolesMarcados.setText(Integer.toString(jugadorBuscar.getNumeroGolesMarcados()));
            this.txtPosicionJugador.setText(jugadorBuscar.getPosicion());
            this.txtSalarioJugador.setText(Double.toString(jugadorBuscar.getSalario()));
            this.cbJugadorEquipo.addItem(jugadorBuscar.getMiEquipo().getNombre());
            this.cbJugadorManager.addItem(jugadorBuscar.getMiManager().getNombre()+ " "+ jugadorBuscar.getMiManager().getApellido());
            JOptionPane.showMessageDialog(this, "Jugador encontrado");
        }
    }//GEN-LAST:event_btnBuscarJugadorActionPerformed

    private void btnEditarJugadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarJugadorActionPerformed
        String cedula = this.txtCedulaJugador.getText();
        Jugador jugadorBuscar = this.miLiga.buscarJugador(cedula);
        if(jugadorBuscar == null){
            JOptionPane.showMessageDialog(this, "Jugador no encontrado");
        }else{
            jugadorBuscar.setNombre(this.txtNombreJugador.getText());
            jugadorBuscar.setApellido(this.txtApellidoJugador.getText());
            jugadorBuscar.setEdad(Integer.parseInt(this.txtEdadJugador.getText()));
            jugadorBuscar.setNacionalidad(this.txtNacionalidad.getText());
            jugadorBuscar.setNumeroGolesMarcados(Integer.parseInt(this.txtGolesMarcados.getText()));
            jugadorBuscar.setPosicion(this.txtPosicionJugador.getText());
            jugadorBuscar.setSalario(Double.parseDouble(this.txtSalarioJugador.getText()));
            actualizarTablaJugadores();
            limpiarCajas();
            actualizarComboEquipoJugador();
            JOptionPane.showMessageDialog(this, "Jugador editado");
        }
    }//GEN-LAST:event_btnEditarJugadorActionPerformed

    private void btnCrearJugadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearJugadorActionPerformed
        try {
            String cedula = this.txtCedulaJugador.getText();
            String nombre = this.txtNombreJugador.getText();
            String apellido = this.txtApellidoJugador.getText();
            int edad = Integer.parseInt(this.txtEdadJugador.getText());
            String posicion = this.txtPosicionJugador.getText();
            String nacionalidad = this.txtNacionalidad.getText();
            double salario = Double.parseDouble(this.txtSalarioJugador.getText());
            int golesMarcados = Integer.parseInt(this.txtGolesMarcados.getText());
            boolean personaExistente = this.miLiga.verificarPersonExiste(cedula);
            if(!personaExistente){
                Persona nuevoJugador = new Jugador(nacionalidad, posicion, golesMarcados, salario, cedula, nombre, apellido, edad);
                this.miLiga.getMisPersonas().add(nuevoJugador);
                actualizarTablaJugadores();
                limpiarCajas();
                actualizarComboEquipoJugador(); 
                jugadorJovenLiga();
                JOptionPane.showMessageDialog(this, "Jugador Creado");
            }else{
                JOptionPane.showMessageDialog(this, "La cedula ingresada ya existe");
            }
            actualizarComboJugadoresManager();
        } catch (Exception e){
            System.out.println(""+e);
        }
    }//GEN-LAST:event_btnCrearJugadorActionPerformed

    private void btnEliminarArbitroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarArbitroActionPerformed
        String cedula = this.txtCedulaArbitro.getText();
        Arbitro buscarArbitro = this.miLiga.buscarArbitro(cedula);
        if(buscarArbitro == null){
            JOptionPane.showMessageDialog(this, "Arbitro no encontrado");
        }else{
            this.miLiga.getMisPersonas().remove(buscarArbitro);
            limpiarCajas();
            actualizarTablaArbitro();
            JOptionPane.showMessageDialog(this, "Arbitro Eliminado");
        }
    }//GEN-LAST:event_btnEliminarArbitroActionPerformed

    private void btnBuscarArbitroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarArbitroActionPerformed
        String cedula = this.txtCedulaArbitro.getText();
        Arbitro buscarArbitro = this.miLiga.buscarArbitro(cedula);
        if(buscarArbitro == null){
            JOptionPane.showMessageDialog(this, "Arbitro no encontrado");
        }else{
            this.txtNombreArbitro.setText(buscarArbitro.getNombre());
            this.txtApellidoArbitro.setText(buscarArbitro.getApellido());
            this.txtEdadArbitro.setText(Integer.toString(buscarArbitro.getEdad()));
            this.checkFIFA.setSelected(buscarArbitro.isEsFiFA());
            JOptionPane.showMessageDialog(this, "Arbitro encontrado");
        }
    }//GEN-LAST:event_btnBuscarArbitroActionPerformed

    private void btnEditarArbitroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarArbitroActionPerformed
        String cedula = this.txtCedulaArbitro.getText();
        Arbitro buscarArbitro = this.miLiga.buscarArbitro(cedula);
        if(buscarArbitro == null){
            JOptionPane.showMessageDialog(this, "Arbitro no encontrado");
        }else{
            buscarArbitro.setNombre(this.txtNombreArbitro.getText());
            buscarArbitro.setApellido(this.txtApellidoArbitro.getText());
            buscarArbitro.setEdad(Integer.parseInt(this.txtEdadArbitro.getText()));
            buscarArbitro.setEsFiFA(this.checkFIFA.isSelected());
            limpiarCajas();
            actualizarTablaArbitro();
            JOptionPane.showMessageDialog(this, "Arbitro encontrado");
        }
    }//GEN-LAST:event_btnEditarArbitroActionPerformed

    private void btnCrearArbitroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearArbitroActionPerformed
        String cedula = this.txtCedulaArbitro.getText();
        String nombre = this.txtNombreArbitro.getText();
        String apellido = this.txtApellidoArbitro.getText();
        int edad = Integer.parseInt(this.txtEdadArbitro.getText());
        boolean fifa = this.checkFIFA.isSelected();
        boolean verificarCedula = this.miLiga.verificarPersonExiste(cedula);
        if(!verificarCedula){
            Persona nuevoArbitro = new Arbitro(fifa, cedula, nombre, apellido, edad);
            this.miLiga.getMisPersonas().add(nuevoArbitro);
            limpiarCajas();
            actualizarTablaArbitro();
            JOptionPane.showMessageDialog(this, "Arbitro creado");
        }else{
            JOptionPane.showMessageDialog(this, "La cedula ingresada ya existe");
        }
        
    }//GEN-LAST:event_btnCrearArbitroActionPerformed

    private void btnEliminarTecnicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarTecnicoActionPerformed
        String cedula = this.txtCedulaTecnico.getText();
        Tecnico buscarTecnico = this.miLiga.buscarTecnico(cedula);
        if(buscarTecnico == null){
            JOptionPane.showMessageDialog(this, "Tecnico no encontrado");
        }else{
            this.miLiga.getMisPersonas().remove(buscarTecnico);
            limpiarCajas();
            actualizatTablaTecnico();
            JOptionPane.showMessageDialog(this, "Tecnico eliminado");
        }
    }//GEN-LAST:event_btnEliminarTecnicoActionPerformed

    private void btnBuscarTecnicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarTecnicoActionPerformed
        String cedula = this.txtCedulaTecnico.getText();
        Tecnico buscarTecnico = this.miLiga.buscarTecnico(cedula);
        if(buscarTecnico == null){
            JOptionPane.showMessageDialog(this, "Tecnico no encontrado");
        }else{
            this.txtNombreTecnico.setText(buscarTecnico.getNombre());
            this.txtApellidoTecnico.setText(buscarTecnico.getApellido());
            this.txtEdadTecnico.setText(Integer.toString(buscarTecnico.getEdad()));
            this.txtAñosExperiencia.setText(Integer.toString(buscarTecnico.getAniosExperiencia()));
            this.txtSalario.setText(Double.toString(buscarTecnico.getSalario()));
            this.cbTecnicoEquipo.setEnabled(false);
            this.cbTecnicoEquipo.addItem(buscarTecnico.getMiEquipo().getNombre());
            JOptionPane.showMessageDialog(this, "Tecnico encontrado");
        }
    }//GEN-LAST:event_btnBuscarTecnicoActionPerformed

    private void btnEditarTecnicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarTecnicoActionPerformed
        String cedula = this.txtCedulaTecnico.getText();
        Tecnico buscarTecnico = this.miLiga.buscarTecnico(cedula);
        if(buscarTecnico == null){
            JOptionPane.showMessageDialog(this, "Tecnico no encontrado");
        }else{
            buscarTecnico.setNombre(this.txtNombreTecnico.getText());
            buscarTecnico.setApellido(this.txtApellidoTecnico.getText());
            buscarTecnico.setEdad(Integer.parseInt(this.txtEdadTecnico.getText()));
            buscarTecnico.setAniosExperiencia(Integer.parseInt(this.txtAñosExperiencia.getText()));
            buscarTecnico.setSalario(Double.parseDouble(this.txtSalario.getText()));
            limpiarCajas();
            actualizatTablaTecnico();
            actualizarComboEquipoTecnico();
            JOptionPane.showMessageDialog(this, "Tecnico editado");
        }
    }//GEN-LAST:event_btnEditarTecnicoActionPerformed

    private void btnCrearTecnicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearTecnicoActionPerformed
        String cedula = this.txtCedulaTecnico.getText();
        String nombre = this.txtNombreTecnico.getText();
        String apellido = this.txtApellidoTecnico.getText();
        int edad = Integer.parseInt(this.txtEdadTecnico.getText());
        int añosExperiencia = Integer.parseInt(this.txtAñosExperiencia.getText());
        double salario = Double.parseDouble(this.txtSalario.getText());
        boolean personExisten = this.miLiga.verificarPersonExiste(cedula);
        if(!personExisten){
            Persona tecnicoNuevo = new Tecnico(añosExperiencia, salario, cedula, nombre, apellido, edad);
            this.miLiga.getMisPersonas().add(tecnicoNuevo);
            limpiarCajas();
            actualizatTablaTecnico();
            actualizarComboEquipoTecnico();
            JOptionPane.showMessageDialog(this, "Tecnico Nuevo");
        }else{
            JOptionPane.showMessageDialog(this, "Este usuario ya existe");
        }
        
    }//GEN-LAST:event_btnCrearTecnicoActionPerformed

    private void btnCrearJornadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearJornadaActionPerformed
        String numeroJornada = this.txtNumeroJornada.getText();
        String slogan = this.txtSloganJornada.getText();
        Jornada nuevaJornada = new Jornada(numeroJornada, slogan);
        this.miLiga.getMisJornadas().add(nuevaJornada);
        limpiarCajas();
        actualizarTablaJornada();
        JOptionPane.showMessageDialog(this, "Jornada creada");
    }//GEN-LAST:event_btnCrearJornadaActionPerformed

    private void btnBuscarJornadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarJornadaActionPerformed
        String numeroJornada = this.txtNumeroJornada.getText();
        Jornada jornada = this.miLiga.buscarJornada(numeroJornada);
        if(jornada == null){
            JOptionPane.showMessageDialog(this, "La jornada no existe");
        }else{
            this.txtNumeroJornada.setText(jornada.getNumero());
            this.txtSloganJornada.setText(jornada.getSlogaFIFA());
            actualizarTablaJornadPartidos();
            JOptionPane.showMessageDialog(this, "Jornada encontrada");
        }
    }//GEN-LAST:event_btnBuscarJornadaActionPerformed

    private void btnEditarJornadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarJornadaActionPerformed
        String numeroJornada = this.txtNumeroJornada.getText();
        Jornada jornada = this.miLiga.buscarJornada(numeroJornada);
        if(jornada == null){
            JOptionPane.showMessageDialog(this, "La jornada no existe");
        }else{
            jornada.setNumero(this.txtNumeroJornada.getText());
            jornada.setSlogaFIFA(this.txtSloganJornada.getText());
            limpiarCajas();
            actualizarTablaJornada();
            JOptionPane.showMessageDialog(this, "Jornada editada");
        }
    }//GEN-LAST:event_btnEditarJornadaActionPerformed

    private void btnEliminarJornadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarJornadaActionPerformed
        String numeroJornada = this.txtNumeroJornada.getText();
        Jornada jornada = this.miLiga.buscarJornada(numeroJornada);
        if(jornada == null){
            JOptionPane.showMessageDialog(this, "La jornada no existe");
        }else{
            this.miLiga.getMisJornadas().remove(jornada);
            limpiarCajas();
            actualizarTablaJornada();
            JOptionPane.showMessageDialog(this, "Jornada Eliminada");
        }
    }//GEN-LAST:event_btnEliminarJornadaActionPerformed

    private void btnCertificadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCertificadoActionPerformed
        String cedula = this.txtCedulaArbitro.getText();
        Arbitro arbitro = this.miLiga.buscarArbitro(cedula);
        if(arbitro != null){
            String contenido = "Colombia, 8 de noviembre de 2021\n\nLa Liga Nacional de Futbol certifica a quien le interese que "+arbitro.getNombre()+ " "+ arbitro.getApellido()+" con cedula "+
                    arbitro.getCedula()+" actua en calidad de arbitro \n\nDado en Colombia, 8 de noviembre de 2021 \n\n Presidencia de la confederacion";
        guardarArchivo(contenido, "doc");
        }else{
            JOptionPane.showMessageDialog(this, "No se encontro arbitros");
        }
        
    }//GEN-LAST:event_btnCertificadoActionPerformed

    private void btnAñadirRelacionJugadorManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAñadirRelacionJugadorManagerActionPerformed
        try {
            String cedula = this.txtCedulaManager.getText();
            Manager manager = this.miLiga.buscarManager(cedula);
            if(manager !=null){
                int indice = this.cbJugadoresManager.getSelectedIndex();
                Jugador seleccionado = this.miLiga.listaJugador().get(indice);
                manager.getMisJugadores().add(seleccionado);
                this.miLiga.listaJugador().get(indice).setMiManager(manager);
                actualizarTablaRelacionJugadoresManager();
                JOptionPane.showMessageDialog(this, "Jugador añadido");
            }else{
                 JOptionPane.showMessageDialog(this, "Manager no encontrado");
       }
        } catch (Exception e) {
                System.out.println(""+e);
        }
       
    }//GEN-LAST:event_btnAñadirRelacionJugadorManagerActionPerformed

    private void btnDesligarManagerJugadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDesligarManagerJugadorActionPerformed
         try {
            String cedula = this.txtCedulaManager.getText();
            Manager manager = this.miLiga.buscarManager(cedula);
            if(manager !=null){
                int indice = this.cbJugadoresManager.getSelectedIndex();
                Jugador seleccionado = this.miLiga.listaJugador().get(indice);
                manager.getMisJugadores().remove(seleccionado);
                manager.getMisJugadores().get(indice).setMiManager(null);
                actualizarTablaRelacionJugadoresManager();
                JOptionPane.showMessageDialog(this, "Jugador Desligado");
            }else{
                 JOptionPane.showMessageDialog(this, "Manager no encontrado");
       }
        } catch (Exception e) {
                System.out.println(""+e);
        }
    }//GEN-LAST:event_btnDesligarManagerJugadorActionPerformed

    private void btnEliminarEquipoJugadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarEquipoJugadorActionPerformed
        String id = this.txtIdEquipo.getText();
       Equipo equipo = this.miLiga.buscarEquipo(id);
       if(equipo != null){
           int indice = this.cbEquipoJugador.getSelectedIndex();
           Jugador seleccionado = this.miLiga.listaJugador().get(indice);
           equipo.getMisJugadores().remove(seleccionado);
           this.miLiga.listaJugador().get(indice).setMiEquipo(null);
           actualizarTablaEquipoJugador();
       }else{
           JOptionPane.showMessageDialog(this, "Equipo no encontrado");
       }
                
    }//GEN-LAST:event_btnEliminarEquipoJugadorActionPerformed

    private void txtGolesMarcadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGolesMarcadosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGolesMarcadosActionPerformed

    private void btnCertificadoAficionadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCertificadoAficionadoActionPerformed
       String id = this.txtCedulaAficionado.getText();
       Aficionado aficionado = this.miLiga.buscarAficionado(id);
       if(aficionado != null){
           String contenido = "Colombia, 8 de noviembre de 2021\n\nLa Liga Nacional de Futbol certifica a quien le interese que "+ aficionado.getNombre()+ " "+ aficionado.getApellido()+ " se encuentra afiliado a la Liga Nacional"
                   + " de Futbol y actua de aficionado para el equipo " +aficionado.getMiEquipo().getNombre()+ " afirmando que lleva "+aficionado.getAniosFidelidad()+" anios acompañándolo en las buenas y en las malas campanias \n\nDado en Colombia, 8 de noviembre de 2021 \n\n Presidencia de la confederacion";
           guardarArchivo(contenido, "doc");
       }else{
           JOptionPane.showMessageDialog(this, "Aficionado no encontrado");
       }
    }//GEN-LAST:event_btnCertificadoAficionadoActionPerformed

    private void btnEquipoAficionadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEquipoAficionadoActionPerformed
         String id = this.txtIdEquipo.getText();
       Equipo equipo = this.miLiga.buscarEquipo(id);
       if(equipo != null){
           int indice = this.cbEquipoAficionado.getSelectedIndex();
           Aficionado seleccionado = this.miLiga.listaAficionado().get(indice);
           equipo.getMisAficionados().add(seleccionado);
           this.miLiga.listaAficionado().get(indice).setMiEquipo(equipo);
           actualizarTablaEquipoAficionado();
           JOptionPane.showMessageDialog(this, "Aficionado asociado");
       }else{
           JOptionPane.showMessageDialog(this, "Equipo no encontrado");
       }
    }//GEN-LAST:event_btnEquipoAficionadoActionPerformed

    private void cbTecnicoEquipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTecnicoEquipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbTecnicoEquipoActionPerformed

    private void btnCertificadoTecnicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCertificadoTecnicoActionPerformed
        String cedula = this.txtCedulaTecnico.getText();
        Tecnico tecnico = this.miLiga.buscarTecnico(cedula);
        if(tecnico != null){
            String contenido = "Colombia, 8 de noviembre de 2021\n\nLa Liga Nacional de Futbol certifica a quien le interese que la persona " + tecnico.getNombre() + " "+ tecnico.getApellido() + " Identificado con cedula "+ tecnico.getCedula()+
                    " se encuentra afiliado a la liga Nacional de futbol y actua en calidad de tecnico del equipo "+ tecnico.getMiEquipo().getNombre() + " \n\nDado en Colombia, 8 de noviembre de 2021 \n\n Presidencia de la confederacion";
                    guardarArchivo(contenido, "doc");
        }else{
            JOptionPane.showMessageDialog(this, "No se encontro el tecnico");
        }
    }//GEN-LAST:event_btnCertificadoTecnicoActionPerformed

    private void btnEquipoManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEquipoManagerActionPerformed
        try {
            String id = this.txtIdEquipo.getText();
        Equipo equipo = this.miLiga.buscarEquipo(id);
        if(equipo != null){
            int index = this.cbEquipoManager.getSelectedIndex();
            Manager seleccionado = this.miLiga.listaManager().get(index);
            equipo.setMiManager(seleccionado);
            equipo.getMiManager().setMiEquipo(equipo);
            JOptionPane.showMessageDialog(this, "Manager Asociado");
        }else{
            JOptionPane.showMessageDialog(this, "Equipo no encontrado");
        }
        } catch (Exception e) {
            System.out.println(""+e);
        }
    }//GEN-LAST:event_btnEquipoManagerActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        String cedula = this.txtCedulaManager.getText();
        Manager manager = this.miLiga.buscarManager(cedula);
        if(manager!=null){
             String contenido = "Colombia, 8 de noviembre de 2021\n\nLa Liga Nacional de Futbol certifica a quien le interese que la persona " + manager.getNombre() + " "+ manager.getApellido() + " Identificado con cedula "+ manager.getCedula()+
                    " se encuentra afiliado a la liga Nacional de futbol y actua en calidad de manager del equipo "+ manager.getMiEquipo().getNombre() +" y de "+ manager.getMisJugadores().size()+" jugadores \n\nDado en Colombia, 8 de noviembre de 2021 \n\n Presidencia de la confederacion";
             guardarArchivo(contenido, "doc");
        }
       
    }//GEN-LAST:event_jButton15ActionPerformed

    private void btnEquipoTecnicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEquipoTecnicoActionPerformed
        String id = this.txtIdEquipo.getText();
        Equipo equipo = this.miLiga.buscarEquipo(id);
        if(equipo != null){
            int index = this.cbEquipoTecnico.getSelectedIndex();
            Tecnico seleccionado = this.miLiga.listaTecnico().get(index);
            equipo.setMiTecnico(seleccionado);
            equipo.getMiTecnico().setMiEquipo(equipo);
            JOptionPane.showMessageDialog(this, "Tecnico Asociado");
        }else{
            JOptionPane.showMessageDialog(this, "Equipo no encontrado");
        }
    }//GEN-LAST:event_btnEquipoTecnicoActionPerformed

    private void btnAgregarEquipoJugadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarEquipoJugadorActionPerformed
       String id = this.txtIdEquipo.getText();
       Equipo equipo = this.miLiga.buscarEquipo(id);
       if(equipo != null){
           int indice = this.cbEquipoJugador.getSelectedIndex();
           Jugador seleccionado = this.miLiga.listaJugador().get(indice);
           equipo.getMisJugadores().add(seleccionado);
           this.miLiga.listaJugador().get(indice).setMiEquipo(equipo);
           actualizarTablaEquipoJugador();
           goleadorLiga();
       }else{
           JOptionPane.showMessageDialog(this, "Equipo no encontrado");
       }
    }//GEN-LAST:event_btnAgregarEquipoJugadorActionPerformed

    private void btnCertificadoJugadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCertificadoJugadorActionPerformed
        String cedula = this.txtCedulaJugador.getText();
        Jugador jugador = this.miLiga.buscarJugador(cedula);
        if(jugador !=null){
            String contenido = "La Liga Nacional de Futbol certifica a quien le interese que "+jugador.getNombre()+" "+ jugador.getApellido()+ " con cedula "+
                    jugador.getCedula()+" se encuentra afiliado a la Liga Nacional de Futbol y actua en calidad de jugador del equipo " + jugador.getMiEquipo().getNombre()+" ocupando la "
                    + "posicion de "+ jugador.getPosicion() ;
            guardarArchivo(contenido, "doc");
        }else{
            JOptionPane.showMessageDialog(this, "Jugador no encontrado");
        }
    }//GEN-LAST:event_btnCertificadoJugadorActionPerformed

    private void btnEliminarEquipoAficionadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarEquipoAficionadoActionPerformed
           String id = this.txtIdEquipo.getText();
       Equipo equipo = this.miLiga.buscarEquipo(id);
       if(equipo != null){
           int indice = this.cbEquipoAficionado.getSelectedIndex();
           Aficionado seleccionado = this.miLiga.listaAficionado().get(indice);
           equipo.getMisAficionados().remove(seleccionado);
           this.miLiga.listaAficionado().get(indice).setMiEquipo(null);
           actualizarTablaEquipoAficionado();
           JOptionPane.showMessageDialog(this, "Aficionado asociado");
       }else{
           JOptionPane.showMessageDialog(this, "Equipo no encontrado");
       }
    }//GEN-LAST:event_btnEliminarEquipoAficionadoActionPerformed

    private void btnAñadirGolLocalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAñadirGolLocalActionPerformed
         
        String id = this.txtIdPartido.getText();
        Partido partido = this.miLiga.buscarPartido(id);
        if(partido != null){
         if(partido.getMiEquipoLocal() !=null){
         int indice = this.cbGolesJugadorLocal.getSelectedIndex();
         Jugador jugadorLocal = partido.getMiEquipoLocal().getMisJugadores().get(indice);
         int golesLocal = jugadorLocal.getNumeroGolesMarcados();
         golesLocal+=Integer.parseInt(this.txtGolesJugadorLocal.getText());
         jugadorLocal.setNumeroGolesMarcados(golesLocal);
         actualizarTablaJugadores();
         JOptionPane.showMessageDialog(this, "Goles añadidos");
        }else
        {
        } 
     
    }
    }//GEN-LAST:event_btnAñadirGolLocalActionPerformed

    private void btnCrearPartidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearPartidoActionPerformed
        try {
       String id = this.txtIdPartido.getText();
       String fecha = this.txtFechaPartido.getText();
       int golesLocal = Integer.parseInt(this.txtGolesLocal.getText());
       int golesVisita = Integer.parseInt(this.txtGolesVisita.getText());
       Partido nuevoPartido = new Partido(id, fecha, golesLocal, golesVisita);
       this.miLiga.getMisPartidos().add(nuevoPartido);
       actualizarTablaPartidos();
       limpiarCajas();
       JOptionPane.showMessageDialog(this, "Partido Creado");
        } catch (Exception e) {
            System.out.println(""+e);
        }
    }//GEN-LAST:event_btnCrearPartidoActionPerformed

    private void btnAsignarEquiposPartidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsignarEquiposPartidoActionPerformed
        this.cbGolesJugadorLocal.removeAllItems();
        this.cbGolesJugadorVisita.removeAllItems();
        String id = this.txtIdPartido.getText();
        Partido partido = this.miLiga.buscarPartido(id);     
        if(partido !=null){
            int indiceLocal = this.cbEquipoLocal.getSelectedIndex();
            int indiceVisita = this.cbEquipoVisita.getSelectedIndex();
            int indiceArbitro = this.cbPartidoArbitro.getSelectedIndex();
            Arbitro arbitroSeleccionado = this.miLiga.listaArbitros().get(indiceArbitro);
            Equipo seleccionadoLocal = this.miLiga.getMisEquipos().get(indiceLocal);
            Equipo seleccionadoVisita = this.miLiga.getMisEquipos().get(indiceVisita);
            
            if(seleccionadoLocal.equals(seleccionadoVisita)){
                JOptionPane.showMessageDialog(this, "Equipos repetidos");
            }else{
                //Asignar Puntos
                partido.setMiEquipoLocal(seleccionadoLocal);
                partido.setMiEquipoVisitante(seleccionadoVisita);
                partido.asignarPuntos(partido.getGolesVisitante(), partido.getGolesLocal());  
                
                //Asignar Arbitro
                partido.setMiArbitro(arbitroSeleccionado);
                
                //Sumar partidos jugados
                int partidosJugadosLocal = seleccionadoLocal.getPartidosJugados();
                int partidosJugadosVisitante = seleccionadoVisita.getPartidosJugados();
                partidosJugadosVisitante++;
                partidosJugadosLocal++;
                partido.getMiEquipoLocal().setPartidosJugados(partidosJugadosLocal);
                partido.getMiEquipoVisitante().setPartidosJugados(partidosJugadosVisitante);
                
                
                
                //Asignar partidos a los equipos                 
                seleccionadoLocal.getMisPartidos().add(partido);
                seleccionadoVisita.getMisPartidos().add(partido);
                actualizarTablaEstadioPartido();
                actualizarTablaEquipo();
                actualizarComboGolesJugadores();
                JOptionPane.showMessageDialog(this, "Equipos Asignados");
            }               
        }else{
            JOptionPane.showMessageDialog(this, "Partido no encontrado");
        }
    }//GEN-LAST:event_btnAsignarEquiposPartidoActionPerformed

    private void btnBuscarPartidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPartidoActionPerformed
        this.cbGolesJugadorLocal.removeAllItems();
        this.cbGolesJugadorVisita.removeAllItems();
        String id = this.txtIdPartido.getText();
        Partido partido = this.miLiga.buscarPartido(id);
        if(partido !=null){
            this.txtIdPartido.setText(partido.getId());
            this.txtFechaPartido.setText(partido.getFecha());
            this.txtGolesLocal.setText(""+partido.getGolesLocal());
            this.txtGolesVisita.setText(""+partido.getGolesVisitante());
            if(partido.getMiJornada() != null && partido.getMiEstadio() != null){
                this.txtPartidoEstadio.setText("Numero "+ partido.getMiEstadio().getNombre());
                this.txtPartidoJornada.setText(partido.getMiJornada().getNumero());
            }else{
                this.txtPartidoEstadio.setText("");
                this.txtPartidoJornada.setText("");
            }
            actualizarComboGolesJugadores();            
            JOptionPane.showMessageDialog(this, "Partido Encontrado");
        }else{
            JOptionPane.showMessageDialog(this, "Partido no encontrado");
        }
    }//GEN-LAST:event_btnBuscarPartidoActionPerformed

    private void btnEditarPartidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarPartidoActionPerformed
        String id = this.txtIdPartido.getText();
        Partido partido = this.miLiga.buscarPartido(id);
        if(partido !=null){
            partido.setId(this.txtIdPartido.getText());
            partido.setFecha(this.txtFechaPartido.getText());
            partido.setGolesLocal(Integer.parseInt(this.txtGolesLocal.getText()));
            partido.setGolesVisitante(Integer.parseInt(this.txtGolesVisita.getText()));
            actualizarTablaPartidos();
            limpiarCajas();
            JOptionPane.showMessageDialog(this, "Partido Editado");
        }else{
            JOptionPane.showMessageDialog(this, "Partido no encontrado");
        }
    }//GEN-LAST:event_btnEditarPartidoActionPerformed

    private void btnEliminarPartidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarPartidoActionPerformed
        String id = this.txtIdPartido.getText();
        Partido partido = this.miLiga.buscarPartido(id);
        if(partido !=null){
            this.miLiga.getMisPartidos().remove(partido);
            actualizarTablaPartidos();
            limpiarCajas();
            JOptionPane.showMessageDialog(this, "Partido Eliminado");
        }else{
            JOptionPane.showMessageDialog(this, "Partido no encontrado");
        }
    }//GEN-LAST:event_btnEliminarPartidoActionPerformed

    private void btnAgregarJornadaPartidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarJornadaPartidoActionPerformed
        String numero = this.txtNumeroJornada.getText();
        Jornada jornada = this.miLiga.buscarJornada(numero);
        if(jornada != null){
            int indice = this.cbJornadaEquipo.getSelectedIndex();
            Partido partido = this.miLiga.getMisPartidos().get(indice);
            jornada.getMisPartidos().add(partido);
            this.miLiga.getMisPartidos().get(indice).setMiJornada(jornada);
            actualizarTablaJornadPartidos();
            JOptionPane.showMessageDialog(this, "Partido Asociado");
        }else{
            JOptionPane.showMessageDialog(this, "Jornada no encontrada");
        }
    }//GEN-LAST:event_btnAgregarJornadaPartidoActionPerformed

    private void btnEliminarJornadaPartidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarJornadaPartidoActionPerformed
        String numero = this.txtNumeroJornada.getText();
        Jornada jornada = this.miLiga.buscarJornada(numero);
        if(jornada != null){
            int indice = this.cbJornadaEquipo.getSelectedIndex();
            Partido partido = this.miLiga.getMisPartidos().get(indice);
            jornada.getMisPartidos().remove(partido);
            this.miLiga.getMisPartidos().get(indice).setMiJornada(jornada);          
            actualizarTablaJornadPartidos();
            JOptionPane.showMessageDialog(this, "Partido Asociado");
        }else{
            JOptionPane.showMessageDialog(this, "Jornada no encontrada");
        }
    }//GEN-LAST:event_btnEliminarJornadaPartidoActionPerformed

    private void btnEstadioPartidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEstadioPartidoActionPerformed
        String id = this.txtIdEstadio.getText();
        Estadio estadioBuscado= this.miLiga.buscarEstadio(id);
        if(estadioBuscado!=null){
            int indice = this.cbEstadioPartido.getSelectedIndex();
            Partido partido = this.miLiga.getMisPartidos().get(indice); 
            estadioBuscado.getMisPartidos().add(partido);
            this.miLiga.getMisPartidos().get(indice).setMiEstadio(estadioBuscado);
            actualizarTablaEstadioPartido();
            JOptionPane.showMessageDialog(this, "Partido asociado");
        }else{
            JOptionPane.showMessageDialog(this, "Estadio no encontrado");
        }
        
    }//GEN-LAST:event_btnEstadioPartidoActionPerformed

    private void btnEliminarEstadioPartidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarEstadioPartidoActionPerformed
        String id = this.txtIdEstadio.getText();
        Estadio estadioBuscado= this.miLiga.buscarEstadio(id);
        if(estadioBuscado!=null){
            int indice = this.cbEstadioPartido.getSelectedIndex();
            Partido partido = this.miLiga.getMisPartidos().get(indice); 
            estadioBuscado.getMisPartidos().remove(partido);
            this.miLiga.getMisPartidos().get(indice).setMiEstadio(null);
            actualizarTablaEstadioPartido();
            JOptionPane.showMessageDialog(this, "Partido Desasociado");
        }else{
            JOptionPane.showMessageDialog(this, "Estadio no encontrado");
        }
    }//GEN-LAST:event_btnEliminarEstadioPartidoActionPerformed

    private void btnAñadirGolVisitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAñadirGolVisitaActionPerformed
         String id = this.txtIdPartido.getText();
        Partido partido = this.miLiga.buscarPartido(id);
        if(partido != null){
         if(partido.getMiEquipoVisitante()!=null){
         int indice = this.cbGolesJugadorVisita.getSelectedIndex();
         Jugador jugadorVisita = partido.getMiEquipoVisitante().getMisJugadores().get(indice);
         int golesVisita = jugadorVisita.getNumeroGolesMarcados();
         golesVisita+=Integer.parseInt(this.txtGolesJugadorLocal.getText());
         jugadorVisita.setNumeroGolesMarcados(golesVisita);
         JOptionPane.showConfirmDialog(this, "Goles añadidos");
         actualizarTablaJugadores();
        }else
        {
        } 
        }
    }//GEN-LAST:event_btnAñadirGolVisitaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(jfProyectoFinal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(jfProyectoFinal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(jfProyectoFinal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(jfProyectoFinal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new jfProyectoFinal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Jlabel;
    private javax.swing.JButton btnAgregarEquipoJugador;
    private javax.swing.JButton btnAgregarJornadaPartido;
    private javax.swing.JButton btnAsignarEquiposPartido;
    private javax.swing.JButton btnAñadirGolLocal;
    private javax.swing.JButton btnAñadirGolVisita;
    private javax.swing.JButton btnAñadirRelacionJugadorManager;
    private javax.swing.JButton btnBuscarAficionado;
    private javax.swing.JButton btnBuscarArbitro;
    private javax.swing.JButton btnBuscarEquipo;
    private javax.swing.JButton btnBuscarEstadio;
    private javax.swing.JButton btnBuscarJornada;
    private javax.swing.JButton btnBuscarJugador;
    private javax.swing.JButton btnBuscarManager;
    private javax.swing.JButton btnBuscarPartido;
    private javax.swing.JButton btnBuscarTecnico;
    private javax.swing.JButton btnCertificado;
    private javax.swing.JButton btnCertificadoAficionado;
    private javax.swing.JButton btnCertificadoJugador;
    private javax.swing.JButton btnCertificadoTecnico;
    private javax.swing.JButton btnCrearAficionado;
    private javax.swing.JButton btnCrearArbitro;
    private javax.swing.JButton btnCrearEquipo;
    private javax.swing.JButton btnCrearEstadio;
    private javax.swing.JButton btnCrearJornada;
    private javax.swing.JButton btnCrearJugador;
    private javax.swing.JButton btnCrearManager;
    private javax.swing.JButton btnCrearPartido;
    private javax.swing.JButton btnCrearTecnico;
    private javax.swing.JButton btnDesligarManagerJugador;
    private javax.swing.JButton btnEditarAficionado;
    private javax.swing.JButton btnEditarArbitro;
    private javax.swing.JButton btnEditarEquipo;
    private javax.swing.JButton btnEditarEstadio;
    private javax.swing.JButton btnEditarJornada;
    private javax.swing.JButton btnEditarJugador;
    private javax.swing.JButton btnEditarManager;
    private javax.swing.JButton btnEditarPartido;
    private javax.swing.JButton btnEditarTecnico;
    private javax.swing.JButton btnEliminarAficionado;
    private javax.swing.JButton btnEliminarArbitro;
    private javax.swing.JButton btnEliminarEquipo;
    private javax.swing.JButton btnEliminarEquipoAficionado;
    private javax.swing.JButton btnEliminarEquipoJugador;
    private javax.swing.JButton btnEliminarEstadio;
    private javax.swing.JButton btnEliminarEstadioPartido;
    private javax.swing.JButton btnEliminarJornada;
    private javax.swing.JButton btnEliminarJornadaPartido;
    private javax.swing.JButton btnEliminarJugador;
    private javax.swing.JButton btnEliminarManager;
    private javax.swing.JButton btnEliminarPartido;
    private javax.swing.JButton btnEliminarTecnico;
    private javax.swing.JButton btnEquipoAficionado;
    private javax.swing.JButton btnEquipoManager;
    private javax.swing.JButton btnEquipoTecnico;
    private javax.swing.JButton btnEstadioPartido;
    private javax.swing.JComboBox<String> cbAficionadoEquipo;
    private javax.swing.JComboBox<String> cbEquipoAficionado;
    private javax.swing.JComboBox<String> cbEquipoJugador;
    private javax.swing.JComboBox<String> cbEquipoLocal;
    private javax.swing.JComboBox<String> cbEquipoManager;
    private javax.swing.JComboBox<String> cbEquipoTecnico;
    private javax.swing.JComboBox<String> cbEquipoVisita;
    private javax.swing.JComboBox<String> cbEstadioPartido;
    private javax.swing.JComboBox<String> cbGolesJugadorLocal;
    private javax.swing.JComboBox<String> cbGolesJugadorVisita;
    private javax.swing.JComboBox<String> cbJornadaEquipo;
    private javax.swing.JComboBox<String> cbJugadorEquipo;
    private javax.swing.JComboBox<String> cbJugadorManager;
    private javax.swing.JComboBox<String> cbJugadoresManager;
    private javax.swing.JComboBox<String> cbManagerEquipo;
    private javax.swing.JComboBox<String> cbPartidoArbitro;
    private javax.swing.JComboBox<String> cbTecnicoEquipo;
    private javax.swing.JCheckBox checkAbonado;
    private javax.swing.JCheckBox checkFIFA;
    private javax.swing.JButton jButton15;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblAficionados;
    private javax.swing.JTable tblArbitros;
    private javax.swing.JTable tblEquipoAficionado;
    private javax.swing.JTable tblEquipoJugador;
    private javax.swing.JTable tblEquipoPartidos;
    private javax.swing.JTable tblEquipos;
    private javax.swing.JTable tblEstadioPartido;
    private javax.swing.JTable tblJornada;
    private javax.swing.JTable tblJornadaPartido;
    private javax.swing.JTable tblJugadores;
    private javax.swing.JTable tblManagers;
    private javax.swing.JTable tblPartido;
    private javax.swing.JTable tblRelacionJugadoresManager;
    private javax.swing.JTable tblTablaEstadios;
    private javax.swing.JTable tblTecnicos;
    private javax.swing.JTextField txtApellidoAficionado;
    private javax.swing.JTextField txtApellidoArbitro;
    private javax.swing.JTextField txtApellidoJugador;
    private javax.swing.JTextField txtApellidoManager;
    private javax.swing.JTextField txtApellidoTecnico;
    private javax.swing.JTextField txtAñoFundacion;
    private javax.swing.JTextField txtAñosAfiliacion;
    private javax.swing.JTextField txtAñosExperiencia;
    private javax.swing.JTextField txtCapacidad;
    private javax.swing.JTextField txtCedulaAficionado;
    private javax.swing.JTextField txtCedulaArbitro;
    private javax.swing.JTextField txtCedulaJugador;
    private javax.swing.JTextField txtCedulaManager;
    private javax.swing.JTextField txtCedulaTecnico;
    private javax.swing.JTextField txtCiudad;
    private javax.swing.JTextField txtEdadAficionado;
    private javax.swing.JTextField txtEdadArbitro;
    private javax.swing.JTextField txtEdadJugador;
    private javax.swing.JTextField txtEdadManager;
    private javax.swing.JTextField txtEdadTecnico;
    private javax.swing.JFormattedTextField txtFechaPartido;
    private javax.swing.JTextField txtFidelidadAficionado;
    private javax.swing.JTextField txtGoleadorLiga;
    private javax.swing.JTextField txtGolesContra;
    private javax.swing.JTextField txtGolesFavor;
    private javax.swing.JTextField txtGolesJugadorLocal;
    private javax.swing.JTextField txtGolesJugadorVisita;
    private javax.swing.JTextField txtGolesLocal;
    private javax.swing.JTextField txtGolesMarcados;
    private javax.swing.JTextField txtGolesVisita;
    private javax.swing.JTextField txtIdEquipo;
    private javax.swing.JTextField txtIdEstadio;
    private javax.swing.JTextField txtIdPartido;
    private javax.swing.JTextField txtJugadorJovenLiga;
    private javax.swing.JTextField txtNacionalidad;
    private javax.swing.JTextField txtNombreAficionado;
    private javax.swing.JTextField txtNombreArbitro;
    private javax.swing.JTextField txtNombreEquipo;
    private javax.swing.JTextField txtNombreEstadio;
    private javax.swing.JTextField txtNombreJugador;
    private javax.swing.JTextField txtNombreManager;
    private javax.swing.JTextField txtNombreTecnico;
    private javax.swing.JTextField txtNumeroJornada;
    private javax.swing.JTextField txtPartidoEstadio;
    private javax.swing.JTextField txtPartidoJornada;
    private javax.swing.JTextField txtPartidosJugados;
    private javax.swing.JTextField txtPosicionJugador;
    private javax.swing.JTextField txtPromedioEdad;
    private javax.swing.JTextField txtPuntos;
    private javax.swing.JTextField txtSalario;
    private javax.swing.JTextField txtSalarioJugador;
    private javax.swing.JTextField txtSloganJornada;
    private javax.swing.JTextField txtTitulosInternacionales;
    private javax.swing.JTextField txtTitulosNacionales;
    // End of variables declaration//GEN-END:variables
}
