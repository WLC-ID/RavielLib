package me.raviel.gui.methods;

import me.raviel.gui.events.GuiDropItemEvent;

public interface Droppable {

    boolean onDrop(GuiDropItemEvent event);
}
