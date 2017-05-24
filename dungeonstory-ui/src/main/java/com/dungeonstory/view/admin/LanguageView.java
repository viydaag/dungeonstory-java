package com.dungeonstory.view.admin;

import com.dungeonstory.backend.data.Language;
import com.dungeonstory.backend.service.LanguageDataService;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.form.DSAbstractForm;
import com.dungeonstory.form.LanguageForm;
import com.dungeonstory.util.ViewConfig;
import com.dungeonstory.view.AbstractCrudView;
import com.dungeonstory.view.grid.DSGrid;
import com.dungeonstory.view.grid.LanguageGrid;

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
