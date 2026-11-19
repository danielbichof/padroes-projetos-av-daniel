package leilao;

import comissao.RegraComissao;

/**
 * Categoria de lote: nome e regra de comissão padrão. É dado, não enum com
 * switch, então uma categoria nova é só uma nova instância com sua regra (RNF02).
 */
public record Categoria(String nome, RegraComissao regraPadrao) {
}
