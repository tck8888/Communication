package tck.cn.communication.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import butterknife.BindView;
import tck.cn.communication.R;
import tck.cn.communication.base.BaseActivity;

/**
 * 主界面
 */
public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.content)
    FrameLayout mContent;
    @BindView(R.id.activity_main)
    LinearLayout mActivityMain;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.bottom_bar)
    BottomNavigationBar mBottomBar;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        initToolbar();
        initBottombar();
    }

    /**
     * 初始化顶部工具栏
     */
    private void initToolbar() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
     * 初始化底部导航栏
     */
    private void initBottombar() {
        mBottomBar.setMode(BottomNavigationBar.MODE_FIXED);
        mBottomBar.addItem(new BottomNavigationItem(R.mipmap.contact_selected_2, "消息"))
                .addItem(new BottomNavigationItem(R.mipmap.conversation_selected_2, "联系人"))
                .addItem(new BottomNavigationItem(R.mipmap.plugin_selected_2, "动态"))
                .setFirstSelectedPosition(0)
                .initialise();
        mBottomBar
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC
                );
        mBottomBar.setTabSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuBuilder menuBuilder = (MenuBuilder) menu;
        menuBuilder.setOptionalIconsVisible(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                break;
        }
        return true;
    }


    @Override
    public void onTabSelected(int position) {

    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
