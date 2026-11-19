package comissao;

/** Strategy: algoritmo de comissão intercambiável em tempo de execução (RNF02). */
public interface RegraComissao {
    String nome();

    double calcular(double valorVenda);
}
