package com.example.todisplay2.utils;


public interface IMPlayer {

    /**
     * @throws MPlayerException
     */
    void setSource(String url, int position) throws MPlayerException;


    void setDisplay(IMDisplay display);

    /**
     * @throws MPlayerException
     */
    void play() throws MPlayerException;

    void pause();


    void onPause();

    void onResume();

    void onDestroy();

}
