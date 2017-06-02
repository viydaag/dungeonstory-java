package com.dungeonstory.ui.view.character.wizard.form;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.vaadin.viritin.fields.LabelField;
import org.vaadin.viritin.form.AbstractForm;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.CharacterClass;
import com.dungeonstory.backend.data.ClassSpellSlots;
import com.dungeonstory.backend.data.DSClass;
import com.dungeonstory.backend.data.DSClass.SpellCastingType;
import com.dungeonstory.backend.data.Spell;
import com.dungeonstory.backend.data.util.ClassUtil;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.backend.service.SpellDataService;
import com.dungeonstory.ui.component.DSAbstractForm;
import com.dungeonstory.ui.component.DSLabel;
import com.dungeonstory.ui.converter.CollectionToStringConverter;
import com.dungeonstory.ui.i18n.Messages;
import com.vaadin.data.ValueContext;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.shared.MouseEventDetails.MouseButton;
import com.vaadin.ui.Audio;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.TabSheet.SelectedTabChangeListener;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

public class SpellChoiceForm extends DSAbstractForm<CharacterClass> implements AbstractForm.SavedHandler<CharacterClass> {

    private static final long serialVersionUID = 7418266123213990672L;

    private SpellDataService spellService = Services.getSpellService();

    private Character character;
    private DSClass   chosenClass;

    private Label               title;
    private LabelField<DSClass> classe;
    private LabelField<Integer> classLevel;

    private TabSheet             tabs  = null;
    private HorizontalSplitPanel panel = null;

    private VerticalLayout unknownCantripLayout = new VerticalLayout();
    private VerticalLayout knownCantripLayout   = new VerticalLayout();

    private VerticalLayout[] unknownSpellLayout = new VerticalLayout[Spell.MAX_SPELL_LEVEL + 1];
    private VerticalLayout   knownSpellLayout   = new VerticalLayout();

    private int totalNbSpell = 0;

    private Audio spellSound = null;

    public SpellChoiceForm(Character character, DSClass dsClass) {
        super(CharacterClass.class);

        this.character = character;
        this.chosenClass = dsClass;

        String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
        FileResource resource = new FileResource(new File(basepath + "/WEB-INF/sound/selectSpell.mp3"));

        spellSound = new Audio();
        spellSound.setSource(resource);
        spellSound.setShowControls(false);
        spellSound.setAutoplay(false);

        setSavedHandler(this);
    }

    @Override
    protected Component createContent() {

        VerticalLayout layout = new VerticalLayout();

        layout.addComponent(spellSound);

        title = new Label();
        classe = new LabelField<>("Classe : ");
        classLevel = new LabelField<>("Niveau de classe : ");
        layout.addComponents(title);

        tabs = new TabSheet();

        panel = new HorizontalSplitPanel();
        panel.setSizeFull();
        panel.setCaption(Messages.getInstance().getMessage("spellStep.spell.label"));
        panel.setFirstComponent(tabs);
        layout.addComponent(panel);

        return layout;
    }

