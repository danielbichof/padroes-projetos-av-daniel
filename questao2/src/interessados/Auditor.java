package interessados;

import leilao.Lance;
import leilao.Lote;
import leilao.ObservadorLance;
import util.Moeda;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/** Observador concreto: registra todos os lances aceitos com data e hora. */
public class Auditor implements ObservadorLance {
    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    private final List<String> registros = new ArrayList<>();

    @Override
    public void atualizar(Lote lote, Lance lance) {
        String linha = LocalDateTime.now().format(FORMATO) + " | " + lote.nome() + " | "
                + lance.licitante().nome() + " | " + Moeda.formatar(lance.valor());
        registros.add(linha);
        System.out.println("   [Auditor] registrado: " + linha);
    }

    public List<String> registros() {
        return List.copyOf(registros);
    }
}
