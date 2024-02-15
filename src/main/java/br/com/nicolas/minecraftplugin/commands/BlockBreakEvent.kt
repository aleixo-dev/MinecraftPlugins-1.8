package br.com.nicolas.minecraftplugin.commands

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent

class BlockBreakEvent : Listener {

    @EventHandler
    fun onBlockBreak(blockBreakEvent: BlockBreakEvent) {

        val player = blockBreakEvent.player
        if (!player.hasPermission("permissions.blockbreak")) {
            blockBreakEvent.isCancelled = true
        }
    }
}