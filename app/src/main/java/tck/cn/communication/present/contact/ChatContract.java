package tck.cn.communication.present.contact;

import com.hyphenate.chat.EMMessage;

import java.util.List;

/**
 * Description :
 * <p>
 * Created by tck on 2016/10/18.
 */

public interface ChatContract {

    public interface ChatView {
        void onInit(List<EMMessage> emMessageList);

        void onUpdate(int size);
    }

    public interface Presenter {

        void initChat(String contact);
        void updateData(String username);

        void sendMessage(String username, String msg);
    }
}
