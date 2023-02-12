package com.example.shopapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import java.util.List;

public class MyAdapter extends BaseAdapter {

    private final List<Item> itemList;
    private final Context context;

    public MyAdapter(Context context, List<Item> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int i) {
        return itemList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View contextView, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.list, viewGroup, false);

        TextView name, price, amount;
        Button btn_sell, btn_edit;

        name = view.findViewById(R.id.nameList);
        amount = view.findViewById(R.id.amountList);
        price = view.findViewById(R.id.priceList);

        btn_sell = view.findViewById(R.id.btn_sell);
        btn_edit = view.findViewById(R.id.btn_edit);

        btn_sell.setOnClickListener(view1 -> {
            if (MainActivity.itemList.get(position).getAmount() > 0) {
                MainActivity.itemList.get(position).setAmount(MainActivity.itemList.get(position).getAmount() - 1);
                MainActivity.stats = new Stats(MainActivity.stats.getSellItem() + 1, MainActivity.stats.getProfit() + MainActivity.itemList.get(position).getPrise(), MainActivity.stats.getCleanProfit() + (MainActivity.itemList.get(position).getPrise() - MainActivity.itemList.get(position).getWlsPrise()));

                BaseAdapter adapter = new MyAdapter(context.getApplicationContext(), MainActivity.itemList);
                ListFragment.listView.setAdapter(adapter);
            }
        });

        btn_edit.setOnClickListener(view12 -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(viewGroup.getContext());
            builder.setTitle("Редагування!")
                    .setCancelable(true);

            View newView = inflater.inflate(R.layout.dialog, null);
            EditText ePrice, eWls, eAmount, eCode, eName;
            ePrice = newView.findViewById(R.id.priceDialog);
            eWls = newView.findViewById(R.id.wlsDialog);
            eAmount = newView.findViewById(R.id.amountDialog);
            eCode = newView.findViewById(R.id.codeDialog);
            eName = newView.findViewById(R.id.nameDialog);
            Button submit = newView.findViewById(R.id.submit);

            builder.setView(newView);
            AlertDialog dialog = builder.create();


            submit.setOnClickListener(v -> {
                if (eAmount.getText().toString().equals("") && eCode.getText().toString().equals("") && eName.getText().toString().equals("")) {
                    MainActivity.itemList.add(position, new Item(Double.parseDouble(ePrice.getText().toString()), Integer.parseInt(eWls.getText().toString()), MainActivity.itemList.get(position).getAmount(), MainActivity.itemList.get(position).getCode(), MainActivity.itemList.get(position).getName()));
                } else if (eAmount.getText().toString().equals("") && eCode.getText().toString().equals("")) {
                    MainActivity.itemList.add(position, new Item(Double.parseDouble(ePrice.getText().toString()), Integer.parseInt(eWls.getText().toString()), MainActivity.itemList.get(position).getAmount(), MainActivity.itemList.get(position).getCode(), eName.getText().toString()));
                } else if (eAmount.getText().toString().equals("") && eName.getText().toString().equals("")) {
                    MainActivity.itemList.add(position, new Item(Double.parseDouble(ePrice.getText().toString()), Integer.parseInt(eWls.getText().toString()), MainActivity.itemList.get(position).getAmount(), eCode.getText().toString(), MainActivity.itemList.get(position).getName()));
                } else if (eCode.getText().toString().equals("") && eName.getText().toString().equals("")) {
                    MainActivity.itemList.add(position, new Item(Double.parseDouble(ePrice.getText().toString()), Integer.parseInt(eWls.getText().toString()), Integer.parseInt(eAmount.getText().toString()), MainActivity.itemList.get(position).getCode(), MainActivity.itemList.get(position).getName()));
                } else if (eAmount.getText().toString().equals("")) {
                    MainActivity.itemList.add(position, new Item(Double.parseDouble(ePrice.getText().toString()), Integer.parseInt(eWls.getText().toString()), MainActivity.itemList.get(position).getAmount(), eCode.getText().toString(), eName.getText().toString()));
                } else if (eCode.getText().toString().equals("")) {
                    MainActivity.itemList.add(position, new Item(Double.parseDouble(ePrice.getText().toString()), Integer.parseInt(eWls.getText().toString()), Integer.parseInt(eAmount.getText().toString()), MainActivity.itemList.get(position).getCode(), eName.getText().toString()));
                } else if (eName.getText().toString().equals("")) {
                    MainActivity.itemList.add(position, new Item(Double.parseDouble(ePrice.getText().toString()), Integer.parseInt(eWls.getText().toString()), Integer.parseInt(eAmount.getText().toString()), eCode.getText().toString(), MainActivity.itemList.get(position).getName()));
                } else {
                    MainActivity.itemList.add(position, new Item(Double.parseDouble(ePrice.getText().toString()), Integer.parseInt(eWls.getText().toString()), Integer.parseInt(eAmount.getText().toString()), eCode.getText().toString(), eName.getText().toString()));
                }
                MainActivity.itemList.remove(position + 1);
                dialog.dismiss();
            });
            dialog.show();
        });

        name.setText(itemList.get(position).getName());
        price.setText(Integer.toString(itemList.get(position).getPrise()));
        amount.setText(Integer.toString(itemList.get(position).getAmount()));


        return view;
    }
}
