package com.dungeonstory.view;

import com.dungeonstory.form.RegionForm;
import com.dungeonstory.samples.backend.data.Region;
import com.dungeonstory.util.VerticalSpacedLayout;
import com.dungeonstory.util.ViewConfig;
import com.dungeonstory.view.component.RegionGrid;
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

	private Label titre;
	private RegionForm form;
	private RegionGrid grid;
	
	public RegionView() {
		
		form = new RegionForm();
		grid = new RegionGrid(Region.class);
		titre = new Label(form.toString());
		
		Button addNew = new Button("", FontAwesome.PLUS);
//	    Button delete = new Button("", FontAwesome.TRASH_O);
	    
	    addNew.addClickListener(this::addNew);
//	    delete.addClickListener(this::deleteSelected);
	    HorizontalLayout boutonLayout = new HorizontalLayout(addNew);
	    
	    form.setEntity(null);
	    
	    //ajout handlers pour boutons
        form.setSavedHandler(this::entrySaved);
        form.setResetHandler(this::entryReset);
        form.setDeleteHandler(this::deleteSelected);
        form.setSaveCaption("Enregistrer");
        form.setCancelCaption("Annuler");
        form.setDeleteCaption("Supprimer");
        
        grid.addSelectionListener(selectionEvent -> {entrySelected();});
        
        addComponents(titre, boutonLayout, form, grid);
	}
	
	public void entrySaved(Region region) {
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
//    	form.getDeleteButton().setVisible(true);
    }
    
    private void addNew(Button.ClickEvent e) {
    	form.setEntity(new Region());
//    	form.getDeleteButton().setVisible(false);
    }

//    private void deleteSelected(Button.ClickEvent e) {
//    	Region bien = grid.getSelectedRow();
//		grid.remove(bien);
//    	form.setEntity(null);
//    }
    
    private void deleteSelected(Region region) {
		grid.remove(region);
    	form.setEntity(null);
    }

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO : set le data dans le container de la grid

	}

}
