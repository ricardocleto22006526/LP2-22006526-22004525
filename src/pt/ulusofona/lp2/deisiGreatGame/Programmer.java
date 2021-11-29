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
    Abismo abismo;
    ArrayList<Ferramenta> ferramentas = new ArrayList<>();

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

    public Programmer(ArrayList<Ferramenta> ferramentas) {
        this.ferramentas = ferramentas;
    }

    public Programmer(Abismo abismo) {
        this.abismo = abismo;
    }

    public ArrayList<Ferramenta> getFerramentas() {
        return ferramentas;
    }
    public boolean getFerramentas(int idAEntrar) {
        for (int i = 0; i < ferramentas.size() ; i++) {
            if (ferramentas.get(i).id == idAEntrar){
                return true;
            }
        }
        return false;
    }

    public void adicionaFerramenta(Ferramenta ferramenta){
        this.ferramentas.add(ferramenta);
    }

    public void removeFerramenta(int idAEntrar){
        for (int i = 0; i < ferramentas.size() ; i++) {
            if (ferramentas.get(i).id == idAEntrar){
                ferramentas.remove(ferramentas.get(i));
            }
        }
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

    public int getPosPlayerReset(int posicao){
        return this.posPlayer=posicao;
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

    public void alteraEstado() {
        this.estado="Derrotado";
        /*
        if (this.estado.equals("Em Jogo")){
            this.estado = "Derrotado";
        }else {
            this.estado = "Em Jogo";
        }
         */
    }



    @Override
    public String toString() {

        StringBuilder outputLinguagensFavoritas = new StringBuilder();

        if(linguagensFavoritas==null || linguagensFavoritas.size()==0){
            //OU CRIAR -> Programmer a = new Programmer();
            outputLinguagensFavoritas.append("Não tem");
        }else{

            for (int i = 0; i < linguagensFavoritas.size() ; i++) {
                if (i==0){
                    outputLinguagensFavoritas.append(linguagensFavoritas.get(i));
                }else{
                    outputLinguagensFavoritas.append(";").append(linguagensFavoritas.get(i));
                }
            }
        }

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


        //Stringbuilder das ferramentas
        StringBuilder outputferramentas = new StringBuilder();

        if (ferramentas.size()==0){
            outputferramentas.append("No tools");
        }else{
            for (int i = 0; i < ferramentas.size() ; i++) {
                if (i == 0){
                    outputferramentas.append(ferramentas.get(i));
                }else {
                    outputferramentas.append("; ").append(ferramentas.get(i));
                }
            }
        }

        return id + " | " + nome + " | " + posPlayer + " | " + outputferramentas + " | " + resultado + " | " + estado;
    }

}
