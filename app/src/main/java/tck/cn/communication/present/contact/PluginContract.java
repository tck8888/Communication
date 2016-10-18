package tck.cn.communication.present.contact;

/**
 * Description :
 * <p>
 * Created by tck on 2016/10/17.
 */

public interface PluginContract {

    public interface PluginView {

        void onLogout(String username, boolean success, String msg);
    }

    public interface Presenter {
        void logout();
    }
}
