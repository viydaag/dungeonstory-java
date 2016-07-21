package com.dungeonstory.backend;

import static javax.xml.bind.DatatypeConverter.parseBase64Binary;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import javax.mail.Message.RecipientType;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;

import org.simplejavamail.email.Email;
import org.simplejavamail.internal.util.ConfigLoader;
import org.simplejavamail.internal.util.ConfigLoader.Property;
import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.mailer.config.ProxyConfig;
import org.simplejavamail.mailer.config.ServerConfig;
import org.simplejavamail.mailer.config.TransportStrategy;

/**
 * Demonstration program for the Simple Java Mail framework.
 *
 * @author Benny Bottema
 */
public class MailTestApp {

    private static final String YOUR_GMAIL_ADDRESS = "fortier.jc@gmail.com";

    // if you have 2-factor login turned on, you need to generate a once-per app password
    // https://security.google.com/settings/security/apppasswords
    private static final String YOUR_GMAIL_PASSWORD = "xxx";

    private static final ServerConfig serverConfigSMTP = new ServerConfig("smtp.gmail.com", 25, YOUR_GMAIL_ADDRESS, YOUR_GMAIL_PASSWORD);
    private static final ServerConfig serverConfigTLS = new ServerConfig("smtp.gmail.com", 587, YOUR_GMAIL_ADDRESS, YOUR_GMAIL_PASSWORD);
    private static final ServerConfig serverConfigSSL = new ServerConfig("smtp.gmail.com", 465, YOUR_GMAIL_ADDRESS, YOUR_GMAIL_PASSWORD);

    public static void main(final String[] args)
            throws Exception {
        clearConfigProperties();

        final Email emailNormal = new Email();
        emailNormal.setFromAddress("lollypop", "lol.pop@somemail.com");
        // don't forget to add your own address here ->
        emailNormal.addRecipient("C.Cane", YOUR_GMAIL_ADDRESS, RecipientType.TO);
        emailNormal.setText("We should meet up!");
        emailNormal.setTextHTML("<b>We should meet up!</b><img src='cid:thumbsup'>");
        emailNormal.setSubject("hey");

        // add two text files in different ways and a black thumbs up embedded image ->
        emailNormal.addAttachment("dresscode.txt", new ByteArrayDataSource("Black Tie Optional", "text/plain"));
        emailNormal.addAttachment("location.txt", "On the moon!".getBytes(Charset.defaultCharset()), "text/plain");
        String base64String = "iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAABeElEQVRYw2NgoAAYGxu3GxkZ7TY1NZVloDcAWq4MxH+B+D8Qv3FwcOCgtwM6oJaDMTAUXOhmuYqKCjvQ0pdoDrCnmwNMTEwakC0H4u8GBgYC9Ap6DSD+iewAoIPm0ctyLqBlp9F8/x+YE4zpYT8T0LL16JYD8U26+B7oyz4sloPwenpYno3DchCeROsUbwa05A8eB3wB4kqgIxOAuArIng7EW4H4EhC/B+JXQLwDaI4ryZaDSjeg5mt4LCcFXyIn1fdSyXJQVt1OtMWGhoai0OD8T0W8GohZifE1PxD/o7LlsPLiFNAKRrwOABWptLAcqc6QGDAHQEOAYaAc8BNotsJAOgAUAosG1AFA/AtUoY3YEFhKMAvS2AE7iC1+WaG1H6gY3gzE36hUFJ8mqzbU1dUVBBqQBzTgIDQRkWo5qCZdpaenJ0Zx1aytrc0DDB0foIG1oAYKqC0IZK8D4n1AfA6IzwPxXpCFoGoZVEUDaRGGUTAKRgEeAAA2eGJC+ETCiAAAAABJRU5ErkJggg==";
        emailNormal.addEmbeddedImage("thumbsup", parseBase64Binary(base64String), "image/png");

        // let's try producing and then consuming a MimeMessage ->
        final MimeMessage mimeMessage = Mailer.produceMimeMessage(emailNormal);
        final Email emailFromMimeMessage = new Email(mimeMessage);

        // note: the following statements will produce 6 new emails!
        sendMail(emailNormal);
        sendMail(emailFromMimeMessage); // should produce the exact same result as emailNormal!
    }

    private static void sendMail(final Email email) {
        ProxyConfig proxyconfig = new ProxyConfig("proxy-internet.pub.desjardins.com", 8080, "dzb1677", "Jesuisjc5");
        new Mailer(serverConfigSMTP, TransportStrategy.SMTP_TLS, proxyconfig).sendMail(email);
        new Mailer(serverConfigTLS, TransportStrategy.SMTP_TLS, proxyconfig).sendMail(email);
        new Mailer(serverConfigSSL, TransportStrategy.SMTP_SSL).sendMail(email);
    }
    
    public static void setResolvedProperties(Map<Property, Object> value)
            throws Exception {
        Field field = makeAccessible(ConfigLoader.class.getDeclaredField("RESOLVED_PROPERTIES"));
        field.set(null, value);
    }

    private static Field makeAccessible(Field field)
            throws NoSuchFieldException, IllegalAccessException {
        field.setAccessible(true);
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
        return field;
    }

    public static void clearConfigProperties()
            throws Exception {
        setResolvedProperties(new HashMap<Property, Object>());
    }
}