    @Override
    public void afterSetEntity() {

        CharacterClass assignedClass = ClassUtil.getCharacterClass(this.character, this.chosenClass);

        if (assignedClass != null) {

            int classLevel = assignedClass.getClassLevel();
            title.setValue(Messages.getInstance().getMessage("spellStep.class.label", assignedClass.toString()));

            Optional<ClassSpellSlots> spellSlotOpt = ClassUtil.getClassSpellSlots(this.chosenClass, classLevel);

            if (spellSlotOpt.isPresent()) {
                ClassSpellSlots spellSlot = spellSlotOpt.get();

                //number of spell to choose
                final Integer maxNumberOfSpellToChoose;
                final Integer maxNumberOfCantripToChoose;
                maxNumberOfCantripToChoose = spellSlot.getCantripsKnown();
                if (this.chosenClass.getSpellCastingType() == SpellCastingType.KNOWN) {
                    maxNumberOfSpellToChoose = spellSlot.getSpellsKnown();
                } else {
                    maxNumberOfSpellToChoose = 6;
                }

                if (maxNumberOfCantripToChoose != null) {

                    totalNbSpell += maxNumberOfCantripToChoose;

                    List<Spell> unknownCantrips = spellService.findAllUnknownClassSpellsByLevel(Spell.CANTRIP_LEVEL, this.character.getId(),
                            this.chosenClass.getId());
                    List<Spell> knownCantrips = spellService.findAllKnownClassSpellsByLevel(Spell.CANTRIP_LEVEL, this.character.getId(),
                            this.chosenClass.getId());

                    // fill cantrips
                    for (Spell cantrip : unknownCantrips) {
                        Button button = new SpellButton(cantrip);
                        button.addClickListener(new ClickListener() {

                            private static final long serialVersionUID = -7100849411719491325L;

                            @Override
                            public void buttonClick(ClickEvent event) {
                                Button source = event.getButton();
                                if (source.getParent().equals(unknownCantripLayout)) {
                                    unknownCantripLayout.removeComponent(source);
                                    knownCantripLayout.addComponent(source);
                                } else {
                                    knownCantripLayout.removeComponent(source);
                                    unknownCantripLayout.addComponent(source);
                                }
                                unknownCantripLayout.setEnabled(knownCantripLayout.getComponentCount() < maxNumberOfCantripToChoose.intValue());

                            }
                        });
                        unknownCantripLayout.addComponent(button);
                    }

                    for (Spell cantrip : knownCantrips) {
                        Button button = new Button(cantrip.getName());
                        button.setWidth("100%");
                        button.setStyleName(ValoTheme.BUTTON_LARGE);
                        button.setData(cantrip);
                        knownCantripLayout.addComponent(button);
                    }

                    Tab tab = tabs.addTab(unknownCantripLayout, "0");
                    tab.setClosable(false);
                    tab.setEnabled(true);
                    tab.setDescription(Messages.getInstance().getMessage("spellStep.minorSpell.tab.caption"));
                }

                if (maxNumberOfSpellToChoose != null) {

                    totalNbSpell += maxNumberOfSpellToChoose;

                    //get max level spell slot
                    int maxLevelSpellSlot = 0;
                    for (int spellLevel = Spell.MIN_SPELL_LEVEL; spellLevel <= Spell.MAX_SPELL_LEVEL; spellLevel++) {
                        try {
                            Method method = spellSlot.getClass().getDeclaredMethod("getSpellSlots" + spellLevel);
                            Integer nbSpells = (Integer) method.invoke(spellSlot);
                            if (nbSpells != null) {
                                maxLevelSpellSlot = spellLevel;
                            }
                        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
                                | InvocationTargetException e) {
                            continue;
                        }
                    }

                    // fill each tab with spells
                    for (int spellLevel = Spell.MIN_SPELL_LEVEL; spellLevel <= maxLevelSpellSlot; spellLevel++) {
                        List<Spell> unknownSpells = spellService.findAllUnknownClassSpellsByLevel(spellLevel, this.character.getId(),
                                this.chosenClass.getId());
                        List<Spell> knownSpells = spellService.findAllKnownClassSpellsByLevel(spellLevel, this.character.getId(),
                                this.chosenClass.getId());
                        unknownSpellLayout[spellLevel] = new VerticalLayout();
                        final int index = spellLevel;

                        for (Spell spell : unknownSpells) {
                            Button button = new SpellButton(spell);
                            button.addClickListener(new ClickListener() {

                                private static final long serialVersionUID = 2229312481145894791L;

                                @Override
                                public void buttonClick(ClickEvent event) {
                                    Button source = event.getButton();
                                    if (source.getParent().equals(unknownSpellLayout[index])) {
                                        unknownSpellLayout[index].removeComponent(source);
                                        knownSpellLayout.addComponent(source);
                                    } else {
                                        knownSpellLayout.removeComponent(source);
                                        unknownSpellLayout[index].addComponent(source);
                                    }
                                    //refresh spell availability
                                    for (int i = 1; i <= 9; i++) {
                                        unknownSpellLayout[i].setEnabled(knownSpellLayout.getComponentCount() < maxNumberOfSpellToChoose.intValue());
                                    }

                                }
                            });
                            unknownSpellLayout[spellLevel].addComponent(button);
                        }

                        for (Spell spell : knownSpells) {
                            Button button = new Button(spell.getName());
                            button.setWidth("100%");
                            button.setStyleName(ValoTheme.BUTTON_LARGE);
                            button.setData(spell);
                            knownSpellLayout.addComponent(button);
                        }

                        Tab spellTab = tabs.addTab(unknownSpellLayout[spellLevel], String.valueOf(spellLevel));
                        spellTab.setClosable(false);
                        spellTab.setEnabled(true);
                        spellTab.setDescription(Messages.getInstance().getMessage("spellStep.spell.tab.caption", spellLevel));

                    }

                    panel.setSecondComponent(knownCantripLayout);
                }

                tabs.addSelectedTabChangeListener(new SelectedTabChangeListener() {

                    private static final long serialVersionUID = 7305091177090155153L;

                    @Override
                    public void selectedTabChange(SelectedTabChangeEvent event) {
                        TabSheet tabSheet = event.getTabSheet();
                        if (tabSheet.getTabPosition(tabSheet.getTab(tabSheet.getSelectedTab())) == Spell.CANTRIP_LEVEL) {
                            panel.setSecondComponent(knownCantripLayout);
                        } else {
                            panel.setSecondComponent(knownSpellLayout);
                        }

                    }
                });

            }

        }

    }

