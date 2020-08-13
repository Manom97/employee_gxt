package com.mySampleApplication.client;

public enum HeaderConstants {

    AddHeader,
    ListHeader;

    public String toString(){
        switch (this){
            case AddHeader: return "Add Employee";
            case ListHeader: return "Employee List";
            default: throw new IllegalArgumentException();
        }

    }


}
