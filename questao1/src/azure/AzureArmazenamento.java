package azure;

import dominio.Armazenamento;

final class AzureArmazenamento implements Armazenamento {
    static final double CUSTO_GB = 0.02;

    private final String id;
    private final int gb;

    AzureArmazenamento(String id, int gb) {
        this.id = id;
        this.gb = gb;
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
    public int gb() {
        return gb;
    }

    @Override
    public double custoPorGb() {
        return CUSTO_GB;
    }
}
