package me.raviel.gui;

import org.bukkit.event.inventory.InventoryType;

public enum GuiType {

    STANDARD(InventoryType.CHEST),
    DISPENSER(InventoryType.DISPENSER),
    HOPPER(InventoryType.HOPPER);

    protected final InventoryType type;

    private GuiType(InventoryType type) {
        this.type = type;
    }

}
