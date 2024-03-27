package com.gildedgames.aether.item.tool;

import com.gildedgames.aether.entity.projectile.EntityAetherLightning;
import com.gildedgames.aether.utils.EnumElement;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.PigZombieEntity;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.modificationstation.stationapi.api.template.item.TemplateSwordItem;
import net.modificationstation.stationapi.api.util.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ItemSwordElemental extends TemplateSwordItem
{
    public static ArrayList<Class<? extends LivingEntity>> undead;
    private int weaponDamage;
    private int holyDamage;
    private EnumElement element;
    public static Identifier elementalSwordTexture;
    private int colour;

    public ItemSwordElemental(final @NotNull Identifier identifier, final EnumElement element, final int colour)
    {
        super(identifier, ToolMaterial.DIAMOND); // (ToolMaterial) field_1691 = EMERALD
        this.maxCount = 1;
        this.setMaxDamage((element == EnumElement.Holy) ? 128 : 32);
        this.weaponDamage = 4;
        this.holyDamage = 20;
        this.element = element;
        this.colour = colour;
    }

    @Override
    public float getMiningSpeedMultiplier(final ItemStack item, final Block tile)
    {
        return 1.5f;
    }

    @Override
    public boolean postMine(final ItemStack itemstack, final int i, final int j, final int k, final int l, final LivingEntity damageTarget)
    {
        itemstack.damage(2, damageTarget);
        return true;
    }

    @Override
    public boolean postHit(final ItemStack itemstack, final LivingEntity damageSource, final LivingEntity damageTarget)
    {
        if (this.element == EnumElement.Fire)
        {
            damageSource.fire = 600;
        }
        else if (this.element == EnumElement.Lightning)
        {
            damageTarget.world.method_210(new EntityAetherLightning(damageSource.world, damageSource.x, damageSource.y, damageSource.z));
            // aether lightning ModLoader.getMinecraftInstance().level.spawnEntity(new EntityAetherLightning(ModLoader.getMinecraftInstance().level, (int)damageSource.x, (int)damageSource.y, (int)damageSource.z));
        }
        itemstack.damage(1, damageTarget);
        return true;
    }

    @Override
    public int getAttackDamage(final Entity entity)
    {
        if (this.element == EnumElement.Holy && entity instanceof LivingEntity)
        {
            final LivingEntity living = (LivingEntity) entity;
            for (final Class<? extends LivingEntity> cls : ItemSwordElemental.undead)
            {
                if (living.getClass().isAssignableFrom((Class) cls))
                {
                    return this.holyDamage;
                }
            }
        }
        return this.weaponDamage;
    }

    @Override
    public int method_440(final int i)
    {
        return this.colour;
    }

    @Override
    public boolean isHandheld()
    {
        return true;
    }

    static
    {
        (ItemSwordElemental.undead = new ArrayList<Class<? extends LivingEntity>>()).add(ZombieEntity.class);
        ItemSwordElemental.undead.add(SkeletonEntity.class);
        ItemSwordElemental.undead.add(PigZombieEntity.class);
    }
}
