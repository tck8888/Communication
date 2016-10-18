package tck.cn.communication.ui.activity;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sdsmdg.tastytoast.TastyToast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tck.cn.communication.R;
import tck.cn.communication.base.BaseActivity;
import tck.cn.communication.model.User;
import tck.cn.communication.present.AddFriendPresenter;
import tck.cn.communication.present.contact.AddFriendContract;
import tck.cn.communication.ui.activity.adpater.AddFriendAdapter;

public class AddFriendActivity extends BaseActivity implements SearchView.OnQueryTextListener, AddFriendContract.AddFriendView, AddFriendAdapter.OnAddFriendClickListener {

    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.no_data)
    ImageView mNoData;
    @BindView(R.id.activity_add_friend)
    LinearLayout mActivityAddFriend;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;
    private SearchView mSearchView;
    private AddFriendPresenter mAddFriendPresenter;

    @Override
    protected int getLayout() {
        return R.layout.activity_add_friend;
    }

    @Override
    protected void initData() {
        initToolbar();

        mAddFriendPresenter = new AddFriendPresenter(this);
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
        mSearchView = (SearchView) menuItem.getActionView();
        mSearchView.setQueryHint("搜好友");
        mSearchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        if (TextUtils.isEmpty(query)) {
            TastyToast.makeText(this, "请输入用户名再搜索！", TastyToast.LENGTH_SHORT, TastyToast.WARNING);
            return false;
        }

        /**
         * 搜索好友
         */
        mAddFriendPresenter.serachFriend(query);

        //隐藏软键盘
        InputMethodManager inputMethod = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethod.hideSoftInputFromWindow(mSearchView.getWindowToken(), 0);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        if (!TextUtils.isEmpty(newText)) {
            //TastyToast.makeText(this, newText, TastyToast.LENGTH_SHORT, TastyToast.WARNING);
        }
        return true;
    }

    @Override
    public void onSerachResult(List<User> list, List<String> contactsList, boolean isNotNull, String msg) {
        if (isNotNull) {
            mNoData.setVisibility(View.GONE);
            mRecyclerview.setVisibility(View.VISIBLE);
            mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
            AddFriendAdapter addFriendAdapter = new AddFriendAdapter(list, contactsList);
            addFriendAdapter.setOnAddFriendClickListener(this);
            mRecyclerview.setAdapter(addFriendAdapter);

        } else {
            mNoData.setVisibility(View.VISIBLE);
            mRecyclerview.setVisibility(View.GONE);
        }
    }

    @Override
    public void onAddResult(String usename, boolean isSuccess, String msg) {
        if (isSuccess) {
            TastyToast.makeText(this, "添加好友" + usename + "成功", TastyToast.LENGTH_SHORT, TastyToast.INFO);
        } else {
            TastyToast.makeText(this, "添加好友" + usename + "失败", TastyToast.LENGTH_SHORT, TastyToast.WARNING);

        }
    }

    @Override
    public void onAddClick(String username) {
        mAddFriendPresenter.addFriend(username);
    }
}
