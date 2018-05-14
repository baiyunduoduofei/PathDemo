package com.zhou.pathdemo;

import android.annotation.TargetApi;
import android.graphics.Path.FillType;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private PathFillTypeView mPathFillTypeView;
    private PathOPView mPathOPView;
    private TextView mNoteTv;
    private Button mChangeBtn;
    private int mIndex = 0;
    private int mCurrentType = 0;//当前类型
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            setChoice();
            mIndex++;
            mHandler.sendEmptyMessageDelayed(mIndex, 2000);
        }
    };

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void setChoice() {

        if (mCurrentType % 2 == 0) {
            mChangeBtn.setText("Path逻辑运算，不同的OP");
            mPathFillTypeView.setVisibility(View.GONE);
            mPathOPView.setVisibility(View.VISIBLE);
            switch (mIndex % 5) {
                case 0:
                    mPathOPView.setmOp(android.graphics.Path.Op.DIFFERENCE);
                    mNoteTv.setText("Op==Path.Op.DIFFERENCE");
                    break;
                case 1:
                    mPathOPView.setmOp(android.graphics.Path.Op.INTERSECT);
                    mNoteTv.setText("Op==Path.Op.INTERSECT");
                    break;
                case 2:
                    mPathOPView.setmOp(android.graphics.Path.Op.UNION);
                    mNoteTv.setText("Op==Path.Op.UNION");
                    break;
                case 3:
                    mPathOPView.setmOp(android.graphics.Path.Op.XOR);
                    mNoteTv.setText("Op==Path.Op.XOR");
                    break;
                case 4:
                    mPathOPView.setmOp(android.graphics.Path.Op.REVERSE_DIFFERENCE);
                    mNoteTv.setText("Op==Path.Op.REVERSE_DIFFERENCE");
                    break;

            }

        } else {
            mChangeBtn.setText("Path设置不同的fillType");
            mPathFillTypeView.setVisibility(View.VISIBLE);
            mPathOPView.setVisibility(View.GONE);
            switch (mIndex % 4) {
                case 0:
                    mPathFillTypeView.setFillType(FillType.WINDING);
                    mNoteTv.setText("FillType==FillType.WINDING");
                    break;
                case 1:
                    mPathFillTypeView.setFillType(FillType.EVEN_ODD);
                    mNoteTv.setText("FillType==FillType.EVEN_ODD");
                    break;
                case 2:
                    mPathFillTypeView.setFillType(FillType.INVERSE_WINDING);
                    mNoteTv.setText("FillType==FillType.INVERSE_WINDING");
                    break;
                case 3:
                    mPathFillTypeView.setFillType(FillType.INVERSE_EVEN_ODD);
                    mNoteTv.setText("FillType==FillType.INVERSE_EVEN_ODD");
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPathFillTypeView = findViewById(R.id.path_fillType_view);
        mPathOPView = findViewById(R.id.path_op_view);
        mNoteTv = findViewById(R.id.note_tv);
        mChangeBtn = findViewById(R.id.change_btn);
        mChangeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentType++;
            }
        });
        mHandler.sendEmptyMessage(mIndex);

    }
}
