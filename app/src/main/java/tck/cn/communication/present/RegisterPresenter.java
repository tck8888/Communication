package tck.cn.communication.present;

import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import tck.cn.communication.model.User;
import tck.cn.communication.present.contact.RegisterContract;
import tck.cn.communication.utils.ThreadUtils;

/**
 * Description :subscribeOn方法是指定事件发出的线程，observeOn方法是指定事件消费的线程
 * <p>
 * Created by tck on 2016/10/15.
 */

public class RegisterPresenter implements RegisterContract.Presenter {

    public RegisterContract.View mView;

    public User user;

    public RegisterPresenter(RegisterContract.View view) {
        this.mView = view;
        user = new User();
    }
    @Override
    public void register(final String username, final String password) {

        user.setUsername(username);
        user.setPassword(password);

        user.signUp(new SaveListener<User>() {
            //Bmob中的回调方法都是在主线程中被调用的
            @Override
            public void done(final User user, BmobException e) {
                if (e == null) {
                    //成功了再去注册环信平台
                    ThreadUtils.runOnSubThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                EMClient.getInstance().createAccount(username, password);
                                //环信注册成功
                                ThreadUtils.runOnMainThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        mView.registerSuccess(username, password, true);
                                    }
                                });
                            } catch (final HyphenateException e1) {
                                e1.printStackTrace();
                                //将Bmob上注册的user给删除掉
                                user.delete();
                                //环信注册失败了
                                ThreadUtils.runOnMainThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        mView.registerSuccess(username, password, false);
                                    }
                                });
                            }
                        }
                    });
                } else {
                    //失败了，将结果告诉Activity
                    mView.registerSuccess(username, password, false);
                }
            }
        });
    }

}
