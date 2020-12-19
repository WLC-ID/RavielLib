package me.raviel.lib.nms.version.v1_16_R1.anvil;

import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.craftbukkit.v1_16_R1.entity.CraftPlayer;
import net.minecraft.server.v1_16_R1.EntityPlayer;

import me.raviel.lib.nms.version.anvil.CustomAnvil;

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
