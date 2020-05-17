package me.raviel.nms.version.nbt;

public interface NBTCompound {

    NBTCompound set(String tag, String s);

    NBTCompound set(String tag, boolean b);

    NBTCompound set(String tag, int i);

    NBTCompound set(String tag, double i);

    NBTCompound set(String tag, long l);

    NBTCompound set(String tag, short s);

    NBTCompound set(String tag, byte b);

    NBTCompound remove(String tag);

    boolean has(String tag);

    NBTObject getNBTObject(String tag);

}
