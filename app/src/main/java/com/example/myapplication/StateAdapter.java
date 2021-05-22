package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class StateAdapter extends ArrayAdapter<State> {

    private LayoutInflater inflater;
    private int layout;
    private List<State> states;

    public StateAdapter(Context context, int resource, List<State> states) {
        super(context, resource, states);
        this.states = states;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View view=inflater.inflate(this.layout, parent, false);

        TextView where = (TextView) view.findViewById(R.id.name);
        TextView desc = (TextView) view.findViewById(R.id.capital);
        TextView date = (TextView) view.findViewById(R.id.nowDate);
        TextView date2 = (TextView) view.findViewById(R.id.nowDate2);
        TextView status = (TextView) view.findViewById(R.id.status);

        State state = states.get(position);

        where.setText(state.getSysName());
        desc.setText(state.getDescription());
        date2.setText(state.getNowDate().toString().replace('T', ' '));
        date.setText(state.getLastDate().toString().replace('T', ' '));
        status.setText(state.getStatus());

        return view;
    }
}
