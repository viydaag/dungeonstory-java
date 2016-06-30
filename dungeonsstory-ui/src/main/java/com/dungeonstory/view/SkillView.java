package com.dungeonstory.view;

import com.dungeonstory.form.RegionForm;
import com.dungeonstory.samples.backend.DataService;
import com.dungeonstory.samples.backend.data.Region;
import com.dungeonstory.samples.backend.data.Skill;
import com.dungeonstory.util.VerticalSpacedLayout;
import com.dungeonstory.util.ViewConfig;
import com.dungeonstory.view.component.RegionGrid;
import com.dungeonstory.view.component.SkillGrid;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

@ViewConfig(uri = "skills", displayName = "Talents")
public class SkillView extends VerticalSpacedLayout implements View {

	private static final long serialVersionUID = -7630758272011003929L;

	private Label titre;
//	private RegionForm form;
	private SkillGrid grid;
	
	public SkillView() {
//		form = new RegionForm();
		grid = new SkillGrid(Skill.class);
		titre = new Label("Talents");
		
//		Button addNew = new Button("", FontAwesome.PLUS);
////	    Button delete = new Button("", FontAwesome.TRASH_O);
//	    
//	    addNew.addClickListener(this::addNew);
////	    delete.addClickListener(this::deleteSelected);
//	    HorizontalLayout boutonLayout = new HorizontalLayout(addNew);
//	    
//	    form.setEntity(null);
//	    
//	    //ajout handlers pour boutons
//        form.setSavedHandler(this::entrySaved);
//        form.setResetHandler(this::entryReset);
//        form.setDeleteHandler(this::deleteSelected);
//        form.setSaveCaption("Enregistrer");
//        form.setCancelCaption("Annuler");
//        form.setDeleteCaption("Supprimer");
        
//        grid.addSelectionListener(selectionEvent -> {entrySelected();});
        
//        addComponents(titre, boutonLayout, form, grid);
        addComponents(titre, grid);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		grid.setData(DataService.get().getAllSkills());
	}

}
