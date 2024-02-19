package br.com.nicolas.minecraftplugin.files

import org.bukkit.Bukkit
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File
import java.io.IOException

object CustomConfig {

    private lateinit var file: File
    lateinit var fileConfiguration: FileConfiguration

    // finds or generate the custom config file .yml
    fun setup() {
        file = File(
            Bukkit.getServer().pluginManager.getPlugin("MinecraftPlugin").dataFolder,
            "customconfig.yml"
        )

        if (!file.exists()) {
            try {
                file.createNewFile()
            } catch (exception: IOException) {
                println("ooops!")
            }
        }
        fileConfiguration = YamlConfiguration.loadConfiguration(file)
    }

    fun save() {
        try {
            fileConfiguration.save(file)
        }catch (exception : IOException) {
            println("Couldn't save file.")
        }
    }

    fun reload() {
        fileConfiguration = YamlConfiguration.loadConfiguration(file)
    }
}