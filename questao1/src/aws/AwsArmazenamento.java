package aws;

import dominio.Armazenamento;

final class AwsArmazenamento implements Armazenamento {
    static final double CUSTO_GB = 0.023;

    private final String id;
    private final int gb;

    AwsArmazenamento(String id, int gb) {
        this.id = id;
        this.gb = gb;
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
    public int gb() {
        return gb;
    }

    @Override
    public double custoPorGb() {
        return CUSTO_GB;
    }
}
