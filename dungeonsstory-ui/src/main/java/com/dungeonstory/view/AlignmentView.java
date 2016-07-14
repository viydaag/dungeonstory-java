package com.dungeonstory.view;

import com.dungeonstory.backend.DataService;
import com.dungeonstory.backend.data.Alignment;
import com.dungeonstory.form.AlignmentForm;
import com.dungeonstory.util.VerticalSpacedLayout;
import com.dungeonstory.util.ViewConfig;
import com.dungeonstory.view.component.AlignmentGrid;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

@ViewConfig(uri = "alignments", displayName = "Alignements")
public class AlignmentView extends VerticalSpacedLayout implements View {

    private static final long serialVersionUID = 4239959044896030062L;
    
    private Label titre;
	private AlignmentForm form;
	private AlignmentGrid grid;
	
	public AlignmentView() {
		
		form = new AlignmentForm();
		grid = new AlignmentGrid();
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
	
	public void entrySaved(Alignment alignment) {
    	grid.refresh(alignment);
    	form.setEntity(null);
    	grid.scrollTo(alignment);
    	
    	Notification.show("Saved!", Type.HUMANIZED_MESSAGE);
    }
    
    public void entryReset(Alignment alignment) {
    	form.getFieldGroup().discard();
    }
    
    public void entrySelected() {
    	form.setEntity(grid.getSelectedRow() == null ? new Alignment() : grid.getSelectedRow());
    	form.focusFirst();
    }
    
    private void addNew(Button.ClickEvent e) {
    	form.setEntity(new Alignment());
    }

    private void deleteSelected(Alignment alignment) {
		grid.remove(alignment);
    	form.setEntity(null);
    }

	@Override
	public void enter(ViewChangeEvent event) {
		grid.setData(DataService.get().getAllAlignments());
	}

}
