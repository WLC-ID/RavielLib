package me.raviel.gui.events;

import me.raviel.gui.Gui;
import me.raviel.gui.GuiManager;

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
