package br.com.nicolas.minecraftplugin.automessage

import br.com.nicolas.minecraftplugin.MinecraftPlugin
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable

class AutoMessageTask(
    val plugin: MinecraftPlugin,
    val player : Player
) : BukkitRunnable() {

    private val messages = plugin.config.getStringList("messages")
    private var index = 0

    override fun run() {

        if (messages.isNullOrEmpty()) {
            cancel()
            return
        }

        if (index < messages.size) {
            player.sendMessage(" ")
            player.sendMessage(
                ChatColor.translateAlternateColorCodes(
                    '&',
                    "${plugin.config.getString("prefix-message")} ${messages[index]}"
                )
            )
            player.sendMessage(" ")
            index++
        } else {
            index = 0
        }
    }
}