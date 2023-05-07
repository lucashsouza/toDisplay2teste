package com.example.todisplay2.utils;

import android.view.SurfaceHolder;
import android.view.View;

/**
 * Description:
 */
public interface IMDisplay extends IMPlayListener {

    View getDisplayView();
    SurfaceHolder getHolder();

}

