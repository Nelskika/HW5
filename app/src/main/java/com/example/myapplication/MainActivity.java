package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.usage.UsageEvents;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.Touch;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
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
    Button calcButton ;
    Button clearButton;
    Button modeButton ;
    TextView from ;
    TextView to ;
    TextView fromLabel ;
    TextView toLabel;

    UnitsConverter.VolumeUnits fromVolume ;
    UnitsConverter.VolumeUnits toVolume ;

    UnitsConverter.LengthUnits fromLength ;
    UnitsConverter.LengthUnits toLength ;


    static final int CODE = 0;





    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_items, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent = new Intent(MainActivity.this, settingsView.class);
        intent.putExtra("mode", isVol);
        startActivityForResult(intent,CODE);

        return true;
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boolean isVol = false;
        calcButton = (Button)findViewById(R.id.Calculate);
       clearButton =(Button)findViewById(R.id.clear);
       modeButton = (Button)findViewById(R.id.mode);
       from = (TextView)findViewById(R.id.fromFeild);
       to = (TextView)findViewById(R.id.toFeild);
        fromLabel = (TextView)findViewById(R.id.fromLabel);
        toLabel = (TextView)findViewById(R.id.toUnits);


       this.fromVolume = UnitsConverter.VolumeUnits.Gallons;
        this.toVolume = UnitsConverter.VolumeUnits.Liters;

         this.fromLength = UnitsConverter.LengthUnits.Meters;
        this.toLength = UnitsConverter.LengthUnits.Yards;


        calcButton.setOnClickListener(v -> {
            UnitsConverter con = new UnitsConverter();
            try{
            if(from.getText().toString().equals("")){
                if(!this.isVol) {
                    double d = Double.parseDouble(to.getText().toString());
                    this.from.setText(con.convert(d, this.toLength, this.fromLength) + "");
                    hideKeyboard(v);
                }else{
                    double d = Double.parseDouble(to.getText().toString());
                    this.from.setText(con.convert(d, this.toVolume, this.fromVolume) + "");
                    hideKeyboard(v);
                }
            }
              if(!this.isVol) {
                  double d = Double.parseDouble(from.getText().toString());
                  this.to.setText(con.convert(d, this.fromLength, this.toLength) + "");
                  hideKeyboard(v);
              }else{
                  double d = Double.parseDouble(from.getText().toString());
                  this.to.setText(con.convert(d, this.fromVolume, this.toVolume) + "");
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
        });

        from.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                to.setText("");
                return false;
            }
        });

        to.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                from.setText("");
                return false;
            }
        });





    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode,resultCode,data);

                toLabel.setText(data.getStringExtra("toUnit"));
                fromLabel.setText(data.getStringExtra("fromUnit"));

                if(!isVol){
                    System.out.println(toLength);
                    String str = toLabel.getText().toString();
                    this.toLength = UnitsConverter.LengthUnits.valueOf(str);
                    this.fromLength = UnitsConverter.LengthUnits.valueOf(fromLabel.getText().toString());
                    System.out.println(toLength);

                }else{
                    String str = toLabel.getText().toString();
                    this.toVolume = UnitsConverter.VolumeUnits.valueOf(str);
                    this.fromVolume = UnitsConverter.VolumeUnits.valueOf(fromLabel.getText().toString());
                }



    }

    private  void changeMode(){
        this.isVol = !this.isVol;
        if(this.isVol){
            this.fromVolume = UnitsConverter.VolumeUnits.Gallons;
            this.toVolume = UnitsConverter.VolumeUnits.Quarts;
            this.fromLabel.setText(fromVolume.toString());
            this.toLabel.setText(toVolume.toString());
        }else{
            this.fromLength = UnitsConverter.LengthUnits.Yards;
            this.toLength = UnitsConverter.LengthUnits.Meters;
            this.fromLabel.setText(fromLength.toString());
            this.toLabel.setText(toLength.toString());
        }


    }
    private void hideKeyboard(View v){
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(),0);
    }




}
