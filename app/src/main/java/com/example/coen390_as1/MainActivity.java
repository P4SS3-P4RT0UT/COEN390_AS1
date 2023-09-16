package com.example.coen390_as1;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //declare buttons
    private Button settings_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize buttons
        settings_btn = findViewById(R.id.settings_btn);
        settings_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent settings = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(settings);
            }
        });
    }

}