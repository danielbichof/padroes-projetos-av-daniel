package suporte;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Singleton (RNF05): histórico único e acessível globalmente. Todas as
 * operações são synchronized no mesmo monitor, então execuções simultâneas
 * não perdem nem corrompem registros e a ordem de inserção é preservada.
 */
public final class HistoricoAuditoria {
    private static final class Holder {
        private static final HistoricoAuditoria INSTANCIA = new HistoricoAuditoria();
    }

    private final List<RegistroAuditoria> registros = new ArrayList<>();

    private HistoricoAuditoria() {
    }

    public static HistoricoAuditoria getInstance() {
        return Holder.INSTANCIA;
    }

    public synchronized void registrar(String ambiente, String provedor, Resultado resultado, String motivo) {
        // O horário é tirado dentro do lock: ordem da lista = ordem cronológica.
        registros.add(new RegistroAuditoria(LocalDateTime.now(), ambiente, provedor, resultado, motivo));
    }

    public synchronized List<RegistroAuditoria> listar() {
        return List.copyOf(registros);
    }
}
