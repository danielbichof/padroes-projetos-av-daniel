import dominio.Provisionador;
import dominio.SolicitacaoAmbiente;
import suporte.HistoricoAuditoria;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        List<SolicitacaoAmbiente> solicitacoes = List.of(
                new SolicitacaoAmbiente("ambiente-web", "Aws", 2, 100),
                new SolicitacaoAmbiente("ambiente-dados", "Azure", 1, 200),
                new SolicitacaoAmbiente("ambiente-api", "Aws", 1, 50),
                new SolicitacaoAmbiente("ambiente-gigante", "Aws", 10, 500)); // estoura o orçamento

        // Todas as threads esperam a largada para disputar Config e histórico ao mesmo tempo.
        CountDownLatch largada = new CountDownLatch(1);
        List<Thread> threads = new ArrayList<>();
        for (SolicitacaoAmbiente s : solicitacoes) {
            Thread t = new Thread(() -> {
                try {
                    largada.await();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
                new Provisionador(SeletorProvedor.escolher(s.provedor())).provisionar(s);
            }, "provisionamento-" + s.nome());
            threads.add(t);
            t.start();
        }
        largada.countDown();
        for (Thread t : threads) {
            t.join();
        }

        System.out.println("=== Histórico de auditoria ===");
        HistoricoAuditoria.getInstance().listar().forEach(System.out::println);
    }
}
