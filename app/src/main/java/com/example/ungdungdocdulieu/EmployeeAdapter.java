package com.example.ungdungdocdulieu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class EmployeeAdapter extends ArrayAdapter<String> {

    private Context context;
    private List<String> employeeList;

    public EmployeeAdapter(@NonNull Context context, List<String> employeeList) {
        super(context, 0, employeeList);
        this.context = context;
        this.employeeList = employeeList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_employee, parent, false);
        }

        TextView tvItem = convertView.findViewById(R.id.tvItem);
        tvItem.setText(employeeList.get(position));

        return convertView;
    }
}
