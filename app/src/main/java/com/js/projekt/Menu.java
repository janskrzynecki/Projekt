package com.js.projekt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {

    private Button button;
    private Button button2;
    private Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGame1();
            }
        });
        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGame2();
            }
        });
        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGame3();
            }
        });
    }
    public void openGame1(){

        Intent intent = new Intent(this, TicTacToeMenu.class);
        startActivity(intent);
    }
    public void openGame2(){

        Intent intent = new Intent(this, Dice.class);
        startActivity(intent);
    }
    public void openGame3(){

        Intent intent = new Intent(this, Text.class);
        startActivity(intent);
    }
}
