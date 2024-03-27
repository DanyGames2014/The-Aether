package com.gildedgames.aether.item.accessory;

import com.gildedgames.aether.player.AetherPlayerHandler;
import com.matthewperiut.accessoryapi.api.Accessory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.template.item.TemplateItem;
import net.modificationstation.stationapi.api.util.Identifier;

public class InvisibilityCloak extends TemplateItem implements Accessory
{
    public InvisibilityCloak(Identifier identifier)
    {
        super(identifier);
        setMaxCount(1);
        setMaxDamage(300);
    }

    @Override
    public String[] getAccessoryTypes(ItemStack item)
    {
        return new String[]{"cape"};
    }

    @Override
    public void onAccessoryAdded(PlayerEntity playerBase, ItemStack itemInstance)
    {
        AetherPlayerHandler.get(playerBase).visible = false;
    }

    @Override
    public void onAccessoryRemoved(PlayerEntity playerBase, ItemStack itemInstance)
    {
        AetherPlayerHandler.get(playerBase).visible = true;
    }
}
