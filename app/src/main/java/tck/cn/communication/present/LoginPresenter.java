package tck.cn.communication.present;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

import tck.cn.communication.present.contact.LoginContract;
import tck.cn.communication.utils.ThreadUtils;

/**
 * Description :处理登录
 * <p>
 * Created by tck on 2016/10/17.
 */

public class LoginPresenter implements LoginContract.Presenter {
    public LoginContract.View mView;

    public LoginPresenter(LoginContract.View view) {
        this.mView = view;
    }

    @Override
    public void login(final String username, final String password) {
        EMClient.getInstance().login(username, password, new EMCallBack() {
            @Override
            public void onSuccess() {
                ThreadUtils.runOnMainThread(new Runnable() {
                    @Override
                    public void run() {
                        mView.loginSuccess(username, password, true);
                    }
                });
            }

            @Override
            public void onError(int i, String s) {
                ThreadUtils.runOnMainThread(new Runnable() {
                    @Override
                    public void run() {
                        mView.loginSuccess(username, password, false);
                    }
                });
            }

            @Override
            public void onProgress(int i, String s) {

            }
        });

    }
}
