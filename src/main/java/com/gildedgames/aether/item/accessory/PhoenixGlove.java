package com.gildedgames.aether.item.accessory;

import com.gildedgames.aether.mixin.access.EntityBaseAccessor;
import com.gildedgames.aether.registry.AetherItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.util.Identifier;

public class PhoenixGlove extends Glove
{
    public PhoenixGlove(Identifier identifier, int strength, String texture, int color)
    {
        super(identifier, strength, texture, color);
    }

    @Override
    public ItemStack tickWhileWorn(PlayerEntity player, ItemStack itemInstance)
    {
        if (player.inventory.armor[3] != null && player.inventory.armor[3].itemId == AetherItems.PhoenixHelm.id && player.inventory.armor[2] != null && player.inventory.armor[2].itemId == AetherItems.PhoenixBody.id && player.inventory.armor[1] != null && player.inventory.armor[1].itemId == AetherItems.PhoenixLegs.id && player.inventory.armor[0] != null && player.inventory.armor[0].itemId == AetherItems.PhoenixBoots.id)
        {
            ((EntityBaseAccessor) player).setImmunityToFire(true);
            player.fire = 0;
            player.world.addParticle("flame", player.x + ((EntityBaseAccessor) player).getRand().nextGaussian() / 5.0, player.y - 0.5 + ((EntityBaseAccessor) player).getRand().nextGaussian() / 5.0, player.z + ((EntityBaseAccessor) player).getRand().nextGaussian() / 3.0, 0.0, 0.0, 0.0);
        }
        return itemInstance;
    }

    @Override
    public void onAccessoryRemoved(PlayerEntity player, ItemStack itemInstance)
    {
        ((EntityBaseAccessor) player).setImmunityToFire(false);
    }
}
