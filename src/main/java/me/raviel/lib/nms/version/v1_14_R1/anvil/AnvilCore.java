package me.raviel.lib.nms.version.v1_14_R1.anvil;

import me.raviel.lib.nms.version.anvil.CustomAnvil;
import net.minecraft.server.v1_14_R1.EntityPlayer;
import org.bukkit.craftbukkit.v1_14_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryHolder;

public class AnvilCore implements me.raviel.lib.nms.version.anvil.AnvilCore {

    @Override
    public CustomAnvil createAnvil(Player player) {
        EntityPlayer p = ((CraftPlayer) player).getHandle();
        return new AnvilView(p.nextContainerCounter(), p, null);
    }

    @Override
    public CustomAnvil createAnvil(Player player, InventoryHolder holder) {
        EntityPlayer p = ((CraftPlayer) player).getHandle();
        return new AnvilView(p.nextContainerCounter(), p, holder);
    }

}
