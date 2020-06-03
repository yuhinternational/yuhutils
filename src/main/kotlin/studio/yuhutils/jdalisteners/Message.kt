package studio.yuhutils.jdalisteners

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import studio.yuhutils.Main
import java.awt.Color
import java.util.*


class Message(plugin: Main, jda: JDA) : ListenerAdapter() {
    private fun message(author: String, msg: String) {
        Bukkit.getServer().broadcastMessage(ChatColor.GOLD.toString() + author + ChatColor.AQUA + msg)
    }

    override fun onGuildMessageReceived(e: GuildMessageReceivedEvent) {
        if (e.author.isBot) return
        println("Message Received")
        if (e.channel.name == "minecraft") {
            if (e.message.contentRaw.equals("list", ignoreCase = true)) {
                val numPlayers = Bukkit.getServer().onlinePlayers.size
                val playerList: MutableList<String> = ArrayList()
                println('W')
                if (numPlayers == 0) {
                    e.channel.sendMessage("No players currently online.")
                    return
                }
                for (p in Bukkit.getServer().onlinePlayers) {
                    playerList.add(p.name)
                }
                val descriptionList = java.lang.String.join("\n", playerList)
                val embedInfo = EmbedBuilder()
                        .setTitle("Player Info")
                        .addField("Number of Online Players: ", Integer.toString(numPlayers), true)
                        .setDescription(descriptionList)
                e.channel.sendMessage(embedInfo.build()).queue()
                return
            }
            val author = e.author.name + "#" + e.author.discriminator + ": "
            message(author, e.message.contentRaw)
        }
    }



    companion object {
        private var jda: JDA? = null
        private var plugin: Main? = null

        fun chatMessage(author: String?, message: String?) {
            val mcChannel = jda!!.getTextChannelById("705600723089621073")!!
            val embed = EmbedBuilder()
            embed.setTitle(author)
            embed.setColor(Color(0x03FCCF))
            embed.setDescription(message)
            mcChannel.sendMessage(embed.build()).queue()
        }
    }

    init {
        Companion.plugin = plugin
        Companion.jda = jda
    }
}