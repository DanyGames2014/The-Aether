package com.gildedgames.aether.item.accessory;

import com.matthewperiut.accessoryapi.api.Accessory;
import com.matthewperiut.accessoryapi.api.render.AccessoryRenderer;
import com.matthewperiut.accessoryapi.api.render.HasCustomRenderer;
import com.matthewperiut.accessoryapi.api.render.builtin.CapeRenderer;
import com.matthewperiut.accessoryapi.api.render.builtin.ConfigurableRenderer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateItem;

import java.awt.*;
import java.util.Optional;

public class CosmeticCape extends TemplateItem implements Accessory, HasCustomRenderer
{
    public String texture;
    public int color;

    private ConfigurableRenderer renderer;

    public CosmeticCape(Identifier identifier, String texture, int color)
    {
        super(identifier);
        this.texture = texture;
        this.color = color;
        this.setMaxCount(1);
        this.setMaxDamage(500);
    }

    public CosmeticCape(Identifier identifier, String texture)
    {
        this(identifier, texture, 16777215);
    }

    @Environment(EnvType.CLIENT)
    public int getColourMultiplier(int i)
    {
        return color;
    }

    @Override
    public String[] getAccessoryTypes(ItemStack item)
    {
        return new String[]{"cape"};
    }

    @Override
    public Optional<AccessoryRenderer> getRenderer()
    {
        return Optional.ofNullable(renderer);
    }

    @Override
    public void constructRenderer()
    {
        renderer = new CapeRenderer(texture).withColor(new Color(color));
    }
}
