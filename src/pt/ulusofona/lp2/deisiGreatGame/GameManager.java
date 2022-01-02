package pt.ulusofona.lp2.deisiGreatGame;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class GameManager {
    String winner;
    ArrayList<Programmer> players = new ArrayList<>();
    HashMap<Integer, AbismoEFerramenta> playersAbyssesAndTools = new HashMap<>();
    int tamanhoDoTabuleiro;
    int playerAJogar=0;
    int nrDeTurnos=0;
    int nrPosicoesMovida=0;
    int posicaoAnterior=1;
    int posicaoAntesDaAnterior=1;

    ArrayList<Programmer> jogadoresNestaCasa = new ArrayList<>();
    ArrayList<Programmer> jogadoresNoCoreDumped = new ArrayList<>();
    ArrayList<Programmer> playersEmJogo = new ArrayList<>();

    HashMap<Integer,Integer> casasMaisPisadasNoJogo = new HashMap<>();
    HashMap<String,Integer> abyssesMaisPisadasNoJogo = new HashMap<>();

    public GameManager() {
    }

    public void createInitialBoard(String[][] playerInfo, int worldSize) throws InvalidInitialBoardException{
        createInitialBoard(playerInfo,worldSize,null);
    }

    public void createInitialBoard(String[][]playerInfo, int worldSize,String[][] abyssesAndTools) throws InvalidInitialBoardException{

        playersAbyssesAndTools.clear(); // Serve para dar Reset ao hashmap de ferramentas de cada player
        posicaoAnterior=1; // Serve para dar Reset da variavel que guarda a posicao anterior
        posicaoAntesDaAnterior=1; // Serve para dar Reset da variavel que guarda a posicao antes da anterior
        jogadoresNestaCasa.clear(); // Serve para dar Reset ao arraylist de players presos nesta casa
        jogadoresNoCoreDumped.clear(); // Serve para dar Reset ao arraylist de players presos nesta casa
        playersEmJogo.clear(); // Serve para dar Reset ao arraylist de players em jogo

        if (playerInfo == null) {
            //return false;
            throw new InvalidInitialBoardException("Informacao dos jogadores incorreta",0,-1);
        }

        if (abyssesAndTools!=null){
            try{

                for (int i = 0; i < abyssesAndTools.length ; i++) {


                    if ( !abyssesAndTools[i][0].equals("0") && !(abyssesAndTools[i][0].equals("1")) ){
                        //return false;
                        throw new InvalidInitialBoardException("Tipo de Abysses ou Tool invalido",0,-1);

                    }


                    if ( abyssesAndTools[i][0].equals("0") ){
                        //Valida os ids dos abismos
                        if ( !( Integer.parseInt(abyssesAndTools[i][1]) >= 0 && Integer.parseInt(abyssesAndTools[i][1]) <= 9 ) ){
                            //return false;
                            throw new InvalidInitialBoardException("ID do abismo nao esta no range correto (0 - 9)",1, Integer.parseInt(abyssesAndTools[i][1]) );
                        }

                    }else{

                        //Valida os ids das ferramentas
                        if (  !( Integer.parseInt(abyssesAndTools[i][1]) >= 0 && Integer.parseInt(abyssesAndTools[i][1]) <= 5)  ){
                            //return false;
                            throw new InvalidInitialBoardException("ID da ferramenta nao esta no range correto (0 - 5)",2, Integer.parseInt(abyssesAndTools[i][1]) );
                        }

                    }

                    if ( ( Integer.parseInt(abyssesAndTools[i][2]) < 1 || Integer.parseInt(abyssesAndTools[i][2]) > worldSize ) ){
                        //return false;
                        throw new InvalidInitialBoardException("Nao e possivel colocar ferramentas ou abismos fora do tabuleiro",0,-1);
                    }

                    if ( abyssesAndTools[i][0].equals("0") ){
                        playersAbyssesAndTools.put( Integer.parseInt(abyssesAndTools[i][2]), new Abismo( Integer.parseInt(abyssesAndTools[i][1]) ) );
                        abyssesMaisPisadasNoJogo.put(abismoNome(Integer.parseInt(abyssesAndTools[i][1])), 0);
                    }else {
                        playersAbyssesAndTools.put( Integer.parseInt(abyssesAndTools[i][2]), new Ferramenta( Integer.parseInt(abyssesAndTools[i][1]) ) );
                    }


                }

            }catch (InvalidInitialBoardException exceptionMemory) {
                // return false;
                if (exceptionMemory.getMessage() == null){
                    throw new InvalidInitialBoardException("Ocorreu um erro ao correr a createInitialBoard (com AbyssesOuTools)",0,-1);
                }else{
                    throw exceptionMemory;
                }

            }
        }

        players.clear(); // Serve para Limpar os players
        nrDeTurnos=0; // Serve para Limpar os turnos
        playerAJogar=0; // Serve para dar Reset do player a jogar
        winner=""; // Serve para Limpar o nome do vencedor
        ArrayList<Integer> idsRepetidos = new ArrayList<>();
        ArrayList<String> coresRepetidas = new ArrayList<>();

        if ( playerInfo.length < 2 || playerInfo.length > 4  || worldSize < playerInfo.length*2 ){
            //return false;
            throw new InvalidInitialBoardException("Numero de jogadores ou tamanho do tabuleiro invalido",0,-1);
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
                        //return false;
                        throw new InvalidInitialBoardException("Nome do jogador invalido",0,-1);
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
                        //return false;
                        throw new InvalidInitialBoardException("Existem jogadores com cores repetidas",0,-1);
                    }
                }
            }

            for (int i = 0; i < players.size() ; i++) {
                for (int j = i+1; j < idsRepetidos.size() ; j++) {
                    if (players.get(i).id == idsRepetidos.get(j)){
                        //return false;
                        throw new InvalidInitialBoardException("Existem jogadores com IDs repetidos",0,-1);
                    }
                }
            }

        }catch (Exception exceptionMemory){
            //return false;
            if (exceptionMemory.getMessage() == null){
                throw new InvalidInitialBoardException("Ocorreu um erro ao correr a createInitialBoard",0,-1);
            }else{
                throw exceptionMemory;
            }

        }
        // Organiza os players por ID
        players.sort(Comparator.comparingInt((Programmer)-> Programmer.id));

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

            if (players.get(i).getEstado().equals("Em Jogo")){

                output.append(players.get(i).getName()).append(" : ");

                if(players.get(i).getFerramentas()==null || players.get(i).getFerramentas().size()==0){
                    output.append("No tools");
                }

                for (int j = 0; j < players.get(i).getFerramentas().size() ; j++) {
                    if (j==0){
                        output.append(players.get(i).ferramentas.get(j).getTitulo());
                    }else{
                        output.append(";").append(players.get(i).ferramentas.get(j).titulo);
                    }
                }

                if ( i != players.size()-1 ){ // ! (i == players.size()-1)
                    output.append(" | ");
                }
            }
        }

        return output.toString();
    }

    public int getCurrentPlayerID() {

        if(players.get(playerAJogar).getEstado().equals("Derrotado")){

            for (int i = playerAJogar; i < players.size();) {
                if (players.get(i).getEstado().equals("Em Jogo")) {
                    playerAJogar = i;
                    return players.get(i).getId();
                }

                if (i == players.size() - 1) {
                    i = 0;
                } else {
                    i++;
                }

            }
        }

        return players.get(playerAJogar).getId();
    }

    public boolean moveCurrentPlayer(int nrPositions){

        if (nrPositions < 1 || nrPositions > 6 || players.get(playerAJogar).presoNoCicloInfinito){
            return false;
        }

        players.get(playerAJogar).adicionaGuardaPosicao(players.get(playerAJogar).getPosPlayer());

        if (players.get(playerAJogar).getPosPlayer() + nrPositions <= tamanhoDoTabuleiro){

            nrPosicoesMovida = nrPositions;

            posicaoAnterior = players.get(playerAJogar).getArrayListGuardaPosicao().get(players.get(playerAJogar).getArrayListGuardaPosicao().size()-1);

            if (players.get(playerAJogar).getArrayListGuardaPosicao().size()>1){
                posicaoAntesDaAnterior = players.get(playerAJogar).getArrayListGuardaPosicao().get(players.get(playerAJogar).getArrayListGuardaPosicao().size()-2);
            }

            players.get(playerAJogar).andaParaAFrente(nrPositions);
        }else{

            posicaoAnterior = players.get(playerAJogar).getArrayListGuardaPosicao().get(players.get(playerAJogar).getArrayListGuardaPosicao().size()-1);

            if (players.get(playerAJogar).getArrayListGuardaPosicao().size()>1){
                posicaoAntesDaAnterior = players.get(playerAJogar).getArrayListGuardaPosicao().get(players.get(playerAJogar).getArrayListGuardaPosicao().size()-2);
            }

            players.get(playerAJogar).andaParaTras(tamanhoDoTabuleiro,nrPositions);
        }

        if( casasMaisPisadasNoJogo.containsKey(players.get(playerAJogar).getPosPlayer()) ){
            casasMaisPisadasNoJogo.put( players.get(playerAJogar).getPosPlayer(), casasMaisPisadasNoJogo.get(players.get(playerAJogar).getPosPlayer()+1) );
        }else{
            casasMaisPisadasNoJogo.put(players.get(playerAJogar).getPosPlayer(),1);
        }


        if ( playersAbyssesAndTools.containsKey(players.get(playerAJogar).getPosPlayer())
                &&  abyssesMaisPisadasNoJogo.containsKey( playersAbyssesAndTools.get(players.get(playerAJogar).getPosPlayer()).getTitulo()) ){
            abyssesMaisPisadasNoJogo.put( playersAbyssesAndTools.get(players.get(playerAJogar).getPosPlayer()).getTitulo() , abyssesMaisPisadasNoJogo.get(players.get(playerAJogar).getPosPlayer()+1) );
        }


        return true;
    }

    public String reactToAbyssOrTool(){

        int posPlayer=players.get(playerAJogar).getPosPlayer();
        String textOutput="";

        if (playersAbyssesAndTools.containsKey(posPlayer)) {

            //MOVIMENTOS QUANDO O PLAYER CAI EM ABISMOS

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
                if(players.get(playerAJogar).getFerramentas(0)){
                    players.get(playerAJogar).removeFerramenta(0);
                }else{
                    players.get(playerAJogar).getPosPlayerReset(posicaoAnterior);
                }

            }

            if (getImagePng(posPlayer).equals("secondary-effects.png")){
                if(players.get(playerAJogar).getFerramentas(1)){
                    players.get(playerAJogar).removeFerramenta(1);
                }else{
                    players.get(playerAJogar).getPosPlayerReset(posicaoAntesDaAnterior);
                }

            }

            if (getImagePng(posPlayer).equals("bsod.png")){
                if (players.get(playerAJogar).getEstado().equals("Em Jogo")) {
                    players.get(playerAJogar).alteraEstado();
                }
            }

            if (getImagePng(posPlayer).equals("infinite-loop.png")){

                if(players.get(playerAJogar).getFerramentas(1)){
                    players.get(playerAJogar).removeFerramenta(1);
                }else{

                    if (!players.get(playerAJogar).estaPresoNoCicloInfinito()){
                        players.get(playerAJogar).alteraPresoNoCicloInfinito();
                        jogadoresNestaCasa.add(players.get(playerAJogar));
                    }

                    if (jogadoresNestaCasa.size()>1){
                        playerAJogar--;
                        for (int i = 0; i < jogadoresNestaCasa.size() ; i++) {

                            if (players.get(playerAJogar).getId() == jogadoresNestaCasa.get(i).getId()){
                                continue;
                            }

                            players.get(playerAJogar).alteraPresoNoCicloInfinito();
                            jogadoresNestaCasa.remove(0);

                            playerAJogar++;
                            break;
                        }
                    }
                }
            }

            if (getImagePng(posPlayer).equals("core-dumped.png")){

                jogadoresNoCoreDumped.add(players.get(playerAJogar));

                if (jogadoresNoCoreDumped.size() >= 2){
                    for (int i = 0; i < players.size() ; i++) {
                        for (int j = 0; j < jogadoresNoCoreDumped.size() ; j++) {
                            if ( players.get(i).getName().equals(jogadoresNoCoreDumped.get(j).getName()) ){
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
        //System.out.println("TURNO: "+getCurrentPlayerID());
        if (playerAJogar == players.size()-1){
            playerAJogar=0;
        }else{
            playerAJogar++;
        }
        //System.out.println("Turno:" + nrDeTurnos  + " -> " + getProgrammersInfo());
        //System.out.println("Anterior: "+posicaoAnterior);
        //System.out.println("AntesDaAnterior: "+posicaoAntesDaAnterior);
    }

    public boolean gameIsOver(){

        if (players.size()-playersEmJogo.size() == 1){
            winner = players.get(playerAJogar).getName();
            nrDeTurnos++;
            return true;
        }

        if (players.get(playerAJogar).getPosPlayer() == tamanhoDoTabuleiro){
            winner = players.get(playerAJogar).getName();
            nrDeTurnos++;
            return true;
        }

        for (int i = 0; i < players.size() ; i++) {
            if (players.get(i).getEstado().equals("Derrotado") && !playersEmJogo.contains(players.get(i))){
                playersEmJogo.add(players.get(i));
            }
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

    public boolean saveGame(File file){

        try{
            FileWriter writer = new FileWriter(file);
            PrintWriter write = new PrintWriter(writer);

            write.println(tamanhoDoTabuleiro);
            write.println(playerAJogar);
            write.println(nrDeTurnos);
            write.println(nrPosicoesMovida);
            write.println(posicaoAnterior);
            write.println(posicaoAntesDaAnterior);

            for (int i = 0; i < players.size() ; i++) {
                if (players.get(i).getArrayListGuardaPosicao()==null || players.get(i).getArrayListGuardaPosicao().size()==0 ){
                    players.get(i).getArrayListGuardaPosicao().add(1);
                }
                write.println(players.get(i).getId() + "@" + players.get(i).getName() + "@" + players.get(i).getLinguagensFavoritas() + "@" + players.get(i).getColor()
                + "@" + players.get(i).getEstado() + "@" + players.get(i).getPosPlayer() + "@" + players.get(i).getFerramentas() + "@"
                + players.get(i).estaPresoNoCicloInfinito() + "@" + players.get(i).getArrayListGuardaPosicao() );
            }

            for (int i = 0; i < tamanhoDoTabuleiro ; i++) {
                if (playersAbyssesAndTools.containsKey(i)){

                    if (playersAbyssesAndTools.get(i).titulo.equals("Herança") || playersAbyssesAndTools.get(i).titulo.equals("Programação Funcional")
                        || playersAbyssesAndTools.get(i).titulo.equals("Testes unitários") || playersAbyssesAndTools.get(i).titulo.equals("Tratamento de Excepções")
                        || playersAbyssesAndTools.get(i).titulo.equals("IDE")  || playersAbyssesAndTools.get(i).titulo.equals("Ajuda Do Professor")){
                        write.println(i + "@" + playersAbyssesAndTools.get(i));
                    }else{
                        write.println(i + "@" + playersAbyssesAndTools.get(i).titulo);
                    }

                }
            }

            writer.close();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean loadGame(File file){

        try {

            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);

            players.clear(); // Serve para Limpar os players
            nrDeTurnos=0; // Serve para Limpar os turnos
            playerAJogar=0; // Serve para dar Reset do player a jogar
            winner=""; // Serve para Limpar o nome do vencedor
            playersAbyssesAndTools.clear(); // Serve para dar Reset ao hashmap de ferramentas de cada player
            posicaoAnterior=1; // Serve para dar Reset da variavel que guarda a posicao anterior
            posicaoAntesDaAnterior=1; // Serve para dar Reset da variavel que guarda a posicao antes da anterior
            jogadoresNestaCasa.clear(); // Serve para dar Reset ao arraylist de players presos nesta casa
            jogadoresNoCoreDumped.clear(); // Serve para dar Reset ao arraylist de players presos nesta casa
            playersEmJogo.clear(); // Serve para dar Reset ao arraylist de players em jogo

            String linha = null;

            int linhaFile = 1;

            while((linha = reader.readLine()) != null) {

                String[] dados = linha.split("@");


                if (linhaFile <= 6){

                    switch (linhaFile) {
                        case 1 -> {
                            String leituraTamanhoTabuleiro = dados[0];
                            tamanhoDoTabuleiro = Integer.parseInt(leituraTamanhoTabuleiro);
                            linhaFile++;
                            continue;
                        }
                        case 2 -> {
                            String leituraPlayerAJogar = dados[0];
                            playerAJogar = Integer.parseInt(leituraPlayerAJogar);
                            linhaFile++;
                            continue;
                        }
                        case 3 -> {
                            String leituraNrTurnos = dados[0];
                            nrDeTurnos = Integer.parseInt(leituraNrTurnos);
                            linhaFile++;
                            continue;
                        }
                        case 4 -> {
                            String leituranrPosicoesMovida = dados[0];
                            nrPosicoesMovida = Integer.parseInt(leituranrPosicoesMovida);
                            linhaFile++;
                            continue;
                        }
                        case 5 -> {
                            String leituraposicaoAnterior = dados[0];
                            posicaoAnterior = Integer.parseInt(leituraposicaoAnterior);
                            linhaFile++;
                            continue;
                        }
                        case 6 -> {
                            String leituraposicaoAntesDaAnterior = dados[0];
                            posicaoAntesDaAnterior = Integer.parseInt(leituraposicaoAntesDaAnterior);
                            linhaFile++;
                            continue;
                        }
                    }
                }

                if (dados.length==9){
                    int id = Integer.parseInt(dados[0]);
                    String nome = dados[1];

                    ArrayList<String> linguagensFavoritas = new ArrayList<>();
                    String[] nomesLinguagens = dados[2].replace("[","").replace("]","").split(",");
                    for (int i=0 ; nomesLinguagens.length==0 || i < nomesLinguagens.length ; i++) {
                        linguagensFavoritas.add(nomesLinguagens[i]);
                    }

                    ProgrammerColor color = ProgrammerColor.valueOf(dados[3].trim());
                    String estado = dados[4].trim();
                    int posPlayer = Integer.parseInt(dados[5].trim());

                    ArrayList<Ferramenta> playerFerramentas = new ArrayList<>();
                    String[] nomesFerramentas = dados[6].replace("[","").replace("]","").replace(" ","").split(",");

                    if (!nomesFerramentas[0].equals("")){
                        for (int i=0 ; i < nomesFerramentas.length ; i++) {
                            playerFerramentas.add(new Ferramenta(nomeFerramentaFuncao(nomesFerramentas[i])));
                        }
                    }

                    boolean presoNoCiclo = Boolean.parseBoolean(dados[7].trim());

                    ArrayList<Integer> guardaPosicoes = new ArrayList<>();
                    String[] posicaoPlayers = dados[8].replace(" ","").replace("[","").replace("]","").replace("\"","").split(",");
                    for (int i=0 ;posicaoPlayers.length==0 ||  i < posicaoPlayers.length ; i++) {
                        guardaPosicoes.add(Integer.valueOf(posicaoPlayers[i]));
                    }

                    players.add( new Programmer(id,nome,linguagensFavoritas,color,estado,posPlayer,playerFerramentas,presoNoCiclo,guardaPosicoes) );
                }

                if (dados.length==2){
                    int posicaoTabuleiro = Integer.parseInt(dados[0]);
                    String nomesAbismosOUTools = dados[1];
                    if (nomesAbismosOUTools.equals("Herança") || nomesAbismosOUTools.equals("Programação Funcional") || nomesAbismosOUTools.equals("Testes unitários") ||
                            nomesAbismosOUTools.equals("Tratamento de Excepções")  || nomesAbismosOUTools.equals("IDE")  || nomesAbismosOUTools.equals("Ajuda Do Professor")){
                        playersAbyssesAndTools.put( posicaoTabuleiro , new Ferramenta( abismoOUFerramentaID(nomesAbismosOUTools,1) ) );
                    }else {
                        playersAbyssesAndTools.put( posicaoTabuleiro, new Abismo( abismoOUFerramentaID(nomesAbismosOUTools,0) ) );
                    }

                }

            }
            reader.close();
            return true;
        }catch (Exception e){
            return false;
        }
    }


    //REALIZADAS POR MIM PARA COMPLEMENTAR O CODIGO
    public int nomeFerramentaFuncao(String ferramenta){
        switch (ferramenta) {
            case "Herança"-> {
                return 0;
            }
            case "ProgramaçãoFuncional"-> {
                return 1;
            }
            case "Testesunitários"-> {
                return 2;
            }
            case "TratamentodeExcepções"-> {
                return 3;
            }
            case "IDE"-> {
                return 4;
            }
            case "AjudaDoProfessor"-> {
                return 5;
            }

            default -> {
                return -1;
            }
        }
    }

    public int abismoOUFerramentaID(String ferramenta, int abismoOUferramenta){
        //abismoOUferramenta==0 -> abismos / abismoOUferramenta==1 -> ferrramentas
        if (abismoOUferramenta==0){
            switch (ferramenta) {
                case "Erro de sintaxe"-> {
                    return 0;
                }
                case "Erro de lógica"-> {
                    return 1;
                }
                case "Exception"-> {
                    return 2;
                }
                case "File Not Found Exception"-> {
                    return 3;
                }
                case "Crash (aka Rebentaço)"-> {
                    return 4;
                }
                case "Duplicated Code"-> {
                    return 5;
                }
                case "Efeitos secundários"-> {
                    return 6;
                }
                case "Blue Screen of Death"-> {
                    return 7;
                }
                case "Ciclo infinito"-> {
                    return 8;
                }
                case "Segmentation Fault"-> {
                    return 9;
                }

                default -> {
                    return -1;
                }
            }
        }else{
            switch (ferramenta) {
                case "Herança"-> {
                    return 0;
                }
                case "Programação Funcional"-> {
                    return 1;
                }
                case "Testes unitários"-> {
                    return 2;
                }
                case "Tratamento de Excepções"-> {
                    return 3;
                }
                case "IDE"-> {
                    return 4;
                }
                case "Ajuda Do Professor"-> {
                    return 5;
                }

                default -> {
                    return -1;
                }
            }
        }
    }

    public boolean adicionarAbismoOuFerramentaNaCollection(int id, int position){
        if (playersAbyssesAndTools.containsKey(position)){
            return false;
        }
        playersAbyssesAndTools.put(position, new Abismo(id));
        return true;
    }

    public String abismoNome(int id){
        switch (id) {
            case 0 -> {
                return "Erro de sintaxe";
            }
            case 1 -> {
                return "Erro de lógica";
            }
            case 2 -> {
                return "Exception";
            }
            case 3 -> {
                return "File Not Found Exception";
            }
            case 4 -> {
                return "Crash (aka Rebentaço)";
            }
            case 5 -> {
                return "Duplicated Code";
            }
            case 6 -> {
                return "Efeitos secundários";
            }
            case 7 -> {
                return "Blue Screen of Death";
            }
            case 8 -> {
                return "Ciclo infinito";
            }
            case 9 -> {
                return "Segmentation Fault";
            }

            default -> {
                return "";
            }
        }
    }


}
