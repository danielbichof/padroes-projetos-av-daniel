package aws;

import dominio.Armazenamento;
import dominio.Fila;
import dominio.MaquinaVirtual;
import dominio.ProvedorFactory;

import java.util.concurrent.atomic.AtomicInteger;

/** Fábrica concreta da Aws. Os contadores são atômicos: ids únicos mesmo com threads. */
public class AwsFactory implements ProvedorFactory {
    private final AtomicInteger seqMaquina = new AtomicInteger();
    private final AtomicInteger seqArmazenamento = new AtomicInteger();
    private final AtomicInteger seqFila = new AtomicInteger();

    @Override
    public String nome() {
        return "Aws";
    }

    @Override
    public MaquinaVirtual criarMaquinaVirtual() {
        return new AwsMaquinaVirtual("i-" + seqMaquina.incrementAndGet());
    }

    @Override
    public Armazenamento criarArmazenamento(int gb) {
        return new AwsArmazenamento("s3://" + seqArmazenamento.incrementAndGet(), gb);
    }

    @Override
    public Fila criarFila() {
        return new AwsFila("sqs-" + seqFila.incrementAndGet());
    }

    @Override
    public double custoHoraMaquina() {
        return AwsMaquinaVirtual.CUSTO_HORA;
    }

    @Override
    public double custoGbArmazenamento() {
        return AwsArmazenamento.CUSTO_GB;
    }

    @Override
    public double custoMensalFila() {
        return AwsFila.CUSTO_MENSAL;
    }
}
