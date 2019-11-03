package com.example.suanming.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.suanming.R;

public class Sc_Result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sc__result);
        Intent GetResult = getIntent();
        String Trst = GetResult.getStringExtra("Tresult");

        TextView Tresult = findViewById(R.id.Tresult);
        Tresult.setText(Trst);
    }
}
