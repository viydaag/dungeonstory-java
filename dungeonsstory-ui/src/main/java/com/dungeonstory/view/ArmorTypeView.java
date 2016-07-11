package com.dungeonstory.view;

import com.dungeonstory.backend.DataService;
import com.dungeonstory.backend.data.ArmorType;
import com.dungeonstory.form.ArmorTypeForm;
import com.dungeonstory.util.VerticalSpacedLayout;
import com.dungeonstory.util.ViewConfig;
import com.dungeonstory.view.component.ArmorTypeGrid;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

@ViewConfig(uri = "armorTypes", displayName = "Types d'armure")
public class ArmorTypeView extends VerticalSpacedLayout implements View {

    private static final long serialVersionUID = 4239959044896030062L;
    
    private Label titre;
	private ArmorTypeForm form;
	private ArmorTypeGrid grid;
	
	public ArmorTypeView() {
		
		form = new ArmorTypeForm();
		grid = new ArmorTypeGrid();
		titre = new Label(form.toString());
		
		Button addNew = new Button("", FontAwesome.PLUS);
	    
	    addNew.addClickListener(this::addNew);
	    HorizontalLayout boutonLayout = new HorizontalLayout(addNew);
	    
	    form.setEntity(null);
	    
	    //ajout handlers pour boutons
        form.setSavedHandler(this::entrySaved);
        form.setResetHandler(this::entryReset);
        form.setDeleteHandler(this::deleteSelected);
        
        grid.addSelectionListener(selectionEvent -> {entrySelected();});
        
        addComponents(titre, boutonLayout, form, grid);
	}
	
	public void entrySaved(ArmorType armorType) {
    	grid.refresh(armorType);
    	form.setEntity(null);
    	grid.scrollTo(armorType);
    	
    	Notification.show("Saved!", Type.HUMANIZED_MESSAGE);
    }
    
    public void entryReset(ArmorType armorType) {
    	form.getFieldGroup().discard();
    }
    
    public void entrySelected() {
    	form.setEntity(grid.getSelectedRow() == null ? new ArmorType() : grid.getSelectedRow());
    	form.focusFirst();
    }
    
    private void addNew(Button.ClickEvent e) {
    	form.setEntity(new ArmorType());
    }

    private void deleteSelected(ArmorType armorType) {
		grid.remove(armorType);
    	form.setEntity(null);
    }

	@Override
	public void enter(ViewChangeEvent event) {
//		grid.setData(DataService.get().getAllArmorTypes());
	}

}
