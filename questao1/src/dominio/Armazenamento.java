package dominio;

/** Produto abstrato B da Abstract Factory. */
public interface Armazenamento extends Recurso {
    int gb();

    double custoPorGb();
}
