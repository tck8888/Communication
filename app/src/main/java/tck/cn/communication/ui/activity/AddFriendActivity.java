package tck.cn.communication.ui.activity;


import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import tck.cn.communication.R;
import tck.cn.communication.base.BaseActivity;

public class AddFriendActivity extends BaseActivity implements SearchView.OnQueryTextListener {

    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.no_data)
    ImageView mNoData;
    @BindView(R.id.activity_add_friend)
    LinearLayout mActivityAddFriend;

    @Override
    protected int getLayout() {
        return R.layout.activity_add_friend;
    }

    @Override
    protected void initData() {
        initToolbar();
    }

    private void initToolbar() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_friend_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.serach);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("搜索友");
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

}
