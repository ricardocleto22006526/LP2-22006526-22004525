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
        GameManager game = new GameManager();
        assertFalse("Não pode andar posições negativas", game.moveCurrentPlayer(-1));
    }

    @Test
    public void testMoveCurrentPlayerParaUmaPosicaoMaiorQueOTamanhoDoTabuleiro() {
        GameManager game = new GameManager();
        assertFalse("Não pode andar para posições exteriores ao tabuleiro", game.moveCurrentPlayer(game.tamanhoDoTabuleiro));
    }

}
