package dominio;

/** RF03: dados de entrada de um provisionamento. */
public record SolicitacaoAmbiente(String nome, String provedor, int qtdMaquinas, int gbArmazenamento) {
}
