package com.dungeonstory.view.admin;

import java.util.ArrayList;
import java.util.List;

import com.dungeonstory.backend.data.DivineDomain;
import com.dungeonstory.backend.data.DivineDomainSpell;
import com.dungeonstory.backend.service.DataService;
import com.dungeonstory.backend.service.impl.DivineDomainService;
import com.dungeonstory.form.DSAbstractForm;
import com.dungeonstory.form.DivineDomainForm;
import com.dungeonstory.util.ViewConfig;
import com.dungeonstory.view.AbstractCrudView;
import com.dungeonstory.view.CrudView;
import com.dungeonstory.view.component.BeanGrid;
import com.dungeonstory.view.component.DivineDomainGrid;

@ViewConfig(uri = "divineDomains", displayName = "Domains divins")
public class DivineDomainView extends AbstractCrudView<DivineDomain> implements CrudView<DivineDomain> {

    private static final long serialVersionUID = 5117755861151432771L;

    public DivineDomainView() {
        super();
    }

    @Override
    public DSAbstractForm<DivineDomain> getForm() {
        return new DivineDomainForm();
    }

    @Override
    public BeanGrid<DivineDomain> getGrid() {
        return new DivineDomainGrid();
    }

    @Override
    public DataService<DivineDomain, Long> getDataService() {
        return DivineDomainService.getInstance();
    }

    @Override
    public void entrySaved(DivineDomain entity) {

        //set class for each nested objects
        List<DivineDomainSpell> spells = new ArrayList<DivineDomainSpell>(entity.getSpells());
        spells.stream().forEach(spell -> spell.setDomain(entity));
        entity.setSpells(spells);

        //nested entities are automatically removed with the annotation @PrivateOwned

        //save to database with nested objects
        super.entrySaved(entity);
    }

}
