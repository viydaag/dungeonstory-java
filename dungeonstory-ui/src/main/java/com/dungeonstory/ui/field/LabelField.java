package com.dungeonstory.ui.field;

import com.dungeonstory.ui.captionGenerator.ToStringCaptionGenerator;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.ItemCaptionGenerator;
import com.vaadin.ui.Label;

/**
 * A field which represents the value always in a label, so it is not editable.
 * <p>
 * The use case is a non editable value in a form, e.g. an id.
 * <p>
 * The creation of the label text can be controlled via a
 * {@link com.vaadin.ui.ItemCaptionGenerator}.
 *
 *
 * @param <T> the type of the entity
 */
public class LabelField<T> extends CustomField<T> {

    private static final long serialVersionUID = -5505418607821020932L;

    private T     value;
    private Label label = new Label();

    private ItemCaptionGenerator<T> captionGenerator = new ToStringCaptionGenerator<>();

    @Override
    protected void doSetValue(T value) {
        this.value = value;
        updateLabel();
    }

    @Override
    public T getValue() {
        return value;
    }

    public LabelField(String caption) {
        super();
        setCaption(caption);
    }

    public LabelField() {

    }

    @Override
    protected Component initContent() {
        updateLabel();

        return label;
    }

    protected void updateLabel() {
        String caption;
        if (captionGenerator != null) {
            caption = captionGenerator.apply(getValue());
        } else {
            caption = getValue().toString();
        }
        label.setValue(caption);
    }

    /**
     * Sets the ItemCaptionGenerator for creating the label value.
     *
     * @param captionGenerator the caption generator used to format the
     * displayed property
     */
    public void setCaptionGenerator(ItemCaptionGenerator<T> captionGenerator) {
        this.captionGenerator = captionGenerator;
        updateLabel();
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public Label getLabel() {
        return label;
    }

    @Override
    public void setRequiredIndicatorVisible(boolean visible) {
        //do nothing because we don't want required indicator to be visible on a read-only value.
    }

}
