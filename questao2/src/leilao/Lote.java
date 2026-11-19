package leilao;

import comissao.RegraComissao;
import util.Moeda;

import java.util.ArrayList;
import java.util.List;

/**
 * Subject do Observer e Context do Strategy.
 * Conhece apenas as interfaces ObservadorLance, Licitante e RegraComissao.
 */
public class Lote {
    private static final double INCREMENTO_MINIMO = 1.05;
    private static final double EPSILON = 1e-9;

    private final String nome;
    private final Categoria categoria;
    private final double precoInicial;
    private final double precoReserva;
    private final List<ObservadorLance> observadores = new ArrayList<>();
    private final List<Lance> lances = new ArrayList<>();
    private RegraComissao regraComissao;
    private double lanceAtual;
    private StatusLote status = StatusLote.ABERTO;

    public Lote(String nome, Categoria categoria, double precoInicial, double precoReserva) {
        this.nome = nome;
        this.categoria = categoria;
        this.precoInicial = precoInicial;
        this.precoReserva = precoReserva;
        this.regraComissao = categoria.regraPadrao();
        this.lanceAtual = precoInicial;
    }

    // ---- Observer ----

    public void anexar(ObservadorLance observador) {
        if (!observadores.contains(observador)) {
            observadores.add(observador);
        }
    }

    public void desanexar(ObservadorLance observador) {
        observadores.remove(observador);
    }

    private void notificar(Lance lance) {
        // Cópia: um observador pode sair do acompanhamento durante a notificação.
        for (ObservadorLance o : new ArrayList<>(observadores)) {
            if (o != lance.licitante()) { // quem deu o lance não é avisado
                o.atualizar(this, lance);
            }
        }
    }

    // ---- Lances ----

    public ResultadoLance darLance(Licitante licitante, double valor) {
        if (status != StatusLote.ABERTO) {
            return new ResultadoLance(false, "lote encerrado, não aceita novos lances");
        }
        double minimo = lanceAtual * INCREMENTO_MINIMO;
        if (valor + EPSILON < minimo) {
            return new ResultadoLance(false, "o lance deve ser pelo menos 5% maior que o atual ("
                    + Moeda.formatar(lanceAtual) + "), ou seja, no mínimo " + Moeda.formatar(minimo));
        }
        Lance lance = new Lance(licitante, valor);
        lances.add(lance);
        lanceAtual = valor;
        notificar(lance);
        return new ResultadoLance(true, "lance aceito");
    }

    // ---- Encerramento ----

    public StatusLote encerrar() {
        if (status != StatusLote.ABERTO) {
            throw new IllegalStateException("Lote já encerrado: " + nome);
        }
        boolean vendido = !lances.isEmpty() && lanceAtual >= precoReserva;
        status = vendido ? StatusLote.VENDIDO : StatusLote.NAO_VENDIDO;
        return status;
    }

    // ---- Strategy ----

    /** RF07: troca a regra em tempo de execução, sem recriar o lote. */
    public void trocarRegraComissao(RegraComissao novaRegra) {
        if (status != StatusLote.ABERTO) {
            throw new IllegalStateException("A regra só pode ser trocada antes do encerramento");
        }
        this.regraComissao = novaRegra;
    }

    public double comissao() {
        return regraComissao.calcular(valorVenda());
    }

    // ---- Consultas ----

    public double valorVenda() {
        if (status != StatusLote.VENDIDO) {
            throw new IllegalStateException("Lote não vendido: " + nome);
        }
        return lanceAtual;
    }

    public String nome() {
        return nome;
    }

    public Categoria categoria() {
        return categoria;
    }

    public RegraComissao regraComissao() {
        return regraComissao;
    }

    public double lanceAtual() {
        return lanceAtual;
    }

    public int totalLances() {
        return lances.size();
    }

    public StatusLote status() {
        return status;
    }

    public Licitante vencedor() {
        return status == StatusLote.VENDIDO ? lances.get(lances.size() - 1).licitante() : null;
    }

    public double precoInicial() {
        return precoInicial;
    }

    public double precoReserva() {
        return precoReserva;
    }
}
