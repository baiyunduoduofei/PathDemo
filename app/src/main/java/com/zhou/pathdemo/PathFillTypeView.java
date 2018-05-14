package com.zhou.pathdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PorterDuff;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author: zhouyunfei
 * @date: 2018/5/11
 * @desc:
 */
public class PathFillTypeView extends View {


    private Paint mPaint;
    private Path mPath;
    private Context mContext;
    private int mWidth;
    private int mHeight;
    private Path.FillType mFillType = Path.FillType.WINDING;

    public PathFillTypeView(Context context) {
        super(context);
        init(context);
    }

    public PathFillTypeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        mPaint = new Paint();
        mPaint.setColor(Color.GREEN);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeJoin(Paint.Join.ROUND);//圆弧
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPath = new Path();

    }

    public void setFillType(Path.FillType mFillType) {
        this.mFillType = mFillType;
        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        mPath.reset();
        mPath.addRect(10, 10, (int) (mWidth * 0.7), (int) (mHeight * 0.7), Path.Direction.CW);
        mPath.addCircle((int) (mWidth * 0.7), (int) (mHeight * 0.5), (int) (mHeight * 0.3), Path.Direction.CW);
        mPath.setFillType(mFillType);
        canvas.drawPath(mPath, mPaint);
    }
}
