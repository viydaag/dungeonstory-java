package com.dungeonstory.backend;

import java.util.ResourceBundle;

public final class Configuration {
    
    private final String DEBUG = "debug";
    private final String MOCK = "mock";

    private final String DATABASE_TYPE = "databaseType";
    
    private ResourceBundle conf;
    private static Configuration instance = null;
    
    public static synchronized Configuration getInstance() {
        if (instance == null) {
            instance = new Configuration();
        }
        return instance;
    }
    
    private Configuration() {
        conf = ResourceBundle.getBundle("configuration");
    }

    public boolean isDebug() {
        return Boolean.parseBoolean(conf.getString(DEBUG));
    }

    public String getDatabaseType() {
        return conf.getString(DATABASE_TYPE);
    }
    
    public boolean isMock() {
        return Boolean.parseBoolean(conf.getString(MOCK));
    }

}
