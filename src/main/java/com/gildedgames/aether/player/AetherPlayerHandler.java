package com.gildedgames.aether.player;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.gildedgames.aether.Aether;
import com.gildedgames.aether.gui.container.ContainerAether;
import com.gildedgames.aether.inventory.InventoryAether;
import com.gildedgames.aether.mixin.DimesnionFileAccessor;
import com.gildedgames.aether.mixin.LevelAccessor;
import com.gildedgames.aether.mixin.MinecraftClientAccessor;
import com.gildedgames.aether.registry.AetherItems;

import net.minecraft.entity.player.AbstractClientPlayer;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.level.dimension.DimensionFile;
import net.minecraft.util.io.AbstractTag;
import net.minecraft.util.io.CompoundTag;
import net.minecraft.util.io.ListTag;
import net.minecraft.util.io.NBTIO;

public class AetherPlayerHandler implements net.modificationstation.stationapi.api.entity.player.PlayerHandler{
	private PlayerBase player;
	public AetherPlayerHandler(PlayerBase playerBase) {
		player = playerBase;
	}
	@Override
	public boolean writeEntityBaseToNBT(CompoundTag tag) {
		try {
    		final CompoundTag customData = new CompoundTag();
    		customData.put("MaxHealth", (byte)Aether.maxHealth);
    		customData.put("Inventory", (AbstractTag)Aether.inv.writeToNBT(new ListTag()));
        
        	final File file = new File(((DimesnionFileAccessor)((DimensionFile)((LevelAccessor)player.level).getDimData())).getSaveFolder(), "aether.dat");
            NBTIO.writeGzipped(customData, (OutputStream)new FileOutputStream(file));
        }
        catch (Exception ioexception) {
            //throw new RuntimeException("Failed to create player data");
        }
		return false;
		
    }
	@Override
	public boolean readEntityBaseFromNBT(CompoundTag tag) {
		CompoundTag customData = new CompoundTag();
        try {
        	final File file = new File(((DimesnionFileAccessor)((DimensionFile)((LevelAccessor)player.level).getDimData())).getSaveFolder(), "aether.dat");
            customData = NBTIO.readGzipped((InputStream)new FileInputStream(file));
            
            Aether.maxHealth = customData.getByte("MaxHealth");
            if (Aether.maxHealth < 20) {
                Aether.maxHealth = 20;
            }
            final ListTag nbttaglist = customData.getListTag("Inventory");
            Aether.inv.readFromNBT(nbttaglist);
        }
        catch (Exception ioexception) {
            System.out.println("Failed to read player data. Making new");
            Aether.maxHealth = 20;
        }
        return false;
    }
	private void readCustomData() {
    	CompoundTag customData = new CompoundTag();
        try {
        	final File file = new File(((DimesnionFileAccessor)((DimensionFile)((LevelAccessor)player.level).getDimData())).getSaveFolder(), "aether.dat");
            customData = NBTIO.readGzipped((InputStream)new FileInputStream(file));
            
            Aether.maxHealth = customData.getByte("MaxHealth");
            if (Aether.maxHealth < 20) {
                Aether.maxHealth = 20;
            }
            final ListTag nbttaglist = customData.getListTag("Inventory");
            Aether.inv.readFromNBT(nbttaglist);
        }
        catch (Exception ioexception) {
            System.out.println("Failed to read player data. Making new");
            Aether.maxHealth = 20;
        }
    }
	private void writeCustomData(final InventoryAether inv) {
        final CompoundTag customData = new CompoundTag();
        Aether.inv = inv;
        AbstractClientPlayer player = AbstractClientPlayer.class.cast(this);
        customData.put("MaxHealth", (byte)Aether.maxHealth);
        customData.put("Inventory",inv.writeToNBT(new ListTag()));
        try {
            final File file = new File(((DimesnionFileAccessor)((DimensionFile)((LevelAccessor)player.level).getDimData())).getSaveFolder(), "aether.dat");
            NBTIO.writeGzipped(customData, (OutputStream)new FileOutputStream(file));
        }
        catch (IOException ioexception) {
            ioexception.printStackTrace();
            throw new RuntimeException("Failed to create player data");
        }
    }
	@Override
	public boolean onLivingUpdate() {
    	if(Aether.inv == null) {
    		Aether.inv = new InventoryAether(player);
    		readCustomData();
    	}
    	if(!(player.playerContainer instanceof ContainerAether)) {
        	player.playerContainer = new ContainerAether(player.inventory, Aether.inv, true);
        	player.container = player.playerContainer;
    	}
        //if (MainMenu.mmactive) {
        //    player.setPosition(player.prevRenderX, player.prevRenderY, player.prevRenderZ);
        //}
        if (player.field_1645 % 400 == 0) {
            if (Aether.inv.slots[0] != null && Aether.inv.slots[0].itemId == AetherItems.ZanitePendant.id) {
                Aether.inv.slots[0].applyDamage(1, player);
                if (Aether.inv.slots[0].count < 1) {
                    Aether.inv.slots[0] = null;
                }
            }
            if (Aether.inv.slots[4] != null && Aether.inv.slots[4].itemId == AetherItems.ZaniteRing.id) {
                Aether.inv.slots[4].applyDamage(1, player);
                if (Aether.inv.slots[4].count < 1) {
                    Aether.inv.slots[4] = null;
                }
            }
            if (Aether.inv.slots[5] != null && Aether.inv.slots[5].itemId == AetherItems.ZaniteRing.id) {
                Aether.inv.slots[5].applyDamage(1, player);
                if (Aether.inv.slots[5].count < 1) {
                    Aether.inv.slots[5] = null;
                }
            }
            if (Aether.inv.slots[0] != null && Aether.inv.slots[0].itemId == AetherItems.IcePendant.id) {
                Aether.inv.slots[0].applyDamage(1, player);
                if (Aether.inv.slots[0].count < 1) {
                    Aether.inv.slots[0] = null;
                }
            }
            if (Aether.inv.slots[4] != null && Aether.inv.slots[4].itemId == AetherItems.IceRing.id) {
                Aether.inv.slots[4].applyDamage(1, player);
                if (Aether.inv.slots[4].count < 1) {
                    Aether.inv.slots[4] = null;
                }
            }
            if (Aether.inv.slots[5] != null && Aether.inv.slots[5].itemId == AetherItems.IceRing.id) {
                Aether.inv.slots[5].applyDamage(1, player);
                if (Aether.inv.slots[5].count < 1) {
                    Aether.inv.slots[5] = null;
                }
            }
        }
        if (player.level.difficulty == 0 && player.health >= 20 && player.health < Aether.maxHealth && player.field_1645 % 20 == 0) {
            player.addHealth(1);
        }
		return false;
	}
}