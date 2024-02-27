package br.com.nicolas.minecraftplugin.tasks

import br.com.nicolas.minecraftplugin.MinecraftPlugin
import org.bukkit.Bukkit
import org.bukkit.scheduler.BukkitRunnable

class KeepDayTask(
    private val plugin: MinecraftPlugin
) : BukkitRunnable() {

    override fun run() {
        val world = plugin.config.getString("lobby-world")
        if (Bukkit.getServer().getWorld(world) != null) {
            Bukkit.getServer().getWorld(world).time = 2000L
        } else {
            println("The world currently set as lobby does not exists.")
        }
    }
}