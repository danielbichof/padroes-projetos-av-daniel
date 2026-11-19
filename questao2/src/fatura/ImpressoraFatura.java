package fatura;

import util.Moeda;

/** RF09/RNF04: trata igual qualquer Fatura, sem checar quais serviços existem. */
public final class ImpressoraFatura {
    private ImpressoraFatura() {
    }

    public static void imprimir(Fatura fatura) {
        System.out.println("--- Fatura do comprador: " + fatura.lote() + " ---");
        for (LinhaFatura linha : fatura.linhas()) {
            System.out.println(String.format("%-32s", linha.descricao()) + Moeda.formatar(linha.custo()));
        }
        System.out.println(String.format("%-32s", "TOTAL A PAGAR") + Moeda.formatar(fatura.total()));
        System.out.println();
    }
}
