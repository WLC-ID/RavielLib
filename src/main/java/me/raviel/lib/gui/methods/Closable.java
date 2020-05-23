package me.raviel.lib.gui.methods;

import me.raviel.lib.gui.events.GuiCloseEvent;

public interface Closable {

    void onClose(GuiCloseEvent event);
}
