package me.raviel.lib.gui.events;

import me.raviel.lib.gui.Gui;
import me.raviel.lib.gui.GuiManager;

public class GuiPageEvent {

    final Gui gui;
    final GuiManager manager;
    final int lastPage;
    final int page;

    public GuiPageEvent(Gui gui, GuiManager manager, int lastPage, int page) {
        this.gui = gui;
        this.manager = manager;
        this.lastPage = lastPage;
        this.page = page;
    }

}
