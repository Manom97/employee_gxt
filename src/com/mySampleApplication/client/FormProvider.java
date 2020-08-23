package com.mySampleApplication.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.button.ButtonBar;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FieldSet;
import com.sencha.gxt.widget.core.client.form.TextField;

public class FormProvider extends BasicGrid implements IsWidget  {

    private VerticalLayoutContainer widget;
    private TextField nameTxt;
    private TextField contactTxt;
    private TextField addTxt;


    public Widget asWidget(){

        if( widget == null){

             nameTxt = new TextField();
            contactTxt = new TextField();
            addTxt = new TextField();


            FieldLabel flabel2 = new FieldLabel(nameTxt,"Enter Name ");
            FieldLabel flabel3 = new FieldLabel(contactTxt,"Enter Contact ");
            FieldLabel flabel4 = new FieldLabel(addTxt,"Enter Address ");

            ButtonBar  btnBar = new ButtonBar();
            Button save = new Button("Save");
            save.getElement().getStyle().setProperty("width","100");
            save.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {

                        InsertEmp();
                }
            });
            Button clear = new Button("Clear");
            clear.getElement().getStyle().setProperty("width","100");
            clear.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {

                    nameTxt.setText("");
                    contactTxt.setText("");
                    addTxt.setText("");
                }
            });

            btnBar.add(save);
            btnBar.add(clear);

            VerticalLayoutContainer vbox = new VerticalLayoutContainer();
            vbox.add(flabel2);
            vbox.add(flabel3);
            vbox.add(flabel4);
            vbox.add(btnBar);

            FieldSet fset = new FieldSet();
            fset.getElement().getStyle().setProperty("border","none");
            fset.setCollapsible(true);
            fset.setHeading("Employee Details");
            fset.add(vbox);

            widget = new VerticalLayoutContainer();
            widget.add(fset, new VerticalLayoutContainer.VerticalLayoutData(1,-1,new Margins(20)));

        }

        return widget;
    }




    AsyncCallback<InsertEmp> insertEmpAsyncCallback = new AsyncCallback<InsertEmp>() {
        @Override
        public void onFailure(Throwable caught) {
            Window.alert("Failed to add employee \n" + caught.toString());

        }

        @Override
        public void onSuccess(InsertEmp result) {

        Window.alert("Employee added Successfully.");
        nameTxt.setText("");
        contactTxt.setText("");
        addTxt.setText("");

        

        }
    };

    MySampleApplicationServiceAsync service = GWT.create(MySampleApplicationService.class);


    public void InsertEmp(){
        if(service == null){
            service = GWT.create(MySampleApplication.class);
        }
        if(nameTxt.getText() == "" || contactTxt.getText() == "" || addTxt.getText() == ""){
            Window.alert("Field can not be blank!!!");
            return;
        }


        service.insertEmp(nameTxt.getText(),Integer.parseInt(contactTxt.getText()),addTxt.getText(),insertEmpAsyncCallback);

    }


}
