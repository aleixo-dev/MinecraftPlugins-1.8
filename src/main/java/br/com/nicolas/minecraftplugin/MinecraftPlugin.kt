package br.com.nicolas.minecraftplugin

import br.com.nicolas.minecraftplugin.commands.*
import br.com.nicolas.minecraftplugin.commands.spawn.SetSpawnCommand
import br.com.nicolas.minecraftplugin.commands.spawn.SpawnCommand
import br.com.nicolas.minecraftplugin.listeners.PlayerJoinListener
import br.com.nicolas.minecraftplugin.extensions.registerEvent
import br.com.nicolas.minecraftplugin.listeners.PlayerBedEnterListener
import br.com.nicolas.minecraftplugin.listeners.ShearSheepListener
import br.com.nicolas.minecraftplugin.listeners.SpawnListeners
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class MinecraftPlugin : JavaPlugin() {

    override fun onLoad() {
        logger.info("plugin loading..")
    }

    override fun onEnable() {

        Bukkit.getConsoleSender().sendMessage("§aPlugin is active!")

        config.options().copyDefaults()
        saveDefaultConfig()

        setupCommands()
        setupEvents()

    }

    private fun setupCommands() {
        getCommand("kit").executor = KitCommand()
        getCommand("speed").executor = SpeedCommand()
        getCommand("food").executor = FoodCommand()
        getCommand("repeat").executor = RepeatCommand()
        getCommand("potion").executor = PotionPlayerCommand()
        getCommand("setspawn").executor = SetSpawnCommand(this)
        getCommand("spawn").executor = SpawnCommand(this)
    }

    private fun setupEvents() {
        registerEvent(PlayerJoinListener(), this)
        registerEvent(ShearSheepListener(), this)
        registerEvent(PlayerBedEnterListener(), this)
        registerEvent(BlockBreakEvent(), this)
        registerEvent(SpawnListeners(this), this)
    }


    override fun onDisable() {}
}