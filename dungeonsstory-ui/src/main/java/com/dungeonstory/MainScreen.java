package com.dungeonstory;

import com.dungeonstory.util.DSTheme;
import com.dungeonstory.util.LazyProvider;
import com.dungeonstory.util.PageTitleUpdater;
import com.dungeonstory.util.VerticalSpacedLayout;
import com.dungeonstory.util.ViewConfig;
import com.dungeonstory.view.AlignmentView;
import com.dungeonstory.view.ClassView;
import com.dungeonstory.view.DamageTypeView;
import com.dungeonstory.view.ErrorView;
import com.dungeonstory.view.HomeView;
import com.dungeonstory.view.LevelView;
import com.dungeonstory.view.RegionView;
import com.dungeonstory.view.SkillView;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

/**
 * Content of the UI when the user is logged in.
 * 
 * 
 */
public class MainScreen extends HorizontalLayout {
	
	private static final long serialVersionUID = -6331656609834870730L;
	
//	private Menu menu;
	private VerticalLayout viewContainer;
	private NavBar navBar;
    private Navigator navigator;

    public MainScreen(DungeonStoryUI ui) {

        setStyleName(DSTheme.MAIN_LAYOUT);
        
        setSizeFull();
        
        initLayout();
        setupNavigator();

//        CssLayout viewContainer = new CssLayout();
//        viewContainer.addStyleName("valo-content");
//        viewContainer.setSizeFull();

//		navigator = new Navigator(ui, viewContainer);
//		navigator.setErrorView(ErrorView.class);
		
//		menu = new Menu(navigator);
//		menu.addView(new SampleCrudView(), SampleCrudView.VIEW_NAME,
//				SampleCrudView.VIEW_NAME, FontAwesome.EDIT);
//		menu.addView(new AboutView(), AboutView.VIEW_NAME, AboutView.VIEW_NAME,
//				FontAwesome.INFO_CIRCLE);
//		menu.addView(new RegionView(), RegionView.VIEW_NAME,
//				RegionView.VIEW_NAME, null);

//        navigator.addViewChangeListener(viewChangeListener);
//        navigator.addViewChangeListener(new PageTitleUpdater());

//        addComponent(menu);
//        addComponent(viewContainer);
//        setExpandRatio(viewContainer, 1);
        
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
    		addView(RegionView.class);
    		addView(LevelView.class);
    		addView(SkillView.class);
    		addView(ClassView.class);
    		addView(DamageTypeView.class);
    		addView(AlignmentView.class);
    	}
	}

	/**
     * Registers av given view to the navigator and adds it to the NavBar
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

    // notify the view menu about view changes so that it can display which view
    // is currently active
//    ViewChangeListener viewChangeListener = new ViewChangeListener() {
//
//		private static final long serialVersionUID = -7163291894890818701L;
//
//		@Override
//        public boolean beforeViewChange(ViewChangeEvent event) {
//            return true;
//        }
//
//        @Override
//        public void afterViewChange(ViewChangeEvent event) {
//            menu.setActiveView(event.getViewName());
//        }
//
//    };
}
