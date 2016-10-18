package tck.cn.communication.present.contact;

import java.util.List;

import tck.cn.communication.model.User;

/**
 * Description :
 * <p>
 * Created by tck on 2016/10/18.
 */

public interface AddFriendContract {

    public interface AddFriendView {
        void onSerachResult(List<User> list, List<String> contactsList, boolean isNotNull, String msg);

        void onAddResult(String usename, boolean isSuccess, String msg);
    }

    public interface Presenter {
        void serachFriend(String keyword);

        void addFriend(String username);
    }
}
