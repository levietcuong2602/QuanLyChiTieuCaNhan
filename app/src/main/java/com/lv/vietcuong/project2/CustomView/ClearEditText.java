package com.lv.vietcuong.project2.CustomView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

import com.lv.vietcuong.project2.R;

public class ClearEditText extends android.support.v7.widget.AppCompatEditText {

    Drawable cross, nonCorss;
    Boolean visible = false;

    public ClearEditText(Context context) {
        super(context);
        setup();
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup();
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup();
    }

    public void setup(){
        cross = ContextCompat.getDrawable(getContext(), R.drawable.icon_cancel).mutate();
        nonCorss = ContextCompat.getDrawable(getContext(), android.R.drawable.screen_background_light_transparent).mutate();
        config();
    }

    private void config() {
        setInputType(InputType.TYPE_CLASS_TEXT);
        Drawable[]drawables = getCompoundDrawables();
        Drawable drawable = visible?cross:nonCorss;
        setCompoundDrawablesWithIntrinsicBounds(drawables[0],drawables[1], drawable, drawables[3]);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN && event.getX() >= (getRight() - cross.getBounds().width()))
        {
            setText("");
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        if (lengthAfter == 0 && start == 0){
            visible = false;
        }else {
            visible = true;
        }
        config();
    }
}
