package br.com.nicolas.minecraftplugin.commands.player

import br.com.nicolas.minecraftplugin.files.CustomConfig
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class MessagePlayer : CommandExecutor {
    override fun onCommand(
        sender: CommandSender?,
        command: Command?,
        label: String?,
        args: Array<out String>?
    ): Boolean {

        if (sender is Player) {

            val player : Player = sender
            player.sendMessage(CustomConfig.fileConfiguration.getString("message"))

            return true
        }

        return true

    }
}