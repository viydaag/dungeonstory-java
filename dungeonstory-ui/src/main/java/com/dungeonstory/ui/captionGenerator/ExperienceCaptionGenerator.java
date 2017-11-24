package com.dungeonstory.ui.captionGenerator;

import com.vaadin.ui.ItemCaptionGenerator;

public class ExperienceCaptionGenerator implements ItemCaptionGenerator<Long> { 
    
    private static final long serialVersionUID = -5087912562848650093L;

    private long maxExperience;
    
    public ExperienceCaptionGenerator(long maxExperience) {
        this.maxExperience = maxExperience;
    }

    @Override
    public String apply(Long experience) {
        return experience + " / " + maxExperience;
    }

}
