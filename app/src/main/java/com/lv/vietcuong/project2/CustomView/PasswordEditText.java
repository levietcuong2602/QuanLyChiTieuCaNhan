package com.lv.vietcuong.project2.CustomView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

import com.lv.vietcuong.project2.R;

/**
 * Created by Administor on 3/24/2018.
 */

public class PasswordEditText extends android.support.v7.widget.AppCompatEditText {

    Drawable show_eye, hide_eye;
    Boolean useStrike = false;
    Boolean visible = false;

    public PasswordEditText(Context context) {
        super(context);
        startFont(null);
    }

    public PasswordEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        startFont(attrs);
    }

    public PasswordEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        startFont(attrs);
    }

    public void startFont(AttributeSet attr){
        //lấy thuộc tính người dùng truyền file xml
        if (attr != null){
            TypedArray array = getContext().getTheme().obtainStyledAttributes(attr, R.styleable.PasswordEditText, 0, 0);
            //đã lấy được thuộc tính useStrike ở file xml
            this.useStrike = array.getBoolean(R.styleable.PasswordEditText_useStrike, false);
        }

        show_eye = ContextCompat.getDrawable(getContext(), R.drawable.show_eye).mutate();
        hide_eye = ContextCompat.getDrawable(getContext(), R.drawable.hide_eye).mutate();
        build();
    }

    public void build(){
        setInputType(InputType.TYPE_CLASS_TEXT | (visible?InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD:InputType.TYPE_TEXT_VARIATION_PASSWORD));
        Drawable[]drawables = getCompoundDrawables();
        Drawable drawable = useStrike && visible?show_eye:hide_eye;
        setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[1], drawable,drawables[3]);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP && event.getX() >= (getRight() - hide_eye.getBounds().width())){
            visible = !visible;
            build();
            invalidate();
        }
        return super.onTouchEvent(event);
    }
}
