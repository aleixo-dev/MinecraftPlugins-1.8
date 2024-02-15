package br.com.nicolas.minecraftplugin.extensions

import org.bukkit.event.Listener
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin

fun JavaPlugin.registerEvent(events : Listener, plugin : Plugin) {
    server.pluginManager.registerEvents(events, plugin)
}