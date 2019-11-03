package com.example.suanming.ui.dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.suanming.MainActivity;
import com.example.suanming.R;

public class cqresult extends AppCompatActivity {

    Button cq_btn;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cqresult);
        imageView = findViewById(R.id.qianmian);

        Intent intent = getIntent();

        int flag = intent.getIntExtra("flag",0);
        int random = intent.getIntExtra("random",0);

        switch (random){
            case 1:
                imageView.setImageResource(R.drawable.qian1);
                break;
            case 2:
                imageView.setImageResource(R.drawable.qian2);
                break;
            case 3:
                imageView.setImageResource(R.drawable.qian3);
                break;
            case 4:
                imageView.setImageResource(R.drawable.qian4);
                break;
            case 5:
                imageView.setImageResource(R.drawable.qian5);
                break;
            case 6:
                imageView.setImageResource(R.drawable.qian6);
                break;
            case 7:
                imageView.setImageResource(R.drawable.qian7);
                break;
            case 8:
                imageView.setImageResource(R.drawable.qian8);
                break;
            case 9:
                imageView.setImageResource(R.drawable.qian9);
                break;
            case 10:
                imageView.setImageResource(R.drawable.qian10);
                break;
        }
    }
}
