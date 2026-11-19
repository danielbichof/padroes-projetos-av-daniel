package dominio;

/** RF04: fórmula do custo mensal estimado. */
public final class CalculoCusto {
    public static final int HORAS_NO_MES = 730;

    private CalculoCusto() {
    }

    public static double mensal(int qtdMaquinas, double custoHora, int gb, double custoGb, double custoFila) {
        return qtdMaquinas * custoHora * HORAS_NO_MES + gb * custoGb + custoFila;
    }
}
