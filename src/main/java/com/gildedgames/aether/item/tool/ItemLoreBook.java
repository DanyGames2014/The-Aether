package com.gildedgames.aether.item.tool;

import com.gildedgames.aether.gui.GuiLore;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.item.TemplateItem;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.SideUtil;
import org.jetbrains.annotations.NotNull;

public class ItemLoreBook extends TemplateItem
{
    public ItemLoreBook(final @NotNull Identifier identifier)
    {
        super(identifier);
        this.maxCount = 1;
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
    }

    @Override
    public int method_440(final int i)
    {
        if (i == 0)
        {
            return 8388479;
        }
        if (i == 1)
        {
            return 16744319;
        }
        return 8355839;
    }

    @Override
    public ItemStack use(final ItemStack item, final World level, final PlayerEntity player)
    {
        SideUtil.run(() -> useLoreClient(player, item), () -> useLoreServer());
        return item;
    }

    @Environment(EnvType.SERVER)
    private static void useLoreServer() {

    }

    @Environment(EnvType.CLIENT)
    private static void useLoreClient(final PlayerEntity player, final ItemStack item) {
        //noinspection deprecation
        if (FabricLoader.getInstance().getGameInstance() instanceof Minecraft mc)
            mc.setScreen(new GuiLore(player.inventory, item.getDamage()));
    }

    @Override
    public String getTranslationKey(final ItemStack item)
    {
        int i = item.getDamage();
        if (i > 2)
        {
            i = 2;
        }
        return super.getTranslationKey() + "." + i;
    }
}
