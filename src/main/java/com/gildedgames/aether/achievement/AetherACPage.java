package com.gildedgames.aether.achievement;

import com.gildedgames.aether.AetherMod;
import com.gildedgames.aether.event.listener.TextureListener;
import net.minecraft.block.Block;
import net.modificationstation.stationapi.api.client.gui.screen.achievement.AchievementPage;

import java.util.Random;

public class AetherACPage extends AchievementPage
{
    public AetherACPage()
    {
        super(AetherMod.MODID, "Aether");
    }

    @Override
    public int getBackgroundTexture(final Random random, final int i, final int j, int randomizedRow, int currentTexture)
    {
        int k = Block.SAND.textureId;
        final int l = randomizedRow;
        if (l > 37 || j == 35)
        {
            k = TextureListener.sprAercloud;
            //k = AetherBlocks.AERCLOUD.texture;
        }
        else if (l == 22)
        {
            k = ((random.nextInt(2) != 0) ? TextureListener.sprMossyHolystone : TextureListener.sprGravititeOre);
        }
        else if (l == 10)
        {
            k = TextureListener.sprZaniteOre;
        }
        else if (l == 8)
        {
            k = TextureListener.sprAmbrosiumOre;
        }
        else if (l > 4)
        {
            k = TextureListener.sprHolystone;
        }
        else if (l > 0)
        {
            k = TextureListener.sprDirt;
        }
        return k; //TODO: texture
        //currentTexture = BlockBase.WOOL.texture;
        //return BlockBase.WOOL.getTextureForSide(0);
    }
}
