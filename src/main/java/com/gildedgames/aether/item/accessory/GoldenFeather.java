package com.gildedgames.aether.item.accessory;

import com.gildedgames.aether.mixin.access.EntityBaseAccessor;
import com.matthewperiut.accessoryapi.api.Accessory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.template.item.TemplateItem;
import net.modificationstation.stationapi.api.util.Identifier;

public class GoldenFeather extends TemplateItem implements Accessory
{
    public GoldenFeather(Identifier identifier)
    {
        super(identifier);
        this.setMaxCount(1);
        this.setMaxDamage(500);
    }

    @Override
    public String[] getAccessoryTypes(ItemStack item)
    {
        return new String[]{"misc"};
    }

    public static void slowFall(PlayerEntity player)
    {
        if (player.velocityY < 0)
        {
            if (player.method_1373()) // isSneaking
                player.velocityY *= 0.9;
            else player.velocityY *= 0.6;
        }

        ((EntityBaseAccessor) (player)).setFallDistance(0.0f);

    }

    @Override
    public ItemStack tickWhileWorn(PlayerEntity playerBase, ItemStack itemInstance)
    {
        slowFall(playerBase);
        return itemInstance;
    }
}
