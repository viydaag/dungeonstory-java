package com.dungeonstory.backend.factory;

import java.io.Serializable;

public interface Factory<E> extends Serializable {
    
    E create();
    
}
