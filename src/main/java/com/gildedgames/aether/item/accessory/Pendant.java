package com.gildedgames.aether.item.accessory;

import com.matthewperiut.accessoryapi.api.Accessory;
import com.matthewperiut.accessoryapi.api.render.AccessoryRenderer;
import com.matthewperiut.accessoryapi.api.render.HasCustomRenderer;
import com.matthewperiut.accessoryapi.api.render.builtin.NecklaceRenderer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.template.item.TemplateItem;
import net.modificationstation.stationapi.api.util.Identifier;

import java.awt.*;
import java.util.Optional;

public class Pendant extends TemplateItem implements Accessory, HasCustomRenderer
{
    String texture;
    int colour;

    public Pendant(Identifier identifier, String texture, int colour)
    {
        super(identifier);
        setMaxCount(1);
        setMaxDamage(500);
        this.colour = colour;
        this.texture = texture;
    }

    public Pendant(Identifier identifier, String texture)
    {
        this(identifier, texture, 16777215);
    }

    @Environment(EnvType.CLIENT)
    public int getColourMultiplier(int i)
    {
        return colour;
    }

    @Override
    public String[] getAccessoryTypes(ItemStack item)
    {
        return new String[]{"pendant"};
    }

    AccessoryRenderer renderer;

    @Override
    public Optional<AccessoryRenderer> getRenderer()
    {
        return Optional.ofNullable(renderer);
    }

    @Override
    public void constructRenderer()
    {
        renderer = new NecklaceRenderer(texture).withColor(new Color(colour));
    }
}
