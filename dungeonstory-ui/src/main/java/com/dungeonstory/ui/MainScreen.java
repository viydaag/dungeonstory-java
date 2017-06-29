package com.dungeonstory.ui;

import java.util.HashMap;

import com.dungeonstory.backend.data.AccessRole;
import com.dungeonstory.ui.authentication.CurrentUser;
import com.dungeonstory.ui.event.ViewAddedEvent;
import com.dungeonstory.ui.event.ViewRemovedEvent;
import com.dungeonstory.ui.i18n.MessageViewUpdater;
import com.dungeonstory.ui.util.DSTheme;
import com.dungeonstory.ui.util.LazyProvider;
import com.dungeonstory.ui.util.PageTitleUpdater;
import com.dungeonstory.ui.util.ViewConfig;
import com.dungeonstory.ui.util.ViewConfigUtil;
import com.dungeonstory.ui.view.AboutView;
import com.dungeonstory.ui.view.ErrorView;
import com.dungeonstory.ui.view.HomeView;
import com.dungeonstory.ui.view.SourceView;
import com.dungeonstory.ui.view.admin.AbilityView;
import com.dungeonstory.ui.view.admin.AlignmentView;
import com.dungeonstory.ui.view.admin.ArmorTypeView;
import com.dungeonstory.ui.view.admin.BackgroundView;
import com.dungeonstory.ui.view.admin.CharacterListView;
import com.dungeonstory.ui.view.admin.CityView;
import com.dungeonstory.ui.view.admin.ClassFeatureView;
import com.dungeonstory.ui.view.admin.ClassSpecializationView;
import com.dungeonstory.ui.view.admin.ClassView;
import com.dungeonstory.ui.view.admin.CreatureTypeView;
import com.dungeonstory.ui.view.admin.DamageTypeView;
import com.dungeonstory.ui.view.admin.DeityView;
import com.dungeonstory.ui.view.admin.EquipmentView;
import com.dungeonstory.ui.view.admin.FeatView;
import com.dungeonstory.ui.view.admin.InnView;
import com.dungeonstory.ui.view.admin.LanguageView;
import com.dungeonstory.ui.view.admin.LevelView;
import com.dungeonstory.ui.view.admin.MonsterView;
import com.dungeonstory.ui.view.admin.RaceView;
import com.dungeonstory.ui.view.admin.RegionView;
import com.dungeonstory.ui.view.admin.ShopView;
import com.dungeonstory.ui.view.admin.SkillView;
import com.dungeonstory.ui.view.admin.SpellView;
import com.dungeonstory.ui.view.admin.TempleView;
import com.dungeonstory.ui.view.admin.UserListView;
import com.dungeonstory.ui.view.admin.WeaponTypeView;
import com.dungeonstory.ui.view.adventure.AdventureListView;
import com.dungeonstory.ui.view.adventure.AdventureView;
import com.dungeonstory.ui.view.character.CharacterView;
import com.dungeonstory.ui.view.character.NewCharacterView;
import com.dungeonstory.ui.view.combat.PlayerVsMonsterListView;
import com.dungeonstory.ui.view.shop.ShopListView;
import com.dungeonstory.ui.view.user.UserView;
import com.google.common.eventbus.Subscribe;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.server.Resource;
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

    private static final long         serialVersionUID = -6331656609834870730L;

    private VerticalLayout            viewContainer;
    private NavBar                    navBar;
    private Navigator                 navigator;
    private MenuBar                   menuBar;
    private HashMap<String, MenuItem> menuItems;

    public MainScreen(DungeonStoryUI ui) {

        setStyleName(DSTheme.MAIN_LAYOUT);

        setSizeFull();

        initLayout();
        setupNavigator();

        DungeonStoryUI.getEventBus().register(this);
    }

    private void initLayout() {
        navBar = new NavBar();

        viewContainer = new VerticalLayout();
        viewContainer.addStyleName(DSTheme.VALO_CONTENT);
        viewContainer.addStyleName(DSTheme.CRUD_VIEW);
        //        viewContainer.setSizeFull();

        // DMenu menu = new DMenu();
        // menu.setCenteredTabs(true);
        // menu.setFloatingMenu(true);
        // menu.setFloatingMenuItemAutoShow(true);
        // menu.setMenuItemsSameHeight(true);
        // menu.setMenuItemElementsSameHeight(true);
        // org.dussan.vaadin.dmenu.menuitem.MenuItem item1 = new
        // org.dussan.vaadin.dmenu.menuitem.MenuItem( "TAB 1" );
        // MenuItemElement itemElement1 = new MenuItemElement( "ELEMENT 1", new
        // Label( "LABEL 1" ) );
        // item1.addMenuItemElement( itemElement1 );
        // menu.addMenuItem( item1 );

        menuBar = new MenuBar();
        menuItems = new HashMap<>();

        VerticalLayout rightLayout = new VerticalLayout();
        // rightLayout.addComponent(menu);
        rightLayout.addComponent(menuBar);
        rightLayout.addComponent(viewContainer);

        rightLayout.setSizeFull();
        rightLayout.setExpandRatio(viewContainer, 1);
        rightLayout.addStyleName(DSTheme.SCROLLABLE);

        // Panel panel = new Panel(rightLayout);

        addComponents(navBar, rightLayout);
        setExpandRatio(rightLayout, 1);
    }

    private void setupNavigator() {
        navigator = new Navigator(DungeonStoryUI.getCurrent(), viewContainer);

        registerViews();

        // Add view change listeners so we can do things like select the correct
        // menu item and update the page title
        navigator.addViewChangeListener(navBar);
        navigator.addViewChangeListener(new PageTitleUpdater());
        navigator.addViewChangeListener(new MessageViewUpdater());

        navigator.setErrorView(ErrorView.class);
        navigator.navigateTo(navigator.getState());
    }

    private void registerViews() {
        addViewToNavBar(HomeView.class);

        if (CurrentUser.get().getCharacter() == null) {
            addViewToNavBar(NewCharacterView.class);
        } else {
            addViewToMenuBar(CharacterView.class);
            addViewToMenuBar(ShopListView.class);
            addView(com.dungeonstory.ui.view.shop.ShopView.class);
        }

        addViewToMenuBar(UserView.class);
        addViewToMenuBar(AdventureListView.class);
        addView(AdventureView.class);
        if (CurrentUser.get().getAdventure() != null) {
            menuBar.addItem("Mon aventure", null, command -> navigator
                    .navigateTo(AdventureView.URI + "/" + CurrentUser.get().getAdventure().getId()));
        }
        addViewToMenuBar(PlayerVsMonsterListView.class);

        if (DungeonStoryUI.get().getAccessControl().isUserInRole(AccessRole.ADMIN)) {
            addViewToNavBar(UserListView.class);
            addViewToNavBar(CharacterListView.class);
            addViewToNavBar(AbilityView.class);
            addViewToNavBar(AlignmentView.class);
            addViewToNavBar(DamageTypeView.class);
            addViewToNavBar(RegionView.class);
            addViewToNavBar(CityView.class);
            addViewToNavBar(LevelView.class);
            addViewToNavBar(SkillView.class);
            addViewToNavBar(FeatView.class);
            addViewToNavBar(ClassFeatureView.class);
            addViewToNavBar(LanguageView.class);
            addViewToNavBar(RaceView.class);
            addViewToNavBar(SpellView.class);
            addViewToNavBar(ClassView.class);
            addViewToNavBar(ClassSpecializationView.class);
            addViewToNavBar(WeaponTypeView.class);
            addViewToNavBar(ArmorTypeView.class);
            addViewToNavBar(ShopView.class);
            addViewToNavBar(EquipmentView.class);
            addViewToNavBar(DeityView.class);
            addViewToNavBar(BackgroundView.class);
            addViewToNavBar(CreatureTypeView.class);
            addViewToNavBar(MonsterView.class);
            addViewToNavBar(InnView.class);
            addViewToNavBar(TempleView.class);
        }

        addView(AboutView.class);
        addView(SourceView.class);

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

        }
    }

    private void addViewToNavBar(Class<? extends View> viewClass, String caption) {
        ViewConfig viewConfig = viewClass.getAnnotation(ViewConfig.class);
        addView(viewClass);
        navBar.addView(viewConfig.uri(),
                caption == null ? ViewConfigUtil.getDisplayName(viewConfig.displayName()) : caption);
    }

    private void addViewToNavBar(Class<? extends View> viewClass) {
        addViewToNavBar(viewClass, null);
    }

    /**
     * Registers a given view to the navigator and menu bar.
     */
    private void addViewToMenuBar(Class<? extends View> viewClass, Resource icon, String caption, String uri) {
        ViewConfig viewConfig = viewClass.getAnnotation(ViewConfig.class);
        addView(viewClass);
        String itemCaption = caption != null ? ViewConfigUtil.getDisplayName(caption)
                : ViewConfigUtil.getDisplayName(viewConfig.displayName());
        String itemUri = uri != null ? uri : viewConfig.uri();
        MenuItem item = menuBar.addItem(itemCaption, icon, command -> navigator.navigateTo(itemUri));
        menuItems.put(viewConfig.uri(), item);
    }

    private void addViewToMenuBar(Class<? extends View> viewClass) {
        addViewToMenuBar(viewClass, null, null, null);
    }

    @Subscribe
    public void viewRemoved(ViewRemovedEvent event) {
        navigator.removeView(event.getViewName());
        navBar.removeView(event.getViewName());
        menuBar.removeItem(menuItems.get(event.getViewName()));
    }

    @Subscribe
    public void viewAdded(ViewAddedEvent event) {
        if (event != null) {
            switch (event.getDestination()) {
                case NAVBAR:
                    addViewToNavBar(event.getViewClass(), event.getCaption());
                    break;
                case MENUBAR:
                    addViewToMenuBar(event.getViewClass(), null, event.getCaption(), event.getUri());
                    break;
                case NAVIGATOR:
                default:
                    addView(event.getViewClass());
                    break;
            }
        }
    }

}
