package com.dungeonstory.ui.captionGenerator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import com.vaadin.ui.ItemCaptionGenerator;

public class LocalDateTimeCaptionGenerator implements ItemCaptionGenerator<LocalDateTime> { 

    private static final long serialVersionUID = 6439225588566989607L;

    @Override
    public String apply(LocalDateTime dateTime) {
        if (dateTime != null) {
            return dateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG, FormatStyle.SHORT));
        } else {
            return "";
        }
    }

}
