package com.dungeonstory.view;

import com.dungeonstory.backend.DataService;
import com.dungeonstory.backend.data.Level;
import com.dungeonstory.util.VerticalSpacedLayout;
import com.dungeonstory.util.ViewConfig;
import com.dungeonstory.view.component.LevelGrid;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

@ViewConfig(uri = "levels", displayName = "Niveaux")
public class LevelView extends VerticalSpacedLayout implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1085138977601539109L;
	private Label titre;
	private LevelGrid grid;
	
	public LevelView() {
		grid = new LevelGrid();
		titre = new Label("Niveaux");
		
		Button addNew = new Button("", FontAwesome.PLUS);
	    
	    addNew.addClickListener(this::addNew);
	    HorizontalLayout boutonLayout = new HorizontalLayout(addNew);
	    
	    addComponents(titre, boutonLayout, grid);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		grid.setData(DataService.get().getAllLevels());
	}
	
	private void addNew(Button.ClickEvent e) {
    	grid.addRow(new Level());
    }

}
