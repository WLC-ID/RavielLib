package me.raviel.nms.v1_8_R1.nbt;

import me.raviel.nms.nbt.NBTCompound;
import me.raviel.nms.nbt.NBTCore;
import me.raviel.nms.nbt.NBTItem;
import org.bukkit.craftbukkit.v1_8_R1.inventory.CraftItemStack;
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
