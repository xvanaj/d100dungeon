
data class Room(
    val description: String,
    var monster: Boolean,
    var directions: Set<Direction>
)

enum class Direction {    NORTTH,    SOUTH,    EAST,    WEST, }

data class Character(
    var strength: Int = 50,
    var dexterity: Int = 40,
    var intelligence: Int = 30,
    var reputation: Int = 1,
    var health: Int = 10,
    var fate: Int = 3,
    var lives: Int = 3,
    var skills: Map<String, Int> = emptyMap(),
    var skillBonuses: Map<String, Int> = emptyMap(),
    var characteristicsExperience: Map<String, Int> = mapOf("strength" to 0, "dexterity" to 0, "intelligence" to 0),
    var skillsExperience: Map<String, Int> = mapOf("strength" to 0, "dexterity" to 0, "intelligence" to 0),
    var equipment: List<String> = emptyList(),

    var defense: Int = 0,
    var damage: Int = 0,
    var hpBonuses: Int = 0,
    var characteristicsBonuses: Map<String, Int> = mapOf("strength" to 0, "dexterity" to 0, "intelligence" to 0),
)

data class Monster(
    val probabilityRange: IntRange,
    val name: String,
    val attackValue: Int,
    val def: Int,
    val damage: Int,
    val hp: Int,
    val abilities: List<String>,
    val lootTables: List<String>,
)

data class Quest(
    val probabilityRange: IntRange,
    val name : String,
    val objective: String,
    val encounterModifier: Int,
    val rewardGold: Int,
    val rewardRep: Int,
    val failureResult: String,
)

enum class GameStatus {
    NEW_GAME,
    IN_GAME,
    GAME_OVER,
    GAME_WON,
    MENU,
    CHOOSE_CHARACTER,
    CHOOSE_QUEST,
    DUNGEON,
}