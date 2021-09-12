package net.runedar.snr.registry;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.runedar.snr.SnR;

import java.util.LinkedHashMap;
import java.util.Map;

public class ModSounds {
    private static final Map<SoundEvent, Identifier> SOUND_EVENTS = new LinkedHashMap<>();
            //Blocks
            public static final SoundEvent BLOCK_STATUE_ACTIVATE = create("block.statue.activate");
            public static final SoundEvent BLOCK_STATUE_DEACTIVATE = create("block.statue.deactivate");

    private static SoundEvent create(String name) {
        Identifier id = new Identifier(SnR.MODID, name);
        SoundEvent soundEvent = new SoundEvent(id);
        SOUND_EVENTS.put(soundEvent, id);
        return soundEvent;
    }

        public static void registerSounds () {
            SOUND_EVENTS.keySet().forEach(soundEvent -> Registry.register(Registry.SOUND_EVENT, SOUND_EVENTS.get(soundEvent), soundEvent));
        }
}
