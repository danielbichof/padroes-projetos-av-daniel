package fatura;

/** Certificado de autenticidade: R$ 300,00 fixos. */
public class CertificadoAutenticidade extends ServicoAdicional {
    public CertificadoAutenticidade(Fatura fatura) {
        super(fatura);
    }

    @Override
    protected String descricaoServico() {
        return "Certificado de autenticidade";
    }

    @Override
    protected double custoServico() {
        return 300.00;
    }
}
