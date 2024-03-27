package com.gildedgames.aether.gui;

import com.gildedgames.aether.entity.tile.TileEntityFreezer;
import com.gildedgames.aether.gui.container.ContainerFreezer;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import org.lwjgl.opengl.GL11;

public class GuiFreezer extends HandledScreen
{
    private TileEntityFreezer freezerInventory;

    public GuiFreezer(final PlayerInventory inventoryplayer, final TileEntityFreezer tileentityFreezer)
    {
        super(new ContainerFreezer(inventoryplayer, tileentityFreezer));
        this.freezerInventory = tileentityFreezer;
    }

    @Override
    protected void drawForeground()
    {
        this.textRenderer.draw("Freezer", 60, 6, 4210752);
        this.textRenderer.draw("Inventory", 8, this.backgroundHeight - 96 + 2, 4210752);
    }

    @Override
    protected void drawBackground(final float tickDelta)
    {
        final int i = this.minecraft.textureManager.getTextureId("/assets/aether/textures/gui/enchanter.png");
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.minecraft.textureManager.bindTexture(i);
        final int j = (this.width - this.backgroundWidth) / 2;
        final int k = (this.height - this.backgroundHeight) / 2;
        this.drawTexture(j, k, 0, 0, this.backgroundWidth, this.backgroundHeight);
        if (this.freezerInventory.isBurning())
        {
            final int l = this.freezerInventory.getBurnTimeRemainingScaled(12);
            this.drawTexture(j + 57, k + 47 - l, 176, 12 - l, 14, l + 2);
        }
        final int i2 = this.freezerInventory.getCookProgressScaled(24);
        this.drawTexture(j + 79, k + 35, 176, 14, i2 + 1, 16);
    }
}
