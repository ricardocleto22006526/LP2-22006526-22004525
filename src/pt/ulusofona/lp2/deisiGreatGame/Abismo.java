package pt.ulusofona.lp2.deisiGreatGame;

public class Abismo extends AbismoEFerramenta {

    //Imagens dos abismos
    @Override
    public String imagemTabuleiro() {

        if (titulo.equals("Erro de sintaxe")){
            return "syntax.png";
        }

        if (titulo.equals("Erro de lógica")){
            return "logic.png";
        }

        if (titulo.equals("Exception")){
            return "exception.png";
        }

        if (titulo.equals("File Not Found Exception")){
            return "file-not-found-exception.png";
        }

        if (titulo.equals("Crash (aka Rebentaço)")){
            return "crash.png";
        }

        if (titulo.equals("Duplicated Code")){
            return "duplicated-code.png";
        }

        if (titulo.equals("Efeitos secundários")){
            return "secondary-effects.png";
        }

        if (titulo.equals("Blue Screen of Death")){
            return "bsod.png";
        }

        if (titulo.equals("Ciclo infinito")){
            return "infinite-loop.png";
        }

        if (titulo.equals("Segmentation Fault")){
            return "core-dumped.png";
        }

        return null;
    }

    //Titulos dos abismos
    public Abismo(int id) {
        super(id);
        this.id=id;

        if (id==0){
            this.titulo = "Erro de sintaxe";
        }
        if (id==1){
            this.titulo = "Erro de lógica";
        }
        if (id==2){
            this.titulo = "Exception";
        }
        if (id==3){
            this.titulo = "File Not Found Exception";
        }
        if (id==4){
            this.titulo = "Crash (aka Rebentaço)";
        }
        if (id==5){
            this.titulo = "Duplicated Code";
        }
        if (id==6){
            this.titulo = "Efeitos secundários";
        }
        if (id==7){
            this.titulo = "Blue Screen of Death";
        }
        if (id==8){
            this.titulo = "Ciclo infinito";
        }
        if (id==9){
            this.titulo = "Segmentation Fault";
        }
    }

}
