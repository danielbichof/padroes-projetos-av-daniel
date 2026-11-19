package fatura;

/** Seguro de transporte: 1,5% do valor de venda. */
public class SeguroTransporte extends ServicoAdicional {
    public SeguroTransporte(Fatura fatura) {
        super(fatura);
    }

    @Override
    protected String descricaoServico() {
        return "Seguro de transporte (1,5%)";
    }

    @Override
    protected double custoServico() {
        return fatura.valorVenda() * 0.015;
    }
}
