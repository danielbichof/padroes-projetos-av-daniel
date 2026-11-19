package comissao;

/** Veículos: 8% do valor, limitada a R$ 5.000,00. */
public class ComissaoVeiculos implements RegraComissao {
    private static final double TETO = 5000.00;

    @Override
    public String nome() {
        return "Veículos (8%, teto R$ 5.000,00)";
    }

    @Override
    public double calcular(double valorVenda) {
        return Math.min(valorVenda * 0.08, TETO);
    }
}
