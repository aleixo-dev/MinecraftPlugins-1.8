package br.com.nicolas.minecraftplugin.custom_plugin_reward

import br.com.nicolas.minecraftplugin.MinecraftPlugin
import org.bukkit.*
import org.bukkit.entity.Chicken
import org.bukkit.entity.EntityType
import org.bukkit.entity.Firework
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityDeathEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class BlockRewardListener(
    private val plugin: MinecraftPlugin
) : Listener {

    @EventHandler
    fun onBlockBreakEvent(blockBreakEvent: BlockBreakEvent) {

        val player = blockBreakEvent.player
        val block = blockBreakEvent.block

        if (block.type == Material.LAPIS_ORE && isPickaxe(player.inventory.itemInHand.type)) {
            if (isPercentage()) {

                block.world.createExplosion(block.location, 0f)

                player.sendMessage("${ChatColor.GOLD}Que sorte! você achou uma entidade chefe!")
                player.playSound(block.location, Sound.LEVEL_UP, 2.0f, 1.0f)
                player.inventory.addItem(ItemStack(Material.INK_SACK, 1, 4.toShort())
                )

                val playerLocation = Location(
                    player.location.world,
                    player.location.x,
                    player.location.y,
                    player.location.z + 1
                )

                val entitySpawn = block.location.world.spawnEntity(
                    playerLocation,
                    EntityType.CHICKEN
                ) as Chicken

                entitySpawn.maxHealth = 10.0
                entitySpawn.health = 10.0
                entitySpawn.isCustomNameVisible = true

                entitySpawn.customName =
                    "${ChatColor.GOLD}Entity Boss"
                entitySpawn.addPotionEffect(PotionEffect(PotionEffectType.SPEED, 1000, 4))

            }
        }
    }

    @EventHandler
    fun onEntityDeath(entityDeathEvent: EntityDeathEvent) {

        if (entityDeathEvent.entityType.entityClass == Chicken::class.java && entityDeathEvent.entity.customName == "${ChatColor.GOLD}Entity Boss") {
            if (entityDeathEvent.entity.killer != null) {
                giveRewardToPlayer(entityDeathEvent.entity.killer.player, ItemStack(Material.SOUL_SAND, 1))

                val firework =
                    entityDeathEvent.entity.world.spawn(entityDeathEvent.entity.location, Firework::class.java)

                val fireworkMeta = firework.fireworkMeta
                fireworkMeta.power = 1
                fireworkMeta.addEffects(
                    FireworkEffect.builder()
                        .with(FireworkEffect.Type.BURST)
                        .withColor(Color.OLIVE)
                        .withFlicker()
                        .with(FireworkEffect.Type.BALL_LARGE)
                        .withColor(Color.SILVER)
                        .with(FireworkEffect.Type.BALL_LARGE)
                        .withFade(Color.GREEN)
                        .withTrail()
                        .build()
                )
                firework.fireworkMeta = fireworkMeta
            }
        }
    }

    @EventHandler
    fun onEntityHit(entityDamageEvent: EntityDamageByEntityEvent) {

        if (entityDamageEvent.entityType.entityClass == Chicken::class.java && entityDamageEvent.entity.customName == "${ChatColor.GOLD}Entity Boss") {


            val chickenEntity = entityDamageEvent.entity as Chicken
            if (entityDamageEvent.damager !is Player) return

            val player = entityDamageEvent.damager as Player
            if (player.itemInHand.itemMeta == null) return

            val swordCustomNameInHand =
                player.inventory.itemInHand.itemMeta.displayName.replace("", "")

            val swordDisplayName = plugin.config.getString("material-killer-name")

            println(swordCustomNameInHand)
            println(swordDisplayName)

            if (swordCustomNameInHand.equals(plugin.config.getString("material-killer-name"))) {

                chickenEntity.health = 0.0
                player.sendMessage(" ")
                player.sendMessage("${ChatColor.LIGHT_PURPLE}Você acabou de matar uma entidade chefe!")
                player.sendMessage(" ")
            }
        }
    }

    private fun giveRewardToPlayer(player: Player, item: ItemStack) {
        player.inventory.addItem(item)
    }

    private fun isPercentage(): Boolean {

        val randomNumber = (0..99).random()
        return randomNumber < 30

    }

    private fun isPickaxe(material: Material): Boolean {
        return material.name.endsWith("_PICKAXE")
    }
}