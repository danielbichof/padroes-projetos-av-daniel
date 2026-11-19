package leilao;

/** Quem dá lances. O Lote só enxerga esta interface, nunca a classe Participante (RNF01). */
public interface Licitante {
    String nome();
}
