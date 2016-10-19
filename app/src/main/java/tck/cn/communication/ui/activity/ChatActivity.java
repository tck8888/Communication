package tck.cn.communication.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
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

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import tck.cn.communication.R;
import tck.cn.communication.base.BaseActivity;
import tck.cn.communication.present.ChatPresenter;
import tck.cn.communication.present.contact.ChatContract;
import tck.cn.communication.ui.activity.adpater.ChatAdapter;

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
    private String mUsername;
    private ChatAdapter mAdpater;

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
        mUsername = intent.getStringExtra("username");

        if (TextUtils.isEmpty(mUsername)) {
            TastyToast.makeText(this, "跟鬼聊呀，请携带username参数！", TastyToast.LENGTH_SHORT, TastyToast.ERROR);
            finish();
            return;
        }

        mTvTitle.setText("与" + mUsername + "聊天中");

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
        mChatPresenter.initChat(mUsername);

        EventBus.getDefault().register(this);
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
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdpater = new ChatAdapter(emMessageList);

        mRecyclerView.setAdapter(mAdpater);

        if (emMessageList.size() != 0) {
            mRecyclerView.scrollToPosition(emMessageList.size() - 1);
        }
    }

    @Override
    public void onUpdate(int size) {
        mAdpater.notifyDataSetChanged();
        if (size != 0) {
            mRecyclerView.smoothScrollToPosition(size - 1);
        }
    }


    /**
     * 发送按钮的点击事件的处理
     */
    @OnClick(R.id.btn_send)
    public void onClick() {
        String msg = mEtMsg.getText().toString().trim();
        mChatPresenter.sendMessage(mUsername, msg);
        mEtMsg.getText().clear();
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.toString().length() == 0) {
            mBtnSend.setEnabled(false);
        } else {
            mBtnSend.setEnabled(true);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EMMessage message) {
        /**
         * 判断当前这个消息是不是正在聊天的用户发给我的
         *  如果是，让ChatPresenter 更新数据
         *
         */
        String from = message.getFrom();
        if (from.equals(mUsername)) {
            mChatPresenter.updateData(mUsername);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

}
