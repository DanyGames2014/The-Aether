package com.gildedgames.aether.item.tool;

import com.gildedgames.aether.registry.AetherItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.modificationstation.stationapi.api.template.item.TemplatePickaxeItem;
import net.modificationstation.stationapi.api.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class ItemHolystonePickaxe extends TemplatePickaxeItem
{

    public ItemHolystonePickaxe(final @NotNull Identifier identifier, final ToolMaterial enumtoolmaterial)
    {
        super(identifier, enumtoolmaterial);
    }

    @Override
    public boolean postMine(final ItemStack itemstack, final int i, final int j, final int k, final int l, final LivingEntity damageTarget)
    {
        if (random.nextInt(50) == 0)
        {
            damageTarget.method_1325(AetherItems.AmbrosiumShard.id, 1, 0.0f);
        }
        return super.postMine(itemstack, i, j, k, l, damageTarget);
    }
}
