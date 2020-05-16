package me.raviel.gui.methods;

import me.raviel.gui.events.GuiCloseEvent;

public interface Closable {

    void onClose(GuiCloseEvent event);
}
