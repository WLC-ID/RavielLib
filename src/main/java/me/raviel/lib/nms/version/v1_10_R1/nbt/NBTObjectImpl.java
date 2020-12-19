package me.raviel.lib.nms.version.v1_10_R1.nbt;

import me.raviel.lib.nms.version.nbt.NBTObject;
import net.minecraft.server.v1_10_R1.NBTTagCompound;

public class NBTObjectImpl implements NBTObject {

    private final NBTTagCompound compound;
    private final String tag;

    public NBTObjectImpl(NBTTagCompound compound, String tag) {
        this.compound = compound;
        this.tag = tag;
    }

    public String asString() {
        return compound.getString(tag);
    }

    public boolean asBoolean() {
        return compound.getBoolean(tag);
    }

    public int asInt() {
        return compound.getInt(tag);
    }

    public double asDouble() {
        return compound.getDouble(tag);
    }

    public long asLong() {
        return compound.getLong(tag);
    }

    public short asShort() {
        return compound.getShort(tag);
    }

    public byte asByte() {
        return compound.getByte(tag);
    }

    public int[] asIntArray() {
        return compound.getIntArray(tag);
    }

}
