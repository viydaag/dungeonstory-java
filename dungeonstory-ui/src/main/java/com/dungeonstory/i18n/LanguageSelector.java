package com.dungeonstory.i18n;

import java.util.Locale;

import com.vaadin.ui.ComboBox;
import com.vaadin.ui.ItemCaptionGenerator;
import com.vaadin.ui.UI;


public class LanguageSelector extends ComboBox<Locale> implements Translatable {

    private static final long serialVersionUID = -1706959924457092942L;

    public LanguageSelector() {
        //        addItem(Locale.ENGLISH);
        //        addItem(Locale.FRENCH);
        setItems(Locale.ENGLISH, Locale.CANADA_FRENCH);
        setEmptySelectionAllowed(false);
        setValue(Locale.CANADA_FRENCH);
        addValueChangeListener(e -> {
            if (e.getValue() != null) {
                UI.getCurrent().setLocale(e.getValue());
            }
        });
    }

    @Override
    public void updateMessageStrings() {
        Messages messages = Messages.getInstance();
        //        setValue(getLocale());
//        setItemCaption(Locale.ENGLISH, messages.getMessage("languageSelector.en"));
//        setItemCaption(Locale.FRENCH, messages.getMessage("languageSelector.fr"));
        setItemCaptionGenerator(new ItemCaptionGenerator<Locale>() {
            private static final long serialVersionUID = -1634395306396320788L;

            @Override
            public String apply(Locale option) {
                if (option == Locale.CANADA_FRENCH) {
                    return messages.getMessage("languageSelector.fr");
                } else if (option == Locale.ENGLISH) {
                    return messages.getMessage("languageSelector.en");
                }
                return "";
            }
        });
    }
}
