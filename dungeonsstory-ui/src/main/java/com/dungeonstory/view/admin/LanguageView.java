package com.dungeonstory.view.admin;

import com.dungeonstory.backend.Configuration;
import com.dungeonstory.backend.data.Language;
import com.dungeonstory.backend.service.DataService;
import com.dungeonstory.backend.service.impl.LanguageService;
import com.dungeonstory.backend.service.mock.MockLanguageService;
import com.dungeonstory.form.DSAbstractForm;
import com.dungeonstory.form.LanguageForm;
import com.dungeonstory.util.ViewConfig;
import com.dungeonstory.view.AbstractCrudView;
import com.dungeonstory.view.component.BeanGrid;
import com.dungeonstory.view.component.LanguageGrid;

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
    public BeanGrid<Language> getGrid() {
        return new LanguageGrid();
    }

    @Override
    public DataService<Language, Long> getDataService() {
        if (Configuration.getInstance().isMock()) {
            return MockLanguageService.getInstance();
        }
        return LanguageService.getInstance();
    }

}
