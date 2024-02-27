package br.com.nicolas.minecraftplugin.custom_plugin_reward

import br.com.nicolas.minecraftplugin.MinecraftPlugin
import br.com.nicolas.minecraftplugin.utils.item
import br.com.nicolas.minecraftplugin.utils.meta
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

class RewardCommand(
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

            val sword = item(Material.getMaterial(plugin.config.getString("material-killer"))) {
                amount = 1
                meta<ItemMeta> {
                    displayName =
                        ChatColor.translateAlternateColorCodes('&', plugin.config.getString("material-killer-name"))
                }
            }
            player.inventory.addItem(sword)
        }

        return true
    }
}