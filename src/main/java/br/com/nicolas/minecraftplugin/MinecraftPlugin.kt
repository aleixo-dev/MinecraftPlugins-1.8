package br.com.nicolas.minecraftplugin

import br.com.nicolas.minecraftplugin.automessage.AutoMessageTask
import br.com.nicolas.minecraftplugin.commands.*
import br.com.nicolas.minecraftplugin.commands.cooldowns.EffectCommand
import br.com.nicolas.minecraftplugin.commands.custom_event.GameEndCommand
import br.com.nicolas.minecraftplugin.commands.holograms.HologramsCommand
import br.com.nicolas.minecraftplugin.commands.menu.GuiCommand
import br.com.nicolas.minecraftplugin.commands.menu.MenuCommand
import br.com.nicolas.minecraftplugin.commands.player.*
import br.com.nicolas.minecraftplugin.commands.set_files.SetMessageCommand
import br.com.nicolas.minecraftplugin.commands.spawn.SetSpawnCommand
import br.com.nicolas.minecraftplugin.commands.spawn.SpawnCommand
import br.com.nicolas.minecraftplugin.commands.target.PotionPlayerCommand
import br.com.nicolas.minecraftplugin.listeners.player.PlayerJoinListener
import br.com.nicolas.minecraftplugin.extensions.registerEvent
import br.com.nicolas.minecraftplugin.files.CustomConfig
import br.com.nicolas.minecraftplugin.listeners.GameListener
import br.com.nicolas.minecraftplugin.listeners.player.PlayerBedEnterListener
import br.com.nicolas.minecraftplugin.listeners.ShearSheepListener
import br.com.nicolas.minecraftplugin.listeners.SpawnListeners
import br.com.nicolas.minecraftplugin.listeners.menu.GUIMoveItem
import br.com.nicolas.minecraftplugin.listeners.menu.MenuListener
import br.com.nicolas.minecraftplugin.listeners.weather.GoodWeatherListener
import br.com.nicolas.minecraftplugin.tasks.KeepDayTask
import br.com.nicolas.minecraftplugin.vanish.command.VanishCommand
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class MinecraftPlugin : JavaPlugin() {

    val invisibleList = ArrayList<Player>()

    override fun onLoad() {
        logger.info("plugin loading..")
    }

    override fun onEnable() {

        Bukkit.getConsoleSender().sendMessage("§aPlugin is active!")

        /* setup config.yml */
        saveDefaultConfig()

        /* setup customfile.yml */
        CustomConfig.apply {
            setup()
            fileConfiguration.addDefault("message", "This is a test message")
            fileConfiguration.options().copyDefaults(true)
            save()
        }

        setupCommands()
        setupEvents()

        /* SCHEDULERS / TASKS */
        KeepDayTask(this).runTaskTimer(this, 0, 500L)
    }

    private fun setupCommands() {
        getCommand("kit").executor = KitCommand()
        getCommand("speed").executor = SpeedCommand()
        getCommand("food").executor = FoodCommand(this)
        getCommand("repeat").executor = RepeatCommand()
        getCommand("potion").executor = PotionPlayerCommand()
        getCommand("setspawn").executor = SetSpawnCommand(this)
        getCommand("spawn").executor = SpawnCommand(this)
        getCommand("setmessage").executor = SetMessageCommand(this)
        getCommand("menu").executor = MenuCommand()
        getCommand("setEffect").executor = EffectCommand()
        getCommand("gui").executor = GuiCommand()
        getCommand("hologram").executor = HologramsCommand()
        getCommand("message").executor = MessagePlayer()
        getCommand("prereload").executor = PreReload()
        getCommand("endgame").executor = GameEndCommand()

        /* VANISH */
        getCommand("vanish").executor = VanishCommand(this)

    }

    private fun setupEvents() {
        registerEvent(PlayerJoinListener(this), this)
        registerEvent(ShearSheepListener(), this)
        registerEvent(PlayerBedEnterListener(), this)
        registerEvent(BlockBreakEvent(), this)
        registerEvent(SpawnListeners(this), this)
        registerEvent(MenuListener(), this)
        registerEvent(GUIMoveItem(), this)
        registerEvent(GameListener(), this)
        registerEvent(GoodWeatherListener(), this)
    }


    override fun onDisable() {}
}