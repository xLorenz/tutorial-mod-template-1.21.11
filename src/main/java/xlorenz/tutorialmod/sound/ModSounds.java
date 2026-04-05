package xlorenz.tutorialmod.sound;

import net.minecraft.block.jukebox.JukeboxSong;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import xlorenz.tutorialmod.TutorialMod;

public class ModSounds {

    public static final SoundEvent CHISEL_USE = register("chisel_use");

    public static final SoundEvent MAGIC_BLOCK_BREAK = register("magic_block_break");
    public static final SoundEvent MAGIC_BLOCK_STEP = register("magic_block_step");
    public static final SoundEvent MAGIC_BLOCK_PLACE = register("magic_block_place");
    public static final SoundEvent MAGIC_BLOCK_HIT = register("magic_block_hit");
    public static final SoundEvent MAGIC_BLOCK_FALL = register("magic_block_fall");

    public static final SoundEvent BAR_BRAWL = register("bar_brawl");
    public static final RegistryKey<JukeboxSong> BAR_BRAWL_KEY = RegistryKey.of(RegistryKeys.JUKEBOX_SONG,
            Identifier.of(TutorialMod.MOD_ID, "bar_brawl"));


    public static final BlockSoundGroup MAGIC_BLOCK_SOUNDS = new BlockSoundGroup(1.0f, 1.0f,
            MAGIC_BLOCK_BREAK, MAGIC_BLOCK_STEP, MAGIC_BLOCK_PLACE, MAGIC_BLOCK_HIT, MAGIC_BLOCK_FALL);





    public  static SoundEvent register(String name) {
        Identifier id = Identifier.of(TutorialMod.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }


    public static void initialize(){
        TutorialMod.LOGGER.info("Registering Mod Sounds fot " + TutorialMod.MOD_ID);
    }
}
