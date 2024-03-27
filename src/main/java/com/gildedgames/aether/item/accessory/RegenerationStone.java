package com.gildedgames.aether.item.accessory;

import com.gildedgames.aether.player.AetherPlayerHandler;
import com.matthewperiut.accessoryapi.api.Accessory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.template.item.TemplateItem;
import net.modificationstation.stationapi.api.util.Identifier;

public class RegenerationStone extends TemplateItem implements Accessory
{
    public RegenerationStone(Identifier identifier)
    {
        super(identifier);
        this.setMaxCount(1);
        this.setMaxDamage(250);
    }

    @Override
    public String[] getAccessoryTypes(ItemStack item)
    {
        return new String[]{"misc"};
    }

    @Override
    public ItemStack tickWhileWorn(PlayerEntity playerBase, ItemStack itemInstance)
    {
        if (playerBase.field_1645 % 200 == 0)
        {
            int maxHealth = AetherPlayerHandler.get(playerBase).maxHealth;
            if (playerBase.health < maxHealth)
            {
                playerBase.health += 1;
                itemInstance.damage(1, playerBase);
            }
        }
        return itemInstance;
    }
}
