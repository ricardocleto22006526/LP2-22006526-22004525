package pt.ulusofona.lp2.deisiGreatGame;

import org.junit.Test;

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
    public void comoFazerUnitTests() {
        GameManager game = new GameManager();

        String[][] players = new String[4][4];
        String[][] abismos = new String[1][3];

        //metes a informação que queres nos jogadores
        players[0][0] = "1";
        players[0][1] = "Jogador1";
        players[0][2] = "Java";
        players[0][3] = "Purple";

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

        //metes a informação que queres no abismo, no teu caso agora queres testar o BSOD
        abismos[0][0] = "0";
        abismos[0][1] = "7";
        abismos[0][2] = "5";

        /*
        //crias o gameManager, podes tambem fazer um asserTrue disto para ver se o createInitialBoard está a funcionar corretamente
        game.createInitialBoard(players,10,abismos);

        // avanças o jogador para a casa onde esta o abismo
        assertTrue(game.moveCurrentPlayer(4));

        System.out.println(game.getCurrentPlayerID());

        //msg que estas a espera de receber quando alguem cai no abismo BSOD e nao tem a ferramenta
        String expected = "Abismo: bsod";

        // podes tambem fazer um assert false neste caso, porque so um jogador foi eliminado entao o jogo continua
        game.gameIsOver();

        // chamas o react para reagir ao abismo
        assertEquals(expected,game.reactToAbyssOrTool());
         */

        game.createInitialBoard(players,10,abismos);

        assertTrue(game.moveCurrentPlayer(4));
        System.out.println(game.getCurrentPlayerID());
        String expected1 = "Abismo: bsod";
        game.gameIsOver();
        assertEquals(expected1,game.reactToAbyssOrTool());

        assertTrue(game.moveCurrentPlayer(5));
        System.out.println(game.getCurrentPlayerID());
        String expected2 = null;
        game.gameIsOver();
        assertEquals(expected2,game.reactToAbyssOrTool());


        assertTrue(game.moveCurrentPlayer(4));
        System.out.println(game.getCurrentPlayerID());
        String expected3 = "Abismo: bsod";
        game.gameIsOver();
        assertEquals(expected3,game.reactToAbyssOrTool());


        assertTrue(game.moveCurrentPlayer(1));
        System.out.println(game.getCurrentPlayerID());
        String expected4 = null;
        game.gameIsOver();
        assertEquals(expected4,game.reactToAbyssOrTool());

        game.gameIsOver();

        /*
        assertTrue(game.moveCurrentPlayer(2));
        System.out.println(game.getCurrentPlayerID());
        String expected5 = null;
        game.gameIsOver();
        assertEquals(expected5,game.reactToAbyssOrTool());

        assertTrue(game.moveCurrentPlayer(1));
        System.out.println(game.getCurrentPlayerID());
        String expected6 = null;
        game.gameIsOver();
        assertEquals(expected6,game.reactToAbyssOrTool());

        assertTrue(game.moveCurrentPlayer(3));
        System.out.println(game.getCurrentPlayerID());
        String expected7 = null;
        game.gameIsOver();
        assertEquals(expected7,game.reactToAbyssOrTool());

        assertTrue(game.moveCurrentPlayer(2));
        System.out.println(game.getCurrentPlayerID());
        String expected8 = null;
        game.gameIsOver();
        assertEquals(expected8,game.reactToAbyssOrTool());

         */


        //"Abismo: bsod"
        //null

        //continuas a avançar jogadores
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
    public void testCreateInitialBoard01com2parametros(){
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


        assertFalse("Como tem 2 id's repetidos, é suposto dar como inválido" , game.createInitialBoard(players,10) );
    }

    @Test
    public void testCreateInitialBoard02com2parametros(){
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


        assertFalse("Como tem um nome do programador invalido, dá como inválido" , game.createInitialBoard(players,10) );
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

        assertFalse("Como tem um nome do programador invalido, dá como inválido" , game.createInitialBoard(players,10));
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

        boolean a=game.createInitialBoard(players,10);
        assertFalse("Como o jogador tem uma cor inválida, dá como inválido" , a);
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

        assertFalse("2 jogadores têm a mesma cor,logo dá como inválido" , game.createInitialBoard(players,10));
    }

    @Test
    public void testCreateInitialBoard06com2parametros(){
        GameManager game = new GameManager();
        String[][] players = new String[1][4];

        players[0][0] = "1"; //ID
        players[0][1] = "Jogador1"; //NOME
        players[0][2] = "Java"; //Linguagens Favoritas
        players[0][3] = "Purple"; //Cor do player
        assertFalse("Respeite o minimo de jogadores possivel" , game.createInitialBoard(players,10));
    }

    @Test
    public void testCreateInitialBoard01com3parametros(){
        GameManager game = new GameManager();
        String[][] abismosOUferramentas = new String[1][3];

        //criacao dos abismos ou tools
        abismosOUferramentas[0][0] = "3"; // [0]abismo - [1]ferramenta
        abismosOUferramentas[0][1] = "5"; //ID do abismo/ferramenta
        abismosOUferramentas[0][2] = "30"; //Posicao no tabuleiro

        boolean abyssesAndTools = game.createInitialBoard(createPlayers(),50,abismosOUferramentas);
        assertFalse("id do abismo ou ferramenta inválido" , abyssesAndTools );
    }

    @Test
    public void testCreateInitialBoard02com3parametros(){
        GameManager game = new GameManager();
        String[][] abismosOUferramentas = new String[1][3];

        //criacao dos abismos ou tools
        abismosOUferramentas[0][0] = "3"; // [0]abismo - [1]ferramenta
        abismosOUferramentas[0][1] = "5"; //ID do abismo/ferramenta
        abismosOUferramentas[0][2] = "30"; //Posicao no tabuleiro

        boolean abyssesAndTools = game.createInitialBoard(createPlayers(),50,abismosOUferramentas);
        assertFalse("id do abismo ou ferramenta inválido" , abyssesAndTools );
    }

    @Test
    public void testCreateInitialBoard03com3parametros(){
        GameManager game = new GameManager();
        String[][] abismosOUferramentas = new String[1][3];

        //criacao dos abismos ou tools
        abismosOUferramentas[0][0] = "0"; // [0]abismo - [1]ferramenta
        abismosOUferramentas[0][1] = "10"; //ID do abismo/ferramenta
        abismosOUferramentas[0][2] = "30"; //Posicao no tabuleiro

        boolean abyssesAndTools = game.createInitialBoard(createPlayers(),50,abismosOUferramentas);
        assertFalse("id do abismo ou ferramenta inválido" , abyssesAndTools );
    }

    @Test
    public void testCreateInitialBoard04com3parametros(){
        GameManager game = new GameManager();
        String[][] abismosOUferramentas = new String[1][3];

        //criacao dos abismos ou tools
        abismosOUferramentas[0][0] = "1"; // [0]abismo - [1]ferramenta
        abismosOUferramentas[0][1] = "6"; //ID do abismo/ferramenta
        abismosOUferramentas[0][2] = "30"; //Posicao no tabuleiro

        boolean abyssesAndTools = game.createInitialBoard(createPlayers(),50,abismosOUferramentas);
        assertFalse("id do abismo ou ferramenta inválido" , abyssesAndTools );
    }

    @Test
    public void testCreateInitialBoard05com3parametros(){
        GameManager game = new GameManager();
        String[][] abismosOUferramentas = new String[1][3];

        //criacao dos abismos ou tools
        abismosOUferramentas[0][0] = "1"; // [0]abismo - [1]ferramenta
        abismosOUferramentas[0][1] = "2"; //ID do abismo/ferramenta
        abismosOUferramentas[0][2] = "72"; //Posicao no tabuleiro

        boolean abyssesAndTools = game.createInitialBoard(createPlayers(),50,abismosOUferramentas);
        assertFalse("posicao do abismo ou tool invalida" , abyssesAndTools );
    }















































    @Test
    public void testBSOD01() {
        GameManager game = new GameManager();
        String[][] players = createPlayers();
        String[][] abismoOUferramenta = createAbismosOUTools("0","7","5");
        game.createInitialBoard(players,10,abismoOUferramenta);

        assertTrue(game.moveCurrentPlayer(4));
        System.out.println(game.getCurrentPlayerID());
        String expected1 = "Abismo: bsod";
        game.gameIsOver();
        assertEquals(expected1,game.reactToAbyssOrTool());

        assertTrue(game.moveCurrentPlayer(5));
        System.out.println(game.getCurrentPlayerID());
        String expected2 = null;
        game.gameIsOver();
        assertEquals(expected2,game.reactToAbyssOrTool());


        assertTrue(game.moveCurrentPlayer(4));
        System.out.println(game.getCurrentPlayerID());
        String expected3 = "Abismo: bsod";
        game.gameIsOver();
        assertEquals(expected3,game.reactToAbyssOrTool());


        assertTrue(game.moveCurrentPlayer(1));
        System.out.println(game.getCurrentPlayerID());
        String expected4 = null;
        game.gameIsOver();
        assertEquals(expected4,game.reactToAbyssOrTool());

        game.gameIsOver();

    }
}
