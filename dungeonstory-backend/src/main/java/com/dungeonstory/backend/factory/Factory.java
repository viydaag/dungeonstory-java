package com.dungeonstory.backend.factory;

import java.io.Serializable;

@FunctionalInterface
public interface Factory<E> extends Serializable {
    
    E create();
    
}
