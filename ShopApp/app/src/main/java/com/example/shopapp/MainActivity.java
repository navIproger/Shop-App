package com.example.shopapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Button;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private File file = null;
    private File file_data = null;

    static List<Item> itemList = new ArrayList<>();
    static Stats stats = new Stats(0, 0, 0);

    private final ActionFragment aF = new ActionFragment();
    private final MainFragment mF = new MainFragment();
    private final ListFragment lF = new ListFragment();

    Button main, list, action;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startProject();

        main = findViewById(R.id.main);
        list = findViewById(R.id.list);
        action = findViewById(R.id.action);

        setNewFragment(mF);

        main.setOnClickListener(view -> setNewFragment(mF));
        list.setOnClickListener(view -> setNewFragment(lF));
        action.setOnClickListener(view -> setNewFragment(aF));
    }

    @Override
    protected void onPause() {
        super.onPause();

        writeToFile(1);
        writeToFile(2);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        writeToFile(1);
        writeToFile(2);
    }

    private void setNewFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame, fragment);
        ft.commit();
    }

    public void startProject() {
        file = new File(getFilesDir(), "Item.item");
        file_data = new File(getFilesDir(), "stats.stats");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            if (file.length() != 0) {
                readFromFile(1);
            }
            if (!file_data.exists()) {
                file_data.createNewFile();
            }
            if (file_data.length() != 0) {
                readFromFile(2);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeToFile(int i) {
        file = new File(getFilesDir(), "Item.item");
        file_data = new File(getFilesDir(), "stats.stats");
        if (i == 1) {
            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))) {
                objectOutputStream.writeObject(itemList);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (i == 2) {
            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file_data))) {
                objectOutputStream.writeObject(stats);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void readFromFile(int i) {
        file = new File(getFilesDir(), "Item.item");
        file_data = new File(getFilesDir(), "stats.stats");
        if (i == 1) {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
                itemList = (List<Item>) objectInputStream.readObject();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (i == 2) {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file_data))) {
                stats = (Stats) objectInputStream.readObject();
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }

}