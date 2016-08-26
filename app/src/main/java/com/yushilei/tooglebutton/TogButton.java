package com.yushilei.tooglebutton;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author by  yushilei.
 * @time 2016/8/18 -16:25.
 */
public class TogButton extends View implements View.OnClickListener {
    private int mHeight;
    private int mWidth;
    private int mR;

    private int x;

    private Paint mBackPaint;

    private Paint mPaint;
    private Paint textPaint;
    private RectF mBackRectF;

    ObjectAnimator mAnimator;

    int duration = 250;

    boolean isOpen = false;

    String text = "关";
    private Paint.FontMetricsInt mFontMetricsInt;

    public TogButton(Context context) {
        this(context, null);
    }

    public TogButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        mBackRectF = new RectF();
        mBackPaint = new Paint();
        mBackPaint.setColor(Color.LTGRAY);
        mBackPaint.setAntiAlias(true);

        mBackPaint.setStyle(Paint.Style.FILL);

        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);

        textPaint = new TextPaint();
        textPaint.setAntiAlias(true);
        textPaint.setColor(Color.GRAY);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(35);
        mFontMetricsInt = textPaint.getFontMetricsInt();

        setOnClickListener(this);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHeight = h;
        mR = mHeight / 2;
        x = mR;
        mWidth = w;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);

        mBackRectF.set(0, 0, mWidth, mHeight);
        canvas.drawRoundRect(mBackRectF, mR, mR, mBackPaint);

        canvas.drawCircle(x, mR, mR + 0.1f, mPaint);

        int ascent = Math.abs(mFontMetricsInt.ascent);
        int descent = Math.abs(mFontMetricsInt.descent);
        int distance = (ascent + descent) / 2 - descent;

        canvas.drawText(text, x, mR + distance, textPaint);
    }

    @Override
    public void onClick(View v) {
        if (isOpen) {
            text = "开";
            mAnimator = ObjectAnimator.ofInt(this, "X", mR, mWidth - mR);

        } else {
            text = "关";
            mAnimator = ObjectAnimator.ofInt(this, "X", mWidth - mR, mR);
        }
        mAnimator.setDuration(duration);
        mAnimator.start();
        isOpen = !isOpen;
    }

    public void setX(int x) {
        this.x = x;
        invalidate();
    }
}
