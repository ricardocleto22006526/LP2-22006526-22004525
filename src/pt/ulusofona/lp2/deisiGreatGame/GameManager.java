package pt.ulusofona.lp2.deisiGreatGame;

import javax.swing.*;
import java.util.*;

public class GameManager {
    String winner;
    ArrayList<Programmer> players = new ArrayList<>();
    HashMap<Integer, AbismoEFerramenta> playersAbyssesAndTools = new HashMap<>();
    int tamanhoDoTabuleiro;
    int playerAJogar=0;
    int nrDeTurnos=0;
    int nrPosicoesMovida=0;
    ArrayList<Integer> guardaPosicoes = new ArrayList<>();
    int posicaoAnterior=0;
    int posicaoAntesDaAnterior=0;

    public GameManager() {
    }

    public boolean createInitialBoard(String[][] playerInfo, int worldSize){
        players.clear(); // Serve para Limpar os players
        nrDeTurnos=0; // Serve para Limpar os turnos
        playerAJogar=0; // Serve para dar Reset do player a jogar
        winner=""; // Serve para Limpar o nome do vencedor
        ArrayList<Integer> idsRepetidos = new ArrayList<>();
        ArrayList<String> coresRepetidas = new ArrayList<>();

        if ( playerInfo.length < 2 || playerInfo.length > 4  || worldSize < playerInfo.length*2 ){
            return false;
        }else{
            tamanhoDoTabuleiro=worldSize;
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
        // Organiza os players por ID
        players.sort(Comparator.comparingInt((Programmer)-> Programmer.id));
        return true;
    }

    public boolean createInitialBoard(String[][]playerInfo, int worldSize,String[][] abyssesAndTools){
        playersAbyssesAndTools.clear();
        guardaPosicoes.clear();

        if (playerInfo == null) { return false; }

        boolean eAbismoOuFerramenta=false, tamanhoTabuleiroValido=false;

        try{

            for (int i = 0; i < abyssesAndTools.length ; i++) {


                if ( !(abyssesAndTools[i][0].equals("0") || (abyssesAndTools[i][0].equals("1"))) ){
                    return false;
                }

                if ( abyssesAndTools[i][0].equals("0") ){
                    eAbismoOuFerramenta = ( Integer.parseInt(abyssesAndTools[i][1]) >= 0 && Integer.parseInt(abyssesAndTools[i][1]) <= 9 );
                }else{
                    //Valida os ids das ferramentas
                    eAbismoOuFerramenta = ( Integer.parseInt(abyssesAndTools[i][1]) >= 0 && Integer.parseInt(abyssesAndTools[i][1]) <= 5 );
                }

                tamanhoTabuleiroValido = ( Integer.parseInt(abyssesAndTools[i][2]) > 0 && Integer.parseInt(abyssesAndTools[i][2]) < tamanhoDoTabuleiro );

                if ( !abyssesAndTools[i][0].equals("0") && !abyssesAndTools[i][0].equals("1") && !eAbismoOuFerramenta && !tamanhoTabuleiroValido  ){
                    return false;
                }

                if ( abyssesAndTools[i][0].equals("0") ){
                    playersAbyssesAndTools.put( Integer.parseInt(abyssesAndTools[i][2]), new Abismo( Integer.parseInt(abyssesAndTools[i][1]) ) );
                }else {
                    playersAbyssesAndTools.put( Integer.parseInt(abyssesAndTools[i][2]), new Ferramenta( Integer.parseInt(abyssesAndTools[i][1]) ) );
                }

            }

        }catch (Exception e){
            return false;
        }

        return createInitialBoard(playerInfo,worldSize);
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
            return "Winner.png";
        }

        if ( playersAbyssesAndTools.containsKey(position) ) {
            return playersAbyssesAndTools.get(position).imagemTabuleiro() + ".png";
        }

        return null;
    }

    public String getTitle(int position){

        if (position > tamanhoDoTabuleiro){
            return null;
        }

        if ( playersAbyssesAndTools.containsKey(position) ) {
            return playersAbyssesAndTools.get(position).getTitulo();
        }

        return null;
    }

    public List<Programmer> getProgrammers(boolean includeDefeated){


        ArrayList<Programmer> programmersVivosEmjogo = new ArrayList<>();
        List<Programmer> listaJogadores;

        if (!includeDefeated) {
            for (Programmer player : players) {
                if (player.getEstado().equals("Em Jogo")) {

                    programmersVivosEmjogo.add(player);
                }
            }
            listaJogadores=programmersVivosEmjogo;
        } else {
            listaJogadores = players;
        }

        return listaJogadores;
    }

