package tck.cn.communication.ui.fragment;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import tck.cn.communication.R;
import tck.cn.communication.base.BaseFragment;
import tck.cn.communication.present.ConversationPresenter;
import tck.cn.communication.present.contact.ConversationContract;
import tck.cn.communication.ui.activity.ChatActivity;
import tck.cn.communication.ui.activity.MainActivity;
import tck.cn.communication.ui.activity.adpater.CoversationAdapter;

/**
 * Description : 消息界面
 * <p>
 * Created by tck on 2016/10/17.
 */

public class MessageFragment extends BaseFragment implements ConversationContract.ConversationView, View.OnClickListener, CoversationAdapter.OnItemClickListener {

    private RecyclerView mRecyclerView;
    private FloatingActionButton mFab;
    private ConversationPresenter mConversationPresenter;
    private CoversationAdapter mCoversationAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_message, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mFab = (FloatingActionButton) view.findViewById(R.id.fab);

        mFab.setOnClickListener(this);


        /**
         * 初始化会话列表
         */
        mConversationPresenter = new ConversationPresenter(this);
        mConversationPresenter.initConversation();

        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EMMessage message) {
        MainActivity activity = (MainActivity) getActivity();
        mConversationPresenter.initConversation();
    }

    /**
     * 悬浮按钮的点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        //将所有的会话全部比较为已读
        ObjectAnimator.ofFloat(mFab, "rotation", 0, 360).setDuration(1000).start();
        EMClient.getInstance().chatManager().markAllConversationsAsRead();
        MainActivity activity = (MainActivity) getActivity();
        activity.updateUnreadCount();
        if (mCoversationAdapter != null) {
            mCoversationAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        mCoversationAdapter = null;
    }

    @Override
    public void onInitConversation(List<EMConversation> emConversationList) {

        if (mCoversationAdapter == null) {
            mCoversationAdapter = new CoversationAdapter(emConversationList);
            mRecyclerView.setAdapter(mCoversationAdapter);
            mCoversationAdapter.setOnItemClickListener(this);
        } else {
            mCoversationAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mCoversationAdapter != null) {
            mCoversationAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public void onItemClick(EMConversation conversation) {
        String userName = conversation.getUserName();
        MainActivity activity = (MainActivity) getActivity();
        activity.startActivity(ChatActivity.class, false, userName);
    }
}
