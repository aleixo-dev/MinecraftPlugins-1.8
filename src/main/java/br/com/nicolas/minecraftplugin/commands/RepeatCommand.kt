package br.com.nicolas.minecraftplugin.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class RepeatCommand : CommandExecutor {

    override fun onCommand(
        sender: CommandSender?, command: Command?, label: String?, args: Array<out String>?
    ): Boolean {

        if (sender !is Player) return true

        val player: Player = sender

        if (args.isNullOrEmpty()) {
            player.sendMessage("You didn't provide any arguments when running the command. Try again!")
            player.sendMessage("Use /repeat <message>")
            return true
        }

        if (args.size == 1) {
            player.sendMessage("Message: ${args[0]}")
        } else {

            val stringBuilder = StringBuilder()

            for (i in args.indices) {
                stringBuilder.append(args[i])
                stringBuilder.append(" ")
            }

            player.sendMessage("Here your big message: $stringBuilder")
        }
        return true
    }
}