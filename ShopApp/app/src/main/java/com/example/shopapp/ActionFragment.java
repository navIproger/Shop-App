package com.example.shopapp;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class ActionFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_action, container, false);

        EditText price, wlsPrice, amount, code, name;
        Button add;

        price = view.findViewById(R.id.price);
        wlsPrice = view.findViewById(R.id.wlsPrice);
        amount = view.findViewById(R.id.amount);
        code = view.findViewById(R.id.code);
        name = view.findViewById(R.id.name);
        add = view.findViewById(R.id.add);

        add.setOnClickListener(view1 -> check(new Item(Double.parseDouble(price.getText().toString()), Integer.parseInt(wlsPrice.getText().toString()), Integer.parseInt(amount.getText().toString()), code.getText().toString(), name.getText().toString())));


        return view;
    }

    private void showAlert(String text, Item itemInList, Item newItem) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(text)
                .setMessage("Змінити дані, або перезаписати існуючі дані?")
                .setCancelable(false)
                .setPositiveButton("Змінити", (dialogInterface, i) -> dialogInterface.cancel())
                .setNegativeButton("Перезаписати", (dialogInterface, i) -> {
                    itemInList.setAmount(itemInList.getAmount() + newItem.getAmount());
                    itemInList.setPrise(newItem.getPrise());
                    itemInList.setWlsPrise(newItem.getWlsPrise());
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void check(Item item) {
        boolean check = false;
        for (Item items : MainActivity.itemList) {
            if (items.getName().equals(item.getName()) && items.getCode().equals(item.getCode())) {
                showAlert("\nПредмет з таким іменем і кодом, вже існує!\n", items, item);
                check = true;
                break;
            } else if (items.getName().equals(item.getName())) {
                showAlert("\nПредмет з таким іменем, вже існує!\n", items, item);
                check = true;
                break;
            } else if (items.getCode().equals(item.getCode())) {
                showAlert("\nПредмет з таким кодом, вже існує!\n", items, item);
                check = true;
                break;
            }
        }
        if (!check) {
            MainActivity.itemList.add(item);
        }
    }
}