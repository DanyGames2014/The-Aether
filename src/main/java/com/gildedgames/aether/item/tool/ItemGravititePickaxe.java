package com.gildedgames.aether.item.tool;

import com.gildedgames.aether.entity.projectile.EntityFloatingBlock;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.tag.TagKey;
import net.modificationstation.stationapi.api.template.item.TemplatePickaxeItem;
import net.modificationstation.stationapi.api.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class ItemGravititePickaxe extends TemplatePickaxeItem
{
    public ItemGravititePickaxe(final @NotNull Identifier identifier, final ToolMaterial enumtoolmaterial)
    {
        super(identifier, enumtoolmaterial);
    }

    @Override
    public boolean useOnBlock(ItemStack item, PlayerEntity player, World level, int x, int y, int z, int idk)
    {
        if (!level.isRemote)
        {
            BlockState b = level.getBlockState(x, y, z);

            TagKey<Block> tag = this.getEffectiveBlocks(item);
            if (b.isIn(tag))
            {
                final int blockID = level.getBlockId(x, y, z);
                final int metadata = level.getBlockMeta(x, y, z);
                final EntityFloatingBlock floating = new EntityFloatingBlock(level, x + 0.5f, y + 0.5f, z + 0.5f, blockID, metadata);
                level.method_210(floating);
                item.damage(1, player);
            }

            return super.useOnBlock(item, player, level, x, y, z, idk);
        }
        return false;
    }
}
