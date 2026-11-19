import aws.AwsFactory;
import azure.AzureFactory;
import dominio.ProvedorFactory;

import java.util.Map;

/**
 * Único ponto que conhece as fábricas concretas. Para incluir o Gcp (RNF03)
 * basta criar o pacote gcp e registrar a nova fábrica aqui.
 * Uma instância por provedor, para a numeração sequencial continuar entre pedidos.
 */
public final class SeletorProvedor {
    private static final Map<String, ProvedorFactory> FABRICAS = Map.of(
            "aws", new AwsFactory(),
            "azure", new AzureFactory());

    private SeletorProvedor() {
    }

    public static ProvedorFactory escolher(String provedor) {
        ProvedorFactory fabrica = FABRICAS.get(provedor.toLowerCase());
        if (fabrica == null) {
            throw new IllegalArgumentException("Provedor desconhecido: " + provedor);
        }
        return fabrica;
    }
}
