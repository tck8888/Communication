package tck.cn.communication.present;

import com.hyphenate.chat.EMClient;

import tck.cn.communication.listener.LoginOutCallBackListener;
import tck.cn.communication.present.contact.PluginContract;

/**
 * Description :
 * <p>
 * Created by tck on 2016/10/17.
 */

public class PluginPresenter implements PluginContract.Presenter {
    private PluginContract.PluginView mPluginView;

    public PluginPresenter(PluginContract.PluginView pluginView) {
        mPluginView = pluginView;
    }

    @Override
    public void logout() {
        EMClient.getInstance().logout(true, new LoginOutCallBackListener() {
            @Override
            public void onMainSuccess() {
                mPluginView.onLogout(EMClient.getInstance().getCurrentUser(), true, null);
            }

            @Override
            public void onMainError(int i, String s) {
                mPluginView.onLogout(EMClient.getInstance().getCurrentUser(), false, s);
            }
        });
    }
}
