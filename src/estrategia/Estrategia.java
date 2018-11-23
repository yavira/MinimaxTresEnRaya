package estrategia;

import tresenraya.Posicion;

public interface Estrategia {
  Posicion hacerJugada();
  void registrarJuagadaContraria(Posicion posicion);
  void reiniciarJuego();

}
