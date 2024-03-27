package com.gildedgames.aether.item.tool;

import com.gildedgames.aether.mixin.access.EntityBaseAccessor;
import com.gildedgames.aether.mixin.access.LivingAccessor;
import net.minecraft.entity.EntityRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.modificationstation.stationapi.api.template.item.TemplateSwordItem;
import net.modificationstation.stationapi.api.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class ItemPigSlayer extends TemplateSwordItem
{
    public ItemPigSlayer(final @NotNull Identifier identifier)
    {
        // field_1690 is IRON
        super(identifier, ToolMaterial.IRON);
        this.setMaxDamage(0);
    }

    @Override
    public boolean postHit(final ItemStack itemstack, final LivingEntity damageSource, final LivingEntity damageTarget)
    {
        if (damageSource == null || damageTarget == null)
        {
            return false;
        }
        final String s = EntityRegistry.getId(damageSource);
        if (!s.equals("") && s.toLowerCase().contains("pig"))
        {
            if (damageSource.health > 0)
            {
                damageSource.health = 1;
                damageSource.hurtTime = 0;
                damageSource.damage(damageTarget, 9999);
            }
            for (int j = 0; j < 20; ++j)
            {
                final double d = ((EntityBaseAccessor) damageTarget).getRand().nextGaussian() * 0.02;
                final double d2 = ((EntityBaseAccessor) damageTarget).getRand().nextGaussian() * 0.02;
                final double d3 = ((EntityBaseAccessor) damageTarget).getRand().nextGaussian() * 0.02;
                final double d4 = 5.0;
                damageSource.world.addParticle("flame", damageSource.x + ((EntityBaseAccessor) damageSource).getRand().nextFloat() * damageSource.spacingXZ * 2.0f - damageSource.spacingXZ - d * d4, damageSource.y + ((EntityBaseAccessor) damageSource).getRand().nextFloat() * damageSource.spacingY - d2 * d4, damageSource.z + ((EntityBaseAccessor) damageSource).getRand().nextFloat() * damageSource.spacingXZ * 2.0f - damageSource.spacingXZ - d3 * d4, d, d2, d3);
            }
            ((LivingAccessor) damageSource).invokeGetDrops();
            damageSource.dead = true;
        }
        return true;
    }

    public boolean func_25008_a(final ItemStack itemstack, final int i, final int j, final int k, final int l, final LivingEntity entityliving)
    {
        return true;
    }
}
