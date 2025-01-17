package com.example.listycity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> dataList;
    private ArrayAdapter<String> cityAdapter;
    private String selectedCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView cityList = findViewById(R.id.cityList);
        Button addCityButton = findViewById(R.id.addCityButton);
        Button deleteCityButton = findViewById(R.id.deleteCityButton);

        //  city data
        dataList = new ArrayList<>();
        dataList.add("Edmonton");
        dataList.add("Calgary");
        dataList.add("Vancouver");

        cityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
        cityList.setAdapter(cityAdapter);

        cityList.setOnItemClickListener((parent, view, position, id) -> {
            selectedCity = dataList.get(position);
        });

        addCityButton.setOnClickListener(v -> showAddCityDialog());

        deleteCityButton.setOnClickListener(v -> {
            if (selectedCity != null) {
                dataList.remove(selectedCity);
                cityAdapter.notifyDataSetChanged();
                selectedCity = null;
            }
        });
    }

    private void showAddCityDialog() {
        // dialog box to add a new city
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_add_city, null);
        EditText cityInput = dialogView.findViewById(R.id.cityInput);
        Button confirmButton = dialogView.findViewById(R.id.confirmButton);

        builder.setView(dialogView);
        AlertDialog dialog = builder.create();

        confirmButton.setOnClickListener(v -> {
            String newCity = cityInput.getText().toString().trim();
            if (!newCity.isEmpty() && !dataList.contains(newCity)) {
                dataList.add(newCity);
                cityAdapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
