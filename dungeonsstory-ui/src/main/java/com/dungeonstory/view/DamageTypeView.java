package com.dungeonstory.view;

import com.dungeonstory.backend.DataService;
import com.dungeonstory.backend.data.DamageType;
import com.dungeonstory.form.DamageTypeForm;
import com.dungeonstory.util.VerticalSpacedLayout;
import com.dungeonstory.util.ViewConfig;
import com.dungeonstory.view.component.DamageTypeGrid;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

@ViewConfig(uri = "damageTypes", displayName = "Types de dommages")
public class DamageTypeView extends VerticalSpacedLayout implements View {

    private static final long serialVersionUID = 2197369530490492330L;
    
    private Label titre;
	private DamageTypeForm form;
	private DamageTypeGrid grid;
	
	public DamageTypeView() {
		
		form = new DamageTypeForm();
		grid = new DamageTypeGrid();
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
	
	public void entrySaved(DamageType damageType) {
    	grid.refresh(damageType);
    	form.setEntity(null);
    	grid.scrollTo(damageType);
    	
    	Notification.show("Saved!", Type.HUMANIZED_MESSAGE);
    }
    
    public void entryReset(DamageType damageType) {
    	form.getFieldGroup().discard();
    }
    
    public void entrySelected() {
    	form.setEntity(grid.getSelectedRow() == null ? new DamageType() : grid.getSelectedRow());
    	form.focusFirst();
    }
    
    private void addNew(Button.ClickEvent e) {
    	form.setEntity(new DamageType());
    }

    private void deleteSelected(DamageType damageType) {
		grid.remove(damageType);
    	form.setEntity(null);
    }

	@Override
	public void enter(ViewChangeEvent event) {
		grid.setData(DataService.get().getAllDamageTypes());
	}

}
