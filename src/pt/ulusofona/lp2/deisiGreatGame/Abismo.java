package pt.ulusofona.lp2.deisiGreatGame;

public class Abismo extends AbismoEFerramenta {

    //Imagens dos abismos
    @Override
    public String imagemTabuleiro() {

        if (titulo.equals("Erro de sintaxe")){
            return "syntax";
        }

        if (titulo.equals("Erro de lógica")){
            return "logic";
        }

        if (titulo.equals("Exception")){
            return "exception";
        }

        if (titulo.equals("File Not Found Exception")){
            return "file-not-found-exception";
        }

        if (titulo.equals("Crash (aka Rebentaço)")){
            return "crash";
        }

        if (titulo.equals("Duplicated Code")){
            return "duplicated-code";
        }

        if (titulo.equals("Efeitos secundários")){
            return "secondary-effects";
        }

        if (titulo.equals("Blue Screen of Death")){
            return "bsod";
        }

        if (titulo.equals("Ciclo infinito")){
            return "infinite-loop";
        }

        if (titulo.equals("Segmentation Fault")){
            return "core-dumped";
        }

        if (titulo.equals("Vamos Fazer Contas")){
            return "contas";
        }

        return null;
    }

    @Override
    String tituloDoAbismoOUFerramenta() {

        return switch (titulo) {
            case "Erro de sintaxe" -> "Abismo: syntax";
            case "Erro de lógica" ->"Abismo: logic";
            case "Exception" ->"Abismo: exception";
            case "File Not Found Exception" ->"Abismo: file-not-found-exception";
            case "Crash (aka Rebentaço)" ->"Abismo: crash";
            case "Duplicated Code" ->"Abismo: duplicated-code";
            case "Efeitos secundários" ->"Abismo: secondary-effects";
            case "Blue Screen of Death" ->"Abismo: bsod";
            case "Ciclo infinito" ->"Abismo: infinite-loop";
            case "Segmentation Fault" ->"Abismo: core-dumped";
            case "Vamos Fazer Contas" ->"Abismo: contas";
            default -> "";
        };
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
            this.titulo = "Ciclo Infinito";
        }
        if (id==9){
            this.titulo = "Segmentation Fault";
        }
        if (id==10){
            this.titulo = "Vamos Fazer Contas";
        }
    }

}
