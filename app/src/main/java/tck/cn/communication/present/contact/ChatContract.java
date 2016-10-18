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
    }

    public interface Presenter {

        void initChat(String contact);
    }
}
