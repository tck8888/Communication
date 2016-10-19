package tck.cn.communication.present;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import tck.cn.communication.listener.LoginOutCallBackListener;
import tck.cn.communication.present.contact.ChatContract;

/**
 * Description :
 * <p>
 * Created by tck on 2016/10/18.
 */

public class ChatPresenter implements ChatContract.Presenter {

    private ChatContract.ChatView mChatView;
    private List<EMMessage> mEMMessageList = new ArrayList<>();

    public ChatPresenter(ChatContract.ChatView chatView) {
        mChatView = chatView;
    }

    @Override
    public void initChat(String contact) {

        /**
         * 1. 如果曾经跟contact有聊天过，那么获取最多最近的20条聊天记录，然后展示到View层
         * 2. 如果没有聊天过，返回一个空的List
         */
        updateChatData(contact);
        mChatView.onInit(mEMMessageList);
    }

    @Override
    public void updateData(String username) {
        updateChatData(username);
        mChatView.onUpdate(mEMMessageList.size());
    }

    @Override
    public void sendMessage(String username, String msg) {
        EMMessage emMessage = EMMessage.createTxtSendMessage(msg, username);
        emMessage.setStatus(EMMessage.Status.INPROGRESS);
        mEMMessageList.add(emMessage);
        mChatView.onUpdate(mEMMessageList.size());
        emMessage.setMessageStatusCallback(new LoginOutCallBackListener() {
            @Override
            public void onMainSuccess() {
                mChatView.onUpdate(mEMMessageList.size());
            }

            @Override
            public void onMainError(int i, String s) {
                mChatView.onUpdate(mEMMessageList.size());
            }
        });

        EMClient.getInstance().chatManager().sendMessage(emMessage);


    }

    private void updateChatData(String contact) {
        EMConversation conversation = EMClient.getInstance().chatManager().getConversation(contact);
        if (conversation != null) {
            //需要将所有的未读消息标记为已读
            conversation.markAllMessagesAsRead();

            //曾经有聊天过
            //那么获取最多最近的20条聊天记录，然后展示到View层
            //获取最后一条消息
            EMMessage lastMessage = conversation.getLastMessage();
            //获取最后一条消息之前的19条（最多）
            int count = 19;
            if (mEMMessageList.size() >= 19) {
                count = mEMMessageList.size();
            }
            List<EMMessage> messageList = conversation.loadMoreMsgFromDB(lastMessage.getMsgId(), count);
            Collections.reverse(messageList);
            mEMMessageList.clear();
            mEMMessageList.add(lastMessage);
            mEMMessageList.addAll(messageList);
            Collections.reverse(mEMMessageList);
        } else {
            mEMMessageList.clear();
        }
    }
}
