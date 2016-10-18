package tck.cn.communication.widget;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import tck.cn.communication.R;

/**
 * Description :自定义联系人主界面组合布局
 * <p>
 * Created by tck on 2016/10/17.
 */

public class ContactLayout extends RelativeLayout {


    private TextView tvFlaot;
    private SliderMenu sliderMenu;
    private RecyclerView recyclerview;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    public ContactLayout(Context context) {
        this(context, null);
    }

    public ContactLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ContactLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(context).inflate(R.layout.contact, this, true);

        tvFlaot = (TextView) findViewById(R.id.tv_flaot);
        sliderMenu = (SliderMenu) findViewById(R.id.sliderMenu);
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefreshlayout);
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
    }

    public void setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener onRefreshListener) {
        mSwipeRefreshLayout.setOnRefreshListener(onRefreshListener);
    }

    public void setRefreshing(boolean refreshing) {
        mSwipeRefreshLayout.setRefreshing(refreshing);
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview.setAdapter(adapter);
    }


}
