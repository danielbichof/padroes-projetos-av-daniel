package interessados;

import leilao.Lance;
import leilao.Lote;
import leilao.ObservadorLance;
import util.Moeda;

/** Observador concreto: exibe sempre o maior lance e o total de lances do lote. */
public class PainelLeiloeiro implements ObservadorLance {
    @Override
    public void atualizar(Lote lote, Lance lance) {
        System.out.println("   [Painel do leiloeiro] Lote '" + lote.nome() + "': maior lance "
                + Moeda.formatar(lote.lanceAtual()) + " | total de lances: " + lote.totalLances());
    }
}
