import comissao.AcertoVendedor;
import comissao.ComissaoArte;
import comissao.ComissaoEletronicos;
import fatura.CertificadoAutenticidade;
import fatura.EmbalagemEspecial;
import fatura.EntregaExpressa;
import fatura.Fatura;
import fatura.FaturaBase;
import fatura.ImpressoraFatura;
import fatura.SeguroTransporte;
import interessados.Auditor;
import interessados.PainelLeiloeiro;
import interessados.Participante;
import leilao.Categoria;
import leilao.Lote;
import leilao.ResultadoLance;
import util.Moeda;

public class Main {
    public static void main(String[] args) {
        Categoria arte = new Categoria("Arte", new ComissaoArte());
        Categoria eletronicos = new Categoria("Eletrônicos", new ComissaoEletronicos());

        Lote quadro = new Lote("Pintura Abstrata", arte, 10_000.00, 12_000.00);
        Lote notebook = new Lote("Notebook Gamer", eletronicos, 2_000.00, 3_500.00);

        Participante ana = new Participante("Ana");
        Participante bruno = new Participante("Bruno");
        Participante carla = new Participante("Carla");
        PainelLeiloeiro painel = new PainelLeiloeiro();
        Auditor auditor = new Auditor();

        // Acompanhamento
        for (Lote l : new Lote[]{quadro, notebook}) {
            l.anexar(painel);
            l.anexar(auditor);
        }
        ana.acompanhar(quadro);
        bruno.acompanhar(quadro);
        carla.acompanhar(quadro);
        ana.acompanhar(notebook);
        bruno.acompanhar(notebook);

        System.out.println("##### Lances: Pintura Abstrata (inicial R$ 10.000,00) #####");
        lance(ana, quadro, 10_200.00);    // recusado: menos de 5%
        lance(ana, quadro, 10_500.00);    // aceito: exatamente 5%
        lance(bruno, quadro, 11_000.00);  // recusado: mínimo 11.025,00
        lance(bruno, quadro, 11_025.00);  // aceito
        System.out.println("-> Carla deixa de acompanhar a Pintura Abstrata");
        carla.deixarDeAcompanhar(quadro);
        lance(ana, quadro, 13_000.00);    // aceito, Carla não é avisada

        System.out.println("\n##### Lances: Notebook Gamer (inicial R$ 2.000,00) #####");
        lance(bruno, notebook, 2_100.00); // aceito
        lance(ana, notebook, 2_200.00);   // recusado: mínimo 2.205,00
        lance(ana, notebook, 2_300.00);   // aceito

        System.out.println("\n##### Campanha de comissão #####");
        System.out.println("Regra atual do quadro: " + quadro.regraComissao().nome());
        quadro.trocarRegraComissao(new ComissaoEletronicos());
        System.out.println("Regra trocada em tempo de execução para: " + quadro.regraComissao().nome());

        System.out.println("\n##### Encerramento #####");
        System.out.println(quadro.nome() + ": " + quadro.encerrar());
        System.out.println(notebook.nome() + ": " + notebook.encerrar());
        lance(carla, quadro, 20_000.00);  // lote encerrado

        System.out.println("\n##### Acerto do vendedor #####");
        AcertoVendedor.imprimir(quadro.nome(), quadro.valorVenda(), quadro.comissao());

        System.out.println("##### Faturas do comprador (" + quadro.vencedor().nome() + ") #####");
        Fatura simples = new FaturaBase(quadro.nome(), quadro.valorVenda());
        ImpressoraFatura.imprimir(simples); // sem serviços, mesmo código de impressão

        Fatura completa = new FaturaBase(quadro.nome(), quadro.valorVenda());
        completa = new SeguroTransporte(completa);
        completa = new CertificadoAutenticidade(completa);
        completa = new EmbalagemEspecial(completa);
        completa = new EmbalagemEspecial(completa); // contratada duas vezes
        completa = new EntregaExpressa(completa);
        ImpressoraFatura.imprimir(completa);

        System.out.println("##### Registros do auditor #####");
        auditor.registros().forEach(System.out::println);
    }

    private static void lance(Participante p, Lote lote, double valor) {
        ResultadoLance r = p.darLance(lote, valor);
        System.out.println((r.aceito() ? "ACEITO   " : "RECUSADO ") + p.nome() + " -> " + lote.nome()
                + " " + Moeda.formatar(valor) + (r.aceito() ? "" : ": " + r.mensagem()));
    }
}
