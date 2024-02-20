package br.com.nicolas.minecraftplugin.commands.custom_event

import br.com.nicolas.minecraftplugin.events.GameEndEvent
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class GameEndCommand : CommandExecutor {
    override fun onCommand(
        sender: CommandSender?,
        command: Command?,
        label: String?,
        args: Array<out String>
    ): Boolean {

        if (sender is Player) {

            val player: Player = sender

            if (args.size == 1) {
                val target = Bukkit.getPlayerExact(args[0])
                if (target != null) {
                    Bukkit.getServer().pluginManager.callEvent(GameEndEvent(player, target, 2000))
                }
            } else {
                player.sendMessage("Ooops! try use /endgame <player>")
            }
        }
        return true
    }
}