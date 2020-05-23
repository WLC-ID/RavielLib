package me.raviel.lib.gui.methods;

import me.raviel.lib.gui.events.GuiDropItemEvent;

public interface Droppable {

    boolean onDrop(GuiDropItemEvent event);
}
