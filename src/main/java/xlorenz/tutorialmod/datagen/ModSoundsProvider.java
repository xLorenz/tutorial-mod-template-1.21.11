package xlorenz.tutorialmod.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.builder.SoundTypeBuilder;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricSoundsProvider;
import net.minecraft.data.DataOutput;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.sound.SoundEvent;
import xlorenz.tutorialmod.sound.ModSounds;

import java.util.concurrent.CompletableFuture;

public class ModSoundsProvider extends FabricSoundsProvider {
    public ModSoundsProvider(DataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registryLookup, SoundExporter exporter) {
        add(exporter, ModSounds.CHISEL_USE);

        add(exporter, ModSounds.MAGIC_BLOCK_BREAK);
        add(exporter, ModSounds.MAGIC_BLOCK_FALL);
        add(exporter, ModSounds.MAGIC_BLOCK_HIT);
        add(exporter, ModSounds.MAGIC_BLOCK_PLACE);
        add(exporter, ModSounds.MAGIC_BLOCK_STEP);
    }

    public void add(SoundExporter exporter, SoundEvent sound) {
        exporter.add(sound, SoundTypeBuilder.of().sound(SoundTypeBuilder.EntryBuilder.ofFile(Registries.SOUND_EVENT.getId(sound))));

    }

    @Override
    public String getName() {
        return "TutorialModSounds";
    }
}
