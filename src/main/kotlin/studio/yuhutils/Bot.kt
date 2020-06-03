package studio.yuhutils

import net.dv8tion.jda.api.AccountType
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import studio.yuhutils.jdalisteners.Message
import studio.yuhutils.spigotlisteners.AsyncChatListener
import io.github.cdimascio.dotenv.dotenv


class Bot(pluginInstance: Main) {
    private var jda: JDA
    private val plugin: Main = pluginInstance
    private val dotenv = dotenv()
    private val token: String? = dotenv["BOT_TOKEN"]

    init {
        jda = JDABuilder(AccountType.BOT)
                .setToken(token)
                .build()
        jda.addEventListener(Message(plugin, jda))
        AsyncChatListener(plugin, jda)
    }
}