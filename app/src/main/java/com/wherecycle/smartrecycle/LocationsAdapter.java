package com.wherecycle.smartrecycle;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.wherecycle.smartrecycle.model.Locations;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by james on 27/12/2017.
 */

public class LocationsAdapter extends ArrayAdapter<Locations>{

    private Context myContext;
    private List<Locations> locationsList = new ArrayList<Locations>();

    public LocationsAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Locations> objects) {
        super(context, resource, objects);
        myContext = context;
        locationsList = objects;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View listItem = convertView;
        if(listItem == null){
            listItem = LayoutInflater.from(myContext).inflate(R.layout.list_locations, parent, false);
        }
    }
}