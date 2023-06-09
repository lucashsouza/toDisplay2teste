package com.example.todisplay2;

import android.app.Presentation;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;

import com.example.todisplay2.utils.IMPlayListener;
import com.example.todisplay2.utils.IMPlayer;
import com.example.todisplay2.utils.MPlayer;
import com.example.todisplay2.utils.MPlayerException;
import com.example.todisplay2.utils.MinimalDisplay;

import java.io.File;

public class VideoDisplay extends BasePresentation{


    private SurfaceView mPlayerView;
    private MPlayer player;
    private final String TAG = "SUNMI";
    private String path;
    private FrameLayout container;
    public static int positon = 0;
    private Handler myHandle = new Handler(Looper.getMainLooper());

    public VideoDisplay(Context context, Display display, String path) {
        super(context, display);
        this.path = path;
        Log.d(TAG, "VideoDisplay: ------------>" + path);
        File file = new File(path);
        Log.d(TAG, "VideoDisplay: --------->" + file.exists());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vice_video_layout);
        mPlayerView = (SurfaceView) findViewById(R.id.mPlayerView);
        container = (FrameLayout) findViewById(R.id.playerContainer);

        initPlayer();
        Log.d(TAG, "onCreate: ------------>" + (player == null));

    }

    private void initPlayer() {
        player = new MPlayer();
        player.setDisplay(new MinimalDisplay(mPlayerView));
        player.setPlayListener(new IMPlayListener() {
            @Override
            public void onStart(IMPlayer player) {

            }

            @Override
            public void onPause(IMPlayer player) {

            }

            @Override
            public void onResume(IMPlayer player) {

            }

            @Override
            public void onComplete(IMPlayer player) {
                try {
                    player.setSource(path, 0);
                } catch (MPlayerException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    @Override
    public void onSelect(boolean isShow) {
        if(player != null){
            if (isShow) {
                try {
                    player.setSource(path, positon);
                    player.onResume();
                } catch (MPlayerException e) {
                    e.printStackTrace();
                    myHandle.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                player.setSource(path, 0);
                                player.onResume();
                            } catch (MPlayerException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }, 3000);
                    Log.d(TAG, "onCreate: ---------->" + e.getMessage());
                }
            }else {
                if(player.getPosition()!=0)
                    positon = player.getPosition();
            }
        }
    }


    @Override
    public void onDisplayRemoved() {
        super.onDisplayRemoved();
//        player.onDestroy();
    }
}
