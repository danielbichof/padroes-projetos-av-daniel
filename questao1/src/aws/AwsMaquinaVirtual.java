package aws;

import dominio.MaquinaVirtual;

/** Visibilidade de pacote: só a AwsFactory consegue instanciar (RNF06). */
final class AwsMaquinaVirtual implements MaquinaVirtual {
    static final double CUSTO_HORA = 0.10;

    private final String id;

    AwsMaquinaVirtual(String id) {
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
    public double custoPorHora() {
        return CUSTO_HORA;
    }
}
