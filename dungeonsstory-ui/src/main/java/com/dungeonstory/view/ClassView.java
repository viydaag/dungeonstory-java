package com.dungeonstory.view;

import com.dungeonstory.backend.data.DSClass;
import com.dungeonstory.form.ClassForm;
import com.dungeonstory.util.VerticalSpacedLayout;
import com.dungeonstory.util.ViewConfig;
import com.dungeonstory.view.component.ClassGrid;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

@ViewConfig(uri = "classes", displayName = "Classes")
public class ClassView extends VerticalSpacedLayout implements View {

	private static final long serialVersionUID = 5117755861151432771L;

	private Label titre;
	private ClassForm form;
	private ClassGrid grid;
	
	public ClassView() {
	    
		form = new ClassForm();
		grid = new ClassGrid(DSClass.class);
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
	
	public void entrySaved(DSClass region) {
    	grid.refresh(region);
    	form.setEntity(null);
    	grid.scrollTo(region);
    	
    	Notification.show("Saved!", Type.HUMANIZED_MESSAGE);
    }
    
    public void entryReset(DSClass region) {
    	form.getFieldGroup().discard();
    }
    
    public void entrySelected() {
    	form.setEntity(grid.getSelectedRow() == null ? new DSClass() : grid.getSelectedRow());
    	form.focusFirst();
//    	form.getDeleteButton().setVisible(true);
    }
    
    private void addNew(Button.ClickEvent e) {
    	form.setEntity(new DSClass());
//    	form.getDeleteButton().setVisible(false);
    }

//    private void deleteSelected(Button.ClickEvent e) {
//    	Class bien = grid.getSelectedRow();
//		grid.remove(bien);
//    	form.setEntity(null);
//    }
    
    private void deleteSelected(DSClass region) {
		grid.remove(region);
    	form.setEntity(null);
    }

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO : set le data dans le container de la grid

	}

}
