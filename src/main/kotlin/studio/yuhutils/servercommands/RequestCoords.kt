package studio.yuhutils.servercommands

import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.ComponentBuilder
import net.md_5.bungee.api.chat.HoverEvent
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import org.bukkit.entity.Player
import java.util.*


class RequestCoords : TabExecutor {
    private var cooldowns = HashMap<Player, Long>()


    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<String>): Boolean {
        val cooldownTime = 10
        if (sender is Player) {
            if (cooldowns.containsKey(sender)) {
                val secondsLeft = cooldowns.get(sender)!! / 1000 + cooldownTime - System.currentTimeMillis() / 1000
                if (secondsLeft > 0) {
                    // Still cooling down
                    sender.sendMessage("You cant use that commands for another $secondsLeft seconds!")
                    return true
                }
            } else {
                if (args.isNotEmpty()) {
                    val target = Bukkit.getServer().getPlayer(args[0])
                    if (target != null) {
                        val message = TextComponent(ChatColor.DARK_PURPLE.toString() + sender.name + ChatColor.GREEN + " is requesting your coordinates. Would you like to share your coordinates with " + ChatColor.DARK_PURPLE + sender.name + "?")
                        message.isUnderlined = true
                        message.clickEvent = ClickEvent(ClickEvent.Action.RUN_COMMAND, "/sharecoords " + sender.name)
                        message.hoverEvent = HoverEvent(HoverEvent.Action.SHOW_TEXT,
                                ComponentBuilder("Are you sure you want to share your coordinates with this player?").color(ChatColor.DARK_RED).italic(true).create())
                        target.spigot().sendMessage(message)
                    }
                } else {
                    sender.sendMessage("No player specified to send the data to.")
                    sender.sendMessage("/requestcoords [playerName]")
                }
            }

        }
        return true
    }

    override fun onTabComplete(commandSender: CommandSender, command: Command, s: String, args: Array<String>): List<String>? {
        if (args.size == 1) {

            return getPlayerNames()
        }
        return null
    }
}
