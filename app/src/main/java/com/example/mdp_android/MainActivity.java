package com.example.mdp_android;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.nio.charset.Charset;

public class MainActivity extends AppCompatActivity {

    Button btnBTFunctions;
    Button btnForward, btnReverse, btnSteerLeft, btnSteerRight, btnMode1, btnMode2;
    public static TextView consoleLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnBTFunctions = (Button) findViewById(R.id.btnBTFunctions);
        btnForward = (Button) findViewById(R.id.btnForward);
        btnSteerLeft = (Button) findViewById(R.id.btnSteerLeft);
        btnSteerRight = (Button) findViewById(R.id.btnSteerRight);
        btnReverse = (Button) findViewById(R.id.btnReverse);
        btnMode1 = (Button) findViewById(R.id.btnMode1);
        btnMode2 = (Button) findViewById(R.id.btnMode2);
        consoleLog = (TextView) findViewById(R.id.consoleLogText);
        consoleLog.setMovementMethod(new ScrollingMovementMethod());

        btnBTFunctions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startBT = new Intent(MainActivity.this, com.example.mdp_android.Bluetooth.class);
                startBT.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivityIfNeeded(startBT,0);
            }
        });

        /*
        btnForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String up = "UP";
                byte[] bytes = up.getBytes(Charset.defaultCharset());
                Bluetooth.mBluetoothConnection.write(bytes);
            }
        });

         */
        btnForward.setOnTouchListener(sendMovement);
        btnSteerLeft.setOnTouchListener(sendMovement);
        btnSteerRight.setOnTouchListener(sendMovement);
        btnReverse.setOnTouchListener(sendMovement);

        btnMode1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String up = "Mode 1 Selected";
                byte[] bytes = up.getBytes(Charset.defaultCharset());
                com.example.mdp_android.Bluetooth.mBluetoothConnection.write(bytes);
            }
        });

        btnMode2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String up = "Mode 2 Selected";
                byte[] bytes = up.getBytes(Charset.defaultCharset());
                com.example.mdp_android.Bluetooth.mBluetoothConnection.write(bytes);
            }
        });
    }


    private final View.OnTouchListener sendMovement = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if(view.getId() == R.id.btnForward){
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    btnReverse.setEnabled(false);
                    String up = "startForward";
                    byte[] bytes = up.getBytes(Charset.defaultCharset());
                    com.example.mdp_android.Bluetooth.mBluetoothConnection.write(bytes);
                }
                else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    btnReverse.setEnabled(true);
                    String up = "stopForward";
                    byte[] bytes = up.getBytes(Charset.defaultCharset());
                    com.example.mdp_android.Bluetooth.mBluetoothConnection.write(bytes);
                }
                return true;
            }
            if(view.getId() == R.id.btnSteerLeft){
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    btnSteerRight.setEnabled(false);
                    String up = "startLeft";
                    byte[] bytes = up.getBytes(Charset.defaultCharset());
                    com.example.mdp_android.Bluetooth.mBluetoothConnection.write(bytes);
                }
                else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    btnSteerRight.setEnabled(true);
                    String up = "stopLeft";
                    byte[] bytes = up.getBytes(Charset.defaultCharset());
                    com.example.mdp_android.Bluetooth.mBluetoothConnection.write(bytes);
                }
                return true;
            }
            if(view.getId() == R.id.btnSteerRight){
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    btnSteerLeft.setEnabled(false);
                    String up = "startRight";
                    byte[] bytes = up.getBytes(Charset.defaultCharset());
                    com.example.mdp_android.Bluetooth.mBluetoothConnection.write(bytes);
                }
                else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    btnSteerLeft.setEnabled(true);
                    String up = "stopRight";
                    byte[] bytes = up.getBytes(Charset.defaultCharset());
                    com.example.mdp_android.Bluetooth.mBluetoothConnection.write(bytes);
                }
                return true;
            }
            if(view.getId() == R.id.btnReverse){
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    btnForward.setEnabled(false);
                    String up = "startReverse";
                    byte[] bytes = up.getBytes(Charset.defaultCharset());
                    com.example.mdp_android.Bluetooth.mBluetoothConnection.write(bytes);
                }
                else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    btnForward.setEnabled(true);
                    String up = "stopReverse";
                    byte[] bytes = up.getBytes(Charset.defaultCharset());
                    com.example.mdp_android.Bluetooth.mBluetoothConnection.write(bytes);
                }
                return true;
            }
            return false;
        }
    };
}