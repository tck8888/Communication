package tck.cn.communication.present;

import tck.cn.communication.present.contact.LoginContract;

/**
 * Description :
 * <p>
 * Created by tck on 2016/10/17.
 */

public class LoginPresenter implements LoginContract.Presenter {
    public LoginContract.View mView;

    public LoginPresenter(LoginContract.View view) {
        this.mView = view;
    }

    @Override
    public void login(String username, String password) {
        mView.loginSuccess(username, password, true);
    }
}
