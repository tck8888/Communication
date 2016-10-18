package tck.cn.communication.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hyphenate.chat.EMMessage;
import com.sdsmdg.tastytoast.TastyToast;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import tck.cn.communication.R;
import tck.cn.communication.base.BaseActivity;
import tck.cn.communication.present.ChatPresenter;
import tck.cn.communication.present.contact.ChatContract;

/**
 * 聊天界面功能的简单实现
 */
public class ChatActivity extends BaseActivity implements ChatContract.ChatView, TextWatcher {

    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.et_msg)
    EditText mEtMsg;
    @BindView(R.id.btn_send)
    Button mBtnSend;

    private ChatPresenter mChatPresenter;

    @Override
    protected int getLayout() {
        return R.layout.activity_chat;
    }

    @Override
    protected void initData() {
        mChatPresenter = new ChatPresenter(this);
        initToolbar();

        Intent intent = getIntent();
        //聊天对象
        String username = intent.getStringExtra("username");

        if (TextUtils.isEmpty(username)) {
            TastyToast.makeText(this, "跟鬼聊呀，请携带username参数！", TastyToast.LENGTH_SHORT, TastyToast.ERROR);
            finish();
            return;
        }

        mTvTitle.setText("与" + username + "聊天中");

        mEtMsg.addTextChangedListener(this);

        String msg = mEtMsg.getText().toString().trim();

        if (TextUtils.isEmpty(msg)) {
            mBtnSend.setEnabled(false);
        } else {
            mBtnSend.setEnabled(true);
        }

        /**
         * 显示最多最近的20条聊天记录，然后定位RecyclerView到最后一行
         */
        mChatPresenter.initChat(username);
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
    public void onInit(List<EMMessage> emMessageList) {

    }


    @OnClick(R.id.btn_send)
    public void onClick() {
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.toString().length() == 0) {
            mBtnSend.setEnabled(false);
        } else {
            mBtnSend.setEnabled(true);
        }
    }
}
