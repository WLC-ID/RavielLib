package me.raviel.nms.version.v1_9_R2.nbt;

import me.raviel.nms.version.nbt.NBTCompound;
import me.raviel.nms.version.nbt.NBTCore;
import me.raviel.nms.version.nbt.NBTItem;
import org.bukkit.craftbukkit.v1_9_R2.inventory.CraftItemStack;
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
