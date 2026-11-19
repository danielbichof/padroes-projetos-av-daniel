package suporte;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

/**
 * Singleton (RNF04): o config.properties é lido uma única vez, na primeira
 * chamada a getInstance(). Initialization-on-demand holder: o carregamento
 * da classe Holder é thread-safe e preguiçoso, sem precisar de synchronized.
 */
public final class Config {
    private static final class Holder {
        private static final Config INSTANCIA = new Config();
    }

    private final String regiaoPadrao;
    private final double orcamentoMensalMaximo;

    private Config() {
        Properties props = new Properties();
        Path arquivo = localizar();
        try (Reader leitor = Files.newBufferedReader(arquivo, StandardCharsets.UTF_8)) {
            props.load(leitor);
        } catch (IOException e) {
            throw new IllegalStateException("Não foi possível ler " + arquivo, e);
        }
        this.regiaoPadrao = props.getProperty("regiao.padrao");
        this.orcamentoMensalMaximo = Double.parseDouble(props.getProperty("orcamento.mensal.maximo"));
        System.out.println("[Config] " + arquivo + " lido uma única vez");
    }

    public static Config getInstance() {
        return Holder.INSTANCIA;
    }

    private static Path localizar() {
        for (String candidato : new String[]{"config.properties", "questao1/config.properties"}) {
            Path p = Path.of(candidato);
            if (Files.exists(p)) {
                return p;
            }
        }
        throw new IllegalStateException("config.properties não encontrado (execute a partir de questao1/)");
    }

    public String regiaoPadrao() {
        return regiaoPadrao;
    }

    public double orcamentoMensalMaximo() {
        return orcamentoMensalMaximo;
    }
}
