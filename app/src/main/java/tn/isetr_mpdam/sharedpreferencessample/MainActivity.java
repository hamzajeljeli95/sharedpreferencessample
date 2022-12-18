package tn.isetr_mpdam.sharedpreferencessample;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    static String PREF_NAME = "tn.isetr_mpdam.sharedpreferencessample";
    static String PREF_KEY = "sample";
    Button SaveBtn, LoadBtn, ClearBtn;
    EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input = findViewById(R.id.textarea);
        SaveBtn = findViewById(R.id.savebtn);
        SaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SaveToSharedPreferences(PREF_KEY, input.getText().toString())) {
                    Toast.makeText(MainActivity.this, "Values saved !", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_LONG).show();
                }
            }
        });

        LoadBtn = findViewById(R.id.loadbtn);
        LoadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText(ReadFromSharedPreferences(PREF_KEY));
                Toast.makeText(MainActivity.this, "Values Loaded !", Toast.LENGTH_LONG).show();
            }
        });

        ClearBtn = findViewById(R.id.clearbtn);
        ClearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ClearSharedPreferences()) {
                    Toast.makeText(MainActivity.this, "Values cleared !", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public String ReadFromSharedPreferences(String Key) {
        return this.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).getString(Key, "");
    }

    public boolean SaveToSharedPreferences(String Key, String Value) {
        SharedPreferences sharedPreferences = this.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.edit().putString(Key, Value).commit();
    }

    public boolean ClearSharedPreferences() {
        SharedPreferences sharedPreferences = this.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.edit().clear().commit();
    }
}