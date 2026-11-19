package dominio;

import suporte.Config;
import suporte.HistoricoAuditoria;
import suporte.Moeda;
import suporte.Resultado;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Cliente da Abstract Factory (RNF02): recebe a fábrica de fora e só conhece
 * as interfaces. Não há if/switch sobre o provedor nem new de classe concreta.
 */
public class Provisionador {
    private final ProvedorFactory fabrica;

    public Provisionador(ProvedorFactory fabrica) {
        this.fabrica = fabrica;
    }

    public Optional<Ambiente> provisionar(SolicitacaoAmbiente solicitacao) {
        Config config = Config.getInstance();
        HistoricoAuditoria historico = HistoricoAuditoria.getInstance();

        double estimado = CalculoCusto.mensal(solicitacao.qtdMaquinas(), fabrica.custoHoraMaquina(),
                solicitacao.gbArmazenamento(), fabrica.custoGbArmazenamento(), fabrica.custoMensalFila());

        if (estimado > config.orcamentoMensalMaximo()) {
            String motivo = "custo estimado " + Moeda.formatar(estimado)
                    + " excede o orçamento de " + Moeda.formatar(config.orcamentoMensalMaximo());
            historico.registrar(solicitacao.nome(), fabrica.nome(), Resultado.RECUSADO, motivo);
            System.out.println("[RECUSADO] " + solicitacao.nome() + " (" + fabrica.nome() + "): " + motivo + "\n");
            return Optional.empty();
        }

        List<MaquinaVirtual> maquinas = new ArrayList<>();
        for (int i = 0; i < solicitacao.qtdMaquinas(); i++) {
            maquinas.add(fabrica.criarMaquinaVirtual());
        }
        Armazenamento armazenamento = fabrica.criarArmazenamento(solicitacao.gbArmazenamento());
        Fila fila = fabrica.criarFila();

        Ambiente ambiente = new Ambiente(solicitacao.nome(), config.regiaoPadrao(), maquinas, armazenamento, fila);
        historico.registrar(solicitacao.nome(), fabrica.nome(), Resultado.ACEITO,
                "custo estimado " + Moeda.formatar(ambiente.custoMensal()) + " dentro do orçamento");
        System.out.println(ambiente.resumo());
        return Optional.of(ambiente);
    }
}
