package com.dungeonstory;

import com.dungeonstory.authentication.CurrentUser;
import com.dungeonstory.event.ViewRemovedEvent;
import com.dungeonstory.i18n.MessageViewUpdater;
import com.dungeonstory.util.DSTheme;
import com.dungeonstory.util.LazyProvider;
import com.dungeonstory.util.PageTitleUpdater;
import com.dungeonstory.util.ViewConfig;
import com.dungeonstory.util.ViewConfigUtil;
import com.dungeonstory.util.layout.VerticalSpacedLayout;
import com.dungeonstory.view.AboutView;
import com.dungeonstory.view.ErrorView;
import com.dungeonstory.view.HomeView;
import com.dungeonstory.view.SourceView;
import com.dungeonstory.view.admin.AbilityView;
import com.dungeonstory.view.admin.AlignmentView;
import com.dungeonstory.view.admin.ArmorTypeView;
import com.dungeonstory.view.admin.BackgroundView;
import com.dungeonstory.view.admin.CharacterListView;
import com.dungeonstory.view.admin.CityView;
import com.dungeonstory.view.admin.ClassSpecializationView;
import com.dungeonstory.view.admin.ClassView;
import com.dungeonstory.view.admin.CreatureTypeView;
import com.dungeonstory.view.admin.DamageTypeView;
import com.dungeonstory.view.admin.DeityView;
import com.dungeonstory.view.admin.DivineDomainView;
import com.dungeonstory.view.admin.EquipmentView;
import com.dungeonstory.view.admin.FeatView;
import com.dungeonstory.view.admin.LanguageView;
import com.dungeonstory.view.admin.LevelView;
import com.dungeonstory.view.admin.RaceView;
import com.dungeonstory.view.admin.RegionView;
import com.dungeonstory.view.admin.ShopView;
import com.dungeonstory.view.admin.SkillView;
import com.dungeonstory.view.admin.SpellView;
import com.dungeonstory.view.admin.UserListView;
import com.dungeonstory.view.admin.WeaponTypeView;
import com.dungeonstory.view.adventure.AdventureListView;
import com.dungeonstory.view.adventure.AdventureView;
import com.dungeonstory.view.character.CharacterView;
import com.dungeonstory.view.character.NewCharacterView;
import com.dungeonstory.view.shop.ShopListView;
import com.dungeonstory.view.user.UserView;
import com.google.common.eventbus.Subscribe;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.VerticalLayout;

/**
 * Content of the UI when the user is logged in.
 * 
 * 
 */
public class MainScreen extends HorizontalLayout {

    private static final long serialVersionUID = -6331656609834870730L;

    private VerticalLayout viewContainer;
    private NavBar         navBar;
    private Navigator      navigator;
    private MenuBar        bar;

    public MainScreen(DungeonStoryUI ui) {

        setStyleName(DSTheme.MAIN_LAYOUT);

        setSizeFull();

        initLayout();
        setupNavigator();

        DungeonStoryUI.getEventBus().register(this);
    }

    private void initLayout() {
        navBar = new NavBar();

        viewContainer = new VerticalSpacedLayout();
        viewContainer.addStyleName(DSTheme.VALO_CONTENT);
        viewContainer.addStyleName(DSTheme.CRUD_VIEW);
        viewContainer.setSizeFull();

//                DMenu menu = new DMenu();
//                menu.setCenteredTabs(true);
//                menu.setFloatingMenu(true);
//                menu.setFloatingMenuItemAutoShow(true);
//                menu.setMenuItemsSameHeight(true);
//                menu.setMenuItemElementsSameHeight(true);
//                org.dussan.vaadin.dmenu.menuitem.MenuItem item1 = new org.dussan.vaadin.dmenu.menuitem.MenuItem( "TAB 1" );
//                MenuItemElement itemElement1 = new MenuItemElement( "ELEMENT 1", new Label( "LABEL 1" ) );
//                item1.addMenuItemElement( itemElement1 );
//                menu.addMenuItem( item1 );

        bar = new MenuBar();

        MenuBar.Command userCommand = new MenuBar.Command() {

            private static final long serialVersionUID = -3306939299168985290L;

            @Override
            public void menuSelected(MenuItem selectedItem) {
                navigator.navigateTo(UserView.USER_URI);
            }
        };

        MenuItem userItem = bar.addItem("Utilisateur", FontAwesome.USER, userCommand);


        VerticalLayout rightLayout = new VerticalLayout();
//                rightLayout.addComponent(menu);
        rightLayout.addComponent(bar);
        rightLayout.addComponent(viewContainer);

        rightLayout.setSizeFull();
        rightLayout.setExpandRatio(viewContainer, 1);
        rightLayout.addStyleName(DSTheme.SCROLLABLE);

        //        Panel panel = new Panel(rightLayout);

        addComponents(navBar, rightLayout);
        setExpandRatio(rightLayout, 1);
    }

