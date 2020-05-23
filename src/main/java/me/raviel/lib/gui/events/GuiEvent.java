package me.raviel.lib.gui.events;

import me.raviel.lib.gui.Gui;
import me.raviel.lib.gui.GuiManager;
import org.bukkit.entity.Player;

public abstract class GuiEvent {

    public final GuiManager manager;
    public final Gui gui;
    public final Player player;

    public GuiEvent(GuiManager manager, Gui gui, Player player) {
        this.manager = manager;
        this.gui = gui;
        this.player = player;
    }

}
