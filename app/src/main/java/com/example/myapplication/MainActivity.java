package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import android.widget.EditText;
import androidx.appcompat.widget.*;

import com.google.android.material.snackbar.Snackbar;

import android.view.inputmethod.InputMethodManager;




public class MainActivity extends AppCompatActivity {

    boolean isVol = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Button calcButton = (Button)findViewById(R.id.Calculate);
        Button clearButton =(Button)findViewById(R.id.clear);
        Button modeButton = (Button)findViewById(R.id.mode);
        TextView from = (TextView)findViewById(R.id.fromFeild);
        TextView to = (TextView)findViewById(R.id.toFeild);
        TextView fromLabel = (TextView)findViewById(R.id.fromLabel);
        TextView toLabel = (TextView)findViewById(R.id.toUnits);


        UnitsConverter.VolumeUnits fromVolume = UnitsConverter.VolumeUnits.Gallons;
        UnitsConverter.VolumeUnits toVolume = UnitsConverter.VolumeUnits.Liters;

        UnitsConverter.LengthUnits fromLength = UnitsConverter.LengthUnits.Meters;
        UnitsConverter.LengthUnits toLength = UnitsConverter.LengthUnits.Yards;

        calcButton.setOnClickListener(v -> {
            UnitsConverter con = new UnitsConverter();


            try{

              if(!isVol) {
                  double d = Double.parseDouble(from.getText().toString());
                  to.setText(con.convert(d, fromLength, toLength) + "");
                  hideKeyboard(v);
              }else{
                  double d = Double.parseDouble(from.getText().toString());
                  to.setText(con.convert(d, fromVolume, toVolume) + "");
                  hideKeyboard(v);
              }
            }catch (Exception e){
            }
        });


        clearButton.setOnClickListener( v->{
            from.setText("");
            to.setText("");
            hideKeyboard(v);

        });

        modeButton.setOnClickListener(v -> {
            changeMode();
            hideKeyboard(v);
            if(!isVol){
                fromLabel.setText(fromLength.toString());
                toLabel.setText(toLength.toString());
            }else{
                fromLabel.setText(fromVolume.toString());
                toLabel.setText(toVolume.toString());
            }
        });


    }
    private  void changeMode(){
        isVol = !isVol;
        System.out.println(isVol);
    }
    private void hideKeyboard(View v){
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(),0);
    }




}
