package tck.cn.communication.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;

import com.hyphenate.util.DensityUtil;

import java.util.List;

import tck.cn.communication.R;
import tck.cn.communication.listener.IContactAdapter;
import tck.cn.communication.utils.StringUtils;

/**
 * Description :联系人界面侧边的自定义---搜索menu
 * <p>
 * Created by tck on 2016/10/17.
 */

public class SliderMenu extends View {
    private static final String[] SECTIONS = {"搜", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J"
            , "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private int mX;
    private int mY;
    private Paint mPaint;
    private TextView mTextView;
    private RecyclerView mRecyclerview;

    public SliderMenu(Context context) {
        this(context, null);
    }

    public SliderMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SliderMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextSize(DensityUtil.sp2px(getContext(), 10));
        mPaint.setTextAlign(Paint.Align.CENTER);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();

        mX = measuredWidth / 2;
        mY = measuredHeight / SECTIONS.length;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < SECTIONS.length; i++) {
            canvas.drawText(SECTIONS[i], mX, mY * (i + 1), mPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                /**
                 * 1.设置背景
                 * 2.显示点中的文本
                 * 3.显示定位的用户
                 */
                setBackgroundResource(R.drawable.slidebar_bg);
                scrollSlidebar(event.getY());
                break;
            case MotionEvent.ACTION_UP:
                /**
                 * 隐藏背景，影藏悬浮按钮
                 */
                setBackgroundColor(Color.TRANSPARENT);
                if (mTextView != null) {
                    mTextView.setVisibility(GONE);
                }

                break;

            default:
                break;
        }
        return true;
    }

    private void scrollSlidebar(float y) {
        int index = (int) (y / mY);
        if (index < 0) {
            index = 0;
        } else if (index > SECTIONS.length - 1) {
            index = SECTIONS.length - 1;
        }
        String section = SECTIONS[index];

        if (mTextView == null) {
            ViewGroup parent = (ViewGroup) getParent();
            mTextView = (TextView) parent.findViewById(R.id.tv_flaot);
            mRecyclerview = (RecyclerView) parent.findViewById(R.id.recyclerview);
        }
        mTextView.setVisibility(VISIBLE);
        mTextView.setText(section);

        RecyclerView.Adapter adapter = mRecyclerview.getAdapter();
        if (adapter instanceof IContactAdapter) {
            IContactAdapter contactAdapter = (IContactAdapter) adapter;
            List<String> data = contactAdapter.getData();
            for (int i = 0; i < data.size(); i++) {
                String s = data.get(i);
                if (section.equals(StringUtils.getInitial(s))) {
                    mRecyclerview.smoothScrollToPosition(i);
                    return;
                }
            }
        }


    }
}
