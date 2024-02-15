package br.com.nicolas.minecraftplugin.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class SpeedCommand : CommandExecutor {

    override fun onCommand(
        sender: CommandSender?,
        command: Command?,
        label: String?,
        args: Array<out String>?
    ): Boolean {

        if (sender !is Player) {
            return true
        }

        val player: Player = sender

        player.addPotionEffect(
            PotionEffect(
                PotionEffectType.SPEED,
                1000,
                2,
                true,
                true
            ),
            true
        )
        return true
    }
}