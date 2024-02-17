package br.com.nicolas.minecraftplugin.listeners.menu

import br.com.nicolas.minecraftplugin.utils.item
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack

class MenuListener : Listener {

    @EventHandler
    fun onMenuClick(inventoryClickEvent: InventoryClickEvent) {

        if (inventoryClickEvent.view.title.equals("Red Team")) {

            if (inventoryClickEvent.currentItem == null) return

            when (inventoryClickEvent.currentItem.type) {
                Material.BREAD -> {
                    val player = inventoryClickEvent.whoClicked as Player
                    player.sendMessage("${ChatColor.AQUA}You received a delicious bread!")
                    item(Material.BREAD) {
                        amount = 1
                        itemMeta = inventoryClickEvent.currentItem.itemMeta
                        player.inventory.addItem(this)
                    }
                    inventoryClickEvent.view.close()
                }

                Material.DIAMOND_HOE -> {
                    val player = inventoryClickEvent.whoClicked as Player
                    player.sendMessage("${ChatColor.AQUA}Go to work! you have a diamond hoe!")
                    player.inventory.addItem(ItemStack(Material.DIAMOND_HOE, 1))
                    inventoryClickEvent.view.close()
                }

                else -> return
            }
            inventoryClickEvent.isCancelled = true
        }
    }
}