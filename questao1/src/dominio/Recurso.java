package dominio;

/** Produto abstrato comum a todos os recursos de um ambiente. */
public interface Recurso {
    String id();

    String provedor();
}
