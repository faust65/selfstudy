package com.example.clm2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button cldbtn=findViewById(R.id.button5);
        Button cmsbtn=findViewById(R.id.button6);
        cldbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_main);
                Intent itt=new Intent(MainActivity2.this,MainActivity.class);
                startActivity(itt);
            }
        });
    }
}