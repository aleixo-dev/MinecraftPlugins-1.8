package br.com.nicolas.minecraftplugin.commands

import br.com.nicolas.minecraftplugin.utils.item
import br.com.nicolas.minecraftplugin.utils.meta
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.inventory.meta.EnchantmentStorageMeta
import org.bukkit.inventory.meta.ItemMeta

class KitCommand : CommandExecutor {

    override fun onCommand(
        commandSender: CommandSender?, command: Command?, label: String?, args: Array<out String>?
    ): Boolean {

        if (commandSender !is Player) {
            commandSender?.sendMessage("Only player can do this!")
            return true
        }

        val player: Player = commandSender

        if (args?.size == 1) {
            if (args[0].equals("common", ignoreCase = true)) {

                val stringBuilder = StringBuilder()
                    .appendLine("Diamond block of the god!")
                    .append("Be careful! it's more important!")

                player.inventory.addItem(
                    item(Material.DIAMOND_BLOCK) {
                        amount = 1
                        meta<ItemMeta> {
                            displayName = "God's Block"
                            lore = stringBuilder.lines()
                        }
                    }
                )
                player.sendMessage("§e[§1!§e]§e Wooow! you received a kit common!")
            }

            if (args[0].equals("vip", ignoreCase = true)) {

                val stringBuilder = StringBuilder()
                    .appendLine("God's Sword")
                    .append("It's so powerful!")

                player.inventory.addItem(item(Material.DIAMOND_SWORD) {
                    amount = 1
                    meta<ItemMeta> {
                        displayName = "Vip Sword"
                        lore = stringBuilder.lines()
                    }
                    addEnchantment(Enchantment.DAMAGE_ALL, 5)
                })
                player.sendMessage("§e[§1!§e]§e Wooow! you received a kit vip!")
            }
        } else {
            player.sendMessage("§e[§1!§e]§2 Oops! use /kit <name>")
        }

        return true
    }
}