package com.dungeonstory.ui.i18n;

import org.apache.commons.lang3.StringUtils;

import com.vaadin.server.CustomizedSystemMessages;
import com.vaadin.server.SystemMessages;
import com.vaadin.server.SystemMessagesInfo;
import com.vaadin.server.SystemMessagesProvider;

/**
 * Provides system messages for dungeonstory app.
 *
 */
public class DSSystemMessagesProvider implements SystemMessagesProvider {

    private static final long serialVersionUID = -3929777635602507150L;

    @Override
    public SystemMessages getSystemMessages(SystemMessagesInfo systemMessagesInfo) {

        Messages messages = Messages.getInstance(systemMessagesInfo.getLocale());

        CustomizedSystemMessages systemMesages = new CustomizedSystemMessages();

        systemMesages.setSessionExpiredNotificationEnabled(true);
        systemMesages.setSessionExpiredCaption(messages.getMessage("sessionExpiredCaption"));
        systemMesages.setSessionExpiredMessage(messages.getMessage("sessionExpiredMessage"));
        systemMesages.setSessionExpiredURL(StringUtils.defaultIfBlank(messages.getMessage("sessionExpiredURL"), null));

        systemMesages.setCommunicationErrorNotificationEnabled(true);
        systemMesages.setCommunicationErrorCaption(messages.getMessage("communicationErrorCaption"));
        systemMesages.setCommunicationErrorMessage(messages.getMessage("communicationErrorMessage"));
        systemMesages.setCommunicationErrorURL(StringUtils.defaultIfBlank(messages.getMessage("communicationErrorURL"), null));

        systemMesages.setAuthenticationErrorNotificationEnabled(true);
        systemMesages.setAuthenticationErrorCaption(messages.getMessage("authenticationErrorCaption"));
        systemMesages.setAuthenticationErrorMessage(messages.getMessage("authenticationErrorMessage"));
        systemMesages.setAuthenticationErrorURL(StringUtils.defaultIfBlank(messages.getMessage("authenticationErrorURL"), null));

        systemMesages.setInternalErrorNotificationEnabled(true);
        systemMesages.setInternalErrorCaption(messages.getMessage("internalErrorCaption"));
        systemMesages.setInternalErrorMessage(messages.getMessage("internalErrorMessage"));
        systemMesages.setInternalErrorURL(StringUtils.defaultIfBlank(messages.getMessage("internalErrorURL"), null));

        systemMesages.setCookiesDisabledNotificationEnabled(true);
        systemMesages.setCookiesDisabledCaption(messages.getMessage("cookiesDisabledCaption"));
        systemMesages.setCookiesDisabledMessage(messages.getMessage("cookiesDisabledMessage"));
        systemMesages.setCookiesDisabledURL(StringUtils.defaultIfBlank(messages.getMessage("cookiesDisabledURL"), null));

        return systemMesages;
    }

}
