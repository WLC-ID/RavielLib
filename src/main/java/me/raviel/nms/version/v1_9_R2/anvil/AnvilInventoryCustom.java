package me.raviel.nms.version.v1_9_R2.anvil;

import net.minecraft.server.v1_9_R2.IInventory;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_9_R2.inventory.CraftInventoryAnvil;
import org.bukkit.inventory.InventoryHolder;

public class AnvilInventoryCustom extends CraftInventoryAnvil {

    final InventoryHolder holder;

    public AnvilInventoryCustom(InventoryHolder holder, Location location, IInventory inventory, IInventory resultInventory) {
        super(location, inventory, resultInventory);
        this.holder = holder;
    }

    @Override
    public InventoryHolder getHolder() {
        return holder;
    }
}
