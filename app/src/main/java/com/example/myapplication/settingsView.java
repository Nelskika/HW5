package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class settingsView extends AppCompatActivity {

    UnitsConverter.LengthUnits lFromUnit, lToUnit;
    UnitsConverter.VolumeUnits vFromUnit, fToUnit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        boolean isVol = false;
        Intent payload = getIntent();
        if (payload.hasExtra("mode")){
            isVol = payload.getBooleanExtra("mode",false);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Spinner toSpinner = (Spinner)findViewById(R.id.toSpinner);
        Spinner fromSpinner = (Spinner)findViewById(R.id.fromSpinner);

        final boolean retVol = isVol;






        String[] lengthOpts = new String[] {
                "Yards", "Meters", "Miles"};

        String[] volOpts = new String[] {
                "Gallons", "Liters", "Quarts"};

        ArrayAdapter<String> adapter;

        if(!isVol) {
            adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, lengthOpts);
        }else {
            adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, volOpts);
        }

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toSpinner.setAdapter(adapter);


            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            fromSpinner.setAdapter(adapter);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent();
                if(retVol){
                    intent.putExtra("toUnit", toSpinner.getSelectedItem().toString());
                    intent.putExtra("fromUnit", fromSpinner.getSelectedItem().toString());
                }else{
                    intent.putExtra("toUnit", toSpinner.getSelectedItem().toString());
                    intent.putExtra("fromUnit", fromSpinner.getSelectedItem().toString());
                }

                setResult(RESULT_OK, intent);
                finish();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
