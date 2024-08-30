import Game.commands
import Game.dungeonMap
import Game.playerPosition
import Game.gameText
import Game.gameStatus
import Game.questId
import Game.afterTriggerText
import GameData.quests
import java.awt.Point
import kotlin.system.exitProcess

object Game {
    var gameStatus = GameStatus.MENU
    var gameText: String = "MAIN MENU"
    var afterTriggerText: String = ""
    var character = Character()
    val commands = mutableListOf<Command>()
    var questId = 0
    var playerPosition = Point(0,0)
    var dungeonMap : Array<Array<Room>> = Array(10) { Array(10) { Room("EMPTY ROOM", false, emptySet()) } }
}

data class Command(val status: GameStatus, val message: String, val trigger: String, val function: () -> Unit)

fun main() {
    commands.add(Command(GameStatus.MENU, "New game.", "n") {
        gameStatus = GameStatus.CHOOSE_CHARACTER
    })
    commands.add(Command(GameStatus.MENU, "Exit game.", "e") {
        exitProcess(0)
    })
    commands.add(Command(GameStatus.CHOOSE_CHARACTER, "Warrior", "w") {
        Game.character = Character(strength = 50, dexterity = 40, intelligence = 30)
        gameStatus = GameStatus.DUNGEON
        questId = 0
        gameText = "Welcome on your first quest. It will be a simple one. ${quests[questId].objective}"
    })
    commands.add(Command(GameStatus.CHOOSE_CHARACTER, "Rogue", "r") {
        Game.character = Character(strength = 37, dexterity = 50, intelligence = 37)
        gameStatus = GameStatus.DUNGEON
        questId = 0
        gameText = "Welcome on your first quest. It will be a simple one. ${quests[questId].objective}"
    })
    commands.add(Command(GameStatus.CHOOSE_CHARACTER, "mage", "m") {
        Game.character = Character(strength = 30, dexterity = 40, intelligence = 50)
        gameStatus = GameStatus.DUNGEON
        questId = 0
        gameText = "Welcome on your first quest. It will be a simple one. ${quests[questId].objective}"
        playerPosition = Point(20,20)
        //dungeonMap[playerPosition.x][playerPosition.y] = //todo
    })
    commands.add(Command(GameStatus.DUNGEON, "move west", "w") {
        afterTriggerText = "You moved west."
    })

    // Game loop
    while (true) {
        clearConsole()
        //print result of previous command
        afterTriggerText.isNotBlank().apply { println(afterTriggerText) }

        // Print the current options
        println(gameText)

        // Print the available commands
        val availableCommands = commands.filter { it.status == gameStatus }
        availableCommands.forEach { println("${it.message} (${it.trigger})") }

        // Get the player's command
        val command = readlnOrNull()

        // Process the command
        availableCommands.find { it.trigger == command }?.function?.invoke()
            ?: run { afterTriggerText = "Invalid command." }
    }
}


fun clearConsole() {
    when {
        System.getProperty("os.name").contains("Windows") -> {
            print("\u001b[H\u001b[2J")
            System.out.flush()
        }

        else -> Runtime.getRuntime().exec("clear")
    }
}
