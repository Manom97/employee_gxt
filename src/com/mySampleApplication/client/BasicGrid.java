package com.mySampleApplication.client;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.google.gwt.cell.client.TextButtonCell;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.Composite;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.GridView;

public class BasicGrid extends Composite {
    // Basic model for each row in the grid
    public class NameModel {
        private int id;
        private String name;
        private String Address;

        public NameModel(int id, String name, String Address){
            this.id = id;
            this.name = name;
            this.Address = Address;

        }


        public String getAddress() {
            return Address;
        }

        public void setAddress(String address) {
            Address = address;
        }

        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }

    List<NameModel> getEmp(){

        List<NameModel> list = new ArrayList<NameModel>();

        list.add(new NameModel(1, " Manom", "Powai road"));
        list.add(new NameModel(2, " Badman", "Powai road"));
        list.add(new NameModel(3, " Shailendra", "Powai road"));
        list.add(new NameModel(4, " Anand", "Powai road"));


    return list;
    }




    // Property access definitions for the values in the NameModel
    public interface GridProperties extends PropertyAccess<NameModel> {
        ModelKeyProvider<NameModel> id();
        ValueProvider<NameModel, String> name();
        ValueProvider<NameModel,String> Address();
    }

    // Setup the property access definitions for the values for the grid columns
    public static GridProperties gridProperties = GWT.create(GridProperties.class);

    public BasicGrid() {
        // Setup the ListStore.

        ListStore<NameModel> listStore = new ListStore<NameModel>(gridProperties.id());

        listStore.addAll(getEmp());


        // Setup the grid columns

        //ColumnConfig<NameModel, String> actionCol = new ColumnConfig<NameModel,String>(null,50,"Action");
        ColumnConfig<NameModel, String> nameCol = new ColumnConfig<NameModel, String>(gridProperties.name(), 50, "Name");
        ColumnConfig<NameModel, String> addCol = new ColumnConfig<>(gridProperties.Address(),50,"Address");

        TextButtonCell button = new TextButtonCell(){
            @Override
            public boolean handlesSelection() {
                return false;
            }
        };

        //actionCol.setCell(button);
        List<ColumnConfig<NameModel, ?>> columns = new ArrayList<ColumnConfig<NameModel, ?>>();
       // columns.add(actionCol);
        columns.add(nameCol);
        columns.add(addCol);
        ColumnModel<NameModel> columnModel = new ColumnModel<NameModel>(columns);

        // Setup the grid view for styling
        GridView<NameModel> gridView = new GridView<NameModel>();
        gridView.setAutoExpandColumn(addCol);

        // Setup the grid
        Grid<NameModel> grid = new Grid<NameModel>(listStore, columnModel, gridView);
        // Setup a size if not depending on the parent container to give it a size.
        grid.setPixelSize(500, 200);

        initWidget(grid);
    }
}