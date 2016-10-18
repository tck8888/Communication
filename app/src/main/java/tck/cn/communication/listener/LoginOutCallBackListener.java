package tck.cn.communication.listener;

import com.hyphenate.EMCallBack;

import tck.cn.communication.utils.ThreadUtils;

/**
 * Description :
 * <p>
 * Created by tck on 2016/10/17.
 */

public abstract class LoginOutCallBackListener implements EMCallBack {

    public abstract void onMainSuccess();

    public abstract void onMainError(int i, String s);

    @Override
    public void onSuccess() {
        ThreadUtils.runOnMainThread(new Runnable() {
            @Override
            public void run() {
                onMainSuccess();
            }
        });
    }

    @Override
    public void onError(final int i, final String s) {
        ThreadUtils.runOnMainThread(new Runnable() {
            @Override
            public void run() {
                onMainError(i, s);
            }
        });
    }

    @Override
    public void onProgress(int i, String s) {

    }
}
