package com.dungeonstory.view.admin;

import com.dungeonstory.backend.data.Level;
import com.dungeonstory.backend.service.impl.LevelService;
import com.dungeonstory.util.VerticalSpacedLayout;
import com.dungeonstory.util.ViewConfig;
import com.dungeonstory.view.component.LevelGrid;
import com.vaadin.data.fieldgroup.FieldGroup.CommitEvent;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.fieldgroup.FieldGroup.CommitHandler;
import com.vaadin.data.util.BeanItem;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

@ViewConfig(uri = "levels", displayName = "Niveaux")
public class LevelView extends VerticalSpacedLayout implements View {

	private static final long serialVersionUID = 1085138977601539109L;
	private Label titre;
	private LevelGrid grid;
	
	private LevelService service;
	
	public LevelView() {
		grid = new LevelGrid();
		titre = new Label("Niveaux");
		service = LevelService.getInstance();
		
		Button addNew = new Button("", FontAwesome.PLUS);
	    
	    addNew.addClickListener(this::addNew);
	    HorizontalLayout boutonLayout = new HorizontalLayout(addNew);
	    
	    grid.getEditorFieldGroup().addCommitHandler(new CommitHandler() {
            
            private static final long serialVersionUID = 8024962379061308819L;

            @Override
            public void preCommit(CommitEvent commitEvent) throws CommitException {
                //nothing
            }
            
            @SuppressWarnings("unchecked")
            @Override
            public void postCommit(CommitEvent commitEvent) throws CommitException {
                //only after the data is available in the grid, the beau can be persisted
                BeanItem<Level> levelItem = (BeanItem<Level>) commitEvent.getFieldBinder().getItemDataSource();
                service.saveOrUpdate(levelItem.getBean());
            }
        });
	    
	    addComponents(titre, boutonLayout, grid);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		grid.setData(service.findAll());
	}
	
	private void addNew(Button.ClickEvent e) {
	    Level level = service.create();
    	grid.addRow(level);
    	service.create(level);
    }

}
