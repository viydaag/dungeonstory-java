package com.dungeonstory.view.admin;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.service.DataService;
import com.dungeonstory.backend.service.impl.CharacterService;
import com.dungeonstory.form.DSAbstractForm;
import com.dungeonstory.util.ViewConfig;
import com.dungeonstory.view.AbstractCrudView;
import com.dungeonstory.view.grid.CharacterGrid;
import com.dungeonstory.view.grid.DSGrid;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Grid.Column;
import com.vaadin.ui.renderers.ButtonRenderer;

@ViewConfig(uri = "characters", displayName = "Personnages")
public class CharacterView extends AbstractCrudView<Character> {

    private static final long serialVersionUID = 2774259451633303742L;

    @Override
    public DSAbstractForm<Character> getForm() {
        return null;
    }

    @Override
    public DSGrid<Character> getGrid() {
        return new CharacterGrid();
    }

    @Override
    public DataService<Character, Long> getDataService() {
        return CharacterService.getInstance();
    }

    @Override
    public void enter(ViewChangeEvent event) {
        super.enter(event);
        //        GeneratedPropertyContainer gpc = new GeneratedPropertyContainer(grid.getContainer());
        //        gpc.addGeneratedProperty("delete", new PropertyValueGenerator<String>() {
        //
        //            private static final long serialVersionUID = 6332188902387035064L;
        //
        //            @Override
        //            public String getValue(Item item, Object itemId, Object propertyId) {
        //                return "Delete";
        //            }
        //
        //            @Override
        //            public Class<String> getType() {
        //                return String.class;
        //            }
        //        });
        //        grid.removeAllColumns();
        //        grid.setContainerDataSource(gpc);
        //        grid.withProperties("name", "delete");



        // Render a button that deletes the data row (item)
        Column deleteColumn = grid.getColumn("delete");
        deleteColumn.setRenderer(new ButtonRenderer(e -> {
            deleteSelected((Character) e.getItemId());
        }));

    }

}
