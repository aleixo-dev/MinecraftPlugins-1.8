package br.com.nicolas.minecraftplugin.commands.spawn

import br.com.nicolas.minecraftplugin.MinecraftPlugin
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class SetSpawnCommand(private val plugin: MinecraftPlugin) : CommandExecutor {

    override fun onCommand(
        sender: CommandSender?,
        command: Command?,
        label: String?,
        args: Array<out String>?
    ): Boolean {

        if (sender !is Player) return true

        val player: Player = sender

        val location = player.location
        plugin.apply {
            config.set("spawn", location)
            saveConfig()
        }

        return true
    }

}