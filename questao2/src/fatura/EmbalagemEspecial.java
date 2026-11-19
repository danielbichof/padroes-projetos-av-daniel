package fatura;

/** Embalagem especial: R$ 180,00 fixos. */
public class EmbalagemEspecial extends ServicoAdicional {
    public EmbalagemEspecial(Fatura fatura) {
        super(fatura);
    }

    @Override
    protected String descricaoServico() {
        return "Embalagem especial";
    }

    @Override
    protected double custoServico() {
        return 180.00;
    }
}
