package fatura;

import java.util.List;

/** ConcreteComponent: só o valor de venda, sem serviços. Recebe dados simples, não um Lote (RNF05). */
public class FaturaBase implements Fatura {
    private final String lote;
    private final double valorVenda;

    public FaturaBase(String lote, double valorVenda) {
        this.lote = lote;
        this.valorVenda = valorVenda;
    }

    @Override
    public String lote() {
        return lote;
    }

    @Override
    public double valorVenda() {
        return valorVenda;
    }

    @Override
    public List<LinhaFatura> linhas() {
        return List.of(new LinhaFatura("Valor de venda", valorVenda));
    }
}
