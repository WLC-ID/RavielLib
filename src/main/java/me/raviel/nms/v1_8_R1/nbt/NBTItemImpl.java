package me.raviel.nms.v1_8_R1.nbt;

import me.raviel.nms.nbt.NBTItem;
import net.minecraft.server.v1_8_R1.NBTTagCompound;
import org.bukkit.craftbukkit.v1_8_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

public class NBTItemImpl extends NBTCompoundImpl implements NBTItem {

    private net.minecraft.server.v1_8_R1.ItemStack nmsItem;

    public NBTItemImpl(net.minecraft.server.v1_8_R1.ItemStack nmsItem) {
        super(nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound());
        this.nmsItem = nmsItem;
    }

    public ItemStack finish() {
        return CraftItemStack.asBukkitCopy(nmsItem);
    }
}
