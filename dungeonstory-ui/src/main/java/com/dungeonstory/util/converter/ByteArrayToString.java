package com.dungeonstory.util.converter;

import java.util.Locale;

import org.apache.commons.codec.binary.Base64;

import com.vaadin.data.util.converter.Converter;

public class ByteArrayToString implements Converter<String, byte[]> {

    private static final long serialVersionUID = 6893355715031071885L;

    @Override
    public byte[] convertToModel(String value, Class<? extends byte[]> targetType, Locale locale)
            throws com.vaadin.data.util.converter.Converter.ConversionException {
        return Base64.decodeBase64(value);
    }

    @Override
    public String convertToPresentation(byte[] value, Class<? extends String> targetType, Locale locale)
            throws com.vaadin.data.util.converter.Converter.ConversionException {
        return Base64.encodeBase64String(value);
    }

    @Override
    public Class<byte[]> getModelType() {
        return byte[].class;
    }

    @Override
    public Class<String> getPresentationType() {
        return String.class;
    }

}
