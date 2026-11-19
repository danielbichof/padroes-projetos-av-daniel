package aws;

import dominio.Fila;

final class AwsFila implements Fila {
    static final double CUSTO_MENSAL = 0.40;

    private final String id;

    AwsFila(String id) {
        this.id = id;
    }

    @Override
    public String id() {
        return id;
    }

    @Override
    public String provedor() {
        return "Aws";
    }

    @Override
    public double custoMensalFixo() {
        return CUSTO_MENSAL;
    }
}
