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
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

class GuiCommand : CommandExecutor {

    override fun onCommand(
        sender: CommandSender?,
        command: Command?,
        label: String?,
        args: Array<out String>?
    ): Boolean {

        if (sender is Player) {

            val player: Player = sender

            val gui = Bukkit.createInventory(player, 9, "${ChatColor.AQUA}Custom GUI")

            val suicide = item(Material.TNT) {
                meta<ItemMeta> {
                    displayName = "${ChatColor.RED}Suicide"
                }
            }
            val feed = item(Material.BREAD) {
                meta<ItemMeta> {
                    displayName = "${ChatColor.DARK_GREEN} Feed"
                }
            }
            val sword = item(Material.DIAMOND_SWORD) {
                meta<ItemMeta> {
                    displayName = "${ChatColor.LIGHT_PURPLE}Sword"
                }
            }

            val items: ArrayList<ItemStack> = arrayListOf(suicide, feed, sword)

            gui.contents = items.toTypedArray()
            player.openInventory(gui)

            return true
        }

        return true
    }
}