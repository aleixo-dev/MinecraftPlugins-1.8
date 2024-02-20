package br.com.nicolas.minecraftplugin.vanish.command

import br.com.nicolas.minecraftplugin.MinecraftPlugin
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class VanishCommand(
    private val plugin: MinecraftPlugin
) : CommandExecutor {
    override fun onCommand(
        sender: CommandSender?,
        command: Command?,
        label: String?,
        args: Array<out String>
    ): Boolean {

        if (sender is Player) {

            val player: Player = sender

            if (plugin.invisibleList.contains(player)) {
                for (p in Bukkit.getOnlinePlayers()) {
                    p.showPlayer(player)
                }

                plugin.invisibleList.remove(player)
                player.sendMessage("You are now visible to other players on the server.")

            } else if (!plugin.invisibleList.contains(player)) {

                for (people in Bukkit.getOnlinePlayers()) {
                    people.hidePlayer(player)
                }

                plugin.invisibleList.add(player)
                player.sendMessage("You are now invisible!");
            }
        }
        return true
    }
}