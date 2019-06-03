package com.js.projekt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Text extends AppCompatActivity {

    private TextView tv_text, tv_result;
    private EditText et_text;
    private String fullStory;
    private long startTime, endTime;
    private Button reset2;
    private boolean gameStarted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        tv_text = (TextView) findViewById(R.id.tv_text);
        tv_result = (TextView) findViewById(R.id.tv_result);
        et_text = (EditText) findViewById(R.id.et_text);
        reset2 = (Button) findViewById(R.id.reset2);
        fullStory = tv_text.getText().toString();

        et_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String currentStory = et_text.getText().toString();

                //początek pisania
                if(currentStory.length() == 1 && !gameStarted){
                    startTime = System.currentTimeMillis();
                    tv_result.setText("Start!");
                    gameStarted = true;
                }
                //koniec pisania
                if (currentStory.equals(fullStory)){
                    endTime = System.currentTimeMillis();
                    long diffTime = (endTime - startTime)/1000;
                    tv_result.setText("Ukończono w " + diffTime + "sekund!");

                    et_text.setEnabled(false);
                    et_text.clearFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        reset2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_text.setEnabled(true);
                et_text.setText("");
                tv_result.setText("");
                gameStarted = false;
            }
        });
    }
}
