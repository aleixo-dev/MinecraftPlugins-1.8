package br.com.nicolas.minecraftplugin.commands.target

import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class PotionPlayerCommand : CommandExecutor {
    override fun onCommand(
        sender: CommandSender?,
        command: Command?,
        label: String?,
        args: Array<out String>?
    ): Boolean {

        if (sender !is Player) return true

        val player: Player = sender

        if (args.isNullOrEmpty()) {
            player.sendMessage("Invalid command! use /potion <player>")
            return true
        }

        val target = Bukkit.getServer().getPlayerExact(args[0])
        if (target == null) {
            player.sendMessage("I'm so sorry! this player is not online.")
        } else {
            player.sendMessage("You sent a special potion to ${target.displayName}")
            target.sendMessage("Wow dude! you received potion effects ${player.displayName}! :)")
            target.addPotionEffect(PotionEffect(PotionEffectType.JUMP, 2000, 3))
        }
        return true
    }
}