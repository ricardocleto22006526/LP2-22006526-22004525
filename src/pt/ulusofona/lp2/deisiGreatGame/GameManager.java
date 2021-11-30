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
    //ArrayList<Integer> guardaPosicoes = new ArrayList<>();
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
        //guardaPosicoes.clear();

        if (playerInfo == null) { return false; }

        try{

            for (int i = 0; i < abyssesAndTools.length ; i++) {


                if ( !abyssesAndTools[i][0].equals("0") && !(abyssesAndTools[i][0].equals("1")) ){
                    return false;
                }


                if ( abyssesAndTools[i][0].equals("0") ){
                    //Valida os ids dos abismos
                    if ( !( Integer.parseInt(abyssesAndTools[i][1]) >= 0 && Integer.parseInt(abyssesAndTools[i][1]) <= 9 ) ){
                        return false;
                    }

                }else{

                    //Valida os ids das ferramentas
                    if (  !( Integer.parseInt(abyssesAndTools[i][1]) >= 0 && Integer.parseInt(abyssesAndTools[i][1]) <= 5)  ){
                        return false;
                    }

                }

                if ( ( Integer.parseInt(abyssesAndTools[i][2]) < 1 && Integer.parseInt(abyssesAndTools[i][2]) > tamanhoDoTabuleiro ) ){
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

        for (int i = 0; i < players.size() ; i++) {

            if(players.get(i).getFerramentas()==null || players.get(i).getFerramentas().size()==0){
                if (i==0){
                    output.append(players.get(i).getName()).append(" : ").append("No tools");
                }else{
                    output.append(" | ").append(players.get(i).getName()).append(" : ").append("No tools");
                }

            }else{

                if (i>0){
                    output.append(" | ").append(players.get(i).getName());
                }else{
                    output.append(players.get(i).getName());
                }
                for (int j = 0; j < players.get(i).getFerramentas().size() ; j++) {
                    if (j==0){
                        output.append(" : ").append(players.get(i).ferramentas.get(j).titulo);
                    }else{
                        output.append(" ; ").append(players.get(i).ferramentas.get(j).titulo);
                    }
                }
            }
        }

        return output.toString();
    }

    public int getCurrentPlayerID(){
        return players.get(playerAJogar).getId();
    }

    public boolean moveCurrentPlayer(int nrPositions){

        //guardaPosicoes.add(players.get(playerAJogar).getPosPlayer());
        players.get(playerAJogar).adicionaGuardaPosicao(players.get(playerAJogar).getPosPlayer());

        if (nrPositions < 1 || nrPositions > 6 || players.get(playerAJogar).presoNoCicloInfinito){
            return false;
        }

        if (players.get(playerAJogar).getPosPlayer() + nrPositions <= tamanhoDoTabuleiro){

            nrPosicoesMovida = nrPositions;

            posicaoAnterior = players.get(playerAJogar).getArrayListGuardaPosicao().get(players.get(playerAJogar).getArrayListGuardaPosicao().size()-1);

            if (players.get(playerAJogar).getArrayListGuardaPosicao().size()>1){
                posicaoAntesDaAnterior = players.get(playerAJogar).getArrayListGuardaPosicao().get(players.get(playerAJogar).getArrayListGuardaPosicao().size()-2);
            }

            /*
            nrPosicoesMovida = nrPositions;

            posicaoAnterior = guardaPosicoes.get(guardaPosicoes.size()-1);

            if (guardaPosicoes.size()>2){
                posicaoAntesDaAnterior = guardaPosicoes.get(guardaPosicoes.size()-2);
            }

             */

            players.get(playerAJogar).andaParaAFrente(nrPositions);
        }else{

            posicaoAnterior = players.get(playerAJogar).getArrayListGuardaPosicao().get(players.get(playerAJogar).getArrayListGuardaPosicao().size()-1);

            if (players.get(playerAJogar).getArrayListGuardaPosicao().size()>1){
                posicaoAntesDaAnterior = players.get(playerAJogar).getArrayListGuardaPosicao().get(players.get(playerAJogar).getArrayListGuardaPosicao().size()-2);
            }

            players.get(playerAJogar).andaParaTras(tamanhoDoTabuleiro,nrPositions);

            /*
            posicaoAnterior = guardaPosicoes.get(guardaPosicoes.size()-1);

            if (guardaPosicoes.size()>=2){
                posicaoAntesDaAnterior = guardaPosicoes.get(guardaPosicoes.size()-2);
            }

            players.get(playerAJogar).andaParaTras(tamanhoDoTabuleiro,nrPositions);
             */

        }

        return true;
    }

    public String reactToAbyssOrTool(){
        int posPlayer=players.get(playerAJogar).getPosPlayer();
        String textOutput="";

        if (playersAbyssesAndTools.containsKey(posPlayer)) {

            if (getImagePng(posPlayer).equals("syntax.png")){ //FUNCIONA

                if(players.get(playerAJogar).getFerramentas(5)){
                    players.get(playerAJogar).removeFerramenta(5);
                }else if(players.get(playerAJogar).getFerramentas(4)){
                    players.get(playerAJogar).removeFerramenta(4);
                }else{
                    players.get(playerAJogar).andaParaAFrente(-1);
                }

            }

            if (getImagePng(posPlayer).equals("logic.png")){//FUNCIONA

                if(players.get(playerAJogar).getFerramentas(5)){
                    players.get(playerAJogar).removeFerramenta(5);
                }else if(players.get(playerAJogar).getFerramentas(2)){
                    players.get(playerAJogar).removeFerramenta(2);
                }else{
                    players.get(playerAJogar).andaParaAFrente(-(nrPosicoesMovida/2));
                }

            }

            if (getImagePng(posPlayer).equals("exception.png")) {//FUNCIONA

                if(players.get(playerAJogar).getFerramentas(5)){
                    players.get(playerAJogar).removeFerramenta(5);
                }else if(players.get(playerAJogar).getFerramentas(3)){
                    players.get(playerAJogar).removeFerramenta(3);
                }else{
                    players.get(playerAJogar).andaParaAFrente(-2);
                }

            }

            if (getImagePng(posPlayer).equals("file-not-found-exception.png")){//FUNCIONA

                if(players.get(playerAJogar).getFerramentas(5)){
                    players.get(playerAJogar).removeFerramenta(5);
                }else if(players.get(playerAJogar).getFerramentas(3)){
                    players.get(playerAJogar).removeFerramenta(3);
                }else{
                    players.get(playerAJogar).andaParaAFrente(-3);
                }
            }

            if (getImagePng(posPlayer).equals("crash.png")){//FUNCIONA
                players.get(playerAJogar).getPosPlayerReset(1);
            }

            if (getImagePng(posPlayer).equals("duplicated-code.png")){//FUNCIONA
                players.get(playerAJogar).getPosPlayerReset(posicaoAnterior);
            }

            //FALTA FAZER ESTA
            if (getImagePng(posPlayer).equals("secondary-effects.png")){
                if(players.get(playerAJogar).getFerramentas(1)){
                    players.get(playerAJogar).removeFerramenta(1);
                }else{
                    players.get(playerAJogar).getPosPlayerReset(posicaoAntesDaAnterior);
                }

            }

            //COMO TIRAR O PLAYER DO MAPA?
            //ERRO NO alteraEstado()
            //OBRIGATORIA

            if (getImagePng(posPlayer).equals("bsod.png")){
                //players.get(playerAJogar).getPosPlayerReset(1);
                if (players.get(playerAJogar).getEstado().equals("Em Jogo")) {
                    players.get(playerAJogar).alteraEstado();
                    //players.remove(players.get(playerAJogar));
                }

                //playersDerrotados.add(players.get(playerAJogar));
                //NAO POSSO REMOVER
                //players.remove(players.get(playerAJogar));
                //getProgrammers(false);
            }

            //FALTA FAZER
            //OBRIGATORIA
            if (getImagePng(posPlayer).equals("infinite-loop.png")){

                if(players.get(playerAJogar).getFerramentas(1)){
                    players.get(playerAJogar).removeFerramenta(1);
                }else{
                    //FALTA FAZER ESTA PARTE

                    int count=0;

                    if (!players.get(playerAJogar).estaPresoNoCicloInfinito()){
                        players.get(playerAJogar).alteraPresoNoCicloInfinito();
                    }

                    ArrayList<Programmer> jogadoresNestaCasa = new ArrayList<>();

                    jogadoresNestaCasa.add(players.get(playerAJogar));


                    if (jogadoresNestaCasa.size()>1){
                        for (int i = 0; i < jogadoresNestaCasa.size() ; i++) {
                            if (!players.get(playerAJogar).estaPresoNoCicloInfinito()){
                                jogadoresNestaCasa.remove(count);
                            }
                            count++;
                            if ( players.get(playerAJogar).getId() == jogadoresNestaCasa.get(i).getId()){
                                continue;
                            }
                            players.get(playerAJogar).alteraPresoNoCicloInfinito();
                        }
                    }

















                    /*
                    for (int i = 0; i < players.size(); i++) {
                        for (int j = 1; j < players.size() ; j++) {
                            if (players.get(i).getPosPlayer() == players.get(j).getPosPlayer() && !jogadoresNestaCasa.contains(players.get(i))){
                                jogadoresNestaCasa.add(players.get(i));
                            }
                        }
                    }

                    if ( jogadoresNestaCasa.size() >= 2){
                        for (int i = 0; i < players.size() ; i++) {
                            for (int j = 0; j < jogadoresNestaCasa.size() ; j++) {
                                if (players.get(i).getName().equals(jogadoresNestaCasa.get(j).getName())) {
                                    players.get(i).alteraPresoNoCicloInfinito();
                                }
                            }
                        }
                    }



                    ArrayList<Programmer> jogadoresstuck = new ArrayList<>();
                    for (int i = 0; i < players.size(); i++) {
                        for (int j = 1; j < players.size() ; j++) {
                            if (players.get(i).getPosPlayer() == players.get(j).getPosPlayer()){
                                if (!jogadoresstuck.contains(players.get(i))){
                                    jogadoresstuck.add(players.get(i));
                                }

                            }
                        }
                    }

                    if (jogadoresstuck.size()>1){
                        players.get(playerAJogar).alteraPresoNoCicloInfinito();
                    }




                    /*
                    if (jogadoresstuck.size()>1){
                        for (int i = 0; i < players.size() ; i++) {
                            for (int j = 0; j < jogadoresstuck.size() ; j++) {
                                if (players.get(playerAJogar).estaPresoNoCicloInfinito()){
                                    jogadoresstuck.remove(count);
                                }
                                count++;
                                if (players.get(playerAJogar).getId() == jogadoresstuck.get(j).getId()){
                                    continue;
                                }
                                players.get(playerAJogar).alteraPresoNoCicloInfinito();
                            }
                        }
                    }

                    /*
                    ArrayList<Programmer> stuck = new ArrayList<>();
                    stuck.add(players.get(playerAJogar));

                    while (players.get(playerAJogar).estaPresoNoCicloInfinito()){

                        for (int i = 0; i < players.size() ; i++) {
                            for (int j = 0; j < stuck.size() ; j++) {
                                if (players.get(i).getId()==stuck.get(j).getId()){

                                }
                            }
                        }
                    }
                      */

                }
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

            if (getImagePng(posPlayer).equals("inheritance.png")){

                if(!players.get(playerAJogar).getFerramentas(0)){
                    players.get(playerAJogar).adicionaFerramenta(new Ferramenta(0));
                }

            }

            if (getImagePng(posPlayer).equals("functional.png")){
                if(!players.get(playerAJogar).getFerramentas(1)){
                    players.get(playerAJogar).adicionaFerramenta(new Ferramenta(1));
                }
            }

            if (getImagePng(posPlayer).equals("unit-tests.png")){
                if(!players.get(playerAJogar).getFerramentas(2)){
                    players.get(playerAJogar).adicionaFerramenta(new Ferramenta(2));
                }
            }

            if (getImagePng(posPlayer).equals("catch.png")){
                if(!players.get(playerAJogar).getFerramentas(3)){
                    players.get(playerAJogar).adicionaFerramenta(new Ferramenta(3));
                }
            }

            if (getImagePng(posPlayer).equals("IDE.png")){
                if(!players.get(playerAJogar).getFerramentas(4)){
                    players.get(playerAJogar).adicionaFerramenta(new Ferramenta(4));
                }
            }

            if (getImagePng(posPlayer).equals("ajuda-professor.png")){
                if(!players.get(playerAJogar).getFerramentas(5)){
                    players.get(playerAJogar).adicionaFerramenta(new Ferramenta(5));
                }
            }

            textOutput = playersAbyssesAndTools.get(posPlayer).tituloDoAbismoOUFerramenta();

            if (!gameIsOver()){
                mudancaDeTurno();
            }

            return textOutput;
        }
        if (!gameIsOver()){
            mudancaDeTurno();
        }

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
        System.out.println(getProgrammersInfo());
        //System.out.println("Anterior "+posicaoAnterior);
        //System.out.println("AntesDaAnterior "+posicaoAntesDaAnterior);
    }

    public boolean gameIsOver(){
        ArrayList<Programmer> playersEmJogo = new ArrayList<>();

        for (int i = 0; i < players.size() ; i++) {
            if (players.get(i).getEstado().equals("Em Jogo")){
                playersEmJogo.add(players.get(i));
            }
        }

        if (players.get(playerAJogar).getPosPlayer() == tamanhoDoTabuleiro || playersEmJogo.size()==1){
            winner = players.get(playerAJogar).getName();
            nrDeTurnos++;
            return true;
        }
        return false;

    }

    public List<String> getGameResults(){

        List<String> results = new ArrayList<>();

        Collections.sort(players, (p1, p2) -> {
                    if (p1.getPosPlayer() < p2.getPosPlayer()) {
                        return -1;
                    } else if (p1.getPosPlayer() > p2.getPosPlayer()) {
                        return 1;
                    } else {
                        return p1.getName().compareTo(p2.getName());
                    }
        });

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

        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getName().equals(winner)){
                continue;
            }
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
