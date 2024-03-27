package com.gildedgames.aether.item.tool;

import com.gildedgames.aether.entity.projectile.EntityFlamingArrow;
import com.gildedgames.aether.item.misc.ItemAether;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class ItemPhoenixBow extends ItemAether
{
    public ItemPhoenixBow(final @NotNull Identifier identifier)
    {
        super(identifier);
        this.maxCount = 1;
    }

    @Override
    public ItemStack use(final ItemStack item, final World level, final PlayerEntity player)
    {
        if (player.inventory.method_676(Item.ARROW.id))
        {
            level.playSound((Entity) player, "mob.ghast.fireball", 1.0f, 1.0f / (ItemPhoenixBow.random.nextFloat() * 0.4f + 0.8f));
            if (!level.isRemote)
            {
                level.method_210(new EntityFlamingArrow(level, player));
            }
        }
        return item;
    }
}
