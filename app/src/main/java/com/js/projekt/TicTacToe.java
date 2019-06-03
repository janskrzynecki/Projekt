package com.js.projekt;;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TicTacToe extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_p1, tv_p2;
    private Button reset3;
    private Button [][] btns=new Button[3][3];
    private int player1Points=0,player2Points=0;
    private int roundCount=0;
    private Boolean player1Turn=true;
    private String player1Name,player2Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tictactoe);

        initViews();

        player1Name=getIntent().getStringExtra("player1Name");
        player2Name=getIntent().getStringExtra("player2Name");
        tv_p1.setText(player1Name+"(X): 0");
        tv_p2.setText(player2Name+"(0): 0");

        reset3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });

    }

    private void initViews() {

        tv_p1=findViewById(R.id.tv_p1);
        tv_p2=findViewById(R.id.tv_p2);
        reset3=findViewById(R.id.reset3);

        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                String id_name="btn_"+i+j; //btn_00 ,btn_01,btn_02
                int btnId=this.getResources().getIdentifier(id_name,"id",getPackageName());
                btns[i][j]=findViewById(btnId); //findViewById(R.id.btn_00);
                btns[i][j].setOnClickListener(this);
            }
        }
    }

    @Override
    public void onClick(View v) {

        if (player1Turn)
        {
            ((Button)v).setText("X");
            ((Button)v).setTextColor(this.getResources().getColor(R.color.colorGreen));
            ((Button)v).setEnabled(false);
        }else
        {
            ((Button)v).setText("O");
            ((Button)v).setTextColor(this.getResources().getColor(R.color.colorRed));
            v.setEnabled(false);
        }
        roundCount++;

        if (checkForWin())
        {
            if (player1Turn)
            {
                player1Wins();
            }else
            {
                player2Wins();
            }
        }else if(roundCount==9)
        {
            draw();
        }else
        {
            player1Turn = !player1Turn;
        }

    }

    private void draw() {
        askForAnotherGame("Remis!");
    }

    private void player2Wins() {
        player2Points++;
        updatePointText();
        player1Turn = false;
        askForAnotherGame(player2Name+" wygrał!");
    }

    private void player1Wins() {
        player1Points++;
        updatePointText();
        player1Turn = true;
        askForAnotherGame(player1Name+" wygrał!");
    }

    private void resetBoard() {

        for (int i=0;i<3;i++)
        {
            for (int j=0;j<3;j++)
            {
                btns[i][j].setText("");
                btns[i][j].setEnabled(true);
            }
        }
        roundCount=0;
    }

    private void updatePointText() {
        tv_p1.setText(player1Name+":"+player1Points);
        tv_p2.setText(player2Name+":"+player2Points);

    }

    private boolean checkForWin() {
        String field[][]=new String[3][3];
        for (int i=0;i<3;i++)
        {
            for (int j=0;j<3;j++)
            {
                field[i][j]=btns[i][j].getText().toString();
            }
        }
        //porównanie i0 z i1 i i0 z i2 + warunek aby i0 nie był pusty, gdzie i to wiersze
        for (int i=0;i<3;i++)
        {
            if (field[i][0].equals(field[i][1])&&field[i][0].equals(field[i][2])&&!(field[i][0].equals("")))
            {
                return true;
            }
        }
        //porównaniu 0j z 1j i 0j z 2j + warunek aby 0j nie był pusty, gdzie j to kolumny
        for (int i=0;i<3;i++)
        {
            if (field[0][i].equals(field[1][i])&&field[0][i].equals(field[2][i])&&!(field[0][i].equals("")))
            {
                return true;
            }
        }
        //porównwanie przekątnej \
        if( field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) && (!field[0][0].equals("")))
        {
            return true;
        }

        //porównanie odwrotnej przekątnej /
        if( field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0]) && (!field[0][2].equals("")))
        {
            return true;
        }
        return false;
    }

    public void resetGame()
    {
        player1Points=0;
        player2Points=0;
        resetBoard();
        updatePointText();
    }

    private void askForAnotherGame(String msg)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(TicTacToe.this);
        builder.setTitle(msg);
        builder.setMessage("Chcesz zagrać ponownie?");
        builder.setPositiveButton("Tak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                resetBoard();
            }
        });
        builder.setNegativeButton("Nie", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                startActivity(new Intent(TicTacToe.this, TicTacToeMenu.class));
            }
        });

        AlertDialog alertDialog=builder.create();
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();


    }
}