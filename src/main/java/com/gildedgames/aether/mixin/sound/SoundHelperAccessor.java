package com.gildedgames.aether.mixin.sound;

import net.minecraft.class_266;
import net.minecraft.client.sound.SoundManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import paulscode.sound.SoundSystem;

@Mixin(SoundManager.class)
public interface SoundHelperAccessor
{

    @Accessor("field_2671")
    int getSoundUID();

    @Accessor("field_2670")
    public class_266 getBgMusicMap();

    @Accessor("soundSystem")
    public static SoundSystem getSoundSystem()
    {
        throw new AssertionError();
    }

    ;

    @Accessor("field_2675")
    void setMusicCountdown(int i);
}
