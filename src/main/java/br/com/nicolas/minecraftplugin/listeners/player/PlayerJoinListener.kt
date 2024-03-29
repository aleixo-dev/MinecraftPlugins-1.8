package br.com.nicolas.minecraftplugin.listeners.player

import br.com.nicolas.minecraftplugin.MinecraftPlugin
import br.com.nicolas.minecraftplugin.automessage.AutoMessageTask
import br.com.nicolas.minecraftplugin.tasks.GreetingServerTask
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.scheduler.BukkitRunnable

class PlayerJoinListener(private val plugin: MinecraftPlugin) : Listener {

    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {

        event.joinMessage = ""

        var joinMessage = plugin.config.getString("join-message")
        if (joinMessage != null) {
            joinMessage = joinMessage.replace("%player%", event.player.displayName)
            event.player.sendMessage(ChatColor.translateAlternateColorCodes('&', joinMessage))
            GreetingServerTask(plugin, event.player).runTaskLater(plugin, 40)
        }

        val feedPlayer = plugin.config.getBoolean("feed-players")
        if (feedPlayer) {
            event.player.foodLevel = 20
        }

        if (plugin.config.getBoolean("show-foods")) {
            val foods = plugin.config.getStringList("foods")
            event.player.sendMessage("Your favorite foods are:")
            for (food in foods) {
                event.player.sendMessage("- $food")
            }
        }

        invisiblePlayer(event.player)
        motdServerMessage(event.player)

        if (plugin.config.getBoolean("enabled-auto-message")) {

            AutoMessageTask(plugin, event.player).runTaskTimerAsynchronously(
                plugin,
                plugin.config.getLong("delay-start"),
                plugin.config.getLong("delay-message")
            )
        }

    }


    private fun invisiblePlayer(player: Player) {
        for (invisiblePlayer in plugin.invisibleList) {
            player.hidePlayer(player)
        }
    }

    private fun motdServerMessage(player: Player) {
        if (plugin.config.getBoolean("motd")) {
            for (i in plugin.config.getStringList("motd-message")) {
                player.sendMessage(i)
            }
        }
    }
}