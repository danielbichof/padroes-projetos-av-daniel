package dominio;

import suporte.Moeda;

import java.util.List;

/** Ambiente provisionado: máquinas, armazenamento e fila, sempre do mesmo provedor. */
public final class Ambiente {
    private final String nome;
    private final String regiao;
    private final String provedor;
    private final List<MaquinaVirtual> maquinas;
    private final Armazenamento armazenamento;
    private final Fila fila;

    public Ambiente(String nome, String regiao, List<MaquinaVirtual> maquinas,
                    Armazenamento armazenamento, Fila fila) {
        this.provedor = armazenamento.provedor();
        // Segunda barreira para o RNF01, além da própria Abstract Factory.
        boolean mesmoProvedor = fila.provedor().equals(provedor)
                && maquinas.stream().allMatch(m -> m.provedor().equals(provedor));
        if (!mesmoProvedor) {
            throw new IllegalArgumentException("Recursos de provedores diferentes no ambiente " + nome);
        }
        this.nome = nome;
        this.regiao = regiao;
        this.maquinas = List.copyOf(maquinas);
        this.armazenamento = armazenamento;
        this.fila = fila;
    }

    public String nome() {
        return nome;
    }

    public String provedor() {
        return provedor;
    }

    public double custoMensal() {
        double custoHora = maquinas.isEmpty() ? 0 : maquinas.get(0).custoPorHora();
        return CalculoCusto.mensal(maquinas.size(), custoHora, armazenamento.gb(),
                armazenamento.custoPorGb(), fila.custoMensalFixo());
    }

    /** RF06: resumo montado em uma única string para sair inteiro mesmo com várias threads. */
    public String resumo() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Ambiente ").append(nome).append(" ===\n");
        sb.append("Provedor: ").append(provedor).append('\n');
        sb.append("Região: ").append(regiao).append('\n');
        for (MaquinaVirtual m : maquinas) {
            sb.append("Máquina virtual: ").append(m.id()).append('\n');
        }
        sb.append("Armazenamento: ").append(armazenamento.id()).append(" (").append(armazenamento.gb()).append(" GB)\n");
        sb.append("Fila: ").append(fila.id()).append('\n');
        sb.append("Custo mensal estimado: ").append(Moeda.formatar(custoMensal())).append('\n');
        return sb.toString();
    }
}
