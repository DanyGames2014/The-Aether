package com.gildedgames.aether.item.tool;

import com.gildedgames.aether.item.misc.ItemAether;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class ItemLightningKnife extends ItemAether
{
    public ItemLightningKnife(final @NotNull Identifier identifier)
    {
        super(identifier);
        this.maxCount = 16;
    }

    @Override
    public ItemStack use(final ItemStack item, final World level, final PlayerEntity player)
    {
        --item.count;
        level.playSound(player, "aether:aether.sound.other.dartshooter.shootdart", 2.0f, 1.0f / (ItemLightningKnife.random.nextFloat() * 0.4f + 0.8f));
        //if (!level.isServerSide) {
        //TODO knife level.spawnEntity(new EntityLightningKnife(level, player));
        //}
        return item;
    }
}
