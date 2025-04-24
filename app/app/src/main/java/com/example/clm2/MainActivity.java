package com.example.clm2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button cmsbtn=findViewById(R.id.button6);
        Button cldbtn=findViewById(R.id.button5);
        cmsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_main2);
                Intent itt=new Intent(MainActivity.this,MainActivity2.class);
                startActivity(itt);
            }
        });
        Button cldp=findViewById(R.id.floatingActionButton);
        cldp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itt=new Intent(MainActivity.this,cldp.class);
                startActivity(itt);
            }
        });

    }
}