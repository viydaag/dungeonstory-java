package com.dungeonstory.view.grid;

import org.vaadin.viritin.fields.TypedSelect;

import com.dungeonstory.backend.Configuration;
import com.dungeonstory.backend.data.AccessRole;
import com.dungeonstory.backend.data.User;
import com.dungeonstory.backend.service.DataService;
import com.dungeonstory.backend.service.impl.AccessRoleService;
import com.dungeonstory.backend.service.mock.MockAccessRoleService;
import com.vaadin.data.Item;
import com.vaadin.data.util.PropertyValueGenerator;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.ui.TextField;

public class UserGrid extends DSGrid<User> {

    private static final long serialVersionUID = -6906747725928178053L;

    private DataService<AccessRole, Long> roleService;

    public UserGrid() {
        super(User.class);

        if (Configuration.getInstance().isMock()) {
            roleService = MockAccessRoleService.getInstance();
        } else {
            roleService = AccessRoleService.getInstance();
        }

        setEditorEnabled(true);

        setEditorSaveCaption("Enregistrer");
        setEditorCancelCaption("Annuler");

        withGeneratedColumn("resetPassword", new PropertyValueGenerator<String>() {

            private static final long serialVersionUID = 5081980026289479356L;

            @Override
            public String getValue(Item item, Object itemId, Object propertyId) {
                return "Réinitiliser mot de passe";
            }

            @Override
            public Class<String> getType() {
                return String.class;
            }
        });

        withProperties("username", "role", "name", "email", "status", "resetPassword");
        withColumnHeaders("Utilisateur", "Rôle", "Nom", "Courriel", "Statut", "");

        TypedSelect<AccessRole> roleEditor = new TypedSelect<AccessRole>().asComboBoxType();
        roleEditor.setBeans(roleService.findAll());

        TextField emailEditor = new TextField();
        emailEditor.addValidator(new RegexpValidator("[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}$",
                "doit suivre le pattern \"aaaa@domaine.xxx\""));

        getColumn("email").setEditorField(emailEditor);
        getColumn("role").setEditorField(roleEditor);
        getColumn("username").setEditable(false);
        getColumn("name").setEditable(false);
        getColumn("resetPassword").setEditable(false);
    }
}
