package com.gildedgames.aether.entity.projectile;

import com.gildedgames.aether.AetherMod;
import com.gildedgames.aether.registry.AetherItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;

public class EntityDartEnchanted extends EntityDartGolden
{
    public LivingEntity victim;
    public static int texfxindex;

    public EntityDartEnchanted(final World level)
    {
        super(level);
    }

    public EntityDartEnchanted(final World world, final double x, final double y, final double z)
    {
        super(world, x, y, z);
    }

    public EntityDartEnchanted(final World world, final LivingEntity ent)
    {
        super(world, ent);
    }

    @Override
    public void initDataTracker()
    {
        super.initDataTracker();
        this.item = new ItemStack(AetherItems.Dart, 1, 2);
        this.dmg = 6;
    }

    @Override
    public Identifier getHandlerIdentifier()
    {
        return AetherMod.MODID.id("EnchantedDart");
    }

    static
    {
        EntityDartEnchanted.texfxindex = 94;
    }
}
