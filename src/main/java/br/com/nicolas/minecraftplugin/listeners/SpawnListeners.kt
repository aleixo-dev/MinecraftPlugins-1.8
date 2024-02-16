package br.com.nicolas.minecraftplugin.listeners

import br.com.nicolas.minecraftplugin.MinecraftPlugin
import org.bukkit.Location
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerRespawnEvent

class SpawnListeners(private val plugin : MinecraftPlugin) : Listener {

    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {

        if (!event.player.hasPlayedBefore()) {
            val location: Location? = plugin.config.get("spawn") as? Location
            if (location != null) {
                event.player.teleport(location)
            }
        }
    }

    @EventHandler
    fun onPlayerDeath(event : PlayerRespawnEvent) {
        val location : Location? = plugin.config.get("spawn") as? Location
        if (location != null) {
            event.respawnLocation = location
        }
    }
}