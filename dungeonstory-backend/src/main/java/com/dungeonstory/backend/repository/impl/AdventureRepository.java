package com.dungeonstory.backend.repository.impl;

import com.dungeonstory.backend.data.Adventure;
import com.dungeonstory.backend.repository.AbstractRepository;

public class AdventureRepository extends AbstractRepository<Adventure, Long> {

	private static final long serialVersionUID = 5381315272390790047L;

	@Override
	protected Class<Adventure> getEntityClass() {
		return Adventure.class;
	}


}
