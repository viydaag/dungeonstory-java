package com.dungeonstory.ui.field.listener;

import java.io.Serializable;

import com.dungeonstory.ui.field.event.ElementRemovedEvent;

public interface ElementRemovedListener<ET> extends Serializable {

    void elementRemoved(ElementRemovedEvent<ET> e);

}
