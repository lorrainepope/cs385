package com.wherecycle.smartrecycle;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.wherecycle.smartrecycle.model.Locations;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by james on 27/12/2017.
 */

public class LocationsAdapter extends ArrayAdapter<Locations>{

    private Context myContext;
    private List<Locations> locationsList = new ArrayList<Locations>();

    public LocationsAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Locations> objects) {    //constructor which needs context, the xml resource (layout file), and the array of objects
        super(context, resource, objects);
        myContext = context;
        locationsList = objects;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){       //this method access the array at position to grab the information and place it
    View listItem = convertView;
        if(listItem == null){
            listItem = LayoutInflater.from(myContext).inflate(R.layout.list_locations, parent, false);
        }
        Locations currLoc = locationsList.get(position);                        //these lines take the information from the current array position to fill the list
        TextView tvName = (TextView)listItem.findViewById(R.id.locName);
        tvName.setText("Name: "+currLoc.getName());
        TextView tvAdd = (TextView)listItem.findViewById(R.id.locEmail);
        tvAdd.setText("Email: "+currLoc.getEmail());
        TextView tvPhone = (TextView)listItem.findViewById(R.id.locPhone);
        tvPhone.setText("Phone: "+currLoc.getPhone());
        TextView tvLatLng = (TextView)listItem.findViewById(R.id.locLatLng);
        tvLatLng.setText("Co-ordinates: "+currLoc.getLat()+", "+currLoc.getLng());

        return listItem;
    }
}
