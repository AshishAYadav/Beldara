package com.ashish.beldara;

public class Beldara {
    private String Name, Model, Number;
    private int id;

    Beldara(int id, String name, String number, String model){
        this.id = id;
        this.Name = name;
        this.Model = model;
        this.Number = number;
    }

    public String getName() {
        return this.Name;
    }

    public int getId() {
        return this.id;
    }

    public String getModel() {
        return this.Model;
    }

    public String getNumber() {
        return this.Number;
    }
}
