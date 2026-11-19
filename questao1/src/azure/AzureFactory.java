package azure;

import dominio.Armazenamento;
import dominio.Fila;
import dominio.MaquinaVirtual;
import dominio.ProvedorFactory;

import java.util.concurrent.atomic.AtomicInteger;

/** Fábrica concreta do Azure. */
public class AzureFactory implements ProvedorFactory {
    private final AtomicInteger seqMaquina = new AtomicInteger();
    private final AtomicInteger seqArmazenamento = new AtomicInteger();
    private final AtomicInteger seqFila = new AtomicInteger();

    @Override
    public String nome() {
        return "Azure";
    }

    @Override
    public MaquinaVirtual criarMaquinaVirtual() {
        return new AzureMaquinaVirtual("vm-" + seqMaquina.incrementAndGet());
    }

    @Override
    public Armazenamento criarArmazenamento(int gb) {
        return new AzureArmazenamento("blob://" + seqArmazenamento.incrementAndGet(), gb);
    }

    @Override
    public Fila criarFila() {
        return new AzureFila("sb-" + seqFila.incrementAndGet());
    }

    @Override
    public double custoHoraMaquina() {
        return AzureMaquinaVirtual.CUSTO_HORA;
    }

    @Override
    public double custoGbArmazenamento() {
        return AzureArmazenamento.CUSTO_GB;
    }

    @Override
    public double custoMensalFila() {
        return AzureFila.CUSTO_MENSAL;
    }
}
