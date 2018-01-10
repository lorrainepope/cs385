package com.wherecycle.smartrecycle.model;
/*
* created by Lorraine
* RecyclableItem class gets and sets the recyclableType*/
public class RecycleableItem {

    private RecycleableType _type;//create new instance

    public RecycleableItem(RecycleableType type) {
        _type = type;
    }//constructor

    public RecycleableType getType() {
        return _type;//gets the type
    }

    public void setType(RecycleableType _type) {
        this._type = _type;
    }//sets the type
}
