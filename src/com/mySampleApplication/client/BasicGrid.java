package com.mySampleApplication.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.mySampleApplication.client.ModelClass.Employee;
import com.mySampleApplication.client.ModelClass.EmployeeProperties;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.event.CompleteEditEvent;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.*;
import com.sencha.gxt.widget.core.client.grid.editing.GridEditing;
import com.sencha.gxt.widget.core.client.grid.editing.GridRowEditing;
import com.sencha.gxt.widget.core.client.toolbar.PagingToolBar;


public class BasicGrid extends Composite{
    public  List<Employee> employees = new ArrayList<Employee>();
    private MySampleApplicationServiceAsync service = GWT.create(MySampleApplicationService.class);
    private EmployeeProperties employeeProperties = GWT.create(EmployeeProperties.class);
    private ListStore<Employee> listStore;


    AsyncCallback<List<Employee>> EmployeeListCallBack = new AsyncCallback<List<Employee>>() {

        @Override
        public void onFailure(Throwable caught) {

            Window.alert("Failed to retrieve the data" +caught.toString());

        }

        @Override
        public void onSuccess(List<Employee> result) {

            employees.addAll(result);

            if(listStore !=null) {
                listStore.clear();
                listStore.addAll(employees);
            }


        }
    };

    public BasicGrid() {


        service.getEmployees(EmployeeListCallBack);
        listStore = new ListStore<Employee>(employeeProperties.key());

        ColumnConfig<Employee, String> nameCol = new ColumnConfig<Employee, String>(employeeProperties.name(),100,"Name");
        ColumnConfig<Employee, String> contCol = new ColumnConfig<Employee, String>(employeeProperties.contact(),100,"Contact");
        ColumnConfig<Employee, String> addCol = new ColumnConfig<Employee, String>(employeeProperties.address(),200,"Address");

        List<ColumnConfig<Employee,?>> columns = new ArrayList<ColumnConfig<Employee, ?>>();


        columns.add(nameCol);
        columns.add(contCol);
        columns.add(addCol);

        ColumnModel<Employee> columnModel1 = new ColumnModel<Employee>(columns);

        GridView<Employee> gridView1 = new GridView<Employee>();

//        gridView1.setAutoExpandColumn(addCol);
        Grid<Employee> grid = new Grid<Employee>(listStore,columnModel1,gridView1);


        grid.setSize("400px", "200px");


        GridEditing<Employee> editing =new  GridRowEditing<Employee>(grid);

        editing.addEditor(nameCol, new TextField());
        editing.addEditor(contCol, new TextField());
        editing.addEditor(addCol, new TextField());

        editing.addCompleteEditHandler(new CompleteEditEvent.CompleteEditHandler<Employee>() {
            @Override
            public void onCompleteEdit(CompleteEditEvent<Employee> event) {
                Window.alert("Edited but not persisted");
            }
        });

        final PagingToolBar toolBar = new PagingToolBar(20);
        toolBar.setBorders(false);
        toolBar.bind(listStore);



        VBoxLayoutContainer container = new VBoxLayoutContainer();
    container.add(grid);
    container.add(toolBar);

        initWidget(container);
    }
}