    public List<Programmer> getProgrammers(int position){
        List<Programmer> programmersInThatPosition = new ArrayList<>();

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

    public String getProgrammersInfo(){

        StringBuilder output = new StringBuilder();
        StringBuilder infoPlayers = new StringBuilder();
        StringBuilder ferramentasdoPlayer = new StringBuilder();

        for (int i = 0; i < players.size() ; i++) {

            if(players.get(i).getFerramentas()==null || players.get(i).getFerramentas().size()==0){
                if (i==0){
                    output.append(players.get(i).getName()).append(" : ").append("No tools");
                }else{
                    output.append(" | ").append(players.get(i).getName()).append(" : ").append("No tools");
                }

            }else{

                for (int j = 0; j < players.get(i).getFerramentas().size(); j++) {
                    ferramentasdoPlayer.append(players.get(i).getFerramentas(j));
                }
                infoPlayers.append(players.get(i).getName()).append(ferramentasdoPlayer);

                for (int j = 0; j < players.size() ; j++) {
                    if (j==1){
                        output.append(players.get(i).getName()).append(" : ").append(infoPlayers);
                    }else{
                        output.append(" | ").append(players.get(i).getName()).append(" : ").append(infoPlayers);
                    }

                    /*
                    output.append(players.get(i).getName()).append(" : ").append(infoPlayers).append(" | ");
                    if (j == players.size()-1 ){
                        output.append(players.get(i).getName()).append(" : ").append(infoPlayers);
                    }
                     */
                }
            }
        }

        return output.toString();
    }

    public int getCurrentPlayerID(){
        return players.get(playerAJogar).getId();
    }

    public boolean moveCurrentPlayer(int nrPositions){

        guardaPosicoes.add(players.get(playerAJogar).getPosPlayer());

        if (nrPositions < 1 || nrPositions > 6){
            return false;
        }

        if (players.get(playerAJogar).getPosPlayer() + nrPositions <= tamanhoDoTabuleiro){

            nrPosicoesMovida = nrPositions;
            posicaoAnterior = guardaPosicoes.get(guardaPosicoes.size()-1);
            if (guardaPosicoes.size()>2){
                posicaoAntesDaAnterior = guardaPosicoes.get(guardaPosicoes.size()-2);
            }
            players.get(playerAJogar).andaParaAFrente(nrPositions);
        }else{

            /*
            //REFAZER PARTE DE GUARDAR a posicaoAntesDoAnterior
            guardaPosicoes.add(players.get(playerAJogar).getPosPlayer());

            for (int i = 0; i < guardaPosicoes.size() ; i++) {
                if (i>2){
                    posicaoAntesDaAnterior = guardaPosicoes.get(i);
                }
            }

            posicaoAnterior = players.get(playerAJogar).getPosPlayer();
            nrPosicoesMovida = nrPositions;
             */

            posicaoAnterior = guardaPosicoes.get(guardaPosicoes.size()-1);
            if (guardaPosicoes.size()>2){
                posicaoAntesDaAnterior = guardaPosicoes.get(guardaPosicoes.size()-2);
            }
            players.get(playerAJogar).andaParaTras(tamanhoDoTabuleiro,nrPositions);
        }

        /*
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
         */

        return true;
    }

    public String reactToAbyssOrTool(){
        int posPlayer=players.get(playerAJogar).getPosPlayer();
        String textOutput="";

        if (playersAbyssesAndTools.containsKey(posPlayer)) {

            if (getImagePng(posPlayer).equals("syntax.png")){ //FUNCIONA
                players.get(playerAJogar).andaParaAFrente(-1);
            }

            if (getImagePng(posPlayer).equals("logic.png")){//FUNCIONA
                players.get(playerAJogar).andaParaAFrente(-(nrPosicoesMovida/2));
            }

            if (getImagePng(posPlayer).equals("exception.png")) {//FUNCIONA
                players.get(playerAJogar).andaParaAFrente(-2);
            }

            if (getImagePng(posPlayer).equals("file-not-found-exception.png")){//FUNCIONA
                players.get(playerAJogar).andaParaAFrente(-3);
            }

            if (getImagePng(posPlayer).equals("crash.png")){//FUNCIONA
                players.get(playerAJogar).getPosPlayerReset(1);
            }

            if (getImagePng(posPlayer).equals("duplicated-code.png")){//FUNCIONA
                players.get(playerAJogar).getPosPlayerReset(posicaoAnterior);
            }

            //FALTA FAZER ESTA
            if (getImagePng(posPlayer).equals("secondary-effects.png")){
                players.get(playerAJogar).getPosPlayerReset(posicaoAntesDaAnterior);
            }

            //COMO TIRAR O PLAYER DO MAPA?
            //ERRO NO alteraEstado()
            //OBRIGATORIA

            if (getImagePng(posPlayer).equals("bsod.png")){
                //players.get(playerAJogar).getPosPlayerReset(1);
                players.get(playerAJogar).alteraEstado();


                //playersDerrotados.add(players.get(playerAJogar));
                //NAO POSSO REMOVER
                //players.remove(players.get(playerAJogar));
            }

            //FALTA FAZER
            //OBRIGATORIA
            if (getImagePng(posPlayer).equals("infinite-loop.png")){

            }

            if (getImagePng(posPlayer).equals("core-dumped.png")){ //NAO FUNCIONA CERTO

                ArrayList<Programmer> jogadoresNestaCasa = new ArrayList<>();

                for (int i = 0; i < players.size(); i++) {
                    for (int j = 1; j < players.size() ; j++) {
                        if (players.get(i).getPosPlayer() == players.get(j).getPosPlayer()){
                            jogadoresNestaCasa.add(players.get(i));
                        }
                    }
                }

                if ( jogadoresNestaCasa.size() >= 2){
                    for (int i = 0; i < players.size() ; i++) {
                        for (int j = 0; j < jogadoresNestaCasa.size() ; j++) {
                            if ( players.get(i).getName().equals(jogadoresNestaCasa.get(j).getName()) ){
                                players.get(i).andaParaAFrente(-3);
                            }
                        }
                    }
                }
            }

            //MOVIMENTOS QUANDO O PLAYER CAI EM FERRAMENTAS

            //O ADICIONAR ESTA A DAR EM MEMORIA, NAO EM STRING
            if (getImagePng(posPlayer).equals("inheritance.png")){
                players.get(playerAJogar).adicionaFerramenta(new Ferramenta(0));
            }

            if (getImagePng(posPlayer).equals("functional.png")){
                players.get(playerAJogar).adicionaFerramenta(new Ferramenta(1));
            }

            if (getImagePng(posPlayer).equals("unit-tests.png")){
                players.get(playerAJogar).adicionaFerramenta(new Ferramenta(2));
            }

            if (getImagePng(posPlayer).equals("catch.png")){
                players.get(playerAJogar).adicionaFerramenta(new Ferramenta(3));
            }

            if (getImagePng(posPlayer).equals("IDE.png")){
                players.get(playerAJogar).adicionaFerramenta(new Ferramenta(4));
            }

            if (getImagePng(posPlayer).equals("ajuda-professor.png")){
                players.get(playerAJogar).adicionaFerramenta(new Ferramenta(5));
            }


            textOutput = playersAbyssesAndTools.get(posPlayer).tituloDoAbismoOUFerramenta();
            mudancaDeTurno();
            return textOutput;
        }
        mudancaDeTurno();
        return null;
    }

    //FUNCAO QUE ALTERA O TURNO
    public void mudancaDeTurno(){
        nrDeTurnos++;

        if (playerAJogar == players.size()-1){
            playerAJogar=0;
        }else{
            playerAJogar++;
        }

        //System.out.println("Anterior "+posicaoAnterior);
        //System.out.println("AntesDaAnterior "+posicaoAntesDaAnterior);
    }

    //FUNCOES PARA DIMINUIR A FUNCAO reactToAbyssOrTool()
    public String ferramentaEmAcao(){
        return null;
    }
    public String abismosEmAcao(){
        return null;
    }

    public boolean gameIsOver(){

        if (players.get(playerAJogar).getPosPlayer() == tamanhoDoTabuleiro){
            winner=players.get(playerAJogar).getName();
            return true;
        }

        ArrayList<Programmer> playersEmJogo = new ArrayList<>();

        for (int i = 0; i < players.size() ; i++) {
            if (players.get(i).getEstado().equals("Em Jogo")){
                playersEmJogo.add(players.get(i));
            }
        }

        if (playersEmJogo.size()==1){
            winner=playersEmJogo.get(0).getName();
            return true;
        }

        return false;




        //Caso so haja 1 player em jogo (POSSIVELMENTE NECESSITA DE SER REFEITA)
        /*
        ArrayList<Programmer> ultimoJogadorEmCampo = new ArrayList<>();

        for (int i = 0; i < players.size() ; i++) {
            if (players.get(i).getEstado().equals("Em Jogo")){
                ultimoJogadorEmCampo.add(players.get(i));
            }
        }

        if (ultimoJogadorEmCampo.size()==1){
            return true;
        }
         */
    }

    public List<String> getGameResults(){

        List<String> results = new ArrayList<>();

        //Vai ordenar as posicoes dos jogadores restantes do maior para o menor (em termos de posicao)
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
        label3.setText("                                Lucio Studer");

        JLabel label4 = new JLabel();
        label4.setText("                                    Bruno Cipriano");

        JLabel label5 = new JLabel();
        label5.setText(" Programmers: Ricardo Cleto");

        JLabel label6 = new JLabel();
        label6.setText("                                Rodrigo Amaro");

        JLabel label7 = new JLabel();
        label7.setText("TheDeisiGreatGame © 2021-2022 ULHT-LP2");

        creditos.setSize(300,300);

        creditos.add(label1);
        creditos.add(label2);
        creditos.add(label3);
        creditos.add(label4);
        creditos.add(label5);
        creditos.add(label6);
        creditos.add(label7);

        return creditos;
    }

}
