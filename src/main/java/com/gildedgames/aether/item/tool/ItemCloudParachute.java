package com.gildedgames.aether.item.tool;

import com.gildedgames.aether.entity.EntityCloudParachute;
import com.gildedgames.aether.registry.AetherItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.item.TemplateItem;
import net.modificationstation.stationapi.api.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class ItemCloudParachute extends TemplateItem
{

    public ItemCloudParachute(final @NotNull Identifier identifier, boolean isGolden)
    {
        super(identifier);
        this.maxCount = 1;
        this.setMaxDamage(isGolden ? 20 : 0);
    }

    @Override
    public ItemStack use(final ItemStack item, final World level, final PlayerEntity player)
    {
        if (EntityCloudParachute.entityHasRoomForCloud(level, player))
        {
            for (int i = 0; i < 32; ++i)
            {
                EntityCloudParachute.doCloudSmoke(level, player);
            }
            if (this.id == AetherItems.CloudParachuteGold.id)
            {
                item.damage(1, player);
            }
            else
            {
                --item.count;
            }
            level.playSound((Entity) player, "cloud", 1.0f, 1.0f / (ItemCloudParachute.random.nextFloat() * 0.1f + 0.95f));
            if (!level.isRemote)
            {
                level.method_210(new EntityCloudParachute(level, player, this.id == AetherItems.CloudParachuteGold.id));
            }
        }
        return item;
    }

    @Override
    public int method_440(final int i)
    {
        if (this.id == AetherItems.CloudParachuteGold.id)
        {
            return 16777087;
        }
        return 16777215;
    }

    static
    {
    }
}
