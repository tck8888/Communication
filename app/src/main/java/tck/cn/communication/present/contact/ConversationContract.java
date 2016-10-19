package tck.cn.communication.present.contact;

import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;

import java.util.List;

/**
 * Description :
 * <p>
 * Created by tck on 2016/10/19.
 */

public interface ConversationContract {
    public interface ConversationView {

        void onInitConversation(List<EMConversation> emConversationList);
    }

    public interface Presenter {
        void initConversation();
    }

}
