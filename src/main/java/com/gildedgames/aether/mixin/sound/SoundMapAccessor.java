package com.gildedgames.aether.mixin.sound;

import net.minecraft.class_266;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

/*DEBUG-ONLY*/
@Mixin(class_266.class)
public interface SoundMapAccessor
{
    @Accessor("field_1090")
    public List getSoundList();
}
