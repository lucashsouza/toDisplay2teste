package com.example.todisplay2;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.TextView;

public class TextDisplay extends BasePresentation {
    private TextView tvTitle;
    public int state;

    public TextDisplay(Context outerContext, Display display) {
        super(outerContext, display);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ScreenManager.getInstance().isMinScreen()) {
            setContentView(R.layout.vice_text_min_layout);

        }else {
            Log.i("Display", "Layout inválido. Precisa adequar");
        }

        tvTitle = (TextView) findViewById(R.id.tv_title);
    }

    public void update(String tip, final int state) {
        this.state = state;
        tvTitle.setText(tip);
    }

    @Override
    public void show() {
        super.show();
        tvTitle.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSelect(boolean isShow) {

    }
}
