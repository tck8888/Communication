package tck.cn.communication.present;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;

import java.util.ArrayList;
import java.util.List;

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
        EMConversation conversation = EMClient.getInstance().chatManager().getConversation(contact);
        if (conversation != null) {
            //曾经有聊天过
            //那么获取最多最近的20条聊天记录，然后展示到View层
            //获取最后一条消息
            EMMessage lastMessage = conversation.getLastMessage();
            //获取最后一条消息之前的19条（最多）
            List<EMMessage> messageList = conversation.loadMoreMsgFromDB(lastMessage.getMsgId(), 19);
            mEMMessageList.clear();
            mEMMessageList.add(lastMessage);
            mEMMessageList.addAll(messageList);
        } else {
            mEMMessageList.clear();
        }
        mChatView.onInit(mEMMessageList);
    }
}
