package com.example.shopapp;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MainFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        Button clearArr, clearStats;
        TextView price, wls, kind, amount, sellItem, profit, cleanProfit;
        int p = 0, w = 0, a = 0, c = 0;

        price = view.findViewById(R.id.priceTotal);
        wls = view.findViewById(R.id.wlsTotal);
        kind = view.findViewById(R.id.kindTotal);
        amount = view.findViewById(R.id.amountTotal);
        sellItem = view.findViewById(R.id.sellTotal);
        profit = view.findViewById(R.id.profitTotal);
        cleanProfit = view.findViewById(R.id.cleanProfitTotal);

        for (Item item : MainActivity.itemList) {
            if (item.getAmount() > 0) {
                p += item.getPrise();
                w += item.getWlsPrise();
                a += item.getAmount();
                ++c;
            }
        }

        kind.setText(Integer.toString(c));
        price.setText(Integer.toString(p));
        wls.setText(Integer.toString(w));
        amount.setText(Integer.toString(a));
        sellItem.setText(Integer.toString(MainActivity.stats.getSellItem()));
        profit.setText(Integer.toString(MainActivity.stats.getProfit()));
        cleanProfit.setText(Integer.toString(MainActivity.stats.getCleanProfit()));

        clearArr = view.findViewById(R.id.clearArr);
        clearStats = view.findViewById(R.id.clearStats);

        clearArr.setOnClickListener(view1 -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("Очищення")
                    .setMessage("Очистити базу даних?")
                    .setCancelable(true)
                    .setPositiveButton("Так", (dialogInterface, i) -> {
                        MainActivity.itemList.clear();
                        kind.setText(Integer.toString(0));
                        price.setText(Integer.toString(0));
                        wls.setText(Integer.toString(0));
                        amount.setText(Integer.toString(0));
                    })
                    .setNegativeButton("Ні", (dialogInterface, i) -> dialogInterface.cancel());
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        });

        clearStats.setOnClickListener(view12 -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("Очищення")
                    .setMessage("Очистити статистику?")
                    .setCancelable(true)
                    .setPositiveButton("Так", (dialogInterface, i) -> {
                        MainActivity.stats = new Stats(0, 0, 0);
                        sellItem.setText(Integer.toString(MainActivity.stats.getSellItem()));
                        profit.setText(Integer.toString(MainActivity.stats.getProfit()));
                        cleanProfit.setText(Integer.toString(MainActivity.stats.getCleanProfit()));
                    })
                    .setNegativeButton("Ні", (dialogInterface, i) -> dialogInterface.cancel());
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        });

        return view;
    }


}