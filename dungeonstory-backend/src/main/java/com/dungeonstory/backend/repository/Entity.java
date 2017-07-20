package com.dungeonstory.backend.repository;

import java.io.Serializable;

public interface Entity extends Cloneable, Serializable {

    public Long getId();

    public void setId(Long id);

}
