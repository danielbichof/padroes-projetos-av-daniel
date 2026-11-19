package azure;

import dominio.MaquinaVirtual;

final class AzureMaquinaVirtual implements MaquinaVirtual {
    static final double CUSTO_HORA = 0.12;

    private final String id;

    AzureMaquinaVirtual(String id) {
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
    public double custoPorHora() {
        return CUSTO_HORA;
    }
}
