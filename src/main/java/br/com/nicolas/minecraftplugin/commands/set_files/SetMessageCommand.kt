package br.com.nicolas.minecraftplugin.commands.set_files

import br.com.nicolas.minecraftplugin.MinecraftPlugin
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.lang.StringBuilder

class SetMessageCommand(private val plugin: MinecraftPlugin) : CommandExecutor {

    override fun onCommand(
        sender: CommandSender?,
        command: Command?,
        label: String?,
        args: Array<out String>?
    ): Boolean {

        if (sender !is Player) return true

        val player: Player = sender

        if (args.isNullOrEmpty()) {
            player.sendMessage("You must provide a new message!")
            return true
        }

        val message = StringBuilder()
        for (msg in args.indices) {
            message.append("${args[msg]} ")
        }
        with(plugin) {
            config.set("join-message", message.toString())
            saveConfig()
        }

        return true
    }
}