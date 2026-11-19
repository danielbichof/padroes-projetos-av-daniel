package leilao;

/** Observer: qualquer interessado em lances implementa esta interface (RNF01). */
public interface ObservadorLance {
    void atualizar(Lote lote, Lance lance);
}
