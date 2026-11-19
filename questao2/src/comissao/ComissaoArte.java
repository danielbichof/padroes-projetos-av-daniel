package comissao;

/** Arte: 20% do valor. */
public class ComissaoArte implements RegraComissao {
    @Override
    public String nome() {
        return "Arte (20%)";
    }

    @Override
    public double calcular(double valorVenda) {
        return valorVenda * 0.20;
    }
}
