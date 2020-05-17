package me.raviel.nms.version.v1_13_R2.anvil;

import me.raviel.nms.version.anvil.CustomAnvil;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryHolder;

public class AnvilCore implements me.raviel.nms.version.anvil.AnvilCore {

    @Override
    public CustomAnvil createAnvil(Player player) {
        return new AnvilView(((CraftPlayer) player).getHandle(), null);
    }

    @Override
    public CustomAnvil createAnvil(Player player, InventoryHolder holder) {
        return new AnvilView(((CraftPlayer) player).getHandle(), holder);
    }

}
