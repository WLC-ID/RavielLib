package me.raviel.lib.nms.version.anvil;

import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryHolder;

public interface AnvilCore {
    public CustomAnvil createAnvil(Player player);
    public CustomAnvil createAnvil(Player player, InventoryHolder holder);
}
