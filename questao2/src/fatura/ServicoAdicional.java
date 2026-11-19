package fatura;

import java.util.ArrayList;
import java.util.List;

/**
 * Decorator abstrato: envolve outra Fatura e acrescenta uma linha com a
 * descrição e o custo do serviço. Serviços empilham em qualquer ordem e quantidade.
 */
public abstract class ServicoAdicional implements Fatura {
    protected final Fatura fatura;

    protected ServicoAdicional(Fatura fatura) {
        this.fatura = fatura;
    }

    protected abstract String descricaoServico();

    protected abstract double custoServico();

    @Override
    public String lote() {
        return fatura.lote();
    }

    @Override
    public double valorVenda() {
        return fatura.valorVenda();
    }

    @Override
    public List<LinhaFatura> linhas() {
        List<LinhaFatura> linhas = new ArrayList<>(fatura.linhas());
        linhas.add(new LinhaFatura(descricaoServico(), custoServico()));
        return linhas;
    }
}
