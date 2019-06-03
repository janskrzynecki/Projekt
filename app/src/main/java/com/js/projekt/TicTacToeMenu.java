package com.js.projekt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TicTacToeMenu extends AppCompatActivity {
    private Button start;
    private EditText et_p1 ,et_p2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tictactoemenu);
        intViews();
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(TicTacToeMenu.this, TicTacToe.class);
                intent.putExtra("player1Name",et_p1.getText().toString());
                intent.putExtra("player2Name",et_p2.getText().toString());
                startActivity(intent);

            }
        });

    }

    private void intViews() {
        start=findViewById(R.id.start);
        et_p1=findViewById(R.id.et_p1);
        et_p2=findViewById(R.id.et_p2);
    }
}
