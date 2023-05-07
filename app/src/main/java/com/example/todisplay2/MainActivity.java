package com.example.todisplay2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ScreenManager screenManager = ScreenManager.getInstance();
    private VideoDisplay videoDisplay = null;
    private TextDisplay textDisplays;
    public TextDisplay textDisplay = null;
    MainActivity activity = null;
    private EditText myText;

    public static boolean isVertical = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myText = findViewById(R.id.myTxt);
        DisplayMetrics dm = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        isVertical = height > width;

        if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

        if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }

        initData();

        Button btnPlay = (Button) findViewById(R.id.btnPlay);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (videoDisplay != null) {
                    Toast.makeText(getApplicationContext(),
                            "Play no vídeo!",
                            Toast.LENGTH_LONG).show();
                    videoDisplay.show();
                }
            }
        });

        Button btnPause = (Button) findViewById(R.id.btnStop);
        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoDisplay.hide();
            }
        });

        Button btnText = (Button) findViewById(R.id.btnText);
        btnText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(textDisplays.isShow){
                    textDisplays.update(myText.getText().toString(), 1);
                }else{
                    textDisplays.show();
                    textDisplays.update(myText.getText().toString(), 1);
                }


            }
        });
    }


    private void initData(){
        activity = (MainActivity) this;
        screenManager.init(this);
        Display[] displays = screenManager.getDisplays();
        Log.e("Display", "Displays identificados: " + displays.length);
        for (int i = 0; i < displays.length; i++) {
            Log.e("Display", "Caracteristicas: " + displays[i]);
        }
        Display display = screenManager.getPresentationDisplays();

        if (display != null && !isVertical){
            textDisplay = new TextDisplay(this, display);
            videoDisplay = new VideoDisplay(this, display, "/sdcard/Movies/video_02.mp4");
        }
        textDisplays = activity.textDisplay;
    }
}