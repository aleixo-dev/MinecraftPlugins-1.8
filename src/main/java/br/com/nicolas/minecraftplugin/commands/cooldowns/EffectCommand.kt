package br.com.nicolas.minecraftplugin.commands.cooldowns

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import java.util.UUID
import java.util.concurrent.TimeUnit

class EffectCommand : CommandExecutor {

    // key = uuid of the play
    // long = the epoch time of when trey ran the command
    private val cooldown: HashMap<UUID, Long> = hashMapOf()

    override fun onCommand(
        sender: CommandSender?,
        command: Command?,
        label: String?,
        args: Array<out String>?
    ): Boolean {

        if (sender !is Player) return true

        val player: Player = sender

        if (!cooldown.containsKey(player.uniqueId)) {
            cooldown[player.uniqueId] = System.currentTimeMillis()
            addEffect(player)
            player.sendMessage("You received effect.")
        } else {
            val timeElapsed: Long = System.currentTimeMillis() - (cooldown[player.uniqueId] ?: 0L)
            val remainingTimeSeconds : Long = (10000 - timeElapsed) / 1000
            if (remainingTimeSeconds <= 0) {
                cooldown[player.uniqueId] = System.currentTimeMillis()
                addEffect(player)
                player.sendMessage("You received effect.")
            } else {
                player.sendMessage("You can't do that yet $remainingTimeSeconds seconds remaining.")
            }
        }

        return true
    }

    private fun addEffect(player: Player) {
        player.addPotionEffect(PotionEffect(PotionEffectType.JUMP, 1000, 4))
    }
}