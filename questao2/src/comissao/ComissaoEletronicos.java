package comissao;

/** Eletrônicos: 12% do valor, com mínimo de R$ 50,00. */
public class ComissaoEletronicos implements RegraComissao {
    private static final double MINIMO = 50.00;

    @Override
    public String nome() {
        return "Eletrônicos (12%, mínimo R$ 50,00)";
    }

    @Override
    public double calcular(double valorVenda) {
        return Math.max(valorVenda * 0.12, MINIMO);
    }
}
