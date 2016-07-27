package com.dungeonstory;

import com.dungeonstory.util.DSTheme;
import com.dungeonstory.util.LazyProvider;
import com.dungeonstory.util.PageTitleUpdater;
import com.dungeonstory.util.VerticalSpacedLayout;
import com.dungeonstory.util.ViewConfig;
import com.dungeonstory.view.ErrorView;
import com.dungeonstory.view.HomeView;
import com.dungeonstory.view.admin.AlignmentView;
import com.dungeonstory.view.admin.ArmorTypeView;
import com.dungeonstory.view.admin.ClassView;
import com.dungeonstory.view.admin.DamageTypeView;
import com.dungeonstory.view.admin.FeatView;
import com.dungeonstory.view.admin.LevelView;
import com.dungeonstory.view.admin.RaceView;
import com.dungeonstory.view.admin.RegionView;
import com.dungeonstory.view.admin.ShopView;
import com.dungeonstory.view.admin.SkillView;
import com.dungeonstory.view.admin.WeaponTypeView;
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
	private NavBar navBar;
    private Navigator navigator;

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
    	
    	if (DungeonStoryUI.get().getAccessControl().isUserInRole("admin")) {
    	    addView(AlignmentView.class);
    		addView(RegionView.class);
    		addView(LevelView.class);
    		addView(SkillView.class);
    		addView(FeatView.class);
    		addView(ClassView.class);
    		addView(DamageTypeView.class);
    		addView(RaceView.class);
    		addView(WeaponTypeView.class);
    		addView(ArmorTypeView.class);
    		addView(ShopView.class);
    	}
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

}
