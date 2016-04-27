package com.example.administrador.javialex_lab08;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Administrador on 04/04/2016.
 */
public class MiTextView extends TextView {
    private Paint lineaPaint;
    public MiTextView(Context context) {
        super(context);
        Init();
    }

    public MiTextView(Context context, AttributeSet attrs) {
        /*Este usara el loyout debido a que consume atributos*/
        super(context, attrs);
        Init();
    }

    public void Init() {
        lineaPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        lineaPaint.setColor(Color.RED);
    }
    @Override
    public void onDraw(Canvas canvas){
        canvas.drawLine(0,0,getMeasuredHeight(),0,lineaPaint);
        canvas.drawLine(0,getMeasuredHeight(),getMeasuredWidth(),getMeasuredHeight(),lineaPaint);
        canvas.save();
        super.onDraw(canvas);
        canvas.restore();
    }
}
