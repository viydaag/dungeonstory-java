package com.dungeonstory.i18n;

import java.util.Locale;

import com.vaadin.ui.ComboBox;

public class LanguageSelector extends ComboBox implements Translatable {

    private static final long serialVersionUID = -1706959924457092942L;

    public LanguageSelector() {
        addItem(Locale.ENGLISH);
        addItem(Locale.FRENCH);
        setValue(Locale.FRENCH);
        setNullSelectionAllowed(false);
        addValueChangeListener(e -> getUI().setLocale((Locale) getValue()));
    }

    @Override
    public void updateMessageStrings() {
        Messages messages = Messages.getInstance();
        setValue(getLocale());
        setItemCaption(Locale.ENGLISH, messages.getMessage("languageSelector.en"));
        setItemCaption(Locale.FRENCH, messages.getMessage("languageSelector.fr"));
    }
}
