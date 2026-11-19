package fatura;

import java.util.List;

/** Component do Decorator: a fatura base e todos os serviços compartilham esta interface. */
public interface Fatura {
    String lote();

    double valorVenda();

    List<LinhaFatura> linhas();

    default double total() {
        return linhas().stream().mapToDouble(LinhaFatura::custo).sum();
    }
}
