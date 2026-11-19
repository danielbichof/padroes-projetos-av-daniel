package dominio;

/**
 * Abstract Factory: cria a família completa de recursos de UM provedor.
 * Quem recebe uma instância desta interface só consegue obter recursos
 * do mesmo provedor, o que impede combinações misturadas (RNF01).
 */
public interface ProvedorFactory {
    String nome();

    MaquinaVirtual criarMaquinaVirtual();

    Armazenamento criarArmazenamento(int gb);

    Fila criarFila();

    // Tarifas, para estimar o custo sem criar nenhum recurso (RF05).
    double custoHoraMaquina();

    double custoGbArmazenamento();

    double custoMensalFila();
}
