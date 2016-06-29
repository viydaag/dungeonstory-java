package com.dungeonstory.view;

import com.dungeonstory.form.RegionForm;
import com.dungeonstory.samples.backend.data.Region;
import com.dungeonstory.util.VerticalSpacedLayout;
import com.dungeonstory.util.ViewConfig;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

@ViewConfig(uri = "regions", displayName = "Régions")
public class RegionView extends VerticalSpacedLayout implements View {

	private static final long serialVersionUID = 5117755861151432771L;

	public static final String VIEW_NAME = "Region";
	
	private static Long regionId = 0L;
	
	private Label titre;
	private RegionForm form;
	private RegionGrid grid;
	
	public RegionView() {
		
		form = new RegionForm();
		grid = new RegionGrid(Region.class);
		titre = new Label(form.toString());
		
		Button addNew = new Button("", FontAwesome.PLUS);
	    Button delete = new Button("", FontAwesome.TRASH_O);
	    
	    addNew.addClickListener(this::addNew);
	    delete.addClickListener(this::deleteSelected);
	    HorizontalLayout boutonLayout = new HorizontalLayout(addNew, delete);
	    
	    form.setEntity(null);
	    
	    //ajout handlers pour boutons
        form.setSavedHandler(this::entrySaved);
        form.setResetHandler(this::entryReset);
        form.setSaveCaption("Enregistrer");
        form.setCancelCaption("Annuler");
        
        grid.addSelectionListener(selectionEvent -> {entrySelected();});
        
        addComponents(titre, boutonLayout, form, grid);
	}
	
	public void entrySaved(Region region) {
    	
		region.setId(regionId++);
    	grid.refresh(region);
    	form.setEntity(null);
    	grid.scrollTo(region);
    	
    	Notification.show("Saved!", Type.HUMANIZED_MESSAGE);
    }
    
    public void entryReset(Region region) {
    	form.getFieldGroup().discard();
    }
    
    public void entrySelected() {
    	form.setEntity(grid.getSelectedRow() == null ? new Region() : grid.getSelectedRow());
    	form.focusFirst();
    }
    
    private void addNew(Button.ClickEvent e) {
    	form.setEntity(new Region());
    }

    private void deleteSelected(Button.ClickEvent e) {
    	Region bien = grid.getSelectedRow();
//    	bienEpargneService.remove(bien);
		grid.remove(bien);
    	form.setEntity(null);
    }

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub

	}

}
