package com.dungeonstory.view;

import com.dungeonstory.backend.data.DSClass;
import com.dungeonstory.backend.service.DataService;
import com.dungeonstory.backend.service.impl.ClassService;
import com.dungeonstory.backend.service.mock.MockClassService;
import com.dungeonstory.form.ClassForm;
import com.dungeonstory.form.DSAbstractForm;
import com.dungeonstory.samples.crud.BeanGrid;
import com.dungeonstory.util.ViewConfig;
import com.dungeonstory.view.component.ClassGrid;

@ViewConfig(uri = "classes", displayName = "Classes")
public class ClassView extends AbstractCrudView<DSClass> implements CrudView<DSClass> {

	private static final long serialVersionUID = 5117755861151432771L;
	
	public ClassView() {
	    super();
        setFormPopup(true);
    }

    @Override
    public DSAbstractForm<DSClass> getForm() {
        return new ClassForm();
    }

    @Override
    public BeanGrid<DSClass> getGrid() {
        return new ClassGrid();
    }

    @Override
    public DataService<DSClass, Long> getDataService() {
        return MockClassService.getInstance();
    }

}
