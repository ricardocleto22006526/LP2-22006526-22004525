package pt.ulusofona.lp2.deisiGreatGame

enum class CommandType { GET, POST }

//Function1<CommandType, Function2<GameManager, List<String>, String>> = ((CommandType) -> (GameManager, List<String>)->String)?
//(CommandType?) -> ((CommandType) -> (GameManager, List<String>) -> String)?
//((CommandType?) -> (GameManager, List<String>)->String)?


//Function1<CommandType, Function2<GameManager, List<String>, String?>>
fun router():  (CommandType) -> (GameManager,List<String>) -> String?{

    return { commandType -> qualComando(commandType) }
}


//Function2<GameManager, List<String>, String?>
fun qualComando(tipoDeComando : CommandType): (GameManager,List<String>) -> String? {

    when(tipoDeComando) {
        CommandType.GET -> return { game, valores -> commandTypeGETfuncao(game, valores) }
        CommandType.POST -> return { game, valores -> commandTypeGETfuncao(game, valores) }
    }

}

fun commandTypePOSTfuncao(game: GameManager, lista: List<String>) : String? {
    when(lista.get(0)){
        "MOVE" -> return move(game, lista.get(1))
        "ABYSS" -> return abyss(game, lista.get(1), lista.get(2))
    }

    return null
}

fun commandTypeGETfuncao(game: GameManager, lista: List<String>) : String? {
    when(lista.get(0)){
        "PLAYER" -> return player(game, lista.get(1))
        "PLAYERS_BY_LANGUAGE" -> return playersByLanguage(game, lista.get(1))
        "POLYGLOTS" -> return polyglots(game)
        "MOST_USED_POSITIONS" -> return mostUsedPositions(game, lista.get(1))
        "MOST_USED_ABYSSES" -> return mostUsedAbysses(game, lista.get(1))
    }
    return null
}


fun player(manager: GameManager, inputNomeDoJogador: String): String? {

    return ""
}

fun playersByLanguage(manager: GameManager, inputLinguagem: String): String? {

    return ""
}

fun polyglots(manager: GameManager): String? {

    return ""
}

fun mostUsedPositions(manager: GameManager, posicoesMaisPisadas: String): String? {

    return ""
}

fun mostUsedAbysses(manager: GameManager, abismosMaisUsados: String): String? {

    return ""
}

fun move(manager: GameManager, posicao: String): String? {

    return ""
}

fun abyss(manager: GameManager, tipoAbismo: String, posicao: String): String? {

    return ""
}



/*
fun abc(tipo:CommandType,manager: GameManager, args: List<String>) : String {
    when(tipo){
        CommandType.GET ->return comando(manager,args).toString()
        CommandType.POST ->return  comando(manager,args).toString()
    }
}

fun introduzido(manager: GameManager, args: List<String>): String?{
    when(args[0]){
        "PLAYER" -> return getplayer(manager,args)
        "PLAYERS_BY_LANGUAGE" -> return playersByLanguage()
        "POLYGLOTS" -> return polyglots()
        "MOST_USED_POSITIONS" -> return mostUsedPositions()
        "MOST_USED_ABYSSES" -> return mostUsedAbysses()
        "MOVE" -> return move(manager)
        "ABYSS" -> return abyss()
    }
    return null
}




fun getplayer(manager: GameManager, args: String):String? {

    //NAO SEI SE FUNCIONA
    if (manager.players.equals(args[1])){
        return manager.players.toString()
    }
    return "Inexistent player"

}




fun playersByLanguage():String{
    return ""
}

fun polyglots():String{
    return ""
}

fun mostUsedPositions():String{
    return ""
}

fun mostUsedAbysses():String{
    return ""
}

fun move(gameManager: GameManager):String{
    gameManager.players.get(gameManager.playerAJogar).andaParaAFrente(3)
    return ""
}

fun abyss():String{
    return ""
}
 */