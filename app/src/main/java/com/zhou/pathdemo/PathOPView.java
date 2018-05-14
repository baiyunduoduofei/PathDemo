package com.zhou.pathdemo;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.Region;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author: zhouyunfei
 * @date: 2018/5/11
 * @desc:
 */

@TargetApi(Build.VERSION_CODES.KITKAT)
public class PathOPView extends View {


    private Paint mPaint;
    private Path mPathA;
    private Path mPathB;
    private Context mContext;
    private int mWidth;
    private int mHeight;
    private Path.Op mOp = Path.Op.INTERSECT;

    public PathOPView(Context context) {
        super(context);
        init(context);
    }

    public PathOPView(Context context, @Nullable AttributeSet attrs) {
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
        mPathA = new Path();
        mPathB = new Path();
    }


    public void setmOp(Path.Op mOp) {
        this.mOp = mOp;
        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        mPathA.reset();
        mPathB.reset();
        mPathA.addRect(10, 10, (int) (mWidth * 0.7), (int) (mHeight * 0.7), Path.Direction.CW);
        mPathB.addCircle((int) (mWidth * 0.7), (int) (mHeight * 0.5), (int) (mHeight * 0.3), Path.Direction.CW);
        mPathA.op(mPathB, mOp);
        canvas.drawPath(mPathA, mPaint);
    }
}
