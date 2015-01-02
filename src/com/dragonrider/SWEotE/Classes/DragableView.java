package com.dragonrider.SWEotE.Classes;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.SurfaceView;

/**
 * Created by MGE637 on 21/09/2014.
 */
public class DragableView extends SurfaceView {

    Drawable _image;
    int _x;
    int _y;
    int _width;
    int _height;
    int _backColor;

    public DragableView(Context context, int InitialPositionX, int InitialPositionY, Drawable image, int BackColor) {
        super(context);

        this._image = image;
        this._x = InitialPositionX;
        this._y = InitialPositionY;
        this._width = image.getIntrinsicWidth();
        this._height = image.getIntrinsicHeight();
        this._backColor = BackColor;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();
        //canvas.drawColor(_backColor);

        _image.draw(canvas);


        canvas.restore();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {


        _x += (int)event.getX();
        _y += (int)event.getY();


        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (localMoveListener != null)
                localMoveListener.NewPos(_x, _y);
            return true;
        }




        this.invalidate();

        return super.onTouchEvent(event);
    }



    IEndMove localMoveListener;

    public DragableView setMoveListener(IEndMove listener) {
        this.localMoveListener = listener;
        return this;
    }

    public interface IEndMove {
        void NewPos(int X, int Y);
    }
}
