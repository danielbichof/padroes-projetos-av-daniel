package azure;

import dominio.Fila;

final class AzureFila implements Fila {
    static final double CUSTO_MENSAL = 0.50;

    private final String id;

    AzureFila(String id) {
        this.id = id;
    }

    @Override
    public String id() {
        return id;
    }

    @Override
    public String provedor() {
        return "Azure";
    }

    @Override
    public double custoMensalFixo() {
        return CUSTO_MENSAL;
    }
}
