package me.raviel.lib.nms.version.v1_16_R2.nbt;

import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import me.raviel.lib.nms.version.nbt.NBTItem;
import net.minecraft.server.v1_16_R2.NBTTagCompound;

public class NBTItemImpl extends NBTCompoundImpl implements NBTItem {

    private net.minecraft.server.v1_16_R2.ItemStack nmsItem;

    public NBTItemImpl(net.minecraft.server.v1_16_R2.ItemStack nmsItem) {
        super(nmsItem != null && nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound());
        this.nmsItem = nmsItem;
    }

    public ItemStack finish() {
        if (nmsItem == null) {
            return CraftItemStack.asBukkitCopy(net.minecraft.server.v1_16_R2.ItemStack.a(compound));
        } else {
            return CraftItemStack.asBukkitCopy(nmsItem);
        }
    }

    @Override
    public void addExtras() {
    }
}
