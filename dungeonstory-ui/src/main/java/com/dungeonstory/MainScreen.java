package com.dungeonstory;

import java.util.HashMap;

import com.dungeonstory.authentication.CurrentUser;
import com.dungeonstory.event.ViewAddedEvent;
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

        viewContainer = new VerticalSpacedLayout();
        viewContainer.addStyleName(DSTheme.VALO_CONTENT);
        viewContainer.addStyleName(DSTheme.CRUD_VIEW);
        viewContainer.setSizeFull();

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
            addView(NewCharacterView.class);
        } else {
            addViewToMenuBar(CharacterView.class);
            addViewToMenuBar(ShopListView.class);
            addView(com.dungeonstory.view.shop.ShopView.class);
        }

        addViewToMenuBar(UserView.class);
        addViewToMenuBar(AdventureListView.class);
        addView(AdventureView.class);
        if (CurrentUser.get().getAdventure() != null) {
            menuBar.addItem("Mon aventure", null, command -> navigator
                    .navigateTo(AdventureView.URI + "/" + CurrentUser.get().getAdventure().getId()));
        }

        if (DungeonStoryUI.get().getAccessControl().isUserInRole("ADMIN")) {
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
            addViewToNavBar(LanguageView.class);
            addViewToNavBar(RaceView.class);
            addViewToNavBar(SpellView.class);
            addViewToNavBar(ClassView.class);
            addViewToNavBar(ClassSpecializationView.class);
            addViewToNavBar(WeaponTypeView.class);
            addViewToNavBar(ArmorTypeView.class);
            addViewToNavBar(ShopView.class);
            addViewToNavBar(EquipmentView.class);
            addViewToNavBar(DivineDomainView.class);
            addViewToNavBar(DeityView.class);
            addViewToNavBar(BackgroundView.class);
            addViewToNavBar(CreatureTypeView.class);
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