    private void getSpellWindow(Spell spell) {
        Messages messages = Messages.getInstance();
        Window window = new Window(spell.getName());
        window.setModal(true);
        window.setWidth("60%");

        CollectionToStringConverter converter = new CollectionToStringConverter();

        FormLayout layout = new FormLayout();
        DSLabel componentType = new DSLabel(messages.getMessage("spellStep.component.label"),
                converter.convertToPresentation(spell.getComponentTypes(), new ValueContext()));
        DSLabel text = new DSLabel(messages.getMessage("spellStep.description.label"), spell.getDescription());
        layout.addComponents(componentType, text);

        //TODO : other useful info

        window.setContent(layout);
        UI.getCurrent().addWindow(window);
    }

    private class SpellButton extends Button {

        private static final long serialVersionUID = -2039909976963581426L;

        public SpellButton(Spell spell) {
            super();
            setCaption(spell.getName());
            setWidth("100%");
            setStyleName(ValoTheme.BUTTON_LARGE);
            setData(spell);
            setDescription(spell.getDescription());

            addContextClickListener(event -> {
                if (event != null && event.getButton() == MouseButton.RIGHT) {
                    SpellButton button = (SpellButton) event.getSource();
                    getSpellWindow(button.getData());
                }
            });
            addClickListener(event -> spellSound.play());
            addClickListener(event -> {
                adjustButtons();
            });
        }

        @Override
        public Spell getData() {
            return (Spell) super.getData();
        }
    }

    @Override
    protected void adjustSaveButtonState() {
        if (isBound()) {
            boolean allSpellAssigned = true;
            //            boolean allSpellAssigned = (totalNbSpell == knownCantripLayout.getComponentCount()
            //                    + knownSpellLayout.getComponentCount());
            getSaveButton().setEnabled(allSpellAssigned && getBinder().isValid());
        }
    }

    @Override
    public void onSave(CharacterClass entity) {

        List<Spell> characterKnownSpells = new ArrayList<>();
        Iterator<Component> iteratorCantrip = knownCantripLayout.iterator();
        while (iteratorCantrip.hasNext()) {
            Component c = iteratorCantrip.next();
            if (c instanceof Button) {
                Button b = (Button) c;
                Spell cantrip = (Spell) b.getData();
                characterKnownSpells.add(cantrip);
            }
        }
        Iterator<Component> iteratorSpell = knownSpellLayout.iterator();
        while (iteratorSpell.hasNext()) {
            Component c = iteratorSpell.next();
            if (c instanceof Button) {
                Button b = (Button) c;
                Spell spell = (Spell) b.getData();
                characterKnownSpells.add(spell);
            }
        }

        entity.setKnownSpells(characterKnownSpells);
    }

}
