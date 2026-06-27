package com.github.josegerar.sweetalert;

import android.view.MotionEvent;
import android.view.View;

public class Constants {
    //make bg a little bit darker
    public static final View.OnTouchListener FOCUS_TOUCH_LISTENER = (v, event) -> {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_BUTTON_PRESS:
                v.setPressed(true);
                break;
            case MotionEvent.ACTION_UP:
                v.setPressed(false);
                v.performClick();
                return true;
            case MotionEvent.ACTION_CANCEL:
                v.setPressed(false);
                break;
        }
        return true;
    };
}
