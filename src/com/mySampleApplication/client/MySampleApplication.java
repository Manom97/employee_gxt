package com.mySampleApplication.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.Viewport;


public class MySampleApplication implements EntryPoint {


    private ContentPanel cp;
    public void onModuleLoad() {
         cp = new ContentPanel();
    cp.setHeading("Employee Catalog");
    cp.getElement().getStyle().setProperty("border","1px solid blue");
    cp.add(new TabWindowProvider().asWidget());

        Viewport vp = new Viewport();

        vp.add(cp);

        RootPanel.get().add(vp);



    }



}
