package com.example.todisplay2.utils;

public interface IMPlayListener {
    void onStart(IMPlayer player);
    void onPause(IMPlayer player);
    void onResume(IMPlayer player);
    void onComplete(IMPlayer player);
}