    private void setupNavigator() {
        navigator = new Navigator(DungeonStoryUI.getCurrent(), viewContainer);

        registerViews();

        // Add view change listeners so we can do things like select the correct menu item and update the page title
        navigator.addViewChangeListener(navBar);
        navigator.addViewChangeListener(new PageTitleUpdater());
        navigator.addViewChangeListener(new MessageViewUpdater());

        navigator.setErrorView(ErrorView.class);
        navigator.navigateTo(navigator.getState());
    }

    private void registerViews() {
        addView(HomeView.class);

        if (CurrentUser.get().getCharacter() == null) {
            addView(NewCharacterView.class);
        } else {
            addViewToMenuBar(CharacterView.class);
            addViewToMenuBar(ShopListView.class);
            addViewNoNavBar(com.dungeonstory.view.shop.ShopView.class);
        }
        
//        if (CurrentUser.get().getAdventure() == null) {
        addViewToMenuBar(AdventureListView.class);
//        } else {
        addViewNoNavBar(AdventureView.class);

        if (DungeonStoryUI.get().getAccessControl().isUserInRole("ADMIN")) {
            addView(UserListView.class);
            addView(CharacterListView.class);
            addView(AbilityView.class);
            addView(AlignmentView.class);
            addView(DamageTypeView.class);
            addView(RegionView.class);
            addView(CityView.class);
            addView(LevelView.class);
            addView(SkillView.class);
            addView(FeatView.class);
            addView(LanguageView.class);
            addView(RaceView.class);
            addView(SpellView.class);
            addView(ClassView.class);
            addView(ClassSpecializationView.class);
            addView(WeaponTypeView.class);
            addView(ArmorTypeView.class);
            addView(ShopView.class);
            addView(EquipmentView.class);
            addView(DivineDomainView.class);
            addView(DeityView.class);
            addView(BackgroundView.class);
            addView(CreatureTypeView.class);
        }

        addViewNoNavBar(AboutView.class);
        addViewNoNavBar(SourceView.class);
        addViewNoNavBar(UserView.class);
    }

    /**
     * Registers a given view to the navigator and adds it to the NavBar
     */
    private void addView(Class<? extends View> viewClass) {
        ViewConfig viewConfig = viewClass.getAnnotation(ViewConfig.class);

        if (viewConfig == null) {
            System.out.println("ViewConfig est absent pour la vue " + viewClass.getSimpleName());
        } else {
            switch (viewConfig.createMode()) {
                case ALWAYS_NEW:
                    navigator.addView(viewConfig.uri(), viewClass);
                    break;
                case LAZY_INIT:
                    navigator.addProvider(new LazyProvider(viewConfig.uri(), viewClass));
                    break;
                case EAGER_INIT:
                    try {
                        navigator.addView(viewConfig.uri(), viewClass.newInstance());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            }
            navBar.addView(viewConfig.uri(), ViewConfigUtil.getDisplayName(viewConfig.displayName()));
        }
    }

    /**
     * Registers a given view to the navigator.
     */
    private void addViewNoNavBar(Class<? extends View> viewClass) {
        ViewConfig viewConfig = viewClass.getAnnotation(ViewConfig.class);

        if (viewConfig == null) {
            System.out.println("ViewConfig est absent pour la vue " + viewClass.getSimpleName());
        } else {
            switch (viewConfig.createMode()) {
                case ALWAYS_NEW:
                    navigator.addView(viewConfig.uri(), viewClass);
                    break;
                case LAZY_INIT:
                    navigator.addProvider(new LazyProvider(viewConfig.uri(), viewClass));
                    break;
                case EAGER_INIT:
                    try {
                        navigator.addView(viewConfig.uri(), viewClass.newInstance());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            }
        }
    }
    
    /**
     * Registers a given view to the navigator.
     */
    private void addViewToMenuBar(Class<? extends View> viewClass) {
        ViewConfig viewConfig = viewClass.getAnnotation(ViewConfig.class);

        if (viewConfig == null) {
            System.out.println("ViewConfig est absent pour la vue " + viewClass.getSimpleName());
        } else {
            switch (viewConfig.createMode()) {
                case ALWAYS_NEW:
                    navigator.addView(viewConfig.uri(), viewClass);
                    break;
                case LAZY_INIT:
                    navigator.addProvider(new LazyProvider(viewConfig.uri(), viewClass));
                    break;
                case EAGER_INIT:
                    try {
                        navigator.addView(viewConfig.uri(), viewClass.newInstance());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            }
            bar.addItem(ViewConfigUtil.getDisplayName(viewConfig.displayName()), null, command -> navigator.navigateTo(viewConfig.uri()));
        }
    }

    @Subscribe
    public void viewRemoved(ViewRemovedEvent event) {
        navigator.removeView(event.getViewName());
        navBar.removeView(event.getViewName());
    }

}
