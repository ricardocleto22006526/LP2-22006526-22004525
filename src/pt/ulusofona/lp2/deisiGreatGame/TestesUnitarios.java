package pt.ulusofona.lp2.deisiGreatGame;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class TestesUnitarios {

    private String[][] createPlayers(){

        String[][] players = new String[4][4];

        //Informacoes jogadores
        players[0][0] = "1"; //ID
        players[0][1] = "Jogador1"; //NOME
        players[0][2] = "Java"; //Linguagens Favoritas
        players[0][3] = "Purple"; //Cor do player

        players[1][0] = "2";
        players[1][1] = "Jogador2";
        players[1][2] = "C";
        players[1][3] = "Blue";

        players[2][0] = "3";
        players[2][1] = "Jogador3";
        players[2][2] = "C++";
        players[2][3] = "Green";

        players[3][0] = "4";
        players[3][1] = "Jogador4";
        players[3][2] = "Kotlin";
        players[3][3] = "Brown";

        return players;
    }

    private String[][] createAbismosOUTools(String umOuZeroDeterminaAbismo, String idDoAbismoOUferramenta, String posicaoNoTabuleiro){
        String[][] abismosOUferramentas = new String[1][3];

        //criacao dos abismos ou tools
        abismosOUferramentas[0][0] = umOuZeroDeterminaAbismo; // [0]abismo - [1]ferramenta
        abismosOUferramentas[0][1] = idDoAbismoOUferramenta; //ID do abismo/ferramenta
        abismosOUferramentas[0][2] = posicaoNoTabuleiro; //Posicao no tabuleiro

        return abismosOUferramentas;
    }

    @Test
    public void testMoveCurrentPlayerValorMaiorQueODado01() {
        GameManager manager = new GameManager();
        assertFalse("O dado dá valores entre 1-6, logo acima deste valor dá erro", manager.moveCurrentPlayer(7));
    }

    @Test
    public void testMoveCurrentPlayerValorMaiorQueODado02() {
        GameManager manager = new GameManager();
        assertFalse("Não pode andar mais que 6", manager.moveCurrentPlayer(150));
    }

    @Test
    public void testMoveCurrentPlayerValorMenorQueODado() {
        GameManager manager = new GameManager();
        assertFalse("O dado dá valores entre 1-6, logo a baixo deste valor dá erro", manager.moveCurrentPlayer(0));
    }

    @Test
    public void testMoveCurrentPlayerParaValoresNegativos() {
        GameManager manager = new GameManager();
        assertFalse("Não pode andar posições negativas", manager.moveCurrentPlayer(-1));
    }

    @Test
    public void testMoveCurrentPlayerParaUmaPosicaoMaiorQueOTamanhoDoTabuleiro() {
        GameManager manager = new GameManager();
        assertFalse("Não pode andar para posições exteriores ao tabuleiro", manager.moveCurrentPlayer(manager.tamanhoDoTabuleiro));
    }

    @Test
    public void testCreateInitialBoard01com2parametros()  {
        GameManager game = new GameManager();
        String[][] players = new String[4][4];

        players[0][0] = "1"; //ID
        players[0][1] = "Jogador1"; //NOME
        players[0][2] = "Java"; //Linguagens Favoritas
        players[0][3] = "Purple"; //Cor do player

        players[1][0] = "1";
        players[1][1] = "Jogador2";
        players[1][2] = "C";
        players[1][3] = "Blue";

        players[2][0] = "3";
        players[2][1] = "Jogador3";
        players[2][2] = "C++";
        players[2][3] = "Green";

        players[3][0] = "4";
        players[3][1] = "Jogador4";
        players[3][2] = "Kotlin";
        players[3][3] = "Brown";

        try{
            game.createInitialBoard(players,10);
            fail("Nao deveria ter lancado a exception");
        }catch (InvalidInitialBoardException e){
            assertEquals("Existem jogadores com IDs repetidos",e.getMessage());
        }

    }

    @Test
    public void testCreateInitialBoard02com2parametros()  {
        GameManager game = new GameManager();
        String[][] players = new String[4][4];

        players[0][0] = "1"; //ID
        players[0][1] = null; //NOME
        players[0][2] = "Java"; //Linguagens Favoritas
        players[0][3] = "Purple"; //Cor do player

        players[1][0] = "2";
        players[1][1] = "Jogador2";
        players[1][2] = "C";
        players[1][3] = "Blue";

        players[2][0] = "3";
        players[2][1] = "Jogador3";
        players[2][2] = "C++";
        players[2][3] = "Green";

        players[3][0] = "4";
        players[3][1] = "Jogador4";
        players[3][2] = "Kotlin";
        players[3][3] = "Brown";

        try{
            game.createInitialBoard(players,10);
            fail("Nao deveria ter lancado a exception");
        }catch (InvalidInitialBoardException e){
            assertEquals("Nome do jogador invalido",e.getMessage());
        }


    }

    @Test
    public void testCreateInitialBoard03com2parametros(){
        GameManager game = new GameManager();
        String[][] players = new String[4][4];

        players[0][0] = "1"; //ID
        players[0][1] = "Jogador1"; //NOME
        players[0][2] = "Java"; //Linguagens Favoritas
        players[0][3] = "Purple"; //Cor do player

        players[1][0] = "2";
        players[1][1] = "Jogador2";
        players[1][2] = "C";
        players[1][3] = "Blue";

        players[2][0] = "3";
        players[2][1] = "";
        players[2][2] = "C++";
        players[2][3] = "Green";

        players[3][0] = "4";
        players[3][1] = "Jogador4";
        players[3][2] = "Kotlin";
        players[3][3] = "Brown";

        try{
            game.createInitialBoard(players,10);
            fail("Nao deveria ter lancado a exception");
        }catch (InvalidInitialBoardException e){
            assertEquals("Nome do jogador invalido",e.getMessage());
        }

    }

    @Test
    public void testCreateInitialBoard04com2parametros(){
        GameManager game = new GameManager();
        String[][] players = new String[4][4];

        players[0][0] = "1"; //ID
        players[0][1] = "Jogador1"; //NOME
        players[0][2] = "Java"; //Linguagens Favoritas
        players[0][3] = "Purple"; //Cor do player

        players[1][0] = "2";
        players[1][1] = "Jogador2";
        players[1][2] = "C";
        players[1][3] = "Laranja";

        players[2][0] = "3";
        players[2][1] = "Jogador3";
        players[2][2] = "C++";
        players[2][3] = "Green";

        players[3][0] = "4";
        players[3][1] = "Jogador4";
        players[3][2] = "Kotlin";
        players[3][3] = "Brown";

        try{
            game.corValida(players[1][3]);
            game.createInitialBoard(players,10);
            fail("Deveria ter lancado a exception");
        }catch (Exception e){
            assertEquals("No enum constant pt.ulusofona.lp2.deisiGreatGame.ProgrammerColor.LARANJA",e.getMessage());
        }

    }


    @Test
    public void testCreateInitialBoard05com2parametros(){
        GameManager game = new GameManager();
        String[][] players = new String[4][4];

        players[0][0] = "1"; //ID
        players[0][1] = "Jogador1"; //NOME
        players[0][2] = "Java"; //Linguagens Favoritas
        players[0][3] = "Purple"; //Cor do player

        players[1][0] = "2";
        players[1][1] = "Jogador2";
        players[1][2] = "C";
        players[1][3] = "Green";

        players[2][0] = "3";
        players[2][1] = "Jogador3";
        players[2][2] = "C++";
        players[2][3] = "Green";

        players[3][0] = "4";
        players[3][1] = "Jogador4";
        players[3][2] = "Kotlin";
        players[3][3] = "Brown";

        try{
            game.corValida(players[1][3]);
            game.createInitialBoard(players,10);
            fail("Deveria ter lancado a exception");
        }catch (InvalidInitialBoardException e){

            assertEquals("Existem jogadores com cores repetidas",e.getMessage());
        }

    }

    @Test
    public void testCreateInitialBoard06com2parametros(){
        GameManager game = new GameManager();
        String[][] players = new String[1][4];

        players[0][0] = "1"; //ID
        players[0][1] = "Jogador1"; //NOME
        players[0][2] = "Java"; //Linguagens Favoritas
        players[0][3] = "Purple"; //Cor do player

        try{
            game.createInitialBoard(players,10);
            fail("Deveria ter lancado a exception");
        }catch (InvalidInitialBoardException e){

            assertEquals("Numero de jogadores ou tamanho do tabuleiro invalido",e.getMessage());
        }

    }

    @Test
    public void testCreateInitialBoard01com3parametros(){
        GameManager game = new GameManager();
        String[][] abismosOUferramentas = new String[1][3];

        //criacao dos abismos ou tools
        abismosOUferramentas[0][0] = "3"; // [0]abismo - [1]ferramenta
        abismosOUferramentas[0][1] = "5"; //ID do abismo/ferramenta
        abismosOUferramentas[0][2] = "30"; //Posicao no tabuleiro

        try{
            game.createInitialBoard(createPlayers(),50,abismosOUferramentas);
            fail("Deveria ter lancado a exception");
        }catch (InvalidInitialBoardException e){
            assertEquals("Tipo de Abysses ou Tool invalido",e.getMessage());
        }
    }

    @Test
    public void testCreateInitialBoard02com3parametros(){
        GameManager game = new GameManager();
        String[][] abismosOUferramentas = new String[1][3];

        //criacao dos abismos ou tools
        abismosOUferramentas[0][0] = "3"; // [0]abismo - [1]ferramenta
        abismosOUferramentas[0][1] = "5"; //ID do abismo/ferramenta
        abismosOUferramentas[0][2] = "30"; //Posicao no tabuleiro

        try{
            game.createInitialBoard(createPlayers(),50,abismosOUferramentas);
            fail("Deveria ter lancado a exception");
        }catch (InvalidInitialBoardException e){
            assertEquals("Tipo de Abysses ou Tool invalido",e.getMessage());
        }
    }

    @Test
    public void testCreateInitialBoard03com3parametros(){
        GameManager game = new GameManager();
        String[][] abismosOUferramentas = new String[1][3];

        //criacao dos abismos ou tools
        abismosOUferramentas[0][0] = "0"; // [0]abismo - [1]ferramenta
        abismosOUferramentas[0][1] = "10"; //ID do abismo/ferramenta
        abismosOUferramentas[0][2] = "30"; //Posicao no tabuleiro

        try{
            game.createInitialBoard(createPlayers(),50,abismosOUferramentas);
            fail("Deveria ter lancado a exception");
        }catch (InvalidInitialBoardException e){
            assertEquals("ID do abismo nao esta no range correto (0 - 9)",e.getMessage());
        }

    }

    @Test
    public void testCreateInitialBoard04com3parametros(){
        GameManager game = new GameManager();
        String[][] abismosOUferramentas = new String[1][3];

        //criacao dos abismos ou tools
        abismosOUferramentas[0][0] = "1"; // [0]abismo - [1]ferramenta
        abismosOUferramentas[0][1] = "6"; //ID do abismo/ferramenta
        abismosOUferramentas[0][2] = "30"; //Posicao no tabuleiro

        try{
            game.createInitialBoard(createPlayers(),50,abismosOUferramentas);
            fail("Deveria ter lancado a exception");
        }catch (InvalidInitialBoardException e){
            assertEquals("ID da ferramenta nao esta no range correto (0 - 5)",e.getMessage());
        }
    }

    @Test
    public void testCreateInitialBoard05com3parametros(){
        GameManager game = new GameManager();
        String[][] abismosOUferramentas = new String[1][3];

        //criacao dos abismos ou tools
        abismosOUferramentas[0][0] = "1"; // [0]abismo - [1]ferramenta
        abismosOUferramentas[0][1] = "2"; //ID do abismo/ferramenta
        abismosOUferramentas[0][2] = "72"; //Posicao no tabuleiro

        try{
            game.createInitialBoard(createPlayers(),50,abismosOUferramentas);
            fail("Deveria ter lancado a exception");
        }catch (InvalidInitialBoardException e){
            assertEquals("Nao e possivel colocar ferramentas ou abismos fora do tabuleiro",e.getMessage());
        }

    }

    @Test
    public void testCreateInitialBoard06com3parametros(){
        GameManager game = new GameManager();
        String[][] abismosOUferramentas = new String[1][3];

        //criacao dos abismos ou tools
        abismosOUferramentas[0][0] = "1"; // [0]abismo - [1]ferramenta
        abismosOUferramentas[0][1] = "2"; //ID do abismo/ferramenta
        abismosOUferramentas[0][2] = "32"; //Posicao no tabuleiro

         try{
            game.createInitialBoard(createPlayers(),30,abismosOUferramentas);
            fail("Deveria ter lancado a exception");
        }catch (InvalidInitialBoardException e){
            assertEquals("Nao e possivel colocar ferramentas ou abismos fora do tabuleiro",e.getMessage());
        }

    }

    @Test
    public void testCreateInitialBoard07com3parametros(){
        GameManager game = new GameManager();
        String[][] players = new String[2][4];

        players[0][0] = "1"; //ID
        players[0][1] = "Jogador 1"; //NOME
        players[0][2] = "Java"; //Linguagens Favoritas
        players[0][3] = "Purple"; //Cor do player

        players[1][0] = "2";
        players[1][1] = "Jogador2";
        players[1][2] = "C";
        players[1][3] = "Blue";

        try{
            game.createInitialBoard(players,6);
            game.getImagePng(6);
            game.getImagePng(7);
            game.getImagePng(2);
            assertTrue(game.moveCurrentPlayer(5));
            game.gameIsOver();
        }catch (InvalidInitialBoardException e){
            assertEquals("Nao era suposto dar exception",e.getMessage());
        }
    }

    @Test
    public void testCreateInitialBoard08com3parametros(){
        GameManager game = new GameManager();
        String[][] players = createPlayers();
        String[][] abismoOUferramenta = createAbismosOUTools("0","7","5");


        try{
            game.createInitialBoard(players,10,abismoOUferramenta);
            game.getTitle(11);
            game.getTitle(5);
            game.getTitle(3);
            assertTrue(game.moveCurrentPlayer(5));
            game.gameIsOver();
        }catch (InvalidInitialBoardException e){
            assertEquals("Nao era suposto dar exception",e.getMessage());
        }
    }

    @Test
    public void testCreateInitialBoard09com3parametros(){
        GameManager game = new GameManager();
        String[][] players = createPlayers();
        String[][] abismoOUferramenta = createAbismosOUTools("0","7","5");

        try{
            game.createInitialBoard(players,10,abismoOUferramenta);

            game.getProgrammersInfo();
            assertTrue(game.moveCurrentPlayer(5));
            game.gameIsOver();
        }catch (InvalidInitialBoardException e){
            assertEquals("Nao era suposto dar exception",e.getMessage());
        }
    }

    @Test
    public void testCreateInitialBoard10com3parametros(){
        GameManager game = new GameManager();
        String[][] players = createPlayers();
        String[][] abismos = new String[2][3];

        abismos[0][0] = "1";
        abismos[0][1] = "2";
        abismos[0][2] = "5";

        abismos[1][0] = "1";
        abismos[1][1] = "3";
        abismos[1][2] = "6";

        try{
            game.createInitialBoard(players,10,abismos);

            assertTrue(game.moveCurrentPlayer(4));
            String expected1 = "Ferramenta: unit-tests";
            assertEquals(expected1,game.reactToAbyssOrTool());

            assertTrue(game.moveCurrentPlayer(1));
            game.reactToAbyssOrTool();
            game.getCurrentPlayerID();
            assertTrue(game.moveCurrentPlayer(1));
            game.reactToAbyssOrTool();
            game.getCurrentPlayerID();
            assertTrue(game.moveCurrentPlayer(1));
            game.reactToAbyssOrTool();
            game.getCurrentPlayerID();

            assertTrue(game.moveCurrentPlayer(1));
            String expected2 = "Ferramenta: catch";
            assertEquals(expected2,game.reactToAbyssOrTool());

            assertTrue(game.moveCurrentPlayer(1));

            game.getProgrammersInfo();
            game.gameIsOver();
        }catch (InvalidInitialBoardException e){
            assertEquals("Nao era suposto dar exception",e.getMessage());
        }

    }

    @Test
    public void testCreateInitialBoard11com3parametros(){
        GameManager game = new GameManager();
        String[][] players = createPlayers();

        try{
            String[][] abismoOUferramenta = createAbismosOUTools(null,"7","5");
            game.createInitialBoard(players,10,abismoOUferramenta);
           fail("Deveria ter dado exception");
        }catch (Exception e){
            assertEquals("Cannot invoke \"String.equals(Object)\" because \"abyssesAndTools[i][0]\" is null",e.getMessage());
        }

    }

    @Test
    public void testBSOD01() {

        GameManager game = new GameManager();
        String[][] players = createPlayers();
        String[][] abismoOUferramenta = createAbismosOUTools("0","7","5");

        try{
            game.createInitialBoard(players,10,abismoOUferramenta);

            assertTrue(game.moveCurrentPlayer(4));
            game.getCurrentPlayerID();
            String expected1 = "Abismo: bsod";
            game.gameIsOver();
            assertEquals(expected1,game.reactToAbyssOrTool());

            assertTrue(game.moveCurrentPlayer(5));
            game.getCurrentPlayerID();
            String expected2 = null;
            game.gameIsOver();
            assertEquals(expected2,game.reactToAbyssOrTool());


            assertTrue(game.moveCurrentPlayer(4));
            game.getCurrentPlayerID();
            String expected3 = "Abismo: bsod";
            game.gameIsOver();
            assertEquals(expected3,game.reactToAbyssOrTool());


            assertTrue(game.moveCurrentPlayer(1));
            game.getCurrentPlayerID();
            String expected4 = null;
            game.gameIsOver();
            assertEquals(expected4,game.reactToAbyssOrTool());

            game.gameIsOver();
            game.getProgrammers(5);
            game.getProgrammers(3);
            game.getCurrentPlayerID();
            game.getProgrammers(false);
            game.getGameResults();
            game.getProgrammers(true);
            game.getGameResults();
            game.getAuthorsPanel();

        }catch (Exception e){
            assertEquals("Nao deveria dar exception",e.getMessage());
        }

    }

    @Test
    public void testreactToAbyssORToolFERRAMENTAS() {

        GameManager game = new GameManager();
        String[][] players = createPlayers();
        String[][] abismos = new String[6][3];

        abismos[0][0] = "1";
        abismos[0][1] = "0";
        abismos[0][2] = "2";

        abismos[1][0] = "1";
        abismos[1][1] = "1";
        abismos[1][2] = "3";

        abismos[2][0] = "1";
        abismos[2][1] = "2";
        abismos[2][2] = "4";

        abismos[3][0] = "1";
        abismos[3][1] = "3";
        abismos[3][2] = "5";

        abismos[4][0] = "1";
        abismos[4][1] = "4";
        abismos[4][2] = "6";

        abismos[5][0] = "1";
        abismos[5][1] = "5";
        abismos[5][2] = "7";

        try{
            game.createInitialBoard(players, 10, abismos);

            assertTrue(game.moveCurrentPlayer(4));
            String expected1 = "Ferramenta: catch";
            assertEquals(expected1, game.reactToAbyssOrTool());

            for (int i = 0; i <6 ; i++) {
                assertTrue(game.moveCurrentPlayer(1));
                game.reactToAbyssOrTool();
                game.getCurrentPlayerID();
                assertTrue(game.moveCurrentPlayer(1));
                game.reactToAbyssOrTool();
                game.getCurrentPlayerID();
                assertTrue(game.moveCurrentPlayer(1));
                game.reactToAbyssOrTool();
                game.getCurrentPlayerID();
            }

            assertTrue(game.moveCurrentPlayer(1));
            String expected2 = "Ferramenta: IDE";
            assertEquals(expected2, game.reactToAbyssOrTool());

            assertTrue(game.moveCurrentPlayer(1));

            game.getProgrammersInfo();
            game.gameIsOver();
        }catch (Exception e){
            assertEquals("Nao deveria lancar exception",e.getMessage());
        }
    }

    @Test
    public void testreactToAbyssORToolABISMOS() {

        GameManager game = new GameManager();
        String[][] players = createPlayers();
        String[][] abismos = new String[10][3];

        abismos[0][0] = "0";
        abismos[0][1] = "0";
        abismos[0][2] = "2";

        abismos[1][0] = "0";
        abismos[1][1] = "1";
        abismos[1][2] = "3";

        abismos[2][0] = "0";
        abismos[2][1] = "2";
        abismos[2][2] = "4";

        abismos[3][0] = "0";
        abismos[3][1] = "3";
        abismos[3][2] = "5";

        abismos[4][0] = "0";
        abismos[4][1] = "4";
        abismos[4][2] = "6";

        abismos[5][0] = "0";
        abismos[5][1] = "5";
        abismos[5][2] = "7";

        abismos[6][0] = "0";
        abismos[6][1] = "6";
        abismos[6][2] = "8";

        abismos[7][0] = "0";
        abismos[7][1] = "7";
        abismos[7][2] = "9";

        abismos[8][0] = "0";
        abismos[8][1] = "8";
        abismos[8][2] = "10";

        abismos[9][0] = "0";
        abismos[9][1] = "9";
        abismos[9][2] = "11";


        try{
            game.createInitialBoard(players, 25, abismos);

            assertTrue(game.moveCurrentPlayer(4));
            String expected1 = "Abismo: file-not-found-exception";
            assertEquals(expected1, game.reactToAbyssOrTool());

            for (int i = 0; i <15 ; i++) {
                assertTrue(game.moveCurrentPlayer(1));
                game.reactToAbyssOrTool();
                game.getCurrentPlayerID();
                assertTrue(game.moveCurrentPlayer(1));
                game.reactToAbyssOrTool();
                game.getCurrentPlayerID();
                assertTrue(game.moveCurrentPlayer(1));
                game.reactToAbyssOrTool();
                game.getCurrentPlayerID();
            }

            assertTrue(game.moveCurrentPlayer(1));
            String expected2 = "Abismo: syntax";
            assertEquals(expected2, game.reactToAbyssOrTool());

            assertTrue(game.moveCurrentPlayer(1));

            game.getProgrammersInfo();
            game.gameIsOver();
        }catch (Exception e){
            assertEquals("Nao deveria lancar exception",e.getMessage());
        }
    }

    @Test
    public void testreactToAbyssORToolABISMOSeFerramentas(){
        GameManager game = new GameManager();
        String[][] players = createPlayers();
        String[][] abismos = new String[16][3];

        abismos[0][0] = "0";
        abismos[0][1] = "0";
        abismos[0][2] = "2";

        abismos[1][0] = "0";
        abismos[1][1] = "1";
        abismos[1][2] = "3";

        abismos[2][0] = "0";
        abismos[2][1] = "2";
        abismos[2][2] = "4";

        abismos[3][0] = "0";
        abismos[3][1] = "3";
        abismos[3][2] = "5";

        abismos[4][0] = "0";
        abismos[4][1] = "4";
        abismos[4][2] = "6";

        abismos[5][0] = "0";
        abismos[5][1] = "5";
        abismos[5][2] = "7";

        abismos[6][0] = "0";
        abismos[6][1] = "6";
        abismos[6][2] = "8";

        abismos[7][0] = "0";
        abismos[7][1] = "7";
        abismos[7][2] = "9";

        abismos[8][0] = "0";
        abismos[8][1] = "8";
        abismos[8][2] = "10";

        abismos[9][0] = "0";
        abismos[9][1] = "9";
        abismos[9][2] = "11";


        abismos[10][0] = "1";
        abismos[10][1] = "0";
        abismos[10][2] = "2";

        abismos[11][0] = "1";
        abismos[11][1] = "1";
        abismos[11][2] = "3";

        abismos[12][0] = "1";
        abismos[12][1] = "2";
        abismos[12][2] = "4";

        abismos[13][0] = "1";
        abismos[13][1] = "3";
        abismos[13][2] = "5";

        abismos[14][0] = "1";
        abismos[14][1] = "4";
        abismos[14][2] = "6";

        abismos[15][0] = "1";
        abismos[15][1] = "5";
        abismos[15][2] = "7";

        try{
            game.createInitialBoard(players, 25, abismos);

            assertTrue(game.moveCurrentPlayer(4));
            String expected1 = "Ferramenta: catch";
            assertEquals(expected1, game.reactToAbyssOrTool());

            for (int i = 0; i <15 ; i++) {
                assertTrue(game.moveCurrentPlayer(1));
                game.reactToAbyssOrTool();
                game.getCurrentPlayerID();
                assertTrue(game.moveCurrentPlayer(1));
                game.reactToAbyssOrTool();
                game.getCurrentPlayerID();
                assertTrue(game.moveCurrentPlayer(1));
                game.reactToAbyssOrTool();
                game.getCurrentPlayerID();
            }

            assertTrue(game.moveCurrentPlayer(1));
            String expected2 = "Ferramenta: ajuda-professor";
            assertEquals(expected2, game.reactToAbyssOrTool());

            assertTrue(game.moveCurrentPlayer(1));

            game.getProgrammersInfo();
            game.gameIsOver();
        }catch (Exception e){
            assertEquals("Nao deveria lancar exception",e.getMessage());
        }
    }

    @Test
    public void testreactToAbyssORTools02(){
        GameManager game = new GameManager();
        String[][] players = createPlayers();
        String[][] abismos = new String[1][3];

        abismos[0][0] = "0";
        abismos[0][1] = "9";
        abismos[0][2] = "5";

        try{
            game.createInitialBoard(players, 25, abismos);

            assertTrue(game.moveCurrentPlayer(4));
            String expected1 = "Abismo: core-dumped";
            assertEquals(expected1, game.reactToAbyssOrTool());

            for (int i = 0; i <15 ; i++) {
                assertTrue(game.moveCurrentPlayer(1));
                game.reactToAbyssOrTool();
                game.getCurrentPlayerID();
                assertTrue(game.moveCurrentPlayer(1));
                game.reactToAbyssOrTool();
                game.getCurrentPlayerID();
                assertTrue(game.moveCurrentPlayer(1));
                game.reactToAbyssOrTool();
                game.getCurrentPlayerID();
            }

            game.getProgrammersInfo();
            game.gameIsOver();
        }catch (Exception e){
            assertEquals("Nao deveria lancar exception",e.getMessage());
        }
    }

    @Test
    public void testreactToAbyssORTools03(){
        GameManager game = new GameManager();
        String[][] players = createPlayers();
        String[][] abismos = new String[1][3];

        abismos[0][0] = "0";
        abismos[0][1] = "8";
        abismos[0][2] = "5";

        try{
            game.createInitialBoard(players, 25, abismos);

            assertTrue(game.moveCurrentPlayer(4));
            String expected1 = "Abismo: infinite-loop";
            assertEquals(expected1, game.reactToAbyssOrTool());

            assertTrue(game.moveCurrentPlayer(4));
            String expected2 = "Abismo: infinite-loop";
            assertEquals(expected2, game.reactToAbyssOrTool());

            game.getProgrammersInfo();
            game.gameIsOver();
        }catch (Exception e){
            assertEquals("Nao deveria lancar exception",e.getMessage());
        }
    }

    @Test
    public void testreactToAbyssORTools04(){
        GameManager game = new GameManager();
        String[][] players = createPlayers();
        String[][] abismos = new String[2][3];

        abismos[0][0] = "0";
        abismos[0][1] = "5";
        abismos[0][2] = "5";

        abismos[1][0] = "1";
        abismos[1][1] = "1";
        abismos[1][2] = "4";

        try{
            game.createInitialBoard(players, 25, abismos);

            assertTrue(game.moveCurrentPlayer(3));
            String expected1 = "Ferramenta: functional";
            assertEquals(expected1, game.reactToAbyssOrTool());

            assertTrue(game.moveCurrentPlayer(4));
            String expected2 = "Abismo: duplicated-code";
            assertEquals(expected2, game.reactToAbyssOrTool());

            assertTrue(game.moveCurrentPlayer(1));
            String expected3 = null;
            assertEquals(expected3, game.reactToAbyssOrTool());

            assertTrue(game.moveCurrentPlayer(3));
            String expected4 = "Ferramenta: functional";
            assertEquals(expected4, game.reactToAbyssOrTool());

            game.getProgrammersInfo();
            game.gameIsOver();
        }catch (Exception e){
            assertEquals("Nao deveria lancar exception",e.getMessage());
        }
    }

    @Test
    public void testreactToAbyssORTools05(){
        GameManager game = new GameManager();
        String[][] players = createPlayers();
        String[][] abismos = new String[1][3];

        abismos[0][0] = "0";
        abismos[0][1] = "4";
        abismos[0][2] = "2";

        try{
            game.createInitialBoard(players, 25, abismos);
            assertTrue(game.moveCurrentPlayer(1));
            String expected1 = "Abismo: crash";
            assertEquals(expected1, game.reactToAbyssOrTool());
        }catch (Exception e){
            assertEquals("Nao deveria lancar exception",e.getMessage());
        }

    }

    @Test
    public void testreactToAbyssORTools06(){
        GameManager game = new GameManager();
        String[][] players = createPlayers();
        String[][] abismos = new String[4][3];

        abismos[0][0] = "1";
        abismos[0][1] = "5";
        abismos[0][2] = "2";

        abismos[1][0] = "0";
        abismos[1][1] = "0";
        abismos[1][2] = "3";

        abismos[2][0] = "1";
        abismos[2][1] = "4";
        abismos[2][2] = "4";

        abismos[3][0] = "0";
        abismos[3][1] = "0";
        abismos[3][2] = "5";

       try{
           game.createInitialBoard(players, 25, abismos);

           assertTrue(game.moveCurrentPlayer(1));
           String expected1 = "Ferramenta: ajuda-professor";
           assertEquals(expected1, game.reactToAbyssOrTool());


           assertTrue(game.moveCurrentPlayer(4));
           game.reactToAbyssOrTool();
           game.getCurrentPlayerID();
           assertTrue(game.moveCurrentPlayer(4));
           game.reactToAbyssOrTool();
           game.getCurrentPlayerID();
           assertTrue(game.moveCurrentPlayer(4));
           game.reactToAbyssOrTool();
           game.getCurrentPlayerID();


           assertTrue(game.moveCurrentPlayer(1));
           String expected2 = "Abismo: syntax";
           assertEquals(expected2, game.reactToAbyssOrTool());

           assertTrue(game.moveCurrentPlayer(4));
           game.reactToAbyssOrTool();
           game.getCurrentPlayerID();
           assertTrue(game.moveCurrentPlayer(4));
           game.reactToAbyssOrTool();
           game.getCurrentPlayerID();
           assertTrue(game.moveCurrentPlayer(4));
           game.reactToAbyssOrTool();
           game.getCurrentPlayerID();

           assertTrue(game.moveCurrentPlayer(1));
           String expected3 = "Ferramenta: IDE";
           assertEquals(expected3, game.reactToAbyssOrTool());

           assertTrue(game.moveCurrentPlayer(4));
           game.reactToAbyssOrTool();
           game.getCurrentPlayerID();
           assertTrue(game.moveCurrentPlayer(4));
           game.reactToAbyssOrTool();
           game.getCurrentPlayerID();
           assertTrue(game.moveCurrentPlayer(4));
           game.reactToAbyssOrTool();
           game.getCurrentPlayerID();

           assertTrue(game.moveCurrentPlayer(1));
           String expected4 = "Abismo: syntax";
           assertEquals(expected4, game.reactToAbyssOrTool());
       }catch (Exception e){
            assertEquals("Nao deveria lancar exception",e.getMessage());
       }
    }

    @Test
    public void testreactToAbyssORTools07(){
        GameManager game = new GameManager();
        String[][] players = createPlayers();
        String[][] abismos = new String[4][3];

        abismos[0][0] = "1";
        abismos[0][1] = "5";
        abismos[0][2] = "2";

        abismos[1][0] = "0";
        abismos[1][1] = "1";
        abismos[1][2] = "3";

        abismos[2][0] = "1";
        abismos[2][1] = "2";
        abismos[2][2] = "4";

        abismos[3][0] = "0";
        abismos[3][1] = "1";
        abismos[3][2] = "5";

        try{
            game.createInitialBoard(players, 25, abismos);

            assertTrue(game.moveCurrentPlayer(1));
            String expected1 = "Ferramenta: ajuda-professor";
            assertEquals(expected1, game.reactToAbyssOrTool());


            assertTrue(game.moveCurrentPlayer(4));
            game.reactToAbyssOrTool();
            game.getCurrentPlayerID();
            assertTrue(game.moveCurrentPlayer(4));
            game.reactToAbyssOrTool();
            game.getCurrentPlayerID();
            assertTrue(game.moveCurrentPlayer(4));
            game.reactToAbyssOrTool();
            game.getCurrentPlayerID();


            assertTrue(game.moveCurrentPlayer(1));
            String expected2 = "Abismo: logic";
            assertEquals(expected2, game.reactToAbyssOrTool());

            assertTrue(game.moveCurrentPlayer(4));
            game.reactToAbyssOrTool();
            game.getCurrentPlayerID();
            assertTrue(game.moveCurrentPlayer(4));
            game.reactToAbyssOrTool();
            game.getCurrentPlayerID();
            assertTrue(game.moveCurrentPlayer(4));
            game.reactToAbyssOrTool();
            game.getCurrentPlayerID();

            assertTrue(game.moveCurrentPlayer(1));
            String expected3 = "Ferramenta: unit-tests";
            assertEquals(expected3, game.reactToAbyssOrTool());

            assertTrue(game.moveCurrentPlayer(4));
            game.reactToAbyssOrTool();
            game.getCurrentPlayerID();
            assertTrue(game.moveCurrentPlayer(4));
            game.reactToAbyssOrTool();
            game.getCurrentPlayerID();
            assertTrue(game.moveCurrentPlayer(4));
            game.reactToAbyssOrTool();
            game.getCurrentPlayerID();

            assertTrue(game.moveCurrentPlayer(1));
            String expected4 = "Abismo: logic";
            assertEquals(expected4, game.reactToAbyssOrTool());
        }catch (Exception e){
            assertEquals("Nao deveria lancar exception",e.getMessage());
        }
    }

    @Test
    public void testreactToAbyssORTools08(){
        GameManager game = new GameManager();
        String[][] players = createPlayers();
        String[][] abismos = new String[4][3];

        abismos[0][0] = "1";
        abismos[0][1] = "5";
        abismos[0][2] = "2";

        abismos[1][0] = "0";
        abismos[1][1] = "2";
        abismos[1][2] = "3";

        abismos[2][0] = "1";
        abismos[2][1] = "3";
        abismos[2][2] = "4";

        abismos[3][0] = "0";
        abismos[3][1] = "2";
        abismos[3][2] = "5";

        try{
            game.createInitialBoard(players, 25, abismos);

            assertTrue(game.moveCurrentPlayer(1));
            String expected1 = "Ferramenta: ajuda-professor";
            assertEquals(expected1, game.reactToAbyssOrTool());


            assertTrue(game.moveCurrentPlayer(4));
            game.reactToAbyssOrTool();
            game.getCurrentPlayerID();
            assertTrue(game.moveCurrentPlayer(4));
            game.reactToAbyssOrTool();
            game.getCurrentPlayerID();
            assertTrue(game.moveCurrentPlayer(4));
            game.reactToAbyssOrTool();
            game.getCurrentPlayerID();


            assertTrue(game.moveCurrentPlayer(1));
            String expected2 = "Abismo: exception";
            assertEquals(expected2, game.reactToAbyssOrTool());

            assertTrue(game.moveCurrentPlayer(4));
            game.reactToAbyssOrTool();
            game.getCurrentPlayerID();
            assertTrue(game.moveCurrentPlayer(4));
            game.reactToAbyssOrTool();
            game.getCurrentPlayerID();
            assertTrue(game.moveCurrentPlayer(4));
            game.reactToAbyssOrTool();
            game.getCurrentPlayerID();

            assertTrue(game.moveCurrentPlayer(1));
            String expected3 = "Ferramenta: catch";
            assertEquals(expected3, game.reactToAbyssOrTool());

            assertTrue(game.moveCurrentPlayer(4));
            game.reactToAbyssOrTool();
            game.getCurrentPlayerID();
            assertTrue(game.moveCurrentPlayer(4));
            game.reactToAbyssOrTool();
            game.getCurrentPlayerID();
            assertTrue(game.moveCurrentPlayer(4));
            game.reactToAbyssOrTool();
            game.getCurrentPlayerID();

            assertTrue(game.moveCurrentPlayer(1));
            String expected4 = "Abismo: exception";
            assertEquals(expected4, game.reactToAbyssOrTool());
        }catch (Exception e){
            assertEquals("Nao deveria lancar exception",e.getMessage());
        }
    }

    @Test
    public void testreactToAbyssORTools09(){
        GameManager game = new GameManager();
        String[][] players = createPlayers();
        String[][] abismos = new String[4][3];

        abismos[0][0] = "1";
        abismos[0][1] = "5";
        abismos[0][2] = "2";

        abismos[1][0] = "0";
        abismos[1][1] = "3";
        abismos[1][2] = "3";

        abismos[2][0] = "1";
        abismos[2][1] = "3";
        abismos[2][2] = "4";

        abismos[3][0] = "0";
        abismos[3][1] = "3";
        abismos[3][2] = "5";

        try{
            game.createInitialBoard(players, 25, abismos);

            assertTrue(game.moveCurrentPlayer(1));
            String expected1 = "Ferramenta: ajuda-professor";
            assertEquals(expected1, game.reactToAbyssOrTool());


            assertTrue(game.moveCurrentPlayer(4));
            game.reactToAbyssOrTool();
            game.getCurrentPlayerID();
            assertTrue(game.moveCurrentPlayer(4));
            game.reactToAbyssOrTool();
            game.getCurrentPlayerID();
            assertTrue(game.moveCurrentPlayer(4));
            game.reactToAbyssOrTool();
            game.getCurrentPlayerID();


            assertTrue(game.moveCurrentPlayer(1));
            String expected2 = "Abismo: file-not-found-exception";
            assertEquals(expected2, game.reactToAbyssOrTool());

            assertTrue(game.moveCurrentPlayer(4));
            game.reactToAbyssOrTool();
            game.getCurrentPlayerID();
            assertTrue(game.moveCurrentPlayer(4));
            game.reactToAbyssOrTool();
            game.getCurrentPlayerID();
            assertTrue(game.moveCurrentPlayer(4));
            game.reactToAbyssOrTool();
            game.getCurrentPlayerID();

            assertTrue(game.moveCurrentPlayer(1));
            String expected3 = "Ferramenta: catch";
            assertEquals(expected3, game.reactToAbyssOrTool());

            assertTrue(game.moveCurrentPlayer(4));
            game.reactToAbyssOrTool();
            game.getCurrentPlayerID();
            assertTrue(game.moveCurrentPlayer(4));
            game.reactToAbyssOrTool();
            game.getCurrentPlayerID();
            assertTrue(game.moveCurrentPlayer(4));
            game.reactToAbyssOrTool();
            game.getCurrentPlayerID();

            assertTrue(game.moveCurrentPlayer(1));
            String expected4 = "Abismo: file-not-found-exception";
            assertEquals(expected4, game.reactToAbyssOrTool());
        }catch (Exception e){
            assertEquals("Nao deveria lancar exception",e.getMessage());
        }
    }

    @Test
    public void testreactToAbyssORTools10(){
        GameManager game = new GameManager();
        String[][] players = createPlayers();
        String[][] abismos = new String[4][3];

        abismos[0][0] = "1";
        abismos[0][1] = "0";
        abismos[0][2] = "2";

        abismos[1][0] = "0";
        abismos[1][1] = "5";
        abismos[1][2] = "3";

        abismos[2][0] = "1";
        abismos[2][1] = "0";
        abismos[2][2] = "4";

        abismos[3][0] = "0";
        abismos[3][1] = "5";
        abismos[3][2] = "5";

        try{
            game.createInitialBoard(players, 25, abismos);

            assertTrue(game.moveCurrentPlayer(1));
            String expected1 = "Ferramenta: inheritance";
            assertEquals(expected1, game.reactToAbyssOrTool());


            assertTrue(game.moveCurrentPlayer(4));
            game.reactToAbyssOrTool();
            game.getCurrentPlayerID();
            assertTrue(game.moveCurrentPlayer(4));
            game.reactToAbyssOrTool();
            game.getCurrentPlayerID();
            assertTrue(game.moveCurrentPlayer(4));
            game.reactToAbyssOrTool();
            game.getCurrentPlayerID();


            assertTrue(game.moveCurrentPlayer(1));
            String expected2 = "Abismo: duplicated-code";
            assertEquals(expected2, game.reactToAbyssOrTool());

            assertTrue(game.moveCurrentPlayer(4));
            game.reactToAbyssOrTool();
            game.getCurrentPlayerID();
            assertTrue(game.moveCurrentPlayer(4));
            game.reactToAbyssOrTool();
            game.getCurrentPlayerID();
            assertTrue(game.moveCurrentPlayer(4));
            game.reactToAbyssOrTool();
            game.getCurrentPlayerID();

            assertTrue(game.moveCurrentPlayer(1));
            String expected3 = "Ferramenta: inheritance";
            assertEquals(expected3, game.reactToAbyssOrTool());

            assertTrue(game.moveCurrentPlayer(4));
            game.reactToAbyssOrTool();
            game.getCurrentPlayerID();
            assertTrue(game.moveCurrentPlayer(4));
            game.reactToAbyssOrTool();
            game.getCurrentPlayerID();
            assertTrue(game.moveCurrentPlayer(4));
            game.reactToAbyssOrTool();
            game.getCurrentPlayerID();

            assertTrue(game.moveCurrentPlayer(1));
            String expected4 = "Abismo: duplicated-code";
            assertEquals(expected4, game.reactToAbyssOrTool());
        }catch (Exception e){
            assertEquals("Nao deveria lancar exception",e.getMessage());
        }
    }

    @Test
    public void testreactToAbyssORTools11(){
        GameManager game = new GameManager();
        String[][] players = new String[2][4];
        String[][] abismos = new String[2][3];

        abismos[0][0] = "1";
        abismos[0][1] = "1";
        abismos[0][2] = "2";

        abismos[1][0] = "0";
        abismos[1][1] = "8";
        abismos[1][2] = "3";

        players[0][0] = "1"; //ID
        players[0][1] = "Jogador 1"; //NOME
        players[0][2] = "Java"; //Linguagens Favoritas
        players[0][3] = "Purple"; //Cor do player

        players[1][0] = "2";
        players[1][1] = "Jogador2";
        players[1][2] = "C";
        players[1][3] = "Blue";

        try{
            game.createInitialBoard(players, 25, abismos);

            assertTrue(game.moveCurrentPlayer(1));
            String expected1 = "Ferramenta: functional";
            assertEquals(expected1, game.reactToAbyssOrTool());

            assertTrue(game.moveCurrentPlayer(5));
            String expected2 = null;
            assertEquals(expected2, game.reactToAbyssOrTool());


            assertTrue(game.moveCurrentPlayer(1));
            String expected3 = "Abismo: infinite-loop";
            assertEquals(expected3, game.reactToAbyssOrTool());

            assertTrue(game.moveCurrentPlayer(3));
        }catch (Exception e){
            assertEquals("Nao deveria lancar exception",e.getMessage());
        }
    }

    @Test
    public void testmoveCurrentPlayer01(){
        GameManager game = new GameManager();
        String[][] players = createPlayers();

        try{
            game.createInitialBoard(players,10);

            for (int i = 0; i <3 ; i++) {
                assertTrue(game.moveCurrentPlayer(5));


                assertTrue(game.moveCurrentPlayer(5));


                assertTrue(game.moveCurrentPlayer(5));


                assertTrue(game.moveCurrentPlayer(5));

            }
            game.gameIsOver();
        }catch (Exception e){
            assertEquals("Nao deveria lancar exception",e.getMessage());
        }

    }

}
