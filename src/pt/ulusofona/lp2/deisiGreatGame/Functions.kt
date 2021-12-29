package pt.ulusofona.lp2.deisiGreatGame

enum class CommandType { GET, POST }

//Function1<CommandType, Function2<GameManager, List<String>, String>> = ((CommandType) -> (GameManager, List<String>)->String)?
//(CommandType?) -> ((CommandType) -> (GameManager, List<String>) -> String)?
fun router(): ((CommandType?) -> (GameManager, List<String>)->String)? {
    //return ::comando
    return null
}

fun comando(tipo : CommandType?): ((CommandType?) -> (GameManager, List<String>) -> String)?{
    when(tipo){
        CommandType.GET ->return null
        CommandType.POST ->return null
    }
    return null
}
/*
fun abc(tipo:CommandType,manager: GameManager, args: List<String>) : String {
    when(tipo){
        CommandType.GET ->return comando(manager,args).toString()
        CommandType.POST ->return  comando(manager,args).toString()
    }
}
 */

/*
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

 */


fun getplayer(manager: GameManager,args: List<String>):String {
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

