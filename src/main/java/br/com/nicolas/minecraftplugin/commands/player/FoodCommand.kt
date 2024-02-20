package br.com.nicolas.minecraftplugin.commands.player

import br.com.nicolas.minecraftplugin.MinecraftPlugin
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable

class FoodCommand(
    private val plugin: MinecraftPlugin
) : CommandExecutor {

    override fun onCommand(
        sender: CommandSender?,
        command: Command?,
        label: String?,
        args: Array<out String>?
    ): Boolean {

        if (sender !is Player) return true
        val player: Player = sender

        if (player.hasPermission("permissions.food")) {
            player.apply {
                foodLevel = 20
                sendMessage("Hm-hm-hm ...")

                object : BukkitRunnable() {
                    override fun run() {
                        sendMessage("Yeaah! I'm feel so strong again!")
                    }
                }.runTaskLater(plugin, 20)
            }
        } else {
            player.sendMessage("Oops! you don't have permissions to run this command.")
        }

        return true
    }
}