package com.gildedgames.aether.item.accessory;

import com.matthewperiut.accessoryapi.api.Accessory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.template.item.TemplateItem;
import net.modificationstation.stationapi.api.util.Identifier;

public class IronBubble extends TemplateItem implements Accessory
{
    public IronBubble(Identifier identifier)
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

    @Override
    public ItemStack tickWhileWorn(PlayerEntity playerBase, ItemStack itemInstance)
    {
        playerBase.air = 20;
        return itemInstance;
    }
}
