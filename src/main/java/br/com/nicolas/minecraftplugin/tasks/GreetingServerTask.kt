package br.com.nicolas.minecraftplugin.tasks

import br.com.nicolas.minecraftplugin.MinecraftPlugin
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable

class GreetingServerTask(
    private val plugin: MinecraftPlugin,
    private val player: Player
) : BukkitRunnable() {

    private var hasSendMessageToPlayer = false

    override fun run() {
        if (!hasSendMessageToPlayer) {
            player.sendMessage(" ")
            player.sendMessage("${ChatColor.GOLD}I hope you like this server!")
            player.sendMessage(" ")
            hasSendMessageToPlayer = true
        } else {
            this.cancel()
        }
    }
}