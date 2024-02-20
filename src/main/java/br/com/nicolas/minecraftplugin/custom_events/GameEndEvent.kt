package br.com.nicolas.minecraftplugin.custom_events

import org.bukkit.entity.Player
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

class GameEndEvent(
    val playerOne: Player,
    val playerTwo: Player,
    val score: Int = 0
) : Event() {

    companion object {

        private val HANDLER_LIST = HandlerList()

        @JvmStatic
        fun getHandlerList(): HandlerList {
            return HANDLER_LIST
        }

    }

    override fun getHandlers(): HandlerList {
        return HANDLER_LIST
    }
}