package com.dungeonstory.view.character;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;

import org.dussan.vaadin.dtabs.DTabs;
import org.vaadin.viritin.label.MLabel;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.CharacterClass;
import com.dungeonstory.backend.data.ClassSpellSlots;
import com.dungeonstory.backend.data.DSClass;
import com.dungeonstory.backend.data.DSClass.SpellCastingType;
import com.dungeonstory.backend.data.Spell;
import com.dungeonstory.backend.data.util.ClassUtil;
import com.dungeonstory.backend.service.impl.SpellService;
import com.dungeonstory.form.DSAbstractForm;
import com.dungeonstory.util.layout.VerticalSpacedLayout;
import com.vaadin.data.util.converter.StringToCollectionConverter;
import com.vaadin.shared.MouseEventDetails.MouseButton;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.TabSheet.SelectedTabChangeListener;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

public class SpellChoiceForm extends DSAbstractForm<Character> {

    private static final long serialVersionUID = 7418266123213990672L;

    private SpellService spellService = SpellService.getInstance();

    private DSClass classe;

    private MLabel label;

    private DTabs                tabs  = null;
    private HorizontalSplitPanel panel = null;

    VerticalLayout unknownCantripLayout = new VerticalLayout();
    VerticalLayout knownCantripLayout   = new VerticalLayout();

    VerticalLayout[] unknownSpellLayout = new VerticalLayout[Spell.MAX_SPELL_LEVEL + 1];
    VerticalLayout   knownSpellLayout   = new VerticalLayout();

    public SpellChoiceForm() {
        super();
    }

    public void setClass(DSClass classe) {
        this.classe = classe;
    }

    @Override
    protected Component createContent() {

        VerticalSpacedLayout layout = new VerticalSpacedLayout();

        label = new MLabel();
        layout.addComponent(label);

        tabs = new DTabs();
        tabs.setTabBarBottom(true).setFramedTabs(true);

        panel = new HorizontalSplitPanel();
        panel.setSizeFull();
        panel.setCaption("Choix de sorts");
        panel.setFirstComponent(tabs);
        layout.addComponent(panel);

        return layout;
    }

    @Override
    public void afterSetEntity() {

        Optional<CharacterClass> assignedClass = ClassUtil.getCharacterClass(getEntity(), this.classe);

        if (assignedClass.isPresent()) {

            int classLevel = assignedClass.get().getClassLevel();
            label.setValue("Classe = " + assignedClass.get().toString());

            Optional<ClassSpellSlots> spellSlotOpt = ClassUtil.getClassSpellSlots(this.classe, classLevel);

            if (spellSlotOpt.isPresent()) {
                ClassSpellSlots spellSlot = spellSlotOpt.get();

                //number of spell to choose
                final Integer maxNumberOfSpellToChoose;
                final Integer maxNumberOfCantripToChoose;
                maxNumberOfCantripToChoose = spellSlot.getCantripsKnown();
                if (this.classe.getSpellCastingType() == SpellCastingType.KNOWN) {
                    maxNumberOfSpellToChoose = spellSlot.getSpellsKnown();
                } else {
                    maxNumberOfSpellToChoose = 6;
                }

                if (maxNumberOfCantripToChoose != null) {

                    List<Spell> unknownCantrips = spellService.findAllUnknownClassSpellsByLevel(Spell.CANTRIP_LEVEL,
                            getEntity().getId(), this.classe.getId());
                    List<Spell> knownCantrips = spellService.findAllKnownClassSpellsByLevel(Spell.CANTRIP_LEVEL,
                            getEntity().getId(), this.classe.getId());

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
                                unknownCantripLayout.setEnabled(
                                        knownCantripLayout.getComponentCount() < maxNumberOfCantripToChoose.intValue());

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
                    tab.setDescription("Sorts mineurs");
                }

                if (maxNumberOfSpellToChoose != null) {

                    //get max level spell slot
                    int maxLevelSpellSlot = 0;
                    for (int spellLevel = Spell.MIN_SPELL_LEVEL; spellLevel <= Spell.MAX_SPELL_LEVEL; spellLevel++) {
                        try {
                            Method method = spellSlot.getClass().getDeclaredMethod("getSpellSlots" + spellLevel);
                            Integer nbSpells = (Integer) method.invoke(spellSlot);
                            if (nbSpells != null) {
                                maxLevelSpellSlot = spellLevel;
                            }
                        } catch (NoSuchMethodException | SecurityException | IllegalAccessException
                                | IllegalArgumentException | InvocationTargetException e) {
                            continue;
                        }
                    }

                    // fill each tab with spells
                    for (int spellLevel = Spell.MIN_SPELL_LEVEL; spellLevel <= maxLevelSpellSlot; spellLevel++) {
                        List<Spell> unknownSpells = spellService.findAllUnknownClassSpellsByLevel(spellLevel,
                                getEntity().getId(), this.classe.getId());
                        List<Spell> knownSpells = spellService.findAllKnownClassSpellsByLevel(spellLevel,
                                getEntity().getId(), this.classe.getId());
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
                                        unknownSpellLayout[i].setEnabled(knownSpellLayout
                                                .getComponentCount() < maxNumberOfSpellToChoose.intValue());
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
                        spellTab.setDescription("Sorts niveau " + spellLevel);

                    }

                    panel.setSecondComponent(knownCantripLayout);
                }

                tabs.addSelectedTabChangeListener(new SelectedTabChangeListener() {

                    private static final long serialVersionUID = 7305091177090155153L;

                    @Override
                    public void selectedTabChange(SelectedTabChangeEvent event) {
                        TabSheet tabSheet = event.getTabSheet();
                        if (tabSheet
                                .getTabPosition(tabSheet.getTab(tabSheet.getSelectedTab())) == Spell.CANTRIP_LEVEL) {
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
        Window window = new Window(spell.getName());
        window.setModal(true);
        window.setWidth("60%");

        StringToCollectionConverter converter = new StringToCollectionConverter();

        FormLayout layout = new FormLayout();
        MLabel componentType = new MLabel("Type de composant",
                converter.convertToPresentation(spell.getComponentTypes(), String.class, null));
        MLabel text = new MLabel("Description", spell.getDescription()).withFullWidth();
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
        }

        @Override
        public Spell getData() {
            return (Spell) super.getData();
        }
    }

}
