package com.gildedgames.aether.entity.tile;

import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.nbt.NbtCompound;

public class TileEntityTreasureChest extends ChestBlockEntity
{
    public int rarity = 0;

    public void setRarity(int r)
    {
        rarity = r;
    }

    public void readNbt(NbtCompound arg)
    {
        super.readNbt(arg);
        rarity = arg.getInt("rarity");
    }

    public void writeNbt(NbtCompound arg)
    {
        super.writeNbt(arg);
        arg.putInt("rarity", rarity);
    }
}
