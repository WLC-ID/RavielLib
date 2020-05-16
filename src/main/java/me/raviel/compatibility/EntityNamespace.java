package me.raviel.compatibility;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.entity.EntityType;
import java.util.AbstractMap.SimpleEntry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EntityNamespace {

    static final HashMap<String, EntityType> validTypes = new HashMap<>();
    static final HashMap<String, String> legacyToModernTypes = (HashMap<String, String>) Stream.of(

            new SimpleEntry<>("xporb", "experience_orb"),
            new SimpleEntry<>("xp_orb", "experience_orb"),
            new SimpleEntry<>("leashknot", "leash_knot"),
            new SimpleEntry<>("smallfireball", "small_fireball"),
            new SimpleEntry<>("thrownenderpearl", "ender_pearl"),
            new SimpleEntry<>("eyeofendersignal", "eye_of_ender"),
            new SimpleEntry<>("eye_of_ender_signal", "eye_of_ender"),
            new SimpleEntry<>("thrownexpbottle", "experience_bottle"),
            new SimpleEntry<>("xp_bottle", "experience_bottle"),
            new SimpleEntry<>("itemframe", "item_frame"),
            new SimpleEntry<>("witherskull", "wither_skull"),
            new SimpleEntry<>("primedtnt", "tnt"),
            new SimpleEntry<>("fallingsand", "falling_block"),
            new SimpleEntry<>("fireworksrocketentity", "firework_rocket"),
            new SimpleEntry<>("fireworks_rocket", "firework_rocket"),
            new SimpleEntry<>("spectralarrow", "spectral_arrow"),
            new SimpleEntry<>("tippedarrow", "arrow"),
            new SimpleEntry<>("shulkerbullet", "shulker_bullet"),
            new SimpleEntry<>("dragonfireball", "dragon_fireball"),
            new SimpleEntry<>("armorstand", "armor_stand"),
            new SimpleEntry<>("minecartcommandblock", "command_block_minecart"),
            new SimpleEntry<>("commandblock_minecart", "command_block_minecart"),
            new SimpleEntry<>("minecartrideable", "minecart"),
            new SimpleEntry<>("minecartchest", "chest_minecart"),
            new SimpleEntry<>("minecartfurnace", "furnace_minecart"),
            new SimpleEntry<>("minecarttnt", "tnt_minecart"),
            new SimpleEntry<>("minecarthopper", "hopper_minecart"),
            new SimpleEntry<>("minecartmobspawner", "spawner_minecart"),
            new SimpleEntry<>("pigzombie", "zombie_pigman"),
            new SimpleEntry<>("cavespider", "cave_spider"),
            new SimpleEntry<>("lavaslime", "magma_cube"),
            new SimpleEntry<>("enderdragon", "ender_dragon"),
            new SimpleEntry<>("witherboss", "wither"),
            new SimpleEntry<>("mushroomcow", "mooshroom"),
            new SimpleEntry<>("snowman", "snow_golem"),
            new SimpleEntry<>("snowman", "snow_golem"),
            new SimpleEntry<>("ozelot", "ocelot"),
            new SimpleEntry<>("villagergolem", "iron_golem"),
            new SimpleEntry<>("villager_golem", "iron_golem"),
            new SimpleEntry<>("entityhorse", "horse"),
            new SimpleEntry<>("endercrystal", "end_crystal"),
            new SimpleEntry<>("ender_crystal", "end_crystal")
            
            ).collect(Collectors.toMap(SimpleEntry::getKey, SimpleEntry::getValue));

    static {
        for (EntityType t : EntityType.values()) {
            if (t.getName() != null) {
                validTypes.put(t.getName().toLowerCase(), t);
            }
        }
    }

    public static EntityType minecraftToBukkit(String entity) {
        if (entity == null) {
            return null;
        }
        // first try to translate natively
        EntityType type = EntityType.fromName(entity);
        if (type == null) {
            // try legacy values
            type = EntityType.fromName(legacyToModernTypes.get(entity));
            // try converting modern to legacy
            if (type == null && legacyToModernTypes.containsValue(entity)) {
                for (Map.Entry<String, String> e : legacyToModernTypes.entrySet()) {
                    if (e.getValue().equals(entity) && (type = EntityType.fromName(legacyToModernTypes.get(e.getKey()))) != null) {
                        return type;
                    }
                }
            }
        }
        return type;
    }
}
