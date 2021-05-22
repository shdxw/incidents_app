package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity2 extends AppCompatActivity {

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TextView discover = (TextView) findViewById(R.id.textView);
        discover.setText(getIntent().getStringExtra("desc"));
        TextView register = (TextView) findViewById(R.id.textView5);
        register.setText(getIntent().getStringExtra("register"));
        TextView level = (TextView) findViewById(R.id.textView7);
        level.setText(getIntent().getStringExtra("level"));
        TextView datenow = (TextView) findViewById(R.id.textView9);
        datenow.setText(getIntent().getStringExtra("nowDate"));
        TextView datelast = (TextView) findViewById(R.id.textView11);
        datelast.setText(getIntent().getStringExtra("lastDate"));
        TextView system = (TextView) findViewById(R.id.textView13);
        system.setText(getIntent().getStringExtra("system"));
        TextView status = (TextView) findViewById(R.id.textView15);
        status.setText(getIntent().getStringExtra("status"));
        TextView norm = (TextView) findViewById(R.id.textView17);
        norm.setText(getIntent().getStringExtra("otcl"));
        TextView lnorm = (TextView) findViewById(R.id.textView19);
        lnorm.setText(getIntent().getStringExtra("weight"));




        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //finish();
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}