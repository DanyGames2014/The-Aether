package com.gildedgames.aether.item.tool;

import net.minecraft.block.Block;
import net.minecraft.block.JukeboxBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.item.TemplateMusicDiscItem;
import net.modificationstation.stationapi.api.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class ItemAetherRecord extends TemplateMusicDiscItem
{
    public ItemAetherRecord(final @NotNull Identifier identifier, final String title)
    {
        super(identifier, title);
    }

    @Override
    public boolean useOnBlock(final ItemStack item, final PlayerEntity player, final World level, final int x, final int y, final int z, final int facing)
    {
        if (level.getBlockId(x, y, z) != Block.JUKEBOX.id || level.getBlockMeta(x, y, z) != 0)
        {
            return false;
        }
        if (level.isRemote)
        {
            return true;
        }
        ((JukeboxBlock) Block.JUKEBOX).method_349(level, x, y, z, this.id);
        level.method_173(null, 1005, x, y, z, this.id);
        // todo: jukebox MinecraftClientAccessor.getMCinstance().overlay.showJukeboxMessage("Noisestorm - " + this.title);
        --item.count;
        return true;
    }
}
