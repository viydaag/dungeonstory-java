package com.dungeonstory.view.admin;

import com.dungeonstory.backend.Configuration;
import com.dungeonstory.backend.data.Level;
import com.dungeonstory.backend.service.DataService;
import com.dungeonstory.backend.service.impl.LevelService;
import com.dungeonstory.backend.service.mock.MockLevelService;
import com.dungeonstory.util.ViewConfig;
import com.dungeonstory.view.grid.LevelGrid;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.grid.EditorSaveEvent;
import com.vaadin.ui.components.grid.EditorSaveListener;

@ViewConfig(uri = "levels", displayName = "Niveaux")
public class LevelView extends VerticalLayout implements View {

    private static final long serialVersionUID = 1085138977601539109L;
    
    private Label             titre;
    private LevelGrid         grid;

    private DataService<Level, Long> service;

    public LevelView() {
        grid = new LevelGrid();
        titre = new Label("Niveaux");
        if (Configuration.getInstance().isMock()) {
            service = MockLevelService.getInstance();
        } else {
            service = LevelService.getInstance();
        }

        Button addNew = new Button("", VaadinIcons.PLUS);

        addNew.addClickListener(this::addNew);
        HorizontalLayout boutonLayout = new HorizontalLayout(addNew);

        grid.getEditor().addSaveListener(new EditorSaveListener<Level>() {

            private static final long serialVersionUID = 1L;

            @Override
            public void onEditorSave(EditorSaveEvent<Level> event) {
                service.saveOrUpdate(event.getBean());
            }
        });

        addComponents(titre, boutonLayout);
        addComponentsAndExpand(grid);
    }

    @Override
    public void enter(ViewChangeEvent event) {
        grid.setItems(service.findAll());
    }

    private void addNew(Button.ClickEvent e) {
        Level level = service.create();
        service.create(level);
        grid.setItems(service.findAll());
        grid.scrollToEnd();
    }

}
