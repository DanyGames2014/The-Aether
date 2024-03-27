package com.gildedgames.aether.mixin.data;

import com.gildedgames.aether.AetherMod;
import com.gildedgames.aether.mixin.access.LevelAccessor;
import net.minecraft.class_81;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtIo;
import org.spongepowered.asm.mixin.Mixin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Mixin(PlayerEntity.class)
public class PlayerBaseMixin
{

    private void writeCustomData()
    {
        final NbtCompound customData = new NbtCompound();
        PlayerEntity player = (PlayerEntity) (Object) this;
        customData.putByte("MaxHealth", (byte) AetherMod.getPlayerHandler(PlayerEntity.class.cast(this)).maxHealth);
        try
        {
            final File file = new File(((DimensionFileAccessor) ((class_81) ((LevelAccessor) player.world).getDimData())).getSaveFolder(), "aether.dat");
            NbtIo.writeCompressed(customData, (OutputStream) new FileOutputStream(file));
        }
        catch (IOException ioexception)
        {
            ioexception.printStackTrace();
            throw new RuntimeException("Failed to create player data");
        }
    }
}
