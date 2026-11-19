package comissao;

import util.Moeda;

/** RF10: acerto com o vendedor. Recebe só números, sem depender de Lote (RNF05). */
public final class AcertoVendedor {
    private AcertoVendedor() {
    }

    public static void imprimir(String nomeLote, double valorVenda, double comissao) {
        System.out.println("--- Acerto do vendedor: " + nomeLote + " ---");
        System.out.println("Valor de venda:   " + Moeda.formatar(valorVenda));
        System.out.println("Comissão cobrada: " + Moeda.formatar(comissao));
        System.out.println("Líquido a repassar: " + Moeda.formatar(valorVenda - comissao));
        System.out.println();
    }
}
