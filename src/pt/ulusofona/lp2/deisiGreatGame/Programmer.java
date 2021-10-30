package pt.ulusofona.lp2.deisiGreatGame;

import java.util.ArrayList;

public class Programmer {
    int id;
    String nome;
    ArrayList<String> linguagensFavoritas;
    ProgrammerColor cor;
    String estado = "Em jogo";
    int posPlayer = 1;

    public Programmer(String nome, int id, ProgrammerColor cor, ArrayList<String> linguagensFavoritas,int posPlayer) {
        this.id = id;
        this.nome = nome;
        this.linguagensFavoritas = linguagensFavoritas;
        this.cor = cor;
        this.posPlayer = posPlayer;
    }

    public Programmer(int id, String nome, ArrayList<String> linguagensFavoritas, ProgrammerColor cor) {
        this.id = id;
        this.nome = nome;
        this.linguagensFavoritas = linguagensFavoritas;
        this.cor = cor;
    }

    public Programmer() {
    }

    public int getId(){
        return this.id;
    }

    public String getName(){
        return this.nome;
    }

    public ProgrammerColor getColor(){
        return this.cor;
    }

    int getPosPlayer(){
        return this.posPlayer;
    }

    void andaParaAFrente(int posicoesAAlterar){
       this.posPlayer+= posicoesAAlterar;
    }

    void andaParaTras(int tamanhoDoTabuleiro,int posicoesAAlterar){
        int posicaoInicial = this.posPlayer + posicoesAAlterar;
        this.posPlayer = tamanhoDoTabuleiro - (posicaoInicial-tamanhoDoTabuleiro);
    }

    @Override
    public String toString() {

        StringBuilder outputLinguagensFavoritas = new StringBuilder();

        if(linguagensFavoritas==null || linguagensFavoritas.size()==0){
            //OU CRIAR -> Programmer a = new Programmer();
            outputLinguagensFavoritas.append("Não tem");
        }else{

            for (int i = 0; i < linguagensFavoritas.size() ; i++) {
                if (linguagensFavoritas.size()==1){
                    outputLinguagensFavoritas.append(linguagensFavoritas.get(i));
                }else{
                    outputLinguagensFavoritas.append(linguagensFavoritas.get(i)).append("; ");
                }
            }
        }

        return id + " | " + nome + " | " + posPlayer + " | " + outputLinguagensFavoritas + " | " + estado;
    }

}
