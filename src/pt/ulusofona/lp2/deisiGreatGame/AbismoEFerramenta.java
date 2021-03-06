package pt.ulusofona.lp2.deisiGreatGame;

abstract public class AbismoEFerramenta {
    int id;
    String titulo;

    public AbismoEFerramenta(int id) {
        this.id = id;
    }

    public AbismoEFerramenta(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    abstract String imagemTabuleiro();
    abstract String tituloDoAbismoOUFerramenta();

}
