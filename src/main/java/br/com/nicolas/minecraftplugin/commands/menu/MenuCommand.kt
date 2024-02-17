package br.com.nicolas.minecraftplugin.commands.menu

import br.com.nicolas.minecraftplugin.utils.item
import br.com.nicolas.minecraftplugin.utils.meta
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.meta.ItemMeta

class MenuCommand : CommandExecutor {
    override fun onCommand(
        sender: CommandSender?,
        command: Command?,
        label: String?,
        args: Array<out String>?
    ): Boolean {

        if (sender !is Player) return true
        val player: Player = sender

        /* [9, 18, 27, 36, 45, 54] */

        val inventory = Bukkit.createInventory(player, 9, "Red Team")

        item(Material.DIAMOND_HOE) {
            amount = 1
            meta<ItemMeta> {
                displayName = "${ChatColor.AQUA}Click me."
            }
            inventory.setItem(0, this)
        }

        item(Material.BREAD) {
            amount = 1
            meta<ItemMeta> {
                displayName = "${ChatColor.GOLD}God's Bread"
                lore = StringBuilder()
                    .appendLine("Lorem Ipsum is simply dummy")
                    .appendLine("Dummy text ever since the 1500s")
                    .lines()
            }
            inventory.setItem(4, this)
        }

        player.openInventory(inventory)

        return true
    }
}