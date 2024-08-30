object DungeonService {
    fun getRandomRoom(): Room {
        return Room("You are in a dark room.", false, emptySet())
    }
}