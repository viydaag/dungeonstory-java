package com.dungeonstory.ui.view.admin;

import com.dungeonstory.backend.data.Language;
import com.dungeonstory.backend.service.LanguageDataService;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.ui.component.DSAbstractForm;
import com.dungeonstory.ui.util.ViewConfig;
import com.dungeonstory.ui.view.AbstractCrudView;
import com.dungeonstory.ui.view.admin.form.LanguageForm;
import com.dungeonstory.ui.view.admin.grid.DSGrid;
import com.dungeonstory.ui.view.admin.grid.LanguageGrid;

@ViewConfig(uri = "languages", displayName = "Langages")
public class LanguageView extends AbstractCrudView<Language> {

    private static final long serialVersionUID = -1727374101226087197L;

    public LanguageView() {
        super();
    }

    @Override
    public DSAbstractForm<Language> getForm() {
        return new LanguageForm();
    }

    @Override
    public DSGrid<Language> getGrid() {
        return new LanguageGrid();
    }

    @Override
    public LanguageDataService getDataService() {
        return Services.getLanguageService();
    }

}
