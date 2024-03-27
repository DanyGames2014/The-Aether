package com.gildedgames.aether.gui;

import com.gildedgames.aether.entity.tile.TileEntityEnchanter;
import com.gildedgames.aether.gui.container.ContainerEnchanter;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import org.lwjgl.opengl.GL11;

public class GuiEnchanter extends HandledScreen
{
    private TileEntityEnchanter enchanterInventory;

    public GuiEnchanter(final PlayerInventory inventoryplayer, final TileEntityEnchanter tileentityEnchanter)
    {
        super(new ContainerEnchanter(inventoryplayer, tileentityEnchanter));
        this.enchanterInventory = tileentityEnchanter;
    }

    @Override
    protected void drawForeground()
    {
        this.textRenderer.draw("Enchanter", 60, 6, 4210752);
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
        if (this.enchanterInventory.isBurning())
        {
            final int l = this.enchanterInventory.getBurnTimeRemainingScaled(12);
            this.drawTexture(j + 57, k + 47 - l, 176, 12 - l, 14, l + 2);
        }
        final int i2 = this.enchanterInventory.getCookProgressScaled(24);
        this.drawTexture(j + 79, k + 35, 176, 14, i2 + 1, 16);
    }
}
