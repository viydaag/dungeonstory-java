package com.dungeonstory.backend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Objects;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public final class Labels implements Serializable {

    private static final long serialVersionUID = 7483872812052536838L;

    private final ResourceBundle labels;
    private Locale locale;

    private static Labels instance;

    private Labels(Locale locale) {
        this.locale = Objects.requireNonNull(locale);
        this.labels = ResourceBundle.getBundle("labels", locale, new UnicodeResourceBundleControl());
    }

    public static Labels getInstance(Locale locale) {
        if (instance == null || !instance.getLocale().getLanguage().equals(locale.getLanguage())) {
            synchronized(Labels.class) {
                if (instance == null || !instance.getLocale().getLanguage().equals(locale.getLanguage())) {
                    instance = new Labels(locale);
                }
            }
            
        }
        return instance;
    }

    public static Labels getInstance() {
        if (instance == null || instance.getLocale() == null) {
            throw new IllegalStateException("No locale initiated for messages");
        }
        return getInstance(instance.getLocale());
    }

    public Locale getLocale() {
        return locale;
    }

    public String getMessage(String key, Object... arguments) {
        try {
            final String pattern = labels.getString(key);
            final MessageFormat format = new MessageFormat(pattern, getLocale());
            return format.format(arguments);
        } catch (MissingResourceException ex) {
            return "!" + key;
        }
    }

    private class UnicodeResourceBundleControl extends ResourceBundle.Control {
        @Override
        public ResourceBundle newBundle(String baseName, Locale locale, String format, ClassLoader loader, boolean reload)
                throws IllegalAccessException, InstantiationException, IOException {

            String bundleName = toBundleName(baseName, locale);
            String resourceName = toResourceName(bundleName, "properties");
            final URL resourceURL = loader.getResource(resourceName);
            if (resourceURL == null)
                return null;

            BufferedReader in = new BufferedReader(new InputStreamReader(resourceURL.openStream(), StandardCharsets.UTF_8));

            try {
                return new PropertyResourceBundle(in);
            } finally {
                in.close();
            }
        }
    }
}
