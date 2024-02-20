package br.com.nicolas.minecraftplugin.listeners.weather

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.weather.WeatherChangeEvent

class GoodWeatherListener : Listener {

    @EventHandler
    fun onWeatherChanger(weatherChangeEvent: WeatherChangeEvent) {
        weatherChangeEvent.isCancelled = true
    }
}