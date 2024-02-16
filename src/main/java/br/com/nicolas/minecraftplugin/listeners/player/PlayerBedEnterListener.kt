package br.com.nicolas.minecraftplugin.listeners.player

import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerBedEnterEvent
import org.bukkit.inventory.ItemStack

/**This is a Cancellable Event*/

class PlayerBedEnterListener : Listener {

    @EventHandler
    fun onJoinBed(playerBedEnterListener: PlayerBedEnterEvent) {

        with(playerBedEnterListener.player) {
            sendMessage("Good Night!")
            inventory.addItem(fetchDiamondBlock())
        }

    }

    private fun fetchDiamondBlock(): ItemStack {
        val itemStack = ItemStack(Material.DIAMOND_BLOCK, 1)
        itemStack.itemMeta = itemStack.itemMeta.apply {
            displayName = "this block is powerful!"
        }
        return itemStack
    }
}