package br.com.nicolas.minecraftplugin.commands.holograms

import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player

class HologramsCommand : CommandExecutor {

    override fun onCommand(
        sender: CommandSender?,
        command: Command?,
        label: String?,
        args: Array<out String>?
    ): Boolean {

        if (sender !is Player) return true

        val player: Player = sender

        val hologram = player.world.spawnEntity(player.location, EntityType.ARMOR_STAND) as ArmorStand
        with(hologram) {
            isVisible = false
            isCustomNameVisible = true
            customName = "${ChatColor.RED}Hologram"
            setGravity(false)
        }

        val secondHologram =
            player.world.spawnEntity(player.location.add(0.0, -0.5, 0.0), EntityType.ARMOR_STAND) as ArmorStand

        with(secondHologram) {
            isVisible = false
            isCustomNameVisible = true
            customName = "${ChatColor.RED}Line 2"
            setGravity(false)
        }

        return true
    }
}