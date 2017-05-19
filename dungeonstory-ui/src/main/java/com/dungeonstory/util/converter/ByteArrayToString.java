package com.dungeonstory.util.converter;

import org.apache.commons.codec.binary.Base64;

import com.vaadin.data.Converter;
import com.vaadin.data.Result;
import com.vaadin.data.ValueContext;

public class ByteArrayToString implements Converter<String, byte[]> {

    private static final long serialVersionUID = 6893355715031071885L;

    @Override
    public Result<byte[]> convertToModel(String value, ValueContext context) {
        return Result.ok(Base64.decodeBase64(value));
    }

    @Override
    public String convertToPresentation(byte[] value, ValueContext context) {
        return Base64.encodeBase64String(value);
    }

}
