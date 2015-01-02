package com.dragonrider.SWEotE.Classes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.dragonrider.SWEotE.R;


public class MoveableView extends ImageView {
    public Drawable mIcon;
    private float mPosX;
    private float mPosY;

    public float getPosX() { return mPosX;}
    public float getPosY() { return mPosY;}


    public MoveableView setPosX(float x) { mPosX = x; this.invalidate(); return this;}
    public MoveableView setPosY(float y) { mPosY = y; this.invalidate(); return this;}


    private float mLastTouchX;
    private float mLastTouchY;

    private float mOffestX;
    private float mOffsetY;


    public MoveableView(Context context, int x, int y, Drawable icon, int color) {
        super(context);
        mIcon = icon;

        this.setImageDrawable(icon);
        this.setScaleType(ScaleType.FIT_CENTER);
        FrameLayout.LayoutParams layout = new FrameLayout.LayoutParams(64, 128);
        layout.setMargins(x, y, 0, 0);
        this.mPosY = y;
        this.mPosX = x;
        setLayoutParams(layout);

        this.setPadding(2, 2, 2, 2);

        this.setBackgroundColor(color);

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final int action = event.getAction();


        switch (action) {
            case MotionEvent.ACTION_DOWN:
            {
                mLastTouchX = event.getX();
                mLastTouchY = event.getY();

                mOffestX = mLastTouchX - mPosX;
                mOffsetY = mLastTouchY - mPosY;

                return true;
            }
            case MotionEvent.ACTION_MOVE:
            {

                float x = event.getX();
                float y = event.getY();

                mPosX += x - mLastTouchX;
                mPosY += y - mLastTouchY;

                mLastTouchX = x;
                mLastTouchY = y;

                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) getLayoutParams();
                params.setMargins(((int) x + params.leftMargin) - (int) mOffestX, (int)y+params.topMargin - (int)mOffsetY, 0, 0);
                setLayoutParams(params);

                invalidate();
                return true;

            }
            case MotionEvent.ACTION_UP: {
                Log.i("hopla", String.format("getX=%f, getY=%f, mLastTouchX=%f, mLastTouchY=%f", mPosX, mPosY, mLastTouchX, mLastTouchY));
                //localMoveListener.NewPos((int)mPosX ,(int)mPosY);
                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) getLayoutParams();
                localMoveListener.NewPos(((int) event.getX() + params.leftMargin) - (int) mOffestX, (int)event.getY()+params.topMargin - (int)mOffsetY);
                return true;
            }
        }

        return super.onTouchEvent(event);
    }
    IEndMove localMoveListener;

    public MoveableView setMoveListener(IEndMove listener) {
        this.localMoveListener = listener;
        return this;
    }

    public interface IEndMove {
        void NewPos(int X, int Y);
    }
}
