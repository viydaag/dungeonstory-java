package com.dungeonstory.view;

import com.dungeonstory.backend.DataService;
import com.dungeonstory.backend.data.Skill;
import com.dungeonstory.form.SkillForm;
import com.dungeonstory.util.VerticalSpacedLayout;
import com.dungeonstory.util.ViewConfig;
import com.dungeonstory.view.component.SkillGrid;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

@ViewConfig(uri = "skills", displayName = "Talents")
public class SkillView extends VerticalSpacedLayout implements View {

	private static final long serialVersionUID = -7630758272011003929L;

	private Label titre;
	private SkillForm form;
	private SkillGrid grid;
	
	public SkillView() {
		form = new SkillForm();
		grid = new SkillGrid();
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
//        addComponents(titre, grid);
	}
	
	public void entrySaved(Skill skill) {
        grid.refresh(skill);
        form.setEntity(null);
        grid.scrollTo(skill);
        
        Notification.show("Saved!", Type.HUMANIZED_MESSAGE);
    }
    
    public void entryReset(Skill skill) {
        form.getFieldGroup().discard();
    }
    
    public void entrySelected() {
        form.setEntity(grid.getSelectedRow() == null ? new Skill() : grid.getSelectedRow());
        form.focusFirst();
//      form.getDeleteButton().setVisible(true);
    }
    
    private void addNew(Button.ClickEvent e) {
        form.setEntity(new Skill());
//      form.getDeleteButton().setVisible(false);
    }

//    private void deleteSelected(Button.ClickEvent e) {
//      Region bien = grid.getSelectedRow();
//      grid.remove(bien);
//      form.setEntity(null);
//    }
    
    private void deleteSelected(Skill skill) {
        grid.remove(skill);
        form.setEntity(null);
    }

	@Override
	public void enter(ViewChangeEvent event) {
		grid.setData(DataService.get().getAllSkills());
	}

}
