package com.dungeonstory.view.admin;

import org.apache.commons.codec.digest.DigestUtils;
import org.vaadin.dialogs.ConfirmDialog;
import org.vaadin.viritin.ListContainer;

import com.dungeonstory.backend.Configuration;
import com.dungeonstory.backend.data.User;
import com.dungeonstory.backend.service.DataService;
import com.dungeonstory.backend.service.impl.UserService;
import com.dungeonstory.backend.service.mock.MockUserService;
import com.dungeonstory.util.ViewConfig;
import com.dungeonstory.util.layout.VerticalSpacedLayout;
import com.dungeonstory.view.grid.UserGrid;
import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.FieldGroup.CommitEvent;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.fieldgroup.FieldGroup.CommitHandler;
import com.vaadin.data.util.BeanItem;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Grid.Column;
import com.vaadin.ui.Label;
import com.vaadin.ui.renderers.ButtonRenderer;

@ViewConfig(uri = "users", displayName = "Utilisateurs")
public class UserListView extends VerticalSpacedLayout implements View {

    private static final long serialVersionUID = 1085138977601539109L;

    private Label    titre;
    private UserGrid grid;

    private DataService<User, Long> service;

    public UserListView() {
        grid = new UserGrid();
        titre = new Label("Utilisateurs");
        if (Configuration.getInstance().isMock()) {
            service = MockUserService.getInstance();
        } else {
            service = UserService.getInstance();
        }

        grid.getEditorFieldGroup().addCommitHandler(new CommitHandler() {

            private static final long serialVersionUID = 8024962379061308819L;

            @Override
            public void preCommit(CommitEvent commitEvent) throws CommitException {
                //nothing
            }

            @SuppressWarnings("unchecked")
            @Override
            public void postCommit(CommitEvent commitEvent) throws CommitException {
                //only after the data is available in the grid, the bean can be persisted
                Item itemDataSource = commitEvent.getFieldBinder().getItemDataSource();
                if (itemDataSource instanceof ListContainer.DynaBeanItem) {
                    ListContainer<User>.DynaBeanItem<User> dynaBeanItem = (ListContainer<User>.DynaBeanItem<User>) itemDataSource;
                    User bean = dynaBeanItem.getBean();
                    service.saveOrUpdate(bean);
                } else if (itemDataSource instanceof BeanItem) {
                    BeanItem<User> item = (BeanItem<User>) itemDataSource;
                    service.saveOrUpdate(item.getBean());
                } else {
                    //here we have a GeneratedPropertyItem but we cannot retrieve the bean 
                    //and the fieldgroup does not have the RowRefreshListener from MGrid
                    User item = (User) grid.getEditedItemId();
                    service.saveOrUpdate(item);
                    grid.refreshRow(item);
                }
            }
        });

        Column resetColumn = grid.getColumn("resetPassword");
        resetColumn.setRenderer(new ButtonRenderer(e -> {
            ConfirmDialog.show(getUI(), "Réinitiliser mot de passe", "Êtes-vous certain?", "OK", "Annuler",
                    new Runnable() {
                        @Override
                        public void run() {
                            User user = (User) e.getItemId();
                            user.setPassword(DigestUtils.md5Hex(user.getUsername()));
                            service.update(user);
                        }
                    });

        }));

        addComponents(titre, grid);
    }

    @Override
    public void enter(ViewChangeEvent event) {
        grid.setRows(service.findAll());
    }

}
