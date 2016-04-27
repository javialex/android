package com.example.administrador.javialex_lab09;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrador on 05/04/2016.
 */
public class CuadradoView extends View {
    private int colorCuadrado;
    private int colorLabel;
    private String stringLabel;
    private Paint paint;
    private boolean onTrue;
    public CuadradoView(Context context) {
        super(context);
    }

    public boolean isOnTrue() {
        return onTrue;
    }

    public void setOnTrue(boolean onTrue) {
        this.onTrue = onTrue;
        invalidate();
        requestLayout();
    }

    public CuadradoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,R.styleable.ViewCuadrado,0,0);

        try {
            stringLabel = a.getString(R.styleable.ViewCuadrado_stringLabel);
            colorCuadrado = a.getInt(R.styleable.ViewCuadrado_colorCuadrado, 0);
            colorLabel = a.getInt(R.styleable.ViewCuadrado_colorLabel, 0);
            onTrue = a.getBoolean(R.styleable.ViewCuadrado_onTrue, true);
        }catch (Exception ex){
            Log.e("CuadradoView", ex.getMessage());
        }
        finally {
            a.recycle();
        }
    }

    public int getColorCuadrado() {
        return colorCuadrado;
    }

    public void setColorCuadrado(int colorCuadrado) {
        this.colorCuadrado = colorCuadrado;
        /*Invalidamos los valores predispuestos por el padre*/
        invalidate();
        requestLayout();
    }

    public int getColorLabel() {
        return colorLabel;
    }

    public void setColorLabel(int colorLabel) {
        this.colorLabel = colorLabel;
        invalidate();
        requestLayout();
    }

    public String getStringLabel() {
        return stringLabel;
    }

    public void setStringLabel(String stringLabel) {
        this.stringLabel = stringLabel;
        invalidate();
        requestLayout();
    }

    protected void onDraw(Canvas canvas){
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setColor(colorCuadrado);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), paint);
        paint.setColor(colorLabel);
        paint.setTextSize(50);
        canvas.drawText(stringLabel,this.getMeasuredWidth()/2,this.getMeasuredHeight()/2,paint);
    }
}
