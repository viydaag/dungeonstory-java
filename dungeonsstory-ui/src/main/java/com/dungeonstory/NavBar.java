package com.dungeonstory;

import java.util.HashMap;
import java.util.Map;

import com.dungeonstory.event.EventBus;
import com.dungeonstory.event.LogoutEvent;
import com.dungeonstory.event.NavigationEvent;
import com.dungeonstory.util.DSTheme;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;

public class NavBar extends CssLayout implements ViewChangeListener {

	private static final long serialVersionUID = 1320578793416318124L;

	private Map<String, Button> buttonMap = new HashMap<>();

	public NavBar() {
		setHeight("100%");
		addStyleName(DSTheme.MENU_ROOT);
		addStyleName(DSTheme.NAVBAR);

		Label logo = new Label("<strong>Dungeon Story</strong>", ContentMode.HTML);
		logo.addStyleName(DSTheme.MENU_TITLE);
		addComponent(logo);

		addLogoutButton();
	}

	private void addLogoutButton() {
		Button logout = new Button("Log out",
				click -> EventBus.post(new LogoutEvent()));
		addComponent(logout);

		logout.addStyleName(DSTheme.BUTTON_LOGOUT);
		logout.addStyleName(DSTheme.BUTTON_BORDERLESS);
		logout.setIcon(FontAwesome.SIGN_OUT);
	}

	public void addView(String uri, String displayName) {
		Button viewButton = new Button(displayName,
				click -> EventBus.post(new NavigationEvent(uri)));
		viewButton.addStyleName(DSTheme.MENU_ITEM);
		viewButton.addStyleName(DSTheme.BUTTON_BORDERLESS);
		buttonMap.put(uri, viewButton);

		addComponent(viewButton, components.size() - 1);
	}

	@Override
	public boolean beforeViewChange(ViewChangeEvent event) {
		return true; // false blocks navigation, always return true here
	}

	@Override
	public void afterViewChange(ViewChangeEvent event) {
		buttonMap.values().forEach(
				button -> button.removeStyleName(DSTheme.SELECTED));
		Button button = buttonMap.get(event.getViewName());
		if (button != null) {
			button.addStyleName(DSTheme.SELECTED);
		}
	}
}
