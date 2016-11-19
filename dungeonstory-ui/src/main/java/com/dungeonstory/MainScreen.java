package com.dungeonstory;

import com.dungeonstory.authentication.CurrentUser;
import com.dungeonstory.util.DSTheme;
import com.dungeonstory.util.LazyProvider;
import com.dungeonstory.util.PageTitleUpdater;
import com.dungeonstory.util.ViewConfig;
import com.dungeonstory.util.layout.VerticalSpacedLayout;
import com.dungeonstory.view.AboutView;
import com.dungeonstory.view.ErrorView;
import com.dungeonstory.view.HomeView;
import com.dungeonstory.view.SourceView;
import com.dungeonstory.view.admin.AbilityView;
import com.dungeonstory.view.admin.AlignmentView;
import com.dungeonstory.view.admin.ArmorTypeView;
import com.dungeonstory.view.admin.BackgroundView;
import com.dungeonstory.view.admin.CharacterView;
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
import com.dungeonstory.view.admin.WeaponTypeView;
import com.dungeonstory.view.character.NewCharacterView;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.ui.HorizontalLayout;
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

    public MainScreen(DungeonStoryUI ui) {

        setStyleName(DSTheme.MAIN_LAYOUT);

        setSizeFull();

        initLayout();
        setupNavigator();
    }

    private void initLayout() {
        navBar = new NavBar();

        viewContainer = new VerticalSpacedLayout();
        viewContainer.addStyleName(DSTheme.VALO_CONTENT);
        viewContainer.setSizeFull();

        //        DMenu menu = new DMenu();
        //        menu.setCenteredTabs(true);
        //        menu.setFloatingMenu(true);
        //        menu.setFloatingMenuItemAutoShow(true);
        //        menu.setMenuItemsSameHeight(true);
        //        menu.setMenuItemElementsSameHeight(true);

        addComponents(navBar, viewContainer);
        setExpandRatio(viewContainer, 1);
    }

    private void setupNavigator() {
        navigator = new Navigator(DungeonStoryUI.getCurrent(), viewContainer);

        registerViews();

        // Add view change listeners so we can do things like select the correct menu item and update the page title
        navigator.addViewChangeListener(navBar);
        navigator.addViewChangeListener(new PageTitleUpdater());

        navigator.setErrorView(ErrorView.class);
        navigator.navigateTo(navigator.getState());
    }

    private void registerViews() {
        addView(HomeView.class);

        if (CurrentUser.get().getCharacter() == null) {
            addView(NewCharacterView.class);
        }

        if (DungeonStoryUI.get().getAccessControl().isUserInRole("ADMIN")) {
            addView(CharacterView.class);
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
            navBar.addView(viewConfig.uri(), viewConfig.displayName());
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

}