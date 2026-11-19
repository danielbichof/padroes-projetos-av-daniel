package suporte;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** RF07: registro imutável, então não pode ser corrompido depois de criado. */
public record RegistroAuditoria(LocalDateTime dataHora, String ambiente, String provedor,
                                Resultado resultado, String motivo) {
    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss.SSS");

    @Override
    public String toString() {
        return dataHora.format(FORMATO) + " | " + ambiente + " | " + provedor + " | " + resultado + " (" + motivo + ")";
    }
}
