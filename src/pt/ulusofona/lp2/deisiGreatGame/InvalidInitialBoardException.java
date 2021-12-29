package pt.ulusofona.lp2.deisiGreatGame;

public class InvalidInitialBoardException extends Exception {

    String mensagem;
    int invalidAbyssOrTool;
    int numeroAbyssOuTool;

    InvalidInitialBoardException(String mensagem, int invalidAbyssOrTool, int numeroAbyssOuTool) {
        this.mensagem = mensagem;
        this.invalidAbyssOrTool = invalidAbyssOrTool;
        this.numeroAbyssOuTool=numeroAbyssOuTool;

        if (this.invalidAbyssOrTool == 1){
            getTypeId();
            isInvalidAbyss();
        }else if (this.invalidAbyssOrTool == 2){
            getTypeId();
            isInvalidTool();
        }

    }

    public InvalidInitialBoardException(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMessage(){
        return mensagem;
    }

    public boolean isInvalidAbyss(){
        return true;
    }

    public boolean isInvalidTool(){
        return true;
    }

    public String getTypeId(){
        if (invalidAbyssOrTool == 1 || invalidAbyssOrTool == 2){
            return String.valueOf(numeroAbyssOuTool);
        }
        return null;
    }
}
