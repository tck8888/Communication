package tck.cn.communication.present;

import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import tck.cn.communication.model.User;
import tck.cn.communication.present.contact.AddFriendContract;
import tck.cn.communication.utils.DBUtils;
import tck.cn.communication.utils.ThreadUtils;

/**
 * Description :
 * <p>
 * Created by tck on 2016/10/18.
 */

public class AddFriendPresenter implements AddFriendContract.Presenter {

    public AddFriendContract.AddFriendView mAddFriendView;

    public AddFriendPresenter(AddFriendContract.AddFriendView addFriendView) {
        mAddFriendView = addFriendView;
    }

    @Override
    public void serachFriend(String keyword) {
        final String currentUser = EMClient.getInstance().getCurrentUser();
        BmobQuery<User> bmobQuery = new BmobQuery<>();

        bmobQuery.addWhereStartsWith("username", keyword)
                .addWhereNotEqualTo("username", currentUser)
                .findObjects(new FindListener<User>() {
                    @Override
                    public void done(List<User> list, BmobException e) {
                        if (e == null && list != null && list.size() > 0) {
                            List<String> contacts = DBUtils.getContacts(currentUser);
                            //获取到数据
                            mAddFriendView.onSerachResult(list, contacts, true, null);
                        } else {
                            //没有找到数据
                            if (e == null) {
                                mAddFriendView.onSerachResult(null, null, false, "没有找到对应的用户。");
                            } else {
                                mAddFriendView.onSerachResult(null, null, false, e.getMessage());
                            }
                        }
                    }
                });
    }

    @Override
    public void addFriend(final String username) {
        ThreadUtils.runOnSubThread(new Runnable() {
            @Override
            public void run() {
                try {
                    EMClient.getInstance().contactManager().addContact(username,"想加您为好友，一起写代码！");
                    //添加成功（仅仅代表这个请求发送成功了，对方有没有同意是另外一会儿事儿）
                    onAddResult(username,true,null);
                } catch (HyphenateException e) {
                    //添加失败
                    e.printStackTrace();
                    onAddResult(username,false,e.getMessage());

                }
            }
        });
    }

    private void onAddResult(final String username, final boolean success, final String msg) {
        ThreadUtils.runOnMainThread(new Runnable() {
            @Override
            public void run() {
                mAddFriendView.onAddResult(username, success, msg);
            }
        });
    }
}
