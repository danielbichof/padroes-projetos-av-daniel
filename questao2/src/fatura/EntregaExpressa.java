package fatura;

/** Entrega expressa: R$ 450,00 fixos. */
public class EntregaExpressa extends ServicoAdicional {
    public EntregaExpressa(Fatura fatura) {
        super(fatura);
    }

    @Override
    protected String descricaoServico() {
        return "Entrega expressa";
    }

    @Override
    protected double custoServico() {
        return 450.00;
    }
}
