package br.com.nicolas.minecraftplugin.utils

import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

fun item(itemType: Material, init: ItemStack.() -> Unit): ItemStack {
    val itemStack = ItemStack(itemType)
    itemStack.init()
    return itemStack
}

inline fun <reified T : ItemMeta> ItemStack.meta(init: T.() -> Unit) {
    itemMeta = (itemMeta as T).apply(init)
}
