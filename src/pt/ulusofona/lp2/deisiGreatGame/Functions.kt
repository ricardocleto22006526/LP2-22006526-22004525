package pt.ulusofona.lp2.deisiGreatGame

enum class CommandType { GET, POST }

fun router():  (CommandType) -> (GameManager,List<String>) -> String?{

    return { commandType -> qualComando(commandType) }
}

fun qualComando(tipoDeComando : CommandType): (GameManager,List<String>) -> String? {

    when(tipoDeComando) {
        CommandType.GET -> return { game, valores -> commandTypeGETfuncao(game, valores) }
        CommandType.POST -> return { game, valores -> commandTypePOSTfuncao(game, valores) }
    }

}

fun commandTypePOSTfuncao(game: GameManager, lista: List<String>) : String? {
    when(lista[0]){
        "MOVE" -> return move(game, lista)
        "ABYSS" -> return abyss(game, lista)
    }

    return null
}

fun commandTypeGETfuncao(game: GameManager, lista: List<String>) : String? {
    when(lista[0]){
        "PLAYER" -> return player(game, lista)
        "PLAYERS_BY_LANGUAGE" -> return playersByLanguage(game, lista)
        "POLYGLOTS" -> return polyglots(game)
        "MOST_USED_POSITIONS" -> return mostUsedPositions(game, lista)
        "MOST_USED_ABYSSES" -> return mostUsedAbysses(game, lista)
    }
    return null
}

fun player(manager: GameManager, inputNomeDoJogador: List<String>): String? {

    return if (manager.players.filter { it.name == inputNomeDoJogador[1] }.joinToString { it.toString() }.isEmpty()) { "Inexistent player"
    } else { manager.players.filter { it.name == inputNomeDoJogador[1] }.joinToString { it.toString() } }
}

fun playersByLanguage(manager: GameManager, inputLinguagem: List<String>): String? {

    return manager.players.filter { it.temAlinguagem(inputLinguagem[1]) }.joinToString(","){ it.name }
}

fun polyglots(manager: GameManager): String? {

    return manager.players.filter { it.linguagensFavoritasKotlin.size > 1 }.sortedBy { it.linguagensFavoritasKotlin.size }
        .joinToString("\n") { it.name + ":" + it.linguagensFavoritasKotlin.distinct().count()}
}

fun move(manager: GameManager, posicao: List<String>): String? {
    manager.moveCurrentPlayer(Integer.parseInt(posicao[1]))

    return if (manager.reactToAbyssOrTool()==null){ "OK" } else {
        manager.playersAbyssesAndTools[manager.playerAJogar].toString()
    }
}

fun abyss(manager: GameManager, tipoAbismo: List<String>): String? {
    return if ( manager.adicionarAbismoOuFerramentaNaCollection( Integer.parseInt(tipoAbismo[1]), Integer.parseInt(tipoAbismo[2]) ) ) { "OK" }
    else { "Position is occupied" }
}

fun mostUsedPositions(manager: GameManager, posicoesMaisPisadas: List<String>): String? {
    return manager.casasMaisPisadasNoJogo.map { it.key to it.value }.sortedByDescending { it.second }.take(Integer.parseInt(posicoesMaisPisadas[1])).joinToString("\n"){""+ it.first + ":" + it.second }
}

fun mostUsedAbysses(manager: GameManager, abismosMaisUsados: List<String>): String? {
    return manager.abyssesMaisPisadasNoJogo.map { it.key to it.value }.sortedByDescending { it.second }.take(Integer.parseInt(abismosMaisUsados[1])).joinToString("\n"){""+ it.first + ":" + it.second }
}



