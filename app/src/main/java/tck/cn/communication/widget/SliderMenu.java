package tck.cn.communication.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.hyphenate.util.DensityUtil;

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

    public SliderMenu(Context context) {
        this(context, null);
    }

    public SliderMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SliderMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextSize(DensityUtil.sp2px(getContext(),10));
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
        for (int i = 0; i <SECTIONS.length ; i++) {
            canvas.drawText(SECTIONS[i],mX,mY*(i+1),mPaint);
        }
    }
}
