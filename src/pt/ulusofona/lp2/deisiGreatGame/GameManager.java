package pt.ulusofona.lp2.deisiGreatGame;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Comparator;

public class GameManager {
    String winner;
    ArrayList<Programmer> players = new ArrayList<>();
    int tamanhoDoTabuleiro;
    int playerAJogar=0;
    int nrDeTurnos=0;

    public GameManager() {
    }

    public boolean createInitialBoard(String[][] playerInfo, int boardSize){
        players.clear(); // Serve para Limpar os players
        nrDeTurnos=0;
        playerAJogar=0;
        winner="";
        ArrayList<Integer> idsRepetidos = new ArrayList<>();
        ArrayList<String> coresRepetidas = new ArrayList<>();

        if ( playerInfo.length < 2 || playerInfo.length > 4  || boardSize < playerInfo.length*2 ){
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
                    if (players.get(j).getName() == null || players.get(j).getName().equals("")) {
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

            }

            for (int j = 0; j < players.size() ; j++) {
                for (int k = j+1; k < coresRepetidas.size() ; k++) {
                    if (players.get(j).cor.toString().equals(coresRepetidas.get(k))){
                        return false;
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

        if (position > tamanhoDoTabuleiro){
            return null;
        }

        if (position == tamanhoDoTabuleiro){
            return "glory.png";
        }
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

        if (programmersInThatPosition.size()==0 || position > tamanhoDoTabuleiro){
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

        if (players.get(playerAJogar).getPosPlayer() + nrPositions <= tamanhoDoTabuleiro){
            players.get(playerAJogar).andaParaAFrente(nrPositions);
        }else{
            players.get(playerAJogar).andaParaTras(tamanhoDoTabuleiro,nrPositions);
        }
        nrDeTurnos++;

        if ( gameIsOver() ){
            nrDeTurnos++;
            return true;
        }

        if (playerAJogar == players.size()-1){
            playerAJogar=0;
        }else{
            playerAJogar++;
        }

        return true;
    }

    public boolean gameIsOver(){

        if (players.get(playerAJogar).getPosPlayer() == tamanhoDoTabuleiro){
            winner=players.get(playerAJogar).getName();
            return true;
        }
        return false;
    }

    public ArrayList<String> getGameResults(){

        ArrayList<String> results = new ArrayList<>();

        //Vai ordenar as posicoes dos jogadores restantes do maior para o menor
        players.sort(Comparator.comparingInt((Programmer posicao)-> posicao.posPlayer).reversed());

        results.add("O GRANDE JOGO DO DEISI");
        results.add("");
        results.add("NR. DE TURNOS");
        results.add(String.valueOf(nrDeTurnos));
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
        JPanel creditos = new JPanel();

        JLabel label1 = new JLabel();
        label1.setText("THE DEISI GREATE GAME");

        JLabel label2 = new JLabel();
        label2.setText("Game Director: Pedro Alves");

        JLabel label3 = new JLabel();
        label3.setText(" Programmers: Ricardo Cleto");

        JLabel label4 = new JLabel();
        label4.setText("                                Rodrigo Amaro");

        JLabel label5 = new JLabel();
        label5.setText("TheDeisiGreatGame © 2021-2022 ULHT-LP2");

        creditos.setSize(300,300);

        creditos.add(label1);
        creditos.add(label2);
        creditos.add(label3);
        creditos.add(label4);
        creditos.add(label5);

        return creditos;
    }

}
