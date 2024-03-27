package com.gildedgames.aether.item.tool;

import com.gildedgames.aether.entity.mobs.EntityMiniCloud;
import com.gildedgames.aether.item.misc.ItemAether;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ItemCloudStaff extends ItemAether
{
    public ItemCloudStaff(final @NotNull Identifier identifier)
    {
        super(identifier);
        this.maxCount = 1;
        this.setMaxDamage(60);
    }

    @Override
    public ItemStack use(final ItemStack item, final World level, final PlayerEntity player)
    {
        if (!this.cloudsExist(level, player))
        {
            final EntityMiniCloud c1 = new EntityMiniCloud(level, player, false);
            final EntityMiniCloud c2 = new EntityMiniCloud(level, player, true);
            level.method_210(c1);
            level.method_210(c2);
            item.damage(1, null);
        }
        return item;
    }

    private boolean cloudsExist(final World world, final PlayerEntity entityplayer)
    {
        final List<Entity> list = world.getEntities(entityplayer, entityplayer.boundingBox.expand(128.0, 128.0, 128.0));
        for (int j = 0; j < list.size(); ++j)
        {
            final Entity entity1 = (Entity) list.get(j);
            if (entity1 instanceof EntityMiniCloud)
            {
                return true;
            }
        }
        return false;
    }
}
