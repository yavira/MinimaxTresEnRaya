import estrategia.MiEstrategia;
import tresenraya.Posicion;

import java.util.Scanner;

public class Main {
  public static  void main(String []args) {
    MiEstrategia juego = new MiEstrategia();
    int pos;
    while (juego.getEstado() != "2") {
      if (juego.getTurno() == 2) {
        Posicion posicion = juego.hacerJugada();
        System.out.println("turno 2 : " + posicion.fila + " " + posicion.columna );
        juego.registrarJugadaContraria(posicion);
//        Scanner entrada = new Scanner(System.in);
//        pos = Integer.parseInt(entrada.nextLine());
//        juego.registrarJugadaContraria(new Posicion((pos) / 3, (pos) % 3));
      } else if (juego.getTurno() == 1) {
        Posicion posicion = juego.hacerJugada();
        System.out.println("turno 1 : " + posicion.fila + " " + posicion.columna );
        juego.registrarJugadaContraria(posicion);
      }
    }
  }
}
