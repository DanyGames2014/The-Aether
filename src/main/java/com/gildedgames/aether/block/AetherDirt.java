package com.gildedgames.aether.block;

import net.minecraft.block.material.Material;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.block.TemplateBlockBase;

public class AetherDirt extends TemplateBlockBase {

    public AetherDirt(Identifier identifier) {
        super(identifier, Material.DIRT);
    }

//    public void onBlockPlaced(World world, int i, int j, int k, int l)
//    {
//        world.setBlockWithNotify(i, j, k, blockID);
//        world.setBlockMetadataWithNotify(i, j, k, 1);
//    }
//
//    public void harvestBlock(World world, EntityPlayer entityplayer, int i, int j, int k, int l)
//    {
//        entityplayer.addStat(StatList.mineBlockStatArray[blockID], 1);
//        if(l == 0 && mod_Aether.equippedSkyrootShovel())
//        {
//            dropBlockAsItem(world, i, j, k, l);
//        }
//        dropBlockAsItem(world, i, j, k, l);
//    }
}
