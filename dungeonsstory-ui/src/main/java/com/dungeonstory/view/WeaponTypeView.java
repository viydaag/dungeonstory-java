package com.dungeonstory.view;

import com.dungeonstory.backend.DataService;
import com.dungeonstory.backend.data.WeaponType;
import com.dungeonstory.form.WeaponTypeForm;
import com.dungeonstory.util.VerticalSpacedLayout;
import com.dungeonstory.util.ViewConfig;
import com.dungeonstory.view.component.WeaponTypeGrid;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

@ViewConfig(uri = "weaponTypes", displayName = "Types d'arme")
public class WeaponTypeView extends VerticalSpacedLayout implements View {

    private static final long serialVersionUID = 4239959044896030062L;
    
    private Label titre;
	private WeaponTypeForm form;
	private WeaponTypeGrid grid;
	
	public WeaponTypeView() {
		
		form = new WeaponTypeForm();
		grid = new WeaponTypeGrid();
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
	
	public void entrySaved(WeaponType weaponType) {
    	grid.refresh(weaponType);
    	form.setEntity(null);
    	grid.scrollTo(weaponType);
    	
    	Notification.show("Saved!", Type.HUMANIZED_MESSAGE);
    }
    
    public void entryReset(WeaponType weaponType) {
    	form.getFieldGroup().discard();
    }
    
    public void entrySelected() {
    	form.setEntity(grid.getSelectedRow() == null ? new WeaponType() : grid.getSelectedRow());
    	form.focusFirst();
    }
    
    private void addNew(Button.ClickEvent e) {
    	form.setEntity(new WeaponType());
    }

    private void deleteSelected(WeaponType weaponType) {
		grid.remove(weaponType);
    	form.setEntity(null);
    }

	@Override
	public void enter(ViewChangeEvent event) {
//		grid.setData(DataService.get().getAllWeaponTypes());
	}

}
