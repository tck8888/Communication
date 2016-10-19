package tck.cn.communication.present;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import tck.cn.communication.present.contact.ConversationContract;

/**
 * Description :会话的P层
 * <p>
 * Created by tck on 2016/10/19.
 */

public class ConversationPresenter implements ConversationContract.Presenter {

    private ConversationContract.ConversationView mConversationView;

    private List<EMConversation> mEMConversationList = new ArrayList<>();

    public ConversationPresenter(ConversationContract.ConversationView conversationView) {
        mConversationView = conversationView;
    }


    @Override
    public void initConversation() {
        Map<String, EMConversation> allConversations = EMClient.getInstance().chatManager().getAllConversations();
        mEMConversationList.clear();
        mEMConversationList.addAll(allConversations.values());
        /**
         * 排序，最近的时间在最上面
         */
        Collections.sort(mEMConversationList, new Comparator<EMConversation>() {
            @Override
            public int compare(EMConversation o1, EMConversation o2) {

                return (int) (o2.getLastMessage().getMsgTime()-o1.getLastMessage().getMsgTime());
            }
        });
        mConversationView.onInitConversation(mEMConversationList);
    }
}
