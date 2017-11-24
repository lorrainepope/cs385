package com.wherecycle.smartrecycle.model;

public class RecycleableItem {

    private RecycleableType _type;

    public RecycleableItem(RecycleableType type) {
        _type = type;
    }

    public RecycleableType getType() {
        return _type;
    }

    public void setType(RecycleableType _type) {
        this._type = _type;
    }
}
