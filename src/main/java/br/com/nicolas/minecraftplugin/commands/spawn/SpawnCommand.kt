package br.com.nicolas.minecraftplugin.commands.spawn

import br.com.nicolas.minecraftplugin.MinecraftPlugin
import org.bukkit.Location
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class SpawnCommand(private val plugin: MinecraftPlugin) : CommandExecutor {

    override fun onCommand(
        sender: CommandSender?,
        command: Command?,
        label: String?,
        args: Array<out String>?
    ): Boolean {

        if (sender !is Player) return true

        val player: Player = sender
        val location: Location? = plugin.config.get("spawn") as? Location

        if (location != null) {
            player.teleport(location)
            player.sendMessage("You have benn teleported to the spawn point.")
        } else {
            plugin.logger.info("There is no spawn point!")
        }

        return true
    }
}