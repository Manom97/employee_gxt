package com.mySampleApplication.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.TabPanel;


public class TabWindowProvider implements IsWidget {



    public MySampleApplicationServiceAsync caller = GWT.create(MySampleApplicationService.class);


 private String user="";

    public String getUser(){

            caller.getStatus("Hello",new AsyncCallback<String>(){


            @Override
            public void onFailure(Throwable caught) {

                Window.alert(caught.toString());

            }

            @Override
            public void onSuccess(String result) {


                user = result;

                Window.alert("welcome " + user);

            }
        });

        return user;
    }


    @Override
    public Widget asWidget() {

        TabPanel tabs = null;
        if(tabs == null){
            tabs = new TabPanel();
            tabs.setWidth("600");
            tabs.add(new FormProvider().asWidget(),"Add Employee");
            tabs.add(new BasicGrid(),"Employee List");
        }
        return tabs;
    }




}
