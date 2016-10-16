package tck.cn.communication.utils;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Description :
 * <p>
 * Created by tck on 2016/10/16.
 */

public class ThreadUtils {

    private static Handler sHandler = new Handler(Looper.getMainLooper());

    private static Executor sExecutor = Executors.newSingleThreadExecutor();

    public static void runOnSubThread(Runnable runnable) {
        sExecutor.execute(runnable);
    }

    public static void runOnMainThread(Runnable runnable) {
        sHandler.post(runnable);

    }
}
