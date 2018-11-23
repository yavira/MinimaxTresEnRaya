package estrategia;

import tresenraya.Posicion;

public class MiEstrategia {
  private StringBuilder tablero;
  private String estado;
  private int turno;
  private static String humano = "1";
  private static String cpu = "2";
  private String estadoJuego; // jugando 0, esperando 1, terminado 2

  public MiEstrategia() {
    this.estado = "";
    this.tablero = new StringBuilder("000000000");
    this.turno = 1;
  }

  public void registrarJugadaContraria(Posicion posicion) {
    int pos = this.convertPosicion(posicion);
    this.estado =  this.getEstado();
    if ( this.estado == "0") {
      if (this.casillaDisponible(pos)) {
        this.marcarCasilla( pos,String.valueOf(this.turno));
        this.verificarGanador(this.turno);
        if ( this.turno == 1) {
           this.turno = 2;
        } else  {
          this.turno = 1;
        }
      }
    }
    else if (this.getEstado() == "2") {

    }
   // System.out.println("jugada Contraria" + this.imprimirTablero());
  }
  private void verificarGanador(int turno) {
    if (this.esGanador(String.valueOf(turno))) {
      this.cambiarEstado("2");
      System.out.println("¡HA GANADO! " +  this.turno);
    } else if (!this.existeCasillaVacia()) {
      this.cambiarEstado("2");
      System.out.println("EMPATE");
    } else {
      this.cambiarEstado("0");
    }
  }
  public Posicion hacerJugada() {
    Posicion posicion = null;
    int pos =  0;
    int n = this.tamano();
    int aux, mejor = -9999;
    for (int i = 0 ; i < n ; i ++) {
      if(this.casillaDisponible(i)) {
         this.marcarCasilla(i, String.valueOf(this.turno));

        aux =  this.min();
        if( aux > mejor) {
          mejor = aux;
          pos =  i;
        }
         this.marcarCasilla(i, "0");
      }
    }
    this.marcarCasilla(pos, String.valueOf(this.turno));
    this.verificarGanador(this.turno);
    if ( this.turno == 1) {
      this.turno = 2;
    } else  {
      this.turno = 1;
    }
    posicion =  new Posicion((pos) / 3, (pos) % 3);
    return posicion;
  }
  public int min() {

    if (this.esGanador(this.getCpu())) {
      return  1;
    }
    if(!this.existeCasillaVacia()) {
      return  0;
    }
    int n  = this.tamano();
    int aux, mejor = 9999;
    for (int i = 0 ; i < n ; i ++) {
      if(this.casillaDisponible(i)) {
        this.marcarCasilla(i, this.getHumano());
        aux = this.max();
        if( aux < mejor) {
          mejor =  aux;
        }
        this.marcarCasilla(i, "0");
      }
    }
    return mejor;
  }

  public int max() {

    if (this.esGanador(this.getHumano())) {
      return  -1;
    }
    if(!this.existeCasillaVacia()) {
      return  0;
    }
    int n  = this.tamano();
    int aux, mejor = -9999;
    for (int j = 0 ; j < n ; j ++) {
      if(this.casillaDisponible(j)) {
        this.marcarCasilla(j, this.getCpu());
        aux = this.min();
        if( aux > mejor) {
          mejor =  aux;
        }
         this.marcarCasilla(j, "0");
      }
    }
    return mejor;
  }

  public int convertPosicion(Posicion posicion) {
    int x = posicion.fila;
    int y = posicion.columna;
    return (3*x + y);
  }

  private void cambiarEstado(String value) {
    this.setEstadoJuego(value);
    this.estado = this.getEstadoJuego();
  }
  public String getEstado() {
    return  this.estado;
  }

  public int getTurno() {
    return turno;
  }

  public String imprimirTablero() {
    return "TABLERO INTERNO  " + this.tablero.toString();
  }

  public boolean casillaDisponible( int pos) {
    boolean vacia = false;
    if (this.tablero.charAt(pos) == '0') {
      vacia = true;
    }
    return vacia;
  }

  public void marcarCasilla(int pos, String ficha) {
    this.tablero.deleteCharAt(pos);
    this.tablero.insert(pos, ficha);
  }

  public boolean esGanador(String cadena) {
    boolean existeGanador = false;
    char ficha =  cadena.charAt(0);
    if (fila(ficha) || diagonal( ficha) || columna(ficha)) {
      existeGanador = true;
    }
    return  existeGanador;
  }

  private boolean fila(char ficha) {
    boolean existe = false;
    if (this.tablero.charAt(0) == ficha && this.tablero.charAt(1) == ficha && this.tablero.charAt(2) == ficha) {
      existe = true;
    } else if (this.tablero.charAt(3) == ficha && this.tablero.charAt(4) == ficha && this.tablero.charAt(5) == ficha) {
      existe = true;
    } else if (this.tablero.charAt(6) == ficha && this.tablero.charAt(7) == ficha && this.tablero.charAt(8) == ficha) {
      existe = true;
    }
    return existe;
  }

  private boolean diagonal(char ficha) {
    boolean existeGanador = false;
    if (this.tablero.charAt(0) == ficha && this.tablero.charAt(4) == ficha && this.tablero.charAt(8) == ficha) {
      existeGanador = true;
    } else if (this.tablero.charAt(2) == ficha && this.tablero.charAt(4) == ficha && this.tablero.charAt(6) == ficha) {
      existeGanador = true;
    }
    return existeGanador;
  }

  private boolean columna(char ficha) {
    boolean existeGanador = false;
    if (this.tablero.charAt(0) == ficha && this.tablero.charAt(3) == ficha && this.tablero.charAt(6) == ficha) {
      existeGanador = true;
    } else if (this.tablero.charAt(1) == ficha && this.tablero.charAt(4) == ficha && this.tablero.charAt(7) == ficha) {
      existeGanador = true;
    } else if (this.tablero.charAt(2) == ficha && this.tablero.charAt(5) == ficha && this.tablero.charAt(8) == ficha) {
      existeGanador = true;
    }
    return existeGanador;
  }

  public boolean existeCasillaVacia() {
    boolean res =  false;
    int pos =  this.tablero.indexOf("0");
    if ( pos > -1 ) {
      res = true;
    }
    return  res;
  }


  public void setEstadoJuego(String estadoJuego) {
    this.estadoJuego = estadoJuego;
  }
  public String getEstadoJuego() {
    return  this.estadoJuego;
  }
  public String getHumano() {
    return  this.humano;
  }
  public String getCpu() {
    return  this.cpu;
  }

  public int tamano() {
    return  this.tablero.length();
  }

}
