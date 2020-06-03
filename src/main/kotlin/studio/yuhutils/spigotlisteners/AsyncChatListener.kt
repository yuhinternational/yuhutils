package studio.yuhutils.spigotlisteners

import net.dv8tion.jda.api.JDA
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent
import studio.yuhutils.Main
import studio.yuhutils.jdalisteners.Message


class AsyncChatListener(plugin: Main, api: JDA?) : Listener {
    @EventHandler
    fun onAsyncPlayerChatEvent(e: AsyncPlayerChatEvent) {
        println("message sent")
        val message = e.message
        val author = e.player.displayName
        //System.out.println(author.getName() + " has said: " + message);
        Message.chatMessage(author, message)
    }

    companion object {
        private var plugin: Main? = null
        private var jda: JDA? = null
    }

    init {
        Companion.plugin = plugin
        jda = api
        Bukkit.getPluginManager().registerEvents(this, plugin)
    }
}