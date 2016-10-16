package tck.cn.communication.present.contact;

/**
 * Description :
 * <p>
 * Created by tck on 2016/10/15.
 */

public interface RegisterContract {
    interface View  {
        void registerSuccess(String username, String password, boolean isSuccess);

        void showProgress();

        void hideProgress();
    }

    interface Presenter  {
        void register(String username, String password);
    }
}
