package pt.ulusofona.lp2.deisiGreatGame;

import java.util.ArrayList;
import java.util.Arrays;

public class Programmer {
    int id;
    String nome;
    ArrayList<String> linguagensFavoritas;
    ProgrammerColor cor;
    String estado = "Em Jogo";
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

    public int getPosPlayer(){
        return this.posPlayer;
    }

    public String getEstado(){
        return this.estado;
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
            outputLinguagensFavoritas.append("NÃ£o tem");
        }else{

            for (int i = 0; i < linguagensFavoritas.size() ; i++) {
                if (i==0){
                    outputLinguagensFavoritas.append(linguagensFavoritas.get(i));
                }else{
                    outputLinguagensFavoritas.append(";").append(linguagensFavoritas.get(i));
                }
            }
        }

        //expected:<...o | 6 | Common Lisp;[ ]PHP | Em Jogo>
        //but was:<...o | 6 | Common Lisp;[]PHP | Em Jogo>



        String[] stringNormal = outputLinguagensFavoritas.toString().split(";");
        Arrays.sort(stringNormal);


        StringBuilder output = new StringBuilder();


        for (int i = 0; i < stringNormal.length; i++) {
            if(i==0){
                output.append(stringNormal[i]);
            } else {
                output.append(";").append(stringNormal[i]);
            }
        }


        String resultado = output.toString().replace(";","; ");

        return getId() + " | " + getName() + " | " + getPosPlayer() + " | " + resultado + " | " + getEstado();
    }

}
