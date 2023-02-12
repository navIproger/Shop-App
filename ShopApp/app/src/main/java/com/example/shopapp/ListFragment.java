package com.example.shopapp;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class ListFragment extends Fragment {

    static ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        listView = view.findViewById(R.id.listView);
        BaseAdapter adapter = new MyAdapter(getActivity().getApplicationContext(), MainActivity.itemList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((adapterView, view12, i, l) -> Toast.makeText(getActivity().getApplicationContext(), "Ціна закупки: " + MainActivity.itemList.get(i).getWlsPrise() + "\nКод: " + MainActivity.itemList.get(i).getCode(), Toast.LENGTH_SHORT).show());

        listView.setOnItemLongClickListener((adapterView, view1, position, l) -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("Видалення")
                    .setCancelable(false)
                    .setMessage("Видалити?")
                    .setNegativeButton("Ні", (dialogInterface, i) -> dialogInterface.cancel())
                    .setPositiveButton("Так", (dialogInterface, i) -> {
                        MainActivity.itemList.remove(position);
                        BaseAdapter adapter1 = new MyAdapter(getActivity().getApplicationContext(), MainActivity.itemList);
                        listView.setAdapter(adapter1);
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
            return true;
        });

        return view;
    }
}