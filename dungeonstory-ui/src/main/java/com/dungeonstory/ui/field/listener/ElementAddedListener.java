package com.dungeonstory.ui.field.listener;

import java.io.Serializable;

import com.dungeonstory.ui.field.event.ElementAddedEvent;

public interface ElementAddedListener<ET> extends Serializable {

    void elementAdded(ElementAddedEvent<ET> e);

}
