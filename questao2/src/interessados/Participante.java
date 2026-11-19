package interessados;

import leilao.Lance;
import leilao.Licitante;
import leilao.Lote;
import leilao.ObservadorLance;
import leilao.ResultadoLance;
import util.Moeda;

/** Observador concreto: mostra o nome do lote e o novo valor. Também dá lances. */
public class Participante implements Licitante, ObservadorLance {
    private final String nome;

    public Participante(String nome) {
        this.nome = nome;
    }

    @Override
    public String nome() {
        return nome;
    }

    public void acompanhar(Lote lote) {
        lote.anexar(this);
    }

    public void deixarDeAcompanhar(Lote lote) {
        lote.desanexar(this);
    }

    public ResultadoLance darLance(Lote lote, double valor) {
        return lote.darLance(this, valor);
    }

    @Override
    public void atualizar(Lote lote, Lance lance) {
        System.out.println("   [" + nome + "] Lote '" + lote.nome() + "': novo lance de "
                + Moeda.formatar(lance.valor()));
    }
}
