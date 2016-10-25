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
import com.dungeonstory.backend.data.Spell;
import com.dungeonstory.backend.data.util.ClassUtil;
import com.dungeonstory.backend.service.impl.SpellService;
import com.dungeonstory.form.DSAbstractForm;
import com.dungeonstory.util.field.DSSubSetSelector;
import com.dungeonstory.util.layout.VerticalSpacedLayout;
import com.vaadin.ui.AbstractSelect.ItemDescriptionGenerator;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.TabSheet.SelectedTabChangeListener;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class SpellChoiceForm extends DSAbstractForm<Character> {

    private static final long serialVersionUID = 7418266123213990672L;

    private SpellService     spellService     = SpellService.getInstance();
    
    private DSClass classe;
    
    private MLabel label;
    private DSSubSetSelector<Spell> cantrips;

    private DTabs              tabs = null;
    private HorizontalSplitPanel panel;

    VerticalLayout unknownCantripLayout = new VerticalLayout();
    VerticalLayout knownCantripLayout   = new VerticalLayout();

    VerticalLayout[] unknownSpellLayout = new VerticalLayout[10];
    VerticalLayout knownSpellLayout   = new VerticalLayout();

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
        cantrips = new DSSubSetSelector<Spell>(Spell.class);
        cantrips.setCaption("Sorts mineurs");
        cantrips.setVisibleProperties("name", "school");
        cantrips.setColumnHeader("name", "Sort");
        cantrips.setColumnHeader("school", "École de magie");

        cantrips.getTable().setItemDescriptionGenerator(new ItemDescriptionGenerator() {

            private static final long serialVersionUID = -3475401659477278855L;

            @Override
            public String generateDescription(Component source, Object itemId, Object propertyId) {
                Spell spell = (Spell) itemId;
                return spell.getDescription();
            }
        });

        layout.addComponent(label);
        layout.addComponent(cantrips);

        panel = new HorizontalSplitPanel();
        panel.setSizeFull();

        tabs = new DTabs();
        tabs.setTabBarBottom(true).setFramedTabs(true);


        //        for (int index = 1; index <= 5; index++) {
        //            String tabcaption = "aaaa";
        //            VerticalLayout content = new VerticalLayout();
        //            content.setMargin(true);
        //            content.setSpacing(true);
        //            content.addComponent(new Label("Content for tab " + index));
        //            if (index == 2) {
        //                content.addComponent(new Label(
        //                        "Excepteur sint obcaecat cupiditat non proident culpa. Magna pars studiorum, prodita quaerimus."));
        //            }
        //
        //            Tab tab = tabs.addTab(content, tabcaption);
        //            tab.setClosable(true);
        //            tab.setEnabled(true);
        //            //            tab.setIcon(icons[index - 1]);
        //
        //            // First tab is always visible
        //            if (index == 1) {
        //                tab.setEnabled(true);
        //                tab.setClosable(false);
        //            }
        //        }

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

            Optional<ClassSpellSlots> spellSlotOpt = this.classe.getSpellSlots().stream()
                    .filter(slot -> slot.getLevel().getId().intValue() == classLevel).findFirst();
            if (spellSlotOpt.isPresent()) {
                ClassSpellSlots spellSlot = spellSlotOpt.get();
                Integer nbCantrips = spellSlot.getCantripsKnown();
                if (nbCantrips != null) {
                    List<Spell> unknownCantrips = spellService.findAllUnknownClassSpellsByLevel(0, getEntity().getId(),
                            this.classe.getId());
                    cantrips.setOptions(unknownCantrips);
                    List<Spell> knownCantrips = spellService.findAllKnownClassSpellsByLevel(0, getEntity().getId(),
                            this.classe.getId());
                    cantrips.setValue(knownCantrips);

                    // fill cantrips
                    for (Spell cantrip : unknownCantrips) {
                        Button button = new Button(cantrip.getName());
                        button.setWidth("100%");
                        button.setStyleName(ValoTheme.BUTTON_LARGE);
                        button.setData(cantrip);
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

                    panel.setSecondComponent(knownCantripLayout);

                    Tab tab = tabs.addTab(unknownCantripLayout, "0");
                    tab.setClosable(false);
                    tab.setEnabled(true);

                    //get max level spell slot
                    int maxLevelSpellSlot = 0;
                    for (int spellLevel = 1; spellLevel <= 9; spellLevel++) {
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
                    for (int spellLevel = 1; spellLevel <= maxLevelSpellSlot; spellLevel++) {
                        List<Spell> unknownSpells = spellService.findAllUnknownClassSpellsByLevel(spellLevel,
                                getEntity().getId(), this.classe.getId());
                        List<Spell> knownSpells = spellService.findAllKnownClassSpellsByLevel(spellLevel,
                                getEntity().getId(), this.classe.getId());
                        unknownSpellLayout[spellLevel] = new VerticalLayout();
                        final int index = spellLevel;

                        for (Spell spell : unknownSpells) {
                            Button button = new Button(spell.getName());
                            button.setWidth("100%");
                            button.setStyleName(ValoTheme.BUTTON_LARGE);
                            button.setData(spell);
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

                        Tab tab2 = tabs.addTab(unknownSpellLayout[spellLevel], String.valueOf(spellLevel));
                        tab2.setClosable(false);
                        tab2.setEnabled(true);

                    }

                }

                tabs.addSelectedTabChangeListener(new SelectedTabChangeListener() {

                    private static final long serialVersionUID = 7305091177090155153L;

                    @Override
                    public void selectedTabChange(SelectedTabChangeEvent event) {
                        TabSheet tabSheet = event.getTabSheet();
                        if (tabSheet.getTabPosition(tabSheet.getTab(tabSheet.getSelectedTab())) == 0) {
                            panel.setSecondComponent(knownCantripLayout);
                        } else {
                            panel.setSecondComponent(knownSpellLayout);
                        }

                    }
                });

            }

        }

    }

}
