package com.dungeonstory.ui.view;

import java.util.ArrayList;
import java.util.List;

import com.dungeonstory.ui.util.DSTheme;
import com.dungeonstory.ui.util.ViewConfig;
import com.vaadin.navigator.View;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Composite;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.VerticalLayout;

@ViewConfig(uri = "sources", displayName = "Sources")
public class SourceView extends Composite implements View {
    
    private static final long serialVersionUID = 5443345955597338264L;

    public SourceView() {

        VerticalLayout layout = new VerticalLayout();
        
        Label caption = new Label("Sources");
        caption.addStyleName(DSTheme.LABEL_HUGE);
        layout.addComponents(caption);

        List<Link> linkList = new ArrayList<Link>();

        Link aidedd = new Link("AideDD", new ExternalResource("http://www.aidedd.org/"));
        linkList.add(aidedd);
        Link gemmaline = new Link("Gemmaline", new ExternalResource("http://www.gemmaline.com/faerun/"));
        linkList.add(gemmaline);
        Link frwiki = new Link("Wiki des royaumes oubliés",
                new ExternalResource("https://fr.wikipedia.org/wiki/Portail:Royaumes_oubli%C3%A9s"));
        linkList.add(frwiki);

        for (Link link : linkList) {
            link.setTargetName("_blank");
            layout.addComponent(link);
        }
        
        setCompositionRoot(layout);
    }

}
