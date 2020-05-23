package me.raviel.lib.nms.version.v1_8_R2.anvil;

import me.raviel.lib.nms.version.anvil.CustomAnvil;
import org.bukkit.craftbukkit.v1_8_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryHolder;

public class AnvilCore implements me.raviel.lib.nms.version.anvil.AnvilCore {

    @Override
    public CustomAnvil createAnvil(Player player) {
        return new AnvilView(((CraftPlayer) player).getHandle(), null);
    }

    @Override
    public CustomAnvil createAnvil(Player player, InventoryHolder holder) {
        return new AnvilView(((CraftPlayer) player).getHandle(), holder);
    }

}
