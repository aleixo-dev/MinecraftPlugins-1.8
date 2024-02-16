package br.com.nicolas.minecraftplugin.commands.player

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class FoodCommand : CommandExecutor {

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
                sendMessage("Yeaah! I'm feel so strong again!")
            }
        } else {
            player.sendMessage("Oops! you don't have permissions to run this command.")
        }

        return true
    }
}