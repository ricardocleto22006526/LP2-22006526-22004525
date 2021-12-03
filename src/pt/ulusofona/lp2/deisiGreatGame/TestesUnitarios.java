package pt.ulusofona.lp2.deisiGreatGame;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestesUnitarios {

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
    public void testBSOD01() {
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
}
