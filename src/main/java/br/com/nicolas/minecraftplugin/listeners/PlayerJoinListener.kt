package br.com.nicolas.minecraftplugin.listeners
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class PlayerJoinListener : Listener {

    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        event.joinMessage = ""
        event.player.apply {
            sendMessage("§a-> §e${this.name} Welcome to server!")
        }
    }
}