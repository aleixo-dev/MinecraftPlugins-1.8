package br.com.nicolas.minecraftplugin.listeners

import org.bukkit.entity.EntityType
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerShearEntityEvent

/** This is a Cancellable Event. */

class ShearSheepListener : Listener {

    @EventHandler
    fun onShearSheep(shearSheepListener: PlayerShearEntityEvent) {

        val player = shearSheepListener.player
        val entity = shearSheepListener.entity

        if (entity.type == EntityType.SHEEP) {
            player.sendMessage("This is a sheep. you can't do that.")
            shearSheepListener.isCancelled = true
        } else {
            player.sendMessage("This isn't a sheep.")
        }
    }
}