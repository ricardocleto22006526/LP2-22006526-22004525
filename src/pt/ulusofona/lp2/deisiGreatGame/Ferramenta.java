package pt.ulusofona.lp2.deisiGreatGame;

public class Ferramenta extends AbismoEFerramenta {

    //Imagens das ferramentas
    @Override
    String imagemTabuleiro() {
        if (titulo.equals("Herança")){
            return "inheritance";
        }

        if (titulo.equals("Programação funcional")){
            return "functional";
        }

        if (titulo.equals("Testes unitários")){
            return "unit-tests";
        }

        if (titulo.equals("Tratamento de Excepções")){
            return "catch";
        }

        if (titulo.equals("IDE")){
            return "IDE";
        }

        if (titulo.equals("Ajuda Do Professor")){
            return "ajuda-professor";
        }

        return null;
    }

    //Titulos das ferramentas
    public Ferramenta(int id) {
        super(id);
        this.id=id;

        if (id==0){
            this.titulo = "Herança";
        }
        if (id==1){
            this.titulo = "Programação funcional";
        }
        if (id==2){
            this.titulo = "Testes unitários";
        }
        if (id==3){
            this.titulo = "Tratamento de Excepções";
        }
        if (id==4){
            this.titulo = "IDE";
        }
        if (id==5){
            this.titulo = "Ajuda Do Professor";
        }
    }

}
