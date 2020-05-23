package me.raviel.lib.nms.version.v1_12_R1.nbt;

import me.raviel.lib.nms.version.nbt.NBTCompound;
import me.raviel.lib.nms.version.nbt.NBTCore;
import me.raviel.lib.nms.version.nbt.NBTItem;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

public class NBTCoreImpl implements NBTCore {

    @Override
    public NBTItem of(ItemStack item) {
        return new NBTItemImpl(CraftItemStack.asNMSCopy(item));
    }

    @Override
    public NBTCompound newCompound() {
        return new NBTCompoundImpl();
    }

}
