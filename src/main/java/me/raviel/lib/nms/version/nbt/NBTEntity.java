package me.raviel.lib.nms.version.nbt;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

public interface NBTEntity extends NBTCompound {

    Entity spawn(Location location);

    Entity reSpawn(Location location);

}