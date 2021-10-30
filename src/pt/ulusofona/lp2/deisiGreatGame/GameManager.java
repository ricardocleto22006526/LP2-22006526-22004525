package pt.ulusofona.lp2.deisiGreatGame;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Comparator;

public class GameManager {
    String winner;
    ArrayList<Programmer> players = new ArrayList<>();
    int tamanhoDoTabuleiro;
    int posPlayerInfoBefore;
    int playerAJogar=0;
    int nrDeTurnos=0;

    public GameManager() {
    }

    public boolean createInitialBoard(String[][] playerInfo, int boardSize){

        ArrayList<Integer> idsRepetidos = new ArrayList<>();
        ArrayList<String> coresRepetidas = new ArrayList<>();

        if ( playerInfo.length < 2 || playerInfo.length > 4  || tamanhoDoTabuleiro < players.size()*2 ){
            return false;
        }else{
            tamanhoDoTabuleiro=boardSize;
        }

        try{

            for (int i = 0; i < playerInfo.length ; i++) {

                //Caracteristicas de cada Player
                int id = Integer.parseInt(playerInfo[i][0]);
                //adiciona os IDs dos players no arraylist, para verificacao de repeticao
                idsRepetidos.add(id);


                for (int j = 0; j < players.size() ; j++) {
                    if (players.get(j).nome == null || players.get(j).nome.equals("")) {
                        return false;
                    }
                }
                String nome = playerInfo[i][1];


                ArrayList<String> linguagensFavoritas = new ArrayList<>();
                linguagensFavoritas.add(playerInfo[i][2]);


                ProgrammerColor corDoPlayer = ProgrammerColor.valueOf(playerInfo[i][3].toUpperCase());
                //Funcao que valida se e uma das 4 cores validas
                corValida(corDoPlayer.toString());

                //adiciona as cores dos players no arraylist, para verificacao de repeticao
                coresRepetidas.add(playerInfo[i][3].toUpperCase());

                //Adiciona o novo Player ao arraylist de Players
                players.add( new Programmer(id, nome, linguagensFavoritas, corDoPlayer) );


                for (int j = 0; j < players.size() ; j++) {
                    for (int k = j+1; k < coresRepetidas.size() ; k++) {
                        if (players.get(j).cor.toString().equals(coresRepetidas.get(k))){
                            return false;
                        }
                    }
                }
            }

            for (int i = 0; i < players.size() ; i++) {
                for (int j = i+1; j < idsRepetidos.size() ; j++) {
                    if (players.get(i).id == idsRepetidos.get(j)){
                        return false;
                    }
                }
            }

            /*
            for (int i = 0; i < players.size() ; i++) {
                if ( players.get(i).nome == null || players.get(i).nome.equals("") ){
                    return false;
                }
            }
             */


        }catch (Exception e){
            return false;
        }

        players.sort(Comparator.comparingInt((Programmer)-> Programmer.id));
        return true;

    }

    //FUNCAO CRIADA PARA VERIFICAR SE TEM COR VALIDA
    public boolean corValida(String corDoPlayer){
        switch (corDoPlayer) {
            case "PURPLE", "BLUE", "GREEN", "BROWN" -> {
                return true;
            }
            default -> {
                return false;
            }
        }
    }

    public String getImagePng(int position){
        return "";
    }

    public ArrayList<Programmer> getProgrammers(){
        return players;
    }

    public ArrayList<Programmer> getProgrammers(int position){
        ArrayList<Programmer> programmersInThatPosition = new ArrayList<>();

        for (int i = 0; i < players.size() ; i++) {
            if (players.get(i).getPosPlayer()==position){
                programmersInThatPosition.add(players.get(i));
            }
        }

        if (programmersInThatPosition.size()==0){
            return null;
        }else {
            return programmersInThatPosition;
        }
    }

    public int getCurrentPlayerID(){
        return players.get(playerAJogar).id;
    }

    public boolean moveCurrentPlayer(int nrPositions){
        if (nrPositions < 1 || nrPositions > 6){
            return false;
        }

        if (playerAJogar == 0){
            playerAJogar+= 1;
            posPlayerInfoBefore=players.size()-1;
        }else if (playerAJogar == 1){
            if(players.size() != 2){
                playerAJogar+= 1;
                posPlayerInfoBefore=players.size()-1;
            }else {
                playerAJogar=0;
                posPlayerInfoBefore=1;
            }
        }else if (playerAJogar == 3){
            if (players.size() != 3 ){
                posPlayerInfoBefore=players.size()-1;
                playerAJogar+= 1;
            }else {
                posPlayerInfoBefore=2;
                playerAJogar=0;
            }
        }else{
            posPlayerInfoBefore=players.size()-1;
            playerAJogar=0;
        }

        if (players.get(playerAJogar).getPosPlayer() + nrPositions <= tamanhoDoTabuleiro){
            players.get(playerAJogar).andaParaAFrente(nrPositions);
        }else{
            players.get(playerAJogar).andaParaTras(tamanhoDoTabuleiro,nrPositions);
        }

        nrDeTurnos++;
        gameIsOver();
        return true;
    }

    public boolean gameIsOver(){

        if (players.get(playerAJogar).posPlayer == tamanhoDoTabuleiro){
            winner=players.get(playerAJogar).nome;
            return true;
        }
        return false;
    }

    public ArrayList<String> getGameResults(){
        StringBuilder adicionaNoTexto = new StringBuilder();
        ArrayList<String> results = new ArrayList<>();


        adicionaNoTexto.append("O GRANDE JOGO DO DEISI\n"+"\n").append("NR. DE TURNOS\n"+ nrDeTurnos).append("VENCEDOR\n" + winner);
        players.sort(Comparator.comparingInt((Programmer posicao)-> posicao.posPlayer).reversed());
        results.add("O GRANDE JOGO DO DEISI");
        results.add("");
        results.add("VENCEDOR");
        results.add(winner);
        results.add("");
        results.add("RESTANTES");

        for (int i = 1; i < players.size(); i++) {
            results.add(players.get(i).getName() + " " + players.get(i).getPosPlayer());
        }

        return results;
    }

    public JPanel getAuthorsPanel(){
        return  new JPanel();
    }


}
