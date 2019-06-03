package com.js.projekt;

import android.app.Service;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

public class Dice extends AppCompatActivity implements SensorEventListener {

    private ImageView iv_cpu, iv_player;
    private TextView tv_cpu, tv_player;
    private Random r;
    private int cpuPoints=0, playerPoints=0;
    private Button reset1;
    SensorManager sensorManager;
    Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice);

        iv_cpu = (ImageView) findViewById(R.id.iv_cpu);
        iv_player = (ImageView) findViewById(R.id.iv_player);
        reset1 = (Button) findViewById(R.id.reset1);
        tv_cpu = (TextView) findViewById(R.id.tv_cpu);
        tv_player = (TextView) findViewById(R.id.tv_player);

        sensorManager = (SensorManager) getSystemService(Service.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor. TYPE_LIGHT);
        r = new Random();

        reset1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cpuPoints = 0;
                playerPoints = 0;
                tv_cpu.setText("CPU: 0");
                tv_player.setText("JA: 0");
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        int cpuThrow = r.nextInt(6) + 1;
        int playerThrow = r.nextInt(6) + 1;

        if ((event.values[0] < 1))
        {
            Animation rotate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
            iv_cpu.startAnimation(rotate);
            iv_player.startAnimation(rotate);

            setImageCPU(cpuThrow);
            setImagePlayer(playerThrow);

            if(cpuThrow > playerThrow){
                cpuPoints++;
                Toast.makeText(Dice.this, "Przegrałeś!", Toast.LENGTH_SHORT).show();
            }

            if(playerThrow > cpuThrow){
                playerPoints++;
                Toast.makeText(Dice.this, "Wygrałeś!", Toast.LENGTH_SHORT).show();
            }
            if(playerThrow == cpuThrow){
                Toast.makeText(Dice.this, "Remis!", Toast.LENGTH_SHORT).show();
            }
            tv_cpu.setText("CPU: " + cpuPoints);
            tv_player.setText("JA: " + playerPoints);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void setImageCPU(int num){
        switch (num){
            case 1:
                iv_cpu.setImageResource(R.drawable.one);
                break;
            case 2:
                iv_cpu.setImageResource(R.drawable.two);
                break;
            case 3:
                iv_cpu.setImageResource(R.drawable.three);
                break;
            case 4:
                iv_cpu.setImageResource(R.drawable.four);
                break;
            case 5:
                iv_cpu.setImageResource(R.drawable.five);
                break;
            case 6:
                iv_cpu.setImageResource(R.drawable.six);
                break;
        }
    }

    public void setImagePlayer(int num){
        switch (num){
            case 1:
                iv_player.setImageResource(R.drawable.one);
                break;
            case 2:
                iv_player.setImageResource(R.drawable.two);
                break;
            case 3:
                iv_player.setImageResource(R.drawable.three);
                break;
            case 4:
                iv_player.setImageResource(R.drawable.four);
                break;
            case 5:
                iv_player.setImageResource(R.drawable.five);
                break;
            case 6:
                iv_player.setImageResource(R.drawable.six);
                break;
        }
    }

}