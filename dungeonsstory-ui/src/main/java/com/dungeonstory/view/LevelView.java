package com.dungeonstory.view;

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
		grid = new LevelGrid(Level.class);
		titre = new Label("Niveaux");
		
		Button addNew = new Button("", FontAwesome.PLUS);
//	    Button delete = new Button("", FontAwesome.TRASH_O);
	    
	    addNew.addClickListener(this::addNew);
//	    delete.addClickListener(this::deleteSelected);
	    HorizontalLayout boutonLayout = new HorizontalLayout(addNew);
	    
	    addComponents(titre, boutonLayout, grid);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO set level from backend

	}
	
	private void addNew(Button.ClickEvent e) {
    	grid.addRow(new Level());
    }

}
