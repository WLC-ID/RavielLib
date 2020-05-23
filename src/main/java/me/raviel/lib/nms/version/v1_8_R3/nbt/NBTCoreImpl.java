package me.raviel.lib.nms.version.v1_8_R3.nbt;

import me.raviel.lib.nms.version.nbt.NBTCompound;
import me.raviel.lib.nms.version.nbt.NBTCore;
import me.raviel.lib.nms.version.nbt.NBTItem;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
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
