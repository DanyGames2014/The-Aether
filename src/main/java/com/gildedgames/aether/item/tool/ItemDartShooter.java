package com.gildedgames.aether.item.tool;

import com.gildedgames.aether.entity.projectile.EntityDartEnchanted;
import com.gildedgames.aether.entity.projectile.EntityDartGolden;
import com.gildedgames.aether.entity.projectile.EntityDartPoison;
import com.gildedgames.aether.event.listener.TextureListener;
import com.gildedgames.aether.registry.AetherItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.item.TemplateItem;
import net.modificationstation.stationapi.api.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class ItemDartShooter extends TemplateItem
{


    public ItemDartShooter(final @NotNull Identifier identifier)
    {
        super(identifier);
        this.setHasSubtypes(true);
        this.maxCount = 1;
    }

    @Override
    public int getTextureId(final int damage)
    {
        if (damage == 0)
        {
            return TextureListener.sprShooterNormal;
        }
        if (damage == 1)
        {
            return TextureListener.sprShooterPoison;
        }
        if (damage == 2)
        {
            return TextureListener.sprShooterEnchanted;
        }
        return TextureListener.sprShooterNormal;
    }

    @Override
    public String getTranslationKey(final ItemStack item)
    {
        int i = item.getDamage();
        if (i > 2)
        {
            i = 2;
        }
        return this.getTranslationKey() + i;
    }

    @Override
    public ItemStack use(final ItemStack item, final World level, final PlayerEntity player)
    {
        final int consume = this.consumeItem(player, AetherItems.Dart.id, item.getDamage());
        if (consume != -1)
        {
            level.playSound((Entity) player, "aether:aether.sound.other.dartshooter.shootdart", 2.0f, 1.0f / (ItemDartShooter.random.nextFloat() * 0.4f + 0.8f));
            if (!level.isRemote)
            {
                EntityDartGolden dart = null;
                switch (consume)
                {
                    case 1:
                        dart = new EntityDartPoison(level, player);
                    case 2:
                        dart = new EntityDartEnchanted(level, player);
                    default:
                        dart = new EntityDartGolden(level, player);
                }
                level.method_210(dart);
            }
        }
        return item;
    }

    private int consumeItem(final PlayerEntity player, final int itemID, final int maxDamage)
    {
        final Inventory inv = player.inventory;
        for (int i = 0; i < inv.size(); ++i)
        {
            ItemStack stack = inv.getStack(i);
            if (stack != null)
            {
                final int damage = stack.getDamage();
                if (stack.itemId == itemID && stack.getDamage() == maxDamage)
                {
                    final ItemStack itemInstance = stack;
                    --itemInstance.count;
                    if (stack.count == 0)
                    {
                        stack = null;
                    }
                    inv.setStack(i, stack);
                    return damage;
                }
            }
        }
        return -1;
    }

    static
    {
    }
}
