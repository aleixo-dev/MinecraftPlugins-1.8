package br.com.nicolas.minecraftplugin.listeners.menu

import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack

class GUIMoveItem : Listener {

    @EventHandler
    fun onClickEvent(inventoryClickEvent: InventoryClickEvent) {
        if (inventoryClickEvent.view.title.equals("${ChatColor.AQUA}Custom GUI")) {

            val player = inventoryClickEvent.whoClicked as Player

            when (inventoryClickEvent.currentItem.type) {

                Material.TNT -> {
                    player.apply {
                        closeInventory()
                        health = 0.0
                        sendMessage("Omg!!")
                    }
                }

                Material.BREAD -> {
                    player.apply {
                        closeInventory()
                        foodLevel = 20
                        sendMessage("Delicious!")
                    }
                }

                Material.DIAMOND_SWORD -> {
                    player.apply {
                        closeInventory()
                        inventory.addItem(ItemStack(Material.DIAMOND_SWORD))
                        sendMessage("Escalibur!")
                    }
                }

                else -> return
            }
        }
    }
}