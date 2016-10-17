package tck.cn.communication.present.contact;

/**
 * Description :
 * <p>
 * Created by tck on 2016/10/17.
 */

public interface LoginContract {

    interface View {
        void loginSuccess(String username, String password, boolean isSuccess);
    }

    interface Presenter {
        void login(String username, String password);
    }
}
