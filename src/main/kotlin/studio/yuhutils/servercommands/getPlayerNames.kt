package studio.yuhutils.servercommands

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.util.*


fun getPlayerNames(): List<String>? {
    val playerNames: MutableList<String> = ArrayList()
    val players: Array<Player> = Bukkit.getServer().onlinePlayers.toTypedArray();
    for (element in players) {
        playerNames.add(element.name)
    }
    return playerNames
}
