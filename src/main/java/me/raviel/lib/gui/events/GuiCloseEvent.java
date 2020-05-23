package me.raviel.lib.gui.events;

import me.raviel.lib.gui.Gui;
import me.raviel.lib.gui.GuiManager;
import org.bukkit.entity.Player;

public class GuiCloseEvent extends GuiEvent {

    public GuiCloseEvent(GuiManager manager, Gui gui, Player player) {
        super(manager, gui, player);
    }

}
