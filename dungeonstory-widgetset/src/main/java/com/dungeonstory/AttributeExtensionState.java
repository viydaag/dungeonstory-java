package com.dungeonstory;

import java.util.HashMap;

import com.vaadin.shared.JavaScriptExtensionState;

/**
 * Shared state class for {@link AttributeExtension} communication from server
 * to client.
 */
public class AttributeExtensionState extends JavaScriptExtensionState {

    private static final long serialVersionUID = 1386655185750945408L;

    public HashMap<String, String> attributes = new HashMap<String, String>();
}
