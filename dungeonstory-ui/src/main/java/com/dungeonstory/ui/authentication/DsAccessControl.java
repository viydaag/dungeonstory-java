package com.dungeonstory.ui.authentication;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.security.auth.login.LoginException;
import javax.servlet.http.Cookie;

import com.dungeonstory.backend.data.User;
import com.dungeonstory.backend.data.enums.AccessRole;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.backend.service.impl.LoginService;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinSession;

/**
 * Default DS implementation of {@link AccessControl}. This implementation
 * calls the {@link LoginService} to authenticate the user.
 */
public class DsAccessControl implements AccessControl {

    private static final long serialVersionUID = -5090993487106313401L;
    
    private static final String COOKIE_NAME = "ds-remember-me";
    public static final int COOKIE_MAX_AGE_DAYS = 30;

    private static Map<String, String> remembered = new HashMap<>();
    private static SecureRandom random = new SecureRandom();

    private LoginService loginService = new LoginService();

    @Override
    public boolean signIn(String username, String password, boolean rememberMe) {
        try {
            User user = loginService.login(username, password);
            if (rememberMe) {
                rememberUser(username);
            }
            CurrentUser.set(user);
        } catch (LoginException e) {
            return false;
        }

        return true;
    }
    
    @Override
    public void signout() {
        removeRememberedUser();
        CurrentUser.set(null);

        VaadinSession.getCurrent().getSession().invalidate();
        VaadinSession.getCurrent().close();
    }

    @Override
    public boolean isUserSignedIn() {
        if (CurrentUser.isLoggedIn()) {
            return true;
        } else if (isRememberedUser()) {
            String username = remembered.get(getCookie().get().getValue());
            User user = Services.getUserService().findByUsername(username);
            CurrentUser.set(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean isUserInRole(AccessRole role) {
        AccessRole accessRole = getRole();
        if (accessRole != null) {
            return accessRole == role;
        }
        return false;
    }

    @Override
    public AccessRole getRole() {
        if (isUserSignedIn()) {
            return CurrentUser.get().getRole();
        }
        return null;
    }
    
    public boolean isRememberedUser() {
        Optional<Cookie> cookie = getCookie();
        return cookie.isPresent() && remembered.containsKey(cookie.get().getValue());
    }
    
    private void removeRememberedUser() {
        Optional<Cookie> cookie = getCookie();

        if (cookie.isPresent()) {
            remembered.remove(cookie.get().getValue());
            saveCookie("", 0);
        }
    }

    private void rememberUser(String username) {
        String randomKey = new BigInteger(130, random).toString(32);
        remembered.put(randomKey, username);
        saveCookie(randomKey, 60 * 60 * 24 * COOKIE_MAX_AGE_DAYS);
    }

    private void saveCookie(String value, int age) {
        Cookie cookie = new Cookie(COOKIE_NAME, value);
        cookie.setPath("/");
        cookie.setMaxAge(age);
        VaadinService.getCurrentResponse().addCookie(cookie);
    }

    private Optional<Cookie> getCookie() {
        Cookie[] cookies = VaadinService.getCurrentRequest().getCookies();
        return Arrays.stream(cookies)
                .filter(c -> COOKIE_NAME.equals(c.getName()))
                .findFirst();
    }

}
