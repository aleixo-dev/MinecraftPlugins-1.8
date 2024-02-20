package br.com.nicolas.minecraftplugin.listeners

import br.com.nicolas.minecraftplugin.custom_events.GameEndEvent
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class GameListener : Listener {

    @EventHandler
    fun onGameEnd(gameEndEvent: GameEndEvent) {
        Bukkit.getServer().broadcastMessage("§1----- §cGAME ENDED! §1-----")
        Bukkit.getServer().broadcastMessage(" ")
        Bukkit.getServer().broadcastMessage("${ChatColor.GOLD}Winner: §a${gameEndEvent.playerOne.displayName}")
        Bukkit.getServer().broadcastMessage("${ChatColor.RED}Loser: §e${gameEndEvent.playerTwo.displayName}")
        Bukkit.getServer().broadcastMessage("SCORE: ${gameEndEvent.score}")
        Bukkit.getServer().broadcastMessage(" ")
        Bukkit.getServer().broadcastMessage("§1----- §cGAME ENDED! §1-----")
    }
}