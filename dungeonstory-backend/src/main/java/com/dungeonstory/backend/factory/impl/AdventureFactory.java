package com.dungeonstory.backend.factory.impl;

import com.dungeonstory.backend.data.Adventure;
import com.dungeonstory.backend.factory.Factory;

public class AdventureFactory implements Factory<Adventure> {

	private static final long serialVersionUID = 2823271201526253126L;

	@Override
	public Adventure create() {
		return new Adventure();
	}

}
