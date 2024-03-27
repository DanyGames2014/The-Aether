package com.gildedgames.aether.item.misc;

import com.gildedgames.aether.AetherMod;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.item.TemplateItem;
import net.modificationstation.stationapi.api.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class ItemLifeShard extends TemplateItem
{
    public ItemLifeShard(final @NotNull Identifier identifier)
    {
        super(identifier);
        this.maxCount = 1;
    }

    @Override
    public ItemStack use(final ItemStack item, final World level, final PlayerEntity player)
    {
        --item.count;
        AetherMod.getPlayerHandler(player).increaseMaxHP(2);
        return item;
    }
}
