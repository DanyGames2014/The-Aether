package com.gildedgames.aether.item.tool;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.hit.HitResultType;
import net.modificationstation.stationapi.api.item.CustomReachProvider;
import net.modificationstation.stationapi.api.template.item.TemplateAxeItem;
import net.modificationstation.stationapi.api.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class ItemValkyrieAxe extends TemplateAxeItem implements CustomReachProvider
{
    public ItemValkyrieAxe(final @NotNull Identifier identifier, final ToolMaterial enumtoolmaterial)
    {
        super(identifier, enumtoolmaterial);
    }

    @Override
    public double getReach(ItemStack itemInstance, PlayerEntity player, HitResultType type, double currentReach)
    {
        return 10.f;
    }
}
