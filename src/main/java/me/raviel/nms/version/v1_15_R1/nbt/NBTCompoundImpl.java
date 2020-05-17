package me.raviel.nms.version.v1_15_R1.nbt;

import me.raviel.nms.version.nbt.NBTCompound;
import me.raviel.nms.version.nbt.NBTObject;
import net.minecraft.server.v1_15_R1.NBTTagCompound;

public class NBTCompoundImpl implements NBTCompound {

    protected NBTTagCompound compound;

    protected NBTCompoundImpl(NBTTagCompound compound) {
        this.compound = compound;
    }

    public NBTCompoundImpl() {
        this.compound = new NBTTagCompound();
    }

    @Override
    public NBTCompound set(String tag, String s) {
        compound.setString(tag, s);
        return this;
    }

    @Override
    public NBTCompound set(String tag, boolean b) {
        compound.setBoolean(tag, b);
        return this;
    }

    @Override
    public NBTCompound set(String tag, int i) {
        compound.setInt(tag, i);
        return this;
    }

    @Override
    public NBTCompound set(String tag, double i) {
        compound.setDouble(tag, i);
        return this;
    }

    @Override
    public NBTCompound set(String tag, long l) {
        compound.setLong(tag, l);
        return this;
    }

    @Override
    public NBTCompound set(String tag, short s) {
        compound.setShort(tag, s);
        return this;
    }

    @Override
    public NBTCompound set(String tag, byte b) {
        compound.setByte(tag, b);
        return this;
    }

    @Override
    public NBTCompound remove(String tag) {
        compound.remove(tag);
        return this;
    }

    @Override
    public boolean has(String tag) {
        return compound.hasKey(tag);
    }

    @Override
    public NBTObject getNBTObject(String tag) {
        return new NBTObjectImpl(compound, tag);
    }

}
