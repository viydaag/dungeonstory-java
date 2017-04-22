package com.dungeonstory.view.adventure;

import com.dungeonstory.authentication.CurrentUser;
import com.dungeonstory.backend.data.Adventure;
import com.dungeonstory.backend.data.Adventure.AdventureStatus;
import com.dungeonstory.backend.service.DataService;
import com.dungeonstory.backend.service.impl.AdventureService;
import com.dungeonstory.form.DSAbstractForm;
import com.dungeonstory.util.ViewConfig;
import com.dungeonstory.view.AbstractCrudView;
import com.dungeonstory.view.grid.DSGrid;

@ViewConfig(displayName="Aventures", uri="adventures")
public class AdventureListView extends AbstractCrudView<Adventure> {
	
	private static final long serialVersionUID = -2282322292623232916L;
	
	public AdventureListView() {
		super();
		getAddButton().setCaption("Nouvelle aventure");
		setFilterAllowed(false);
	}

	@Override
	public DSAbstractForm<Adventure> getForm() {
		return new AdventureForm();
	}

	@Override
	public DSGrid<Adventure> getGrid() {
		return new DSGrid<Adventure>(Adventure.class);
	}

	@Override
	public DataService<Adventure, Long> getDataService() {
		return AdventureService.getInstance();
	}

	@Override
	public void entrySaved(Adventure entity) {
		entity.setCreator(CurrentUser.get());
		entity.setStatus(AdventureStatus.OPENED);
		super.entrySaved(entity);
	}
}
